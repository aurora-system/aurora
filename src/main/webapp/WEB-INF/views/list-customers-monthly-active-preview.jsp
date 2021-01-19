<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="fragments/header.jsp" />
</head>
<body>
    <jsp:include page="fragments/nav.jsp" />
    <div class="container">

		<div class="row">
        <div class="col-lg-12">
            <div class="panel panel-info">
                <div class="panel-body">
                    <table id="myTable" class="table table-striped table-bordered table-hover" style="font-size: 10px;">
                        <thead>
                          <tr>
                            <th style="padding: 8px;">Type</th>
                            <th style="padding: 8px;">Customer Name</th>
                            <th style="padding: 8px;">Refill</th>
                            <th style="padding: 8px;">Most Recent Order</th>
                            <th style="padding: 8px;">Days</th>
                            <th style="padding: 8px;">Address</th>
                            <th style="padding: 8px;">Contact Number</th>
                          </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cp" items="${cpeList}">
                                <tr>
                                    <td style="padding: 8px;">${cp.customer.type}</td>
                                    <td style="padding: 8px;">${cp.customer.name}</td>
                                    <td style="padding: 8px;">${cp.refillPrice}</td>
                                    <td style="padding: 8px;">${cp.mostRecentOrderDate}</td>
                                    <td style="padding: 8px;">${cp.customer.orderInterval}</td>
                                    <td style="padding: 8px;">${cp.customer.address}</td>
                                    <td style="padding: 8px;">${cp.customer.mainNumber}</td>
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
    
    <script type="text/javascript">
        $(document).ready(() => {
            $('#myTable').DataTable({
            	"columns": [
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