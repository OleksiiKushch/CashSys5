<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>CashSys.user.catalog</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

<security:check role="senior cashier" loggedUserRole="${sessionScope.logged_user.role.name}" />

<div class="container-fluid">
    <h1 class="mt-4"><fmt:message key="user_catalog.user.catalog.text"/></h1>

    <nav class="navbar">

        <ul class="pagination justify-content-end">
            <li class="page-item">
                <form action="<%= request.getContextPath() %>/FrontController" method="get">
                    <input name="command" value="/user_catalog" type="hidden">
                    <input name="page" value="1" type="hidden">
                    <input name="page_size" value="8" type="hidden">
                    <label for="selectSortParameter" class="form-label"><fmt:message key="user_catalog.sort.by.text"/></label>
                    <select id="selectSortParameter" name="user_sort_param">
                        <option value="none" selected><fmt:message key="user_catalog.sort.by.none.text"/></option>
                        <option value="email"><fmt:message key="user_catalog.sort.by.email.text"/></option>
                    </select>
                    <button type="submit" class="btn btn-primary btn-sm"><fmt:message key="user_catalog.go.text"/></button>
                </form>
            </li>
        </ul>

<%--        <ul class="pagination justify-content-end">--%>
<%--            <li class="page-item">--%>
<%--                <form action="<%= request.getContextPath() %>/FrontController" method="get">--%>
<%--                    <input name="command" value="/user_catalog" type="hidden">--%>
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
                    <input name="command" value="/user_catalog" type="hidden">
                    <label for="selectPage" class="form-label"><fmt:message key="user_catalog.select.page.number.text"/></label>
                    <select class="form-select" name="page" id="selectPage">
                        <c:forEach begin="1" end="${page_count}" var="p">
                            <option value="${p}" ${p == param.page ? 'selected' : ''}>${p}</option>
                        </c:forEach>
                    </select>
                    <input name="page_size" value="${page_size}" type="hidden">
                    <button type="submit" class="btn btn-primary btn-sm"><fmt:message key="user_catalog.go.text"/></button>
                </form>
            </li>
        </ul>
        <ul class="pagination justify-content-end">

            <c:choose>
                <c:when test="${page - 1 > 0}">
                    <li class="page-item">
                        <a class="page-link"
                           href="<%= request.getContextPath() %>/FrontController?command=/user_catalog&page=${page-1}&page_size=${page_size}">
                            <fmt:message key="user_catalog.previous.text"/></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1"><fmt:message key="user_catalog.previous.text"/></a>
                    </li>
                </c:otherwise>
            </c:choose>

            <c:forEach var="p" begin="${min_possible_page}" end="${max_possible_page}">
                <c:choose>
                    <c:when test="${page == p}">
                        <li class="page-item disabled">
                            <a class="page-link"><fmt:message key="user_catalog.page.text"/> ${p}
                                <fmt:message key="user_catalog.of.text"/> ${page_count}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link"
                               href="<%= request.getContextPath() %>/FrontController?command=/user_catalog&page=${p}&page_size=${page_size}">
                                    ${p}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:choose>
                <c:when test="${page + 1 <= page_count}">
                    <li class="page-item">
                        <a class="page-link"
                           href="<%= request.getContextPath() %>/FrontController?command=/user_catalog&page=${page + 1}&page_size=${page_size}">
                            <fmt:message key="user_catalog.next.text"/></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1"><fmt:message key="user_catalog.next.text"/></a>
                    </li>
                </c:otherwise>
            </c:choose>

        </ul>
    </nav>

    <table class="table table-striped border rounded">
        <thead class="thead-light">
        <tr>
            <th scope="col"><fmt:message key="user_catalog.id.text"/></th>
            <th scope="col"><fmt:message key="user_catalog.email.address.text"/></th>
            <th scope="col"><fmt:message key="user_catalog.first.name.text"/></th>
            <th scope="col"><fmt:message key="user_catalog.middle.name.text"/></th>
            <th scope="col"><fmt:message key="user_catalog.last.name.text"/></th>
            <th scope="col"><fmt:message key="user_catalog.role.text"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${requestScope.paginate_users}">
            <tr>
                <th class="col-md-1" scope="row">${user.id}</th>
                <td>${user.email}</td>
                <td class="col-md-2">${user.firstName}</td>
                <td class="col-md-1">${user.middleName}</td>
                <td class="col-md-2">${user.lastName}</td>
                <td class="col-md-1"><fmt:message key="${user.role.message}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>
