<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<%@ page import="com.finalprojultimate.util.Attribute" %>
<%@ page import="com.finalprojultimate.util.Parameter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="formatUserName" uri="http://com.finalprojultimate/model/view/tag/formatting/TagFormattedUserName" %>
<%@ taglib prefix="totalReceiptSum" uri="http://com.finalprojultimate/model/view/tag/supplying/TagTotalReceiptSum" %>

<html>
<head>
    <title>CashSys.receipt.catalog</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</head>

<c:set var="logged_user" value="${sessionScope[Attribute.LOGGED_USER]}"/>
<c:set var="page_count" value="${requestScope[Attribute.PAGE_COUNT]}"/>
<c:set var="page_size" value="${requestScope[Attribute.PAGE_SIZE]}"/>
<c:set var="page" value="${requestScope[Attribute.PAGE]}"/>
<c:set var="min_possible_page" value="${requestScope[Attribute.MIN_POSSIBLE_PAGE]}"/>
<c:set var="max_possible_page" value="${requestScope[Attribute.MAX_POSSIBLE_PAGE]}"/>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<roleSecurity:check role="${Role.SENIOR_CASHIER.name}" loggedUserRole="${logged_user.role.name}" />

<div class="container-fluid">
    <h1 class="mt-4"><fmt:message key="receipt_catalog.receipt.catalog.text"/></h1>

    <nav class="navbar">

        <ul class="pagination justify-content-end">
            <li class="page-item">
                <form action="${Path.APP_CONTEXT}${Path.CONTROLLER}" method="get">
                    <input name="${Path.COMMAND}" value="${Command.RECEIPT_CATALOG}" type="hidden">
                    <input name="${Attribute.PAGE}" value="${Path.DEFAULT_START_PAGE_NUMBER}" type="hidden">
                    <input name="${Attribute.PAGE_SIZE}" value="${Path.DEFAULT_SIZE_CATALOG_PAGE}" type="hidden">
                    <label for="selectSortParameter" class="form-label"><fmt:message key="receipt_catalog.sort.by.text"/></label>
                    <select id="selectSortParameter" name="${Attribute.RECEIPT_SORT_PARAM}">
                        <option value="${Parameter.NONE}" selected><fmt:message key="receipt_catalog.sort.by.none.text"/></option>
                        <option value="${Parameter.DATE_TIME}"><fmt:message key="receipt_catalog.sort.by.date.time.text"/></option>
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
                <form action="${Path.APP_CONTEXT}${Path.CONTROLLER}" method="get">
                    <input name="${Path.COMMAND}" value="${Command.RECEIPT_CATALOG}" type="hidden">
                    <label for="selectPage" class="form-label"><fmt:message key="receipt_catalog.select.page.number.text"/></label>
                    <select class="form-select" name="${Attribute.PAGE}" id="selectPage">
                        <c:forEach begin="1" end="${page_count}" var="p">
                            <option value="${p}" ${p == param.page ? 'selected' : ''}>${p}</option>
                        </c:forEach>
                    </select>
                    <input name="${Attribute.PAGE_SIZE}" value="${page_size}" type="hidden">
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
                <td>(${receipt.userId}) <formatUserName:get userId="${receipt.userId}" /></td>
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

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

</body>
</html>
