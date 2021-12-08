<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.finalprojultimate.util.Path" %>
<%@ page import="com.finalprojultimate.util.Attribute" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>

<%-- set the locale --%>
<fmt:setLocale value="${locale}" scope="session"/>
<%--<fmt:setLocale value="en" />--%>

<%-- load the bundle (by locale) --%>
<fmt:setBundle basename="i18n.messages"/>

<%-- set current locale to session --%>
<c:set var="currentLocale" value="${locale}" scope="session"/>

<header class="card-header text-dark bg-light">
    <nav class="navbar">
        <a class="navbar-brand" href="#">CashSys</a>

        <c:if test="${sessionScope.logged_user.role == Role.CASHIER}">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/new_receipt">
                    <fmt:message key="header.create.new.receipt.text"/></a></li>
            </ul>
        </c:if>

        <c:if test="${sessionScope.logged_user.role == Role.SENIOR_CASHIER}">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/set_global_receipt_properties">
                    <fmt:message key="header.set.global.receipt.properties.text"/></a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/receipt_catalog&page=1&page_size=8">
                    <fmt:message key="header.receipt.catalog.text"/></a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/generate_report">
                    <fmt:message key="header.generate.report.text"/></a></li>
            </ul>
        </c:if>

        <c:if test="${sessionScope.logged_user.role == Role.COMMODITY_EXPERT}">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/product_catalog&page=1&page_size=8">
                    <fmt:message key="header.product.catalog.text"/></a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/create_new_product">
                    <fmt:message key="header.create.new.product.text"/></a></li>
            </ul>
        </c:if>

        <ul class="nav navbar-nav">
            <li>
                <form action="<%= request.getContextPath() %>/FrontController" method="get">
                    <input name="command" value="/change_local" type="hidden">
                    <div class="row">
                        <button type="submit" class="btn btn-secondary">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-translate" viewBox="0 0 16 16">
                                <path d="M4.545 6.714 4.11 8H3l1.862-5h1.284L8 8H6.833l-.435-1.286H4.545zm1.634-.736L5.5 3.956h-.049l-.679 2.022H6.18z"></path>
                                <path d="M0 2a2 2 0 0 1 2-2h7a2 2 0 0 1 2 2v3h3a2 2 0 0 1 2 2v7a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2v-3H2a2 2 0 0 1-2-2V2zm2-1a1 1 0 0 0-1
                            1v7a1 1 0 0 0 1 1h7a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H2zm7.138
                            9.995c.193.301.402.583.63.846-.748.575-1.673 1.001-2.768 1.292.178.217.451.635.555.867
                            1.125-.359 2.08-.844 2.886-1.494.777.665 1.739 1.165 2.93 1.472.133-.254.414-.673.629-.89-1.125-.253-2.057-.694-2.82-1.284.681-.747
                            1.222-1.651 1.621-2.757H14V8h-3v1.047h.765c-.318.844-.74 1.546-1.272 2.13a6.066 6.066 0 0 1-.415-.492 1.988 1.988 0 0 1-.94.31z"></path>
                            </svg>
                        </button>
                        <select name="locale" class="form-control" style="width:auto;">
                            <c:forEach items="${applicationScope.locales}" var="locale">
                                <c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
                                <option value="${locale.key}" ${selected}>${locale.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </li>
        </ul>


        <c:if test="${sessionScope.logged_user != null}">
            <ul class="nav navbar-nav">
                <li><a href=""><fmt:message key="header.my.profile.text"/></a></li>
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/logout">
                    <fmt:message key="header.logout.text"/></a></li>
            </ul>
        </c:if>

        <c:if test="${sessionScope.logged_user == null}">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/registration">
                    <fmt:message key="header.registration.text"/></a></li>
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/login">
                    <fmt:message key="header.login.text"/></a></li>
            </ul>
        </c:if>

    </nav>
</header>


