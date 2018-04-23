<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS库存结算系统 | Blank Page</title>
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/navhead.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="ticket_storage"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">入库列表</h3>
                    <div class="box-tools">
                        <a href="/ticket/storage/new" class="btn btn-sm btn-success"><i class="fa fa-plus"></i>新增入库</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>入库时间</th>
                                <th>内容</th>
                                <th>起始票号</th>
                                <th>截止票号</th>
                                <th>数量</th>
                                <th>入库人</th>
                                <th>#</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${ticketInRecordList}" var="ticketInRecord">
                                <tr>
                                    <th><fmt:formatDate value="${ticketInRecord.createTime}"/></th>
                                    <th>${ticketInRecord.content}</th>
                                    <th>${ticketInRecord.beginTicketNum}</th>
                                    <th>${ticketInRecord.endTicketNum}</th>
                                    <th>${ticketInRecord.totalNum}</th>
                                    <th>${ticketInRecord.accountName}</th>
                                    <th>
                                        <a href="/ticket/storage/${ticketInRecord.id}/edit"><i class="fa fa-pencil"></i></a>
                                        <a href="javascript:;" rel="${ticketInRecord.id}" class="delBtn"><i class="fa fa-trash"></i></a>
                                    </th>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </section>

        <!-- Main content -->
        <section class="content">

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        $(".delBtn").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除么？",function (index) {
                layer.close(index);
                $.get("/ticket/storage/"+id+"/del").done(function(result){
                    if(result.status == 'success'){
                        window.history.go(0);
                    } else if(result.status == 'error'){
                        layer.msg(result.message);
                    }
                }).error(function () {
                    layer.msg("服务器忙")
                })
            })
        })
    });
</script>
</body>
</html>
