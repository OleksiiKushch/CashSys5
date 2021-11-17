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

        <nav>
<%--            <ul class="pagination justify-content-end">--%>
<%--                <li class="page-item">--%>
<%--                    <form action="<%= request.getContextPath() %>/FrontController" method="get">--%>
<%--                        <input name="command" value="/product_catalog" type="hidden">--%>
<%--                        <label for="selectPage" class="form-label">Select page number:</label>--%>
<%--                        <select class="form-select" name="page" id="selectPage">--%>
<%--                            <c:forEach begin="1" end="${page_count}" var="p">--%>
<%--                                <option value="${p}" ${p == param.page ? 'selected' : ''}>${p}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </select>--%>
<%--                        <input name="page_size" value="${page_size}" type="hidden">--%>
<%--                        <button type="submit" class="btn btn-primary btn-sm">Go</button>--%>
<%--                    </form>--%>
<%--                </li>--%>
<%--            </ul>--%>
            <ul class="pagination justify-content-end">

                <c:choose>
                    <c:when test="${page - 1 > 0}">
                        <li class="page-item">
                            <a class="page-link"
                               href="<%= request.getContextPath() %>/FrontController?command=/product_catalog&page=${page-1}&page_size=${page_size}">
                                Previous</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1">Previous</a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <c:forEach var="p" begin="${min_possible_page}" end="${max_possible_page}">
                    <c:choose>
                        <c:when test="${page == p}">
                            <li class="page-item disabled">
                                <a class="page-link">Page ${p} of ${page_count}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link"
                                   href="<%= request.getContextPath() %>/FrontController?command=/product_catalog&page=${p}&page_size=${page_size}">
                                        ${p}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:choose>
                    <c:when test="${page + 1 <= page_count}">
                        <li class="page-item">
                            <a class="page-link"
                               href="<%= request.getContextPath() %>/FrontController?command=/product_catalog&page=${page+1}&page_size=${page_size}">
                                Next</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1">Next</a>
                        </li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </nav>

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
                <c:forEach var="product" items="${requestScope.paginate_products}">
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
