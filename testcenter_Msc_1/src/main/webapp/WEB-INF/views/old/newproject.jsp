<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<title>TestCenter - New Project</title>
		<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
	</head>
	<body>		
<div id="container">
	<div id="header">
		<a href="index.html"><img src="images/title.png"></a>	
	</div>
	<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
		<div id="loggedin">
			<security:authentication property="principal.username"/>
		</div>
	</security:authorize>
	<div >
		<ul id="menu">		
			<li><span><a href="index.html">Home</a></span></li>				
			<li><span><a href="viewcompanies.html?companyID=${company.companyID}">Companies</a></span></li>	
			<li><span ><a href="newcompany.html?companyID=${company.companyID}">New Company</a></span></li>	
			<li><span><a href="viewprojects.html?companyID=${company.companyID}">Projects</a></span></li>	
			<li><span  class="current">New project</span></li>		
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
		<form method="post">
		<input type="hidden" name="companyID" value="${company.companyID}">			
				<table border="0" cellpadding="5" align="center">		
				<tr>
					<td>
						[${company.companyID}] Project :
					</td>
				</tr>
				<tr>		
					<td>
						<input type="text" name="projectName" size="50">					
							
					</td>
				</tr>
				<tr>
					<td align="right">						
						<input type="submit">				
					</td>			
				</tr>
				</table>
		
		</form>	
	</div>
	<div id="footer">
		Copyright © Testcenter, 2012
	</div>
</div>	
	<div id="preload">
		<img src="images/title.png">				
	</div>
	</body>
</html>