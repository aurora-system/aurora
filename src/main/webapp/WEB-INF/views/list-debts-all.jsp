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
                        <p>List of Customers' debts as of today</p>
                    </div>
                    <div class="panel-body">
                        <table id="debtsTable" class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Customer</th>
                                    <th>Total Debts</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="entry" items="${debtsMap}">
                                    <spring:url value="/customers/view?customerId=${entry.key}" var="urlViewCustomer" />
                                    <tr>
                                        <td><a href="${urlViewCustomer}">${entry.value.customerName}</a></td>
                                        <td>${entry.value.debtsTotal}</td>
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
            $('#debtsTable').DataTable()
        })
    </script>
</body>
</html>