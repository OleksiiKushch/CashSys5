<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CashSys.product.catalog</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

</head>
<body class="d-flex flex-column min-vh-100">

    <%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

    <div class="container-fluid">
        <h1 class="mt-4">Product catalog:</h1>
        <table class="table table-striped border rounded">
            <thead class="thead-light">
            <tr>
                <th scope="col">id</th>
                <th scope="col">Name</th>
                <th scope="col">Amount</th>
                <th scope="col">Barcode</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${requestScope.product_catalog}">
                    <tr>
                        <th class="col-md-1" scope="row">${product.id}</th>
                        <td>${product.name}</td>
                        <td class="col-md-2">${product.getAmount()} ${product.unit.name}</td>
                        <td class="col-md-1">${product.barcode}</td>
                        <td class="col-md-1">
                            <a href="#?productId=${product.id}">edit</a>
                            &nbsp &nbsp
                            <a href="#?productId=${product.id}">delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>
