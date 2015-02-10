<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/tablib.jsp"%>

<security:authorize access="hasRole('ROLE_ADMIN')">

<script type="text/javascript">
jQuery(document).ready(function($) {
	$("#categoryRemoveId").click(
		function(e) {
			e.preventDefault();
			$("#categoryRemove").modal();
	});
	
	$("#categoryRemoveBtnId").click(function(){
		var link = $('#categoryCombo :selected').val() + ".html";
		if($('#categoryCombo :selected').val() != undefined){
			$("#categoryRemove .removeBtn").attr("href",$(".triggerRemove").attr("href") + link);
		}
		
	});
});
</script>

<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary"
		data-toggle="modal" data-target="#categoryModel">Kategorie erstellen</button>

	<form:form class="form-horizontal" action="/categoryEdit.html" method="post" enctype="multipart/form-data">

		<!-- Modal -->
		<div class="modal fade" id="categoryModel" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Neue Kategorie Erstellen</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Kategorie:</label>
							<div class="col-sm-10">
								<input name="name" class="form-control"/>
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
	
	<br>
	
	<a href="<spring:url value="/item/category/remove/"/>" id="categoryRemoveId" class="btn btn-danger triggerRemove">Kategorie löschen</a>

	<div class="modal fade" id="categoryRemove" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Wähle eine Kategorie aus, die gelöscht werden soll</h4>
					<div class="modal-body">
					<div class="form-group">
						<label>
							Bedenke: Wenn eine Kategorie, Artikel beinhaltet, werden mit der löschung der Kategorie 
							ebenfalls alle Artikel, die in Verbindung mit der Kategorie stehen, gelöscht.
						</label>
					</div>
					<div class="form-group">
							<label for="category" class="col-sm-2 control-label">Kategorie:</label>
							<div class="col-sm-10">
   								<select name="categoryId" id="categoryCombo" class="form-control">
       								<c:forEach items="${categorys}" var="category">
       									<option value="${category.id}">${category.name}</option>
       								</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">abbrechen</button>
					<a id="categoryRemoveBtnId" class="btn btn-danger removeBtn">löschen</a>
				</div>
			</div>
		</div>
	</div>
</security:authorize>