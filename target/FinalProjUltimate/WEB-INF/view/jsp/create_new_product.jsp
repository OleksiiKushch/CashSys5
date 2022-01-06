<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<%@ page import="com.finalprojultimate.model.entity.product.Unit" %>
<%@ page import="com.finalprojultimate.util.Attribute" %>
<%@ page import="com.finalprojultimate.util.Parameter" %>
<%@ page import="com.finalprojultimate.util.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>CashSys.create.new.product</title>

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
<c:set var="saved_product_name" value="${requestScope[Attribute.PRODUCT_DATA].name}"/>
<c:set var="saved_price" value="${requestScope[Attribute.PRODUCT_DATA].price}"/>
<c:set var="saved_amount" value="${requestScope[Attribute.PRODUCT_DATA].amount}"/>
<c:set var="saved_unit" value="${requestScope[Attribute.PRODUCT_DATA].unit}"/>
<c:set var="saved_barcode" value="${requestScope[Attribute.PRODUCT_DATA].barcode}"/>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<div class="container">

    <h1 class="mt-4"><fmt:message key="create_new_product.new.product.creation.form.text"/></h1>

    <security:check role="${Role.COMMODITY_EXPERT.name}" loggedUserRole="${logged_user.role.name}" />

    <div class="card my-3">
        <div class="card-body">
            <form class="was-validated" action="${Path.CREATE_NEW_PRODUCT}" method="post">

                <div class="mb-3">
                    <label for="inputProductName" class="form-label"><fmt:message key="create_new_product.name.text"/></label>
                    <input type="text" class="form-control is-invalid" id="inputProductName" name="${Parameter.PRODUCT_NAME}"
                           maxlength="512"
                    <c:if test="${saved_product_name != null}"> value="${saved_product_name}" </c:if>
                           placeholder="<fmt:message key="create_new_product.enter.name.text"/>" required>
                </div>

                <div class="mb-3">
                    <label for="inputPrice" class="form-label"><fmt:message key="create_new_product.price.text"/></label>
                    <input type="number" min="0" step=".01" class="form-control is-invalid" id="inputPrice" name="${Parameter.PRICE}"
                    <c:if test="${saved_price != null}"> value="${saved_price}" </c:if>
                           placeholder="<fmt:message key="create_new_product.enter.price.text"/>" required>
                </div>

                <div class="mb-3">
                    <label for="inputAmount" class="form-label"><fmt:message key="create_new_product.amount.text"/></label>
                    <input type="number" min="0" step=".001" class="form-control is-invalid" id="inputAmount" name="${Parameter.AMOUNT}"
                    <c:if test="${saved_amount != null}"> value="${saved_amount}" </c:if>
                           placeholder="<fmt:message key="create_new_product.enter.amount.text"/>" required>
                </div>

                <fieldset class="form-group">
                    <div class="row">
                        <label class="col-form-label col-sm-2 pt-0"><fmt:message key="create_new_product.unit.text"/></label>
                        <div class="col-sm-10">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="${Parameter.UNIT}" id="radioPieces"
                                       value="${Unit.PIECES.name}"
                                <c:if test="${saved_unit != null && saved_unit == Unit.PIECES}"> checked </c:if> required>
                                <label class="form-check-label" for="radioPieces">
                                    <fmt:message key="create_new_product.pieces.text"/>
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="${Parameter.UNIT}" id="radioKilogram"
                                       value="${Unit.KILOGRAM.name}"
                                <c:if test="${saved_unit != null && saved_unit == Unit.KILOGRAM}"> checked </c:if>>
                                <label class="form-check-label" for="radioKilogram">
                                    <fmt:message key="create_new_product.kilogram.text"/>
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="${Parameter.UNIT}" id="radioLitre"
                                       value="${Unit.LITRE.name}"
                                <c:if test="${saved_unit != null && saved_unit == Unit.LITRE}"> checked </c:if>>
                                <label class="form-check-label" for="radioLitre">
                                    <fmt:message key="create_new_product.litre.text"/>
                                </label>
                            </div>
                        </div>
                    </div>
                </fieldset>

                <div class="mb-3">
                    <label for="inputBarcode" class="form-label"><fmt:message key="create_new_product.barcode.text"/></label>
                    <input type="text" class="form-control is-invalid" id="inputBarcode" name="${Parameter.BARCODE}"
                           minlength="4" maxlength="128"
                    <c:if test="${saved_barcode != null}"> value="${saved_barcode}" </c:if>
                           placeholder="<fmt:message key="create_new_product.enter.barcode.text"/>" required>
                </div>

                <button type="submit" class="mb-3 btn btn-primary"><fmt:message key="create_new_product.create.text"/></button>

            </form>

        </div>
    </div>
</div>

<div class="container mt-2 mb-5">
    <div class="card">
        <div class="card-body">
            <%@ include file="/WEB-INF/view/jsp/template/error_messages.jsp" %>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

</body>
</html>
