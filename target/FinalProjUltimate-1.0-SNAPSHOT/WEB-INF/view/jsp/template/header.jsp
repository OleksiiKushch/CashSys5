<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" >

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.model.entity.user.Role" %>
<%@ page import="com.finalprojultimate.util.Attribute" %>
<%@ page import="com.finalprojultimate.util.Path" %>
<%@ page import="com.finalprojultimate.util.Command" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://com.finalprojultimate/model/tag/TagSecurity" %>

<%-- set the locale --%>
<fmt:setLocale value="${sessionScope[Attribute.LOCALE]}"/>
<%-- load the bundle (by locale) --%>
<fmt:setBundle basename="i18n.messages"/>
<%-- set current locale to session --%>
<c:set var="currentLocale" value="${sessionScope[Attribute.LOCALE]}" />

<c:set var="loggedUser" value="${sessionScope[Attribute.LOGGED_USER]}" />

<header class="card-header text-dark bg-light">
    <nav class="navbar">
        <a class="navbar-brand" href="${Path.MAIN}">CashSys</a>

        <c:if test="${loggedUser.role == Role.CASHIER}">
            <ul class="nav navbar-nav">
                <li><a href="${Path.NEW_RECEIPT}">
                    <fmt:message key="header.create.new.receipt.text"/></a></li>
            </ul>
        </c:if>

        <c:if test="${loggedUser.role == Role.SENIOR_CASHIER}">
            <ul class="nav navbar-nav">
                <li><a href="${Path.SET_GLOBAL_RECEIPT_PROPERTIES}">
                    <fmt:message key="header.set.global.receipt.properties.text"/></a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="${Path.RECEIPT_CATALOG}">
                    <fmt:message key="header.receipt.catalog.text"/></a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="${Path.USER_CATALOG}">
                    <fmt:message key="header.user.catalog.text"/></a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="${Path.GENERATE_REPORT}">
                    <fmt:message key="header.generate.report.text"/></a></li>
            </ul>
        </c:if>

        <c:if test="${loggedUser.role == Role.COMMODITY_EXPERT}">
            <ul class="nav navbar-nav">
                <li><a href="${Path.PRODUCT_CATALOG}">
                    <fmt:message key="header.product.catalog.text"/></a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="${Path.CREATE_NEW_PRODUCT}">
                    <fmt:message key="header.create.new.product.text"/></a></li>
            </ul>
        </c:if>

        <ul class="nav navbar-nav">
            <li>
                <form action="${Path.APP_CONTEXT}${Path.CONTROLLER}" method="get" class="text-center">
                    <input name="${Path.COMMAND}" value="${Command.CHANGE_LOCAL}" type="hidden">
                    <label for="selectLocale" class="form-label"><fmt:message key="header.select.locale"/></label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <button type="submit" class="btn btn-secondary">
                                <i class="fa fa-language" aria-hidden="true"></i>
<%--                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-translate" viewBox="0 0 16 16">--%>
<%--                                    <path d="M4.545 6.714 4.11 8H3l1.862-5h1.284L8 8H6.833l-.435-1.286H4.545zm1.634-.736L5.5 3.956h-.049l-.679 2.022H6.18z"></path>--%>
<%--                                    <path d="M0 2a2 2 0 0 1 2-2h7a2 2 0 0 1 2 2v3h3a2 2 0 0 1 2 2v7a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2v-3H2a2 2 0 0 1-2-2V2zm2-1a1 1 0 0 0-1--%>
<%--                                1v7a1 1 0 0 0 1 1h7a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H2zm7.138--%>
<%--                                9.995c.193.301.402.583.63.846-.748.575-1.673 1.001-2.768 1.292.178.217.451.635.555.867--%>
<%--                                1.125-.359 2.08-.844 2.886-1.494.777.665 1.739 1.165 2.93 1.472.133-.254.414-.673.629-.89-1.125-.253-2.057-.694-2.82-1.284.681-.747--%>
<%--                                1.222-1.651 1.621-2.757H14V8h-3v1.047h.765c-.318.844-.74 1.546-1.272 2.13a6.066 6.066 0 0 1-.415-.492 1.988 1.988 0 0 1-.94.31z"></path>--%>
<%--                                </svg>--%>
                            </button>
                        </div>
                        <select id="selectLocale" name="${Attribute.LOCALE}" class="form-control" style="width:auto;">
                            <c:forEach var="locale" items="${applicationScope.locales}">
                                <c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
                                <option value="${locale.key}" ${selected}>${locale.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </li>
        </ul>


        <c:if test="${loggedUser != null}">
            <ul class="nav navbar-nav">
                <li><a href="${Path.MY_PROFILE}">
                    <fmt:message key="header.my.profile.text"/></a></li>
                <li><a href="" class="logoutLink" data-toggle="modal" data-target="#logoutModal">
                    <fmt:message key="header.logout.text"/></a></li>
            </ul>
        </c:if>

        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="logoutModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="logoutModalCenterTitle"><fmt:message key="header.logout.warning.text"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <%-- Here a help message (warning) on logout --%>
                        <h6 id="modalBodyWarningMessageLogout"></h6>
                    </div>
                    <div class="modal-footer">
                        <form id="logoutForm" action="${Path.APP_CONTEXT}${Path.CONTROLLER}" method="get">
                            <input name="${Path.COMMAND}" value="${Command.LOGOUT}" type="hidden">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="header.no.text"/></button>
                            <button type="submit" class="btn btn-primary"><fmt:message key="header.yes.text"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <c:if test="${loggedUser == null}">
            <ul class="nav navbar-nav">
                <li><a href="${Path.REGISTRATION}">
                    <fmt:message key="header.registration.text"/></a></li>
                <li><a href="${Path.LOGIN}">
                    <fmt:message key="header.login.text"/></a></li>
            </ul>
        </c:if>

    </nav>
</header>

<script type="text/javascript">
    $(".logoutLink").click(function () {
        var str = "<fmt:message key="header.logout.warning.help.text"/>";
        $("#modalBodyWarningMessageLogout").html(str);
    });
</script>


