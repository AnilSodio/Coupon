/**
 * Created by thh on 15/9/6.
 */

;(function($) {

    $.fn.countdown = function(o) {
        var opts = $.extend({
            time: 60000,
            text: "$time秒后重新发送",
            init: function() {},
            callback: function() {}
        }, o || {});
        this.each(function() {
            var $this = $(this), html = $this.html(), nTime = opts.time/1000, timer;
            if ($this.is(":disabled")) return;
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
        });
    };

})(jQuery);

function page(page, size){
	jQuery("#pageNumber").val(page);
	jQuery("#searchForm").submit();
}

$(document).on("click", "[rel=dialog]", function() {
	var __this = $(this)[0], $dropdown = $(this).parents(".dropdown");
    if ($dropdown.length > 0) $dropdown.removeClass("open");
    $.jBox.open("iframe:"+__this.href, __this.title, 700, 500, {id: "relDialog", buttons: {}});
	return false;
});
