<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="fragments/header.jsp" />
<link href="/resources/css/main.css" rel="stylesheet">
<spring:url value="/orders/daily" var="dailySalesUrl"/>
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
                        <form class="form-horizontal" method="get" action="${dailySalesUrl}">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">Date: </label>
                                <div class="col-sm-3">
                                    <input path="d" type="date" class="form-control" name="d" placeholder="Date"/>
                                </div>
   								<div class="col-sm-5">
								    <button type="submit" class="btn btn-primary">Refresh</button>
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
	                    Daily sales for ${datePicked}.
	                </div>
	                <div class="panel-body">
	                    <table id="myTable" class="table table-striped table-bordered table-hover">
	                        <thead>
	                          <tr>
	                          	<th>DR#</th>
	                          	<th width="100">Time</th>
	                            <th width="100">Customer</th>
								<th>RD</th>
	                            <th>SD</th>
	                            <th>Price</th>
	                            <th width="100">Remarks</th>
	                            <th bgcolor="F7E8D0">A/R</th>
	                            <th bgcolor="ECFBEA">Cash</th>
	                            <th bgcolor="BBC2FB">Check</th>
	                            <th bgcolor="FBEAEA">Expense</th>
	                            <th>RR</th>
	                            <th>SR</th>
	                          </tr>
	                        </thead>
	                        <tbody>
	                            <c:forEach var="d" items="${dailySales}">
	                                <tr>
	                                	<td>${d.order.deliveryReceiptNum}</td>
	                                	<td>${d.dateAndTime}</td>
	                                    <td>${d.customerName}</td>
	                                    <td>${d.order.roundCount}</td>
	                                    <td>${d.order.slimCount}</td>
	                                    <td></td>
	                                    <td>${d.remarks}</td>
	                                    <td bgcolor="F7E8D0">${d.balanceAmount}</td>
	                                    <td bgcolor="ECFBEA">${d.paidCash}</td>
	                                    <td bgcolor="BBC2FB">${d.paidCheck}</td>
	                                    <td bgcolor="FBEAEA">${d.expenseAmount}</td>
	                                    <td>${d.order.roundReturned}</td>
	                                    <td>${d.order.slimReturned}</td>
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
                		Totals
                	</div>
                	
                	 <div class="panel-body">
                	 	<table id="myTable" class="table table-striped table-bordered table-hover">
                	 		<thead>
	                          <tr>
	                            <th>Slim Delivered</th>
	                            <th>Round Delivered</th>
	                            <th>Slim Returned</th>
	                            <th>Round Returned</th>
	                            <th bgcolor="F7E8D0">A/R</th>
	                            <th bgcolor="ECFBEA">Cash Payment</th>
	                            <th bgcolor="BBC2FB">Check Payment</th>
	                            <th bgcolor="FBEAEA">Expense</th>
	                            <th bgcolor="D0E6F7">Net Cash</th>
	                          </tr>
	                        </thead>
	                        <tbody>
	                        	<tr>
	                        		<td>${totalSlimDelivered}</td>
	                        		<td>${totalRoundDelivered}</td>
	                        		<td>${totalSlimReturned}</td>
	                        		<td>${totalRoundReturned}</td>
	                        		<td bgcolor="F7E8D0">${totalDebt}</td>
	                        		<td bgcolor="ECFBEA">${totalCashPayments}</td>
	                        		<td bgcolor="BBC2FB">${totalCheckPayments}</td>
	                        		<td bgcolor="FBEAEA">${totalExpenses}</td>
	                        		<td bgcolor="D0E6F7">${netCash}</td>
	                        	</tr>
	                        </tbody>
                	 	</table>
                	 </div>
                </div>
    		</div>
    	</div>
    	
    	<span class="label label-default">RD - Round Delivered</span>
    	<span class="label label-default">SD - Slim Delivered</span>
    	<span class="label label-default">RR - Round Returned</span>
    	<span class="label label-default">SR - Round Returned</span>
    </div>
    <jsp:include page="fragments/footer.jsp" />
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/datatables.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/datatables.min.css"/>"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
        $(document).ready(() => {
            $('#myTable').DataTable( {
                "order": [[ 0, "desc" ]]
            } );
        })
        
    </script>

</body>
</html>