<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<%@ page import="com.finalprojultimate.util.Parameter" %>
<%@ page import="com.finalprojultimate.util.Path" %>

<html>
<head>
    <title>CashSys.registration</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<div class="container">

    <h1 class="mt-4"><fmt:message key="registration.employee.register.form.text"/></h1>

    <div class="card my-3">
        <div class="card-body">
            <form class="was-validated" action="${Path.REGISTRATION}" method="post">

                <div class="mb-3">
                    <label for="inputEmail" class="form-label"><fmt:message key="registration.email.address.text"/></label>
                    <input type="email" class="form-control is-invalid" id="inputEmail" name="${Parameter.EMAIL}"
                           placeholder="<fmt:message key="registration.enter.email.address.text"/>" required>
                </div>

                <div class="mb-3">
                    <label for="inputFirstName" class="form-label"><fmt:message key="registration.first.name.text"/></label>
                    <input type="text" class="form-control is-invalid" id="inputFirstName" name="${Parameter.FIRST_NAME}"
                           placeholder="<fmt:message key="registration.enter.first.name.text"/>" required>
                </div>

                <div class="mb-3">
                    <label for="inputMiddleName" class="form-label"><fmt:message key="registration.middle.name.text"/></label>
                    <input type="text" class="form-control is-valid" id="inputMiddleName" name="${Parameter.MIDDLE_NAME}"
                           placeholder="<fmt:message key="registration.enter.middle.name.text"/>" required>
                </div>

                <div class="mb-3">
                    <label for="inputLastName" class="form-label"><fmt:message key="registration.last.name.text"/></label>
                    <input type="text" class="form-control is-valid" id="inputLastName" name="${Parameter.LAST_NAME}"
                           placeholder="<fmt:message key="registration.enter.last.name.text"/>" required>
                </div>

                <div class="mb-3">
                    <label for="inputPassword" class="form-label"><fmt:message key="registration.password.text"/></label>
                    <input type="password" class="form-control is-valid" id="inputPassword" name="${Parameter.PASSWORD}"
                           placeholder="<fmt:message key="registration.enter.password.text"/>"
                           minlength="4" maxlength="16" required>
                </div>

                <div class="mb-3">
                    <label for="inputConfirmationPassword" class="form-label"><fmt:message key="registration.confirm.password.text"/></label>
                    <input type="password" class="form-control is-valid" id="inputConfirmationPassword" name="${Parameter.CONFIRMATION_PASSWORD}"
                           placeholder="<fmt:message key="registration.enter.password.again.text"/>"
                           minlength="4" maxlength="16" required>
                </div>

                <fieldset class="form-group">
                    <div class="row">
                        <label class="col-form-label col-sm-2 pt-0"><fmt:message key="registration.role.text"/></label>
                        <div class="col-sm-10">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="${Parameter.ROLE}" id="radioCashier"
                                       value="${Role.CASHIER.name}" checked>
                                <label class="form-check-label" for="radioCashier">
                                    <fmt:message key="registration.cashier.text"/>
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="${Parameter.ROLE}" id="radioSeniorCashier"
                                       value="${Role.SENIOR_CASHIER.name}">
                                <label class="form-check-label" for="radioSeniorCashier">
                                    <fmt:message key="registration.senior.cashier.text"/>
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="${Parameter.ROLE}" id="radioCommodityExpert"
                                       value="${Role.COMMODITY_EXPERT.name}">
                                <label class="form-check-label" for="radioCommodityExpert">
                                    <fmt:message key="registration.commodity.expert.text"/>
                                </label>
                            </div>
                        </div>
                    </div>
                </fieldset>

                <button type="submit" class="mb-3 btn btn-primary"><fmt:message key="registration.register.text"/></button>

                <div class="mb-1">
                    <label for="alreadyAccount" class="form-label"><fmt:message key="registration.have.already.an.account.text"/></label>
                    <a href="${Path.LOGIN}"
                       class="fw-bold text-body" id="alreadyAccount">
                        <u><fmt:message key="registration.login.here.text"/></u>
                    </a>
                </div>

            </form>

        </div>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

</body>
</html>
