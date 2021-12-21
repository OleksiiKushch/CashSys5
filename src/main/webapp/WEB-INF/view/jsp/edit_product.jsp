<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.model.entity.product.Unit" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<%@ page import="com.finalprojultimate.util.Attribute" %>
<%@ page import="com.finalprojultimate.util.Parameter" %>
<%@ page import="com.finalprojultimate.util.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>CashSys.edit.product</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</head>

<c:set var="logged_user" value="${sessionScope[Attribute.LOGGED_USER]}"/>
<%-- Product data from database --%>
<c:set var="product" value="${requestScope[Attribute.PRODUCT]}"/>
<%-- Product data changed by the client (from the form) --%>
<c:set var="saved_id" value="${requestScope[Attribute.PRODUCT_DATA].id}"/>
<c:set var="saved_product_name" value="${requestScope[Attribute.PRODUCT_DATA].name}"/>
<c:set var="saved_price" value="${requestScope[Attribute.PRODUCT_DATA].price}"/>
<c:set var="saved_amount" value="${requestScope[Attribute.PRODUCT_DATA].amount}"/>
<c:set var="saved_unit" value="${requestScope[Attribute.PRODUCT_DATA].unit}"/>
<c:set var="saved_barcode" value="${requestScope[Attribute.PRODUCT_DATA].barcode}"/>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<roleSecurity:check role="${Role.COMMODITY_EXPERT.name}" loggedUserRole="${logged_user.role.name}" />

<div class="container">

    <h1 class="mt-4"><fmt:message key="edit_product.product.edit.form.text"/></h1>

    <div class="card my-3">
        <div class="card-body">

            <form class="was-validated" action="${Path.APP_CONTEXT}${Path.CONTROLLER}" method="post">
                <input name="${Path.COMMAND}" value="${Command.EDIT_PRODUCT}" type="hidden">
                <input name="${Parameter.PRODUCT_ID}" value="${product.id}" type="hidden">

                    <div class="mb-3">
                        <label for="inputProductName" class="form-label"><fmt:message key="edit_product.name.text"/></label>
                        <input type="text" class="form-control is-invalid" id="inputProductName" name="${Parameter.PRODUCT_NAME}"
                               maxlength="512" placeholder="<fmt:message key="edit_product.enter.name.text"/>"
                               value="${saved_product_name == null ? product.name : saved_product_name}" required>
                    </div>

                    <div class="mb-3">
                        <label for="inputPrice" class="form-label"><fmt:message key="edit_product.price.text"/></label>
                        <input type="number" min="0" step=".01" class="form-control is-invalid" id="inputPrice" name="${Parameter.PRICE}"
                               placeholder="<fmt:message key="edit_product.enter.price.text"/>"
                               value="${saved_price == null ? product.price : saved_price}" required>
                    </div>

                    <div class="mb-3">
                        <label for="inputAmount" class="form-label"><fmt:message key="edit_product.amount.text"/></label>
                        <input type="number" min="0" step=".001" class="form-control is-invalid" id="inputAmount" name="${Parameter.AMOUNT}"
                               placeholder="<fmt:message key="edit_product.enter.amount.text"/>"
                               value="${saved_amount == null ? product.amount : saved_amount}" required>
                    </div>

                    <fieldset class="form-group">
                        <div class="row">
                            <label class="col-form-label col-sm-2 pt-0"><fmt:message key="edit_product.unit.text"/></label>
                            <div class="col-sm-10">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="${Parameter.UNIT}" id="radioPieces"
                                           value="${Unit.PIECES.name}" required
                                    <c:if test="${product.unit == Unit.PIECES || saved_unit == Unit.PIECES}"> checked </c:if>>
                                    <label class="form-check-label" for="radioPieces">
                                        <fmt:message key="edit_product.pieces.text"/>
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="${Parameter.UNIT}" id="radioKilogram"
                                           value="${Unit.KILOGRAM.name}" required
                                    <c:if test="${product.unit == Unit.KILOGRAM || saved_unit == Unit.KILOGRAM}"> checked </c:if>>
                                    <label class="form-check-label" for="radioKilogram">
                                        <fmt:message key="edit_product.kilogram.text"/>
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="${Parameter.UNIT}" id="radioLitre"
                                           value="${Unit.LITRE.name}" required
                                    <c:if test="${product.unit == Unit.LITRE || saved_unit == Unit.LITRE}"> checked </c:if>>
                                    <label class="form-check-label" for="radioLitre">
                                        <fmt:message key="edit_product.litre.text"/>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </fieldset>

                    <div class="mb-3">
                        <label for="inputBarcode" class="form-label"><fmt:message key="edit_product.barcode.text"/></label>
                        <input type="text" class="form-control is-invalid" id="inputBarcode" name="${Parameter.BARCODE}"
                               minlength="4" maxlength="128" placeholder="<fmt:message key="edit_product.enter.barcode.text"/>"
                               value="${saved_barcode == null ? product.barcode : saved_barcode}" required>
                    </div>

                <button type="submit" class="mt-2 btn btn-primary"><fmt:message key="edit_product.save.changes.text"/></button>

                <a href="${pageContext.request.contextPath}/FrontController?command=/edit_product&productId=${product.id}"
                   class="mt-2 btn btn-primary" role="button"><fmt:message key="edit_product.reset.changes.text"/></a>

            </form>

        </div>
    </div>

    <div class="container mt-2 mb-3">
        <div class="card">
            <div class="card-body">
                <%@ include file="/WEB-INF/view/jsp/template/error_messages.jsp" %>
            </div>
        </div>
    </div>

    <div class="p-2 mx-2">
        <a href="${Path.PRODUCT_CATALOG}"
           type="submit" class="mb-5 btn btn-primary float-right">
            <fmt:message key="edit_product.come.back.to.product.catalog.text"/>
        </a>
    </div>

</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

</body>
</html>
