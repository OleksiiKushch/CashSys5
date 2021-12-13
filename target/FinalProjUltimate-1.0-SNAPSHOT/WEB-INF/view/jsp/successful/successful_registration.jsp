<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>CashSys.reg.success</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

</head>
<body class="d-flex flex-column min-vh-100">

    <%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

    <div class="container">
        <div class="jumbotron mt-3">
            <h1><fmt:message key="successful_registration.employee.successful.registered.text"/></h1>
            <p class="lead"><fmt:message key="successful_registration.employee.successful.registered.help.text"/></p>
            <a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/FrontController?command=/login"
               role="button"><fmt:message key="successful_registration.employee.sign.in.text"/></a>
        </div>
    </div>

    <%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>
