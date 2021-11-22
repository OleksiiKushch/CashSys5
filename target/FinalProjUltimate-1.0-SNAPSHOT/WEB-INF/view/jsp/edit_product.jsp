<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.finalprojultimate.model.entity.product.Unit" %>
<html>
<head>
    <title>CashSys.edit.product</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/vue.js"></script>

</head>
<body class="d-flex flex-column min-vh-100">

    <%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

    <div class="container">

        <h1 class="mt-4">Product edit form:</h1>

        <div class="card my-3">
            <div class="card-body">
                <form class="was-validated"
                      action="<%= request.getContextPath() %>/FrontController?command=/edit_product&product_id=${requestScope.product.id}"
                      method="post">

                        <div class="mb-3">
                            <label for="inputProductName" class="form-label">Name:</label>
                            <input type="text" class="form-control is-invalid" id="inputProductName" name="productName"
                                   placeholder="Enter name" value="${requestScope.product.name}" required>
                        </div>

                        <div class="mb-3">
                            <label for="inputPrice" class="form-label">Price:</label>
                            <input type="number" min="0" step=".01" class="form-control is-invalid" id="inputPrice" name="price"
                                   placeholder="Enter price" value="${requestScope.product.price}" required>
                        </div>

                        <div class="mb-3">
                            <label for="inputAmount" class="form-label">Amount:</label>
                            <input type="number" min="0" step=".001" class="form-control is-invalid" id="inputAmount" name="amount"
                                   placeholder="Enter amount" value="${requestScope.product.amount}" required>
                        </div>

                        <fieldset class="form-group">
                            <div class="row">
                                <label class="col-form-label col-sm-2 pt-0">Unit:</label>
                                <div class="col-sm-10" id="radioForm">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="unit" id="radio1"
                                               value="pieces" required :checked="${requestScope.product.unit == Unit.PIECES}">
                                        <label class="form-check-label" for="radio1">
                                            pieces
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="unit" id="radio2"
                                               value="kilogram" required :checked="${requestScope.product.unit == Unit.KILOGRAM}">
                                        <label class="form-check-label" for="radio2">
                                            kilogram
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="unit" id="radio3"
                                               value="litre" required :checked="${requestScope.product.unit == Unit.LITRE}">
                                        <label class="form-check-label" for="radio3">
                                            litre
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </fieldset>

                        <div class="mb-3">
                            <label for="inputBarcode" class="form-label">Barcode:</label>
                            <input type="text" class="form-control is-invalid" id="inputBarcode" name="barcode"
                                   placeholder="Enter barcode" value="${requestScope.product.barcode}" required>
                        </div>

                    <button type="submit" class="mb-3 btn btn-primary">Save changes</button>

                </form>

            </div>
        </div>

        <div class="p-2 mx-2">
            <a href="${pageContext.request.contextPath}/FrontController?command=/product_catalog&page=1&page_size=8"
               type="submit" class="mb-5 btn btn-primary float-right">
                Come back to product catalog
            </a>
        </div>

    </div>

    <%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

    <script>
        let radioFormListener = new Vue({
            el: '#radioForm'
        });
    </script>

</body>
</html>
