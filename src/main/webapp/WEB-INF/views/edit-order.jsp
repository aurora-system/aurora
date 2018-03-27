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
                    <spring:url value="/orders/update" var="saveOrderUrl"/>
                    <form:form class="form-horizontal" action="${saveOrderUrl}" method="post" modelAttribute="orderForm" id="orderForm">
                        <form:hidden path="orderId"/>
<%--                         <spring:bind path="name"> --%>
<%--                             <div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<!--                                 <label class="col-sm-3 control-label" for="name">Customer name:</label> -->
<!--                                 <div class="col-sm-9"> -->
<%--                                     <form:input path="name" type="text" class="form-control" id="name" placeholder="Customer name" /> --%>
<%--                                     <form:errors path="name" class="control-label"/> --%>
<!--                                 </div> -->
<!--                             </div> -->
<%--                         </spring:bind> --%>
						
						<form:hidden path="customerId"/>
						
<%-- 						<spring:bind path="customerId"> --%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="type">Customer Name:</label>
                               <div class="col-sm-9">
                                    <input type="text" class="form-control" id="customerName" placeholder="${customerName}" disabled="true"/>
                                </div>
                            </div>
<%--                         </spring:bind> --%>

						<spring:bind path="deliveryReceiptNum">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="col-sm-3 control-label" for="type">Delivery Receipt Number:</label>
                                <div class="col-sm-9"> 
                                    <input name="deliveryReceiptNum" type="text" class="form-control" id="deliveryReceiptNum" value="${drNumber}" disabled="true"/>
                                    <%--<form:input path="deliveryReceiptNum" type="hidden" class="form-control" value="${newDrNumber}" />--%>
                                    <form:errors path="deliveryReceiptNum" class="control-label"/>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="amountPaid">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="col-sm-3 control-label" for="type">Amount Paid:</label>
                                <div class="col-sm-9"> 
                                    <form:input path="amountPaid" type="text" class="form-control" id="amountPaid" placeholder="Amount Paid"/>
                                    <form:errors path="amountPaid" class="control-label"/>
                                </div>
                            </div>
                        </spring:bind>
                        
                        <spring:bind path="totalAmount">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="col-sm-3 control-label" for="type">Total Amount:</label>
                                <div class="col-sm-9"> 
                                    <form:input path="totalAmount" type="text" class="form-control" id="totalAmount" placeholder="Total Amount"/>
                                    <form:errors path="totalAmount" class="control-label"/>
                                </div>
                            </div>
                        </spring:bind>
                        
                         <spring:bind path="slimCount">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="type">Slim Order:</label>
                                <div class="col-sm-9"> 
                                    <form:input path="slimCount" type="text" class="form-control" id="slimCount" placeholder="0"/>
                                    <form:errors path="slimCount" class="control-label"/>
                                </div>
                            </div>
                        </spring:bind>
                        
                        <spring:bind path="roundCount">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="type">Round Order:</label>
                                <div class="col-sm-9"> 
                                    <form:input path="roundCount" type="text" class="form-control" id="roundCount" placeholder="0"/>
                                    <form:errors path="roundCount" class="control-label"/>
                                </div>
                            </div>
                        </spring:bind>
                        
                        <spring:bind path="slimReturned">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="type">Slim Returned:</label>
                                <div class="col-sm-9"> 
                                    <form:input path="slimReturned" type="text" class="form-control" id="slimReturned" placeholder="0"/>
                                    <form:errors path="slimReturned" class="control-label"/>
                                </div>
                            </div>
                        </spring:bind>
                        
                        <spring:bind path="roundReturned">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="type">Round Returned:</label>
                                <div class="col-sm-9"> 
                                    <form:input path="roundReturned" type="text" class="form-control" id="roundReturned" placeholder="0"/>
                                    <form:errors path="roundReturned" class="control-label"/>
                                </div>
                            </div>
                        </spring:bind>
                        
                        <spring:bind path="remarks">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="type">Remarks:</label>
                                <div class="col-sm-9"> 
                                    <form:textarea path="remarks" type="text" class="form-control" id="remarks" />
                                    <form:errors path="remarks" class="control-label"/>
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
        })
    </script>

</body>
</html>