<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1>List of expenses for the day</h1>
            </div>
            <div class="panel-body">
                <table id="expensesTable" class="table table-striped table-bordered table-hover">
			        <thead>
			        	<tr>
			        		<th>Date</th>
			        		<th>Description</th>
			        		<th>Amount</th>
			        	</tr>
                    </thead>
                    <tbody>
                        <c:forEach var="exp" items="${expenses}">
                            <tr>
                                <td>${exp.created_at}</td>
                                <td>${exp.description}</td>
                                <td>${exp.amount}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
            </div>
        </div>
    </div>

    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/datatables.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/datatables.min.css"/>"/>
    <script type="text/javascript">
        $(document).ready(() => {
            $('#expensesTable').DataTable()
        })
    </script>

</body>
</html>