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
                        New Debt for Customer: ${customer.name}
                    </div>
                    <div class="panel-body">
                        <spring:url value="/debts/save" var="saveDebtUrl"/>
                        <form:form class="form-horizontal" method="post" modelAttribute="debt" action="${saveDebtUrl}">
                            <form:hidden path="debtId"/>
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
                            	<spring:url value="/debts/list?cid=${debt.customerId}" var="urlListDebts" />
		                        <div class="col-sm-offset-3 col-sm-9">
		                      	    <button type="submit" class="btn btn-primary">Save A/R Entry</button>
		                      	    
		                      	    <a href="${urlListDebts}" class="btn btn-primary">Cancel</a>
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