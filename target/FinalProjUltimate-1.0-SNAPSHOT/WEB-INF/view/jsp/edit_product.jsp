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

    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14"></script>

</head>

<c:set var="logged_user" value="${sessionScope[Attribute.LOGGED_USER]}"/>
<c:set var="product" value="${requestScope[Attribute.PRODUCT]}"/>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<security:check role="${Role.COMMODITY_EXPERT.name}" loggedUserRole="${logged_user.role.name}" />

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
                               placeholder="<fmt:message key="edit_product.enter.name.text"/>" value="${product.name}" required>
                    </div>

                    <div class="mb-3">
                        <label for="inputPrice" class="form-label"><fmt:message key="edit_product.price.text"/></label>
                        <input type="number" min="0" step=".01" class="form-control is-invalid" id="inputPrice" name="${Parameter.PRICE}"
                               placeholder="<fmt:message key="edit_product.enter.price.text"/>" value="${product.price}" required>
                    </div>

                    <div class="mb-3">
                        <label for="inputAmount" class="form-label"><fmt:message key="edit_product.amount.text"/></label>
                        <input type="number" min="0" step=".001" class="form-control is-invalid" id="inputAmount" name="${Parameter.AMOUNT}"
                               placeholder="<fmt:message key="edit_product.enter.amount.text"/>" value="${product.amount}" required>
                    </div>

                    <fieldset class="form-group">
                        <div class="row">
                            <label class="col-form-label col-sm-2 pt-0"><fmt:message key="edit_product.unit.text"/></label>
                            <div class="col-sm-10" id="radioForm">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="${Parameter.UNIT}" id="radioPieces"
                                           value="${Unit.PIECES.name}" required :checked="${product.unit == Unit.PIECES}">
                                    <label class="form-check-label" for="radioPieces">
                                        <fmt:message key="edit_product.pieces.text"/>
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="${Parameter.UNIT}" id="radioKilogram"
                                           value="${Unit.KILOGRAM.name}" required :checked="${product.unit == Unit.KILOGRAM}">
                                    <label class="form-check-label" for="radioKilogram">
                                        <fmt:message key="edit_product.kilogram.text"/>
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="${Parameter.UNIT}" id="radioLitre"
                                           value="${Unit.LITRE.name}" required :checked="${product.unit == Unit.LITRE}">
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
                               placeholder="<fmt:message key="edit_product.enter.barcode.text"/>" value="${product.barcode}" required>
                    </div>

                <button type="submit" class="mb-3 btn btn-primary"><fmt:message key="edit_product.save.changes.text"/></button>

            </form>

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

<script type="text/javascript">
    let radioFormListener = new Vue({
        el: '#radioForm'
    });
</script>

</body>
</html>
