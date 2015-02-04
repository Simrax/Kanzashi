<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/tablib.jsp"%>

<form:form commandName="user" cssClass="form-horizontal">

	<c:if test="${param.success eq true}">
		<div class="alert alert-success">Registration successful!</div>
	</c:if>
	<c:if test="${param.success eq false}">
		<div class="alert alert-danger">Email already exist!</div>
	</c:if>

	<div class="form-group">
		<label for="emailLabel" class="col-sm-2 control-label">Email:</label>
		<div class="col-sm-10">
			<form:input path="email" cssClass="form-control" />
			<form:errors path="email" />
		</div>
	</div>

	<div class="form-group">
		<label for="passwordLabel" class="col-sm-2 control-label">Password:</label>
		<div class="col-sm-10">
			<form:password path="password" cssClass="form-control" />
			<form:errors path="password" />
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-2">
			<input type="submit" value="create" class="btn btn.lg btn-primary">
		</div>
	</div>
</form:form>