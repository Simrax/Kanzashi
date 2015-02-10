<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/tablib.jsp"%>

<c:if test="${fail eq false}">
		<div class="alert alert-danger">Artikel Erstellung vergeschlagen. Felder müssen richtig ausgefüllt werden.</div>
</c:if>

<script type="text/javascript">
jQuery(document).ready(function($) {
	
	$("#commentWrapper").hide();
	
	$("#itemRemoveId").click(
		function(e) {
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href",
					$(this).attr("href"));
			$("#modalRemove").modal();
	});
	
	$("#deleteImageButton").click(function(){
		$("#ImageDivId").remove();
		$('#fileUploadId').get(0).type = 'file';
	});
});
</script>

<security:authorize access="hasRole('ROLE_ADMIN')">

	<a href="<spring:url value="/item/remove/${item.id}.html"/>" id="itemRemoveId"
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

<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary"
		data-toggle="modal" data-target="#myModal">edit item</button>

	<form class="form-horizontal itemForm" action="/item/editDetails/${item.id}.html" method="post" enctype="multipart/form-data">

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
								<input type="hidden" name="id" value="${item.id}" />
								<input name="name" class="form-control" value="${item.name}"/>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Preis:</label>
							<div class="col-sm-10">
								<input name="price" type="number" class="form-control" value="${item.price}"/>
							</div>
						</div>
						<div class="form-group">
							<label for="category" class="col-sm-2 control-label">Kategorie:</label>
							<div class="col-sm-10">
   								<select name="categoryId" class="form-control">
       								<c:forEach items="${categorys}" var="category">
       									<option value="${category.id}">${category.name}</option>
       								</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Beschr.:</label>
							<div class="col-sm-10">
								<textarea name="description" cols="63" rows="15">${item.description}</textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-10" align="center">
								<input id="fileUploadId" type="hidden" name="file" accept="image/*"/>
							</div>
						</div>
						<div class="form-group" id="ImageDivId">
							<div class="col-sm-10" align="center">
								<img src="/image.html?id=${item.itemImage.id}" style="width: 200px; height: 150px;" />
								<button id="deleteImageButton" class="btn btn-danger">Bild löschen</button>
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
	</form>
	
<script type="text/javascript">
jQuery(document).ready(function($) {
	
	$(".itemForm").validate(
		{
			rules: {
				name : {
					required : true,
					minlength : 3
				},
				description: {
					required : true,
					minlength : 1
				},
				price: {
					required : true,
					number : true
				},
				file: {
					required : true,
					accept : "image/*"
				},
				categoryId: {
		            required: function(element) {
		                return $("#categoryId").val() != '';
		            },
		            minlength : 1
		        }
			},
			highlight: function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight: function(element) {
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			},
			messages: {
				file: "Dieses Feld ist erforderlich",
				name: "Name muss mindestens 3 Zeichen lang sein",
				price: "Dieses Feld ist erforderlich",
				categoryId: "Kategorie muss ausgewählt sein",
				description: "Beschreibung muss mindestens 1 Zeichen lang sein"
			}
		}
	);
});
</script>
</security:authorize>