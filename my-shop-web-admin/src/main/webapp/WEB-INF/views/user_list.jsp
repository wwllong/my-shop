<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 用户管理</title>
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
                用户管理
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">用户列表</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">

                    <c:if test="${baseResult.status != null}">
                        <div class="alert alert-${baseResult.status == 200 ? "success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <i class="icon fa fa-${baseResult.status == 200 ? "check":"ban"}"></i> ${baseResult.message}
                        </div>
                    </c:if>

                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">用户列表</h3>

                            <div class="row" style="padding-left:12px; padding-top: 10px;">
                                <a href="/user/form" class="btn btn-xs btn-default" type="button"><i class="fa fa-plus"></i> 新增</a>
                                <a href="#" class="btn btn-xs btn-default" type="button"><i class="fa fa-trash-o"></i> 删除</a>
                                <a href="#" class="btn btn-xs btn-default" type="button"><i class="fa fa-download"></i> 导入</a>
                                <a href="#" class="btn btn-xs btn-default" type="button"><i class="fa fa-upload"></i> 导出</a>
                            </div>

                            <div class="row">
                                <form:form id="inputForm" action="/user/search" method="post" cssClass="navbar-form" modelAttribute="tbUser">
                                    <div class="form-group input-group-sm">
                                        <div class="form-inline">
                                            <div class="form-group">
                                                <label for="username" class="control-label">用户名</label>
                                                <form:input path="username" cssClass="form-control" placeholder="请输入用户名"/>
                                            </div>
                                            <div class="form-group input-group-sm">
                                                <label for="email" class="control-label">邮箱</label>
                                                <form:input path="email" cssClass="form-control" placeholder="请输入邮箱"/>
                                            </div>
                                            <div class="form-group input-group-sm">
                                                <label for="phone" class="control-label">手机</label>
                                                <form:input path="phone" cssClass="form-control" placeholder="请输入手机"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-sm btn-default">搜索</button>
                                    </div>
                                </form:form>
                            </div>

                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <tr>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>手机号</th>
                                    <th>邮箱</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach items="${tbUsers}" var="tbUser">
                                    <tr>
                                        <td>${tbUser.id}</td>
                                        <td>${tbUser.username}</td>
                                        <td>${tbUser.phone}</td>
                                        <td>${tbUser.email}</td>
                                        <td><fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>
                                            <a href="#" class="btn btn-xs btn-primary" type="button"><i class="fa fa-search"></i> 查看</a>
                                            <a href="#" class="btn btn-xs btn-warning" type="button"><i class="fa fa-edit"></i> 编辑</a>
                                            <a href="#" class="btn btn-xs btn-danger" type="button"><i class="fa fa-trash-o"></i> 删除</a>
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

    </div>
    <!-- /.content-wrapper -->

    <jsp:include page="../includes/copyright.jsp" />
</div>

<jsp:include page="../includes/footer.jsp" />
<script>
    $(function () {
        Validate.validateForm("inputForm");
    });
</script>
</body>
</html>
