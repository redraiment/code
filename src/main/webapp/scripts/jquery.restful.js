var RESTful;

(function($) {
    var methods = {
        "create": "post",
        "update": "put",
        "delete": "delete",
        "query": "get"
    };

    var dummy = function() {};

    RESTful = function(url) {
        var me = this;
        me.url = url;
        me.type = 'json';
        $.each(methods, function(rest, method) {
            me[rest] = function(params, fn) {
                $.ajax({
                    type: method,
                    url: me.url,
                    data: params || '',
                    success: fn || dummy,
                    dataType: me.type
                });
            };
        });
    };
})(jQuery);
