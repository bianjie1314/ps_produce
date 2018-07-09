!(function ($) {
    /**
     * $.myAjax AJAX异步提交二次封装
     * @require: window.jQuery
     */
    $.myAjax = function(opt) {
        var hasComFn = opt.hasComFn || false;
        $.ajax({
            url:  opt.sUrl || "",
            type: opt.sType || "GET",
            dataType: opt.sDataType || "json",
            data: opt.oData || null,
            async:opt.sAsync == null ? true : opt.sAsync,
            beforeSend: function(oXHR, oSettings) {
            },
            success: function(oData) {
                opt.fnSuccess(oData)
            },
            error: function(oXHR, sStatus) {
            	if (opt.fnError) {
            		opt.fnError(oXHR)
            	}
            },
            complete: function(oXHR, sStatus) {
            	if (opt.fnComplete) {
            		opt.fnComplete();
            	}
            }
        });
    };
})(jQuery);

function formatDate(date, fmt) {
    function pad(value) {
        return (value.toString().length < 2) ? '0' + value : value;
    }
    return fmt.replace(/%([a-zA-Z])/g, function (_, fmtCode) {
        switch (fmtCode) {
        case 'Y':
            return date.getFullYear();
        case 'M':
            return pad(date.getMonth()+1);
        case 'd':
            return pad(date.getDate());
        case 'H':
            return pad(date.getHours());
        case 'm':
            return pad(date.getMinutes());
        case 's':
            return pad(date.getSeconds());
        default:
            throw new Error('Unsupported format code: ' + fmtCode);
        }
    });
}