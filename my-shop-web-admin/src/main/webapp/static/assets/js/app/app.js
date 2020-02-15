/**
 * icheck app
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
    let handlerInit = function () {
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
    }

    return {
        /**
         * 初始化iCheck以及全选功能
         */
        init : function () {
            handlerInit();
            handlerCheckboxAll();
        },
        deleteSingle : function (url, id, msg) {
            handlerDeleteSingle(url, id, msg);
        },
        deleteMulti : function (url) {
            handlerDeleteMulti(url);
        }

    }

}();

$(document).ready(function () {
    App.init();
});