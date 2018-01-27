<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="fragments/header.jsp" />
<spring:url value="/customers/neworder" var="urlNewOrder" />
<spring:url value="/payments/new" var="urlNewPayment" />
<spring:url value="/debts/new" var="urlNewDebt" />
<spring:url value="/customers/edit" var="urlEditCustomer" />
<spring:url value="/container/return" var="urlReturnContainer" />
<body>
    <div class="container">
    
    <div class="row">
        <div class="col-lg-12">
            <c:if test="${not empty msg}">
                <div class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">X—</span>
                    </button>
                    <strong>${msg}</strong>
                </div>
            </c:if>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-info">
                <div class="panel-heading clearfix">
                    <p class="pull-left" style="font-size: 20px"><b>${customer.name}</b></p>
                    <a href="${urlEditCustomer}?customerId=${customer.customerId}" class="btn btn-default pull-right" role="button">Edit</a>
                </div>
                <div class="panel-body">
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
    
    <div class="container">
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="panel panel-info">
	                <div class="panel-heading clearfix">
	                    Order History
	                    <a href="${urlNewOrder}?customerId=${customer.customerId}" class="btn btn-default pull-right" role="button">New Order</a>
	                </div>
	                <div class="panel-body">
	                    <table id="myTable" class="table table-striped table-bordered table-hover">
	                        <thead>
	                          <tr>
	                            <th>Delivery Receipt #</th>
	                            <th>Amount Paid</th>
	                            <th>Total Amount</th>
	                            <th>Slim #</th>
	                            <th>Round #</th>
	                            <th>Date</th>
	                          </tr>
	                        </thead>
	                        <tbody>
	                            <c:forEach var="o" items="${orders}">
	                                <tr>
	                                    <td>${o.deliveryReceiptNum}</td>
	                                    <td>${o.amountPaid}</td>
	                                    <td>${o.totalAmount}</td>
	                                    <td>${o.slimCount}</td>
	                                    <td>${o.roundCount}</td>
	                                    <td>${o.createdAt}</td>
	                                </tr>
	                            </c:forEach>
	                        </tbody>
	                    </table>
	                </div>
	            </div>
	        </div>
	    </div>
    </div>
    
    <div class="container">
    	<div class="row">
    		<div class="col-lg-12">
    			<div class="panel panel-info">
                	<div class="panel-heading clearfix">
                		Details
                		<a href="${urlReturnContainer}?cid=${customer.customerId}" class="btn btn-default pull-right" role="button">Return Container</a>
                		<a href="${urlNewDebt}?cid=${customer.customerId}" style="margin-right: 5px" class="btn btn-default pull-right" role="button">New Debt</a>
						<a href="${urlNewPayment}?cid=${customer.customerId}" style="margin-right: 5px" class="btn btn-default pull-right" role="button">New Payment</a>
                	</div>
                	
                	 <div class="panel-body">
                	 	<table id="myTable" class="table table-striped table-bordered table-hover">
                	 		<thead>
	                          <tr>
	                            <th>Last Order Date</th>
	                            <th>Total Borrowed Slim</th>
	                            <th>Total Borrowed Round</th>
	                            <th>Total Debt</th>
	                          </tr>
	                        </thead>
	                        <tbody>
	                        	<tr>
	                        		<td>${mostRecentOrderDate}</td>
	                        		<td>${totalBorrowedSlim}</td>
	                        		<td>${totalBorrowedRound}</td>
	                        		<td>${totalDebt}</td>
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
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
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