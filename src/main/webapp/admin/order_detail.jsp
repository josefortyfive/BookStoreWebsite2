<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Manage Reviews - M.A.'s Bookstore Administration</title>
	<link rel="stylesheet" href="../css/style.css">
	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
</head>
<body>


	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<h1 class="pageheading">Detail of Order ID: ${order.orderId}</h1>
	</div>
	
	<c:if test="${message != null}">
		<div align = "center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>
	
	<div align="center">
		<h2>Order Overview: </h2>
		<table>
			<tr>
				<td><b>Ordered By: </b></td>
				<td>${order.customer.fullname}</td>
				
			</tr>
			<tr>
				<td><b>Book Copies: </b></td>
				<td>${order.bookCopies }</td>
			</tr>
			<tr>
				<td><b>Total Amount: </b></td>
				<td><fmt:formatNumber value="${order.total}" type="currency"/></td>
			</tr>
			<tr>
				<td><b>Receipt Name: </b></td>
				<td>${order.recipientName }</td>
			</tr>
			<tr>
				<td><b>Payment Method: </b></td>
				<td>${order.paymentMethod}</td>
			</tr>
			<tr>
				<td><b>Shipping Address: </b></td>
				<td>${order.shippingAddress}</td>
			</tr>
			<tr>
				<td><b>Order Status: </b></td>
				<td>${order.status }</td>
			</tr>
			<tr>
				<td><b>Order Date: </b></td>
				<td>${order.orderDate }</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<h2>Order Status</h2>
		<table border="1">
			<tr>
				<th>Index</th>
				<th>Book Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Subtotal</th>
			</tr>
			<c:forEach items="${order.orderDetails}" var= "orderDetail" varStatus="status">
			<tr>
				<td>${status.index + 1 }</td>
				<td>${orderDetail.book.title }</td>
				<td>${orderDetail.book.author }</td>
				<td><fmt:formatNumber value="${orderDetail.book.price }" type="currency"/></td>
				<td>${orderDetail.quantity }</td>
				<td><fmt:formatNumber value="${order.total}" type="currency"/></td>
			</tr>
			
			</c:forEach>
			<tr>
				<td colspan="4" align="right">
					<b><i>TOTAL:</i></b>
				</td>
				<td>
					<b>${order.bookCopies}</b>				
				</td>
				<td>
					<b>${order.total}</b>
				</td>
			</tr>
		</table>
	
	</div>
	<div align="center">
		<a href="">Edit this Order</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		<a href="">Delete this order</a>
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