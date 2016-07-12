<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.plantronics.scrape.sfdc.KbArticle"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/foundation.css"/>
<title>Plantronics</title>
</head>
<body>
<div class="container">
	<h1>Find Product Info</h1>
	<div class="row large-4 column">
		<form action="productInfo.jsp" method="POST">
			Enter URL:<br>
			<input type="text" name="url"><br><br><br>
			<input type="submit" value="submit">
		
		</form>
	</div>
</div>
</body>
</html>
