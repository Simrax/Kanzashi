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

	<form:form commandName="item" cssClass="form-horizontal itemForm"
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
	
<script type="text/javascript">
jQuery(document).ready(function($) {
	$(".itemForm").validate(
		{
			rules: {
				name : {
					required : true,
					minlength : 3
				},
				price: {
					required : true,
					number : true
				},
				file: {
					required : true,
					accept : "image/*"
				}
			},
			highlight: function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight: function(element) {
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			},
			messages: {
				email: {
					remote: "Such username already exists!"
				}
			}
		}
	);
});
</script>
	
	
</security:authorize>

<br><br>
 

 <table id="ItemTable" style="width: 100%;" class="table-hover">
	
		<thead>
        <tr>
            <th></th>
            <th></th>
        </tr>
    </thead>
		<tbody>
		<c:forEach items="${items}" var="item">
			<tr>
				<td>
					<div class="modal-body">
						<a href='<spring:url value="/item/itemDetails/${item.name}.html"/>'>
								<img src="/image.html?id=${item.itemImage.id}"
								alt="${item.name}" class="img-circle img-thumbnail" style="width: 200px; height: 150px;"/>
						</a>
					</div>
				</td>
				<td>
					<div class="modal-body">
						<div class="form-group">
							<h1><label style="color: #80BFFF" for="name">${item.name}</label></h1>
						</div>
						<div class="form-group">
							<label style="color: #FA0505; font-size: 20px" for="price">${item.price} EUR</label>
						</div>
					</div> 
				</td>
			</tr>
			</c:forEach>
		</tbody>
	
</table>