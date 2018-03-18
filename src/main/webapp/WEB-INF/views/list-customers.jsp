<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<spring:url value="/resources/css/main.css" var="mainCss" />
<spring:url value="/customers/new" var="urlAddCustomer" />
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
        </div>
    </div>
    
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-info">
                <div class="panel-heading clearfix">
                    <p class="pull-left">List of Customers</p> 
                    
                    <a href="${urlAddCustomer}" class="btn btn-default pull-right" role="button">New Customer</a>
                </div>
                <div class="panel-body">
                    <table id="myTable" class="table table-striped table-bordered table-hover">
                        <thead>
                          <tr>
                            <th>Type</th>
                            <th>Customer Name</th>
                            <th>Address</th>
<!--                             <th>Email Address</th> -->
                            <th>Contact Person</th>
                            <th>Contact Number</th>
<!--                             <th>Alt Number</th> -->
                            <th width="100">Actions</th>
                          </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="c" items="${customers}">
                                <tr>
                                    <td>${c.type}</td>
                                    <td>${c.name}</td>
                                    <td>${c.address}</td>
<%--                                     <td>${c.emailAddress}</td> --%>
									<td>${c.contactName}</td>
                                    <td>${c.mainNumber}</td>
<%--                                     <td>${c.alternateNumber}</td> --%>
                                    <td nowrap>
<!-- 										<p><button class="btn btn-default">New Order</button></p>  -->
										<!--                                         <button class="btn btn-info">View</button> -->
										
										<form action="view" method="get" style="display: inline-block;" >
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
											<input type="hidden" name=customerId value=${c.customerId}> 
											<input class="btn btn-info" type="submit" value="View">
										</form>
										
										<form action="neworder" method="get" style="display: inline-block;">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
											<input type="hidden" name=customerId value=${c.customerId}> 
											<input class="btn btn-default" type="submit" value="New Order">
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
            $('#myTable').DataTable();
            
            $('#headerNav a').click(function(e) {
    			$('#headerNav a').removeClass('active');
    			$(this).addClass('active');
    		});
        });
    </script>

</body>
</html>