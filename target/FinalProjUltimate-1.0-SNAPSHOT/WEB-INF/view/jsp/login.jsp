<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>CashSys.login</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

<div class="container">
    <h1 class="my-3">Employee Login Form:</h1>
    <div class="card">
        <div class="card-body">
            <form class="was-validated" action="<%= request.getContextPath() %>/FrontController?command=/login"
                  method="post">

                <div class="mb-3">
                    <label for="inputEmail" class="form-label">E-mail address:</label>
                    <input type="email" class="form-control is-invalid" id="inputEmail" name="email"
                           placeholder="Enter E-mail address" required>
                </div>

                <div class="mb-3">
                    <label for="inputPassword" class="form-label">Password:</label>
                    <input type="password" class="form-control is-valid" id="inputPassword" name="password"
                           placeholder="Enter password"
                           minlength="4" maxlength="16" required>
                </div>

                <button type="submit" class="mb-3 btn btn-primary">Sign in</button>

                <div class="mb-1">
                    <label for="forgotPassword" class="form-label">Forgot your password?</label>
                    <a href="#" class="fw-bold text-body" id="forgotPassword"><u>Reset it here</u></a>
                </div>

                <div class="mb-1">
                    <label for="noAccount" class="form-label">No account?</label>
                    <a href="${pageContext.request.contextPath}/FrontController?command=/registration"
                       class="fw-bold text-body" id="noAccount">
                        <u>Registration here</u>
                    </a>
                </div>

            </form>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>