<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/jquery.rateyo.min.css">
	<title>Write Review - Online book Store</title>
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="js/jquery.rateyo.min.js"></script>
	<title>Customer Login</title>
</head>
<body>

	<jsp:directive.include file="header.jsp"/>
	<div align = "center">
		<form id="reviewForm">
			<table class="normal" width="60%">
				<tr>
					<td><h3>You already wrote a review for this book</h3></td>
					<td>&nbsp;</td>
					<td><h2>${loggedCustomer.fullname}</h2></td>
				</tr>
				<tr>
					<td colspan="3"><hr/></td>
				</tr>
				<tr>
					<td>
						<span id ="book-title">${book.title}</span><br/>
						<img class="book-large" src="data:image/jpg;base64, ${book.base64Image}"/>
					</td>
					<td>
						<div id="rateYo"></div>
						<br/>
						<input type="text" name="headline" size="60" readonly="readonly" value="${review.headline}"/>
						<br/>
						<br/>
						<textarea name="comment" cols="70" rows="10" readonly="readonly">${review.comment}</textarea>
						
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<jsp:directive.include file="footer.jsp"/>
	
<script type="text/javascript">


	$(document).ready(function(){
		
		$("#rateYo").rateYo({
			starWidth: "40px",
			fullStar: true,
			rating: ${review.rating},
			readOnly: true
		});
		
	});

</script>
</body>
</html>