<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="../layout/tablib.jsp"%>
<%@ include file="/WEB-INF/jsp-btn/item-create.jsp"%>	

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
						<a href='<spring:url value="/item/itemDetails/${item.id}.html"/>'>
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