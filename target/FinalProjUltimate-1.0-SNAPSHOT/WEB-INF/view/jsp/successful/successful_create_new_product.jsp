<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>CashSys.create.new.product.success</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

</head>
<body class="d-flex flex-column min-vh-100">

    <%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

    <div class="container">
        <div class="jumbotron mt-3">
            <h1><fmt:message key="successful_create_new_product.product.successfully.created.text"/></h1>
            <p class="lead"><fmt:message key="successful_create_new_product.product.successfully.help.text"/></p>
            <a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/FrontController?command=/create_new_product"
               role="button"><fmt:message key="successful_create_new_product.create.new.product.text"/></a>
            <a class="btn btn-lg btn-primary mx-3"
               href="${pageContext.request.contextPath}/FrontController?command=/product_catalog&page=1&page_size=8"
               role="button"><fmt:message key="successful_create_new_product.to.product.catalog.text"/></a>
        </div>
    </div>

    <%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>
