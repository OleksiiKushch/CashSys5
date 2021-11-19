<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>CashSys.create.new.receipt.success</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column min-vh-100">

    <%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

    <div class="container">
        <div class="jumbotron mt-3">
            <h1>Receipt successfully created!</h1>
            <p class="lead">If you want to create a new receipt click the button "Create new receipt".</p>
            <a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/FrontController?command=/new_receipt"
               role="button">Create new product</a>
            <a class="btn btn-lg btn-primary mx-3"
               href="${pageContext.request.contextPath}/FrontController?command=/show_created_receipt"
               role="button">Show created receipt</a>
        </div>
    </div>

    <%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>
