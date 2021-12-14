<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="formattedUserName" uri="http://com.finalprojultimate/model/tag/TagFormattedUserName" %>
<%@ taglib prefix="totalReceiptSum" uri="http://com.finalprojultimate/model/tag/TagTotalReceiptSum" %>

<html>
<head>
    <title>CashSys.receipt.catalog</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/jquery.slim.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/bootstrap.bundle.min.js"></script>

</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

<security:check role="senior cashier" loggedUserRole="${sessionScope.logged_user.role.name}" />

<div class="container-fluid">
    <h1 class="mt-4"><fmt:message key="receipt_catalog.receipt.catalog.text"/></h1>

    <nav class="navbar">

        <ul class="pagination justify-content-end">
            <li class="page-item">
                <form action="<%= request.getContextPath() %>/FrontController" method="get">
                    <input name="command" value="/receipt_catalog" type="hidden">
                    <input name="page" value="1" type="hidden">
                    <input name="page_size" value="8" type="hidden">
                    <label for="selectSortParameter" class="form-label"><fmt:message key="receipt_catalog.sort.by.text"/></label>
                    <select id="selectSortParameter" name="receipt_sort_param">
                        <option value="none" selected><fmt:message key="receipt_catalog.sort.by.none.text"/></option>
                        <option value="dateTime"><fmt:message key="receipt_catalog.sort.by.date.time.text"/></option>
                    </select>
                    <button type="submit" class="btn btn-primary btn-sm"><fmt:message key="receipt_catalog.go.text"/></button>
                </form>
            </li>
        </ul>

<%--        <ul class="pagination justify-content-end">--%>
<%--            <li class="page-item">--%>
<%--                <form action="<%= request.getContextPath() %>/FrontController" method="get">--%>
<%--                    <input name="command" value="/receipt_catalog" type="hidden">--%>
<%--                    <label for="setSizePage" class="form-label">Set size page:</label>--%>
<%--                    <select class="form-select" name="page_size" id="setSizePage">--%>
<%--                        <option value="6">6</option>--%>
<%--                        <option value="8" selected>8</option>--%>
<%--                        <option value="12">12</option>--%>
<%--                        <option value="20">20</option>--%>
<%--                    </select>--%>
<%--                    <button type="submit" class="btn btn-primary btn-sm">Go</button>--%>
<%--                </form>--%>
<%--            </li>--%>
<%--        </ul>--%>

        <ul class="pagination justify-content-end">
            <li class="page-item">
                <form action="<%= request.getContextPath() %>/FrontController" method="get">
                    <input name="command" value="/receipt_catalog" type="hidden">
                    <label for="selectPage" class="form-label"><fmt:message key="receipt_catalog.select.page.number.text"/></label>
                    <select class="form-select" name="page" id="selectPage">
                        <c:forEach begin="1" end="${page_count}" var="p">
                            <option value="${p}" ${p == param.page ? 'selected' : ''}>${p}</option>
                        </c:forEach>
                    </select>
                    <input name="page_size" value="${page_size}" type="hidden">
                    <button type="submit" class="btn btn-primary btn-sm"><fmt:message key="receipt_catalog.go.text"/></button>
                </form>
            </li>
        </ul>
        <ul class="pagination justify-content-end">

            <c:choose>
                <c:when test="${page - 1 > 0}">
                    <li class="page-item">
                        <a class="page-link"
                           href="<%= request.getContextPath() %>/FrontController?command=/receipt_catalog&page=${page - 1}&page_size=${page_size}">
                            <fmt:message key="receipt_catalog.previous.text"/></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1"><fmt:message key="receipt_catalog.previous.text"/></a>
                    </li>
                </c:otherwise>
            </c:choose>

            <c:forEach var="p" begin="${min_possible_page}" end="${max_possible_page}">
                <c:choose>
                    <c:when test="${page == p}">
                        <li class="page-item disabled">
                            <a class="page-link"><fmt:message key="receipt_catalog.page.text"/> ${p} <fmt:message key="receipt_catalog.of.text"/> ${page_count}</a>
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
                            <fmt:message key="receipt_catalog.next.text"/></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1"><fmt:message key="receipt_catalog.next.text"/></a>
                    </li>
                </c:otherwise>
            </c:choose>

        </ul>
    </nav>

    <table class="table table-striped border rounded">
        <thead class="thead-light">
        <tr>
            <th scope="col"><fmt:message key="receipt_catalog.id.text"/></th>
            <th scope="col"><fmt:message key="receipt_catalog.id.cashier.text"/></th>
            <th scope="col"><fmt:message key="receipt_catalog.date.time.text"/></th>
            <th scope="col"><fmt:message key="receipt_catalog.sum.text"/></th>
            <th scope="col"><fmt:message key="receipt_catalog.change.text"/></th>
            <th scope="col"><fmt:message key="receipt_catalog.type.payment.text"/></th>
            <th scope="col"><fmt:message key="receipt_catalog.status.text"/></th>
            <th scope="col"><fmt:message key="receipt_catalog.action.text"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="receipt" items="${requestScope.paginate_receipts}">
            <tr>
                <th class="col-md-1" scope="row">${receipt.id}</th>
                <td>(${receipt.userId}) <formattedUserName:get userId="${receipt.userId}" /></td>
                <td class="col-md-2">${receipt.dateTime}</td>
                <td class="col-md-1"><totalReceiptSum:get receiptId="${receipt.id}" /></td>
                <td class="col-md-1">${receipt.change}</td>
                <td class="col-md-1"><fmt:message key="${receipt.payment.message}"/></td>
                <td class="col-md-1"><fmt:message key="${receipt.status.message}"/></td>
                <td class="col-md-1">
                    <a href="${pageContext.request.contextPath}/FrontController?command=/see_receipt_details&receiptId=${receipt.id}">
                        <fmt:message key="receipt_catalog.see.details.text"/></a>
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
