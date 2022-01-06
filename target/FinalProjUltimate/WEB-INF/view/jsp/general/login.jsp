<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.util.Attribute" %>
<%@ page import="com.finalprojultimate.util.Parameter" %>
<%@ page import="com.finalprojultimate.util.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>CashSys.login</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" >

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</head>

<c:set var="saved_email" value="${requestScope[Attribute.LOGIN_DATA].email}"/>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<div class="container">

    <h1 class="my-3"><fmt:message key="login.employee.login.form.text"/></h1>

    <div class="card">
        <div class="card-body">

            <form class="was-validated" action="${Path.LOGIN}" method="post">

                <div class="mb-3">
                    <label for="inputEmail" class="form-label"><fmt:message key="login.email.address.text"/></label>
                    <input type="email" class="form-control is-invalid" id="inputEmail" name="${Parameter.EMAIL}"
                    <c:if test="${saved_email != null}"> value="${saved_email}" </c:if>
                           placeholder="<fmt:message key="login.enter.email.address.text"/>" required>
                </div>

                <div class="mb-3">
                    <label for="inputPassword" class="form-label"><fmt:message key="login.password.text"/></label>
                    <div class="input-group" id="showHidePassword">
                        <input type="password" class="form-control is-valid" id="inputPassword" name="${Parameter.PASSWORD}"
                               placeholder="<fmt:message key="login.enter.password.text"/>"
                               minlength="4" maxlength="16" required>
                        <span class="input-group-append">
                            <button type="button" class="btn btn-secondary">
                                <i class="fa fa-eye-slash" aria-hidden="true"></i>
                            </button>
                        </span>
                    </div>
                </div>

                <button type="submit" class="mb-3 btn btn-primary"><fmt:message key="login.sign.in.text"/></button>

                <div class="mb-1">
                    <label for="forgotPassword" class="form-label"><fmt:message key="login.forgot.your.password.text"/></label>
                    <a href="#" class="fw-bold text-body" id="forgotPassword"><u><fmt:message key="login.reset.it.here.text"/></u></a>
                </div>

                <div class="mb-1">
                    <label for="noAccount" class="form-label"><fmt:message key="login.no.account.text"/></label>
                    <a href="${Path.REGISTRATION}" class="fw-bold text-body" id="noAccount">
                        <u><fmt:message key="login.registration.here.text"/></u>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/custom/show.hide.password.js"></script>

</body>
</html>