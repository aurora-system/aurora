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
            <div class="panel panel-info">
                <div class="panel-heading">
                    Edit Order
                </div>
                <div class="panel-body">
                    <spring:url value="/orders/update" var="updateOrderUrl"/>
                    <form:form class="form-horizontal" action="${updateOrderUrl}" method="post" modelAttribute="opeForm" id="opeForm">
                        <form:hidden path="order.orderId"/>
<%--                         <spring:bind path="name"> --%>
<%--                             <div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<!--                                 <label class="col-sm-3 control-label" for="name">Customer name:</label> -->
<!--                                 <div class="col-sm-9"> -->
<%--                                     <form:input path="name" type="text" class="form-control" id="name" placeholder="Customer name" /> --%>
<%--                                     <form:errors path="name" class="control-label"/> --%>
<!--                                 </div> -->
<!--                             </div> -->
<%--                         </spring:bind> --%>
						
						<form:hidden path="order.customerId"/>
						
<%-- 						<spring:bind path="customerId"> --%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="type">Customer Name:</label>
                               <div class="col-sm-9">
                                    <input type="text" class="form-control" id="customerName" placeholder="${customerName}" readonly="true"/>
                                </div>
                            </div>
<%--                         </spring:bind> --%>

						<spring:bind path="order.deliveryReceiptNum">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="col-sm-3 control-label" for="type">Delivery Receipt Number:</label>
                                <div class="col-sm-9"> 
                                    <input name="order.deliveryReceiptNum" type="text" class="form-control" id="deliveryReceiptNum" value="${drNumber}" readonly="true"/>
                                    <%--<form:input path="deliveryReceiptNum" type="hidden" class="form-control" value="${newDrNumber}" />--%>
                                    <form:errors path="order.deliveryReceiptNum" class="control-label"/>
                                </div>
                            </div>
                        </spring:bind>
                        
                        <hr />
                        
                        <!-- PRODUCTS -->
                       	<spring:bind path="opList">
	                        <div class="form-group ${status.error ? 'has-error' : ''}">
	                        <c:forEach var="p" items="${productList}" varStatus="s">
	                        	<div class="form-group">
		                        	<label class="col-sm-3 control-label" for="type">${p.name} Qty:</label>
		                        	<div class="col-sm-1"> 
<%-- 		                        		<input name="quantity" type="text" class="form-control calc" id="${p.productId}-qty" placeholder="0"/> --%>
		                            	<form:input path="opList[${s.index}].quantity" name="quantity" type="text" class="form-control calc" id="${p.productId}-qty" placeholder="0"/>
		                            	<form:input path="opList[${s.index}].productId" name="productId" type="hidden" class="form-control" id="${p.productId}" placeholder="0"/>
		                            	<form:input path="opList[${s.index}].orderProductId" name="orderProductId" type="hidden" class="form-control" id="orderProductId" placeholder="0"/>
		                            	<input type="hidden" class="${p.productId}-qty price" value="${p.sellingPrice}"> 
										<%-- <form:errors path="name" class="control-label"/> --%>
		                             </div>
	                             </div>
	                        </c:forEach>
							</div>
						</spring:bind>
						
						<form:input type="hidden" path="order.roundCount" class="form-control" id="roundCount"/>
						<form:input type="hidden" path="order.slimCount" class="form-control" id="slimCount"/>
						<hr />

 						<spring:bind path="order.totalAmount">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="col-sm-3 control-label" for="type">Total Amount:</label>
                                <div class="col-sm-9"> 
                                    <form:input path="order.totalAmount" type="text" class="form-control" id="totalAmount" placeholder="Total Amount" readonly="true"/>
<%--                                     <form:errors path="totalAmount" class="control-label"/> --%>
                                </div>
                            </div>
                        </spring:bind>
                        
                        <spring:bind path="order.amountPaid">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="col-sm-3 control-label" for="type">Amount Paid:</label>
                                <div class="col-sm-9"> 
                                    <form:input path="order.amountPaid" type="text" class="form-control" id="amountPaid" placeholder="Amount Paid"/>
                                    <form:errors path="order.amountPaid" class="control-label"/>
                                </div>
                            </div>
                        </spring:bind>
                        
<%--                          <spring:bind path="slimCount"> --%>
<!--                             <div class="form-group"> -->
<!--                                 <label class="col-sm-3 control-label" for="type">Slim Order:</label> -->
<!--                                 <div class="col-sm-9">  -->
<%--                                     <form:input path="slimCount" type="text" class="form-control" id="slimCount" placeholder="0"/> --%>
<%--                                     <form:errors path="slimCount" class="control-label"/> --%>
<!--                                 </div> -->
<!--                             </div> -->
<%--                         </spring:bind> --%>
                        
<%--                         <spring:bind path="roundCount"> --%>
<!--                             <div class="form-group"> -->
<!--                                 <label class="col-sm-3 control-label" for="type">Round Order:</label> -->
<!--                                 <div class="col-sm-9">  -->
<%--                                     <form:input path="roundCount" type="text" class="form-control" id="roundCount" placeholder="0"/> --%>
<%--                                     <form:errors path="roundCount" class="control-label"/> --%>
<!--                                 </div> -->
<!--                             </div> -->
<%--                         </spring:bind> --%>
                        
                        <spring:bind path="order.roundReturned">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="type">Round Returned:</label>
                                <div class="col-sm-9"> 
                                    <form:input path="order.roundReturned" type="text" class="form-control" id="roundReturned" placeholder="0"/>
                                    <form:errors path="order.roundReturned" class="control-label"/>
                                </div>
                            </div>
                        </spring:bind>
                        
                        <spring:bind path="order.slimReturned">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="type">Slim Returned:</label>
                                <div class="col-sm-9"> 
                                    <form:input path="order.slimReturned" type="text" class="form-control" id="slimReturned" placeholder="0"/>
                                    <form:errors path="order.slimReturned" class="control-label"/>
                                </div>
                            </div>
                        </spring:bind>
                        
                        <spring:bind path="order.remarks">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="type">Remarks:</label>
                                <div class="col-sm-9"> 
                                    <form:textarea path="order.remarks" type="text" class="form-control" id="remarks" />
                                    <form:errors path="order.remarks" class="control-label"/>
                                </div>
                            </div>
                        </spring:bind>
                        
                        <div class="col-sm-offset-3 col-sm-9">
                        	<button type="submit" class="btn btn-primary">Submit</button>
	                        <spring:url value="/orders/list" var="listOrdersUrl"/>
	                        <a href="${listOrdersUrl}" class="btn btn-primary">Cancel</a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
    
    
<!--     <div class="row"> -->
<!--         <div class="col-lg-12"> -->
<!--             <div class="panel panel-default"> -->
<!--                 <div class="panel-heading"> -->
<!--                     Search Results -->
<!--                 </div> -->
<!--                 <div class="panel-body"> -->
<!--                     <table id="myTable" class="table table-striped table-bordered table-hover"> -->
<!--                         <thead> -->
<!--                           <tr> -->
<!--                             <th>Customer Name</th> -->
<!--                             <th>Address</th> -->
<!--                             <th>Contact Number</th> -->
<!--                             <th>Contact Person</th> -->
<!--                             <th>Actions</th> -->
<!--                           </tr> -->
<!--                         </thead> -->
<!--                         <tbody> -->
<!--                         </tbody> -->
<!--                     </table> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
    </div>
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/datatables.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/datatables.min.css"/>"/>
    <script type="text/javascript">
        $(document).ready(() => {
            $('#myTable').DataTable()
            
            $('#orderForm').on('submit', () => {
                $('#deliveryReceiptNum').prop('disabled', false);
            })
            
         // Compute the total amount based on the quantity of products
            $('.form-control.calc').change( function() {
            	var total = 0;
            	var productStr;
            	
                $('.form-control.calc').each( function() {
                    if ($(this).val() != '') {
                        var multiplier = "." + $(this).attr('id') + '.price';
                    	total += (parseInt($(this).val()) ) * (parseInt($(multiplier).val()) );
                    	productStr += $(this).attr('id');
                    }
                    
                    if ($(this).attr('id') == '1-qty') {
                    	if ($(this).val() > 0) {
                    		$('#roundCount').val($(this).val());
                    	}
                    }
                    
                    if ($(this).attr('id') == '2-qty') {
                    	if ($(this).val() > 0) {
                    		$('#slimCount').val($(this).val());
                    	}
                    }
                });
                
                $('#totalAmount').attr('value', total);
                $('#totalAmount').html(total);
            });
        })
    </script>

</body>
</html>