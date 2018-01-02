<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>New Customer</title>
</head>
<body>
    <div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Create Customer
                </div>
                <div class="panel-body">
                    <spring:url value="/customers/save" var="customersSave"/>
                    <form:form class="form-horizontal" action="${customersSave}" method="POST">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="name">Customer Name:</label>
                            <div class="col-sm-10"><input type="text" class="form-control" id="name"></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="type">Customer Type:</label>
                            <div class="col-sm-10"><input type="text" class="form-control" id="type"></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="address">Address:</label>
                            <div class="col-sm-10"><input type="text" class="form-control" id="address"></div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="mainnumber">Contact Number:</label>
                          <div class="col-sm-10"><input type="text" class="form-control" id="mainnumber"></div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="contactname">Contact Person:</label>
                          <div class="col-sm-10"><input type="text" class="form-control" id="contactname"></div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="alternatenumber">Alternate Contact:</label>
                          <div class="col-sm-10"><input type="text" class="form-control" id="alternatenumber"></div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="email">Email address:</label>
                          <div class="col-sm-10"><input type="email" class="form-control" id="email"></div>
                        </div>
                        <div class="col-sm-offset-2 col-sm-10"><button type="submit" class="btn btn-default">Submit</button></div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Search Results
                </div>
                <div class="panel-body">
                    <table id="myTable" class="table table-striped table-bordered table-hover">
                        <thead>
                          <tr>
                            <th>Customer Name</th>
                            <th>Address</th>
                            <th>Contact Number</th>
                            <th>Contact Person</th>
                            <th>Actions</th>
                          </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </div>
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