<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Order Details - M.A.'s Bookstore Administration</title>
	<link rel="stylesheet" href="../css/style.css">
	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
</head>
<body>


	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<h1 class="pageheading">Book Order Management</h1>
	</div>
	
	<c:if test="${message != null}">
		<div align = "center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>
	
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Order ID</th>
				<th>Ordered By </th>
				<th>Book Copies</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="order" items="${listOrder}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${order.orderId}</td>
					<td>${order.customer.fullname}</td>
					<td>${order.bookCopies}</td>
					<td><fmt:formatNumber value="${order.total}" type="currency"/></td>
					<td>${order.paymentMethod}</td>
					<td>${order.status}</td>
					<td>${order.orderDate }</td>
					<td>
						<a href="view_order?id=${order.orderId}">Detail</a> &nbsp;
						<a href="edit_review?id=${review.reviewId}">Edit</a> &nbsp;
						<a href="javascript:void(0);" class="deleteLink" id="${review.reviewId}">Delete</a>
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
				reviewId= $(this).attr("id");
				if (confirm('Are you sure you want to delete the review with ID ' +reviewId + '? ')){
					window.location = 'delete_review?id=' +reviewId;
				}
			});
		});
	});

</script>
</body>
</html>