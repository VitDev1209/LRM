var GlobalDataTable = (function () {

    var tables = {};

    function init(config) {
        var table = $('#' + config.tableId).DataTable({
            processing: true,
            serverSide: config.serverSide || false,
            paging: true,
            searching: true,
            info: false,
            lengthChange: false,
            pageLength: config.pageLength || 5,

            ajax: config.url ? {
                url: config.url,
                type: 'GET',
                data: function (d) {
                    if (config.params) {
                        $.extend(d, config.params);
                    }
                }
            } : null,

            columns: config.columns
        });

        tables[config.tableId] = table;
        return table;
    }

    function reload(tableId, newParams) {
        if (newParams) {
            tables[tableId].settings()[0].ajax.data = function (d) {
                $.extend(d, newParams);
            };
        }
        tables[tableId].ajax.reload();
    }

    return {
        init: init,
        reload: reload
    };

})();
