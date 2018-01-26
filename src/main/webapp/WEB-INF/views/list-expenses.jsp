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
                <c:if test="${not empty msg}">
                    <div class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <strong>${msg}</strong>
                    </div>
                </c:if>
                <div class="panel panel-info">
                    <div class="panel-body">
                        <spring:url value="/expenses/list" var="listExpensesUrl"/>
                        <form class="form-horizontal" method="get" action="${listExpensesUrl}">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">Date: </label>
                                <div class="col-sm-3">
                                    <input path="d" type="date" class="form-control" name="d" placeholder="Date"/>
                                </div>
   								<div class="col-sm-5">
								    <button type="submit" class="btn btn-primary">List Expenses</button>
								</div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <p>List of expenses for ${date}.</p>
                        <p>Running Total: ${expensesTotal}.
                            <spring:url value="/expenses/new" var="newExpenseUrl"/>
                            <a href="${newExpenseUrl}" class="btn btn-default pull-right" role="button">Add Expense</a>
                        </p>
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
            $('#expensesTable').DataTable()
        })
    </script>

</body>
</html>