<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Manage Categories - M.A.'s Bookstore Administration</title>
	<link rel="stylesheet" href="../css/style.css">
	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<h1 class="pageheading">Category Management</h1>
		<h3><a href="category_form.jsp">Create New Category</a></h3>
	</div>
	
	<c:if test="${message != null}">
		<div align = "center">
			<h4 class="message">${message }</h4>
		</div>
	</c:if>
	
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Category Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="category" items="${listCategory}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${category.categoryId}</td>
					<td>${category.name}</td>
					<td>
						<a href="edit_category?id=${category.categoryId}">Edit</a>
						<a href="javascript:void(0);" class="deleteLink" id="${category.categoryId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp"/>

<script>
$(document).ready(function(){
	$(".deleteLink").each(function(){
		$(this).on("click", function(){
			categoryId= $(this).attr("id");
			if (confirm('Are you sure you want to delete the Category with ID ' +categoryId + '? ')){
				window.location = 'delete_category?id=' +categoryId;
			}
		});
	});
});

</script>
</body>
</html>