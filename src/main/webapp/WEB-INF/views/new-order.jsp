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
                    Create New Order
                </div>
                <div class="panel-body">
                    <spring:url value="/orders/save" var="saveOrderUrl"/>
                    <form:form class="form-horizontal" action="${saveOrderUrl}" method="post" modelAttribute="opeForm" id="opeForm">
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
						
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="type">Customer Name:</label>
                           <div class="col-sm-9">
                                <input type="text" class="form-control" id="customerName" placeholder="${customerName}" readonly="true"/>
                            </div>
                        </div>
                        
                         <div class="form-group">
                                <label class="col-sm-3 control-label" for="type">Customer Address:</label>
                               <div class="col-sm-9">
                                    <input type="text" class="form-control" id="customerName" placeholder="${customerAddress}" readonly="true"/>
                                </div>
                            </div>

						<spring:bind path="order.deliveryReceiptNum">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="col-sm-3 control-label" for="type">Delivery Receipt Number:</label>
                                <div class="col-sm-9"> 
                                    <input required="required" name="order.deliveryReceiptNum" type="text" class="form-control" id="deliveryReceiptNum" placeholder="Enter the DR Number"/>
                                    <%--<form:input path="deliveryReceiptNum" type="hidden" class="form-control" value="${newDrNumber}" />--%>
                                    <form:errors path="order.deliveryReceiptNum" class="control-label"/>
                                </div>
                            </div>
                        </spring:bind>
                        
                        <spring:bind path="orderInterval">
	                        <div class="form-group">
	                        	<label class="col-sm-3 control-label" for="orderInterval">Order Interval:</label>
	                        		<div class="col-sm-9">
	                     				<form:input type="text" path="orderInterval" class="form-control" id="orderInterval" placeholder="Override the order interval here"/>
	                                </div>
	                        </div>
                        </spring:bind>
                         
                        <hr />
                        
                        <!-- PRODUCTS -->
                       	<spring:bind path="opList">
	                        <div class="form-group ${status.error ? 'has-error' : ''}">
	                        <c:forEach var="p" items="${productList}" varStatus="s">
	                        	<div class="form-group">
		                        	<label class="col-sm-3 control-label" for="type">${p.name}:</label>
		                        	<div class="col-sm-1"> 
<%-- 		                        		<input name="quantity" type="text" class="form-control calc" id="${p.productId}-qty" placeholder="0"/> --%>
		                            	<form:input path="opList[${s.index}].quantity" name="quantity" type="text" class="form-control calc" id="${p.productId}-qty" placeholder="0"/>
		                            	<form:input path="opList[${s.index}].productId" name="productId" type="hidden" class="form-control" id="${p.productId}" placeholder="0"/>
		                            	<input type="hidden" class="${p.productId}-qty price" value="${p.sellingPrice}"> 
										<%-- <form:errors path="name" class="control-label"/> --%>
		                             </div>
	                             </div>
	                        </c:forEach>
							</div>
						</spring:bind>
						
						<form:input type="hidden" path="order.roundRefillOnlyCount" class="form-control" id="roundRefillOnlyCount"/>
						<form:input type="hidden" path="order.slimRefillOnlyCount" class="form-control" id="slimRefillOnlyCount"/>
						<form:input type="hidden" path="order.roundContainerOnlyCount" class="form-control" id="roundContainerOnlyCount"/>
						<form:input type="hidden" path="order.slimContainerOnlyCount" class="form-control" id="slimContainerOnlyCount"/>
						<form:input type="hidden" path="order.roundRefillWithContainerCount" class="form-control" id="roundRefillWithContainerCount"/>
						<form:input type="hidden" path="order.slimRefillWithContainerCount" class="form-control" id="slimRefillWithContainerCount"/>
						<form:input type="hidden" path="order.roundFreeCount" class="form-control" id="roundFreeCount"/>
						<form:input type="hidden" path="order.slimFreeCount" class="form-control" id="slimFreeCount"/>
						<hr />
						
						<!-- TOTAL AMOUNT -->
<%-- 						<spring:bind path="totalAmount"> --%>
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="col-sm-3 control-label" for="type">Total Amount:</label>
                                <div class="col-sm-9"> 
                                    <form:input path="order.totalAmount" type="text" class="form-control" id="totalAmount" readonly="true"/>
<%--                                     <form:errors path="totalAmount" class="control-label"/> --%>
                                </div>
                            </div>
<%--                         </spring:bind> --%>
                        
                        <!-- AMOUNT PAID -->
                        <spring:bind path="order.amountPaid">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="col-sm-3 control-label" for="type">Amount Paid:</label>
                                <div class="col-sm-9"> 
                                    <form:input required="required" path="order.amountPaid" type="text" class="form-control" id="amountPaid" placeholder="Amount Paid"></form:input>
<%--                                     <form:errors path="order.amountPaid" class="control-label"/> --%>
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

						<!-- ROUND RETURNED -->
                        <spring:bind path="order.roundReturned">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="type">Round Returned:</label>
                                <div class="col-sm-9"> 
                                    <form:input style="background-color:#F6ECD5;" path="order.roundReturned" type="text" class="form-control" id="roundReturned" placeholder="0"/>
<%--                                     <form:errors path="order.roundReturned" class="control-label"/> --%>
                                </div>
                            </div>
                        </spring:bind>
                        
						<!-- SLIM RETURNED -->
                        <spring:bind path="order.slimReturned">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="type">Slim Returned:</label>
                                <div class="col-sm-9"> 
                                    <form:input style="background-color:#D5E8F6;" path="order.slimReturned" type="text" class="form-control" id="slimReturned" placeholder="0"/>
<%--                                     <form:errors path="order.slimReturned" class="control-label"/> --%>
                                </div>
                            </div>
                        </spring:bind>
                        
                        <%--<spring:bind path="saveReturned">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="saveReturned">Save Returned Containers Before Delivery:</label>
                                <div class="col-sm-9">
                                    <form:radiobuttons path="saveReturned" items="${saveReturnedAnswers}"/>
                                </div>
                            </div>
                        </spring:bind> --%>
                        
                        <!-- REMARKS -->
                        <spring:bind path="order.remarks">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="type">Remarks:</label>
                                <div class="col-sm-9"> 
                                    <form:textarea path="order.remarks" type="text" class="form-control" id="remarks" />
<%--                                     <form:errors path="order.remarks" class="control-label"/> --%>
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
    <script src="<c:url value="/resources/js/jquery.form-validator.min.js"/>"></script>
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
            	var total = 0.00;
            	var productStr;
            	
                $('.form-control.calc').each( function() {
                    if ($(this).val() != '') {
                        var multiplier = "." + $(this).attr('id') + '.price';
                    	total += (parseFloat($(this).val()) ) * (parseFloat($(multiplier).val()) );
                    	productStr += $(this).attr('id');
                    }
                    
                    if ($(this).attr('id') == '1-qty') {
                    	if ($(this).val() > 0) {
                    		$('#roundRefillOnlyCount').val($(this).val());
                    	}
                    	
                    	if ($(this).val() == 0) {
                    		$('#roundRefillOnlyCount').val(0);
                    	}
                    }
                    
                    if ($(this).attr('id') == '2-qty') {
                    	if ($(this).val() > 0) {
                    		$('#slimRefillOnlyCount').val($(this).val());
                    	}
                    	
                    	if ($(this).val() == 0) {
                    		$('#slimRefillOnlyCount').val(0);
                    	}
                    }
                    
                    if ($(this).attr('id') == '3-qty') {
                    	if ($(this).val() > 0) {
                    		$('#roundContainerOnlyCount').val($(this).val());
                    	}
                    	
                    	if ($(this).val() == 0) {
                    		$('#roundContainerOnlyCount').val(0);
                    	}
                    }
                    
                    if ($(this).attr('id') == '4-qty') {
                    	if ($(this).val() > 0) {
                    		$('#slimContainerOnlyCount').val($(this).val());
                    	}
                    	
                    	if ($(this).val() == 0) {
                    		$('#slimContainerOnlyCount').val(0);
                    	}
                    }
                    
                    if ($(this).attr('id') == '5-qty') {
                    	if ($(this).val() > 0) {
                    		$('#roundRefillWithContainerCount').val($(this).val());
                    	}
                    	
                    	if ($(this).val() == 0) {
                    		$('#roundRefillWithContainerCount').val(0);
                    	}
                    }
                    
                    if ($(this).attr('id') == '6-qty') {
                    	if ($(this).val() > 0) {
                    		$('#slimRefillWithContainerCount').val($(this).val());
                    	}
                    	
                    	if ($(this).val() == 0) {
                    		$('#slimRefillWithContainerCount').val(0);
                    	}
                    }
                    
                    if ($(this).attr('id') == '22-qty') {
                    	if ($(this).val() > 0) {
                    		$('#roundFreeCount').val($(this).val());
                    	}
                    	
                    	if ($(this).val() == 0) {
                    		$('#roundFreeCount').val(0);
                    	}
                    }
                    
                    if ($(this).attr('id') == '23-qty') {
                    	if ($(this).val() > 0) {
                    		$('#slimFreeCount').val($(this).val());
                    	}
                    	
                    	if ($(this).val() == 0) {
                    		$('#slimFreeCount').val(0);
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