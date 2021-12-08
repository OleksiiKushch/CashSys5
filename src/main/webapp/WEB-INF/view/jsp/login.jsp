<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>CashSys.login</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body class="d-flex flex-column min-vh-100">

    <%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

    <div class="container">
        <h1 class="my-3"><fmt:message key="login.employee.login.form.text"/></h1>
        <div class="card">
            <div class="card-body">
                <form class="was-validated" action="<%= request.getContextPath() %>/FrontController?command=/login"
                      method="post">

                    <div class="mb-3">
                        <label for="inputEmail" class="form-label"><fmt:message key="login.email.address.text"/></label>
                        <input type="email" class="form-control is-invalid" id="inputEmail" name="email"
                               placeholder="<fmt:message key="login.enter.email.address.text"/>" required>
                    </div>

                    <div class="mb-3">
                        <label for="inputPassword" class="form-label"><fmt:message key="login.password.text"/></label>
                        <input type="password" class="form-control is-valid" id="inputPassword" name="password"
                               placeholder="<fmt:message key="login.enter.password.text"/>"
                               minlength="4" maxlength="16" required>
                    </div>

                    <button type="submit" class="mb-3 btn btn-primary"><fmt:message key="login.sign.in.text"/></button>

                    <div class="mb-1">
                        <label for="forgotPassword" class="form-label"><fmt:message key="login.forgot.your.password.text"/></label>
                        <a href="#" class="fw-bold text-body" id="forgotPassword"><u><fmt:message key="login.reset.it.here.text"/></u></a>
                    </div>

                    <div class="mb-1">
                        <label for="noAccount" class="form-label"><fmt:message key="login.no.account.text"/></label>
                        <a href="${pageContext.request.contextPath}/FrontController?command=/registration"
                           class="fw-bold text-body" id="noAccount">
                            <u><fmt:message key="login.registration.here.text"/></u>
                        </a>
                    </div>

                </form>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>