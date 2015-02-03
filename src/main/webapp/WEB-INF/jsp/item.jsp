<%@page import="org.apache.taglibs.standard.tag.common.core.OutSupport"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/tablib.jsp"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<security:authorize access="hasRole('ROLE_ADMIN')">
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary btn-lg"
		data-toggle="modal" data-target="#myModal">New Item</button>

	<form:form commandName="file" cssClass="form-horizontal"
		enctype="multipart/form-data">

		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Create new item</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Name:</label>
							<div class="col-sm-10">
								<form:input path="name" cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-10">
								<input type="file" name="file" accept="image/*" />
								<!-- <input name="imageUrl" type="file" size="50" accept="image/*"/> -->
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<input type="submit" class="btn btn-primary" value="create" />
					</div>
				</div>
			</div>
		</div>
	</form:form>
</security:authorize>

<br>
<br>

<c:forEach items="${user.items}" var="item">
	<table class="table table-striped table-hover">
		<tr>
			<td>
				<a href='<spring:url value="/item/${item.name}.html"></spring:url>'><img
					src="index/image.html?id=${item.id}" alt="${item.name}"
					class="img-circle img-thumbnail" /> 
				</a>
			</td>
			<td>${item.name }</td>
		</tr>
	</table>
</c:forEach>