<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv ="Content-type" content="text/html; "charset="ISO-8859-1">
	<title>Create New Customer</title>
	<link rel="stylesheet" href="../css/style.css">	
	<link rel="stylesheet" href="../css/jquery-ui.min.css">
	<link rel="stylesheet" href="../css/jquery-ui.css">
	
	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${customer !=null}">
				Edit Customer
			</c:if>
			<c:if test="${customer == null}">
				Create New Customer
			</c:if>
		</h2>
	</div>
	
	
	<div align="center">
		<c:if test="${customer !=null}">
			<form action="update_customer" method="post" id="customerForm" enctype="multipart/form-data">
			<input type="hidden" name="bookId" value="${customer.customerId}">
		</c:if>
		<c:if test="${customer == null}">
			<form action="create_customer" method="post" id="customerForm" enctype="multipart/form-data">
		</c:if>
		<table class="form">
			<tr>
				<td align="right">Email:</td>
				<td align="left"><input type="text" id ="email" name="email" size="45" value="${customer.email}"/> </td>
			</tr>
			<tr>
				<td align="right">Full Name:</td>
				<td align="left"><input type="text" id="fullname" name="fullname" size="45" value="${customer.fullname}"/> </td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input type="password" id="password" name="password" size="45" value="${customer.password}"/> </td>
			</tr>
			<tr>
				<td align="right">Confirm Password:</td>
				<td align="left"><input type="password" id="confirmPassword" name="confirmPassword" size="45" value="${customer.password}"/> </td>
			</tr>
			<tr>
				<td align="right">Phone Number:</td>
				<td align="left"><input type="text" id="phone" name="phone" size="45" value="${customer.phone}"/> </td>
			</tr>
			<tr>
				<td align="right">Address:</td>
				<td align="left"><input type="text" id="address" name="address" size="45" value="${customer.address}"/> </td>
			</tr>
			<tr>
				<td align="right">City:</td>
				<td align="left"><input type="text" id="city" name="city" size="45" value="${customer.city}"/> </td>
			</tr>
			<tr>
				<td align="right">Zip Code:</td>
				<td align="left"><input type="text" id="zipcode" name="zipcode" size="45" value="${customer.zipcode}"/> </td>
			</tr>
			<tr>
				<td align="right">Country:</td>
				<td align="left"><input type="text" id="country" name="country" size="45" value="${customer.country}"/> </td>
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
				password: "required",
				confirmPassword: {
					required : true,
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
				password: "Please enter your password",
				confirmPassword:{
					required: "Please confirm password",
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