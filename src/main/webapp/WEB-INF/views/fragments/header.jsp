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
<spring:url value="/containers" var="urlContainer" />
<spring:url value="/help" var="urlHelp" />

<spring:url value="/expenses/list?d=today" var="urlListExpenses" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlCustomers}">Crystal Clear</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlOrders}">Orders</a></li>
				<li class="active"><a href="${urlDaily}">Daily Sales</a>
				<li class="active"><a href="${urlCustomers}">Customers</a></li>
				<li class="active"><a href="${urlListExpenses}">Expenses</a></li>
				<li class="active"><a href="${urlDebt}">Debt Summary</a>
				<li class="active"><a href="${urlHelp}">Help</a>
<%-- 				<li class="active"><a href="${urlContainer}">Container Tracking</a> --%>
<!-- 					<ul class="submenu"> -->
<%--             			<li><a href="${urlSearch}">Daily Sales</a></li> --%>
<%--             			<li><a href="${urlSearch}">Debt Report</a></li> --%>
<%--             			<li><a href="${urlSearch}">Containers Summary</a></li> --%>
<!--         			</ul> -->
				</li>
			</ul>
		</div>
	</div>
</nav>
