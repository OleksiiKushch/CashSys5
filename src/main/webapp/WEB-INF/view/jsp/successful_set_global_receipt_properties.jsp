<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>CashSys.set.global.receipt.properties.success</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

<div class="container">
    <div class="jumbotron mt-3">
        <h1>Global receipt properties successfully set/update!</h1>
        <p class="lead"></p>
        <a class="btn btn-lg btn-primary"
           href="${pageContext.request.contextPath}/FrontController?command=/main"
           role="button">To main page</a>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>
