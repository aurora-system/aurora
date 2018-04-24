<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<body>
<jsp:include page="fragments/header.jsp" />
    <div class="container">
    <div class="row">
        <div class="col-lg-12">
            <c:if test="${not empty msg}">
                <div class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        X
                    </button>
                    <strong>${msg}</strong>
                </div>
            </c:if>
            
            <c:if test="${not empty failmsg}">
                <div class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        X
                    </button>
                    <strong>${failmsg}</strong>
                </div>
            </c:if>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-info">
                <div class="panel-heading">
                    List of Orders
                </div>
                <div class="panel-body">
                    <table id="myTable" class="table table-striped table-bordered table-hover">
                        <thead>
                          <tr>
                          	<th>Actions</th>
                          	<th>Status</th>
                            <th>Customer Name</th>
                            <th>DR #</th>
                            <th>Amount Paid</th>
                            <th>Total Amount</th>
                            <th bgcolor="F6ECD5">Del Round</th>
                            <th bgcolor="D5E8F6">Del Slim</th>
                            <th bgcolor="F6ECD5">Ret Round</th>
                            <th bgcolor="D5E8F6">Ret Slim</th>
                            <th>Date</th>
                            <th>Edit</th>
                          </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="o" items="${orders}">
                            	<spring:url value="/customers/view?customerId=${o.order.customerId}" var="urlViewCustomer" />
                                <tr>
                                	<td nowrap>
<%--                                     	<form action="edit" method="get" style="display: inline-block;"> --%>
<%-- 											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />  --%>
<!-- 											<input type="hidden" name=orderId value=${o.order.orderId}>  -->
<%-- 											<input class="btn btn-default" type="submit" value="Edit" <c:if test="${o.order.status == 'Delivered'}"><c:out value="disabled='disabled'"/></c:if>> --%>
<%-- 										</form> --%>
										<form action="deliver" method="get" style="display: inline-block;">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
											<input type="hidden" name=orderId value=${o.order.orderId}> 
											<input class="btn btn-success" type="submit" value="Deliver"
												<c:if test="${o.order.status == 'Delivered' || o.order.status == 'Cancelled'}">
												<c:out value="disabled='disabled'"/></c:if>>
										</form>
										<form action="cancel" method="get" style="display: inline-block;">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
											<input type="hidden" name=orderId value=${o.order.orderId}> 
											<input class="btn btn-danger" type="submit" value="Cancel" <c:if test="${o.order.status == 'Delivered' || o.order.status == 'Cancelled'}">
												<c:out value="disabled='disabled'"/></c:if>>
										</form>
									</td>
									<td>${o.order.status}</td>
                                    <td><a href="${urlViewCustomer}">${o.customerName}</a></td>
                                    <td>${o.order.deliveryReceiptNum}</td>
                                    <td><fmt:formatNumber type = "currency" pattern = "#,##0.00" value = "${o.order.amountPaid}"></fmt:formatNumber></td>
                                    <td><fmt:formatNumber type = "currency" pattern = "#,##0.00" value = "${o.order.totalAmount}"></fmt:formatNumber></td>
                                    <td bgcolor="F6ECD5">${o.order.roundCount}</td>
                                    <td bgcolor="D5E8F6">${o.order.slimCount}</td>
                                    <td bgcolor="F6ECD5">${o.order.roundReturned}</td>
                                    <td bgcolor="D5E8F6">${o.order.slimReturned}</td>
                                    <td>${o.formattedDate}</td>
                                    <td>
                                    	<spring:url value="/orders/edit" var="editOrderUrl"/>
										<form action="${editOrderUrl}" method="get" style="display: inline-block;" modelAttribute="orderForm">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
											<input type="hidden" name=orderId value=${o.order.orderId}> 
											<input class="btn btn-default" type="submit" value="Edit">
										</form>
                                    </td>
                                </tr>
                            </c:forEach>
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
            $('#myTable').DataTable({
            	"order" : [[ 3, "desc"]],
            	"columns": [
            	    { "orderable": false },
            	    null,
            	    null,
            	    null,
            	    null,
            	    null,
            	    null,
            	    null,
            	    null,
            	    null,
            	    null,
            	    { "orderable": false }
            	  ]
            });
            
            $('#headerNav a').click(function(e) {
    			$('#headerNav a').removeClass('active');
    			$(this).addClass('active');
    		});
        });
    </script>

</body>
</html>