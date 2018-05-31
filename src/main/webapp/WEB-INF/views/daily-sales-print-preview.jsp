<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="fragments/header.jsp" />
<link href="/resources/css/main.css" rel="stylesheet">
<body>

	<div class="container" style="width: 800px;">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="panel panel-info">
	                <div class="panel-heading" style="font-size: 8px; padding: 3px;">
	                    Daily sales for ${datePicked}.
	                </div>
	                <div class="panel-body" style="padding: 2px;">
	                    <table id="myTablePrint" class="table table-striped table-bordered table-hover" style="font-size: 8px;">
	                        <thead>
	                          <tr>
	                          	<th style="padding: 2px;">DR#</th>
	                          	<th style="padding: 2px;" width="60">Time</th>
	                            <th style="padding: 2px;" width="100">Customer</th>
								<th style="padding: 2px;">RD</th>
	                            <th style="padding: 2px;">SD</th>
	                            <th style="padding: 2px;" width="100">Remarks</th>
	                            <th style="padding: 2px;">A/R</th>
	                            <th style="padding: 2px;">Cash</th>
	                            <th style="padding: 2px;">Check</th>
	                            <th style="padding: 2px;">Expense</th>
	                            <th style="padding: 2px;">RR</th>
	                            <th style="padding: 2px;">SR</th>
	                          </tr>
	                        </thead>
	                        <tbody>
	                            <c:forEach var="d" items="${dailySales}">
	                                <tr>
	                                	<td style="padding: 1px;">${d.order.deliveryReceiptNum}</td>
	                                	<td style="padding: 1px;">${d.dateAndTime}</td>
	                                    <td style="padding: 1px;">${d.customerName}</td>
	                                    <td style="padding: 1px;">${d.order.roundCount}</td>
	                                    <td style="padding: 1px;">${d.order.slimCount}</td>
	                                    <td style="padding: 1px;">${d.remarks}</td>
	                                    <td style="padding: 1px;"><fmt:formatNumber type = "currency" pattern = "#,##0.00" value = "${d.balanceAmount}" /></td>
	                                    <td style="padding: 1px;"><fmt:formatNumber type = "currency" pattern = "#,##0.00" value = "${d.paidCash}" /></td>
	                                    <td style="padding: 1px;"><fmt:formatNumber type = "currency" pattern = "#,##0.00" value = "${d.paidCheck}" /></td>
	                                    <td style="padding: 1px;"><fmt:formatNumber type = "currency" pattern = "#,##0.00" value = "${d.expenseAmount}" /></td>
	                                    <td style="padding: 1px;">${d.returnedRound}</td>
	                                    <td style="padding: 1px;">${d.returnedSlim}</td>
	                                </tr>
	                            </c:forEach>
	                        </tbody>
	                    </table>
	                </div>
	            </div>
	        </div>
	    </div>
	    
	    <div class="row" >
    		<div class="col-lg-12">
    			<div class="panel panel-info">
                	<div class="panel-heading clearfix" style="font-size: 8px; padding: 3px;">
                		Totals
                	</div>
                	
                	 <div class="panel-body" style="padding: 2px;">
                	 	<table id="myTablePrint" class="table table-striped table-bordered table-hover" style="font-size: 8px;">
                	 		<thead>
	                          <tr style="min-height: 5px;">
	                            <th style="padding: 2px;">Round Delivered</th>
	                            <th style="padding: 2px;">Slim Delivered</th>
	                            <th style="padding: 2px;">Round Returned</th>
	                            <th style="padding: 2px;">Slim Returned</th>
	                            <th style="padding: 2px;">A/R</th>
	                            <th style="padding: 2px;">Cash Payment</th>
	                            <th style="padding: 2px;">Check Payment</th>
	                            <th style="padding: 2px;">Expense</th>
	                            <th style="padding: 2px;">Net Cash</th>
	                          </tr>
	                        </thead>
	                        <tbody>
	                        	<tr>
	                        		<td style="padding: 1px;">${totalRoundDelivered}</td>
	                        		<td style="padding: 1px;">${totalSlimDelivered}</td>
	                        		<td style="padding: 1px;">${totalRoundReturned}</td>
	                        		<td style="padding: 1px;">${totalSlimReturned}</td>
	                        		<td style="padding: 1px;">${totalDebt}</td>
	                        		<td style="padding: 1px;">${totalCashPayments}</td>
	                        		<td style="padding: 1px;">${totalCheckPayments}</td>
	                        		<td style="padding: 1px;">${totalExpenses}</td>
	                        		<td style="padding: 1px;">${netCash}</td>
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
        $(document).ready(() => {
            $('#myTable').DataTable( {
                "order": [[ 0, "desc" ]],
                "break-inside": avoid;
            } );
        })
        
    </script>

</body>
</html>