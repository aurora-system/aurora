<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Aurora Systems</title>

<spring:url value="/resources/css/hello.css" var="coreCss" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/search" var="urlSearch" />
<spring:url value="/orders/list" var="urlOrders" />
<spring:url value="/reports" var="urlReports" />
<spring:url value="/customers/list" var="urlCustomers" />
<spring:url value="/customers/new" var="urlAddUser" />

<spring:url value="/expenses/list?d=today" var="urlListExpenses" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">Aurora Spring</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlSearch}">Search</a></li>
				<li class="active"><a href="${urlOrders}">Orders</a></li>
				<li class="active"><a href="${urlReports}">Reports</a></li>
				<li class="active"><a href="${urlCustomers}">Customers</a></li>
<%-- 				<li class="active"><a href="${urlAddUser}">New Customer</a></li> --%>
				<li><a href="${urlListExpenses}">Expenses</a></li>
			</ul>
		</div>
	</div>
</nav>