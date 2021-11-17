<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CashSys.create.new.receipt</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/scrollableTable.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/jquery.slim.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/bootstrap.bundle.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/vue.js"></script>

</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

    <div class="container">

        <h1 class="mt-4">New receipt creation form:</h1>

        <div class="card my-3">
            <div class="card-header">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="mb-3 btn btn-primary" href="${pageContext.request.contextPath}/FrontController?command=/cart_of_products">
                            List of added products
                        </a>
                    </li>
                    <li class="nav-item active">
                        <form class="form-inline mt-2 mt-md-0"
                        action="<%= request.getContextPath() %>/FrontController"
                        method="get">
                            <input name="command" value="/find_products_by_parameter" type="hidden">
                            <div class="mb-3">
                                <label for="selectSearchingParam" class="form-label">Select search parameter:</label>
                            </div>
                            <div class="form-group mb-3 mx-2">
                                <select class="form-control" id="selectSearchingParam" name="parameter_searching">
                                    <option value="byBarcode" selected>by barcode</option>
                                    <option value="byName">by name</option>
                                </select>
                            </div>
                            <div class="form-group mb-3 mx-3">
                                <input class="form-control mr-sm-2" type="text" name="pattern_searching"
                                       placeholder="Search" aria-label="Search">
                                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                            </div>
                        </form>
                    </li>
                </ul>
            </div>

            <div class="card-body">

                <table class="table table-striped table-fixed border rounded">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col" class="col-md-1">id</th>
                            <th scope="col" class="col-md-5">Name</th>
                            <th scope="col" class="col-md-2">Amount</th>
                            <th scope="col" class="col-md-2">Barcode</th>
                            <th scope="col" class="col-md-2">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${requestScope.products_found}">
                            <tr>
                                <th class="col-md-1" scope="row">${product.id}</th>
                                <td class="col-md-5">${product.name}</td>
                                <td class="col-md-2">${product.getAmount()} ${product.unit.name}</td>
                                <td class="col-md-2">${product.barcode}</td>
                                <td class="col-md-2">
                                    <form class="was-validated mb-0" method="get"
                                    action="<%= request.getContextPath() %>/FrontController">
                                        <input name="command" value="/add_product_to_cart" type="hidden">
                                        <input name="product_id" value="${product.id}" type="hidden">
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <button type="submit" class="input-group-text mb-3 btn btn-outline-success">
                                                    add</button>
                                            </div>
                                            <label for="inputAmount"></label>
                                            <input type="number" min="0.001" step=".001" class="form-control"
                                                   id="inputAmount" name="amount" value="1" required>
                                        </div>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>

            <div class="card-footer" id="sum">
                <h4>Sum: {{ (${sum}).toFixed(2) }} </h4>
            </div>
        </div>

        <form name="paymentForm" id="paymentForm" class="was-validated"
              action="<%= request.getContextPath() %>/FrontController?command=/create_new_receipt"
              method="post">
            <div class="card my-3">
                <div class="card-body">
                    <fieldset class="form-group">
                        <div class="row">
                            <label class="col-form-label col-sm-2 pt-0">Payment type:</label>
                            <div class="col-sm-10">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="payment" id="radio1"
                                           value="electronic" checked @click="disabledIn(); setPaid();" >
                                    <label class="form-check-label" for="radio1">
                                        electronic
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="payment" id="radio2"
                                           value="cash" @click="unDisabledIn">
                                    <label class="form-check-label" for="radio2">
                                        cash
                                    </label>
                                </div>
                            </div>
                        </div>
                    </fieldset>

                    <div class="mb-3">
                        <label for="inputPaid" class="form-label">Paid:</label>
                        <input type="number" min="0" step=".01" id="inputPaid" name="paid" :disabled="disabled == 0" v-model="paid"
                               class="form-control" placeholder="Enter paid" required > <!-- disabled -->
                    </div>

                </div>
                <div class="card-footer">
                    <h6>Change: {{ (paid - ${sum}).toFixed(2) }} </h6>
                </div>
            </div>
            <div class="p-2 mx-2">
                <button type="submit" class="mb-5 btn btn-primary btn-lg float-right" :disabled="validChange == 0">Create</button>
            </div>
        </form>


    </div>

    <%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

<%--    <script>--%>
<%--        const inputPaid = document.getElementById('inputPaid');--%>
<%--        const rad = document.paymentForm.payment;--%>
<%--        let prev = null;--%>
<%--        const electronicType = document.getElementById('radio1');--%>
<%--        const cashType = document.getElementById('radio2');--%>
<%--        for(let i = 0; i < rad.length; i++) {--%>
<%--            rad[i].onclick = function() {--%>
<%--                if(this !== prev) {--%>
<%--                    prev = this;--%>
<%--                    console.log(prev);--%>
<%--                    if (prev === electronicType) {--%>
<%--                        inputPaid.setAttribute('disabled', 'disabled');--%>
<%--                    }--%>
<%--                    if (prev === cashType) {--%>
<%--                        inputPaid.removeAttribute('disabled');--%>
<%--                    }--%>
<%--                }--%>
<%--            };--%>
<%--        }--%>
<%--    </script>--%>

    <script>
        let paymentFormListener = new Vue({
            el: '#paymentForm',
            data: {
                disabled: 0,
                paid: ${sum},
                validChange: 1
            },
            methods: {
                disabledIn() {
                    this.disabled = 0;
                },
                unDisabledIn() {
                    this.disabled = 1;
                },
                setPaid() {
                    this.paid = ${sum};
                }
            }
        });
        let sumListener = new Vue({
            el: '#sum'
        })
    </script>

</body>
</html>