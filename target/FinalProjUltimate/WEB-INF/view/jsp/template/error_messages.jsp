<%@ page import="com.finalprojultimate.util.Attribute" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt_err" uri="http://java.sun.com/jsp/jstl/fmt"  %>

<c:set var="error_messages" value="${requestScope[Attribute.ERROR_MESSAGES]}"/>
<c:set var="error_validation_messages" value="${requestScope[Attribute.ERROR_VALIDATION_MESSAGES]}"/>

<c:choose>
    <c:when test="${not empty error_messages}">
        <c:forEach var="message" items="${error_messages}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <a href="#" class="close" data-dismiss="alert" aria-label="Close">&times;</a>
                <strong><fmt_err:message key="error_messages.error.message"/></strong>
                <fmt_err:message key="${message}"/>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:if test="${not empty error_validation_messages}">
            <c:forEach var="message" items="${error_validation_messages}">
                <div class="alert alert-warning" role="alert">
                    <a href="#" class="close" data-dismiss="alert" aria-label="Close">&times;</a>
                    <strong><fmt_err:message key="error_messages.error.message"/></strong>
                    <fmt_err:message key="${message}"/>
                </div>
            </c:forEach>
        </c:if>
    </c:otherwise>
</c:choose>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/custom/close.error.alert.js"></script>
