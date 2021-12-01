<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>CashSys.create.new.reject.receipt.success</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

<div class="container">
    <div class="jumbotron mt-3">
        <h1>Reject receipt successfully created!</h1>
        <p class="lead"></p>
        <a class="btn btn-lg btn-primary mx-3"
           href="${pageContext.request.contextPath}/FrontController?command=/receipt_catalog&page=1&page_size=8"
           role="button">To receipt catalog</a>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>
