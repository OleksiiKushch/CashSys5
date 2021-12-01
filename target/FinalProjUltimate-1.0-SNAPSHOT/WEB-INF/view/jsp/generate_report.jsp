<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>CashSys.generate.report</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

<div class="jumbotron">
    <div class="container text-center">
        <h2 class="display-4">List reports</h2>
        <p></p>
        <p><a class="btn btn-primary" href="${pageContext.request.contextPath}/FrontController?command=/main"
              role="button">Back to main page</a></p>
    </div>
</div>
<div class="container">
    <div class="row mt-3">
        <div class="card col-md-4">
            <h3 class="card-title mt-3">Best Cashier</h3>
            <p class="card-body">Generates a list of the most productive (by the number of closed receipts)
                cashiers for the <b>last month</b>.</p>
            <p><a class="btn btn-primary mx-3"
                  href="${pageContext.request.contextPath}/FrontController?command=/best_cashier_for_the_last_month&page_size=5">
                Generate</a></p>
        </div>
    </div>
    <hr>
</div>

<%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>
