<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %><%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"><html xmlns="http://www.w3.org/1999/xhtml"	  xmlns:h="http://java.sun.com/jsf/html"><head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>${companyName} - Projects</title>		<!-- Needed -->				<link rel="stylesheet" href="css/final/menubar.css" type="text/css" media="screen" />		<link rel="stylesheet" href="css/final/layout-default-latest.css" type="text/css" />			<link rel="stylesheet" href="css/final/ui.multiselect.css" type="text/css" />		<link rel="stylesheet" href="css/final/jquery-ui-1.9.1.custom.css" type="text/css" />		<link rel="stylesheet" href="css/final/ui.jqgrid.css" type="text/css" />				<!--			<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/start/jquery-ui.css" type="text/css" rel="Stylesheet" class="ui-theme">			<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/redmond/jquery-ui.css" type="text/css" rel="Stylesheet" class="ui-theme">		 -->	<!-- Needed -->		<!-- Not needed 	<link rel="stylesheet" type="text/css" href="http://layout.jquery-dev.net/lib/css/themes/base/jquery.ui.all.css">	Not needed -->			<!-- <script type="text/javascript" src="resources/js/jquery-1.8.2.js"></script>   -->	<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>		 <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>  	<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.20/jquery-ui.min.js"></script>		<script type="text/javascript" src="http://layout.jquery-dev.net/lib/js/jquery.layout-latest.js"></script>	<script type="text/javascript" src="http://layout.jquery-dev.net/lib/js/jquery.layout.resizePaneAccordions-1.0.js"></script>		<script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-4.3.3/plugin/ui.multiselect.js"></script>	<script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-4.3.3/js/i18n/grid.locale-en.js"></script>       <script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-4.3.3/js/jquery.jqGrid.src.js"></script>        <script type="text/javascript">        $.jgrid.no_legacy_api = true;        $.jgrid.useJSON = true;		    </script>    <script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-4.3.3/js/jquery.jqGrid.min.js"></script>    	<style type="text/css">	.grid .ui-jqgrid-htable th,	.grid .ui-jqgrid-btable .jqgrow td {	    height: 10em !important;	}	.ui-priority-secondary{background: #E8E8E8;}	.innerAccordian .ui-accordion-header  a	{	display: block;	font-size: 1em;	padding: .2em .5em .5em 2.2em;	}	#fieldS{		margin-left:10%;		color:#606060;		}		.ui-helper-reset {		line-height: 1.3;		font-size: 10px;		list-style: none;		color:blue;	}	.ui-widget 	{		font-family: Verdana,Arial,sans-serif;		font-size: 0.8em;	}	/* customize borders to avoid double-borders around inner-layouts */	.ui-layout-pane {		border:			0; /* override layout-default-latest.css */		border-top:		0px solid #BBB;		border-bottom:	0px solid #BBB;	}	.ui-layout-pane-north , .ui-layout-pane-south	{			border:			0px solid #BBB;		overflow:		hidden;		z-index: 3 !important;		overflow: visible !important; 	}	.ui-layout-pane-east 	{	}	.ui-layout-pane-center		{		border-left:	0;		border-right:	0;	}		.inner-center 		{			border:		0;			border-right:	0; 						padding:		0;			background:		#E8E8E8;		}		.inner-south 		{			border-top:		0px;			border-right:		0; 			border-bottom:		0; 		}		.outer-east, .outer-east .ui-layout-content { 		padding: 0;		overflow: hidden;		background-color: #EEE;	}		/* remove padding & scrolling from panes that are 'containers' for nested layouts */	.outer-center {		border:			0; /* cosmetic */		padding:		0;	}	.middle-center {		border:			0; /* cosmetic */		padding:		0;		/*overflow:		hidden;*/	}	button {		padding-left:9px;		padding-right:9px;		padding-top:2px;		padding-bottom:2px;	}	/*	 *	customize borders on panes/resizers to make pretty	 */		.ui-layout-pane-east			{ 		border-left:		0; 	}	.ui-layout-resizer-east			{		border-left:		0; 		border-right:		0; 	}		.ui-layout-pane-north			{ 		border-bottom:	0; 	}	.ui-layout-resizer-north		{ 		border-top:		1px solid #BBB; 	}		.ui-layout-pane-south			{ 		border-top:		0; 		background:		#FFF;	}	.ui-layout-resizer-south		{ 		border-top:		1px solid #BBB; 		border-bottom: 	0; 	}		/*	 *	add borders to resizers when pane is 'closed'	 *	 *.ui-layout-resizer-closed	{ border:			1px solid #BBB; }	 */	/*	 *	show both borders when the resizer is 'dragging'	 */		.ui-layout-resizer-east-dragging 	{		border-left:		1px solid #BBB;		border-right:		1px solid #BBB;	}	.ui-layout-resizer-north-dragging ,.ui-layout-resizer-south-dragging 	{		border-top:		1px solid #BBB;		border-bottom:	1px solid #BBB;	}	h3, h4 	{ 		/* Headers & Footer in Center & East panes */		font-size:		1.1em;		background:		#EEF;		border:			1px solid #BBB;		border-width:	0 0 1px;		padding:		7px 10px;		margin:			0;	}	A:link {	text-decoration: none;	font-family: Verdana, Arial, Helvetica, sans-serif;	color:	#2554C7 ;}A:visited {	text-decoration: none;	font-family: Verdana, Arial, Helvetica, sans-serif;	color:	#2554C7 ;}A:active {	text-decoration: none;	font-family: Verdana, Arial, Helvetica, sans-serif;	color:	#2554C7 ;}A:hover {	text-decoration: underline; 	color: blue;	font-family: Verdana, Arial, Helvetica, sans-serif;	color:	#2554C7;}.progressBarDiv{ 	-webkit-box-shadow: #999 1px 5px 10px;	-moz-box-shadow: #999 1px 1px 10px;	//-moz-box-shadow: 0px 0px 4px #333; 	//-webkit-box-shadow: 0px 0px 4px #333;    // box-shadow: 1px 1px 2px #333; 	overflow: hidden;	cursor:pointer;}.completeBar {				background-color:#6eaf37; 	height:30px;	float: left;	display:inline-block; 	overflow: hidden;}.completeBar:hover {	opacity:0.7;	filter:alpha(opacity=70); /* For IE8 and earlier */	//background-color:#5dad58; 	//-moz-transform: scale(2);	//background-color:#89a54e; }.notCompleteBar {						background-color:#456e8c; 	height:30px;	float: right;	display:inline-block; 		overflow: hidden;}.notCompleteBar:hover {	opacity:0.7;	filter:alpha(opacity=70); /* For IE8 and earlier */	//background-color:#4573a7; }		.blueBar		{ 						background-color:#4573a7;					//background-image: url(images/redProgressBar.jpg); height:auto;		}				.redBar		{ 				background-color:#AE3B37; 						//background-color:#C2423D; 						//background-image: url(images/redProgressBar.jpg); height:auto		}		.redBar:hover {						//background-color:#A64E5E; 		}				.orangeBar 		{								background-color:#DDC23F; 				//background-color:#E9AF32; 			}		.greenBar 		{								background-color:#6eaf37; 							//background-color:#89a54e; 			//background-image: url(images/green_bg3.png);		}		.greenBar:hover {			//-moz-transform: scale(2);			//background-color:#89a54e; 		}		.pgPercent{			width:20%;			float: left;			color: #4573a7;			font-weight:bold;			display:inline-block;'		}</style>	<script type="text/javascript">$(function() {	$(document).ready(function () 	{				// OUTER-LAYOUT		$('body').layout(		{			center__paneSelector:	".outer-center"	,				east__paneSelector:		".outer-east",					east__size:				250,			//onresize: resizeGrid,			spacing_open:			8 , // ALL panes			spacing_closed:			12, // ALL panes			north__minSize:			90,					south__maxSize:			60,			south__resizable:	false,						south__closable:	false,				north__resizable:	false,						north__closable:	false,				// MIDDLE-LAYOUT (child of outer-center-pane)			center__childOptions: 			{								center__paneSelector:	".middle-center",						// INNER-LAYOUT (child of middle-center-pane)				center__childOptions: 				{					center__paneSelector:	".inner-center",					south__paneSelector:	".inner-south",							south__minSize:			20,					south__resizable:	false,								south__closable:	false,									}			}		});			$("#accordion2").accordion({fillSpace:true, active:0});					$("#accordion").accordion(		{			autoHeight:true,			icons:{ "header": "ui-icon-triangle-1-e", "headerSelected": "ui-icon-triangle-1-s"}		}); 	});	});//]]></script></head><body><!--	 ******************************************************************************************************************	 ************************************              Center objects           *****************************************	 ******************************************************************************************************************--><div class="outer-center">		<div class="middle-center">		<div class="inner-center" >			<div style="font-size:10px; padding-bottom:4px;padding-top:0px;padding-left:6px" >							Home					</div>						<div class="ui-layout-content ui-widget-content" style="background:#fff;">							<table width="100%" border="0" cellpadding="20" cellspacing="20">				<tr>						<c:forEach var="project" items="${company.projects}" varStatus="count" >												<td>						<table border="0">							<tr>								<td colspan="2" align="left">																		<a href="?userpath=${fn:replace(projectsDisplayName,' ', '')}>${project.projectID}"><b><font color="#014c6c">${project.projectName}</font></b></a>								</td>							</tr>							<tr>								<td >									<a href="?userpath=${fn:replace(projectsDisplayName,' ', '')}>${project.projectID}">										<img src="images/project.png" width="100" height="100" alt="${projectDisplayName} Details" />										</a>								</td>																							<td valign="top">									<table border="0" style="font-size:10px; color:'#5994d6';" cellpadding="0" cellspacing="0">																		<tr>										<td >											<a href="?userpath=${fn:replace(projectsDisplayName,' ', '')}>${project.projectID}>${fn:replace(cyclesDisplayName,' ', '')}">												<font color="#5994d6">													${company.cyclesDisplayName}												</font>											</a>										</td>										<td>												<c:set var="cyclecount" value="0" scope="page" />																					<c:set var="cyclecount" value="${cyclecount + fn:length(project.cycles)}" scope="page"/>											&nbsp; ${cyclecount}										</td>									</tr>									<tr>										<td >											<a href="?userpath=${fn:replace(projectsDisplayName,' ', '')}>${project.projectID}>${fn:replace(testplansDisplayName,' ', '')}">												<font color="#5994d6">													${company.testplansDisplayName}												</font>											</a>										</td>										<td>											&nbsp; ${fn:length(project.testplans)}										</td>									</tr>									<c:set var="testcasecount" value="0" scope="page" />									<c:forEach var="testplan" items="${project.testplans}" varStatus="testplancount2" >																				<c:set var="testcasecount" value="${testcasecount + fn:length(testplan.testcases)}" scope="page"/>									</c:forEach>									<tr>										<td>											<a href="?userpath=${fn:replace(projectsDisplayName,' ', '')}>${project.projectID}>${fn:replace(testcasesDisplayName,' ', '')}">												<font color="#5994d6">													${company.testcasesDisplayName}												</font>											</a>										</td>										<td>											&nbsp; ${testcasecount}										</td>									</tr>									<c:set var="testruncount" value="0" scope="page" />									<c:set var="defectcount" value="0" scope="page" />									<c:forEach var="cycle" items="${project.cycles}" varStatus="cyclecount2" >																														<c:set var="testruncount" value="${testruncount + fn:length(cycle.testruns)}" scope="page"/>																															<c:forEach var="testrun" items="${cycle.testruns}" varStatus="testruncount2" >																															<c:set var="defectcount" value="${defectcount + fn:length(testrun.defects)}" scope="page"/>																															</c:forEach>									</c:forEach>									<tr>										<td >											<a href="?userpath=${fn:replace(projectsDisplayName,' ', '')}>${project.projectID}>${fn:replace(testrunsDisplayName,' ', '')}">												<font color="#5994d6">													${company.testrunsDisplayName}												</font>											</a>										</td>										<td>											&nbsp; ${testruncount}										</td>									</tr>																		<tr>										<td >											<a href="?userpath=${fn:replace(projectsDisplayName,' ', '')}>${project.projectID}>${fn:replace(defectsDisplayName,' ', '')}">												<font color="#5994d6">													${company.defectsDisplayName}												</font>											</a>										</td>										<td>											&nbsp; ${defectcount}										</td>									</tr>									</table>								</td>								   							</tr>								<c:if test="${cyclecount gt 0}"> 								<tr>								<tr>								<td style="font-size:'0.1em'';">								<font size="1">Current ${company.cycleDisplayName} :</font> 								</td></tr>									<td colspan="2">										<div style='width:100%;height:18px;' class='progressBarDiv' >											<div class='completeBar' style='height:18px;width:80%; float: left;' title="80% Complete">												&nbsp; 											</div>														<div class='notCompleteBar' style='height:18px;width:20%;float: right;' title="20% InComplete"> 												&nbsp;											</div>										</div>										</td>								</tr>								</c:if>											</table>						</td>						<c:if test="${count.index == 2}"> 						</tr>						<tr>						</c:if>											</c:forEach>				</tr>				</table>									</div>					</div>		</div></div><!--	 ******************************************************************************************************************	 ************************************              EAST objects           *****************************************	 ******************************************************************************************************************--><div class="outer-east">	<div class="ui-layout-content">		<div id="accordion2" class="basic" >		<h3 style="height: 30px; font-size:12px; padding-top: 4px;"><a href="#">Quick Tasks</a></h3>			<div>					<fieldset style="width:90%;">					<legend>Test libray:</legend>					<div id="fieldS" >					    Execute Teat Run <BR/>						Execute Test Plan<BR/>					</div>				</fieldset>				<fieldset style="width:90%;">					<legend>Defect:</legend>					<div id="fieldS" >						Create Defect <BR />						Update Defect<BR />					</div>				</fieldset>					<fieldset style="width:90%;">					  <legend>Requirement:</legend>						<div id="fieldS" >							Execute Teat Run <BR />							Execute Test Plan<BR />						</div>					</fieldset>					<fieldset style="width:90%;">					  <legend>Users:</legend>					<div style="margin-left:10%;">						Execute Teat Run <BR />						Execute Test Plan<BR />					  </div>					</fieldset>					<fieldset style="width:90%;">					  <legend>Cycles:</legend>						<div id="fieldS" >							Execute Teat Run <BR />							Execute Test Plan<BR />						</div>					</fieldset>				</div>			<h3 style="height: 30px; font-size:12px; padding-top: 4px;"><a href="#">Reports</a></h3>			<div>							</div>			<h3 style="height: 30px; font-size:12px; padding-top: 4px;"><a href="#">Section 4</a></h3>			<div>							</div>					</div>	</div></div><!--	 ******************************************************************************************************************	 ************************************              north header             *****************************************	 ******************************************************************************************************************--><div class="ui-layout-north ui-widget-content" style="display: none;"><table style="width:100%;">	<tr>		<td align="left" style="width:50%;color:#456e8c;">			<img style="height:25px;" src ="images/title.png" />					</td>		<td align="right" style="width:50%;color:#456e8c;" valign="middle">			${companyName}	&nbsp; <a href="editcompany.html"><img height="25" width="25" src="images/settings.png" /></a>		</td>	</tr>	<tr>		<td colspan="2">		<!--			 ******************************************************************************************************************			 ************************************         Nav bar     *****************************************			 ******************************************************************************************************************		-->			<ul id="menu">			    <li style="padding-left:30px"><a href="index.html" ><img src="images/home.jpg" height="15px" /> Home </a><!-- Begin Home Item --></li><!-- End Home Item --> 			    <li><a href="?userpath=${fn:replace(projectsDisplayName,' ', '')}" class="drop">${projectsDisplayName}</a><!-- Begin 4 columns Item --></li><!-- End 5 columns Item -->    				    <li><a href="reports.html" class="drop">${reportsDisplayName}</a><!-- Begin 4 columns Item --> </li><!-- End 4 columns Item -->								<li><a href="defects.html" class="drop">${defectsDisplayName}</a><!-- Begin 4 columns Item --></li><!-- End 4 columns Item -->			    <li><a href="?userpath=${fn:replace(cyclesDisplayName,' ', '')}" class="drop">${cyclesDisplayName}</a><!-- Begin 4 columns Item --></li><!-- End 4 columns Item -->			   	<li><a href="?userpath=${fn:replace(testLibraryDisplayName,' ', '')}" class="drop">${testLibraryDisplayName}</a>			   	 <div class="dropdown_3columns align_right">  			            <div class="col_3">			           		<table>							<tr>								<td rowspan="2">									<a href="?userpath=${fn:replace(testplansDisplayName,' ', '')}">										<img src="images/testplan.jpg" width="70" height="70" class="img_left imgshadow" alt="" />										</a>								</td>								<td>									<a href="?userpath=${fn:replace(testplansDisplayName,' ', '')}"><b>${testplansDisplayName}</b></a>								</td>							</tr>							<tr>								<td>									<p>						                Contains a List of all ${testplansDisplayName} for this company. A ${testplanDisplayName} is a container for one or multiple ${testcasesDisplayName}						            </p>								</td>								   							</tr>													</table>							<table>							<tr>								<td rowspan="2">									<a href="?userpath=${fn:replace(testcasesDisplayName,' ', '')}">										<img src="images/testcase.jpg" width="70" height="70" class="img_left imgshadow" alt="" />										</a>								</td>								<td>									<a href="?userpath=${fn:replace(testcasesDisplayName,' ', '')}"><b>${testcasesDisplayName}</b></a>								</td>							</tr>							<tr>								<td>									<p>						               Contains a List of all ${testcasesDisplayName} for this company. A ${testcaseDisplayName} is used to examine an aspect of a specific requirement for a given environment				           </p>								</td>								   							</tr>													</table>									<table>							<tr>								<td rowspan="2">									<a href="?userpath=${fn:replace(testrunsDisplayName,' ', '')}">										<img src="images/testrun.png" width="70" height="70" class="img_left imgshadow" alt="" />										</a>								</td>								<td>									<a href="?userpath=${fn:replace(testrunsDisplayName,' ', '')}"><b>${testrunsDisplayName}</b></a>								</td>							</tr>							<tr>								<td>									<p>						               Contains a List of all ${testrunsDisplayName} for this company. A ${testrunDisplayName} is the result of executing a ${testcaseDisplayName} for a specified ${cycleDisplayName}				           			</p>								</td>								   							</tr>													</table>										            </div>			        			        </div>			   	</li><!-- End 4 columns Item -->						<li><a href="requirements.html" class="drop">${requirementsDisplayName}</a></li><!-- End 4 columns Item -->   				<li><a href="users.html" class="drop">${usersDisplayName}</a> </li><!-- End 4 columns Item -->    				<li><a href="environments.html" class="drop">${environmentsDisplayName}</a></li>   			</ul>		</td>	</tr></table></div><!--	 ******************************************************************************************************************	 ************************************         End of north header         *****************************************	 ******************************************************************************************************************--><!--	 ******************************************************************************************************************	 ************************************            South Footer             *****************************************	 ******************************************************************************************************************--><div class="ui-layout-south" style=""><table style="width:100%">	<tr>		<td style="width:50%">			User : Kenneth		</td>		<td style="width:50%">			Role : Tester		</td>	</tr></table></div></body></html>