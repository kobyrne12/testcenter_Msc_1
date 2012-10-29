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
		
		<font color="#707070">List of current Projects (total: ${fn:length(projects)}) :</font>
		
		<BR/><BR/>
		
		<table border="0" cellpadding="4" cellspacing="3" align="center">			
			<tr style="background-color:#7F94B0">
				<security:authorize ifAllGranted="ROLE_ADMIN">				
				<td style="background-color:#ffffff">					
				</td>	
				</security:authorize>				
				<td>
					<font color="#ffffff">Project ID</font>
				</td>
				<td width="100">
					<font color="#ffffff" >Project Name</font>
				</td>
				<td>
					<font color="#ffffff">Parent ID</font>
				</td>					
				<td>
					<font color="#ffffff">Regression %</font>
				</td>
				<td>
					<font color="#ffffff">New Feature %</font>
				</td>
				<td>
					<font color="#ffffff">Sev 1</font>
				</td>
				<td>
					<font color="#ffffff">Sev 2</font>
				</td>
				<td>
					<font color="#ffffff">Sev 3</font>
				</td>
				<td>
					<font color="#ffffff">Sev 4</font>
				</td>
<!-- 				<td> -->
<!-- 					<font color="#ffffff">creation Date</font> -->
<!-- 				</td> -->
<!-- 				<td> -->
<!-- 					<font color="#ffffff">Created By</font> -->
<!-- 				</td> -->
				<td width="150">
					<font color="#ffffff">Last Modified</font>
				</td>
				<td>
					<font color="#ffffff">Modified By</font>
				</td>
			</tr>	
			<c:forEach var="project" items="${projects}" varStatus="index">		
				<tr>
					<security:authorize ifAllGranted="ROLE_ADMIN">	
					<td>									
						<form name="edittestplan" action="../projects/editproject.html" method="get">
							<input type="hidden" name="projectID" value="${project.projectID}">							
							<input type="image" src="../images/edit.png" alt="Edit Project" title="Edit Project" width="15">
						</form>							
					</td>	
					</security:authorize>				
					<td valign="top">
						<font color="#707070">[${project.companyID}] ${project.projectID} </font>
					</td>
					<td  valign="top">		
						<font color="#707070"><a href="viewcycles.html?projectID=${projectID}">${project.projectName}</a></font>									
					</td>
					<td  valign="top">		
						<font color="#707070">${project.parentID}</font>									
					</td>
					<td  valign="top">
						<font color="#707070">${project.regressionRequiredPercent} %</font>
					</td>					
					<td  valign="top" align="center">
						<font color="#707070">${project.newFeatureRequiredPercent} %</font>
					</td>
					<td   valign="top" align="center">
						<font color="#707070">${project.allowedSev1}</font>
					</td>
					<td  valign="top" align="center">
						<font color="#707070">${project.allowedSev2}</font>
					</td>
					<td  valign="top" align="center">
						<font color="#707070">${project.allowedSev3}</font>
					</td>
					<td  valign="top" align="center">
						<font color="#707070">${project.allowedSev4}</font>
					</td>
<!-- 					<td  valign="top" align="center"> -->
<%-- 						<font color="#707070">${project.creationDate}</font> --%>
<!-- 					</td> -->
<!-- 					<td  valign="top" align="center"> -->
<%-- 						<font color="#707070">${project.createdBy}</font> --%>
<!-- 					</td> -->
					<td  valign="top" align="center">
						<font color="#707070">${project.lastModifiedDate}</font>
					</td>
					<td  valign="top" align="center">
						<font color="#707070">${project.lastModifiedBy}</font>
					</td>
				
					<security:authorize ifAllGranted="ROLE_ADMIN">			
					<td>
						<form name="deleteproject" action="projects/deleteproject.html" method="get">
							<input type="hidden" name="projectID" value="${project.projectID}">							
							<input type="image" src="images/delete.png" alt="Delete Testplan" title="Delete Project"  onclick="return confirm('Are you sure you want to delete Project (${project.projectName}) ?')">
						</form>		
					</td>	
					</security:authorize>
				</tr>	
			</c:forEach>
			
			<tr>
				
				<td colspan="11">
						<form name="newproject" action="newproject.html" method="get">
							<input type="hidden" name="companyID" value="${companyID}">							
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