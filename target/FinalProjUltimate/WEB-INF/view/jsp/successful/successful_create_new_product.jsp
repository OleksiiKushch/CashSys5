<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<%@ page import="com.finalprojultimate.util.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>CashSys.create.new.product.success</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>

<c:set var="logged_user" value="${sessionScope[Attribute.LOGGED_USER]}"/>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<roleSecurity:check role="${Role.COMMODITY_EXPERT.name}" loggedUserRole="${logged_user.role.name}" />

<div class="container">
    <div class="jumbotron mt-3">
        <h1><fmt:message key="successful_create_new_product.product.successfully.created.text"/></h1>
        <p class="lead"><fmt:message key="successful_create_new_product.product.successfully.help.text"/></p>
        <a class="btn btn-lg btn-primary" href="${Path.CREATE_NEW_PRODUCT}"
           role="button"><fmt:message key="successful_create_new_product.create.new.product.text"/></a>
        <a class="btn btn-lg btn-primary mx-3" href="${Path.PRODUCT_CATALOG}"
           role="button"><fmt:message key="successful_create_new_product.to.product.catalog.text"/></a>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

</body>
</html>