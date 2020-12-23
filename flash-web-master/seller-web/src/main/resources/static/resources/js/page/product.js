/**
 * Created by thh on 16/4/8.
 */


var tpl = '<div class="sku-sub-group">\
            <h3 class="sku-group-title">\
                <input type="hidden" class="select2-offscreen" name="sku_name" tabindex="-1" />\
                <a class="js-remove-sku-group remove-sku-group">×</a>\
            </h3>\
            <div class="js-sku-atom-container sku-group-cont">\
                    <div>\
                        <div class="js-sku-atom-list sku-atom-list"></div>\
                        <a class="js-add-sku-atom add-sku" href="javascript:;">+添加</a>\
                    </div>\
                </div>\
            </div>';

var subTpl = '{@each values as item}\
                <div class="sku-atom"><span data-atom-id="${item.id}">${item.text}</span>\
                    <div class="atom-close close-modal small js-remove-sku-atom">×</div>\
                    <div class="upload-img-wrap hide">\
                        <div class="arrow"></div>\
                        <div style="position:relative;" class="js-upload-container">\
                            <div class="add-image js-btn-add">+</div>\
                        </div>\
                    </div>\
                </div>\
               {@/each}';


var tableTpl = '<table class="table-sku-stock">\
    <tr>\
        {@each values as item}\
        <th class="text-center">${item.text}</th>\
        {@/each}\
        <th class="th-price">价格（元）</th>\
        <th class="th-code">接单侠提成比例(%)</th>\
        <th class="text-right">收益</th>\
    </tr>\
    {@each values as items,index}\
        {@if index == 0}{@each items.child as item,index}\
            {@if rows1.length > 0}{@each rows1 as subItem,nIndex}\
                {@if rows2.length > 0}{@each rows2 as minItem,sIndex}\
                <tr>\
                    {@if sIndex == 0 && nIndex == 0}<td rowspan="${rows1.length * rows2.length}" data-atom-id="${item.id}">${item.text}</td>{@/if}\
                    {@if sIndex == 0}<td rowspan="${rows2.length}" data-atom-id="${subItem.id}">${subItem.text}{@/if}</td>\
                    <td rowspan="1" data-atom-id="${minItem.id}">${minItem.text}</td>\
                    <td>\
                        <input type="text" maxlength="10" value="" class="js-price input-mini form-control" name="sku_price_${item.id}_${subItem.id}_${minItem.id}">\
                    </td>\
                    <td><input type="text" maxlength="9" value="" class="js-stock-num input-mini form-control" required name="stock_num_${item.id}_${subItem.id}_${minItem.id}"></td>\
                    <td class="text-right">0</td>\
                </tr>\
                {@/each}{@else}\
                <tr>\
                    {@if nIndex == 0}<td rowspan="${rows1.length}" data-atom-id="${item.id}">${item.text}</td>{@/if}\
                    <td rowspan="1" data-atom-id="${subItem.id}">${subItem.text}</td>\
                    <td>\
                        <input type="text" maxlength="10" value="" class="js-price input-mini form-control" name="sku_price_${item.id}_${subItem.id}">\
                    </td>\
                    <td><input type="text" maxlength="9" value="" class="js-stock-num input-mini form-control" name="stock_num_${item.id}_${subItem.id}"></td>\
                    <td class="text-right">0</td>\
                </tr>\
                {@/if}\
            {@/each}{@else}\
                <tr>\
                    <td rowspan="1" data-atom-id="${item.id}">${item.text}</td>\
                    <td>\
                        <input type="text" maxlength="10" value="" class="js-price input-mini form-control {required:true,minlength:5}" name="sku_price_${item.id}">\
                    </td>\
                    <td><input type="text" maxlength="9" value="" class="js-stock-num input-mini form-control" name="stock_num_${item.id}"></td>\
                    <td class="text-right">0</td>\
                </tr>\
            {@/if}\
    {@/each}{@/if}{@/each}\
</table>';



$(".js-add-sku-group").click(function () {
	categorySelectEvent.call(this);
});

function categorySelectEvent(isInit, $group){
	var $this = $(this), $parent = $this.parents(".js-sku-group-opts"), $list = $parent.siblings(".js-sku-list-container"), html = "";

    var $html = isInit? $group : $(juicer(tpl, {})), $input;
    $list.append($html);

    $input = $html.find(".sku-group-title input");

    $.get("/seller/loadGoodsSpec?v="+Math.random(),function(data,status){
    	$input.select2({
            width: 300,
            data: data,
            createSearchChoice: function (term, data) {
                if ($(data).filter(function () {
                    return this.text.localeCompare(term) === 0;
                }).length === 0) {
                    return {id: term, text: term};
                }
            }
        });
    	if (!isInit) $input.select2("open");
      });
    
    $input.on("change", function (e) {
        var __data = $(this).select2("data");

        $html.find(".js-sku-atom-container").show().attr("sid", __data.id);

    }).on("select2-selecting", function (e) {
        if ($(".js-sku-atom-container[sid=" + e.val + "]").length > 0) {
            return false;
        }
    });

    $html.find(".js-add-sku-atom").popover({
        placement: "bottom",
        html: true,
        animation: false,
        content: '<input type="hidden" id="category" name="category" /> <input type="button" value="确定" class="btn btn-primary btn-mini js-save"> <input type="button" value="取消" class="btn btn-mini btn-default js-cancel">',
        template: '<div class="popover popover-content-subBox" role="tooltip"><div class="arrow"></div><div class="popover-content"></div></div>'
    }).on('shown.bs.popover', function () {
    	
    	var sid = $html.find(".js-sku-atom-container").attr("sid");    	
    	$.get("/seller/loadGoodsSpecProperty?goodsSpecId="+sid+"&sellerId="+sellerId+"&v="+Math.random(),function(data,status){
    		$("#category").select2({
                width: 200,
                data: data || [],
                maximumSelectionSize: 5,
                multiple: true,
                createSearchChoice: function (term, data) {
                    if ($(data).filter(function () {
                        return this.text.localeCompare(term) === 0;
                    }).length === 0) {
                        return {id: "", text: term};
                    }
                }
            }).on("select2-selecting", function(e) {
            	if (!e.val) {
            		$.get("/seller/addGoodsSpecProperty?goodsSpecId="+sid+"&sellerId="+sellerId+"&propertyValue="+e.object.text+"&v="+Math.random(),
            			function(data,status) {
            				e.object.id = e.val = data;
            	      });            		
            	}
            }).select2("open");
	      });
    	
        $html.find(".js-sku-atom-container").find(".js-save").click(function () {

            var selector = $("#category").select2("data"), selectData = [];
            $.each(selector, function (i) {
                if ($("span[data-atom-id=" + selector[i].id + "]").length == 0) {
                    selectData.push(selector[i]);
                }
            });

            var $subHtml = $(juicer(subTpl, {values: selectData})), data;//JSON.stringify($("#category").select2("data"))})

            $html.find(".js-sku-atom-list").append($subHtml);

            drawTable();

            $("#category").select2("destroy");

            $html.find(".js-add-sku-atom").click();

        });
        $html.find(".js-sku-atom-container").find(".js-cancel").click(function() {
            $("#category").select2("destroy");
            $html.find(".js-add-sku-atom").click();
        });


    });
}

function initCategorySelect() {
	$.each($(".sku-sub-group"), function() {
		var $this = $(this);
		categorySelectEvent.call($(".js-add-sku-group"), true, $this);
	});
}

function getCategoryData() {
    var data = [];
    $(".sku-sub-group").each(function (i) {
        var $list = $(this).find("[data-atom-id]"), __text = "";
        if ($list.length > 0) {
            var child = [];
            $list.each(function (n) {
                child.push({id: $(this).attr("data-atom-id"), text: $(this).text()});
            });
            $(this).find(".select2-chosen").length > 0 ? __text = $(this).find(".select2-chosen").text() : __text = $(this).find("[name=sku_name]")[0].title;
            data.push({id: $(this).find("[name=sku_name]").val(), text: __text, child: child});
        }
    });
    return data;
}

function drawTable() {
    var data = getCategoryData();
    if (data.length > 0) {
        $("#stock-region").html($(juicer(tableTpl, {values: data, rows1: (data[1] ? data[1].child : []), rows2:  (data[2] ? data[2].child : [])})));
        $("#stockTables").removeClass("hidden");
    } else {
        $("#stock-region").empty();
        $("#stockTables").addClass("hidden");
    }

}

$(".js-sku-list-container").on("click",".js-remove-sku-group",function() {
    $(this).parents(".sku-sub-group").remove();
    drawTable();
});

$(".js-sku-list-container").on("click",".js-remove-sku-atom",function(){
    $(this).parents(".sku-atom").remove();
    drawTable();
});


var labelTpl = '{@each items as item,index}\
    <span>${item}<i class="remove js-remove-label">×</i></span>\
    {@/each}'

$(".js-add-label").popover({
    placement: "bottom",
    html: true,
    animation: false,
    content: '<input type="type" style="width: 200px" id="labelText" /> <input type="button" value="确定" class="btn btn-primary btn-mini js-save"> <input type="button" value="取消" class="js-cancel btn btn-mini btn-default">',
    template: '<div class="popover popover-content-subBox" role="tooltip"><div class="arrow"></div><div class="popover-content"></div></div>'
}).on('shown.bs.popover', function () {
    var $box = $("#labelContainer"), $text = $("#labelText"), $input = $box.find("input:hidden"), valArr = $input.val() ? $input.val().split(","): [];
    $box.find(".js-save").click(function() {
        if($.trim($text.val())) {
            valArr.push($text.val());
            if (valArr.length <= $input.attr("maxCount")) {
                $input.val(valArr.join());
                $("#labelItems").html(juicer(labelTpl, {items: valArr}));
            } else {
                valArr.pop()
            }
        }
        $(".js-add-label").click();
    });
    $box.find(".js-cancel").click(function() {
        $(".js-add-label").click();
    });
});

$("#labelItems").on("click",".js-remove-label",function(){
    var $input = $("#labelContainer").find("input:hidden"), valArr = $input.val().split(",");
    valArr.splice($(this).parent().index(),1);
    $("#labelItems").html(juicer(labelTpl, {items: valArr}));
    $input.val(valArr.join());
});


function initUpload(inputId, fileId, buttonId, viewId) {
    var $input = $("#" + inputId), $file = $("#" + fileId), $button = $("#" + buttonId), $view = $("#" + viewId), uploadFun;
    $button.click(function() {
    	$("#" + fileId).click();
    });
    uploadFun = function() {
    	var $file = $(this);
        if (!$.trim($file.val())) return false;
    	if ($view.find(".item").length == $input.attr("maxCount")) {
            alert("图片上传数量超出最大值！");
            return false;
        }
    	
        $.ajaxFileUpload({
            url: '/upload.htm', //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: fileId, //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            success: function (data, status)  //服务器成功响应处理函数
            {
            	$view.append('<span class="item"><img src="'+ data.url +'" /><i class="remove js-remove-upload">×</i></span>');
                if ($.trim($input.val())) {
                    $input.val($input.val() + "*" + data.url);
                } else {
                    $input.val(data.url);
                }
            	
                if (typeof (data.error) != 'undefined') {
                    if (data.error == 1) {
                        alert(data.message);
                    } 
                }
                $file.val("").empty();
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                alert(e);
            }
        });
    };
    
    $("body").on("change", "#" + fileId, function() {
    	uploadFun.call(this);
    });
    $view.on("click",".js-remove-upload",function(){
        var valArr = $input.val().split("*");
        valArr.splice($(this).parent().index(),1);
        $(this).parent().remove();
        $input.val(valArr.join("*"));
    });
}

initUpload("goodsCoverImage","goodsCoverFile","goodsCoverButton","viewCoverBox");
initUpload("goodsImage","goodsImageFile","goodsImageButton","viewPicBox");

$("#stock-region").on("change", "input", function(){
	var $tr = $(this).parents("tr"), priceVal = $tr.find(".js-price").val(), percent = $tr.find(".js-stock-num").val();
	
	if(Number(priceVal) != NaN && Number(percent) != NaN){
		$tr.find(".text-right").html(priceVal * percent / 100);
	}
});

