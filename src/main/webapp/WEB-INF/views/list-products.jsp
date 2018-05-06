<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="fragments/header.jsp" />
<body>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <c:if test="${not empty msg}">
                    <div class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            X
                        </button>
                        <strong>${msg}</strong>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-info">
                    <div class="panel-heading clearfix">
                        <p style="margin-bottom: 0px;">
                            <strong>List of Products</strong>
                            <spring:url value="/products/new" var="newProductUrl"/>
                            <a href="${newProductUrl}" class="btn btn-default pull-right" role="button">Add New Product</a>
                        </p>                        
                    </div>
                    <div class="panel-body">
                        <table id="productsTable" class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Selling Price</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="product" items="${products}">
                                    <tr>
                                        <td>${product.name}</td>
                                        <td>${product.description}</td>
                                        <td><fmt:formatNumber type = "currency" pattern = "#,##0.00" value = "${product.sellingPrice}"></fmt:formatNumber></td>
                                        <td>
                                            <spring:url value="/products/edit/${product.productId}" var="editProductUrl"/>
                                            <a href="${editProductUrl}" class="btn btn-default">Edit Product</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="fragments/footer.jsp" />
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/datatables.min.js"/>"></script>
    <script src="<c:url value="/resources/js/dataTables.fixedHeader.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/fixedHeader.bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/datatables.min.css"/>"/>
    <script type="text/javascript">
        $(document).ready(() => {
            $('#productsTable').DataTable({
                fixedHeader: true,
                paging: false,
                scrollY: 350,
                scrollCollapse: true
            })
        })
    </script>
</body>
</html>