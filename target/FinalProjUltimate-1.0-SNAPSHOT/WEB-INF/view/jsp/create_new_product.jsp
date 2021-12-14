<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>CashSys.create.new.product</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

<div class="container">

    <h1 class="mt-4"><fmt:message key="create_new_product.new.product.creation.form.text"/></h1>

    <security:check role="commodity expert" loggedUserRole="${sessionScope.logged_user.role.name}" />

    <div class="card my-3">
        <div class="card-body">
            <form class="was-validated" action="<%= request.getContextPath() %>/FrontController?command=/create_new_product"
                  method="post">

                <div class="mb-3">
                    <label for="inputProductName" class="form-label"><fmt:message key="create_new_product.name.text"/></label>
                    <input type="text" class="form-control is-invalid" id="inputProductName" name="productName"
                           placeholder="<fmt:message key="create_new_product.enter.name.text"/>" required>
                </div>

                <div class="mb-3">
                    <label for="inputPrice" class="form-label"><fmt:message key="create_new_product.price.text"/></label>
                    <input type="number" min="0" step=".01" class="form-control is-invalid" id="inputPrice" name="price"
                           placeholder="<fmt:message key="create_new_product.enter.price.text"/>" required>
                </div>

                <div class="mb-3">
                    <label for="inputAmount" class="form-label"><fmt:message key="create_new_product.amount.text"/></label>
                    <input type="number" min="0" step=".001" class="form-control is-invalid" id="inputAmount" name="amount"
                           placeholder="<fmt:message key="create_new_product.enter.amount.text"/>" required>
                </div>

                <fieldset class="form-group">
                    <div class="row">
                        <label class="col-form-label col-sm-2 pt-0"><fmt:message key="create_new_product.unit.text"/></label>
                        <div class="col-sm-10">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="unit" id="radio1"
                                       value="pieces" checked>
                                <label class="form-check-label" for="radio1">
                                    <fmt:message key="create_new_product.pieces.text"/>
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="unit" id="radio2"
                                       value="kilogram">
                                <label class="form-check-label" for="radio2">
                                    <fmt:message key="create_new_product.kilogram.text"/>
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="unit" id="radio3"
                                       value="litre">
                                <label class="form-check-label" for="radio3">
                                    <fmt:message key="create_new_product.litre.text"/>
                                </label>
                            </div>
                        </div>
                    </div>
                </fieldset>

                <div class="mb-3">
                    <label for="inputBarcode" class="form-label"><fmt:message key="create_new_product.barcode.text"/></label>
                    <input type="text" class="form-control is-invalid" id="inputBarcode" name="barcode"
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

<%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>
