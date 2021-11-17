<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CashSys.cart.of.products</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/jquery.slim.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/bootstrap.bundle.min.js"></script>
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

<div class="container-fluid">
    <h1 class="mt-4">List of added products:</h1>

    <table class="table border rounded">
        <thead class="thead-light">
        <tr>
            <th scope="col">id</th>
            <th scope="col">Name</th>
            <th scope="col">Amount</th>
            <th scope="col">Barcode</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${requestScope.products}">
            <tr>
                <th class="col-md-1" scope="row">${product.id}</th>
                <td>${product.name}</td>
                <td class="col-md-2">${product.getAmount()} ${product.unit.name}</td>
                <td class="col-md-1">${product.barcode}</td>
                <td class="col-md-1">
                    <a href="" class="editLink" data-id="${product.id}" data-toggle="modal" data-target="#editModal">
                        edit</a>
                    &nbsp &nbsp
                    <a href="" class="deleteLink" data-id="${product.id}" data-toggle="modal" data-target="#deleteModal">
                        delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="p-2 mx-2">
        <a href="${pageContext.request.contextPath}/FrontController?command=/create_new_receipt"
                type="submit" class="mb-5 btn btn-primary float-right">
            Come back to receipt creation form
        </a>
    </div>

</div>

<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered"> <!-- role="document" -->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalCenterTitle">Form</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <%--                    Form for edit the amount of product with id: ${product.id} in the cart--%>
                <h6 id="modalBodyEditForm"></h6>
            </div>
            <div class="modal-footer">
                <form id="editForm" class="was-validated" action="<%= request.getContextPath() %>/FrontController"
                      method="get">
                    <input name="command" value="/edit_product_amount_from_cart" type="hidden">
                    <input id="editFormProductId" name="product_id" value="" type="hidden">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <button type="submit" class="input-group-text mb-3 btn btn-outline-success">
                                edit</button>
                        </div>
                        <label for="inputAmount"></label>
                        <input type="number" min="0.001" step=".001" class="form-control"
                               id="inputAmount" name="amount" value="1" required>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered"> <!-- role="document" -->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalCenterTitle">Warning!</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <%--                    Are you sure you want to drop this product (with id: ${product.id}) from a flow receipt?--%>
                <h6 id="modalBodyDeleteForm"></h6>
            </div>
            <div class="modal-footer">
                <form id="deleteForm" action="<%= request.getContextPath() %>/FrontController"
                      method="get">
                    <input name="command" value="/delete_product_from_cart" type="hidden">
                    <input id="deleteFormProductId" name="product_id" value="" type="hidden">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                    <button type="submit" class="btn btn-primary">Yes</button>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

<script type="text/javascript">
    $(".editLink").click(function () {
        let id = $(this).attr("data-id");
        let str = "Form for edit the amount of product with id: " + id + " in the cart";
        $("#modalBodyEditForm").html(str);
        $("#editFormProductId").attr("value", id);
    });
</script>

<script type="text/javascript">
    $(".deleteLink").click(function () {
        let id = $(this).attr("data-id");
        let str = "Are you sure you want to drop this product (with id: " + id + ") from a flow receipt?";
        $("#modalBodyDeleteForm").html(str);
        $("#deleteFormProductId").attr("value", id);
    });
</script>

</body>
</html>
