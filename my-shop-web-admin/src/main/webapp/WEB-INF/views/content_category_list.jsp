<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/treeTable/themes/default/treeTable.min.css">
    <link rel="stylesheet" href="/static/assets/plugins/treeTable/themes/vsStyle/treeTable.min.css">
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
                内容分类管理
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">内容分类列表</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <!-- /.row -->
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
                            <h3 class="box-title">内容分类</h3>

                            <div class="row" style="padding-left:12px; padding: 10px;">
                                <a href="/content/category/form" class="btn btn-sm btn-success" type="button"><i class="fa fa-plus"></i> 新增</a>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table id="treeTable" class="table table-hover">
                                <tr>
                                    <th>ID</th>
                                    <th>名称</th>
                                    <th>排序</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach var="tbContentCategory" items="${tbContentCategories}">
                                    <tr id="${tbContentCategory.id}" pId="${tbContentCategory.parent.id}">
                                        <td>${tbContentCategory.id}</td>
                                        <td>${tbContentCategory.name}</td>
                                        <td>${tbContentCategory.sortOrder}</td>
                                        <td>
                                            <a href="/content/category/form?id=${tbContentCategory.id}" type="button" class="btn btn-sm btn-warning"><i class="fa fa-edit"></i> 编辑</a>
                                            <button  type="button" class="btn btn-sm btn-danger" onclick="App.deleteSingle('/content/category/delete',${tbContentCategory.id},'内容分类的子类目以及内容都将删除，您确定删除数据项吗？')"><i class="fa fa-trash-o"></i> 删除</button >
                                            <a href="/content/category/form?parent.id=${tbContentCategory.id}&parent.name=${tbContentCategory.name}" class="btn btn-sm btn-success" type="button"><i class="fa fa-plus"></i> 新增下级菜单</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>

    </div>
    <!-- /.content-wrapper -->

    <jsp:include page="../includes/copyright.jsp" />
</div>

<tags:modal_confirm />

<jsp:include page="../includes/footer.jsp" />
<script src="/static/assets/plugins/treeTable/jquery.treeTable.min.js"></script>
<script>
    $(function () {
        App.initTreeTable("treeTable");
    });
</script>
</body>
</html>
