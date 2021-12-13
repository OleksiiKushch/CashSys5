<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CashSys.set.global.receipt.properties</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/jquery.slim.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/bootstrap.bundle.min.js"></script>

</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

<div class="container">

    <h1 class="mt-4"><fmt:message key="set_global_receipt_properties.global.receipt.properties.set.edit.form.text"/></h1>

    <div class="card my-3">
        <div class="card-body">
            <form class="was-validated"
                  action="<%= request.getContextPath() %>/FrontController?command=/set_global_receipt_properties"
                  method="post">

                <div class="mb-3">
                    <label for="inputOrganizationTaxIdNumber" class="form-label">
                        <fmt:message key="set_global_receipt_properties.organization.tax.id.number.text"/></label>
                    <input type="number" min="0" class="form-control is-invalid" id="inputOrganizationTaxIdNumber" name="organizationTaxIdNumber"
                           placeholder="<fmt:message key="set_global_receipt_properties.enter.organization.tax.id.number.text"/>"
                           value="${requestScope.global_receipt_properties.organizationTaxIdNumber}" required>
                </div>

                <div class="mb-3">
                    <label for="inputNameOrganization" class="form-label">
                        <fmt:message key="set_global_receipt_properties.name.organization.text"/></label>
                    <input type="text" class="form-control is-invalid" id="inputNameOrganization" name="nameOrganization"
                           placeholder="<fmt:message key="set_global_receipt_properties.enter.name.organization.text"/>"
                           value="${requestScope.global_receipt_properties.nameOrganization}" required>
                </div>

                <div class="mb-3">
                    <label for="inputAddressTradePoint" class="form-label">
                        <fmt:message key="set_global_receipt_properties.address.trade.point.text"/></label>
                    <input type="text" class="form-control is-invalid" id="inputAddressTradePoint" name="addressTradePoint"
                           placeholder="<fmt:message key="set_global_receipt_properties.enter.address.trade.point.text"/>"
                           value="${requestScope.global_receipt_properties.addressTradePoint}" required>
                </div>

                <div class="mb-3">
                    <label for="inputVat" class="form-label">
                        <fmt:message key="set_global_receipt_properties.vat.text"/></label>
                    <input type="number" min="0" step=".01" class="form-control is-invalid" id="inputVat" name="vat"
                           placeholder="<fmt:message key="set_global_receipt_properties.enter.vat.text"/>"
                           value="${requestScope.global_receipt_properties.vat}" required>
                </div>

                <div class="mb-3">
                    <label for="inputTaxationSys" class="form-label">
                        <fmt:message key="set_global_receipt_properties.taxation.system.text"/></label>
                    <input type="text" class="form-control is-invalid" id="inputTaxationSys" name="taxationSys"
                           placeholder="<fmt:message key="set_global_receipt_properties.enter.taxation.system.text"/>"
                           value="${requestScope.global_receipt_properties.taxationSys}" required>
                </div>

                <button type="submit" class="mb-3 btn btn-primary"><fmt:message key="set_global_receipt_properties.save.text"/></button>

            </form>

            <a href="" class="resetLink btn btn-danger" data-toggle="modal" data-target="#resetModal">
                <fmt:message key="set_global_receipt_properties.reset.all.receipt.properties.text"/></a>
        </div>
    </div>

    <div class="p-2 mx-2">
        <a href="${pageContext.request.contextPath}/FrontController?command=/main"
           type="submit" class="mb-5 btn btn-primary float-right">
            <fmt:message key="set_global_receipt_properties.come.back.to.main.page.text"/>
        </a>
    </div>

</div>

<div class="modal fade" id="resetModal" tabindex="-1" role="dialog" aria-labelledby="resetModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered"> <!-- role="document" -->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="resetModalCenterTitle"><fmt:message key="set_global_receipt_properties.warning.text"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <%--                    Are you sure you want to reset global receipt properties?--%>
                <h6 id="modalBody"></h6>
            </div>
            <div class="modal-footer">
                <form id="resetForm" action=""
                      method="post">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        <fmt:message key="set_global_receipt_properties.no.text"/></button>
                    <button type="submit" class="btn btn-primary">
                        <fmt:message key="set_global_receipt_properties.yes.text"/></button>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

<script type="text/javascript">
    $(".resetLink").click(function () {
        var str = "<fmt:message key="set_global_receipt_properties.set.global.receipt.properties.help.text"/>";
        $("#modalBody").html(str);
        $("#resetForm").attr("action", "<%= request.getContextPath() %>/FrontController?command=/reset_global_receipt_properties");
    });
</script>

</body>
</html>
