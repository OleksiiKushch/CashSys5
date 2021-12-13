<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>CashSys.my.profile</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

<div class="container">

    <h1 class="mt-4"><fmt:message key="my_profile.my.profile.text"/></h1>

    <div class="card my-3">
        <div class="card-body">

            <form>

                <div class="form-row">
                    <div class="col mb-3">
                        <label for="inputFirstName" class="form-label"><fmt:message key="my_profile.first.name.text"/></label>
                        <input type="text" class="form-control" id="inputFirstName" name="firstName"
                               value="${sessionScope.logged_user.firstName}" disabled>
                    </div>
                    <div class="col mb-3">
                        <label for="inputMiddleName" class="form-label"><fmt:message key="my_profile.middle.name.text"/></label>
                        <input type="text" class="form-control" id="inputMiddleName" name="middleName"
                               value="${sessionScope.logged_user.middleName}" disabled>
                    </div>
                    <div class="col mb-3">
                        <label for="inputLastName" class="form-label"><fmt:message key="my_profile.last.name.text"/></label>
                        <input type="text" class="form-control" id="inputLastName" name="lastName"
                               value="${sessionScope.logged_user.lastName}" disabled>
                    </div>
                </div>

                <div class="col mb-3">
                    <label for="inputEmail" class="form-label"><fmt:message key="my_profile.email.address.text"/></label>
                    <input type="email" class="form-control" id="inputEmail" name="email"
                           value="${sessionScope.logged_user.email}" disabled>
                </div>

                <div class="col mb-3">
                    <label for="inputRole" class="form-label"><fmt:message key="my_profile.role.text"/></label>
                    <input type="text" class="form-control" id="inputRole" name="role"
                           value="${sessionScope.logged_user.role.getName()}" disabled>
                </div>


            </form>

        </div>
    </div>

    <div class="p-2 mx-2">
        <a href="${pageContext.request.contextPath}/FrontController?command=/main"
           type="submit" class="mb-5 btn btn-primary float-right">
            <fmt:message key="my_profile.come.back.to.main.page.text"/>
        </a>
    </div>

</div>

<%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

</body>
</html>
