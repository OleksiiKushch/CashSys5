<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>CashSys</title>

    <link rel="stylesheet" href="resources/style/css/bootstrap.min.css">

</head>
<body>

<header class="card-header text-dark bg-light">
    <nav class="navbar">
        <a class="navbar-brand" href="#">CashSys</a>
    </nav>
</header>

<div class="container">
    <h1 class="my-3">Employee Login Form:</h1>
    <div class="card">
        <div class="card-body">
            <form class="was-validated" action="<%= request.getContextPath() %>/login" method="post">

                <div class="mb-3">
                    <label for="inputEmail" class="form-label">E-mail address:</label>
                    <input type="email" class="form-control is-invalid" id="inputEmail" name="email"
                           placeholder="Enter E-mail address" required>
                </div>

                <div class="mb-3">
                    <label for="inputPassword" class="form-label">Password:</label>
                    <input type="password" class="form-control" id="inputPassword" name="password"
                           placeholder="Enter password" required>
                </div>

                <button type="submit" class="mb-5 btn btn-primary">Sign in</button>

                <div class="mb-1">
                    <label for="forgotPassword" class="form-label">Forgot your password?</label>
                    <a href="#" class="fw-bold text-body" id="forgotPassword"><u>Reset it here</u></a>
                </div>

                <div class="mb-1">
                    <label for="noAccount" class="form-label">No account?</label>
                    <a href="registration" class="fw-bold text-body" id="noAccount"><u>Registration here</u></a>
                </div>

            </form>
        </div>
    </div>
</div>

<footer class="card-footer my-5 pt-5 text-dark text-center text-small bg-light">
    <p class="mb-1">© 2021-2022 CashSys</p>
    <ul class="list-inline">
        <li class="list-inline-item"><a href="#">Privacy</a></li>
        <li class="list-inline-item"><a href="#">Terms</a></li>
        <li class="list-inline-item"><a href="#">Support</a></li>
    </ul>
</footer>

</body>
</html>