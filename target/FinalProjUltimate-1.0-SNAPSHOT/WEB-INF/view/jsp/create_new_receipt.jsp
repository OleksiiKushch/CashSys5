<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<%@ page import="com.finalprojultimate.model.entity.receipt.Payment" %>
<%@ page import="com.finalprojultimate.util.Parameter" %>
<%@ page import="com.finalprojultimate.util.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="amount" uri="http://com.finalprojultimate/model/tag/TagAmount" %>
<%@ taglib prefix="price" uri="http://com.finalprojultimate/model/tag/TagPrice" %>

<html>
<head>
    <title>CashSys.create.new.receipt</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/custom/scrollable.table.css">

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

<div class="container">

    <h1 class="mt-4"><fmt:message key="create_new_receipt.new.receipt.creation.form.text"/></h1>

    <div class="card my-3">
        <div class="card-header">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="mb-3 btn btn-primary" href="${Path.CART_OF_PRODUCTS}">
                        <fmt:message key="create_new_receipt.list.of.added.products.text"/>
                    </a>
                </li>
                <li class="nav-item active">
                    <form class="form-inline mt-2 mt-md-0"
                    action="${Path.APP_CONTEXT}${Path.CONTROLLER}"
                    method="get">
                        <input name="${Path.COMMAND}" value="${Command.FIND_PRODUCTS_BY_PARAMETER}" type="hidden">
                        <div class="mb-3">
                            <label for="selectSearchingParam" class="form-label">
                                <fmt:message key="create_new_receipt.select.search.parameter.text"/></label>
                        </div>
                        <div class="form-group mb-3 mx-2">
                            <select class="form-control" id="selectSearchingParam" name="${Attribute.PARAMETER_SEARCHING}">
                                <option value="${Parameter.BY_BARCODE}" selected><fmt:message key="create_new_receipt.by.barcode.text"/></option>
                                <option value="${Parameter.BY_NAME}"><fmt:message key="create_new_receipt.by.name.text"/></option>
                            </select>
                        </div>
                        <div class="form-group mb-3 mx-3">
                            <input class="form-control mr-sm-2" type="text" name="${Attribute.PATTERN_SEARCHING}"
                                   placeholder="<fmt:message key="create_new_receipt.search.text"/>" aria-label="Search">
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                                <fmt:message key="create_new_receipt.search.text"/></button>
                        </div>
                    </form>
                </li>
            </ul>
        </div>

        <div class="card-body">

            <table class="table table-striped table-fixed border rounded">
                <thead class="thead-light">
                    <tr>
                        <th scope="col" class="col-md-1"><fmt:message key="create_new_receipt.id.text"/></th>
                        <th scope="col" class="col-md-3"><fmt:message key="create_new_receipt.name.text"/></th>
                        <th scope="col" class="col-md-1"><fmt:message key="create_new_receipt.price.text"/></th>
                        <th scope="col" class="col-md-2"><fmt:message key="create_new_receipt.amount.text"/></th>
                        <th scope="col" class="col-md-2"><fmt:message key="create_new_receipt.barcode.text"/></th>
                        <th scope="col" class="col-md-3"><fmt:message key="create_new_receipt.action.text"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${requestScope.products_found}">
                        <tr>
                            <th class="col-md-1" scope="row">${product.id}</th>
                            <td class="col-md-3">${product.name}</td>
                            <td class="col-md-1">${product.price}</td>
                            <td class="col-md-2"><amount:get amount="${product.amount}" unit="${product.unit}" />
                                <fmt:message key="${product.unit.message}"/></td>
                            <td class="col-md-2">${product.barcode}</td>
                            <td class="col-md-3">
                                <form class="was-validated mb-0" method="get" action="${Path.APP_CONTEXT}${Path.CONTROLLER}">
                                    <input name="${Path.COMMAND}" value="${Command.ADD_PRODUCT_TO_CART}" type="hidden">
                                    <input name="${Parameter.PRODUCT_ID}" value="${product.id}" type="hidden">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <button type="submit" class="input-group-text mb-3 btn btn-outline-success">
                                                <fmt:message key="create_new_receipt.add.text"/></button>
                                        </div>
                                        <label for="inputAmount"></label>
                                        <input type="number" min="0.001" step=".001" class="form-control is-valid"
                                               id="inputAmount" name="${Parameter.AMOUNT}" value="1" required>
                                    </div>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>

        <div class="card-footer">
            <h4><fmt:message key="create_new_receipt.sum.text"/>
                <price:get price="${cart.sum}" /></h4>
        </div>
    </div>

    <form name="paymentForm" id="paymentForm" class="was-validated"
          action="${Path.CREATE_NEW_RECEIPT}" method="post">
        <div class="card my-3">
            <div class="card-body">
                <fieldset class="form-group">
                    <div class="row">
                        <label class="col-form-label col-sm-2 pt-0"><fmt:message key="create_new_receipt.payment.type.text"/></label>
                        <div class="col-sm-10">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="${Parameter.PAYMENT}" id="radioElectronic"
                                       value="${Payment.ELECTRONIC.name}" checked @click="disabledIn(); setPaid();" >
                                <label class="form-check-label" for="radioElectronic">
                                    <fmt:message key="create_new_receipt.electronic.text"/>
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="${Parameter.PAYMENT}" id="radioCash"
                                       value="${Payment.CASH.name}" @click="unDisabledIn">
                                <label class="form-check-label" for="radioCash">
                                    <fmt:message key="create_new_receipt.cash.text"/>
                                </label>
                            </div>
                        </div>
                    </div>
                </fieldset>

                <div class="mb-3">
                    <label for="inputPaid" class="form-label"><fmt:message key="create_new_receipt.paid.text"/></label>
                    <input type="number" min="0" step=".01" id="inputPaid" name="paid" :disabled="disabled == 0" v-model="paid"
                           value="" class="form-control" placeholder="<fmt:message key="create_new_receipt.enter.paid.text"/>" required >
                </div>

            </div>
            <div class="card-footer">
                <h6><fmt:message key="create_new_receipt.change.text"/> {{ (paid - ${cart.sum}).toFixed(2) }} </h6>
            </div>
        </div>
        <div class="p-2 mx-2">
            <button type="submit" class="mb-5 btn btn-primary btn-lg float-right"
                    :disabled="${cart.container.isEmpty()} || paid - ${cart.sum} < 0">
                <fmt:message key="create_new_receipt.create.text"/>
            </button>
        </div>
    </form>


</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

<script type="text/javascript">
    let paymentFormListener = new Vue({
        el: '#paymentForm',
        data: {
            disabled: 0,
            paid: ${cart.sum}
        },
        methods: {
            disabledIn() {
                this.disabled = 0;
            },
            unDisabledIn() {
                this.disabled = 1;
            },
            setPaid() {
                this.paid = ${cart.sum};
            }
        }
    });
</script>

</body>
</html>
