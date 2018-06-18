<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="fragments/header.jsp" />
<link href="/resources/css/main.css" rel="stylesheet">
<body>

	<div class="container" style="width: 860px;">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="panel panel-info">
	                <div class="panel-heading" style="font-size: 10px; padding: 6px;">
	                    Daily sales for ${datePicked}.
	                </div>
	                <div class="panel-body" style="padding: 8px;">
	                    <table id="myTablePrintData" class="table table-striped table-bordered table-hover" style="font-size: 10px;">
	                        <thead>
	                          <tr>
	                          	<th style="padding: 8px;">DR#</th>
	                          	<th style="padding: 8px;" width="60">Time</th>
	                            <th style="padding: 8px;" width="100">Customer</th>
								<th style="padding: 8px;">RD</th>
	                            <th style="padding: 8px;">SD</th>
	                            <th style="padding: 8px;" width="100">Remarks</th>
	                            <th style="padding: 8px;">A/R</th>
	                            <th style="padding: 8px;">Cash</th>
	                            <th style="padding: 8px;">Check</th>
	                            <th style="padding: 8px;">Expense</th>
	                            <th style="padding: 8px;">RR</th>
	                            <th style="padding: 8px;">SR</th>
	                          </tr>
	                        </thead>
	                        <tbody>
	                            <c:forEach var="d" items="${dailySales}">
	                                <tr>
	                                	<td style="padding: 6px;">${d.order.deliveryReceiptNum}</td>
	                                	<td style="padding: 6px;">${d.dateAndTime}</td>
	                                    <td style="padding: 6px;">${d.customerName}</td>
	                                    <td style="padding: 6px;">${d.order.roundCount}</td>
	                                    <td style="padding: 6px;">${d.order.slimCount}</td>
	                                    <td style="padding: 6px;">${d.remarks}</td>
	                                    <td style="padding: 6px;"><fmt:formatNumber type = "currency" pattern = "#,##0.00" value = "${d.balanceAmount}" /></td>
	                                    <td style="padding: 6px;"><fmt:formatNumber type = "currency" pattern = "#,##0.00" value = "${d.paidCash}" /></td>
	                                    <td style="padding: 6px;"><fmt:formatNumber type = "currency" pattern = "#,##0.00" value = "${d.paidCheck}" /></td>
	                                    <td style="padding: 6px;"><fmt:formatNumber type = "currency" pattern = "#,##0.00" value = "${d.expenseAmount}" /></td>
	                                    <td style="padding: 6px;">${d.returnedRound}</td>
	                                    <td style="padding: 6px;">${d.returnedSlim}</td>
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
                	<div class="panel-heading clearfix" style="font-size: 10px; padding: 6px;">
                		Totals
                	</div>
                	
                	 <div class="panel-body" style="padding: 8px;">
                	 	<table id="myTablePrintData" class="table table-striped table-bordered table-hover" style="font-size: 10px;">
                	 		<thead>
	                          <tr style="min-height: 5px;">
	                            <th style="padding: 8px;">Round Delivered</th>
	                            <th style="padding: 8px;">Slim Delivered</th>
	                            <th style="padding: 8px;">Round Returned</th>
	                            <th style="padding: 8px;">Slim Returned</th>
	                            <th style="padding: 8px;">A/R</th>
	                            <th style="padding: 8px;">Cash Payment</th>
	                            <th style="padding: 8px;">Check Payment</th>
	                            <th style="padding: 8px;">Expense</th>
	                            <th style="padding: 8px;">Net Cash</th>
	                          </tr>
	                        </thead>
	                        <tbody>
	                        	<tr>
	                        		<td style="padding: 6px;">${totalRoundDelivered}</td>
	                        		<td style="padding: 6px;">${totalSlimDelivered}</td>
	                        		<td style="padding: 6px;">${totalRoundReturned}</td>
	                        		<td style="padding: 6px;">${totalSlimReturned}</td>
	                        		<td style="padding: 6px;">${totalDebt}</td>
	                        		<td style="padding: 6px;">${totalCashPayments}</td>
	                        		<td style="padding: 6px;">${totalCheckPayments}</td>
	                        		<td style="padding: 6px;">${totalExpenses}</td>
	                        		<td style="padding: 6px;">${netCash}</td>
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
            $('#myTablePrintData').DataTable( {
                "order": [[ 0, "desc" ]],
                "break-inside": avoid
            } );
        })
        
    </script>

</body>
</html>