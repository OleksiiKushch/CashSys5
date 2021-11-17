<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>CashSys.registration</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

</head>
<body class="d-flex flex-column min-vh-100">

    <%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

    <div class="container">

        <h1 class="mt-4">Employee register form:</h1>

        <div class="card my-3">
            <div class="card-body">
                <form class="was-validated" action="<%= request.getContextPath() %>/FrontController?command=/registration"
                      method="post">

                    <div class="mb-3">
                        <label for="inputEmail" class="form-label">E-mail address:</label>
                        <input type="email" class="form-control is-invalid" id="inputEmail" name="email"
                               placeholder="Enter E-mail address" required>
                    </div>

                    <div class="mb-3">
                        <label for="inputFirstName" class="form-label">First name:</label>
                        <input type="text" class="form-control is-invalid" id="inputFirstName" name="firstName"
                               placeholder="Enter first name" required>
                    </div>

                    <div class="mb-3">
                        <label for="inputMiddleName" class="form-label">Middle name:</label>
                        <input type="text" class="form-control" id="inputMiddleName" name="middleName"
                               placeholder="Enter middle name" required>
                    </div>

                    <div class="mb-3">
                        <label for="inputLastName" class="form-label">Last name:</label>
                        <input type="text" class="form-control" id="inputLastName" name="lastName"
                               placeholder="Enter last name" required>
                    </div>

                    <div class="mb-3">
                        <label for="inputPassword" class="form-label">Password:</label>
                        <input type="password" class="form-control" id="inputPassword" name="password"
                               placeholder="Enter password"
                               minlength="4" maxlength="16" required>
                    </div>

                    <div class="mb-3">
                        <label for="inputConfirmationPassword" class="form-label">Confirm password:</label>
                        <input type="password" class="form-control" id="inputConfirmationPassword" name="confirmationPassword"
                               placeholder="Enter password (again)"
                               minlength="4" maxlength="16" required>
                    </div>

                    <fieldset class="form-group">
                        <div class="row">
                            <label class="col-form-label col-sm-2 pt-0">Role:</label>
                            <div class="col-sm-10">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="role" id="radio1"
                                           value="cashier" checked>
                                    <label class="form-check-label" for="radio1">
                                        cashier
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="role" id="radio2"
                                           value="senior cashier">
                                    <label class="form-check-label" for="radio2">
                                        senior cashier
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="role" id="radio3"
                                           value="commodity expert">
                                    <label class="form-check-label" for="radio3">
                                        commodity expert
                                    </label>
                                </div>
                            </div>
                        </div>
                    </fieldset>

                    <button type="submit" class="mb-3 btn btn-primary">Register</button>

                    <div class="mb-1">
                        <label for="alreadyAccount" class="form-label">Have already an account?</label>
                        <a href="${pageContext.request.contextPath}/FrontController?command=/login"
                           class="fw-bold text-body" id="alreadyAccount">
                            <u>Login here</u>
                        </a>
                    </div>

                </form>

            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>