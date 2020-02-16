/**
 * icheck 、datatable - app
 */
var App = function () {

    // iCheck
    let _checkboxAll;
    let _checkbox;

    // 删除的id 数组
    let _idArray;

    /**
     * 激活iCheck，初始化iCheck
     */
    let handlerInitCheckbox = function () {
        // 激活 iCheck
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });

        // 全选checkbox
        _checkboxAll = $('input[type="checkbox"].minimal.checkbox-all');
        // 列表中的checkbox
        _checkbox = $("tbody").find("[type='checkbox']").not("[disabled]");
    };

    /**
     * iCheck 全选功能
     */
    let handlerCheckboxAll = function () {
        // 绑定全选控制端功能
        _checkboxAll.on("ifClicked", function (e) {
            // 当前状态已选中，点击后应取消选中
            if (e.target.checked) {
                _checkbox.iCheck("uncheck");
            }
            // 当前状态未选中，点击后应全选
            else {
                _checkbox.iCheck("check");
            }
        });

        // 列表中checkbox联动全选
        _checkbox.on("ifClicked",function(e){
            // 当前状态已选中，点击后为 未选中
            if (e.target.checked) {
                // 全选box 应该为未选中
                if(_checkboxAll.is(":checked")){
                    _checkboxAll.iCheck("uncheck");
                }
            }
            // 当前状态未选中，点击后为 选中
            else {
                // 当仅剩下当前按钮为未选中时，全选box应为选中
                let uncheck = 0;
                // 统计未选中的box
                _checkbox.each(function () {
                    if (!$(this).is(":checked")) {
                        ++uncheck;
                    }
                });
                if(uncheck===1){
                    _checkboxAll.iCheck("check");
                }
            }
        });
    };

    /**
     * 删除单条记录
     */
    let handlerDeleteSingle = function (url, id, msg) {

        // 将ID放入数组，调用批量删除方法
        _idArray = new Array();
        _idArray.push(id);

        $("#modal-message").html( !!msg ? msg : "您确定删除数据项吗？");
        $("#modal-confirm").modal("show");

        // 确定按钮绑定删除事件
        $("#btn-modal-ok").bind("click", function () {
            handlerDeleteData(url);
        });
    };

    /**
     * 删除多条记录
     */
    let handlerDeleteMulti = function (url) {
        _idArray = new Array();

        // 将要删除的元素的ID放入数组
        _checkbox.each( function(){
            let _id = $(this).val();
            if(!!_id && $(this).is(":checked")){ // !!_id 等于 (_id!=null && _id!="" && _id!=undefined)
                _idArray.push(_id);
            }
        });

        // 判断用户是否选择了数据项
        if (_idArray.length === 0) {
            $("#modal-message").html("您还没有选择任何数据项，请至少选择一项");
        }
        else {
            $("#modal-message").html("您确定删除数据项吗？");
        }

        $("#modal-confirm").modal("show");

        // 确定按钮绑定删除事件
        $("#btn-modal-ok").bind("click", function () {
            handlerDeleteData(url);
        });
        
    };

    /**
     * 删除数据
     */
    let handlerDeleteData = function (url) {

        $("#modal-confirm").modal("hide");

        if(_idArray.length > 0) {
            // ajax异步请求删除数据
            setTimeout(function () {
                $.ajax({
                    "url": url,
                    "type": "POST",
                    "data": {"ids": _idArray.toString()},
                    "dataType": "JSON",
                    "success": function (data) {
                        $("#btn-modal-ok").unbind("click");
                        if (data.status === 200) {
                            window.location.reload();
                        } else {
                            $("#modal-message").html(data.message);
                            $("#modal-confirm").modal("show");

                            // 重新绑定确定按钮的事件
                            $("#btn-modal-ok").bind("click", function () {
                                $("#modal-confirm").modal("hide");
                            });
                        }
                    }
                });
            },500);
        }
    };

    /**
     * DT初始化
     */
    let handlerInitDataTables = function (url, columns) {
        let _dataTable = $('#dataTable').DataTable({
            //是否自适应宽度,禁止这个选项可以实现最优的性能
            "autoWidth": true,
            //是否显示表格左下角的信息
            "info": true,
            //是否允许用户改变表格每页显示的记录数
            "lengthChange": false,
            //是否允许开启排序
            "ordering": false,
            //是否开启本地分页
            "paging": true,
            //是否显示处理状态(排序的时候，数据很多耗费时间长的话，也会显示这个)
            "processing": true,
            // 是否允许 DataTables 开启本地搜索
            "searching": false,
            // 控制 DataTables 的延迟渲染，可以提高初始化的速度
            "deferRender": true,
            // 是否开启服务器模式
            "serverSide": true,
            "ajax": {
                "url": url
            },
            // 分页按钮显示选项
            "pagingType": "full_numbers",
            // 设置列的数据源
            "columns": columns,
            // 表格重绘的回调函数
            "drawCallback": function (settings) {
                handlerInitCheckbox();
                handlerCheckboxAll();
            },
            // 国际化
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        });

        return _dataTable;
    };

    /**
     * 查看详情
     */
    let handlerShowDetail = function(url){
        $.ajax({
            url: url,
            type: "get",
            dataType: "html",
            success: function (data) {
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    };

    /**
     * 搜索
     * @param param
     */
    let handlerSearch = function (dataTable,param) {
        dataTable.settings()[0].ajax.data = param;
        dataTable.ajax.reload();
    }

    return {
        /**
         * 初始化iCheck以及全选功能
         */
        init : function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
        },
        /**
         * 删除单条记录
         * @param url
         * @param id
         * @param msg
         */
        deleteSingle : function (url, id, msg) {
            handlerDeleteSingle(url, id, msg);
        },
        /**
         * 批量删除
         * @param url
         */
        deleteMulti : function (url) {
            handlerDeleteMulti(url);
        },
        /**
         * datatable 初始化
         * @param url 表格请求连接
         * @param columns 表格对应的列
         * @returns {jQuery}
         */
        initDataTables : function (url, columns) {
           return  handlerInitDataTables(url, columns);
        },
        /**
         * 详情
         * @param url
         */
        showDetail : function (url) {
            handlerShowDetail(url);
        },
        /**
         * 搜索
         * @param dataTable 表格实体
         * @param param 搜索条件
         */
        search : function (dataTable,param) {
            handlerSearch(dataTable,param);
        }

    }

}();

$(document).ready(function () {
    App.init();
});