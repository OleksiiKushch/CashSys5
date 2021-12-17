<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>CashSys.main</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<div class="card  text-center w-75 m-auto">
    <div class="card-body">
        <div class="alert alert-primary" role="alert">
            <h4 class="alert-heading"><fmt:message key="main.alert.header.text"/></h4>
            <p><fmt:message key="main.alert.body.description.information.system.text"/></p>
            <hr><hr>
            <p><fmt:message key="main.alert.body.roles.information.system.text"/></p>
            <hr>
            <p><fmt:message key="main.alert.body.cashier.role.description.text"/></p>
            <hr>
            <p><fmt:message key="main.alert.body.senior.cashier.role.description.text"/></p>
            <hr>
            <p class="mb-0"><fmt:message key="main.alert.body.commodity.expert.role.description.text"/></p>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

</body>
</html>