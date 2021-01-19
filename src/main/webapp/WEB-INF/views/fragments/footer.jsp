<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container">
	<hr>
	<footer>
		<div class="row">
			<div class="col-sm-6">
				<p class="pull-left">&copy; Aurora Systems v3.0</p>
			</div>
			<div class="col-sm-offset-5 col-sm-1 pull-right">
				<spring:url var="logoutUrl" value="/logout" />
				<form class="form-inline" action="${logoutUrl}" method="post">
					<input type="submit" value="Log out" /> <input type="hidden"
						name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		</div>
	</footer>
</div>


