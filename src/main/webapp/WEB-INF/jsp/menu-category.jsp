<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/tablib.jsp"%>
<%@ include file="/WEB-INF/jsp-btn/category-createAndRemove.jsp"%>
<br><br>

<div id='cssmenu'>
<ul>
	<li><a href='<spring:url value="/"></spring:url>'><span>Alle Artikel</span></a></li>
	<c:forEach items="${categorys}" var="category">
		<li><a href='<spring:url value="/item/${category.name}.html?id=${category.id}"></spring:url>'><span><c:out value="${category.name}"/></span></a></li>
	</c:forEach>
</ul>
</div>


