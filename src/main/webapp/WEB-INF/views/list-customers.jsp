<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="fragments/header.jsp" />
<body>
    <div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-info">
                <div class="panel-heading">
                    List of Customers
                </div>
                <div class="panel-body">
                    <table id="myTable" class="table table-striped table-bordered table-hover">
                        <thead>
                          <tr>
                            <th>Type</th>
                            <th>Customer Name</th>
                            <th>Address</th>
                            <th>Email Address</th>
                            <th>Contact Number</th>
                            <th>Contact Person</th>
                            <th>Alt Number</th>
                            <th>Actions</th>
                          </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="c" items="${customers}">
                                <tr>
                                    <td>${c.type}</td>
                                    <td>${c.name}</td>
                                    <td>${c.address}</td>
                                    <td>${c.emailAddress}</td>
                                    <td>${c.mainNumber}</td>
                                    <td>${c.contactName}</td>
                                    <td>${c.alternateNumber}</td>
                                    <td nowrap>
                                        <p><button class="btn btn-default">New Order</button>
                                        <button class="btn btn-info">View</button></p>
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
    <script src="<c:url value="/resources/js/datatables.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/datatables.min.css"/>"/>
    <script type="text/javascript">
        $(document).ready(() => {
            $('#myTable').DataTable()
        })
    </script>

</body>
</html>