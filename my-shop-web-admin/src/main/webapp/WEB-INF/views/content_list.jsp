<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                内容管理
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">内容列表</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">

                    <!-- 消息提示 -->
                    <c:if test="${baseResult.status != null}">
                        <div class="alert alert-${baseResult.status == 200 ? "success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <i class="icon fa fa-${baseResult.status == 200 ? "check":"ban"}"></i> ${baseResult.message}
                        </div>
                    </c:if>

                    <div class="box box-info">
                        <div class="box-header">
                            <h3 class="box-title">内容列表</h3>

                            <div class="row" style="padding-left:12px; padding: 10px;">
                                <a href="/content/form" class="btn btn-sm btn-success" type="button"><i class="fa fa-plus"></i> 新增</a>
                                <button type="button" class="btn btn-sm btn-danger" onclick="App.deleteMulti('/content/delete')"><i class="fa fa-trash-o"></i> 删除</button>
                            </div>

                            <!-- 搜索条件 -->
                            <div class="row">
                                <from class="navbar-form">
                                    <div class="form-group input-group-sm">
                                        <div class="form-inline">
                                            <div class="form-group">
                                                <label for="title" class="control-label">标题：</label>
                                                <input id="title" class="form-control" placeholder="请输入标题"/>
                                            </div>
                                            <div class="form-group input-group-sm">
                                                <label for="subTitle" class="control-label">子标题：</label>
                                                <input id="subTitle" class="form-control" placeholder="请输入子标题"/>
                                            </div>
                                            <div class="form-group input-group-sm">
                                                <label for="titleDesc" class="control-label">标题描述：</label>
                                                <input id="titleDesc" class="form-control" placeholder="请输入标题描述"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <button type="button" class="btn btn-sm btn-default" id="btn-search"><i class="fa fa-search"></i> 搜索</button>
                                    </div>
                                </from>
                            </div>

                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table id="dataTable" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox" class="minimal checkbox-all" /></th>
                                        <th>ID</th>
                                        <th>所属类目</th>
                                        <th>标题</th>
                                        <th>子标题</th>
                                        <th>标题描述</th>
                                        <th>链接</th>
                                        <th>图片1</th>
                                        <th>图片2</th>
                                        <th>更新时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>

    </div>
    <!-- /.content-wrapper -->

    <jsp:include page="../includes/copyright.jsp" />
</div>

<jsp:include page="../includes/footer.jsp" />
<!-- 自定义模态框 -->
<tags:modal_confirm />
<tags:modal_detail />

<script>

    $(function () {
        // 表单验证-初始化
        Validate.validateForm("inputForm");

        // 表单对应的列
        let columns = [
            {
                "data": function (row, type, val, meta) {
                    return '<input id="' + row.id + '" type="checkbox" class="minimal" />';
                }
            },
            {"data": "id"},
            {"data": "tbContentCategory.name"},
            {"data": "title"},
            {"data": "subTitle"},
            {"data": "titleDesc"},
            {
                "data": function (row, type, val, meta) {
                    return initViewLabel(row.url);
                }
            },
            {
                "data": function (row, type, val, meta) {
                    return initViewLabel(row.pic);
                }
            },
            {
                "data": function (row, type, val, meta) {
                    return initViewLabel(row.pic2);
                }
            },
            {
                "data": function (row, type, val, meta) {
                    return moment(row.updated).format("YYYY-MM-DD HH:mm:ss");
                }
            },
            {
                "data": function (row, type, val, meta) {
                    var detailURL = "/content/detail?id="+row.id;
                    var deleteURL = "/content/delete";
                    return '<button type="button" class="btn btn-sm btn-primary" onclick="App.showDetail(\''+detailURL+'\')"><i class="fa fa-sticky-note-o"></i> 查看</button>&nbsp;&nbsp;' +
                        '<a href="/content/form?id='+row.id+'" type="button" class="btn btn-sm btn-warning"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;' +
                        '<button  type="button" class="btn btn-sm btn-danger" onclick="App.deleteSingle(\'' + deleteURL + '\', \'' + row.id + '\')"><i class="fa fa-trash-o"></i> 删除</button >';

                }
            }
        ];

        // 表格初始化
        let _dataTable = App.initDataTables( "/content/page",columns);

        // 搜索
        $("#btn-search").bind("click",function () {
            var param = {
                "title": $("#title").val(),
                "subTitle": $("#subTitle").val(),
                "titleDesc": $("#titleDesc").val(),
            };
            App.search(_dataTable,param);
        });

    });

    // 超链接-查看 初始化
    function initViewLabel(href){
        if (!!href) {
            return '<a href="' + href + '" target="_blank">查看</a>';
        }
       return '';
    }
</script>
</body>
</html>
