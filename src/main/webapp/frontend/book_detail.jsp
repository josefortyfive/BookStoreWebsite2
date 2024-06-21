<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>${book.title} Books </title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<h2>${book.title }</h2>
	</div>
	

	<jsp:directive.include file="footer.jsp"/>
	
</body>
</html>