<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="css/style.css">
	<title>Write Review - Online book Store</title>
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<title>Customer Login</title>
</head>
<body>

	<jsp:directive.include file="header.jsp"/>
	<div align = "center">
		<form>
			<table class="normal">
				<tr>
					<td><h2>You Reviews</h2></td>
					<td>${loggedCustomer.fullname}</td>
				</tr>
			</table>
		</form>
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