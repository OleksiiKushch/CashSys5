<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<%@ page import="com.finalprojultimate.util.Parameter" %>
<%@ page import="com.finalprojultimate.util.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>CashSys.registration</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</head>

<c:set var="saved_email" value="${requestScope[Attribute.REGISTRATION_DATA].email}"/>
<c:set var="saved_first_name" value="${requestScope[Attribute.REGISTRATION_DATA].firstName}"/>
<c:set var="saved_middle_name" value="${requestScope[Attribute.REGISTRATION_DATA].middleName}"/>
<c:set var="saved_last_name" value="${requestScope[Attribute.REGISTRATION_DATA].lastName}"/>
<c:set var="saved_role" value="${requestScope[Attribute.REGISTRATION_DATA].role}"/>

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
                    <c:if test="${saved_email != null}"> value="${saved_email}" </c:if>
                           placeholder="<fmt:message key="registration.enter.email.address.text"/>" required>
                </div>

                <div class="mb-3">
                    <label for="inputFirstName" class="form-label"><fmt:message key="registration.first.name.text"/></label>
                    <input type="text" class="form-control is-invalid" id="inputFirstName" name="${Parameter.FIRST_NAME}"
                    <c:if test="${saved_first_name != null}"> value="${saved_first_name}" </c:if>
                           placeholder="<fmt:message key="registration.enter.first.name.text"/>"
                           maxlength="45" required>
                </div>

                <div class="mb-3">
                    <label for="inputMiddleName" class="form-label"><fmt:message key="registration.middle.name.text"/></label>
                    <input type="text" class="form-control is-valid" id="inputMiddleName" name="${Parameter.MIDDLE_NAME}"
                    <c:if test="${saved_middle_name != null}"> value="${saved_middle_name}" </c:if>
                           placeholder="<fmt:message key="registration.enter.middle.name.text"/>"
                           maxlength="45" required>
                </div>

                <div class="mb-3">
                    <label for="inputLastName" class="form-label"><fmt:message key="registration.last.name.text"/></label>
                    <input type="text" class="form-control is-valid" id="inputLastName" name="${Parameter.LAST_NAME}"
                    <c:if test="${saved_last_name != null}"> value="${saved_last_name}" </c:if>
                           placeholder="<fmt:message key="registration.enter.last.name.text"/>"
                           maxlength="45" required>
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

                <fieldset class="form-group required">
                    <div class="row">
                        <label class="col-form-label col-sm-2 pt-0"><fmt:message key="registration.role.text"/></label>
                        <div class="col-sm-10">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="${Parameter.ROLE}" id="radioCashier"
                                       value="${Role.CASHIER.name}"
                                <c:if test="${saved_role != null && saved_role == Role.CASHIER}"> checked </c:if> required>
                                <label class="form-check-label" for="radioCashier">
                                    <fmt:message key="registration.cashier.text"/>
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="${Parameter.ROLE}" id="radioSeniorCashier"
                                       value="${Role.SENIOR_CASHIER.name}"
                                <c:if test="${saved_role != null && saved_role == Role.SENIOR_CASHIER}"> checked </c:if>>
                                <label class="form-check-label" for="radioSeniorCashier">
                                    <fmt:message key="registration.senior.cashier.text"/>
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="${Parameter.ROLE}" id="radioCommodityExpert"
                                       value="${Role.COMMODITY_EXPERT.name}"
                                <c:if test="${saved_role != null && saved_role == Role.COMMODITY_EXPERT}"> checked </c:if>>
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

<div class="container mt-2 mb-5">
    <div class="card">
        <div class="card-body">
            <%@ include file="/WEB-INF/view/jsp/template/error_messages.jsp" %>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

</body>
</html>
