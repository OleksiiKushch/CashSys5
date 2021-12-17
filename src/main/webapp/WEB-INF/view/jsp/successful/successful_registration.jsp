<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.util.Path" %>

<html>
<head>
    <title>CashSys.registration.success</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<div class="container">
    <div class="jumbotron mt-3">
        <h1><fmt:message key="successful_registration.employee.successful.registered.text"/></h1>
        <p class="lead"><fmt:message key="successful_registration.employee.successful.registered.help.text"/></p>
        <a class="btn btn-lg btn-primary" href="${Path.LOGIN}"
           role="button"><fmt:message key="successful_registration.employee.sign.in.text"/></a>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

</body>
</html>
