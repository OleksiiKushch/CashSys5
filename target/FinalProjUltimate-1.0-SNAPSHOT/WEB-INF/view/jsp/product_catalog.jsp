<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CashSys.product.catalog</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/bootstrap.min.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/jquery.slim.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/js/bootstrap.bundle.min.js"></script>

</head>
<body class="d-flex flex-column min-vh-100">

    <%@ include file="/WEB-INF/view/jsp/template/index_header.jsp" %>

    <div class="container-fluid">
        <h1 class="mt-4">Product catalog:</h1>
        <table class="table table-striped border rounded">
            <thead class="thead-light">
            <tr>
                <th scope="col">id</th>
                <th scope="col">Name</th>
                <th scope="col">Amount</th>
                <th scope="col">Barcode</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${requestScope.product_catalog}">
                    <tr>
                        <th class="col-md-1" scope="row">${product.id}</th>
                        <td>${product.name}</td>
                        <td class="col-md-2">${product.getAmount()} ${product.unit.name}</td>
                        <td class="col-md-1">${product.barcode}</td>
                        <td class="col-md-1">
                            <a href="${pageContext.request.contextPath}/FrontController?command=/edit_product&product_id=${product.id}">
                                edit</a>
                            &nbsp &nbsp
                            <a href="" class="deleteLink" data-id="${product.id}" data-toggle="modal" data-target="#deleteModal">delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered"> <!-- role="document" -->
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalCenterTitle">Warning!</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
<%--                    Are you sure you want to delete product with id: ${product.id}?--%>
                    <h6 id="modalBody"></h6>
                </div>
                <div class="modal-footer">
                    <form id="deleteForm" action=""
                          method="post">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                        <button type="submit" class="btn btn-primary">Yes</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/view/jsp/template/index_footer.jsp" %>

    <script type="text/javascript">
        $(".deleteLink").click(function () {
            var id = $(this).attr("data-id");
            var str = "Are you sure you want to delete product with id: " + id;
            $("#modalBody").html(str);
            $("#deleteForm").attr("action", "<%= request.getContextPath() %>/FrontController?command=/delete_product&product_id=" + id);
        });
    </script>

</body>
</html>
