<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/tablib.jsp"%>

<style>
.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>

<c:if test="${sessionScope[\"SPRING_SECURITY_LAST_EXCEPTION\"].message eq 'Bad credentials'}">
<div class="alert alert-danger">Email/Password are incorrect.</div>
</c:if>
<c:if test="${sessionScope[\"SPRING_SECURITY_LAST_EXCEPTION\"].message eq 'User is disabled'}">
<div class="alert alert-danger">Your account is disabled, please contact administrator.</div>
</c:if>
<c:if test="${fn:containsIgnoreCase(sessionScope[\"SPRING_SECURITY_LAST_EXCEPTION\"].message,'A communications error has been detected')}">
<div class="alert alert-danger">Database connection is down, try after sometime.</div>
</c:if>

<form class="form-signin"
	action='<spring:url value="/j_spring_security_check"/>' method="POST">
	<h2 class="form-signin-heading">Please sign in</h2>
	<label for="inputName" class="sr-only">Email</label> 
	<input type="text" name="j_username" id="inputName" class="form-control" placeholder="Email" required autofocus> 
	<label for="inputPassword" class="sr-only">Password</label>
	<input type="password" name="j_password" id="inputPassword" class="form-control" placeholder="Password" required>
	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
</form>
