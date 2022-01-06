<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.math.RoundingMode" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<%@ page import="com.finalprojultimate.util.Attribute" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="totalSum" uri="http://com.finalprojultimate/model/view/tag/processing/TagTotalSum" %>
<%@ taglib prefix="formatAmount" uri="http://com.finalprojultimate/model/view/tag/formatting/TagFormattedAmount" %>
<%@ taglib prefix="formatUserName" uri="http://com.finalprojultimate/model/view/tag/formatting/TagFormattedUserName" %>
<%@ taglib prefix="formatProductNameLen" uri="http://com.finalprojultimate/model/view/tag/formatting/TagFormattedProductNameLength" %>

<html>
<head>
    <title>CashSys.show.created.receipt</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>

<c:set var="logged_user" value="${sessionScope[Attribute.LOGGED_USER]}"/>
<c:set var="receipt" value="${requestScope[Attribute.RECEIPT]}"/>
<c:set var="receipt_details" value="${requestScope[Attribute.RECEIPT_DETAILS]}"/>
<c:set var="receipt_service" value="${requestScope[Attribute.RECEIPT_SERVICE]}"/>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<roleSecurity:check role="${Role.CASHIER.name}" loggedUserRole="${logged_user.role.name}" />

<div class="container">
    <div class="card mt-5" style="width: 25rem; margin: auto;">
        <div class="card-body">
            <h5 class="card-title text-center">${receipt_details.nameOrganization}</h5>

            <p class="card-text">${receipt_details.addressTradePoint}</p>
            <p class="card-text"><fmt:message key="show_created_receipt.cashier.text"/> <formatUserName:get userId="${receipt.userId}" /></p>
            <br>
            <c:forEach var="product" items="${requestScope.products}">
                <p><formatProductNameLen:get productName="${product.name}" length="30" /> (x<formatAmount:get amount="${product.amount}" unit="${product.unit}" />) = <totalSum:get price="${product.price}" amount="${product.amount}" /></p>
            </c:forEach>
            <p class="card-text"><fmt:message key="show_created_receipt.vat.text"/> ${receipt_details.vat}%</p><br>
            <p class="card-text"><fmt:message key="${receipt.payment.message}"/> = ${(receipt_service.getSumReceiptById(receipt.id)
            + receipt.change).setScale(2, RoundingMode.DOWN)}</p>
            <p class="card-text"><fmt:message key="show_created_receipt.change.text"/> = ${receipt.change}</p>
            <br>
            <p class="card-text"><fmt:message key="show_created_receipt.tin.text"/> ${receipt_details.organizationTaxIdNumber}</p>
            <p class="card-text"><fmt:message key="show_created_receipt.ts.text"/> ${receipt_details.taxationSys}</p>
            <p class="card-text text-muted">${receipt.dateTime}</p>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

</body>
</html>
