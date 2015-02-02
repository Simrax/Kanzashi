<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/tablib.jsp" %>

<c:forEach items="${user.items}" var="item">
	<img src="index/image.html?id=${item.id}" alt="item image" class="img-circle img-thumbnail"/>
</c:forEach>