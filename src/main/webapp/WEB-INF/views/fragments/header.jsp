<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Aurora Systems</title>

</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/search" var="urlSearch" />
<spring:url value="/orders/list" var="urlOrders" />
<spring:url value="/reports" var="urlReports" />
<spring:url value="/customers/list" var="urlCustomers" />
<spring:url value="/debts/listAll" var="urlDebt" />
<spring:url value="/orders/daily?d=today" var="urlDaily" />
<spring:url value="/orders/monthly?m=2&y=2018" var="urlMonthly" />
<spring:url value="/container/listAll" var="urlContainer" />
<spring:url value="/help" var="urlHelp" />
<spring:url value="/expenses/list?d=today" var="urlListExpenses" />

<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlCustomers}">Crystal Clear</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right" id="headerNav">
				<li><a href="${urlOrders}">Orders</a></li>
				<li><a href="${urlDaily}">Daily Sales</a>
				<li><a href="${urlMonthly}">Monthly Summary</a>
				<li><a href="${urlContainer}">Container Tracking</a>
				<li><a href="${urlCustomers}">Customers</a></li>
				<li><a href="${urlListExpenses}">Expenses</a></li>
				<li><a href="${urlDebt}">A/R Summary</a></li>
<!-- 				<li class="dropdown"> -->
<!-- 					<a href="#">Reports</a> -->
<!-- 					<span class="caret"></span> -->
<!-- 					<ul class="dropdown-menu"> -->
<%--             			<li><a href="${urlDaily}">Daily Sales</a></li> --%>
<%--             			<li><a href="${urlMonthly}">Monthly Sales</a></li> --%>
<%--             			<li><a href="${urlSearch}">Containers Summary</a></li> --%>
<!--         			</ul> -->
<!-- 				</li> -->
			</ul>
		</div>
	</div>
</nav>
