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
                        Set Container Totals
                    </div>
                    <div class="panel-body">
                        <spring:url value="/customers/updateContainerBalances" var="updateContainerBalancesUrl"/>
                        <p>Please use this feature only when you need to correct container total discrepancies.</p>
                        <form:form class="form-horizontal" method="post" modelAttribute="customerForm" action="${updateContainerBalancesUrl}">
                         	<form:hidden path="customerId"/>
                         	<form:hidden path="type"/>
                         	<form:hidden path="name"/>
                            <spring:bind path="totalRound">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label class="col-sm-3 control-label">Total Round Balance: </label>
                                    <div class="col-sm-9">
                                        <form:input path="totalRound" type="text" class="form-control" id="totalRound" placeholder="Total Round Balance"/>
                                        <form:errors path="totalRound" class="control-label"/>
                                    </div>
                                </div>
                            </spring:bind>
                            
                            <spring:bind path="totalSlim">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label class="col-sm-3 control-label">Total Slim Balance: </label>
                                    <div class="col-sm-9">
                                        <form:input path="totalSlim" type="text" class="form-control" id="totalSlim" placeholder="Total Slim Balance"/>
                                        <form:errors path="totalSlim" class="control-label"/>
                                    </div>
                                </div>
                            </spring:bind>

                            <div class="form-group">
		                        <div class="col-sm-offset-3 col-sm-9">
		                      	    <button type="submit" class="btn btn-primary">Submit</button>
		                        </div>
		                    </div>
                        </form:form>
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
</body>
</html>