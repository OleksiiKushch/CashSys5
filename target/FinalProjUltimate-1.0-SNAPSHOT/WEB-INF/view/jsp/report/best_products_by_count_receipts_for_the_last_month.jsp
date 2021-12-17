<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<%@ page import="com.finalprojultimate.util.Attribute" %>
<%@ page import="com.finalprojultimate.util.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="amount" uri="http://com.finalprojultimate/model/tag/TagAmount" %>
<%@ taglib prefix="price" uri="http://com.finalprojultimate/model/tag/TagPrice" %>


<html>
<head>
    <title>CashSys.best.products.month</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>

<c:set var="logged_user" value="${sessionScope[Attribute.LOGGED_USER]}"/>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<security:check role="${Role.SENIOR_CASHIER.name}" loggedUserRole="${logged_user.role.name}" />

<div class="container">

    <div class="container-fluid">
        <h1 class="mt-4 mb-3"><fmt:message key="best_products_by_count_receipts_for_the_last_month.report.on.the.best.products.for.the.last.month.text"/></h1>

        <table class="table table-striped border rounded">
            <thead class="thead-light">
            <tr>
                <th scope="col"><fmt:message key="best_products_by_count_receipts_for_the_last_month.id.text"/></th>
                <th scope="col"><fmt:message key="best_products_by_count_receipts_for_the_last_month.name.product.text"/></th>
                <th scope="col"><fmt:message key="best_products_by_count_receipts_for_the_last_month.barcode.text"/></th>
                <th scope="col"><fmt:message key="best_products_by_count_receipts_for_the_last_month.total.amount.text"/></th>
                <th scope="col"><fmt:message key="best_products_by_count_receipts_for_the_last_month.total.sum.text"/></th>
                <th scope="col"><fmt:message key="best_products_by_count_receipts_for_the_last_month.count.receipts.text"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${requestScope.products}" varStatus="status">
                <tr>
                    <th class="col-md-1" scope="row">${product.id}</th>
                    <td class="col-md-4">${product.name}</td>
                    <td class="col-md-2">${product.barcode}</td>
                    <td class="col-md-2">
                        <amount:get amount="${requestScope.report_best_products_by_count_receipt[status.index].totalAmount}"
                                    unit="${product.unit}" />&nbsp<fmt:message key="${product.unit.message}"/></td>
                    <td class="col-md-2">
                        <price:get price="${requestScope.report_best_products_by_count_receipt[status.index].totalSum}" /></td>
                    <td class="col-md-1">${requestScope.report_best_products_by_count_receipt[status.index].countReceipts}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="p-2 mx-2">
            <a href="${Path.GENERATE_REPORT}"
               type="submit" class="btn btn-primary float-right">
                <fmt:message key="best_products_by_count_receipts_for_the_last_month.come.back.to.report.generator.text"/>
            </a>
        </div>

    </div>

</div>

</body>
</html>
