<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.finalprojultimate.util.Path" %>
<%@ page import="com.finalprojultimate.util.Attribute" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<header class="card-header text-dark bg-light">
    <nav class="navbar">
        <a class="navbar-brand" href="#">CashSys</a>
        <c:if test="${sessionScope.logged_user == null}">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/registration">Registration</a></li>
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/login">Login</a></li>
            </ul>
        </c:if>

        <c:if test="${sessionScope.logged_user.role == Role.CASHIER}">
            <ul class="nav navbar-nav">
                <li><a href="">New receipt</a></li>
            </ul>
        </c:if>

        <c:if test="${sessionScope.logged_user.role == Role.SENIOR_CASHIER}">
            <ul class="nav navbar-nav">
                <li><a href="">Receipt catalog</a></li>
            </ul>
        </c:if>

        <c:if test="${sessionScope.logged_user.role == Role.COMMODITY_EXPERT}">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/product_catalog">Product catalog</a></li>
            </ul>
        </c:if>

        <c:if test="${sessionScope.logged_user != null}">
            <ul class="nav navbar-nav">
                <li><a href="">My profile</a></li>
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/logout">Logout</a></li>
            </ul>
        </c:if>
    </nav>
</header>


