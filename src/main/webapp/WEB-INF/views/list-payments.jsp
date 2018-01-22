<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <c:if test="${not empty msg}">
                    <div class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <strong>${msg}</strong>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <p>List of payments for ${customer.name}.</p>
                        <p>Running Total: ${paymentsTotal}.
                            <spring:url value="/payments/new?cid=${customer.customerId}" var="newPaymentUrl" />
                            <a href="${newPaymentUrl}" class="btn btn-default pull-right" role="button">New Payment</a>
                        </p>
                    </div>
                    <div class="panel-body">
                        <table id="paymentsTable" class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Date</th>
                                    <th>Remarks</th>
                                    <th>Amount</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="payment" items="${payments}">
                                    <tr>
                                        <td>${payment.createdAt}</td>
                                        <td>${payment.remarks}</td>
                                        <td>${payment.amount}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
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