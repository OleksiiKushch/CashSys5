<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>CashSys.see.receipt.details</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/jquery.slim.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/bootstrap.bundle.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/vue.js"></script>

</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

<div class="container-fluid">
    <h1 class="mt-4"><fmt:message key="see_receipt_details.detail.id.text"/> ${receipt.id}<fmt:message key="see_receipt_details.receipt.information.text"/></h1>
    <form action="<%= request.getContextPath() %>/FrontController?command=/create_reject_receipt" method="post">
        <h3 class="mt-4"><fmt:message key="see_receipt_details.list.of.products.text"/></h3>
        <table class="table border rounded" id="productsTable">
            <thead class="thead-light">
            <tr>
                <th scope="col"><fmt:message key="see_receipt_details.id.text"/></th>
                <th scope="col"><fmt:message key="see_receipt_details.name.text"/></th>
                <th scope="col"><fmt:message key="see_receipt_details.sum.text"/></th>
                <th scope="col"><fmt:message key="see_receipt_details.price.text"/></th>
                <th scope="col"><fmt:message key="see_receipt_details.amount.text"/></th>
                <th scope="col"><fmt:message key="see_receipt_details.barcode.text"/></th>
                <th scope="col"><fmt:message key="see_receipt_details.action.text"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${requestScope.products}">
                <tr>
                    <th class="col-md-1" scope="row">${product.id}</th>
                    <td>${product.name}</td>
                    <td class="col-md-1">${product.price.multiply(product.amount).setScale(2)}</td>
                    <td class="col-md-1">${product.price}</td>
                    <td class="col-md-1">${product.getAmount()} <fmt:message key="${product.unit.message}"/></td>
                    <td class="col-md-1">${product.barcode}</td>
                    <td class="col-md-2">
                        <div class="d-flex justify-content-between">
                            <input name="receiptId" value="${receipt.id}" type="hidden">
                            <div class="custom-control custom-switch">
                                <input type="checkbox" class="custom-control-input rejectSwitcher" id="rejectSwitch${product.id}"
                                       name="rejectReceiptId" value="${product.id}">
                                <label class="custom-control-label" for="rejectSwitch${product.id}">
                                    <fmt:message key="see_receipt_details.reject.text"/></label>
                            </div>
                            <div class="input-group mx-3 was-validated">
                                <label for="rejectInputAmount${product.id}"></label>
                                <input type="number" min="0.001" max="${product.amount}" step=".001"
                                       class="form-control form-control-sm is-valid col-md-8 rejectInputAmount"
                                       id="rejectInputAmount${product.id}" name="amount" value="${product.amount}" required disabled>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="row float-right">
            <div class="p-2 mx-2">
                <button type="submit" class="mb-5 btn btn-primary">
                    <fmt:message key="see_receipt_details.save.text"/>
                </button>
            </div>
            <div class="p-2 mx-2">
                <a class="mb-5 btn btn-secondary rejectAllLink text-light">
                    <fmt:message key="see_receipt_details.reject.all.text"/>
                </a>
            </div>
        </div>

    </form>


    <div class="p-2 mx-2">
        <h4 class="" id="sum"><fmt:message key="see_receipt_details.sum.with.colon.text"/> ${receipt_service.getSumReceiptById(receipt.id).setScale(2)} </h4>
    </div>

    <div class="p-2 mx-2">
        <h6 class="" id="change"><fmt:message key="see_receipt_details.change.text"/> ${receipt.change} </h6>
    </div>

    <div class="row mt-4">
        <div class="col-sm-4">
            <div class="card mx-3" >
                <div class="card-body">
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.cashier.text"/> &nbsp</p>
                        <h6>(<fmt:message key="see_receipt_details.id.text"/>: ${receipt.userId}) ${user_service.getFormattedNameById(receipt.userId)}</h6>
                    </div>
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.type.payment.text"/> &nbsp</p><h6><fmt:message key="${receipt.payment.message}"/></h6>
                    </div>
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.date.time.text"/> &nbsp</p><h6>${receipt.dateTime}</h6>
                    </div>
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.status.text"/> &nbsp</p><h6><fmt:message key="${receipt.status.message}"/></h6>
                    </div>
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.root.receipt.id.text"/> &nbsp</p><h6>${receipt_details.rootReceiptId}</h6>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-8">
            <div class="card mx-3" >
                <div class="card-body">
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.organization.tax.id.number.text"/> &nbsp</p><h6>${receipt_details.organizationTaxIdNumber}</h6>
                    </div>
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.name.organization.text"/> &nbsp</p><h6>${receipt_details.nameOrganization}</h6>
                    </div>
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.address.trade.point.text"/> &nbsp</p><h6>${receipt_details.addressTradePoint}</h6>
                    </div>
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.vat.text"/> &nbsp</p><h6>${receipt_details.vat}</h6>
                    </div>
                    <div class="row p-2 mx-2">
                        <p><fmt:message key="see_receipt_details.taxation.system.text"/> &nbsp</p><h6>${receipt_details.taxationSys}</h6>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="p-2 mx-2">
        <a href="${pageContext.request.contextPath}/FrontController?command=/receipt_catalog&page=1&page_size=8"
           type="submit" class="mt-5 mb-5 btn btn-primary float-left">
            <fmt:message key="see_receipt_details.come.back.to.receipt.catalog.text"/>
        </a>
    </div>

</div>
<%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

<script type="text/javascript">
    $(".rejectAllLink").click(function () {
        if ($(".rejectAllLink").hasClass("btn-secondary")) {
            $(this).removeClass("btn-secondary").addClass("btn-primary");
            $(".rejectSwitcher").prop('checked', true);
            $(".rejectInputAmount").prop('disabled', false);
        } else {
            $(this).removeClass("btn-primary").addClass("btn-secondary");
            $(".rejectSwitcher").prop('checked', false);
            $(".rejectInputAmount").prop('disabled', true);
        }
    });
</script>

<script>
    $(".rejectSwitcher").click(function () {
        let id = $(this).val();
        document.getElementById("rejectInputAmount" + id).disabled = !$(this).prop('checked');
    });
</script>

</body>
</html>
