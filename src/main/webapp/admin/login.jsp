<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="../css/style.css">
	<script type="text/javascript" src="jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="jquery.validate.min.js"></script>
<title>Admin Login</title>
</head>
<body>

	<div align = "center">
		<h1>Book Store Administrator</h1>
		<h2>Admin Login</h2>
		<form id="loginForm" action="login" method="post">
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" id="email" size="20"/></td>
					
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" id="password" size="20"/></td>
					
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Login</button>
						<button>Cancel</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
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