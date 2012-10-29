<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<title>TestCenter - View Projects</title>
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
			<li><span class="current">Home 1</span></li>				
			<li><span><a href="viewcompanies.html?companyID=${companyID}">Companies</a></span></li>		
			<li><span><a href="viewprojects.html?companyID=${companyID}">${projectsDisplayName}</a></span></li>	
			<li><span><a href="#"></a>${reportsDisplayName}</span></li>	
			<li><span><a href="#"></a>${defectsDisplayName}</span></li>	
			<li><span><a href="#"></a>${requirementsDisplayName}</span></li>	
			<li><span><a href="viewcycles.html?companyID=${companyID}">All ${cyclesDisplayName}</a></span></li>					
			<li><span><a href="#"></a>${usersDisplayName}</span></li>	
			<li><span><a href="#"></a>${environmentsDisplayName}</span></li>	
																
			<security:authorize ifAllGranted="ROLE_ADMIN">	
			<li><span><a href="newproject.html">New Project</a></span></li>	
			</security:authorize>	
			<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">	
			<li><span><a href="j_spring_security_logout">Logout</a></span></li>	
			</security:authorize>					
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
		
		<font color="#707070">List of current Cycles (total: ${fn:length(cycles)}) :</font>
		
		<BR/><BR/>
		
		<table border="0" cellpadding="4" cellspacing="3" align="center">			
			<tr style="background-color:#7F94B0">
				<security:authorize ifAllGranted="ROLE_ADMIN">				
				<td style="background-color:#ffffff">					
				</td>	
				</security:authorize>				
				<td>		
					<font color="#ffffff">Cycle ID</font>
				</td>
				<td width="100">
					<font color="#ffffff" >Cycle Name</font>
				</td>
				<td>
					<font color="#ffffff">requiredPriority</font>
				</td>					
				<td>
					<font color="#ffffff">projectPosition</font>
				</td>
				<td>
					<font color="#ffffff">totalCycleEstTime</font>
				</td>
				<td>
					<font color="#ffffff">totalCycleEstTime</font>
				</td>
				<td>
					<font color="#ffffff">cycleStartDate</font>
				</td>
				<td>
					<font color="#ffffff">cycleEndDate</font>
				</td>
				<td>
					<font color="#ffffff">codeChangeRule</font>
				</td>
				<td>
					<font color="#ffffff">defectRule</font>
				</td>
				<td>
					<font color="#ffffff">testHistoryRule</font>
				</td>
				<td>
					<font color="#ffffff">RequirementRule</font>
				</td>
				<td>
					<font color="#ffffff">creationDate</font>
				</td>
				<td>
					<font color="#ffffff">createdBy</font>
				</td>
				<td>
					<font color="#ffffff">lastModifiedDate</font>
				</td>
				<td>
					<font color="#ffffff">lastModifiedBy</font>
				</td>
			</tr>	
			<c:forEach var="cycle" items="${cycless}" varStatus="index">		
				<tr>
					<security:authorize ifAllGranted="ROLE_ADMIN">	
					<td>									
						<form name="edittestplan" action="../projects/editproject.html" method="get">
							<input type="hidden" name="projectID" value="${projectID}">							
							<input type="image" src="../images/edit.png" alt="Edit Project" title="Edit Project" width="15">
						</form>							
					</td>	
					</security:authorize>				
					<td valign="top">
						<font color="#707070">[${companyID}][${cycle.projectID}] ${cycle.cycleID} </font>
					</td>
					<td  valign="top">		
						<font color="#707070">${cycle.projectName}</font>									
					</td>				
					<td  valign="top">		
						<font color="#707070">${cycle.requiredPriority}</font>									
					</td>
					<td  valign="top">
						<font color="#707070">${cycle.projectPosition}</font>
					</td>					
					<td  valign="top" align="center">
						<font color="#707070">${cycle.totalCycleEstTime} %</font>
					</td>
					<td   valign="top" align="center">
						<font color="#707070">${cycle.cycleStartDate}</font>
					</td>
					<td  valign="top" align="center">
						<font color="#707070">${cycle.cycleEndDate}</font>
					</td>
					<td  valign="top" align="center">
						<font color="#707070">${cycle.codeChangeRule}</font>
					</td>
					<td  valign="top" align="center">
						<font color="#707070">${cycle.defectRule}</font>
					</td>
					<td  valign="top" align="center">
						<font color="#707070">${cycle.testHistoryRule}</font>
					</td>
					<td  valign="top" align="center">
						<font color="#707070">${cycle.RequirementRule}</font>
					</td>
					<td  valign="top" align="center"> 
					<font color="#707070">${cycle.creationDate}</font> 
					</td> 
 					<td  valign="top" align="center"> 
						<font color="#707070">${cycle.createdBy}</font> 
 					</td> 
					<td  valign="top" align="center">
						<font color="#707070">${cycle.lastModifiedDate}</font>
					</td>
					<td  valign="top" align="center">
						<font color="#707070">${cycle.lastModifiedBy}</font>
					</td>
				
					<security:authorize ifAllGranted="ROLE_ADMIN">			
					<td>
						<form name="deleteproject" action="projects/deleteproject.html" method="get">
							<input type="hidden" name="cycleID" value="${cycle.cycleID}">							
							<input type="image" src="images/delete.png" alt="Delete Cycle" title="Delete Project"  onclick="return confirm('Are you sure you want to delete cycle (${cycle.cycleName}) ?')">
						</form>		
					</td>	
					</security:authorize>
				</tr>	
			</c:forEach>
			
			<tr>
				
				<td colspan="11">
						<form name="newcycle" action="newcycle.html" method="get">
							<input type="hidden" name="projectID" value="${projectID}">							
<!-- 							<input type="image" src="images/plus.png" alt="New Project" title="New Project" > -->
							<input type="submit" value="Create">	
						</form>
					
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