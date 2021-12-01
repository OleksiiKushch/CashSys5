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
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/new_receipt">
                    Create new Receipt</a></li>
            </ul>
        </c:if>

        <c:if test="${sessionScope.logged_user.role == Role.SENIOR_CASHIER}">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/set_global_receipt_properties">
                    Set global receipt properties</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/receipt_catalog&page=1&page_size=8">
                    Receipt catalog</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/generate_report">
                    Generate report</a></li>
            </ul>
        </c:if>

        <c:if test="${sessionScope.logged_user.role == Role.COMMODITY_EXPERT}">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/product_catalog&page=1&page_size=8">
                    Product catalog</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/FrontController?command=/create_new_product">
                    Create new Product</a></li>
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


