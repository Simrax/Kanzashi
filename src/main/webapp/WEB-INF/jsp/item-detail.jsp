<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/tablib.jsp"%>
<%@ include file="/WEB-INF/jsp-btn/itemDetail-editAndRemove.jsp"%>	
<br>
<br>

<table >
	<tbody>
		<tr>
			<td>
				<div class="targetarea" style="float: left; width: 400px; height: 300px;">
					<img id="multizoom1" alt="zoomable" class="img-responsive" src="/image.html?id=${item.itemImage.id}"
						style="width: 400px; height: 300px;" />
				</div>
			</td>
			<td class="row col-md-4" style="padding-left: 30px; width: 100%; height: 100%;">
				
				<h1 style="font-size: 20px">${item.name}</h1> 
				
				
				<br><br>
				
				<div>
					<table style="width: 100%; height: 100%;">

						<tr>
							<td>
								
								<Label style="font-size: 20px">Preis: ${item.price} EUR</Label> 
									
								<br><br>
									
								<label style="color: red;">Bei diesem Artikel handelt es sich um ein Einzelstück.</label>
								
								<br><br>
								
								<label>Wenn dieser Artikel Sie interessiert, schreiben Sie mir bitte eine E-Mail mit dem Namen des Artikels an <a href="mailto:">${item.customer.email}</a></label>
								
							</td>
						</tr>

					</table>
				</div> 
				
				<br><br>

				<!-- <div>
					<table style="width: 100%; height: 100%;">

						<tr style="background-color: #D3D3D3;">
							<td align="center" valign="middle">
								
								<label>Wenn dieser Artikel Sie interessiert, schreiben Sie mir bitte eine E-Mail mit dem Namen des Artikels an <a href="mailto:wooff@gmx.de">wooff@gmx.de</a></label>
								
								<p></p>
							</td>
						</tr>
					</table>
				</div> -->
			</td>
		</tr>
	</tbody>
</table>

<br><br>
<c:if test="${item.description != null}">
	<h1 class="jumbotron" align="center">Beschreibung:</h1>
	
	<label>${item.description}</label>
	
	<br><br>
</c:if>

<!-- <div class="multizoom1 thumbs">
	<a href="millasmall.jpg" data-large="milla.jpg" data-title="Milla Jojovitch"><img src="milla_tmb.jpg" alt="Milla" title=""/></a> 
	<a href="saleensmall.jpg" data-lens="false" data-magsize="150,150" data-large="saleen.jpg" data-title="Saleen S7 Twin Turbo"><img src="saleen_tmb.jpg" alt="Saleen" title=""/></a> 
	<a href="haydensmall.jpg" data-large="hayden.jpg" data-title="Hayden Panettiere"><img src="hayden_tmb.jpg" alt="Hayden" title=""/></a> 
	<a href="jaguarsmall.jpg" data-large="jaguar.jpg" data-title="Jaguar Type E"><img src="jaguar_tmb.jpg" alt="Jaguar" title=""/></a>
</div> -->

