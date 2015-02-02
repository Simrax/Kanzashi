<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/tablib.jsp" %>

<h1>${user.name}</h1>

<c:forEach items="${user.items}" var="item">

	<h1>${item.name}</h1>
	<p>${item.imageUrl}</p>

</c:forEach>