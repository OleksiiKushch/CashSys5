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

</head>

<c:set var="logged_user" value="${sessionScope[Attribute.LOGGED_USER]}"/>

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
                           placeholder="<fmt:message key="create_new_product.enter.name.text"/>" required>
                </div>

                <div class="mb-3">
                    <label for="inputPrice" class="form-label"><fmt:message key="create_new_product.price.text"/></label>
                    <input type="number" min="0" step=".01" class="form-control is-invalid" id="inputPrice" name="${Parameter.PRICE}"
                           placeholder="<fmt:message key="create_new_product.enter.price.text"/>" required>
                </div>

                <div class="mb-3">
                    <label for="inputAmount" class="form-label"><fmt:message key="create_new_product.amount.text"/></label>
                    <input type="number" min="0" step=".001" class="form-control is-invalid" id="inputAmount" name="${Parameter.AMOUNT}"
                           placeholder="<fmt:message key="create_new_product.enter.amount.text"/>" required>
                </div>

                <fieldset class="form-group">
                    <div class="row">
                        <label class="col-form-label col-sm-2 pt-0"><fmt:message key="create_new_product.unit.text"/></label>
                        <div class="col-sm-10">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="${Parameter.UNIT}" id="radioPieces"
                                       value="${Unit.PIECES.name}" checked>
                                <label class="form-check-label" for="radioPieces">
                                    <fmt:message key="create_new_product.pieces.text"/>
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="${Parameter.UNIT}" id="radioKilogram"
                                       value="${Unit.KILOGRAM.name}">
                                <label class="form-check-label" for="radioKilogram">
                                    <fmt:message key="create_new_product.kilogram.text"/>
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="${Parameter.UNIT}" id="radioLitre"
                                       value="${Unit.LITRE.name}">
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
                           placeholder="<fmt:message key="create_new_product.enter.barcode.text"/>" required>
                </div>

                <button type="submit" class="mb-3 btn btn-primary"><fmt:message key="create_new_product.create.text"/></button>

<%--                <div class="mb-1">--%>
<%--                    <label for="globalSettings" class="form-label">View global settings</label>--%>
<%--                    <a href="${pageContext.request.contextPath}/FrontController?command=/global_settings"--%>
<%--                       class="fw-bold text-body" id="globalSettings">--%>
<%--                        <u>here</u>--%>
<%--                    </a>--%>
<%--                </div>--%>

            </form>

        </div>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

</body>
</html>
