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
		                  Error Encountered
		             </div>
		             <div class="panel-body">
		              	<p>
							Sorry! The system encountered an error. Please contact the developers for assistance.
						</p>
		             </div>
	       		</div>
	       	</div>
	     </div>
    </div>
    
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/datatables.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/datatables.min.css"/>"/>
</body>
</html>