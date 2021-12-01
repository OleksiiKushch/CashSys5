<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CashSys.receipt.catalog</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/jquery.slim.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/bootstrap.bundle.min.js"></script>

</head>
<body class="d-flex flex-column min-vh-100">

    <%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

    <div class="container-fluid">
        <h1 class="mt-4">Receipt catalog:</h1>

        <nav>
            <ul class="pagination justify-content-end">
                <li class="page-item">
                    <form action="<%= request.getContextPath() %>/FrontController" method="get">
                        <input name="command" value="/receipt_catalog" type="hidden">
                        <label for="selectPage" class="form-label">Select page number:</label>
                        <select class="form-select" name="page" id="selectPage">
                            <c:forEach begin="1" end="${page_count}" var="p">
                                <option value="${p}" ${p == param.page ? 'selected' : ''}>${p}</option>
                            </c:forEach>
                        </select>
                        <input name="page_size" value="${page_size}" type="hidden">
                        <button type="submit" class="btn btn-primary btn-sm">Go</button>
                    </form>
                </li>
            </ul>
            <ul class="pagination justify-content-end">

                <c:choose>
                    <c:when test="${page - 1 > 0}">
                        <li class="page-item">
                            <a class="page-link"
                               href="<%= request.getContextPath() %>/FrontController?command=/receipt_catalog&page=${page-1}&page_size=${page_size}">
                                Previous</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1">Previous</a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <c:forEach var="p" begin="${min_possible_page}" end="${max_possible_page}">
                    <c:choose>
                        <c:when test="${page == p}">
                            <li class="page-item disabled">
                                <a class="page-link">Page ${p} of ${page_count}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link"
                                   href="<%= request.getContextPath() %>/FrontController?command=/receipt_catalog&page=${p}&page_size=${page_size}">
                                        ${p}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:choose>
                    <c:when test="${page + 1 <= page_count}">
                        <li class="page-item">
                            <a class="page-link"
                               href="<%= request.getContextPath() %>/FrontController?command=/receipt_catalog&page=${page+1}&page_size=${page_size}">
                                Next</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1">Next</a>
                        </li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </nav>

        <table class="table table-striped border rounded">
            <thead class="thead-light">
            <tr>
                <th scope="col">id</th>
                <th scope="col">(id) Cashier</th>
                <th scope="col">Date time</th>
                <th scope="col">Sum</th>
                <th scope="col">Change</th>
                <th scope="col">Type payment</th>
                <th scope="col">Status</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="receipt" items="${requestScope.paginate_receipts}">
                <tr>
                    <th class="col-md-1" scope="row">${receipt.id}</th>
                    <td>(${receipt.userId}) ${user_service.getFormattedNameById(receipt.userId)}</td>
                    <td class="col-md-2">${receipt.dateTime}</td>
                    <td class="col-md-1">${receipt_service.getSumReceiptById(receipt.id).setScale(2)}</td>
                    <td class="col-md-1">${receipt.change}</td>
                    <td class="col-md-1">${receipt.payment.name}</td>
                    <td class="col-md-1">${receipt.status.name}</td>
                    <td class="col-md-1">
                        <a href="${pageContext.request.contextPath}/FrontController?command=/see_receipt_details&receipt_id=${receipt.id}">
                            see details</a>
                        &nbsp &nbsp
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>
