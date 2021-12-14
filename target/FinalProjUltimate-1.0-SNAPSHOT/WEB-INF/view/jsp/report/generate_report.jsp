<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>CashSys.generate.report</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

<security:check role="senior cashier" loggedUserRole="${sessionScope.logged_user.role.name}" />

<div class="jumbotron">
    <div class="container text-center">
        <h2 class="display-4"><fmt:message key="generate_report.list.reports.text"/></h2>
        <p></p>
        <p><a class="btn btn-primary" href="${pageContext.request.contextPath}/FrontController?command=/main"
              role="button"><fmt:message key="generate_report.back.to.main.page.text"/></a></p>
    </div>
</div>
<div class="container">
    <div class="row m-auto">

        <div class="card col-md-4 m-auto">
            <h3 class="card-title mt-3"><fmt:message key="generate_report.best.cashiers.for.the.last.month.text"/></h3>
            <p class="card-body"><fmt:message key="generate_report.best.cashiers.for.the.last.month.description.text"/></p>
            <p><a class="btn btn-primary mx-3"
                  href="${pageContext.request.contextPath}/FrontController?command=/best_cashiers_by_count_receipts_for_the_last_month&page_size=5">
                <fmt:message key="generate_report.generate.text"/></a></p>
        </div>

        <div class="card col-md-4 m-auto">
            <h3 class="card-title mt-3"><fmt:message key="generate_report.best.products.for.the.last.month.text"/></h3>
            <p class="card-body"><fmt:message key="generate_report.best.products.for.the.last.month.description.text"/></p>
            <p><a class="btn btn-primary mx-3"
                  href="${pageContext.request.contextPath}/FrontController?command=/best_products_by_count_receipts_for_the_last_month&page_size=5">
                <fmt:message key="generate_report.generate.text"/></a></p>
        </div>

    </div>
    <hr>
</div>

<%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>
