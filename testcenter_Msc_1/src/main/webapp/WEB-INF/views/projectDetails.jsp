<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	  xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Testcenter - ${project.projectName}</title>
	
	<!-- Needed -->
		
		<link rel="stylesheet" href="css/final/menubar.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="css/final/layout-default-latest.css" type="text/css" />	
		<link rel="stylesheet" href="css/final/ui.multiselect.css" type="text/css" />
		<link rel="stylesheet" href="css/final/jquery-ui-1.9.1.custom.css" type="text/css" />
		<link rel="stylesheet" href="css/final/ui.jqgrid.css" type="text/css" />
		
		<!--
			<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/start/jquery-ui.css" type="text/css" rel="Stylesheet" class="ui-theme">
			<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/redmond/jquery-ui.css" type="text/css" rel="Stylesheet" class="ui-theme">
		 -->
	<!-- Needed -->
	
	<!-- Not needed 
	<link rel="stylesheet" type="text/css" href="http://layout.jquery-dev.net/lib/css/themes/base/jquery.ui.all.css">
	Not needed -->		

	<!-- <script type="text/javascript" src="resources/js/jquery-1.8.2.js"></script>   -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
	
	 <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>  
	<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.20/jquery-ui.min.js"></script>	
	<script type="text/javascript" src="http://layout.jquery-dev.net/lib/js/jquery.layout-latest.js"></script>
	<script type="text/javascript" src="http://layout.jquery-dev.net/lib/js/jquery.layout.resizePaneAccordions-1.0.js"></script>	
	<script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-4.3.3/plugin/ui.multiselect.js"></script>
	<script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-4.3.3/js/i18n/grid.locale-en.js"></script>   
    <script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-4.3.3/js/jquery.jqGrid.src.js"></script>    
    <script type="text/javascript">
        $.jgrid.no_legacy_api = true;
        $.jgrid.useJSON = true;		
    </script>
    <script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-4.3.3/js/jquery.jqGrid.min.js"></script>
    
	<style type="text/css">
	body, ul, li {
	font-size:12px;
	}
	.grid .ui-jqgrid-htable th,
	.grid .ui-jqgrid-btable .jqgrow td {
	    height: 10em !important;
	}
	.ui-priority-secondary{background: #E8E8E8;}
	.innerAccordian .ui-accordion-header  a
	{
	display: block;
	font-size: 1em;
	padding: .2em .5em .5em 2.2em;
	}
	#fieldS{
		margin-left:10%;
		color:#606060;
		}
	
	.ui-helper-reset {
		line-height: 1.3;
		font-size: 10px;
		list-style: none;
		color:blue;
	}
	.ui-widget 
	{
		font-family: Verdana,Arial,sans-serif;
		font-size: 0.8em;
	}
	/* customize borders to avoid double-borders around inner-layouts */
	.ui-layout-pane {
		border:			0; /* override layout-default-latest.css */
		border-top:		0px solid #BBB;
		border-bottom:	0px solid #BBB;
	}
	.ui-layout-pane-north , .ui-layout-pane-south
	{	
		border:			0px solid #BBB;
		overflow:		hidden;
		z-index: 3 !important;
		overflow: visible !important; 
	}

	.ui-layout-pane-east 
	{
	}
	.ui-layout-pane-center	
	{
		border-left:	0;
		border-right:	0;
	}
		.inner-center 
		{
			border:		0;
			border-right:	0; 			
			padding:		0;
			background:		#E8E8E8;
		}
		.inner-south 
		{
			border-top:		0px;
			border-right:		0; 
			border-bottom:		0; 
		}
	
	.outer-east, .outer-east .ui-layout-content { 
		padding: 0;
		overflow: hidden;
		background-color: #EEE;
	}	
	/* remove padding & scrolling from panes that are 'containers' for nested layouts */
	.outer-center {
		border:			0; /* cosmetic */
		padding:		0;
	}
	.middle-center {
		border:			0; /* cosmetic */
		padding:		0;
		/*overflow:		hidden;*/
	}
	button {
		padding-left:9px;
		padding-right:9px;
		padding-top:2px;
		padding-bottom:2px;
	}
	/*
	 *	customize borders on panes/resizers to make pretty
	 */
	
	.ui-layout-pane-east		
	{ 
		border-left:		0; 
	}
	.ui-layout-resizer-east		
	{
		border-left:		0; 
		border-right:		0; 
	}
	
	.ui-layout-pane-north		
	{ 
		border-bottom:	0; 
	}
	.ui-layout-resizer-north	
	{ 
		border-top:		1px solid #BBB; 
	}
	
	.ui-layout-pane-south		
	{ 
		border-top:		0; 
		background:		#FFF;
	}
	.ui-layout-resizer-south	
	{ 
		border-top:		1px solid #BBB; 
		border-bottom: 	0; 
	}
	
	/*
	 *	add borders to resizers when pane is 'closed'
	 *
	 *.ui-layout-resizer-closed	{ border:			1px solid #BBB; }
	 */
	/*
	 *	show both borders when the resizer is 'dragging'
	 */
	
	.ui-layout-resizer-east-dragging 
	{
		border-left:		1px solid #BBB;
		border-right:		1px solid #BBB;
	}
	.ui-layout-resizer-north-dragging ,.ui-layout-resizer-south-dragging 
	{
		border-top:		1px solid #BBB;
		border-bottom:	1px solid #BBB;
	}
	h3, h4 
	{ 
		/* Headers & Footer in Center & East panes */
		font-size:		1.1em;
		background:		#EEF;
		border:			1px solid #BBB;
		border-width:	0 0 1px;
		padding:		7px 10px;
		margin:			0;
	}
	A:link 
{
	text-decoration: none;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color:	#2554C7 ;
}
A:visited 
{
	text-decoration: none;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color:	#2554C7 ;
}
A:active 
{
	text-decoration: none;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color:	#2554C7 ;
}
A:hover 
{
	text-decoration: underline; 
	color: blue;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color:	#2554C7;
}
.progressBarDiv
{ 
	-webkit-box-shadow: #999 1px 5px 10px;
	-moz-box-shadow: #999 1px 1px 10px;
	//-moz-box-shadow: 0px 0px 4px #333; 
	//-webkit-box-shadow: 0px 0px 4px #333; 
   // box-shadow: 1px 1px 2px #333; 
	overflow: hidden;
	cursor:pointer;
}
.completeBar 
{			
	background-color:#6eaf37; 
	height:30px;
	float: left;
	display:inline-block; 
	overflow: hidden;
}
.completeBar:hover 
{
	opacity:0.7;
	filter:alpha(opacity=70); /* For IE8 and earlier */
	//background-color:#5dad58; 
	//-moz-transform: scale(2);
	//background-color:#89a54e; 
}
.notCompleteBar 
{					
	background-color:#456e8c;
 	height:30px;
	float: right;
	display:inline-block; 	
	overflow: hidden;
}
.notCompleteBar:hover 
{
	opacity:0.7;
	filter:alpha(opacity=70); /* For IE8 and earlier */
	//background-color:#4573a7; 
}

		.blueBar
		{ 
			
			background-color:#4573a7;
		
			//background-image: url(images/redProgressBar.jpg); height:auto;
		}
		
		.redBar
		{ 	
			background-color:#AE3B37; 			
			//background-color:#C2423D; 			
			//background-image: url(images/redProgressBar.jpg); height:auto
		}
		.redBar:hover {
			
			//background-color:#A64E5E; 
		}
		
		.orangeBar 
		{					
			background-color:#DDC23F; 	
			//background-color:#E9AF32; 	
		}
		.greenBar 
		{			
		
			background-color:#6eaf37; 				
			//background-color:#89a54e; 
			//background-image: url(images/green_bg3.png);
		}
		.greenBar:hover {
			//-moz-transform: scale(2);
			//background-color:#89a54e; 
		}
		.pgPercent{
			width:20%;
			float: left;
			color: #4573a7;
			font-weight:bold;
			display:inline-block;'
		}

</style>	
<script type="text/javascript">
$(function() 
{
	$(document).ready(function () 
	{		
		// OUTER-LAYOUT
		$('body').layout(
		{
			center__paneSelector:	".outer-center"	,	
			east__paneSelector:		".outer-east",		
			east__size:				250,
			//onresize: resizeGrid,
			spacing_open:			8 , // ALL panes
			spacing_closed:			12, // ALL panes
			north__minSize:			90,		
			south__maxSize:			60,
			south__resizable:	false,			
			south__closable:	false,	
			north__resizable:	false,			
			north__closable:	false,	
			// MIDDLE-LAYOUT (child of outer-center-pane)
			center__childOptions: 
			{				
				center__paneSelector:	".middle-center",		
				// INNER-LAYOUT (child of middle-center-pane)
				center__childOptions: 
				{
					center__paneSelector:	".inner-center",
					south__paneSelector:	".inner-south",		
					south__minSize:			20,
					south__resizable:	false,			
					south__closable:	false,						
					center__childOptions: 
					{
						center__paneSelector:".inner-inner-center",						
						south__paneSelector:".inner-inner-bottom",						
						south__size:			200,
						south__resizable:	true,			
						south__closable:	true,	
						center__childOptions: 
						{
							center__paneSelector:".inner-inner-inner-center",
							east__paneSelector:	".inner-inner-center-east",								
							east__size: 470,								
							center__childOptions: 
							{
								center__paneSelector:".inner-inner-inner-inner-center",
								south__paneSelector:	".inner-inner-inner-south",								
								south__size: 25,	
								south__resizable:	false,			
								south__closable:	false,	
							}
							
						}
						
					}
				}
			}
		});
	
		$("#accordion2").accordion({fillSpace:true, active:0});			
		$("#accordion").accordion(
		{
			autoHeight:true,
			icons:{ "header": "ui-icon-triangle-1-e", "headerSelected": "ui-icon-triangle-1-s"}
		}); 
		$("#deleteProject" ).button();
		$("#applyChanges" ).button();
		$("#cancelChanges" ).button();
		var subGrid = jQuery("#sub"); 
		subGrid.jqGrid(
				{
					url : "project/relatedObjects/${project.projectID}${relatedObjects}",
					height : '100%',
					width : 200,
					datatype : "json",
					colNames : ['Related object',	'Value' , 'RO_Name','RO_ID'],
					colModel : [
									{
										name : 'relatedObjectName',
										width : 20,
										jsonmap:"relatedObjectName",
										sortable : true
									},
									{
										name : 'relatedObjectValue',
										width : 10,
										align : 'center',
										sortable : true,
										resizable : true,
										search : true,
										jsonmap:"relatedObjectValue",
										sorttype : 'number'										
									},
									{
										name : 'relatedObjectPathName',									
										jsonmap:"relatedObjectPathName",
										hidden : true
									},
									{
										name : 'relatedObjectPathID',										
										jsonmap:"relatedObjectPathID",
										hidden : true
									}
								],
					//rowNum: 5,
					//rowList: [5, 10, 20],
					//pager : jQuery('#subpager'),
					//datatype: "local",              
				 	//rowNum: -1,			          		            
		           // loadonce : true,
					sortname: 'relatedobjectID',
					scroll : 1,
					emptyrecords : '0',
					autoload:true,
					viewrecords : true,
					sortorder: "ASC",
					jsonReader : 
					{
						root : "relatedObjects",
						id : "relatedobjectID",
						repeatitems : false,
						page : function(obj) {return 1;},
						total : function(obj) {return 1;},
						records : function(obj) {return obj.relatedObjects.length;}
					},
					ondblClickRow : function(rowId)
					{					    
						var projRow = subGrid.getRowData(rowId);
						//alert("1 : "+projRow.relatedObjectPathName);												
						window.location =  '?userpath=${userpath}>'+ projRow.relatedObjectPathID+'>'+projRow.relatedObjectPathName;
					},
				});
	});	
});
//]]>
</script>

</head>
<body>
<!--
	 ******************************************************************************************************************
	 ************************************              Center objects           *****************************************
	 ******************************************************************************************************************
-->
<div class="outer-center">	
	<div class="middle-center">
		<div class="inner-center" >
			<div style="font-size:10px; padding-bottom:4px;padding-top:0px;padding-left:6px" >			
				${breadCrumb}	
			</div>			
			<div class="ui-layout-content ui-widget-content" style="background:#fff;">		
				<div class="inner-inner-center" >				
					<div class="inner-inner-inner-center" >	
						<div class="inner-inner-inner-inner-center" >	
							${company.projectDisplayName} Details : 				
							
							<table width="100%" style="min-width='500px';">
								<tr>
									<th width="50%" role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
										<div class="ui-jqgrid-sortable">
											Item
										</div>
									</th>
									<th width="50%" role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
										<div class="ui-jqgrid-sortable">
											Value
										</div>
									</th>
								</tr>
								<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr">
								
									<td role="gridcell" style="text-align:left;" title="${project.projectID}">
										${company.projectDisplayName} ID
									</td>
									<td role="gridcell" style="text-align:right;" title="${project.projectID}">
										${project.projectID}
									</td>
								
								</tr>
								<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr ui-priority-secondary " >
									<td role="gridcell" style="text-align:left;" title="${company.projectDisplayName} Name">
										${company.projectDisplayName} Name
									</td>
									<td role="gridcell" style="text-align:right;" title="${project.projectName}">
										<input type="text" value="${project.projectName}" size="30"/>
									</td>								
								</tr>
								<c:if test="${not empty project.parentID}"> 
									<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr  " >
										<td role="gridcell" style="text-align:left;" title="${company.projectDisplayName} Parent ID ">
											${company.projectDisplayName} Parent ID
										</td>
										<td role="gridcell" style="text-align:right;" title="${project.parentID}">
											<input type="text" value="${project.parentID}" size="30"/>
										</td>								
									</tr>
								</c:if>
								<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr ui-priority-secondary " >
									<td role="gridcell" style="text-align:left;" title="Parent">
										Parent 
									</td>
									<td role="gridcell" style="text-align:right;" title="${project.projectName}">
										<c:if test="${project.parent}"> 
											<img src="images/ok.png" />
										</c:if>									
									</td>								
								</tr>
								<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr" >
									<td role="gridcell" style="text-align:left;" title="Child">
										Child 
									</td>
									<td role="gridcell" style="text-align:right;" >
										<c:if test="${project.child}"> 
											<img src="images/ok.png" />
										</c:if>									
									</td>								
								</tr>
								<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr ui-priority-secondary" >
									<td role="gridcell" style="text-align:left;" title="Allowed Sev 1's ">
										Allowed Sev 1's 
									</td>
									<td role="gridcell" style="text-align:right;" title="${project.allowedSev1}	" >
										<input type="text" value="${project.allowedSev1}" size="2" style="text-align:right;"/>		
									</td>								
								</tr>
								<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr" >
									<td role="gridcell" style="text-align:left;" title="Allowed Sev 2's ">
										Allowed Sev 2's 
									</td>
									<td role="gridcell" style="text-align:right;" title="${project.allowedSev2}	" >
										<input type="text" value="${project.allowedSev2}" size="2" style="text-align:right;"/>			
									</td>								
								</tr>
								<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr ui-priority-secondary" >
									<td role="gridcell" style="text-align:left;" title="Allowed Sev 3's ">
										Allowed Sev 3's 
									</td>
									<td role="gridcell" style="text-align:right;" title="${project.allowedSev3}	" >
										<input type="text" value="${project.allowedSev3}" size="2" style="text-align:right;"/>		
									</td>								
								</tr>
								<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr" >
									<td role="gridcell" style="text-align:left;" title="Allowed Sev 4's ">
										Allowed Sev 4's 
									</td>
									<td role="gridcell" style="text-align:right;" title="${project.allowedSev4}	" >
										<input type="text" value="${project.allowedSev4}" size="2" style="text-align:right;"/>			
									</td>								
								</tr>
								
								<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr ui-priority-secondary" >
									<td role="gridcell" style="text-align:left;" title="Creation Date">
										Creation Date
									</td>
									<td role="gridcell" style="text-align:right;" title="${project.allowedSev3}	" >
										${project.creationDate}			
									</td>								
								</tr>
								<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr" >
									<td role="gridcell" style="text-align:left;" title="Created By">
										Created By
									</td>
									<td role="gridcell" style="text-align:right;" title="${project.allowedSev4}	" >
										${project.createdBy}			
									</td>								
								</tr>
								<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr ui-priority-secondary" >
									<td role="gridcell" style="text-align:left;" title="Last modified Date">
										Last modified Date
									</td>
									<td role="gridcell" style="text-align:right;" title="${project.allowedSev3}	" >
										${project.lastModifiedDate}			
									</td>								
								</tr>
								<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr" >
									<td role="gridcell" style="text-align:left;" title="Last modified By">
										Last modified By
									</td>
									<td role="gridcell" style="text-align:right;" title="${project.allowedSev4}	" >
										${project.lastModifiedBy}			
									</td>								
								</tr>
							</table>	
							</div>
							<div class="inner-inner-inner-south" >								
								<button id="deleteProject"> Delete </button>
								&nbsp; | &nbsp;
								<button id="applyChanges"> Apply </button>
								<button id="cancelChanges"> Cancel </button>
							</div>					
					</div>
					<div class="inner-inner-center-east" >
					${defectsDisplayName}:
					<table width="100%">
							<tr>
								<th width="10%" role="columnheader" >
									<font size="2"></font>									
								</th>
								<th width="10%" role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">Allowed</font>
									</div>
								</th>
								<th width="10%" role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">Current</font>
									</div>
								</th>
								<th width="70%" role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">State</font>
									</div>
								</th>
							</tr>																				
							<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr">
								<td role="gridcell" style="text-align:left;" title="overall" class="ui-state-default ui-th-column ui-th-ltr">
									<font size="1">Overall</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Total Defects Allowed">
									<font size="1">allowed</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Current Total Defects">
									<font size="1">Current</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Overall State">
									<div style='width:100%;height:18px;' class='progressBarDiv' >
										<div class='completeBar' style='height:18px;width:80%; float: left;' title="80% Complete">
											&nbsp; 
										</div>			
										<div class='notCompleteBar' style='height:18px;width:20%;float: right;' title="20% InComplete"> 
											&nbsp;
										</div>
									</div>	
								</td>
							</tr>						    
						    <tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr ui-priority-secondary">
								<td role="gridcell" style="text-align:left;" title="Sev 1" class="ui-state-default ui-th-column ui-th-ltr">
									<font size="1">Sev 1</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Total Sev 1 Allowed">
									<font size="1">allowed</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Current Sev 1 Defects">
									<font size="1">Current</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Sev 1 State">
									<div style='width:100%;height:18px;' class='progressBarDiv' >
										<div class='completeBar' style='height:18px;width:80%; float: left;' title="80% Complete">
											&nbsp; 
										</div>			
										<div class='notCompleteBar' style='height:18px;width:20%;float: right;' title="20% InComplete"> 
											&nbsp;
										</div>
									</div>	
								</td>
							</tr>		
							<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr">
								<td role="gridcell" style="text-align:left;" title="Sev 2" class="ui-state-default ui-th-column ui-th-ltr">
									<font size="1">Sev 2</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Total Sev 2 Allowed">
									<font size="1">allowed</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Current Sev 2 Defects">
									<font size="1">Current </font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Sev 2 State">
									<div style='width:100%;height:18px;' class='progressBarDiv' >
										<div class='completeBar' style='height:18px;width:80%; float: left;' title="80% Complete">
											&nbsp; 
										</div>			
										<div class='notCompleteBar' style='height:18px;width:20%;float: right;' title="20% InComplete"> 
											&nbsp;
										</div>
									</div>	
								</td>
							</tr>				
							<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr ui-priority-secondary">
								<td role="gridcell" style="text-align:left;" title="Sev 3" class="ui-state-default ui-th-column ui-th-ltr">
									<font size="1">Sev 3</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Total Sev 3 Allowed">
									<font size="1">allowed</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Current Sev 3 Defects">
									<font size="1">Current</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Sev 3 State">
									<div style='width:100%;height:18px;' class='progressBarDiv' >
										<div class='completeBar' style='height:18px;width:80%; float: left;' title="80% Complete">
											&nbsp; 
										</div>			
										<div class='notCompleteBar' style='height:18px;width:20%;float: right;' title="20% InComplete"> 
											&nbsp;
										</div>
									</div>	
								</td>
							</tr>				
							<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr">
								<td role="gridcell" style="text-align:left;" title="Sev 4" class="ui-state-default ui-th-column ui-th-ltr">
									<font size="1">Sev 4</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Total Sev 4 Allowed">
									<font size="1">allowed</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Current Sev 4 Defects">
									<font size="1">Current </font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Sev 4 State">
									<div style='width:100%;height:18px;' class='progressBarDiv' >
										<div class='completeBar' style='height:18px;width:80%; float: left;' title="80% Complete">
											&nbsp; 
										</div>			
										<div class='notCompleteBar' style='height:18px;width:20%;float: right;' title="20% InComplete"> 
											&nbsp;
										</div>
									</div>	
								</td>
							</tr>									
						</table>		
											
					</div>
				</div>		
				<div class="inner-inner-bottom" >
						${cyclesDisplayName}:
						<table width="100%">
							<tr>
								<th width="20" class="ui-state-default ui-th-column ui-th-ltr">
									<font size="2">ID</font>									
								</th>
								<th width="150" role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">${company.cycleDisplayName} Name</font>
									</div>
								</th>
								<th width="300" role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">${company.cycleDisplayName} State</font>
									</div>
								</th>
								<th width="20" role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">${company.testrunsDisplayName}</font>
									</div>
								</th>
								<th width="20" role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">Complete</font>
									</div>
								</th>
								<th width="20" role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">Passed</font>
									</div>
								</th>
								<th width="20" role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">Failed</font>
									</div>
								</th>
								<th width="20" role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">Deferred</font>
									</div>
								</th>
								<th width="20" role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">Blocked</font>
									</div>
								</th>
								<th width="20" role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">Incomplete</font>
									</div>
								</th>
								<th width="20" role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">Notrun</font>
									</div>
								</th>
								<th width="20" role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">In progress</font>
									</div>
								</th>
							</tr>																				
							<c:forEach var="cycle" items="${project.cycles}" varStatus="cyclecount2" >	
							<c:choose>
								<c:when test="${cyclecount2.index % 2 == 0}">
						            <tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr">
								</c:when>
						        <c:otherwise>
						            <tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr ui-priority-secondary" >
								</c:otherwise>
							</c:choose>
									<td role="gridcell" style="text-align:right;" title="${cycle.cycleID}">
										<font size="1">${cycle.cycleID}</font>
									</td>
									<td role="gridcell" style="text-align:left;" title="${cycle.cycleName}">
										<font size="1">${cycle.cycleName}</font>
									</td>								
									<td>
										<div style='width:100%;height:18px;' class='progressBarDiv' >
											<div class='completeBar' style='height:18px;width:80%; float: left;' title="80% Complete">
												&nbsp; 
											</div>			
											<div class='notCompleteBar' style='height:18px;width:20%;float: right;' title="20% InComplete"> 
												&nbsp;
											</div>
										</div>	
									</td>
									<td role="gridcell" style="text-align:left;" title="">
										<font size="1"></font>
									</td>
									<td role="gridcell" style="text-align:left;" title="">
										<font size="1"></font>
									</td>
									<td role="gridcell" style="text-align:left;" title="">
										<font size="1"></font>
									</td>
									<td role="gridcell" style="text-align:left;" title="">
										<font size="1"></font>
									</td>
									<td role="gridcell" style="text-align:left;" title="">
										<font size="1"></font>
									</td>
									<td role="gridcell" style="text-align:left;" title="">
										<font size="1"></font>
									</td>
									<td role="gridcell" style="text-align:left;" title="">
										<font size="1"></font>
									</td>
									<td role="gridcell" style="text-align:left;" title="">
										<font size="1"></font>
									</td>	
									<td role="gridcell" style="text-align:left;" title="">
										<font size="1"></font>
									</td>	
								</tr>
							</c:forEach>
						</table>	
				</div>			
			</div>			
		</div>	
	</div>
</div>
<!--
	 ******************************************************************************************************************
	 ************************************              EAST objects           *****************************************
	 ******************************************************************************************************************
-->
<div class="outer-east">
	<div class="ui-layout-content">	
	<div id="accordion2" class="basic" >
	<h3 style="height: 30px; font-size:12px; padding-top: 4px;"><a href="#">Related Objects</a></h3>
			<div style="background:#FFF;">					
				* Double Click object to view		
				<table id="sub"></table>
				<div id="subpager"></div>
			</div>
		<h3 style="height: 30px; font-size:12px; padding-top: 4px;"><a href="#">Quick Tasks</a></h3>
			<div>	
				<fieldset style="width:90%;">
					<legend>Test libray:</legend>
					<div id="fieldS" >
					    Execute Teat Run <BR/>
						Execute Test Plan<BR/>
					</div>
				</fieldset>
				<fieldset style="width:90%;">
					<legend>Defect:</legend>
					<div id="fieldS" >
						Create Defect <BR />
						Update Defect<BR />
					</div>
				</fieldset>
					<fieldset style="width:90%;">
					  <legend>Requirement:</legend>
						<div id="fieldS" >
							Execute Teat Run <BR />
							Execute Test Plan<BR />
						</div>
					</fieldset>
					<fieldset style="width:90%;">
					  <legend>Users:</legend>
					<div style="margin-left:10%;">
						Execute Teat Run <BR />
						Execute Test Plan<BR />
					  </div>
					</fieldset>
					<fieldset style="width:90%;">
					  <legend>Cycles:</legend>
						<div id="fieldS" >
							Execute Teat Run <BR />
							Execute Test Plan<BR />
						</div>
					</fieldset>
				</div>

			<h3 style="height: 30px; font-size:12px; padding-top: 4px;"><a href="#">Reports</a></h3>
			<div>				
			</div>

			<h3 style="height: 30px; font-size:12px; padding-top: 4px;"><a href="#">Section 4</a></h3>
			<div>				
			</div>			
		</div>
	</div>
</div>
<!--
	 ******************************************************************************************************************
	 ************************************              north header             *****************************************
	 ******************************************************************************************************************
-->
<div class="ui-layout-north ui-widget-content" style="display: none;">
<table style="width:100%;">
	<tr>
		<td align="left" style="width:50%;color:#456e8c;">
			<img style="height:25px;" src ="images/title.png" />			
						
		</td>
		<td align="right" style="width:50%;color:#456e8c;" valign="middle">
			${companyName}	&nbsp; <a href="editcompany.html"><img height="25" width="25" src="images/settings.png" /></a>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<!--
			 ******************************************************************************************************************
			 ************************************         Nav bar     *****************************************
			 ******************************************************************************************************************
		-->
			<ul id="menu">
			    <li style="padding-left:30px"><a href="index.html" ><img src="images/home.jpg" height="15px" /> Home </a><!-- Begin Home Item --></li><!-- End Home Item --> 
			    <li><a href="?userpath=${fn:replace(projectsDisplayName,' ', '')}" class="drop">${projectsDisplayName}</a><!-- Begin 4 columns Item --></li><!-- End 5 columns Item -->    	
			    <li><a href="reports.html" class="drop">${reportsDisplayName}</a><!-- Begin 4 columns Item --> </li><!-- End 4 columns Item -->				
				<li><a href="defects.html" class="drop">${defectsDisplayName}</a><!-- Begin 4 columns Item --></li><!-- End 4 columns Item -->
			    <li><a href="?userpath=${fn:replace(cyclesDisplayName,' ', '')}" class="drop">${cyclesDisplayName}</a><!-- Begin 4 columns Item --></li><!-- End 4 columns Item -->
			   	<li><a href="?userpath=${fn:replace(testLibraryDisplayName,' ', '')}" class="drop">${testLibraryDisplayName}</a>
			   	 <div class="dropdown_3columns align_right">  
			            <div class="col_3">
			           		<table>
							<tr>
								<td rowspan="2">
									<a href="?userpath=${fn:replace(testplansDisplayName,' ', '')}">
										<img src="images/testplan.jpg" width="70" height="70" class="img_left imgshadow" alt="" />	
									</a>
								</td>
								<td>
									<a href="?userpath=${fn:replace(testplansDisplayName,' ', '')}"><b>${testplansDisplayName}</b></a>
								</td>
							</tr>
							<tr>
								<td>
									<p>
						                Contains a List of all ${testplansDisplayName} for this company. A ${testplanDisplayName} is a container for one or multiple ${testcasesDisplayName}
						            </p>
								</td>								   
							</tr>						
							</table>
							<table>
							<tr>
								<td rowspan="2">
									<a href="?userpath=${fn:replace(testcasesDisplayName,' ', '')}">
										<img src="images/testcase.jpg" width="70" height="70" class="img_left imgshadow" alt="" />	
									</a>
								</td>
								<td>
									<a href="?userpath=${fn:replace(testcasesDisplayName,' ', '')}"><b>${testcasesDisplayName}</b></a>
								</td>
							</tr>
							<tr>
								<td>
									<p>
						               Contains a List of all ${testcasesDisplayName} for this company. A ${testcaseDisplayName} is used to examine an aspect of a specific requirement for a given environment
				           </p>
								</td>								   
							</tr>						
							</table>		
							<table>
							<tr>
								<td rowspan="2">
									<a href="?userpath=${fn:replace(testrunsDisplayName,' ', '')}">
										<img src="images/testrun.png" width="70" height="70" class="img_left imgshadow" alt="" />	
									</a>
								</td>
								<td>
									<a href="?userpath=${fn:replace(testrunsDisplayName,' ', '')}"><b>${testrunsDisplayName}</b></a>
								</td>
							</tr>
							<tr>
								<td>
									<p>
						               Contains a List of all ${testrunsDisplayName} for this company. A ${testrunDisplayName} is the result of executing a ${testcaseDisplayName} for a specified ${cycleDisplayName}
				           			</p>
								</td>								   
							</tr>						
							</table>							
			            </div>			        
			        </div>
			   	</li><!-- End 4 columns Item -->		
				<li><a href="requirements.html" class="drop">${requirementsDisplayName}</a></li><!-- End 4 columns Item -->   
				<li><a href="users.html" class="drop">${usersDisplayName}</a> </li><!-- End 4 columns Item -->    
				<li><a href="environments.html" class="drop">${environmentsDisplayName}</a></li>   
			</ul>
		</td>
	</tr>
</table>
</div>
<!--
	 ******************************************************************************************************************
	 ************************************         End of north header         *****************************************
	 ******************************************************************************************************************
-->
<!--
	 ******************************************************************************************************************
	 ************************************            South Footer             *****************************************
	 ******************************************************************************************************************
-->
<div class="ui-layout-south" style="">
<table style="width:100%">
	<tr>
		<td style="width:50%">
			User : Kenneth
		</td>
		<td style="width:50%">
			Role : Tester
		</td>
	</tr>
</table>
</div>
</body>
</html>