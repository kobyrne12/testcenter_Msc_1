<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<title>TestCenter - View Companies</title>
		<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
	</head>
<body>
<div id="container">
	<div id="header">
		<a href="index.html"><img src="images/title.png"></a>				
	</div>	
	<div >
		<ul id="menu">				
			<li><span><a href="index.html">Home</a></span></li>				
			<li><span class="current">Companies</span></li>													
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
		<font color="#707070">List of current Companies (total: ${fn:length(companies)}) :</font>
		<BR/>
		<font size="1" color="#7F94B0">note : click Company to enter results </font>
		<BR/><BR/>
		
		<table border="0" cellpadding="4" cellspacing="3" align="center">			
			<tr style="background-color:#7F94B0">
				<security:authorize ifAllGranted="ROLE_ADMIN">				
				<td style="background-color:#ffffff">					
				</td>	
				</security:authorize>				
				<td>
					<font color="#ffffff">Company ID</font>
				</td>
				<td width="300">
					<font color="#ffffff">Company Name</font>
				</td>
				
			</tr>	
			<c:forEach var="company" items="${companies}" varStatus="index">		
				<tr>
					<security:authorize ifAllGranted="ROLE_ADMIN">	
					<td>									
						<form name="edittestplan" action="../testconfig/edittestplan.html" method="get">
							<input type="hidden" name="companyID" value="${company.companyID}">							
							<input type="image" src="../images/edit.png" alt="Edit Company" title="Edit Company" width="15">
						</form>							
					</td>	
					</security:authorize>				
					<td valign="top">
						<font color="#707070">${company.companyID}</font>
					</td>
					<td  valign="top">		
						<font color="#707070"><a href="viewprojects.html?companyID=${company.companyID}">${company.companyName}</a></font>									
					</td>					
					<td>
						<form name="deletecompany" action="testconfig/deletecompany.html" method="get">
							<input type="hidden" name="companyID" value="${company.companyID}">							
							<input type="image" src="images/delete.png" alt="Delete Company" title="Delete company"  onclick="return confirm('Are you sure you want to delete company (${company.companyName}) ?')">
						</form>		
					</td>	
					
				</tr>	
			</c:forEach>
			
			<tr>
				<td>
				</td>
				<td colspan="5">
					<a href="newcompany.html"><img src="images/plus.png" width="15"> <font color="#707070">New Company</font></a>
				</td>				
			</tr>
			
		</table>
	</div>
	<div id="footer">
		Copyright © Testcenter, 2012
	</div>
</div>	
<div id="preload">
	<img src="images/title.png">
	<img src="images/delete.png" alt="Delete Test Case" title="Delete Test Case" /> 
 	<img src="images/edit.png" alt="Edit Test Case" title="Edit Test Case" />    		
</div>
</body>
</html>