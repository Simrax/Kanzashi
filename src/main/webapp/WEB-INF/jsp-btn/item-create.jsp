<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/tablib.jsp"%>

<c:if test="${fail eq false}">
		<div class="alert alert-danger">Artikel Erstellung vergeschlagen. Felder müssen richtig ausgefüllt werden.</div>
</c:if>

<security:authorize access="hasRole('ROLE_ADMIN')">
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary btn-lg"
		data-toggle="modal" data-target="#myModal">Artikel erstellen</button>

	<form:form commandName="item" cssClass="form-horizontal itemForm" enctype="multipart/form-data">
 
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
						<h4 class="modal-title" id="myModalLabel">Erstellung eines neuen Artikels</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Name:</label>
							<div class="col-sm-10">
								<form:input path="name" cssClass="form-control" />
								<form:errors path="name"/>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Preis:</label>
							<div class="col-sm-10">
								<form:input path="price" type="number" step="0.01" cssClass="form-control"/>
								<form:errors path="price"/>
							</div>
						</div>
						<div class="form-group">
							<label for="category" class="col-sm-2 control-label">Kategorie:</label>
							<div class="col-sm-10">
   								<select name="categoryId" class="form-control">
   									<option value="">Wähle eine Kategorie</option> 
       								<c:forEach items="${categorys}" var="category">
       									<option value="${category.id}">${category.name}</option>
       									<form:errors path="categoryId"/>
       								</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Beschr.:</label>
							<div class="col-sm-10">
								<textarea name="description" cols="63" rows="15"></textarea>
							</div>
						</div>
						<div class="form-group" align="center">
							<div class="col-sm-10">
								<form:input type="file" path="file" accept="image/*" />
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