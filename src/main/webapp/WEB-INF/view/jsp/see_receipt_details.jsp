<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<%@ page import="com.finalprojultimate.util.Attribute" %>
<%@ page import="com.finalprojultimate.util.Parameter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="totalSum" uri="http://com.finalprojultimate/model/tag/TagTotalSum" %>
<%@ taglib prefix="amount" uri="http://com.finalprojultimate/model/tag/TagAmount" %>
<%@ taglib prefix="formattedUserName" uri="http://com.finalprojultimate/model/tag/TagFormattedUserName" %>
<%@ taglib prefix="formattedId" uri="http://com.finalprojultimate/model/tag/TagFormattedId" %>
<%@ taglib prefix="totalReceiptSum" uri="http://com.finalprojultimate/model/tag/TagTotalReceiptSum" %>

<html>
<head>
    <title>CashSys.see.receipt.details</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14"></script>

</head>

<c:set var="logged_user" value="${sessionScope[Attribute.LOGGED_USER]}"/>
<c:set var="receipt" value="${requestScope[Attribute.RECEIPT]}"/>
<c:set var="receipt_details" value="${requestScope[Attribute.RECEIPT_DETAILS]}"/>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<security:check role="${Role.SENIOR_CASHIER.name}" loggedUserRole="${logged_user.role.name}" />

<div class="container-fluid">
    <h1 class="mt-4"><fmt:message key="see_receipt_details.detail.id.text"/>
        ${receipt.id}<fmt:message key="see_receipt_details.receipt.information.text"/></h1>
    <form action="${Path.CREATE_REJECT_RECEIPT}" method="post">
        <h3 class="mt-4"><fmt:message key="see_receipt_details.list.of.products.text"/></h3>
        <table class="table border rounded" id="productsTable">
            <thead class="thead-light">
            <tr>
                <th scope="col"><fmt:message key="see_receipt_details.id.text"/></th>
                <th scope="col"><fmt:message key="see_receipt_details.name.text"/></th>
                <th scope="col"><fmt:message key="see_receipt_details.sum.text"/></th>
                <th scope="col"><fmt:message key="see_receipt_details.price.text"/></th>
                <th scope="col"><fmt:message key="see_receipt_details.amount.text"/></th>
                <th scope="col"><fmt:message key="see_receipt_details.barcode.text"/></th>
                <th scope="col"><fmt:message key="see_receipt_details.action.text"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${requestScope.products}">
                <tr>
                    <th class="col-md-1" scope="row">${product.id}</th>
                    <td>${product.name}</td>
                    <td class="col-md-1"><totalSum:get price="${product.price}" amount="${product.amount}" /></td>
                    <td class="col-md-1">${product.price}</td>
                    <td class="col-md-1"><amount:get amount="${product.amount}" unit="${product.unit}" />
                        <fmt:message key="${product.unit.message}"/></td>
                    <td class="col-md-1">${product.barcode}</td>
                    <td class="col-md-3">
                        <div class="d-flex justify-content-between">
                            <input name="${Parameter.RECEIPT_ID}" value="${receipt.id}" type="hidden">
                            <div class="custom-control custom-switch">
                                <input type="checkbox" class="custom-control-input rejectSwitcher" id="rejectSwitch${product.id}"
                                       name="${Parameter.REJECT_RECEIPT_ID}" value="${product.id}">
                                <label class="custom-control-label" for="rejectSwitch${product.id}">
                                    <fmt:message key="see_receipt_details.reject.text"/></label>
                            </div>
                            <div class="input-group mx-3 was-validated">
                                <label for="rejectInputAmount${product.id}"></label>
                                <div class="input-group input-group-sm">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text small">
                                            <fmt:message key="see_receipt_details.how.many.text"/></span>
                                    </div>
                                    <input type="number" min="0.001" max="${product.amount}" step=".001"
                                           class="form-control form-control-sm is-valid col-md-8 rejectInputAmount"
                                           id="rejectInputAmount${product.id}" name="${Parameter.AMOUNT}" value="${product.amount}" required disabled>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="row float-right">
            <div class="p-2 mx-2">
                <button type="submit" class="mb-5 btn btn-primary" id="saveReject" disabled>
                    <fmt:message key="see_receipt_details.save.text"/>
                </button>
            </div>
            <div class="p-2 mx-2">
                <a class="mb-5 btn btn-secondary rejectAllLink text-light">
                    <fmt:message key="see_receipt_details.reject.all.text"/>
                </a>
            </div>
        </div>

    </form>


    <div class="p-2 mx-2">
        <h4 class="" id="sum"><fmt:message key="see_receipt_details.sum.with.colon.text"/> <totalReceiptSum:get receiptId="${receipt.id}" /></h4>
    </div>

    <div class="p-2 mx-2">
        <h6 class="" id="change"><fmt:message key="see_receipt_details.change.text"/> ${receipt.change} </h6>
    </div>

    <div class="row mt-4">
        <div class="col-sm-4">
            <div class="card mx-3" >
                <div class="card-body">
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.cashier.text"/> &nbsp</p>
                        <h6>(<fmt:message key="see_receipt_details.id.text"/>: ${receipt.userId})
                            <formattedUserName:get userId="${receipt.userId}" /></h6>
                    </div>
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.type.payment.text"/> &nbsp</p>
                        <h6><fmt:message key="${receipt.payment.message}"/></h6>
                    </div>
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.date.time.text"/> &nbsp</p>
                        <h6>${receipt.dateTime}</h6>
                    </div>
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.status.text"/> &nbsp</p>
                        <h6><fmt:message key="${receipt.status.message}"/></h6>
                    </div>
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.root.receipt.id.text"/> &nbsp</p>
                        <h6><formattedId:get idValue="${receipt_details.rootReceiptId}" /></h6>
                    </div>
                    <div class="row my-0 mx-3">
                        <form action="${Path.APP_CONTEXT}${Path.CONTROLLER}"
                        method="get">
                            <input name="${Path.COMMAND}" value="${Command.SEE_RECEIPT_DETAILS}" type="hidden">
                            <input name="${Parameter.RECEIPT_ID}" value="${receipt_details.rootReceiptId}" type="hidden">
                            <button class="btn btn-sm btn-primary" type="submit"
                                    <c:if test="${receipt_details.rootReceiptId == 0}">disabled</c:if>>
                                <fmt:message key="see_receipt_details.go.to.root.receipt.text"/>
                            </button>
                        </form>
<%--                        <form action="${pageContext.request.contextPath}/FrontController"--%>
<%--                              method="get">--%>
<%--                            <input name="command" value="/see_receipt_details" type="hidden">--%>
<%--                            <label for="selectRejectReceiptId" class="form-label">Select reject receipt id:</label>--%>
<%--                            <select class="form-select" name="receiptId" id="selectRejectReceiptId">--%>
<%--                                <c:forEach var="i" items="${rejectedReceiptsIds}">--%>
<%--                                    <option value="${i}">${i}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                            <button class="btn btn-sm btn-primary" type="submit"--%>
<%--                                    <c:if test="">disabled</c:if>>--%>
<%--                                Go--%>
<%--                            </button>--%>
<%--                        </form>--%>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-8">
            <div class="card mx-3" >
                <div class="card-body">
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.organization.tax.id.number.text"/> &nbsp</p>
                        <h6><formattedId:get idValue="${receipt_details.organizationTaxIdNumber}" /></h6>
                    </div>
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.name.organization.text"/> &nbsp</p>
                        <h6>${receipt_details.nameOrganization}
                            <c:if test="${receipt_details.nameOrganization == null}">-</c:if></h6>
                    </div>
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.address.trade.point.text"/> &nbsp</p>
                        <h6>${receipt_details.addressTradePoint}
                            <c:if test="${receipt_details.addressTradePoint == null}">-</c:if></h6>
                    </div>
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.vat.text"/> &nbsp</p>
                        <h6>${receipt_details.vat}
                            <c:if test="${receipt_details.vat == null}">-</c:if></h6>
                    </div>
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.taxation.system.text"/> &nbsp</p>
                        <h6>${receipt_details.taxationSys}
                            <c:if test="${receipt_details.taxationSys == null}">-</c:if></h6>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="p-2 mx-2">
        <a href="${Path.RECEIPT_CATALOG}" type="submit" class="mt-5 mb-5 btn btn-primary float-left">
            <fmt:message key="see_receipt_details.come.back.to.receipt.catalog.text"/>
        </a>
    </div>

</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/custom/reject.all.products.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/custom/reject.product.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/custom/check.all.reject.switcher.js"></script>

</body>
</html>
