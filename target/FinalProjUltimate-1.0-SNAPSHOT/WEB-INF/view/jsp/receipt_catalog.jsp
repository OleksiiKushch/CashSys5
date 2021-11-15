<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CashSys.receipt.catalog</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

</head>
<body class="d-flex flex-column min-vh-100">

    <%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

    <div class="container-fluid">
        <h1 class="mt-4">Receipt catalog:</h1>
        <table class="table table-striped border rounded">
            <thead class="thead-light">
            <tr>
                <th scope="col">id</th>
                <th scope="col">Сashier</th>
                <th scope="col">Date time</th>
                <th scope="col">Type payment</th>
                <th scope="col">Status</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="receipt" items="${requestScope.receipt_catalog}">
                <tr>
                    <th class="col-md-1" scope="row">${receipt.id}</th>
                    <td>${receipt.userId}</td>
                    <td class="col-md-2">${receipt.dateTime}</td>
                    <td class="col-md-1">${receipt.payment.name}</td>
                    <td class="col-md-1">${receipt.status.name}</td>
                    <td class="col-md-2">
                        <a href="#?receiptId=${receipt.id}">see details</a>
                        &nbsp &nbsp
                        <a href="#?receiptId=${receipt.id}">edit</a>
                        &nbsp &nbsp
                        <a href="#?receiptId=${receipt.id}">delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>
