<%@page import="org.apache.taglibs.standard.tag.common.core.OutSupport"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/tablib.jsp"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<security:authorize access="hasRole('ROLE_ADMIN')">

	<script type="text/javascript">
		$(document).ready(
				function() {
					$(".triggerRemove").click(
							function(e) {
								e.preventDefault();
								$("#modalRemove .removeBtn").attr("href",
										$(this).attr("href"));
								$("#modalRemove").modal();
							});
				});
	</script>

	<a href="<spring:url value="/item/remove/${item.id}.html"/>"
		class="btn btn-danger triggerRemove">remove item</a>

	<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Remove item</h4>
				</div>
				<div class="modal-body">Really remove?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a href="" class="btn btn-danger removeBtn">Remove</a>
				</div>
			</div>
		</div>
	</div>


	<%-- <!-- Button trigger modal -->
	<button type="button" class="btn btn-primary"
		data-toggle="modal" data-target="#myModal">edit item</button>

	<form:form commandName="itemEdit" cssClass="form-horizontal"
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
						<h4 class="modal-title" id="myModalLabel">Edit Item</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Name:</label>
							<div class="col-sm-10">
								<form:input path="name" cssClass="form-control" value="${item.name}"/>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Price:</label>
							<div class="col-sm-10">
								<form:input path="price" cssClass="form-control" value="${item.price}"/>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Stock:</label>
							<div class="col-sm-10">
								<form:input path="stock" cssClass="form-control" value="${item.stock}"/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-10" align="center">
								<input type="file" name="file" accept="image/*"/>
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
 --%>
</security:authorize>

<br>
<br>

<table >
	<tbody>
		<tr>
			<td>
				<div class="targetarea"
					style="float: left; width: 400px; height: 300px;">
					<img id="multizoom1" alt="zoomable" title=""
						src="/image.html?id=${item.itemImage.id}"
						style="width: 400px; height: 300px;" />
				</div>
			</td>
			<td valign="top" style="padding-left: 30px; width: 100%; height: 100%;" align="center" valign="middle" >
			
			<label style="font-size: 20px" for="name">${item.name}</label> 
				
				<br><br>
				
				<div>
					<table style="width: 100%; height: 100%;">

						<tr>
							<td align="center" valign="middle">
								
								<Label style="font-size: 20px">${item.price} EUR</Label> 
									
								<br><br>
									
								<label style="color: red;">Bei diesem Artikel handelt es sich um ein Einzelstück.</label>
							
							</td>
						</tr>

					</table>
				</div> 
				
				<br><br>

				<div>
					<table style="width: 100%; height: 100%;">

						<tr style="background-color: #D3D3D3;">
							<td align="center" valign="middle">
								
								<label>Wenn dieser Artikel Sie interessiert, schreiben Sie mir bitte eine E-Mail mit dem Namen des Artikels an <a href="mailto:wooff@gmx.de">wooff@gmx.de</a></label>
								
								<p></p>
							</td>
						</tr>
					</table>
				</div></td>
		</tr>
	</tbody>
</table>
<!-- <div class="multizoom1 thumbs">
	<a href="millasmall.jpg" data-large="milla.jpg" data-title="Milla Jojovitch"><img src="milla_tmb.jpg" alt="Milla" title=""/></a> 
	<a href="saleensmall.jpg" data-lens="false" data-magsize="150,150" data-large="saleen.jpg" data-title="Saleen S7 Twin Turbo"><img src="saleen_tmb.jpg" alt="Saleen" title=""/></a> 
	<a href="haydensmall.jpg" data-large="hayden.jpg" data-title="Hayden Panettiere"><img src="hayden_tmb.jpg" alt="Hayden" title=""/></a> 
	<a href="jaguarsmall.jpg" data-large="jaguar.jpg" data-title="Jaguar Type E"><img src="jaguar_tmb.jpg" alt="Jaguar" title=""/></a>
</div> -->

