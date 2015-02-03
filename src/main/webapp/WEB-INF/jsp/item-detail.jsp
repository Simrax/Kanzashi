<%@page import="org.apache.taglibs.standard.tag.common.core.OutSupport"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/tablib.jsp"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<h1>${item.name}</h1>

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

</security:authorize>
hier item details

