<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<title>TestCenter - ${company.companyName}</title>
		<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
	</head>
	<body >
		
	<div id="container">
	<div id="header">
		<a href="index.html"><img src="images/title.png"></a>		
	</div>	
	<div >
		<ul id="menu">					
			<li><span class="current">Home</span></li>
			<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">			
			<li><span><a href="viewtests/viewtestplan.html">Test Plans</a></span></li>			
			</security:authorize>
			<!-- <li><span><a href="testconfig/newtestplan.html">New Test Plan</a></span></li>	 -->				
		</ul>
	</div>
	<div id="content">				
		<c:if test="${fn:length(errormessage) > 0}"> 				
		 		<font color="red">${errormessage}</font> 
		 	<hr>
		</c:if>
		<c:if test="${fn:length(sucessmessage) > 0}">  				
		 		<font color="green">${sucessmessage}</font> 
		 	<hr>
		</c:if>			
	</div>
	<div id="footer">
		Copyright © Testcenter, 2012
	</div>
</div>	
	<div id="preload">
		<img src="../images/title.png">				
	</div>
	</body>
</html>