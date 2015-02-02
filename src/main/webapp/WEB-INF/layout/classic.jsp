<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
</head>
<body>

<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx"%>

<tilesx:useAttribute name="current"/>

	<div class="container">

		<!-- Static navbar -->
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<spring:url value="/"/>">Kanzashi</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="${current == 'index' ? 'active' : ''}"><a href='<spring:url value="/"/>'>Home</a></li>
						<security:authorize access="hasRole('ROLE_ADMIN')">
							<li class="${current == 'users' ? 'active' : ''}"><a href='<spring:url value="/users.html"/>'>Users</a></li>					
						</security:authorize>
						<security:authorize access="isAuthenticated()">
							<li class="${current == 'users' ? 'active' : ''}"><a href='<spring:url value="/account.html"/>'>My account</a></li>
						</security:authorize>
						<li><a href="#">About</a></li>
						<li><a href="#">Contact</a></li>
						<!-- <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="#">Action</a></li>
                  <li><a href="#">Another action</a></li>
                  <li><a href="#">Something else here</a></li>
                  <li class="divider"></li>
                  <li class="dropdown-header">Nav header</li>
                  <li><a href="#">Separated link</a></li>
                  <li><a href="#">One more separated link</a></li>
                </ul>
              </li> -->
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="${current == 'register' ? 'active' : ''}"><a href='<spring:url value="/register.html"/>'>Register</a></li>
						<security:authorize access="!isAuthenticated()">
							<li class="${current == 'login' ? 'active' : ''}"><a href='<spring:url value="/login.html"/>'>Login</a></li>
						</security:authorize>
						<security:authorize access="isAuthenticated()">
							<li><a href='<spring:url value="/j_spring_security_logout"/>'>Logout</a></li>
						</security:authorize>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>

		<tiles:insertAttribute name="body" />

		<br> <br>

		<center>
			<tiles:insertAttribute name="footer" />
		</center>
	</div>
</body>
</html>