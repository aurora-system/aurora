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
                        New Payment for Customer: ${customer.name}
                    </div>
                    <div class="panel-body">
                        <spring:url value="/payments/save" var="savePaymentUrl"/>
                        <form:form class="form-horizontal" method="post" modelAttribute="payment" action="${savePaymentUrl}">
                            <form:hidden path="paymentId"/>
                            <form:hidden path="customerId"/>
                            <spring:bind path="createdAt">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label class="col-sm-3 control-label">Date: </label>
                                    <div class="col-sm-9">
                                        <form:input path="createdAt" type="date" class="form-control" id="createdAt" placeholder="Date"/>
                                        <form:errors path="createdAt" class="control-label"/>
                                    </div>
                                </div>
                            </spring:bind>
                            <spring:bind path="remarks">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label class="col-sm-3 control-label">Remarks: </label>
                                    <div class="col-sm-9">
                                        <form:input path="remarks" type="text" class="form-control" id="remarks" placeholder="Description"/>
                                        <form:errors path="remarks" class="control-label"/>
                                    </div>
                                </div>
                            </spring:bind>
                            <spring:bind path="amount">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label class="col-sm-3 control-label">Amount in Php: </label>
                                    <div class="col-sm-9">
                                        <form:input path="amount" type="text" class="form-control" id="amount" placeholder="Amount"/>
                                        <form:errors path="amount" class="control-label"/>
                                    </div>
                                </div>
                            </spring:bind>
                            <div class="form-group">
		                        <div class="col-sm-offset-3 col-sm-9">
		                      	    <button type="submit" class="btn btn-primary">Log Payment</button>
		                        </div>
		                    </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/datatables.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/datatables.min.css"/>"/>
</body>
</html>