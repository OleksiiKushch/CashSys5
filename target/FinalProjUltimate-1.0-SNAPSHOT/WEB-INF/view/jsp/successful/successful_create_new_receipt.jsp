<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>CashSys.create.new.receipt.success</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

<security:check role="cashier" loggedUserRole="${sessionScope.logged_user.role.name}" />

<div class="container">
    <div class="jumbotron mt-3">
        <h1><fmt:message key="successful_create_new_receipt.receipt.successfully.created.text"/></h1>
        <p class="lead"><fmt:message key="successful_create_new_receipt.receipt.successfully.help.text"/></p>
        <a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/FrontController?command=/new_receipt"
           role="button"><fmt:message key="successful_create_new_receipt.create.new.receipt.text"/></a>
        <a class="btn btn-lg btn-primary mx-3"
           href="${pageContext.request.contextPath}/FrontController?command=/show_created_receipt"
           role="button"><fmt:message key="successful_create_new_receipt.show.created.receipt.text"/></a>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>
