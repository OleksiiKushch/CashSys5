<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CashSys.best.products.month</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

<security:check role="senior cashier" loggedUserRole="${sessionScope.logged_user.role.name}" />

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
            <c:forEach var="products" items="${requestScope.products}" varStatus="status">
                <tr>
                    <th class="col-md-1" scope="row">${products.id}</th>
                    <td class="col-md-4">${products.name}</td>
                    <td class="col-md-2">${products.barcode}</td>
                    <td class="col-md-2">${requestScope.report_best_products_by_count_receipt[status.index].totalAmount}</td>
                    <td class="col-md-2">${requestScope.report_best_products_by_count_receipt[status.index].totalSum}</td>
                    <td class="col-md-1">${requestScope.report_best_products_by_count_receipt[status.index].countReceipts}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="p-2 mx-2">
            <a href="${pageContext.request.contextPath}/FrontController?command=/generate_report"
               type="submit" class="btn btn-primary float-right">
                <fmt:message key="best_products_by_count_receipts_for_the_last_month.come.back.to.report.generator.text"/>
            </a>
        </div>

    </div>

</div>

</body>
</html>
