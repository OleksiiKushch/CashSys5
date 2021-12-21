<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<%@ page import="com.finalprojultimate.util.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>CashSys.set.global.receipt.properties.success</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>

<c:set var="logged_user" value="${sessionScope[Attribute.LOGGED_USER]}"/>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<roleSecurity:check role="${Role.SENIOR_CASHIER.name}" loggedUserRole="${logged_user.role.name}" />

<div class="container">
    <div class="jumbotron mt-3">
        <h1><fmt:message key="successful_set_global_receipt_properties.global.receipt.properties.successfully.set.update.text"/></h1>
        <p class="lead"></p>
        <a class="btn btn-lg btn-primary" href="${Path.SET_GLOBAL_RECEIPT_PROPERTIES}"
           role="button"><fmt:message key="successful_set_global_receipt_properties.to.set.global.receipt.properties.page.text"/></a>
        <a class="btn btn-lg btn-primary" href="${Path.MAIN}"
           role="button"><fmt:message key="successful_set_global_receipt_properties.to.main.page.text"/></a>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

</body>
</html>
