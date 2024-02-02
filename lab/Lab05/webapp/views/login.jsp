<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login Page</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<%--<c:if test="${not empty cookie.username.value and not empty cookie.password.value}">--%>
<%--	<c:set scope="session" value="${cookie.name.value}" var="name"/>--%>
<%--</c:if>--%>
<c:if test="${not empty sessionScope.name}">
	<c:redirect url="/views/products/"/>
</c:if>
<div class="container">
	<div class="row justify-content-center">
		<div class="col-md-6 col-lg-5">
			<h3 class="text-center text-secondary mt-5 mb-3">User Login</h3>
			<form class="border rounded w-100 mb-5 mx-auto px-3 pt-3 bg-light" method="post" action="/user/login">
				<div class="form-group">
					<label for="username">Username</label>
					<input id="username" type="text" class="form-control" placeholder="Username" name="username"
						   value="${cookie.username.value}">
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input id="password" type="password" class="form-control" placeholder="Password" name="password"
						   value="${empty cookie.password.value ? "" : cookie.password.value}">
				</div>
				<div class="form-group form-inline">
					<input id="rememberMe" type="checkbox" class="form-check mr-2" name="rememberMe">
					<label for="rememberMe">Remember username & password</label>
				</div>

				<c:if test="${not empty sessionScope.errorMsg}">
					<div class="form-group text-danger">${sessionScope.errorMsg}</div>
					${sessionScope.remove("errorMsg")}
				</c:if>

				<div class="form-group">
					<button class="btn btn-success px-5">Login</button>
				</div>
				<div class="form-group">
					<p>Forgot password? <a href="#">Click here</a></p>
				</div>
				<div class="form-group">
					<p>Register? <a href="/views/register.jsp">Click here</a></p>
				</div>
			</form>

		</div>
	</div>
</div>

</body>
</html>
