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
	//-moz-box-shadow: 0px 0px 4px #333; 
	//-webkit-box-shadow: 0px 0px 4px #333; 
    //box-shadow: 1px 1px 3px #333; 
	-webkit-box-shadow: #999 1px 2px 3px;
	-moz-box-shadow: #999 1px 1px 3px;
    box-shadow: 1px 1px 2px #333; 
	overflow: hidden;
}
.progressBarDiv:hover {
	//zoom: 200%;		
	//.failedBar.background-color:#333;
}
.progressBarDivSmall
{ 
	//-moz-box-shadow: 0px 0px 4px #333; 
	//-webkit-box-shadow: 0px 0px 4px #333; 
    //box-shadow: 1px 1px 3px #333; 
	-webkit-box-shadow: #999 1px 2px 3px;
	-moz-box-shadow: #999 1px 1px 3px;
    box-shadow: 1px 1px 3px #333; 
	width:100%;
	display:inline-block;	
}
.progressBarDivSmall:hover {
	//zoom: 200%;		
	//.failedBar.background-color:#333;
}
.barchartTextSmall
{
	vertical-align:middle;
	text-align:center;
	color:#ffffff;	
	font-size:11px;
	display:inline-block;	
	-webkit-margin-before: 0.2em;
	-webkit-margin-after: 0px;
	-webkit-margin-start: 0px;
	-webkit-margin-end: 0px;
}
.barchartText
{
	vertical-align:middle;
	text-align:center;
	color:#ffffff;	
	font-size:11px;
	display:block;	
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
.passedBar 
{			
	background-color:#5dad58; 
	height:30px;
	float: left;
	display:inline-block; 
	overflow: hidden;
}
.passedBar:hover 
{
	opacity:0.7;
	filter:alpha(opacity=70); /* For IE8 and earlier */
	//background-color:#6eaf37; 	
	
}
.notRunBar
{ 		
	background-color:#4573a7;	
	height:30px;
	float: left;
	display:inline-block; 
	overflow: hidden;
}
.notRunBar:hover 
{ 		
	opacity:0.7;
	filter:alpha(opacity=70); /* For IE8 and earlier */
	//background-color:#456e8c;
}
.failedBar
{ 	
	background-color:#AE3B37; 	
	height:30px;
	float: left;
	display:inline-block; 	
	overflow: hidden;
}
.failedBar:hover {
	opacity:0.7;
	filter:alpha(opacity=70); /* For IE8 and earlier */
	//background-color:#A64E5E; 
}	
.deferredBar 
{					
	background-color:#997db0; 
	height:30px;
	float: left;
	display:inline-block; 
	overflow: hidden;
}
.deferredBar:hover {
	opacity:0.7;
	filter:alpha(opacity=70); /* For IE8 and earlier */
	//background-color:#A64E5E; 
}
.blockedBar 
{					
	background-color:#555555; 	
	height:30px;
	float: left;
	display:inline-block; 	
	overflow: hidden;
}
.blockedBar:hover {
	opacity:0.7;
	filter:alpha(opacity=70); /* For IE8 and earlier */
	//background-color:#A64E5E; 
}
.inProgressBar 
{					
	background-color:#f57f1d; 
	//background-color:#ff965f; 
	height:30px;
	float: left;
	display:inline-block; 
	overflow: hidden;
	
}
.inProgressBar:hover {
	opacity:0.7;
	filter:alpha(opacity=70); /* For IE8 and earlier */
	//background-color:#A64E5E; 
}
.hiddencompleteBar 
{		
	height:30px;
	float: left;
	display:inline-block; 	
}
.hiddennotCompleteBar 
{		
 	height:30px;
	float: right;
	display:inline-block; 		
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
		

		var infoTips = $(".infoTips");
		function updateInfoTips(t) {
			infoTips.text(t).addClass("ui-state-highlight");			
		}
		
		$("#accordion2").accordion({fillSpace:true, active:0});			
		$("#accordion").accordion(
		{
			autoHeight:true,
			icons:{ "header": "ui-icon-triangle-1-e", "headerSelected": "ui-icon-triangle-1-s"}
		}); 
		$("#deleteProject" ).button();
		var projectName = $("#projectName"),		
		allowedSev1 = $("#allowedSev1"), 
		allowedSev2 = $("#allowedSev2"), 
		allowedSev3 = $("#allowedSev3"), 
		allowedSev4 = $("#allowedSev4");
		$("#applyChanges" ).button().click(function() 
		{
			$.post("project/update", 
				{
					projectID : '${project.projectID}',
					projectName : projectName.val(),					
					allowedSev1 : allowedSev1.val(),
					allowedSev2 : allowedSev2.val(),
					allowedSev3 : allowedSev3.val(),
					allowedSev4 : allowedSev4.val()
				}, 
				function(result) 
				{
					if (result == 'ok') 
					{	
						updateInfoTips("Updated succesfully");						
						window.location.reload();
					}
					else 
					{
						updateInfo(result);
					}
				});
		});
		
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
						window.location =  '?userpath=${userpath}>'+projRow.relatedObjectPathName;
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
										<input type="text" name="projectName" id="projectName"  value="${project.projectName}"/>
									</td>								
								</tr>
								<c:if test="${not empty project.parentID}"> 
									<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr  " >
										<td role="gridcell" style="text-align:left;" title="${company.projectDisplayName} Parent ID ">
											${company.projectDisplayName} Parent ID
										</td>
										<td role="gridcell" style="text-align:right;" title="${project.parentID}">
											<input type="text" value="${project.parentID}" />						
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
										<input type="text" name="allowedSev1" id="allowedSev1" value="${project.allowedSev1}" size="2" style="text-align:right;"/>		
									</td>								
								</tr>
								<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr" >
									<td role="gridcell" style="text-align:left;" title="Allowed Sev 2's ">
										Allowed Sev 2's 
									</td>
									<td role="gridcell" style="text-align:right;" title="${project.allowedSev2}	" >
										<input type="text" name="allowedSev2" id="allowedSev2" value="${project.allowedSev2}" size="2" style="text-align:right;"/>				
									</td>								
								</tr>
								<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr ui-priority-secondary" >
									<td role="gridcell" style="text-align:left;" title="Allowed Sev 3's ">
										Allowed Sev 3's 
									</td>
									<td role="gridcell" style="text-align:right;" title="${project.allowedSev3}	" >
										<input type="text" name="allowedSev3" id="allowedSev3" value="${project.allowedSev3}" size="2" style="text-align:right;"/>			
									</td>								
								</tr>
								<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr" >
									<td role="gridcell" style="text-align:left;" title="Allowed Sev 4's ">
										Allowed Sev 4's 
									</td>
									<td role="gridcell" style="text-align:right;" title="${project.allowedSev4}	" >
										<input type="text" name="allowedSev4" id="allowedSev4" value="${project.allowedSev4}" size="2" style="text-align:right;"/>			
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
								<p class="infoTips"></p>
							</div>					
					</div>
					<div class="inner-inner-center-east" >
					
					<fieldset>
					<legend>${defectsDisplayName}</legend>					
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
										<font size="2">${defects}</font>
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
									<c:set var="sumAllowed" value="${project.allowedSev1 + project.allowedSev2 + project.allowedSev3 + project.allowedSev4}" scope="page" />	
									<font size="1">${sumAllowed}</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Current Total Defects">
									<c:set var="sumCurrent" value="${sev1 + sev2 + sev3 + sev4}" scope="page" />									
									<font size="1">${sumCurrent}</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Sev 1 State">
								
								<c:if test="${sumAllowed > sumCurrent}"> 	
									<c:set var="totalDefectPercentComplete" value="${(sumCurrent / sumAllowed) * 100}" scope="page" />	
									<c:set var="totalDefectPercentCompleteColor" value="passedBar" scope="page" />	
									<c:set var="totalDefectPercentInCompleteColor" value="completeBar" scope="page" />																		
								</c:if>	
								<c:if test="${sumCurrent > sumAllowed}"> 	
									<c:set var="totalDefectPercentComplete" value="${(sumAllowed / sumCurrent ) * 100}" scope="page" />	
									<c:set var="totalDefectPercentCompleteColor" value="inProgressBar" scope="page" />	
									<c:set var="totalDefectPercentInCompleteColor" value="failedBar" scope="page" />										
								</c:if>	
																
									<div style='width:100%;height:18px;' class='progressBarDiv' >
										<div class='${totalDefectPercentCompleteColor}' style='height:18px;width:${totalDefectPercentComplete}%; float: left;' title="${totalDefectPercentComplete} % ">
											&nbsp; 
										</div>			
										<div class='${totalDefectPercentInCompleteColor}' style='height:18px;width:${100 - totalDefectPercentComplete}%;float: right;' title="${100 - totalDefectPercentComplete}% "> 
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
									<font size="1">${project.allowedSev1}</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Current Sev 1 Defects">
									<font size="1">${sev1}</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Sev 1 State">
								
								<c:if test="${project.allowedSev1 > sev1}"> 	
									<c:set var="sev1PercentComplete" value="${(sev1 / project.allowedSev1) * 100}" scope="page" />	
									<c:set var="sev1PercentCompleteColor" value="passedBar" scope="page" />	
									<c:set var="sev1PercentInCompleteColor" value="completeBar" scope="page" />																		
								</c:if>	
								<c:if test="${sev1 > project.allowedSev1}"> 	
									<c:set var="sev1PercentComplete" value="${(project.allowedSev1 / sev1 ) * 100}" scope="page" />	
									<c:set var="sev1PercentCompleteColor" value="inProgressBar" scope="page" />	
									<c:set var="sev1PercentInCompleteColor" value="failedBar" scope="page" />										
								</c:if>	
																
									<div style='width:100%;height:18px;' class='progressBarDiv' >
										<div class='${sev1PercentCompleteColor}' style='height:18px;width:${sev1PercentComplete}%; float: left;' title="${sev1PercentComplete} % ">
											&nbsp; 
										</div>			
										<div class='${sev1PercentInCompleteColor}' style='height:18px;width:${100 - sev1PercentComplete}%;float: right;' title="${100 - sev1PercentComplete}% "> 
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
									<font size="1">${project.allowedSev2}</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Current Sev 2 Defects">
									<font size="1">${sev2} </font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Sev 2 State">
								
								<c:if test="${project.allowedSev2 > sev2}"> 	
									<c:set var="sev2PercentComplete" value="${(sev2 / project.allowedSev2) * 100}" scope="page" />	
									<c:set var="sev2PercentCompleteColor" value="passedBar" scope="page" />	
									<c:set var="sev2PercentInCompleteColor" value="completeBar" scope="page" />																		
								</c:if>	
								<c:if test="${sev2 > project.allowedSev2}"> 	
									<c:set var="sev2PercentComplete" value="${(project.allowedSev2 / sev2 ) * 100}" scope="page" />	
									<c:set var="sev2PercentCompleteColor" value="inProgressBar" scope="page" />	
									<c:set var="sev2PercentInCompleteColor" value="failedBar" scope="page" />										
								</c:if>	
																
									<div style='width:100%;height:18px;' class='progressBarDiv' >
										<div class='${sev2PercentCompleteColor}' style='height:18px;width:${sev2PercentComplete}%; float: left;' title="${sev2PercentComplete} % ">
											&nbsp; 
										</div>			
										<div class='${sev2PercentInCompleteColor}' style='height:18px;width:${100 - sev2PercentComplete}%;float: right;' title="${100 - sev2PercentComplete}% "> 
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
									<font size="1">${project.allowedSev3}</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Current Sev 3 Defects">
									<font size="1">${sev3}</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Sev 1 State">
								
								<c:if test="${project.allowedSev3 > sev3}"> 	
									<c:set var="sev3PercentComplete" value="${(sev3 / project.allowedSev3) * 100}" scope="page" />	
									<c:set var="sev3PercentCompleteColor" value="passedBar" scope="page" />	
									<c:set var="sev3PercentInCompleteColor" value="completeBar" scope="page" />																		
								</c:if>	
								<c:if test="${sev3 > project.allowedSev3}"> 	
									<c:set var="sev3PercentComplete" value="${(project.allowedSev3 / sev3 ) * 100}" scope="page" />	
									<c:set var="sev3PercentCompleteColor" value="inProgressBar" scope="page" />	
									<c:set var="sev3PercentInCompleteColor" value="failedBar" scope="page" />										
								</c:if>	
																
									<div style='width:100%;height:18px;' class='progressBarDiv' >
										<div class='${sev3PercentCompleteColor}' style='height:18px;width:${sev3PercentComplete}%; float: left;' title="${sev3PercentComplete} % ">
											&nbsp; 
										</div>			
										<div class='${sev3PercentInCompleteColor}' style='height:18px;width:${100 - sev3PercentComplete}%;float: right;' title="${100 - sev3PercentComplete}% "> 
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
									<font size="1">${project.allowedSev4}</font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Current Sev 4 Defects">
									<font size="1">${sev4} </font>
								</td>
								<td role="gridcell" style="text-align:right;" title="Sev 1 State">
								
								<c:if test="${project.allowedSev4 > sev4}"> 	
									<c:set var="sev4PercentComplete" value="${(sev4 / project.allowedSev4) * 100}" scope="page" />	
									<c:set var="sev4PercentCompleteColor" value="passedBar" scope="page" />	
									<c:set var="sev4PercentInCompleteColor" value="completeBar" scope="page" />																		
								</c:if>	
								<c:if test="${sev4 > project.allowedSev4}"> 	
									<c:set var="sev4PercentComplete" value="${(project.allowedSev4 / sev4 ) * 100}" scope="page" />	
									<c:set var="sev4PercentCompleteColor" value="inProgressBar" scope="page" />	
									<c:set var="sev4PercentInCompleteColor" value="failedBar" scope="page" />										
								</c:if>	
																
									<div style='width:100%;height:18px;' class='progressBarDiv' >
										<div class='${sev4PercentCompleteColor}' style='height:18px;width:${sev4PercentComplete}%; float: left;' title="${sev4PercentComplete} % ">
											&nbsp; 
										</div>			
										<div class='${sev4PercentInCompleteColor}' style='height:18px;width:${100 - sev4PercentComplete}%;float: right;' title="${100 - sev4PercentComplete}% "> 
											&nbsp;
										</div>
									</div>	
								</td>
							</tr>									
						</table>	
						</fieldset>	
						<!-- ********************* Cycles ************************************** -->
						<fieldset>
						<legend>${cyclesDisplayName}</legend>						
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
										<font size="2">State</font>
									</div>
								</th>	
								
							</tr>	
							
							<c:set var="cycleTotalTestruns" value="0" scope="page"/>
							<c:set var="cycleTotalTestrunsComplete" value="0" scope="page"/>							
							<c:set var="cycleTotalTestrunsPassed" value="0" scope="page"/>
							<c:set var="cycleTotalTestrunsFailed" value="0" scope="page"/>
							<c:set var="cycleTotalTestrunsDeferred" value="0" scope="page"/>
							<c:set var="cycleTotalTestrunsBlocked" value="0" scope="page"/>
							<c:set var="cycleTotalTestrunsIncomplete" value="0" scope="page"/>
							<c:set var="cycleTotalTestrunsNotrun" value="0" scope="page"/>
							<c:set var="cycleTotalTestrunsInprog" value="0" scope="page"/>
							<c:set var="eachCycleTotalTestruns" value="0" scope="page"/>										
							<c:set var="eachCycleTotalTestrunsComplete" value="0" scope="page"/>
																														
							<c:forEach var="cycle" items="${project.cycles}" varStatus="cycleIndex" >								
								<c:if test="${cycle.latest == true}"> 	
									<c:set var="latestCycleID" value="${cycle.cycleID}" scope="page" />
									<c:set var="latestCycleName" value="${cycle.cycleName}" scope="page" />
								</c:if>																										
								
								<c:forEach var="testrun" items="${cycle.testruns}" varStatus="testrunIndex" >	
										<c:set var="eachCycleTotalTestruns" value="${eachCycleTotalTestruns + 1}" scope="page"/>
										
										<c:choose>
											<c:when test="${testrun.passed || testrun.failed || testrun.deferred || testrun.blocked}">	
												<c:if test="${cycle.latest == true}"> 							
													<c:set var="cycleTotalTestrunsComplete" value="${cycleTotalTestrunsComplete + 1}" scope="page" />													
												</c:if>
												<c:set var="eachCycleTotalTestrunsComplete" value="${eachCycleTotalTestrunsComplete + 1}" scope="page"/>
											</c:when>
											<c:otherwise>
												<c:if test="${cycle.latest == true}"> 	
													<c:set var="cycleTotalTestrunsIncomplete" value="${cycleTotalTestrunsIncomplete + 1}" scope="page" />
												</c:if>	
											</c:otherwise>
										</c:choose>	
										<c:if test="${cycle.latest == true}"> 	
										<c:set var="cycleTotalTestruns" value="${cycleTotalTestruns + 1}" scope="page"/>
										
										<c:if test="${testrun.passed}"> 
											<c:set var="cycleTotalTestrunsPassed" value="${cycleTotalTestrunsPassed + 1}" scope="page"/>	
										</c:if>
										<c:if test="${testrun.failed}"> 
											<c:set var="cycleTotalTestrunsFailed" value="${cycleTotalTestrunsFailed + 1}" scope="page"/>	
										</c:if>
										<c:if test="${testrun.deferred}"> 
											<c:set var="cycleTotalTestrunsDeferred" value="${cycleTotalTestrunsDeferred + 1}" scope="page"/>	
										</c:if>
										<c:if test="${testrun.blocked}"> 
											<c:set var="cycleTotalTestrunsBlocked" value="${cycleTotalTestrunsBlocked + 1}" scope="page"/>	
										</c:if>
										<c:if test="${testrun.notrun}"> 
											<c:set var="cycleTotalTestrunsNotrun" value="${cycleTotalTestrunsNotrun + 1}" scope="page"/>	
										</c:if>
										<c:if test="${testrun.inprogress}"> 
											<c:set var="cycleTotalTestrunsInprog" value="${cycleTotalTestrunsInprog + 1}" scope="page"/>	
										</c:if>
									</c:if>											
								</c:forEach>								
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
									<c:set var="percentComplete" value="${(eachCycleTotalTestrunsComplete/eachCycleTotalTestruns) * 100}" scope="page"/>
								
									
										<div style='width:100%;height:18px;display:inline-block;float: left;' class='progressBarDiv' >
											<div class='completeBar' style='height:18px;width:${percentComplete}%; float: left;' title="${percentComplete} % Complete">
													&nbsp; 
											</div>			
											<div class='notCompleteBar' style='height:18px;width:${ 100 - percentComplete}%;float: right;' title="${100 - percentComplete}% InComplete"> 
												&nbsp;
											</div>
										</div>		
									</td>									
								</tr>
							</c:forEach>
						</table>	
						</fieldset>			
					</div>
				</div>		
				<div class="inner-inner-bottom" >
						<fieldset>
						<legend> ${latestCycleName}</legend>						
						<table width="100%" border="0">
							<tr>
								<th class="ui-state-default ui-th-column ui-th-ltr">
									<font size="2">ID</font>									
								</th>								
								<th role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">${company.testrunsDisplayName}</font>
									</div>
								</th>
								<th role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">Complete</font>
									</div>
								</th>
								<th  role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">Passed</font>
									</div>
								</th>
								<th  role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">Failed</font>
									</div>
								</th>
								<th role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">Deferred</font>
									</div>
								</th>
								<th  role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">Blocked</font>
									</div>
								</th>
								<th role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">Incomplete</font>
									</div>
								</th>
								<th  role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">Notrun</font>
									</div>
								</th>
								<th  role="columnheader" class="ui-state-default ui-th-column ui-th-ltr" >
									<div class="ui-jqgrid-sortable">
										<font size="2">In progress</font>
									</div>
								</th>
							</tr>																								
							<tr role="row" tabindex="0" class="ui-widget-content jqgrow ui-row-ltr">
									<td role="gridcell" style="text-align:center;" title="">
										<font size="1">
											${latestCycleID}
										</font>
									</td>									
									<td role="gridcell" style="text-align:center;" title="">
										<font size="1"> ${cycleTotalTestruns}</font>
									</td>
									<td role="gridcell" style="text-align:center;" title="">
										<font size="1"> ${cycleTotalTestrunsComplete}  </font>
									</td>
									<td role="gridcell" style="text-align:center;" title="">
										<font size="1"> ${cycleTotalTestrunsPassed}</font>
									</td>
									<td role="gridcell" style="text-align:center;" title="">
										<font size="1"> ${cycleTotalTestrunsFailed} </font>
									</td>
									<td role="gridcell" style="text-align:center;" title="">
										<font size="1"> ${cycleTotalTestrunsDeferred} </font>
									</td>
									<td role="gridcell" style="text-align:center;" title="">
										<font size="1"> ${cycleTotalTestrunsBlocked}</font>
									</td>
									<td role="gridcell" style="text-align:center;" title="">
										<font size="1"> ${cycleTotalTestrunsIncomplete}  </font>
									</td>	
									<td role="gridcell" style="text-align:center;" title="">
										<font size="1"> ${cycleTotalTestrunsNotrun} </font>
									</td>	
									<td role="gridcell" style="text-align:center;" title="">
										<font size="1"> ${cycleTotalTestrunsInprog} </font>
									</td>	
								</tr>						
						</table>	
						</fieldset>			
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
				  <legend>${company.testLibraryDisplayName}:</legend>
					<div id="fieldS" >
						<a href="#">Create ${company.testplanDisplayName} </a><br/>
						<a href="#">Execute ${company.testplanDisplayName} </a><br/>
						<a href="#">Edit ${company.testplanDisplayName}</a> <br/>
						<a href="#">Delete ${company.testplanDisplayName} </a><br/><br/>
						
						<a href="#">Create ${company.testcaseDisplayName} </a><br/>
						<a href="#">Execute ${company.testcaseDisplayName} </a><br/>
						<a href="#">Edit ${company.testcaseDisplayName}</a> <br/>
						<a href="#">Delete ${company.testcaseDisplayName} </a><br/><br/>
					
						<a href="#">Create ${company.testrunDisplayName} </a><br/>
						<a href="#">Execute ${company.testrunDisplayName} </a><br/>
						<a href="#">Edit ${company.testrunDisplayName}</a> <br/>
						<a href="#">Delete ${company.testrunDisplayName} </a><br/>
						
					</div>
				</fieldset>
				<fieldset style="width:90%;">
				  <legend>${company.defectDisplayName}:</legend>
					<div id="fieldS" >
						<a href="#">Create ${company.defectDisplayName} </a><br/>
						<a href="#">Edit ${company.defectDisplayName}</a> <br/>
						<a href="#">Delete ${company.defectDisplayName} </a><br/>
					</div>
				</fieldset>
				<fieldset style="width:90%;">
				  <legend>${company.requirementDisplayName}:</legend>
					<div id="fieldS" >
						<a href="#">Create ${company.requirementDisplayName} </a><br/>
						<a href="#">Edit ${company.requirementDisplayName}</a> <br/>
						<a href="#">Delete ${company.requirementDisplayName} </a><br/>
					</div>
				</fieldset>
				<fieldset style="width:90%;">
				  <legend>${company.environmentsDisplayName}:</legend>
					<div id="fieldS" >
						<a href="#">Create ${company.environmentDisplayName} </a><br/>
						<a href="#">Edit ${company.environmentDisplayName}</a> <br/>
						<a href="#">Delete ${company.environmentDisplayName} </a><br/>
					</div>
				</fieldset>
				<fieldset style="width:90%;">
				  	<legend>${company.usersDisplayName}:</legend>
				  	<div id="fieldS" >
						<a href="#">Create ${company.userDisplayName}</a><br/>
						<a href="#">Edit ${company.userDisplayName} </a><br/>
						<a href="#">Delete ${company.userDisplayName} </a><br/>
					</div>
				</fieldset>
				<fieldset style="width:90%;">
					<legend>${company.cyclesDisplayName}:</legend>
					<div id="fieldS" >
						<a href="#">Create ${company.cycleDisplayName}</a> <br/>
						<a href="#">Edit ${company.cycleDisplayName}</a> <br/>
						<a href="#">Delete ${company.cycleDisplayName}</a> <br/>
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
			<a href="index.html"><img style="height:25px;" src ="images/title.png" /></a>		
		</td>
		<td align="right" style="width:50%;color:#456e8c;" valign="middle">
			${companyName}	
			&nbsp; <a href="editcompany.html?companyID=${company.companyID}"><img height="15" width="15" src="images/settings.png" title="Edit Company Settings" /></a>
			&nbsp; <a href="viewbill.html?companyID=${company.companyID}"><img height="15" width="15" src="images/money.png" title="View Resource Bill"/></a>
			&nbsp; <a href="logout.html"><img height="15" width="15" src="images/exit.png" title="Logout"/></a>
			&nbsp; &nbsp; 
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
			    <li style="padding-left:30px"><a href="index.html" >Home</a><!-- Begin Home Item --></li><!-- End Home Item --> 
			    <li><a href="?userpath=${fn:replace(projectsDisplayName,' ', '')}" class="drop">${projectsDisplayName}</a><!-- Begin 4 columns Item --></li><!-- End 5 columns Item -->    	
			    <li><a href="?userpath=${fn:replace(reportsDisplayName,' ', '')}" class="drop">${reportsDisplayName}</a><!-- Begin 4 columns Item --> </li><!-- End 4 columns Item -->				
				<li><a href="?userpath=${fn:replace(defectsDisplayName,' ', '')}" class="drop">${defectsDisplayName}</a><!-- Begin 4 columns Item --></li><!-- End 4 columns Item -->
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
				<li><a href="?userpath=${fn:replace(requirementsDisplayName,' ', '')}" class="drop">${requirementsDisplayName}</a></li><!-- End 4 columns Item -->   
				<li><a href="?userpath=${fn:replace(usersDisplayName,' ', '')}" class="drop">${usersDisplayName}</a> </li><!-- End 4 columns Item -->    
				<li><a  href="?userpath=${fn:replace(environmentsDisplayName,' ', '')}" class="drop">${environmentsDisplayName}</a></li>   
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