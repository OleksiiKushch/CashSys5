<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.finalprojultimate.util.Parameter" %>
<%@ page import="com.finalprojultimate.util.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>CashSys.my.profile</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>

<c:set var="logged_user" value="${sessionScope[Attribute.LOGGED_USER]}"/>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<div class="container">

    <h1 class="mt-4"><fmt:message key="my_profile.my.profile.text"/></h1>

    <div class="card my-3">
        <div class="card-body">

            <form>

                <div class="form-row">
                    <div class="col mb-3">
                        <label for="inputFirstName" class="form-label"><fmt:message key="my_profile.first.name.text"/></label>
                        <input type="text" class="form-control" id="inputFirstName" name="${Parameter.FIRST_NAME}"
                               value="${logged_user.firstName}" disabled>
                    </div>
                    <div class="col mb-3">
                        <label for="inputMiddleName" class="form-label"><fmt:message key="my_profile.middle.name.text"/></label>
                        <input type="text" class="form-control" id="inputMiddleName" name="${Parameter.MIDDLE_NAME}"
                               value="${logged_user.middleName}" disabled>
                    </div>
                    <div class="col mb-3">
                        <label for="inputLastName" class="form-label"><fmt:message key="my_profile.last.name.text"/></label>
                        <input type="text" class="form-control" id="inputLastName" name="${Parameter.LAST_NAME}"
                               value="${logged_user.lastName}" disabled>
                    </div>
                </div>

                <div class="col mb-3">
                    <label for="inputEmail" class="form-label"><fmt:message key="my_profile.email.address.text"/></label>
                    <input type="email" class="form-control" id="inputEmail" name="${Parameter.EMAIL}"
                           value="${logged_user.email}" disabled>
                </div>

                <div class="col mb-3">
                    <label for="inputRole" class="form-label"><fmt:message key="my_profile.role.text"/></label>
                    <input type="text" class="form-control" id="inputRole" name="${Parameter.ROLE}"
                           value="<fmt:message key="${logged_user.role.message}"/>" disabled>
                </div>


            </form>

        </div>
    </div>

    <div class="p-2 mx-2">
        <a href="${Path.MAIN}" type="submit" class="mb-5 btn btn-primary float-right">
            <fmt:message key="my_profile.come.back.to.main.page.text"/>
        </a>
    </div>

</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

</body>
</html>
