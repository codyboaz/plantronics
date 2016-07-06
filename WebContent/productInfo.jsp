
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.plantronics.com.scrape.sfdc.KbArticle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/foundation.css"/>
<title>Plantronics</title>
</head>
<body>
<div class="container">
	
	<div class="text-center">
		<h1>Support Knowledge Base</h1>
	</div>
	
	<jsp:useBean id="scrape" class="com.plantronics.com.scrape.sfdc.KbArticle" scope="request" />
	
	<jsp:setProperty name="scrape" property="url" value='<%=request.getParameter("url") %>' />
	
	<c:set var="children" value="${scrape.children}" scope="request"/>
	
	<div class="large-12 columns">

	<form>
		<fieldset>
			<legend>
				<c:set var="searchTitle" value="${scrape.searchTitle}" scope="request" />
				<c:out value="${searchTitle}"/>
			</legend>
			<div class="row">
				<div class="large-4 medium-6 columns">
					<c:set var="articleType" value="${scrape.articleType}" scope="request"/>
					<c:out value="${articleType}" escapeXml="false"/>
				</div>
				<div class="large-4 medium-6 columns">
					<c:set var="familyType" value="${scrape.familyType}" scope="request"/>
					<c:out value="${familyType}" escapeXml="false"/>
				</div>	
		
				<div class="large-4 medium-12 columns">
					<c:set var="keywordSearch" value="${scrape.keywordSearch}" scope="request"/>
					<c:out value="${keywordSearch}" escapeXml="false"/>
				</div>
	        </div>
	        <div class = "row">
	        	<input class="alert button small" type="submit" value="Submit" style="margin-left:22px">
	        </div>
	    </fieldset>
	</form>
	</div>
	<br><br>
		
		
	<div class="large-6 medium 12 columns">
	<c:choose>
		<c:when test="${fn:length(children) eq 3}">
			<Strong>ID:</Strong><br>
			<c:set var="id" value="${scrape.id}" scope="request"/>
			<c:out value="${id}" /><br><br>
			
			<strong>Title:</strong><br>
			<c:set var="title" value="${scrape.title}" scope="request"/>
			<c:out value="${title}"/><br><br>
			
			<strong>Content:</strong><br>
			<c:set var="content" value="${scrape.content}" scope="request"/>
			<c:out value="${content}" escapeXml="false"/><br><br>


			<strong>Search Results:</strong><br>
			<c:set var="searchResults" value="${scrape.searchResults}" scope="request"/>
			<c:out value="${searchResults}" escapeXml="false"/><br><br>
		</c:when>
		
		<c:when test="${fn:length(children) eq 4}">
			<Strong>ID:</Strong><br>
			<c:set var="id" value="${scrape.id}" scope="request"/>
			<c:out value="${id}" /><br><br>
			
			<strong>Title:</strong><br>
			<c:set var="title" value="${scrape.title}" scope="request"/>
			<c:out value="${title}"/><br><br>
			
			<strong>Content:</strong><br>
			<c:set var="content" value="${scrape.content}" scope="request"/>
			<c:out value="${content}" escapeXml="false"/><br><br>
			
			<strong>PDF:</strong><br>
			<c:set var="pdf" value="${scrape.pdf}" scope="request"/>
			<c:out value="${pdf}" escapeXml="false"/><br><br>
					
			<strong>Search Results:</strong><br>
			<c:set var="searchResults" value="${scrape.searchResults}" scope="request"/>
			<c:out value="${searchResults}" escapeXml="false"/><br><br>
		</c:when>
		
		<c:when test="${fn:length(children) eq 5}">
			<Strong>ID:</Strong><br>
			<c:set var="id" value="${scrape.id}" scope="request"/>
			<c:out value="${id}" /><br><br>
			
			<strong>Title:</strong><br>
			<c:set var="title" value="${scrape.title}" scope="request"/>
			<c:out value="${title}"/><br><br>
			
			<strong>Question:</strong><br>
			<c:set var="content" value="${scrape.content}" scope="request"/>
			<c:out value="${content}" escapeXml="false"/><br><br>
			
			<strong>Content Title:</strong><br>
			<c:set var="title2" value="${scrape.title2}" scope="request"/>
			<c:out value="${title2}"/><br><br>
			
			<strong>Content:</strong><br>
			<c:set var="content2" value="${scrape.content2}" scope="request"/>
			<c:out value="${content2}" escapeXml="false"/><br><br>
					
			<strong>Search Results:</strong><br>
			<c:set var="searchResults" value="${scrape.searchResults}" scope="request"/>
			<c:out value="${searchResults}" escapeXml="false"/><br><br>
		</c:when>

	
		<c:when test="${fn:length(children) eq 6}">
			<Strong>ID:</Strong><br>
			<c:set var="id" value="${scrape.id}" scope="request"/>
			<c:out value="${id}" /><br><br>
			
			<strong>Title:</strong><br>
			<c:set var="title" value="${scrape.title}" scope="request"/>
			<c:out value="${title}"/><br><br>
			
			<strong>Content Title:</strong><br>
			<c:set var="questTitle" value="${scrape.questionTitle}" scope="request"/>
			<c:out value="${questTitle}"/><br><br>
			
			<strong>Content:</strong><br>
			<c:set var="questContent" value="${scrape.questionContent}" scope="request"/>
			<c:out value="${questContent}" escapeXml="false"/><br><br>
			
			<strong>Content Title:</strong><br>
			<c:set var="answerTitle" value="${scrape.answerTitle}" scope="request"/>
			<c:out value="${answerTitle}"/><br><br>
			
			<strong>Content:</strong><br>
			<c:set var="answerContent" value="${scrape.answerContent}" scope="request"/>
			<c:out value="${answerContent}" escapeXml="false"/><br><br>
					
			<strong>Search Results:</strong><br>
			<c:set var="searchResults" value="${scrape.searchResults}" scope="request"/>
			<c:out value="${searchResults}" escapeXml="false"/><br><br>
		</c:when>
	</c:choose>
	</div>
	<div class="large-12 columns">
		<c:set var="test" value="${fn:length(children)}" />
		<c:out value="${test}" />
	</div>
</div>
</body>
</html>