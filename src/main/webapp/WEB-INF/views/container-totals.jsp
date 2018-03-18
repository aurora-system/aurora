<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="fragments/header.jsp" />
<body>
    <div class="container" style="width: 800px">
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <p>Total Balance of Borrowed Containers as of Today</p>
                    </div>
                    <div class="panel-body">
                        <table id="containersTable" class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Customer</th>
                                    <th width="50">Round Total Balance</th>
                                    <th width="50">Slim Total Balance</th>
                                    <th width="50">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="c" items="${containersMap}">
                                    <spring:url value="/customers/view?customerId=${c.key}" var="urlViewCustomer" />
                                    <spring:url value="/container/return?cid=${c.key}" var="urlReturnContainer" />
                                    <tr>
                                        <td><a href="${urlViewCustomer}">${c.value.customer.name}</a></td>
                                        <td>${c.value.roundTotal}</td>
                                        <td>${c.value.slimTotal}</td>
                                        <td>
                                            <a href="${urlReturnContainer}" style="text-align: center;" class="btn btn-default" role="button">Return</a>
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
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/datatables.min.css"/>"/>
    <script type="text/javascript">
        $(document).ready(() => {
            $('#containersTable').DataTable()
        })
    </script>
</body>
</html>