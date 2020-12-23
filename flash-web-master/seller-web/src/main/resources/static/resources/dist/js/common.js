/**
 * Created by thh on 15/11/18.
 */

var THINK = (function(THINK) {

    THINK.msg = {
        loadingToast: function(o) {
            var o = $.extend({
                zIndex: 15,
                html: '数据加载中'
            }, o || {});
            this.destroy();
            $("body").append('<div id="loadingToast" class="weui_loading_toast">\
                <div class="weui_mask_transparent"></div>\
                <div class="weui_toast">\
                    <div class="weui_loading">\
                        <div class="weui_loading_leaf weui_loading_leaf_0"></div>\
                        <div class="weui_loading_leaf weui_loading_leaf_1"></div>\
                        <div class="weui_loading_leaf weui_loading_leaf_2"></div>\
                        <div class="weui_loading_leaf weui_loading_leaf_3"></div>\
                        <div class="weui_loading_leaf weui_loading_leaf_4"></div>\
                        <div class="weui_loading_leaf weui_loading_leaf_5"></div>\
                        <div class="weui_loading_leaf weui_loading_leaf_6"></div>\
                        <div class="weui_loading_leaf weui_loading_leaf_7"></div>\
                        <div class="weui_loading_leaf weui_loading_leaf_8"></div>\
                        <div class="weui_loading_leaf weui_loading_leaf_9"></div>\
                        <div class="weui_loading_leaf weui_loading_leaf_10"></div>\
                        <div class="weui_loading_leaf weui_loading_leaf_11"></div>\
                    </div>\
                    <p class="weui_toast_content">' + o.html + '</p>\
                </div>\
            </div>');
        },
        destroy: function() {
            $("#loadingToast").remove();
        },
        toast: function(o) {
            var o = $.extend({
                zIndex: 15,
                html: '已完成',
                time: 2500,
                iconHtml: '<i class="weui_icon_toast"></i>',
                callback: function(){}
            }, o || {});
            var $tips = $('<div id="toast">\
                            <div class="weui_mask_transparent"></div>\
                            <div class="weui_toast">' + o.iconHtml + '\
                                <p class="weui_toast_content">' + o.html + '</p>\
                            </div>\
                        </div>');
            $("body").append($tips);
            var $toast = $tips.find(".weui_toast");
            console.log($toast.outerWidth());
            console.log($toast);
            setTimeout(function() {
                $toast.css({
                    "margin-left": -($toast.outerWidth()/2),
                    "margin-top": -($toast.height()/2)
                });
            });

            setTimeout(function() {
                $tips.animate({
                    opacity: 0
                }, 500, null, function() {
                    $tips.remove();
                    o.callback();
                });
            }, o.time);
        },
        share: function() {
            var o = $.extend({
                time: 2500
            }, o || {});
            var $tips = $('<div id="shareDialog" class="mask">\
                            <div class="share-dialog-cont"></div>\
                            <div class="text-center pt-25"><a id="closeShareDialog" href="javascript:;" class="c-lightRad">关闭提示</a> </div>\
                        </div>');
            $("body").append($tips);
            $("#closeShareDialog").click(function() {
                $("#shareDialog").remove();
            });
        }
    };

    THINK.actionSheet = function(o) {
        var html = '<div id="actionSheet_wrap" class="window-sheet">\
            <div class="weui_mask_transition" id="weui_mask"></div>\
            <div class="weui_actionsheet bg-white p-10" id="weui_actionsheet">\
                <a id="actionsheet_cancel" href="javascript:;" class="c-gray fz-m"><i class="icon-x"></i></a>\
                <div id="weui_actionsheet_content"></div>\
            </div>\
        </div>', $sheet, $was, $mask, $main = $(".container"), wtop = $(window).scrollTop();

        var hideActionSheet = function (weuiActionsheet, mask) {

            weuiActionsheet.removeClass('weui_actionsheet_toggle');
            weuiActionsheet.on('transitionend', function () {
                $main.css({height: "auto", overflow: "visible"});
                $("body,html").scrollTop(wtop);
                weuiActionsheet.hide();
                setTimeout(function() {
                    mask.hide();
                    $("#actionSheet_wrap").remove();
                }, 180);
            }).on('webkitTransitionEnd', function () {
                $main.css({height: "auto", overflow: "visible"});
                $("body,html").scrollTop(wtop);
                weuiActionsheet.hide();
                setTimeout(function() {
                    mask.hide();
                    $("#actionSheet_wrap").remove();
                }, 180);
            })
        };

        $("body").append(html);
            $mask = $("#weui_mask");
            $sheet = $("#weui_actionsheet_content");
            $was = $('#weui_actionsheet');
        $sheet.html(o.html);
        $mask.show().addClass('weui_fade_toggle');
        var height = Math.max($mask.height(), ($was.height() + 20));

        setTimeout(function() {
            $was.addClass('weui_actionsheet_toggle');
            $mask.click(function () {
                hideActionSheet($was, $mask);
            });
            $('#actionsheet_cancel').click(function () {
                hideActionSheet($was, $mask);
            });
            $was.unbind('transitionend').unbind('webkitTransitionEnd');
            $main.css({height: height, overflow: "hidden"});

            $was.on('transitionend', function () {
                $was.css({
                    position: "absolute",
                    top: (function() {
                        return $was.offset().top;
                    }()),
                    bottom: "auto"
                });
            }).on('webkitTransitionEnd', function () {
                $was.css({
                    position: "absolute",
                    top: (function() {
                        return $was.offset().top;
                    }()),
                    bottom: "auto"
                });
            });
        }, 20);

        return {
            hideActionSheet: function() {
                hideActionSheet($was, $mask);
            }
        }

    };

    THINK.dialog = function(o) {
        var html, btns = "", destroyDialog, $dialog;
        var o = $.extend({
            title: "",
            html: "",
            maskClose: false
        }, o || {});
        switch(o.type)
        {
            case "alert":
                btns = '<div class="weui_dialog_ft"><a href="javascript:;" class="weui_btn_dialog primary">确定</a></div>';
                break;
            case "confirm":
                btns = '<div class="weui_dialog_ft">\
                            <a href="javascript:;" class="weui_btn_dialog default">取消</a>\
                            <a href="javascript:;" class="weui_btn_dialog primary">确定</a>\
                        </div>';
                break;
            default: {
                o.cls = "dialog-default";
            }
        }
        html = '<div class="weui_dialog_confirm '+ (o.cls || ' ') +'" id="wei-dialog">\
            <div class="weui_mask"></div>\
            <div class="weui_dialog">\
                <div class="weui_dialog_hd"><strong class="weui_dialog_title">' + o.title + '</strong></div>\
                <div class="weui_dialog_bd">' + o.html + '</div>' + btns +
            '</div>\
        </div>';

        $("body").append(html);
        $dialog = $("#wei-dialog");

        destroyDialog = function() {
            $dialog.remove();
        }

        $dialog.find(".weui_btn_dialog").click(function() {
            if ($(this).hasClass("primary") && o.callback) {
                o.callback.call();
            }
            destroyDialog();
        });
        if (o.maskClose) {
            $dialog.find(".weui_mask").click(function() {
                destroyDialog();
            });
        }
        if (o.type == "confirm" && o.closed) {
            $dialog.find(".default").click(function() {
                o.closed.call();
                destroyDialog();
            });
        }
        return {
            close: destroyDialog
        }
    };

    THINK.initChkList = function() {
        var $lists = $(".chk-list");
        var initStatus = function($inputs) {
            $inputs.each(function() {
                var $input = $(this);
                if ($input.is(":checked")) {
                    $input.parent("label").addClass("active");
                } else {
                    $input.parent("label").removeClass("active");
                }
            });
        };
        $lists.each(function() {
            var $this = $(this), $inputs = $this.find("input");
            if ($this.attr("chkready")) return false;
            initStatus($inputs);
            $inputs.change(function() {
                if ($this.hasClass("radio")) {
                    $inputs.not(this).prop("checked", false);
                }
                initStatus($inputs);
            });
            $this.attr("chkready", "1");
        });
    };

    THINK.adjustInit = function() {
        var $wrap = $(".number-mod");
        $wrap.each(function() {
            var $this = $(this), $add = $this.find(".add-number"), $minus = $this.find(".minus-number"), $ipunt = $this.find(".number");
            $add.click(function() {
                if (parseInt($ipunt.val()) > 0) {
                    $ipunt.val(parseInt($ipunt.val()) + 1);
                } else {
                    $ipunt.val(1);
                }
                $ipunt.change();
            });
            $minus.click(function() {
                if (parseInt($ipunt.val()) > 1) {
                    $ipunt.val(parseInt($ipunt.val()) - 1);
                } else {
                    $ipunt.val(1);
                }
                $ipunt.change();
            });
        });
    };

    THINK.selectAll = function(o) {
        $(o.selector).each(function() {
            var $this = $(this), cid = $(this).attr("chk-id"), $chk = $("input[chk-group='" + cid +"']");
            $this.change(function() {
                if ($this.is(":checked")) {
                    $('input').icheck('check');
                    $chk.prop("checked", true).icheck("checked");
                } else {
                    $chk.prop("checked", false).icheck('unchecked');
                }
                o.changeEvent.call($chk);
            });
            $chk.change(function() {
                if ($chk.length == $chk.filter(":checked").length) {
                    $this.prop("checked", true).icheck('checked');
                } else {
                    $this.prop("checked", false).icheck('unchecked');
                }
                o.changeEvent.call($chk);
            });
        });

//        $(o.selector).each(function() {
//            var $this = $(this), cid = $(this).attr("chk-id"), $chk = $("input[chk-group='" + cid +"']");
//            $this.change(function() {
//                if ($this.is(":checked")) {
//                    $chk.prop("checked", true);
//                } else {
//                    $chk.prop("checked", false);
//                }
//                o.changeEvent.call($chk);
//            });
//            $chk.change(function() {
//                if ($chk.length == $chk.filter(":checked").length) {
//                    $this.prop("checked", true);
//                } else {
//                    $this.prop("checked", false);
//                }
//                o.changeEvent.call($chk);
//            });
//        });
    };

    // 倒计时
    THINK.countdown = function(o) {
        var opts = $.extend({
            time: 60000,
            text: "$time秒后重试",
            init: function() {},
            callback: function() {}
        }, o || {});
        var $this = $(o.selector), html = $this.html(), nTime = opts.time/1000, timer;
        $this.attr("disabled", true);
        opts.init.call($this);
        timer = setInterval(function() {
            nTime--;
            if (nTime == 0) {
                $this.removeAttr("disabled").html(html);
                clearInterval(timer);
                opts.callback.call($this);
            } else {
                $this.html(function() {
                    if (/\$time/.test(opts.text)) {
                        return opts.text.replace("$time", nTime);
                    } else {
                        return nTime + opts.text;
                    }
                });
            }
        }, 1000);
    };

    THINK.selectMenu = function(o) {
        var opts = $.extend({
            selector: "",
            callback: function() {}
        }, o || {});

        $(opts.selector).each(function() {
            var $this = $(this), $sel = $this.find("select"), $selected = $sel.find("option[selected]"), $text = $this.find("input");

            if ($this.parent().hasClass("select-box")) {
                $this.change();
            } else {
                if ($selected.length > 0) {
                    $text.val($selected.text());
                }
                $this.bind("click change", function() {
                    $text.val($sel.find("option:selected").text());
                });
            }

        });
    };

    return THINK;

}(THINK || {}));

function sidePanel(html){
    var $container = $("body"), stack = [], wh = $(window).height(), $main = $(".container");

    var $tpl = $("<div class='page s-panel'>" + html + "<div>").addClass('slideIn');
    $container.append($tpl);
    stack.push($tpl);
    $tpl.css("max-height", wh);

    $($tpl).on('webkitAnimationEnd', function (){
        togglePanel($tpl);
    }).on('animationend', function (){
        togglePanel($tpl);
    });

    var hidePanel = function() {
        $($tpl).addClass("slideOut");
    }

    var togglePanel = function(o) {
        if (o.hasClass("slideOut")) {
            o.remove();
            $main.show();
        } else {
        	$(this).removeClass('slideIn').removeClass('s-panel');
            $main.hide();
        }
    }
    return {
        obj: $tpl,
        destroy: hidePanel
    }
}