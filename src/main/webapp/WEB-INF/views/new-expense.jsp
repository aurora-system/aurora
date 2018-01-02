<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>New Expense</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-green">
                    <div class="panel-heading">
                        <h1>New Expense</h1>
                    </div>
                    <div class="panel-body">
                        <spring:url value="/expenses/save" var="saveExpenseUrl"/>
                        <form:form class="form-horizontal" method="post" modelAttribute="expense" action="${saveExpenseUrl}">
                            <form:hidden path="expenseId"/>
                            <spring:bind path="created_at">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label class="col-sm-2 control-label">Date: </label>
                                    <div class="col-sm-10">
                                        <form:input path="created_at" type="text" class="form-control" id="created_at" placeholder="Date"/>
                                        <form:errors path="created_at" class="control-label"/>
                                    </div>
                                </div>
                            </spring:bind>
                            <spring:bind path="description">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label class="col-sm-2 control-label">Description: </label>
                                    <div class="col-sm-10">
                                        <form:input path="description" type="text" class="form-control" id="description" placeholder="Description"/>
                                        <form:errors path="description" class="control-label"/>
                                    </div>
                                </div>
                            </spring:bind>
                            <spring:bind path="amount">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label class="col-sm-2 control-label">Amount in Php: </label>
                                    <div class="col-sm-10">
                                        <form:input path="amount" type="text" class="form-control" id="amount" placeholder="Amount"/>
                                        <form:errors path="amount" class="control-label"/>
                                    </div>
                                </div>
                            </spring:bind>
                            <div class="form-group">
		                        <div class="col-sm-offset-2 col-sm-10">
		                      	    <button type="submit" class="btn-lg btn-primary">Log Expense</button>
		                        </div>
		                    </div>
                        </form:form>
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
        
    </script>

</body>
</html>