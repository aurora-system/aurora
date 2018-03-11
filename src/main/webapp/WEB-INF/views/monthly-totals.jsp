<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="fragments/header.jsp" />
<link href="/resources/css/main.css" rel="stylesheet">
<spring:url value="/orders/monthly" var="monthlyTotalsUrl"/>
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
                        <form class="form-horizontal" method="get" action="${monthlyTotalsUrl}">
                            <div class="form-group">
<!-- 								<label class="col-sm-2 control-label">Date: </label> -->
<!--  								<input id="datepicker" type="text" class="col-sm-2" placeholder="Choose Month" name="date">  -->
<!--                                 <div class="col-sm-2" data-date="02-2018" data-date-format="mm-yyyy"> -->
<!--                                     <input path="d" type="date" class="form-control" name="d" placeholder="Date" id="datepicker"/> -->
<!--                                     <span class="add-on"><i class="icon-th"></i></span> -->
<!--                                 </div> -->
<!-- 						 		<label>Date:</label> -->
<!-- 						    	<input type="text" class="form-control form-control-1 input-sm from" placeholder="CheckIn" > -->

								<div class="col-sm-4">
								    <button type="submit" class="btn btn-primary">Generate Report</button>
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
	                    Summary for the month of ${monthPicked} ${yearPicked}
	                </div>
	                <div class="panel-body">
	                    <table id="myTable" class="table table-striped table-bordered table-hover table-fixed">
	                        <thead>
	                          <tr>
	                            <th width="100">Date</th>
	                            <th>Sum of Cash</th>
	                            <th>Sum of A/R</th> <!-- Debts -->
	                            <th>Sum of Expenses</th>
	                            <th>Delivered Round</th>
	                            <th>Delivered Slim</th>
	                            <th>Delivered Total</th>
	                          </tr>
	                        </thead>
	                        <tbody>
	                            <c:forEach var="dt" items="${dailyTotals}">
	                                <tr>
	                                   <td>${dt.date}</td>
	                                   <td>${dt.totalCash}</td>
	                                   <td>${dt.totalPayments}</td> <!-- Debts -->
	                                   <td>${dt.totalExpenses}</td>
	                                   <td>${dt.totalDeliveredRound}</td>
	                                   <td>${dt.totalDeliveredSlim}</td>
	                                   <td>${dt.totalDeliveredContainers}</td>
	                                </tr>
	                            </c:forEach>
	                        </tbody>
	                    </table>
	                </div>
	            </div>
	        </div>
	    </div>
	    
	    <div class="row">
    		<div class="col-lg-12">
    			<div class="panel panel-info">
                	<div class="panel-heading clearfix">
                		Grand Total
                	</div>
                	
                	 <div class="panel-body">
                	 	<table id="myTable" class="table table-striped table-bordered table-hover">
	                        <tbody>
	                        	<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
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
    
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	
    <script type="text/javascript">
        $(document).ready(() => {
            $('#myTable').DataTable( {
                "order": [[ 0, "asc" ]],
            	"pageLength": 50
            } );
        })
    </script>

</body>
</html>