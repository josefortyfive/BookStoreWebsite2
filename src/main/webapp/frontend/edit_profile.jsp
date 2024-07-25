<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv ="Content-type" content="text/html; "charset="ISO-8859-1">
	<title>Register New Customer</title>
	
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<h2 class="pageheading">
			Edit My Profile
		</h2>
	</div>
	
	
	<div align="center">
		<form action="update_profile" method="post" id="customerForm">

		<table class="form">
			<tr>
				<td align="right">Email:</td>
				<td align="left"><b>${loggedCustomer.email} (Cannot be changed)</b></td>
			</tr>
			<tr>
				<td align="right">Full Name:</td>
				<td align="left"><input type="text" id="fullname" name="fullname" size="45" value="${loggedCustomer.fullname}"/> </td>
			</tr>
			
			<tr>
				<td align="right">Phone Number:</td>
				<td align="left"><input type="text" id="phone" name="phone" size="45" value="${loggedCustomer.phone}"/> </td>
			</tr>
			<tr>
				<td align="right">Address:</td>
				<td align="left"><input type="text" id="address" name="address" size="45" value="${loggedCustomer.address}"/> </td>
			</tr>
			<tr>
				<td align="right">City:</td>
				<td align="left"><input type="text" id="city" name="city" size="45" value="${loggedCustomer.city}"/> </td>
			</tr>
			<tr>
				<td align="right">Zip Code:</td>
				<td align="left"><input type="text" id="zipcode" name="zipcode" size="45" value="${loggedCustomer.zipcode}"/> </td>
			</tr>
			<tr>
				<td align="right">Country:</td>
				<td align="left"><input type="text" id="country" name="country" size="45" value="${loggedCustomer.country}"/> </td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<i>(Leave password fields blank if you don't want to change the password)</i>
				</td>
			</tr>
			<tr>
				<td align="right">New Password:</td>
				<td align="left"><input type="password" id="password" name="password" size="45" value="${customer.password}"/> </td>
			</tr>
			<tr>
				<td align="right">Confirm New Password:</td>
				<td align="left"><input type="password" id="confirmPassword" name="confirmPassword" size="45" value="${customer.password}"/> </td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td colspan ="2" align="center">
					<button type="submit">Save</button> &nbsp;&nbsp;&nbsp;
					<button id="buttonCancel">Cancel</button>
				</td>
			</tr>
		</table>	
		</form>	
	</div>
	<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript">


	$(document).ready(function(){
		
		$("#customerForm").validate({
			rules:{
				email: {
					required: true,
					email: true
				},
				fullname: "required",
				confirmPassword: {
					equalTo : "#password"
				},
				phone: "required",
				address:"required",
				city: "required",
				zipcode: "required",
				country: "required",
			},
			messages : {
				email: {
					required: "Please enter email address",
					email: "Please enter a valid email address"
				},
				
				fullname: "Please enter your full Name",
				
				confirmPassword:{
					equalTo: "Confirm password does not match password"
				} ,
				phone: "Please enter your phone number",
				address:"Please enter your address",
				city: "Please enter your City",
				zipcode: "Please enter zip code",
				country: "Please enter your country",
			}
		});
		
		$("#buttonCancel").click(function(){
			history.back();
		});
	});
</script>

</html>