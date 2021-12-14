<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>CashSys.update.product.success</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

<security:check role="commodity expert" loggedUserRole="${sessionScope.logged_user.role.name}" />

<div class="container">
    <div class="jumbotron mt-3">
        <h1><fmt:message key="successful_update_product.product.successfully.updated.text"/></h1>
        <p class="lead"></p>
        <a class="btn btn-lg btn-primary"
           href="${pageContext.request.contextPath}/FrontController?command=/product_catalog&page=1&page_size=8"
           role="button"><fmt:message key="successful_update_product.to.product.catalog.text"/></a>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>
