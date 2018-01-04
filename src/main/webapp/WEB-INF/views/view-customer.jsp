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
                    Customer Information
                </div>
                <div class="panel-body">
                    <p><b>Name:</b> ${customer.name}</p>
                    <p><b>Type:</b> ${customer.type}</p>
                    <p><b>Address:</b> ${customer.address}</p>
                    <p><b>Contact Person:</b> ${customer.contactName}</p>
                    <p><b>Main number:</b> ${customer.mainNumber}</p>
                    <p><b>Alternate number:</b> ${customer.alternateNumber}</p>
                    <p><b>Email Address:</b> ${customer.emailAddress}</p>
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