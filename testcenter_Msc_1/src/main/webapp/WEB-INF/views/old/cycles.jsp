
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TestCenter - ${companyID} - Projects</title>
	
	<!-- Needed -->
		
		<link rel="stylesheet" href="css/final/menubar.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="css/final/layout-default-latest.css" type="text/css" />	
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
	.progressBarDiv
		{ 
			
			-moz-box-shadow: 0px 0px 4px #333; 
			-webkit-box-shadow: 0px 0px 4px #333; 
            box-shadow: 1px 1px 3px #333; 
		}
		.progressBarDiv:hover {
			//zoom: 200%;		
			//.redBar.background-color:#333;
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
$(function() {


$(document).ready(function () 
{	
	
		// OUTER-LAYOUT
		$('body').layout(
		{
			center__paneSelector:	".outer-center"	,	
			east__paneSelector:		".outer-east",		
			east__size:				250,
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
				}
			}
		});
		$("#accordion2").accordion({fillSpace:true, active:0});	
		$( "#create-project" ).button();
		$( "#viewDetails" ).button();
		$( "#deleteProject" ).button();

    	var grid = jQuery('#report');
		var subGrid = jQuery("#sub");
///////// Functions For each Row 			
		var functionsMapping = {
			// here we define the implementations of the custom formatter which we use
			"setOverallBarChart" : function(cellValue, opts,rowObject) {
				var barChartHtml = "";
				var remainingPercent = 0;
				var targetreached = false;
				var currentPercent = rowObject.regressionRequiredPercent;
				remainingPercent = 100 - currentPercent;

				barChartHtml = "<div style='width:100%;display:inline-block;'  >";
				barChartHtml = barChartHtml
						+ "<div style='width:75%;display:inline-block;float: left;' class='progressBarDiv' id='progressBarDiv'  >";
				if (targetreached) {
					var barChartHtml = barChartHtml
							+ "<div  class='greenBar' style='width:"+currentPercent+"%; float: left;display:inline-block;'> </div>";
				} else {
					var barChartHtml = barChartHtml
							+ "<div  class='orangeBar' style='width:"+currentPercent+"%;  float: left;display:inline-block;'> </div>";
				}
				var barChartHtml = barChartHtml
						+ "<div class='redBar' style='width:"+remainingPercent+"%;float: left; display:inline-block;'> </div>";
				var barChartHtml = barChartHtml + "</div>";
				var barChartHtml = barChartHtml
						+ "<div id='spacer' style='width:5%; float: left;display:inline-block;'>&nbsp;</div>";
				var barChartHtml = barChartHtml
						+ "<div class='pgPercent'>"
						+ rowObject.regressionRequiredPercent
						+ "% </div>";
				var barChartHtml = barChartHtml + "</div>";
				return barChartHtml;
			},

			"setRegressionBarChart" : function(cellValue, opts,
					rowObject) {
				var barChartHtml = "";
				var remainingPercent = 0;
				var targetreached = true;
				var currentPercent = rowObject.regressionCurrentPercent;
				remainingPercent = 100 - currentPercent;

				barChartHtml = "<div style='width:100%;display:inline-block;'  >";
				barChartHtml = barChartHtml
						+ "<div style='width:75%;display:inline-block;float: left;' class='progressBarDiv' id='progressBarDiv'  >";
				if (rowObject.regressionCurrentPercent >= rowObject.regressionRequiredPercent) {
					var barChartHtml = barChartHtml
							+ "<div  class='greenBar' style='width:80%; float: left;display:inline-block;'> </div>";
				} else {
					var barChartHtml = barChartHtml
							+ "<div  class='orangeBar' style='width:80%; float: left;display:inline-block;'> </div>";
				}
				var barChartHtml = barChartHtml
						+ "<div class='redBar' style='width:20%;float: left; display:inline-block;'> </div>";
				var barChartHtml = barChartHtml + "</div>";
				var barChartHtml = barChartHtml
						+ "<div id='spacer' style='width:5%; float: left;display:inline-block;'>&nbsp;</div>";
				var barChartHtml = barChartHtml
						+ "<div class='pgPercent'>"
						+ rowObject.regressionCurrentPercent
						+ "% </div>";
				var barChartHtml = barChartHtml + "</div>";
				return barChartHtml;
			},
			"setNewFeatureBarChart" : function(cellValue, opts,
					rowObject) {
				var barChartHtml = "";
				var remainingPercent = 0;
				var targetreached = false;
				var currentPercent = rowObject.newFeatureCurrentPercent;
				remainingPercent = 100 - currentPercent;

				barChartHtml = "<div style='width:100%;display:inline-block;'  >";
				barChartHtml = barChartHtml
						+ "<div style='width:75%;display:inline-block;float: left;' class='progressBarDiv' id='progressBarDiv'  >";
				if (rowObject.newFeatureCurrentPercent >= rowObject.newFeatureRequiredPercent) {
					var barChartHtml = barChartHtml
							+ "<div  class='greenBar' style='width:"+currentPercent+"%; float: left;display:inline-block;'> </div>";
				} else {
					var barChartHtml = barChartHtml
							+ "<div  class='orangeBar' style='width:"+currentPercent+"%;  float: left;display:inline-block;'> </div>";
				}
				var barChartHtml = barChartHtml
						+ "<div class='redBar' style='width:"+remainingPercent+"%;float: left; display:inline-block;'> </div>";
				var barChartHtml = barChartHtml + "</div>";
				var barChartHtml = barChartHtml
						+ "<div id='spacer' style='width:5%; float: left;display:inline-block;'>&nbsp;</div>";
				var barChartHtml = barChartHtml
						+ "<div class='pgPercent'>"
						+ rowObject.newFeatureCurrentPercent
						+ "% </div>";
				var barChartHtml = barChartHtml + "</div>";
				return barChartHtml;
			},
			"unSetBarChart" : function(cellValue, opts,
					rowObject) {
				return cellvalue;
			},
			"setProgressBarTitle" : function(rowId, val,
					rowObject, cm, rdata) {
				return 'title=" Required : '
						+ rowObject.regressionRequiredPercent
						+ ' % -  Current : '
						+ rowObject.regressionRequiredPercent
						+ '%"';
			},
			"percentFmatter" : function percentFmatter(
					cellvalue, options, rowObject) {
				return cellvalue + " %";
			},
			"unformatPercent" : function(cellvalue, options) {
				return cellvalue.replace(" %", "");
			},
			"showImage" : function(item) {
				var isAparentProject = true;
				if (isAparentProject) {
					return "<img src='images/ok.png' />";
				} else {
					return "";
				}
			},
			"InterFinalPriceFormatter" : function(cellValue,
					opts, rowObject) {
				return cellValue + "Testing price formatter";
			}
		};
		//////// End of Function 
		$.ajax({
			type : "GET",
			url : 'company/projectColsAndNames/${companyID}',
			dataType : "json",
			success : function(result)
			{

				var columnNames = result.colName;
				var columnModel = result.colModel;

				for ( var i = 0; i < columnModel.length; i++) 
				{
					cm = columnModel[i];
					if (cm.hasOwnProperty("formatter") && functionsMapping.hasOwnProperty(cm.formatter))
					{
						// fix colM[i].formatter from string to the function
						cm.formatter = functionsMapping[cm.formatter];
					}
					if (cm.hasOwnProperty("unformat") && functionsMapping.hasOwnProperty(cm.unformat)) 
					{
						// fix colM[i].formatter from string to the function
						cm.unformat = functionsMapping[cm.unformat];
					}
				}

	
				grid.jqGrid(
	        	{
	        		url : 'company/summary/${companyID}',
					datatype : 'json',
					mtype : "GET",
					colNames : columnNames,
					colModel : columnModel,
					
					
		            
		            //data: mydata,
		            //datatype: "local",              
				 	//rowNum: -1,			          		            
		            loadonce : true, // wont sort (work) with tree grid - turn table into local data hence no refresh
					altClass: 'altRow',
					altRows: true,					
					emptyrecords : 'No Projects',
					scroll : 1,
					//rownumbers: true,
					//rownumWidth: 40,
					//autowidth: true,
					pager : '#pager',
					gridview : true,
					viewrecords : true, // Total number in pager					
					sortable : true,
					sortname : 'projectID',
					sortorder : "DSC",
					height:'100%',	
					height : 400,
					width : 900,
					jsonReader : {
						root : "projects",
						id : "projectID",
						repeatitems : false,
						page : function(obj) {return 1;},
						total : function(obj) {return 1;},
						records : function(obj) {return obj.projects.length;}
					},
					// Single Click 
					onSelectRow : function(ids) 
					{
						if (ids != null) 
						{							
							var rowData = grid.getRowData(ids);
							subGrid.setGridParam({url : "project/projectRelatedObjects/"+ ids,page : 1}).trigger('reloadGrid');
							//subGrid.setGridParam({ url: "company/${company.companyID}", page: 1 });						
							//subGrid.setCaption(rowData['projectName']).trigger('reloadGrid');
						}
					},
					// Double Click
					ondblClickRow : function(id, rowid) 
					{
						var proj = grid.getRowData(id);
						// TODO :: Change URL to Details View 						
						window.location = '/Clients/Details/'+ proj.projectID;
					}            
        		});
				grid.jqGrid('navGrid', '#pager', {del : false,add : false,edit : false,refresh : true}, {}, {}, {}, {
					multipleSearch : true,
					closeAfterSearch : true,
					closeAfterReset : true,
					Find : "Search Project List",
					drag : true,
					Reset : "Reset Project List",
					caption : "Search Project List"

				});
				grid.jqGrid('filterToolbar', {
					stringResult : true,
					searchOnEnter : false
				});
				// Select Columns
				grid.jqGrid('navButtonAdd', '#pager', {
					caption : "Columns",
					//buttonicon: "ui-icon-calculator", 
					title : "Choose Project Columns",
					onClickButton : function() {
						grid.jqGrid('columnChooser');
					}
				});
				//grid.jqGrid('excelExport',{tag:'excel', url:''});
				// Test Button 
				jQuery("#viewDetails").click(function() 
				{
					var id = grid.jqGrid('getGridParam','selrow');
					if (id) 
					{
						window.location = '/Clients/Details/'+ id;
						//var proj= grid.jqGrid('getRowData',id);
						//jQuery("#dialog-modal" ).dialog( "open" );
						//alert("Project ID="+proj.projectID+" Project ID="+proj.projectName+"...");
					} 
					else 
					{
						//alert("Please select row");
					}
				});
				
				jQuery("#searchTable").click( function(){
					$("#search_report").trigger('click');			
				});
				jQuery("#columnSelector").click( function(){
					grid.jqGrid('columnChooser');			
				});
				jQuery("#refreshTable").click( function(){
					grid.trigger('reloadGrid');	
					subGrid.trigger('reloadGrid');	
				});
				
				subGrid.jqGrid(
				{
					//caption : "Project:",
					height : '100%',
					width : 200,
					datatype : "json",
					colNames : ['Related object',	'Value' ],
					colModel : [
									{
										name : 'relatedObjectName',
										width : 20,
										sortable : true
									},
									{
										name : 'relatedObjectValue',
										width : 10,
										align : 'center',
										sortable : true,
										resizable : true,
										search : true,
										sorttype : 'number'
									}

								],
					//rowNum: 5,
					//rowList: [5, 10, 20],
					//pager : jQuery('#subpager'),
					sortname: 'relatedobjectIDrelatedobjectID',
					scroll : 1,
					emptyrecords : '0',
					autoload:false,
					viewrecords : true,
					sortorder: "ASC",
					jsonReader : 
					{
						root : "relatedObjects",
						id : "relatedobjectIDrelatedobjectID",
						repeatitems : false,
						page : function(obj) {return 1;},
						total : function(obj) {return 1;},
						records : function(obj) {return obj.relatedObjects.length;}
					},
					ondblClickRow : function(id, rowid)
					{
						var proj = grid.getRowData(id);
						//alert(proj.projectName);	
						//window.location = '/Clients/Details/' + ret.ClientID;  
						window.location = '/Clients/Details/'+ proj.projectID;
					},
				});
				subGrid.navGrid('#subpager',{add : false, edit : false, del : false,search : false});
			}
    	});	
});

$('.ui-jqgrid-titlebar-close', '#report').remove();
$("#viewDetails").button();
$("#deleteProject").button();

$("#accordion").accordion({
	autoHeight:true,
	icons:{ "header": "ui-icon-triangle-1-e", "headerSelected": "ui-icon-triangle-1-s"}
	 });


$sliderValue="";  
// Slider  
$('#newFeatureSlider').slider({  
   // orientation: "vertical",  
    value : 94,             
    min: 0,  
    max: 100,  
    slide: function(event, ui) {  
    	                     
                $('input[name=newFeature]').val(ui.value);                      
                switch (ui.value) {   
                     //case 94:$("#newFeatureSlider_value").text("Recommended");  
                     //break;                                
                 }  

          },  
    stop: function(event, ui) {  
        $sliderValue=ui.value;  
          }  
}); 
$sliderValue2=""; 
$('#regressionSlider').slider({             
     value : 96,              
     min: 0,  
     max: 100,  
     slide: function(event, ui) {             	                     
                 $('input[name=regression]').val(ui.value);  
                 switch (ui.value) {   
                 //case 94:$("#newFeatureSlider_value").text("Recommended");  
                 //break;                                
             }  
           },  
     stop: function(event, ui) {  
         $sliderValue2=ui.value;  
           }  
 });  
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Varibles 

var projectName = $("#projectName"), regression = $("#regression"), newFeature = $("#newFeature"), minSev1 = $("#minSev1"), minSev2 = $("#minSev2"), minSev3 = $("#minSev3"), minSev4 = $("#minSev4"), allFields = $(
		[]).add(projectName).add(regression).add(newFeature).add(
		minSev1).add(minSev2).add(minSev3).add(minSev4), tips = $(".validateTips");

// End of Varibles 		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Create Project Dialog Validation 
function updateTips(t) {
	tips.text(t).addClass("ui-state-highlight");
	/*setTimeout(function() {
		tips.removeClass( "ui-state-highlight", 1500 );
	}, 500 );*/
}

function checkRange(o, n, min, max) {
	var value = parseInt(o.val(), 10);
	//alert(o.val()+ " : "+ min + " to " + max);
	if (value >= min && value <= max) {
		return true;
	} else {
		o.addClass("ui-state-error");
		
		updateTips(n + " must be between " + min + " and " + max + ".");
		return false;
	}
}

function checkLength(o, n, min, max) {
	if (o.val().length > max || o.val().length < min) {
		o.addClass("ui-state-error");
		updateTips("Length of " + n + " must be between " + min
				+ " and " + max + ".");
		return false;
	} else {
		return true;
	}
}

function checkRegexp(o, regexp, n) {
	if (!(regexp.test(o.val()))) {
		o.addClass("ui-state-error");
		updateTips(n);
		return false;
	} else {
		return true;
	}
}

// End of Create Project Dialog Validation		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Create Project Dialog 

$("#dialog-form")
		.dialog(
				{
					autoOpen : false,
					height : 430,
					width : 510,
					modal : true,
					buttons : {
						"Create a Project" : function() {
							var bValid = true;
							allFields.removeClass("ui-state-error");

							bValid = bValid
									&& checkLength(projectName,
											"Project Name", 3, 16);
							bValid = bValid
									&& checkRange(regression,
											"Required Regression", 1,
											100);
							bValid = bValid
									&& checkRange(newFeature,
											"Required New Feature", 1,
											100);
							bValid = bValid
									&& checkRange(minSev1,
											"Maximum Sev 1 Defects", 1,
											1000);
							bValid = bValid
									&& checkRange(minSev2,
											"Maximum Sev 2 Defects", 1,
											1000);
							bValid = bValid
									&& checkRange(minSev3,
											"Maximum Sev 3 Defects", 1,
											1000);
							bValid = bValid
									&& checkRange(minSev4,
											"Maximum Sev 4 Defects", 1,
											1000);
							//bValid = bValid && checkLength( email, "email", 6, 80 );		
							//bValid = bValid && checkRegexp( projectName, /^[a-z]([0-9a-z_])+$/i, "Project Name may consist of a-z, 0-9, underscores, begin with a letter." );
							// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
							//bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
							bValid = bValid
									&& checkRegexp(regression, /^\d+$/,
											"Required Regression field only allows : 1-100");
							bValid = bValid
									&& checkRegexp(newFeature, /^\d+$/,
											"Required New Feature field only allows : 1-100");

							if (bValid) {
								// Create New Project using Ajax
								$.post("newprojectAJAX", {
									companyID : '${companyID}',
									projectName : projectName.val(),
									regression : regression.val(),
									newFeature : newFeature.val(),
									minSev1 : minSev1.val(),
									minSev2 : minSev2.val(),
									minSev3 : minSev3.val(),
									minSev4 : minSev4.val()
								}, function(result) {
									if (result == 'ok') {
										$(this).dialog("close");
										//$("#report").trigger("reloadGrid");
										//$entityList0.trigger("reloadGrid", [{page:1}]);**
										window.location.reload();
									} else {
										updateTips(result);
									}
								});
							}
						},
						Cancel : function() {
							$(this).dialog("close");
						}
					},
					 open: function()
					 {
						 $("#dialog-form").keypress(function(e) 
								 {
					 
					      if (e.keyCode == $.ui.keyCode.ENTER) {
					        $(this).parent().find("button:eq(0)").trigger("click");
					      }
					    });
					 },
					close : function() {
						allFields.val("").removeClass("ui-state-error");
					}
				});

// End of Create Project Dialog 	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Create Project Dialog Button

$("#create-project").button().click(function() {
	$("#dialog-form").dialog("open");
});
$("#deleteProject").click(function(){ 
	 // Get the currently selected row
    toDelete = jQuery('#report').jqGrid('getGridParam','selrow');
	
    // You'll get a pop-up confirmation dialog, and if you say yes,
    // it will call "delete.php" on your server.
    jQuery('#report').jqGrid('delGridRow',toDelete, { url: 'deleteprojectAJAX',
    height:200,
	reloadAfterSubmit:false,
	afterSubmit:function(data,postd){
		console.log(data);
		console.log(postd);
		return {0:true};
	},
	afterComplete:function(data,postd){
		//window.location.reload();
		return true;
	},			
	msg: "Are you sure you want to Delete\n Project "+toDelete+" \n and all its associated Data?"
});
   
});

// End of Create Project Dialog Button	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
				<u>Home</u> > Projects
			</div>
			<!--<h3 class="ui-widget-header" style="height: 10px; font-size:12px; padding-top: 2px;">Projects</h3>-->
			<div style="left:800px;padding-right:10px;background:#FFF;" align="right"> 				
					<img id="refreshTable" style="cursor:pointer" src="images/refresh.jpg" /> 
					&nbsp;&nbsp;
					<img id="searchTable" style="cursor:pointer" src="images/search.jpg" /> 
					&nbsp;&nbsp;
					<img id="columnSelector" style="cursor:pointer" src="images/column.jpg" /> 				
			</div>			
			<div class="ui-layout-content ui-widget-content">
				<div id="grid_container">
				
					<table id="report"></table>
					<div id="pager"></div>
					
				</div>
			</div>
			<!--
				 ******************************************************************************************************************
				 ************************************              Button objects           *****************************************
				 ******************************************************************************************************************
			-->
			<div style="padding-left:5px; padding-top:5px; padding-bottom:5px;"> 				
			<button id="create-project">Create</button>	
			<button id="viewDetails"> View Details </button>	
			<button id="deleteProject"> Delete </button>
				
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
				<!--<table id="sub"></table><div id="subpager"></div>
				<fieldset style="width:90%;">
				  <legend>Part Of:</legend>
					<div id="fieldS" >
						Nothing for Project
					</div>
				</fieldset>
				<BR />
				<fieldset style="width:90%;">
				  <legend>Contains:</legend>
					<div id="fieldS" >
					  Cycles - 10  <BR />
					  Test Plans - 10 <BR />
					  Test Cases - 1800<BR />
					  Test Runs - 1752	<BR />	
					</div>
				</fieldset>
						
				<fieldset style="width:90%;">
				  <legend>Associated with:</legend>
					<div id="fieldS" >
					  Defects - 10  <BR />
					  Requirements - 10 <BR />
					  Environments - 1800<BR />
					  Testers - 17	<BR />	
					  Senior Testers - 17	<BR />	
					  Developers - 17	<BR />	
					  Senior Developers - 17	<BR />	
					</div>
				</fieldset>
				<BR />		-->
				<table id="sub"></table>
				<div id="subpager"></div>
			</div>

			<h3 style="height: 30px; font-size:12px; padding-top: 4px;"><a href="#">Quick Tasks</a></h3>
			<div >	
				<fieldset style="width:90%;">
				  <legend>Test libray:</legend>
					 <div id="fieldS" >
				    Execute Teat Run <BR />
					Execute Test Plan<BR />
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
<img style="height:25px;" src ="images/title.png">
<ul id="menu">
    <li style="padding-left:30px">	
		<a href="#" ><!--<img src="images/home.jpg" />--> Home </a><!-- Begin Home Item -->
		<div class="dropdown_2columns"><!-- Begin 2 columns container -->
			<div class="col_2">
				<h2>Welcome !</h2>
			</div>
			<div class="col_2">
				<p>Hi and welcome here ! This is a showcase of the possibilities of this awesome Mega Drop Down Menu.</p>
				<p>This item comes with a large range of prepared typographic stylings such as headings, lists, etc.</p>
			</div>
			<div class="col_2">
				<h2>Cross Browser Support</h2>
			</div>
			<div class="col_1">
				<img src="images/title.png" width="125" height="48" alt="" />
			</div>
			<div class="col_1">
				<p>This mega menu has been tested in all major browsers.</p>
			</div>
		</div><!-- End 2 columns container -->
    </li><!-- End Home Item -->
    <li><a href="#" class="drop">Projects</a><!-- Begin 5 columns Item -->
        <div class="dropdown_5columns"><!-- Begin 5 columns container -->
            <div class="col_5">
                <h2>This is an example of a large container with 5 columns</h2>
            </div>
            <div class="col_1">
                <p class="black_box">This is a dark grey box text. Fusce in metus at enim porta lacinia vitae a arcu. Sed sed lacus nulla mollis porta quis.</p>
            </div>
            <div class="col_1">
                <p>Phasellus vitae sapien ac leo mollis porta quis sit amet nisi. Mauris hendrerit, metus cursus accumsan tincidunt.</p>
            </div>
            <div class="col_1">
                <p class="italic">This is a sample of an italic text. Consequat scelerisque. Fusce sed lectus at arcu mollis accumsan at nec nisi porta quis sit amet.</p>
            </div>
            <div class="col_1">
                <p>Curabitur euismod gravida ante nec commodo. Nunc dolor nulla, semper in ultricies vitae, vulputate porttitor neque.</p>
            </div>
            <div class="col_1">
                <p class="strong">This is a sample of a bold text. Aliquam sodales nisi nec felis hendrerit ac eleifend lectus feugiat scelerisque.</p>
            </div>
            <div class="col_5">
                <h2>Here is some content with side images</h2>
            </div>
            <div class="col_3">
                <img src="images/home2.jpg" width="70" height="70" class="img_left imgshadow" alt="" />
                <p>Maecenas eget eros lorem, nec pellentesque lacus. Aenean dui orci, rhoncus sit amet tristique eu, tristique sed odio. Praesent ut interdum elit. Sed in sem mauris. Aenean a commodo mi. Praesent augue lacus.<a href="#">Read more...</a></p>
                <img src="images/home.jpg" width="70" height="70" class="img_left imgshadow" alt="" />
                <p>Aliquam elementum felis quis felis consequat scelerisque. Fusce sed lectus at arcu mollis accumsan at nec nisi. Aliquam pretium mollis fringilla. Nunc in leo urna, eget varius metus. Aliquam sodales nisi.<a href="#">Read more...</a></p>
            </div>
            <div class="col_2">
                <p class="black_box">This is a black box, you can use it to highligh some content. Sed sed lacus nulla, et lacinia risus. Phasellus vitae sapien ac leo mollis porta quis sit amet nisi. Mauris hendrerit, metus cursus accumsan tincidunt.Quisque vestibulum nisi non nunc blandit placerat. Mauris facilisis, risus ut lobortis posuere, diam lacus congue lorem, ut condimentum ligula est vel orci. Donec interdum lacus at velit varius gravida. Nulla ipsum risus.</p>
            </div>
        </div><!-- End 5 columns container -->
    </li><!-- End 5 columns Item -->
    <li><a href="#" class="drop">Reports</a><!-- Begin 4 columns Item -->
        <div class="dropdown_4columns"><!-- Begin 4 columns container -->
            <div class="col_4">
                <h2>This is a heading title</h2>
            </div>
            <div class="col_1">
                <h3>Some Links</h3>
                <ul>
                    <li><a href="#">ThemeForest</a></li>
                    <li><a href="#">GraphicRiver</a></li>
                    <li><a href="#">ActiveDen</a></li>
                    <li><a href="#">VideoHive</a></li>
                    <li><a href="#">3DOcean</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Useful Links</h3>
                <ul>
                    <li><a href="#">NetTuts</a></li>
                    <li><a href="#">VectorTuts</a></li>
                    <li><a href="#">PsdTuts</a></li>
                    <li><a href="#">PhotoTuts</a></li>
                    <li><a href="#">ActiveTuts</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Other Stuff</h3>
                <ul>
                    <li><a href="#">FreelanceSwitch</a></li>
                    <li><a href="#">Creattica</a></li>
                    <li><a href="#">WorkAwesome</a></li>
                    <li><a href="#">Mac Apps</a></li>
                    <li><a href="#">Web Apps</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Misc</h3>
                <ul>
                    <li><a href="#">Design</a></li>
                    <li><a href="#">Logo</a></li>
                    <li><a href="#">Flash</a></li>
                    <li><a href="#">Illustration</a></li>
                    <li><a href="#">More...</a></li>
                </ul>
            </div>
        </div><!-- End 4 columns container -->
    </li><!-- End 4 columns Item -->
	<li><a href="#" class="drop">Defects</a><!-- Begin 4 columns Item -->
        <div class="dropdown_4columns"><!-- Begin 4 columns container -->
            <div class="col_4">
                <h2>This is a heading title</h2>
            </div>
            <div class="col_1">
                <h3>Some Links</h3>
                <ul>
                    <li><a href="#">ThemeForest</a></li>
                    <li><a href="#">GraphicRiver</a></li>
                    <li><a href="#">ActiveDen</a></li>
                    <li><a href="#">VideoHive</a></li>
                    <li><a href="#">3DOcean</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Useful Links</h3>
                <ul>
                    <li><a href="#">NetTuts</a></li>
                    <li><a href="#">VectorTuts</a></li>
                    <li><a href="#">PsdTuts</a></li>
                    <li><a href="#">PhotoTuts</a></li>
                    <li><a href="#">ActiveTuts</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Other Stuff</h3>
                <ul>
                    <li><a href="#">FreelanceSwitch</a></li>
                    <li><a href="#">Creattica</a></li>
                    <li><a href="#">WorkAwesome</a></li>
                    <li><a href="#">Mac Apps</a></li>
                    <li><a href="#">Web Apps</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Misc</h3>
                <ul>
                    <li><a href="#">Design</a></li>
                    <li><a href="#">Logo</a></li>
                    <li><a href="#">Flash</a></li>
                    <li><a href="#">Illustration</a></li>
                    <li><a href="#">More...</a></li>
                </ul>
            </div>
        </div><!-- End 4 columns container -->
    </li><!-- End 4 columns Item -->
	<li><a href="#" class="drop">Cycles</a><!-- Begin 4 columns Item -->
        <div class="dropdown_4columns"><!-- Begin 4 columns container -->
            <div class="col_4">
                <h2>This is a heading title</h2>
            </div>
            <div class="col_1">
                <h3>Some Links</h3>
                <ul>
                    <li><a href="#">ThemeForest</a></li>
                    <li><a href="#">GraphicRiver</a></li>
                    <li><a href="#">ActiveDen</a></li>
                    <li><a href="#">VideoHive</a></li>
                    <li><a href="#">3DOcean</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Useful Links</h3>
                <ul>
                    <li><a href="#">NetTuts</a></li>
                    <li><a href="#">VectorTuts</a></li>
                    <li><a href="#">PsdTuts</a></li>
                    <li><a href="#">PhotoTuts</a></li>
                    <li><a href="#">ActiveTuts</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Other Stuff</h3>
                <ul>
                    <li><a href="#">FreelanceSwitch</a></li>
                    <li><a href="#">Creattica</a></li>
                    <li><a href="#">WorkAwesome</a></li>
                    <li><a href="#">Mac Apps</a></li>
                    <li><a href="#">Web Apps</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Misc</h3>
                <ul>
                    <li><a href="#">Design</a></li>
                    <li><a href="#">Logo</a></li>
                    <li><a href="#">Flash</a></li>
                    <li><a href="#">Illustration</a></li>
                    <li><a href="#">More...</a></li>
                </ul>
            </div>
        </div><!-- End 4 columns container -->
    </li><!-- End 4 columns Item -->
	<li><a href="#" class="drop">Test Library</a><!-- Begin 4 columns Item -->
        <div class="dropdown_4columns"><!-- Begin 4 columns container -->
            <div class="col_4">
                <h2>This is a heading title</h2>
            </div>
            <div class="col_1">
                <h3>Some Links</h3>
                <ul>
                    <li><a href="#">ThemeForest</a></li>
                    <li><a href="#">GraphicRiver</a></li>
                    <li><a href="#">ActiveDen</a></li>
                    <li><a href="#">VideoHive</a></li>
                    <li><a href="#">3DOcean</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Useful Links</h3>
                <ul>
                    <li><a href="#">NetTuts</a></li>
                    <li><a href="#">VectorTuts</a></li>
                    <li><a href="#">PsdTuts</a></li>
                    <li><a href="#">PhotoTuts</a></li>
                    <li><a href="#">ActiveTuts</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Other Stuff</h3>
                <ul>
                    <li><a href="#">FreelanceSwitch</a></li>
                    <li><a href="#">Creattica</a></li>
                    <li><a href="#">WorkAwesome</a></li>
                    <li><a href="#">Mac Apps</a></li>
                    <li><a href="#">Web Apps</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Misc</h3>
                <ul>
                    <li><a href="#">Design</a></li>
                    <li><a href="#">Logo</a></li>
                    <li><a href="#">Flash</a></li>
                    <li><a href="#">Illustration</a></li>
                    <li><a href="#">More...</a></li>
                </ul>
            </div>
        </div><!-- End 4 columns container -->
    </li><!-- End 4 columns Item -->
	<li><a href="#" class="drop">Requirements</a><!-- Begin 4 columns Item -->
        <div class="dropdown_4columns align_right  "><!-- Begin 4 columns container -->
            <div class="col_4">
                <h2>This is a heading title</h2>
            </div>
            <div class="col_1">
                <h3>Some Links</h3>
                <ul>
                    <li><a href="#">ThemeForest</a></li>
                    <li><a href="#">GraphicRiver</a></li>
                    <li><a href="#">ActiveDen</a></li>
                    <li><a href="#">VideoHive</a></li>
                    <li><a href="#">3DOcean</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Useful Links</h3>
                <ul>
                    <li><a href="#">NetTuts</a></li>
                    <li><a href="#">VectorTuts</a></li>
                    <li><a href="#">PsdTuts</a></li>
                    <li><a href="#">PhotoTuts</a></li>
                    <li><a href="#">ActiveTuts</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Other Stuff</h3>
                <ul>
                    <li><a href="#">FreelanceSwitch</a></li>
                    <li><a href="#">Creattica</a></li>
                    <li><a href="#">WorkAwesome</a></li>
                    <li><a href="#">Mac Apps</a></li>
                    <li><a href="#">Web Apps</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Misc</h3>
                <ul>
                    <li><a href="#">Design</a></li>
                    <li><a href="#">Logo</a></li>
                    <li><a href="#">Flash</a></li>
                    <li><a href="#">Illustration</a></li>
                    <li><a href="#">More...</a></li>
                </ul>
            </div>
        </div><!-- End 4 columns container -->
    </li><!-- End 4 columns Item -->
	<li><a href="#" class="drop">Users</a><!-- Begin 4 columns Item -->
        <div class="dropdown_4columns align_right"><!-- Begin 4 columns container -->
            <div class="col_4">
                <h2>This is a heading title</h2>
            </div>
            <div class="col_1">
                <h3>Some Links</h3>
                <ul>
                    <li><a href="#">ThemeForest</a></li>
                    <li><a href="#">GraphicRiver</a></li>
                    <li><a href="#">ActiveDen</a></li>
                    <li><a href="#">VideoHive</a></li>
                    <li><a href="#">3DOcean</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Useful Links</h3>
                <ul>
                    <li><a href="#">NetTuts</a></li>
                    <li><a href="#">VectorTuts</a></li>
                    <li><a href="#">PsdTuts</a></li>
                    <li><a href="#">PhotoTuts</a></li>
                    <li><a href="#">ActiveTuts</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Other Stuff</h3>
                <ul>
                    <li><a href="#">FreelanceSwitch</a></li>
                    <li><a href="#">Creattica</a></li>
                    <li><a href="#">WorkAwesome</a></li>
                    <li><a href="#">Mac Apps</a></li>
                    <li><a href="#">Web Apps</a></li>
                </ul>
            </div>
            <div class="col_1">
                <h3>Misc</h3>
                <ul>
                    <li><a href="#">Design</a></li>
                    <li><a href="#">Logo</a></li>
                    <li><a href="#">Flash</a></li>
                    <li><a href="#">Illustration</a></li>
                    <li><a href="#">More...</a></li>
                </ul>
            </div>
        </div><!-- End 4 columns container -->
    </li><!-- End 4 columns Item -->
	<li ><a href="#" class="drop">Enviroments</a>
		<div class="dropdown_1column align_right">
                <div class="col_1">
                    <ul class="simple">
                        <li><a href="#">FreelanceSwitch</a></li>
                        <li><a href="#">Creattica</a></li>
                        <li><a href="#">WorkAwesome</a></li>
                        <li><a href="#">Mac Apps</a></li>
                        <li><a href="#">Web Apps</a></li>
                        <li><a href="#">NetTuts</a></li>
                        <li><a href="#">VectorTuts</a></li>
                        <li><a href="#">PsdTuts</a></li>
                        <li><a href="#">PhotoTuts</a></li>
                        <li><a href="#">ActiveTuts</a></li>
                        <li><a href="#">Design</a></li>
                        <li><a href="#">Logo</a></li>
                        <li><a href="#">Flash</a></li>
                        <li><a href="#">Illustration</a></li>
                        <li><a href="#">More...</a></li>
                    </ul>
                </div>
		</div>
	</li>   
</ul>
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
<div class="ui-layout-south" style="">Outer South</div>
	<div id="dialog-form" title="Create new Project">
		<p class="validateTips">All form fields are required.</p>
		<form>
			<fieldset>
				<table width="100%" border="0" >
					<tr>
						<td colspan="2" valign="top">
							<table width="100%">
								<tr>
									<td valign="top"><label for="projectName"
										style="float: left;">Project Name :</label></td>
									<td><input type="text" name="projectName" id="projectName"
										style="width: 200px; display: inline-block; float: left;"
										class="text ui-widget-content ui-corner-all" /></td>
								</tr>
							</table>
						</td>

					</tr>
					<tr>
						<td colspan="2">
							<hr />
						</td>
					</tr>
					<tr>
						<td valign="top">
							<table width="100%" border="0" cellpadding="5px">

								<tr>
									<td><label for="regression">Required New Feature %</label></td>
									<td>
										<table width="100%">
											<tr>
												<td><input type="text" name="newFeature"
													id="newFeature" value="94" style="width: 20px;"
													class="text ui-widget-content ui-corner-all" /></td>
												<td>%</td>
											</tr>
											
										</table>
									</td>
									<td><label for="regression">Required Regression %</label></td>
									<td>
										<table width="100%">
											<tr>
												<td><input type="text" name="regression"
													id="regression" value="96" style="width: 20px;"
													class="text ui-widget-content ui-corner-all" /></td>
												<td>%</td>
											</tr>											
										</table>

									</td>
								</tr>
								<tr>
									<td colspan="2" align="center"><div id="newFeatureSlider" style="width:90%"></div></td>
									<td colspan="2" align="center"><div id="regressionSlider" style="width:90%"></div></td>
								</tr>
								<tr>
									<td colspan="4">
										<hr />
									</td>
								</tr>
								<tr>
									<td valign="top"><label for="minSev1">Maximum
											Allowed Sev 1 Defects</label></td>
									<td>
										<table width="100%">
											<tr>
												<td><input type="text" name="minSev1" id="minSev1"
													value="10" style="width: 20px;"
													class="text ui-widget-content ui-corner-all" /></td>
												<td></td>
											</tr>
										</table>

									</td>
									<td valign="top"><label for="minSev2">Maximum
											Allowed Sev 2 Defects </label></td>
									<td>
										<table width="100%">
											<tr>
												<td><input type="text" name="minSev2" id="minSev2"
													value="25" style="width: 20px;"
													class="text ui-widget-content ui-corner-all" /></td>
												<td></td>
											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<td valign="top"><label for="minSev3">Maximum
											Allowed Sev 3 Defects</label></td>
									<td>
										<table width="100%">
											<tr>
												<td><input type="text" name="minSev3" id="minSev3"
													value="50" style="width: 20px;"
													class="text ui-widget-content ui-corner-all" /></td>
												<td></td>
											</tr>
										</table>
									</td>
									<td valign="top"><label for="minSev4">Maximum
											Allowed Sev 4 Defects</label></td>
									<td>
										<table width="100%">
											<tr>
												<td><input type="text" name="minSev4" id="minSev4"
													value="100" style="width: 20px;"
													class="text ui-widget-content ui-corner-all" /></td>
												<td></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<!--  <label for="email">Email</label>
		<input type="email" name="email" id="email" value="" style="width:95%;" class="text ui-widget-content ui-corner-all" />		-->
			</fieldset>
		</form>
	</div>

</body>
</html>