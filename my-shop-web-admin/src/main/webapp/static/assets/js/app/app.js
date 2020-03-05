/**
 * icheck 、datatable - app
 */
var App = function () {

    // iCheck
    let _checkboxAll;
    let _checkbox;

    // 删除的id 数组
    let _idArray;

    // treeTable 初始化默认配置
    let treeTableOpts = {
        theme : "vsStyle",
        expandLevel : 2,
        column : 0
    };

    // treeTable 初始化默认配置
    let zTreeOpts = {
        zTreeId: "myTree",
        view: {
            selectedMulti: false
        },
        async: {
            enable: true,
            url:"",
            autoParam:["id", "name=n", "level=lv"],
            otherParam:{}
        }
    };

    // dropzone 初始化默认配置
    let dropzoneOpts = {
        url: "", // 文件提交地址
        method: "post",  // 也可用put
        paramName: "dropzoneFile", // 默认为file
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 10, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传1个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持!",
        dictRemoveFile:"删除",
        dictCancelUpload: "取消"
    };

    // wangEditor 默认配置
    let wangEditorOpts = {
        id: "#editor",
        uploadImgServer: "/upload",
        uploadFileName: "editorFile",
        uploadImgMaxSize: 10 * 1024 * 1024,
        uploadImgMaxLength: 1
    };

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
    let handlerBatchDelete = function (url) {
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
    };

    /**
     * treeTable初始化
     */
    let handlerInitTreeTable = function (tableId, opts) {
        opts = $.extend(treeTableOpts,opts);
        $("#"+tableId).treeTable(opts);
    };

    /**
     * zTree初始化
     */
    let handlerInitZTree = function (opts, callback) {
        opts = $.extend(true,zTreeOpts,opts);
        opts.async = $.extend(zTreeOpts.async,opts.async);
        let _treeId = "#"+opts.zTreeId;
        $.fn.zTree.init($(_treeId),opts);
        $("#btn-modal-ok").bind("click",function () {
            let zTree = $.fn.zTree.getZTreeObj(opts.zTreeId);
            let nodes = zTree.getSelectedNodes();
            if (nodes.length == 0) {
                alert("请先选择一个节点");
            }else{
                callback(nodes);
            }
        });
    };

    /**
     * dropzone初始化
     */
    let handlerInitDropzone = function (opts) {
        Dropzone.autoDiscover = false;
        opts = $.extend(true,dropzoneOpts,opts);
        for(let i = 0,len = opts.ids.length; i < len; i++){
            let dropzone = new Dropzone(opts.ids[i], opts);
        }
    };

    /**
     * wangEditor初始化
     */
    let handlerInitWangEditor = function (opts) {
        opts = $.extend(true,wangEditorOpts,opts);
        let E = window.wangEditor;
        let editor = new E(opts.id);
        // 或者 var editor = new E( document.getElementById('editor') )
        // 配置服务器端地址
        editor.customConfig.uploadImgServer = opts.uploadImgServer;
        editor.customConfig.uploadFileName = opts.uploadFileName;
        // 将图片大小限制为 10M
        editor.customConfig.uploadImgMaxSize = opts.uploadImgMaxSize;
        // 限制一次最多能传1张图片
        editor.customConfig.uploadImgMaxLength = opts.uploadImgMaxLength;
        editor.create();
        return editor;
    };

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
        batchDelete : function (url) {
            handlerBatchDelete(url);
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
        },
        /**
         * treeTable初始化
         * @param tableId
         * @param opts
         */
        initTreeTable : function (tableId, opts) {
            handlerInitTreeTable(tableId,opts);
        },
        /**
         * 配置方式初始化ZTree
         * @param opts
         * @param callback
         */
        initZTree : function (opts, callback) {
            handlerInitZTree(opts, callback);
        },
        /**
         * 快速初始化ZTree
         * @param url
         * @param callback
         */
        quickInitZTree : function (url, callback) {
            handlerInitZTree({
                async: {
                    url:url
                }
            }, callback);
        },
        /**
         * 初始化dropzone
         * @param opts
         */
        initDropzone : function (opts) {
            handlerInitDropzone(opts);
        },
        /**
         * 初始化wangEditor
         * @param opts
         */
        initWangEditor : function (opts) {
            return handlerInitWangEditor(opts);
        }

    }

}();

$(document).ready(function () {
    App.init();
});