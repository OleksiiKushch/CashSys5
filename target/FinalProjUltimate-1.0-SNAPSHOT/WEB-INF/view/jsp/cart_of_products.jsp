<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<%@ page import="com.finalprojultimate.util.Attribute" %>
<%@ page import="com.finalprojultimate.util.Parameter" %>
<%@ page import="com.finalprojultimate.util.Path" %>
<%@ page import="com.finalprojultimate.util.Command" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="totalSum" uri="http://com.finalprojultimate/model/tag/TagTotalSum" %>
<%@ taglib prefix="amount" uri="http://com.finalprojultimate/model/tag/TagAmount" %>
<%@ taglib prefix="price" uri="http://com.finalprojultimate/model/tag/TagPrice" %>

<html>
<head>
    <title>CashSys.cart.of.products</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14"></script>

</head>

<c:set var="logged_user" value="${sessionScope[Attribute.LOGGED_USER]}"/>
<c:set var="cart" value="${sessionScope[Attribute.CART]}"/>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<security:check role="${Role.CASHIER.name}" loggedUserRole="${logged_user.role.name}" />

<div class="container-fluid">
    <h1 class="mt-4"><fmt:message key="cart_of_products.list.of.added.products.text"/></h1>

    <table class="table border rounded">
        <thead class="thead-light">
        <tr>
            <th scope="col"><fmt:message key="cart_of_products.id.text"/></th>
            <th scope="col"><fmt:message key="cart_of_products.name.text"/></th>
            <th scope="col"><fmt:message key="cart_of_products.sum.text"/></th>
            <th scope="col"><fmt:message key="cart_of_products.price.text"/></th>
            <th scope="col"><fmt:message key="cart_of_products.amount.text"/></th>
            <th scope="col"><fmt:message key="cart_of_products.barcode.text"/></th>
            <th scope="col"><fmt:message key="cart_of_products.action.text"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${requestScope.products}">
            <tr>
                <th class="col-md-1" scope="row">${product.id}</th>
                <td>${product.name}</td>
                <td class="col-md-1"><totalSum:get price="${product.price}" amount="${product.amount}" /></td>
                <td class="col-md-1">${product.price}</td>
                <td class="col-md-2"><amount:get amount="${product.amount}" unit="${product.unit}" />
                    <fmt:message key="${product.unit.message}"/></td>
                <td class="col-md-1">${product.barcode}</td>
                <td class="col-md-1">
                    <a href="" class="editLink" data-amount="${product.amount}" data-id="${product.id}" data-toggle="modal" data-target="#editModal">
                        <fmt:message key="cart_of_products.edit.text"/></a>
                    &nbsp &nbsp
                    <a href="" class="deleteLink" data-id="${product.id}" data-toggle="modal" data-target="#deleteModal">
                        <fmt:message key="cart_of_products.delete.text"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="p-2 mx-2">
        <h4 class="float-left"><fmt:message key="cart_of_products.sum.with.colon.text"/> <price:get price="${cart.sum}" /></h4>
        <a href="${Path.CREATE_NEW_RECEIPT}" type="submit" class="mb-5 btn btn-primary float-right">
            <fmt:message key="cart_of_products.come.back.to.receipt.creation.form.text"/>
        </a>
    </div>

</div>

<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalCenterTitle"><fmt:message key="cart_of_products.form.text"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <%-- Here a help message (form) on editing product in the cart --%>
                <h6 id="modalBodyEditForm"></h6>
            </div>
            <div class="modal-footer">
                <form id="editForm" class="was-validated" action="${Path.APP_CONTEXT}${Path.CONTROLLER}"
                      method="get">
                    <input name="${Path.COMMAND}" value="${Command.EDIT_PRODUCT_AMOUNT_FROM_CART}" type="hidden">
                    <input id="editFormProductId" name="${Parameter.PRODUCT_ID}" value="" type="hidden">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <button type="submit" class="input-group-text mb-3 btn btn-outline-success">
                                <fmt:message key="cart_of_products.edit.text"/></button>
                        </div>
                        <label for="inputAmount"></label>
                        <input type="number" min="0.001" step=".001" class="form-control is-invalid"
                               id="inputAmount" name="${Parameter.AMOUNT}" value="" required>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalCenterTitle"><fmt:message key="cart_of_products.warning.text"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <%-- Here a help message (warning) on deleting product in the cart --%>
                <h6 id="modalBodyDeleteForm"></h6>
            </div>
            <div class="modal-footer">
                <form id="deleteForm" action="${Path.APP_CONTEXT}${Path.CONTROLLER}"
                      method="get">
                    <input name="${Path.COMMAND}" value="${Command.DELETE_PRODUCT_FROM_CART}" type="hidden">
                    <input id="deleteFormProductId" name="${Parameter.PRODUCT_ID}" value="" type="hidden">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="cart_of_products.no.text"/></button>
                    <button type="submit" class="btn btn-primary"><fmt:message key="cart_of_products.yes.text"/></button>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

<script type="text/javascript">
    $(".editLink").click(function () {
        let id = $(this).attr("data-id");
        let amount = $(this).attr("data-amount");
        let str = "<fmt:message key="cart_of_products.edit.form.help.before.id.text"/>" + " "
            + id + " " + "<fmt:message key="cart_of_products.edit.form.help.after.id.text"/>";
        $("#modalBodyEditForm").html(str);
        $("#editFormProductId").attr("value", id);
        $("#inputAmount").attr("value", amount);
    });
</script>

<script type="text/javascript">
    $(".deleteLink").click(function () {
        let id = $(this).attr("data-id");
        let str = "<fmt:message key="cart_of_products.delete.warning.help.before.id.text"/>" + " "
            + id + "<fmt:message key="cart_of_products.delete.warning.help.after.id.text"/>";
        $("#modalBodyDeleteForm").html(str);
        $("#deleteFormProductId").attr("value", id);
    });
</script>

</body>
</html>
