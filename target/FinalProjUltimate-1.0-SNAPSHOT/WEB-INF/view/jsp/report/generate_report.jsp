<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<%@ page import="com.finalprojultimate.util.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>CashSys.generate.report</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>

<c:set var="logged_user" value="${sessionScope[Attribute.LOGGED_USER]}"/>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<security:check role="${Role.SENIOR_CASHIER.name}" loggedUserRole="${logged_user.role.name}" />

<div class="jumbotron">
    <div class="container text-center">
        <h2 class="display-4"><fmt:message key="generate_report.list.reports.text"/></h2>
        <p></p>
        <p><a class="btn btn-primary" href="${Path.MAIN}"
              role="button"><fmt:message key="generate_report.back.to.main.page.text"/></a></p>
    </div>
</div>
<div class="container">
    <div class="row m-auto">

        <div class="card col-md-4 m-auto">
            <h3 class="card-title mt-3"><fmt:message key="generate_report.best.cashiers.for.the.last.month.text"/></h3>
            <p class="card-body"><fmt:message key="generate_report.best.cashiers.for.the.last.month.description.text"/></p>
            <p><a class="btn btn-primary mx-3"
                  href="${Path.BEST_CASHIERS_BY_COUNT_RECEIPTS_FOR_THE_MONTH}">
                <fmt:message key="generate_report.generate.text"/></a></p>
        </div>

        <div class="card col-md-4 m-auto">
            <h3 class="card-title mt-3"><fmt:message key="generate_report.best.products.for.the.last.month.text"/></h3>
            <p class="card-body"><fmt:message key="generate_report.best.products.for.the.last.month.description.text"/></p>
            <p><a class="btn btn-primary mx-3"
                  href="${Path.BEST_PRODUCTS_BY_COUNT_RECEIPTS_FOR_THE_MONTH}">
                <fmt:message key="generate_report.generate.text"/></a></p>
        </div>

    </div>
    <hr>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

</body>
</html>
