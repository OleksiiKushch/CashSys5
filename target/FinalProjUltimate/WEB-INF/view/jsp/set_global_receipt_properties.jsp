<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<%@ page import="com.finalprojultimate.util.Attribute" %>
<%@ page import="com.finalprojultimate.util.Parameter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>CashSys.set.global.receipt.properties</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</head>

<c:set var="logged_user" value="${sessionScope[Attribute.LOGGED_USER]}"/>
<c:set var="saved_global_receipt_properties" value="${requestScope[Attribute.GLOBAL_RECEIPT_PROPERTIES]}"/>
<c:set var="saved_organization_taxId_number" value="${requestScope[Attribute.GLOBAL_RECEIPT_PROPERTIES_DATA].organizationTaxIdNumber}"/>
<c:set var="saved_name_organization" value="${requestScope[Attribute.GLOBAL_RECEIPT_PROPERTIES_DATA].nameOrganization}"/>
<c:set var="saved_address_trade_point" value="${requestScope[Attribute.GLOBAL_RECEIPT_PROPERTIES_DATA].addressTradePoint}"/>
<c:set var="saved_vat" value="${requestScope[Attribute.GLOBAL_RECEIPT_PROPERTIES_DATA].vat}"/>
<c:set var="saved_taxation_sys" value="${requestScope[Attribute.GLOBAL_RECEIPT_PROPERTIES_DATA].taxationSys}"/>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<security:check role="${Role.SENIOR_CASHIER.name}" loggedUserRole="${logged_user.role.name}" />

<div class="container">

    <h1 class="mt-4"><fmt:message key="set_global_receipt_properties.global.receipt.properties.set.edit.form.text"/></h1>

    <div class="card my-3">
        <div class="card-body">
            <form class="was-validated" action="${Path.SET_GLOBAL_RECEIPT_PROPERTIES}" method="post">

                <div class="mb-3">
                    <label for="inputOrganizationTaxIdNumber" class="form-label">
                        <fmt:message key="set_global_receipt_properties.organization.tax.id.number.text"/></label>
                    <input type="number" min="1" max="999999999999999999"
                           class="form-control is-invalid" id="inputOrganizationTaxIdNumber"
                           name="${Parameter.ORGANIZATION_TAX_ID_NUMBER}"
                           placeholder="<fmt:message key="set_global_receipt_properties.enter.organization.tax.id.number.text"/>"
                           value="${saved_organization_taxId_number == null ?
                           saved_global_receipt_properties.organizationTaxIdNumber : saved_organization_taxId_number}" required>
                </div>

                <div class="mb-3">
                    <label for="inputNameOrganization" class="form-label">
                        <fmt:message key="set_global_receipt_properties.name.organization.text"/></label>
                    <input type="text" class="form-control is-invalid" id="inputNameOrganization" name="${Parameter.NAME_ORGANIZATION}"
                           placeholder="<fmt:message key="set_global_receipt_properties.enter.name.organization.text"/>"
                           value='${saved_name_organization == null ?
                           saved_global_receipt_properties.nameOrganization : saved_name_organization}' required>
                </div>

                <div class="mb-3">
                    <label for="inputAddressTradePoint" class="form-label">
                        <fmt:message key="set_global_receipt_properties.address.trade.point.text"/></label>
                    <input type="text" class="form-control is-invalid" id="inputAddressTradePoint" name="${Parameter.ADDRESS_TRADE_POINT}"
                           placeholder="<fmt:message key="set_global_receipt_properties.enter.address.trade.point.text"/>"
                           value='${saved_address_trade_point == null ?
                           saved_global_receipt_properties.addressTradePoint : saved_address_trade_point}' required>
                </div>

                <div class="mb-3">
                    <label for="inputVat" class="form-label">
                        <fmt:message key="set_global_receipt_properties.vat.text"/></label>
                    <input type="number" min="0" step=".01" class="form-control is-invalid" id="inputVat" name="${Parameter.VAT}"
                           placeholder="<fmt:message key="set_global_receipt_properties.enter.vat.text"/>"
                           value="${saved_vat == null ? saved_global_receipt_properties.vat : saved_vat}" required>
                </div>

                <div class="mb-3">
                    <label for="inputTaxationSys" class="form-label">
                        <fmt:message key="set_global_receipt_properties.taxation.system.text"/></label>
                    <input type="text" class="form-control is-invalid" id="inputTaxationSys" name="${Parameter.TAXATION_SYS}"
                           placeholder="<fmt:message key="set_global_receipt_properties.enter.taxation.system.text"/>"
                           value="${saved_taxation_sys == null ?
                           saved_global_receipt_properties.taxationSys : saved_taxation_sys}" required>
                </div>

                <button type="submit" class="mt-2 btn btn-primary"><fmt:message key="set_global_receipt_properties.save.text"/></button>

                <a href="${Path.SET_GLOBAL_RECEIPT_PROPERTIES}" class="mt-2 btn btn-primary" role="button">
                    <fmt:message key="edit_product.reset.changes.text"/></a>

            </form>

            <a href="" class="resetLink btn btn-danger" data-toggle="modal" data-target="#resetModal">
                <fmt:message key="set_global_receipt_properties.reset.all.receipt.properties.text"/></a>
        </div>
    </div>

    <div class="container mt-2 mb-3">
        <div class="card">
            <div class="card-body">
                <%@ include file="/WEB-INF/view/jsp/template/error_messages.jsp" %>
            </div>
        </div>
    </div>

    <div class="p-2 mx-2">
        <a href="${Path.MAIN}" type="submit" class="mb-5 btn btn-primary float-right">
            <fmt:message key="set_global_receipt_properties.come.back.to.main.page.text"/>
        </a>
    </div>

</div>

<div class="modal fade" id="resetModal" tabindex="-1" role="dialog" aria-labelledby="resetModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="resetModalCenterTitle"><fmt:message key="set_global_receipt_properties.warning.text"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <%-- Here a help message (warning) on reseting global receipt properties --%>
                <h6 id="modalBody"></h6>
            </div>
            <div class="modal-footer">
                <form id="resetForm" action="" method="post">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        <fmt:message key="set_global_receipt_properties.no.text"/></button>
                    <button type="submit" class="btn btn-primary">
                        <fmt:message key="set_global_receipt_properties.yes.text"/></button>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

<script type="text/javascript">
    $(".resetLink").click(function () {
        var str = "<fmt:message key="set_global_receipt_properties.set.global.receipt.properties.help.text"/>";
        $("#modalBody").html(str);
        $("#resetForm").attr("action", "${Path.RESET_GLOBAL_RECEIPT_PROPERTIES}");
    });
</script>

</body>
</html>
