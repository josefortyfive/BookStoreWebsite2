<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<title>Shopping Cart</title>
</head>
<body>

	<jsp:directive.include file="header.jsp"/>
	<div align = "center">
		<h2>Your Shopping Cart Details</h2>
		
			<c:if test="${message != null}">
				<div align = "center">
					<h4 class="message">${message}</h4>
				</div>
			</c:if>

			<c:set var="cart" value="${sessionScope['cart']}"/>
			
			<c:if test="${cart.totalItems == 0 }">
				<h2>There's no item in your cart</h2>
			</c:if>
			
	</div>
	
	<jsp:directive.include file="footer.jsp"/>
	
<script type="text/javascript">


	$(document).ready(function(){
		$("#loginForm").validate({
			rules:{
				email: {
					required: true,
					email: true
				},
				password: "required"
			},
			messages : {
				email: {
					required: "Please enter email",
					email: "Please enter an valid email address"
				},
				password: "Password is required!"
			}
		});
	});

</script>
</body>
</html>