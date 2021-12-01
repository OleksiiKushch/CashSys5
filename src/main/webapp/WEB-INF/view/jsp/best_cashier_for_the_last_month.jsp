<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CashSys.best.cashier.month</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

<div class="container">

    <div class="container-fluid">
        <h1 class="mt-4 mb-3">Report on the best cashiers:</h1>

        <table class="table table-striped border rounded">
            <thead class="thead-light">
            <tr>
                <th scope="col">id</th>
                <th scope="col">E-mail</th>
                <th scope="col">First name</th>
                <th scope="col">Middle name</th>
                <th scope="col">Last name</th>
                <th scope="col">Count</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cashier" items="${requestScope.users}" varStatus="status">
                <tr>
                    <th class="col-md-1" scope="row">${cashier.id}</th>
                    <td class="col-md-4">${cashier.email}</td>
                    <td class="col-md-2">${cashier.firstName}</td>
                    <td class="col-md-2">${cashier.middleName}</td>
                    <td class="col-md-2">${cashier.lastName}</td>
                    <td class="col-md-2">${requestScope.amount[status.index]}</td>
                    <td class="col-md-1">
                        <a href="${pageContext.request.contextPath}/FrontController?command=/">
                            send congrat</a>
                        &nbsp &nbsp
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="p-2 mx-2">
            <a href="${pageContext.request.contextPath}/FrontController?command=/generate_report"
               type="submit" class="btn btn-primary float-right">
                Come back to report generator
            </a>
        </div>

    </div>

</div>

</body>
</html>
