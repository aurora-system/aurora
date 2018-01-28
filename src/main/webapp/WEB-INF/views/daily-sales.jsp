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
	                            <th width="100">Customer Name</th>
	                            <th>Slim Delivered</th>
	                            <th>Round Delivered</th>
	                            <th>Slim Returned</th>
	                            <th>Round Returned</th>
	                            <th>Delivery Receipt #</th>
	                            <th bgcolor="F7E8D0">Debt</th>
	                            <th bgcolor="ECFBEA">Payment</th>
	                            <th bgcolor="FBEAEA">Expense</th>
	                            <th width="100">Date and Time</th>
	                            
	                          </tr>
	                        </thead>
	                        <tbody>
	                            <c:forEach var="d" items="${dailySales}">
	                                <tr>
	                                    <td>${d.customerName}</td>
	                                    <td>${d.order.slimCount}</td>
	                                    <td>${d.order.roundCount}</td>
	                                    <td>${d.order.slimReturned}</td>
	                                    <td>${d.order.roundReturned}</td>
	                                    <td>${d.order.deliveryReceiptNum}</td>
	                                    <td bgcolor="F7E8D0">${d.balanceAmount}</td>
	                                    <td bgcolor="ECFBEA">${d.paidAmount}</td>
	                                    <td bgcolor="FBEAEA">${d.expenseAmount}</td>
	                                    <td>${d.dateAndTime}</td>
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
	                            <th bgcolor="F7E8D0">Debt</th>
	                            <th bgcolor="ECFBEA">Payment</th>
	                            <th bgcolor="FBEAEA">Expense</th>
	                            <th bgcolor="D0E6F7">A/R</th>
	                          </tr>
	                        </thead>
	                        <tbody>
	                        	<tr>
	                        		<td>${totalSlimDelivered}</td>
	                        		<td>${totalRoundDelivered}</td>
	                        		<td>${totalSlimReturned}</td>
	                        		<td>${totalRoundReturned}</td>
	                        		<td bgcolor="F7E8D0">${totalDebt}</td>
	                        		<td bgcolor="ECFBEA">${totalPayments}</td>
	                        		<td bgcolor="FBEAEA">${totalExpenses}</td>
	                        		<td bgcolor="D0E6F7">${ar}</td>
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
    <script type="text/javascript">
        $(document).ready(() => {
            $('#myTable').DataTable()
        })
    </script>

</body>
</html>