<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<%@ page import="com.finalprojultimate.util.Attribute" %>
<%@ page import="com.finalprojultimate.util.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>CashSys.best.cashiers.month</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>

<c:set var="logged_user" value="${sessionScope[Attribute.LOGGED_USER]}"/>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<roleSecurity:check role="${Role.SENIOR_CASHIER.name}" loggedUserRole="${logged_user.role.name}" />

<div class="container">

    <div class="container-fluid">
        <h1 class="mt-4 mb-3"><fmt:message key="best_cashiers_by_count_receipts_for_the_last_month.report.on.the.best.cashiers.for.the.last.month.text"/></h1>

        <table class="table table-striped border rounded">
            <thead class="thead-light">
            <tr>
                <th scope="col"><fmt:message key="best_cashiers_by_count_receipts_for_the_last_month.id.text"/></th>
                <th scope="col"><fmt:message key="best_cashiers_by_count_receipts_for_the_last_month.email.text"/></th>
                <th scope="col"><fmt:message key="best_cashiers_by_count_receipts_for_the_last_month.first.name.text"/></th>
                <th scope="col"><fmt:message key="best_cashiers_by_count_receipts_for_the_last_month.middle.name.text"/></th>
                <th scope="col"><fmt:message key="best_cashiers_by_count_receipts_for_the_last_month.last.name.text"/></th>
                <th scope="col"><fmt:message key="best_cashiers_by_count_receipts_for_the_last_month.count.receipts.text"/></th>
                <th scope="col"><fmt:message key="best_cashiers_by_count_receipts_for_the_last_month.action.text"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cashier" items="${requestScope.users}" varStatus="status">
                <tr>
                    <th class="col-md-1" scope="row">${cashier.id}</th>
                    <td class="col-md-4">${cashier.email}</td>
                    <td class="col-md-2">${cashier.firstName}</td>
                    <td class="col-md-2">${cashier.middleName}</td>
                    <td class="col-md-2">${cashier.lastName}</td>
                    <td class="col-md-2">${requestScope.amount[status.index]}</td>
                    <td class="col-md-1">
                        <a href="#">
                            <fmt:message key="best_cashiers_by_count_receipts_for_the_last_month.send.congratulation.text"/></a>
                        &nbsp &nbsp
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="p-2 mx-2">
            <a href="${Path.GENERATE_REPORT}"
               type="submit" class="btn btn-primary float-right">
                <fmt:message key="best_cashiers_by_count_receipts_for_the_last_month.come.back.to.report.generator.text"/>
            </a>
        </div>

    </div>

</div>

</body>
</html>
