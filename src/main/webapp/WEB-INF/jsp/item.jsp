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

	<form:form commandName="item" cssClass="form-horizontal"
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
							<label for="name" class="col-sm-2 control-label">Price:</label>
							<div class="col-sm-10">
								<form:input path="price" cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Stock:</label>
							<div class="col-sm-10">
								<form:input path="stock" cssClass="form-control" />
							</div>
						</div>
						<div class="form-group" align="center">
							<div class="col-sm-10">
								<input type="file" name="file" accept="image/*" />
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



<c:forEach items="${items}" var="item">
	<table style="width: auto; display: inline-table; margin-right: 10px; margin-bottom: 10px;" class="table table-hover">
		<tbody>
			<tr>
				<td>
					<div class="modal-body">
						<a href='<spring:url value="/item/${item.name}.html"/>'>
								<img src="index/image.html?id=${item.itemImage.id}"
								alt="${item.name}" class="img-circle img-thumbnail" />
						</a>
					</div>
				</td>
				<td>
					<div class="modal-body">
						<div class="form-group">
							<label style="color:#80BFFF" for="name">${item.name}</label>
						</div>
						<div class="form-group">
							<label style="color:#FA0505" for="price">${item.price} EUR</label>
						</div>
						<div class="form-group">
							<c:if test="${item.stock <= 5}">
								<label style="color:#FA0505" for="onlyStock">only ${item.stock} in stock</label>
							</c:if>
							<c:if test="${item.stock > 5}">
								<label style="color:#FAC105" for="stillStock">still ${item.stock} stock</label>
							</c:if>
						</div>
					</div>
				
				<%-- 
					<h1><label style="color:#80BFFF" for="name" class="col-sm-2 control-label">${item.name}</label></h1>
					
					
					
					<font color="RED"><b>${item.price} EUR</b></font> 
					<br><br> 
					<c:if test="${item.stock <= 5}">
						<font color="RED">Only ${item.stock} in stock</font>
					</c:if> <c:if test="${item.stock > 5}">
						still ${item.stock} stock
					</c:if> --%>
				</td>
			</tr>
		</tbody>
	</table>
</c:forEach>

