<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Evergreen Books </title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<h3>New Books:</h3>
			<div align="center" style="width:80%; margin: 0 auto; ">
				<c:forEach items="${listNewBook}" var="book">
					<div style="display:inline-block; margin: 20px">
						<div>
							<a href="view_book?id=${book.bookId}">
								<img src="data:image/jpg;base64, ${book.base64Image}" width="120" height="180"/>
							</a>
							
						</div>
						<div>
							<a href="view_book?id=${book.bookId}">
								<b>${book.title}</b>
							</a>
						</div>
						<div>Rating</div>
						<div><i>by ${book.author }</i></div>
						<div><b>$ ${book.price }</b></div>
						</div>
				
				</c:forEach>
			</div>
		<div align="center" style="clear:both">	
			<h3>Best-Selling Books: </h3>
		</div>
		<div align="center" style="clear:both">
			<h3>Most-favored Books </h3>
		</div>
		<br/><br/>
	</div>
	

	<jsp:directive.include file="footer.jsp"/>
	
</body>
</html>