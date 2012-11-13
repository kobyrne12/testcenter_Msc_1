
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>The One</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    
     <!--<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.7/themes/redmond/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-3.8.2/css/ui.jqgrid.css" />-->
	<link rel="stylesheet" type="text/css" href="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-4.3.1/css/ui.jqgrid.css" />
	
	<!--<link rel="stylesheet" type="text/css" href="css/KenTestTheme/jquery-ui-1.9.0.custom.css"/>-->
	<!--<link rel="stylesheet" type="text/css" href="css/start/jquery-ui-1.9.0.custom.css"/>-->
	<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/themes/redmond/jquery-ui.css" />   
	
    <link rel="stylesheet" type="text/css" href="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-4.3.1/plugin/ui.multiselect.css" />
    <style type="text/css">
		hr 
		{
			border: 0;
			width: 100%;
			color: #f00;
			background-color: #b6c4cd;
			height: 1px;	
			margin-top: 10px; 
			margin-bottom:10px;
		}
        html, body { font-size: 75%; }			
		label, input { display:block; }
		input.text { margin-bottom:12px;  padding: .4em; }
		fieldset { padding:0; border:0; margin-top:25px; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		div#users-contain { width: 350px; margin: 20px 0; }
		div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
		div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
		.ui-dialog .ui-state-error { padding: .3em; }
		.validateTips { border: 1px solid transparent; padding: 0.3em; }
		
		.ui-jqgrid .ui-jqgrid-htable th div 
		{
			height:auto;
			overflow:hidden;
			//padding-left:4px;
			//padding-right:4px;
			padding-top:4px;
			padding-bottom:4px;
			position:relative;
			vertical-align:text-top;
			//white-space:normal !important;
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
		
		/* .ui-jqgrid .ui-jqgrid-titlebar-close span {display:none;}*/
    </style>
	
	<!-- <script type="text/javascript" src="js/jquery-1.8.2.js"></script>-->
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
    <!-- <script type="text/javascript" src="js/jquery-ui-1.9.0.custom.js"></script>-->
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
	
    <script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-4.3.1/plugin/ui.multiselect.js"></script>
    <script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-4.3.1/js/i18n/grid.locale-en.js"></script>
    <script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-4.3.1/js/jquery.jqGrid.src.js"></script>
	
	

    <!--
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.js"></script>
    <script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-3.8.2/src/i18n/grid.locale-en.js"></script>
    <script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-3.8.2/src/grid.base.js"></script>
    <script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-3.8.2/src/grid.common.js"></script>
    <script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-3.8.2/src/grid.formedit.js"></script>
    <script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-3.8.2/src/grid.inlinedit.js"></script>
    <script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-3.8.2/src/grid.custom.js"></script>
    <script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-3.8.2/src/jquery.fmatter.js"></script>
    <script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-3.8.2/src/jquery.searchFilter.js"></script>    
    <script type="text/javascript" src="http://www.ok-soft-gmbh.com/jqGrid/jquery.jqGrid-3.8.2/src/jqDnR.js"></script>	
	-->  
    <script type="text/javascript">	
    //<![CDATA[
	//var jq = jQuery.noConflict();
	$(function() {	
		
		$('.ui-jqgrid-titlebar-close','#report').remove();	
		$( "#viewDetails" ).button();
		$( "#deleteProject" ).button();
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Varibles 
		
		var projectName = $( "#projectName" ),			
			regression = $( "#regression" ),
			newFeature = $( "#newFeature" ),
			minSev1 = $( "#minSev1" ),
			minSev2 = $( "#minSev2" ),
			minSev3 = $( "#minSev3" ),
			minSev4 = $( "#minSev4" ),
			allFields = $( [] ).add( projectName ).add( regression ).add( newFeature ).add( minSev1 ).add( minSev2 ).add( minSev3 ).add( minSev4 ),
			tips = $( ".validateTips" );
		
		// End of Varibles 		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Create Project Dialog Validation 
		function updateTips( t ) {
			tips
				.text( t )
				.addClass( "ui-state-highlight" );
			/*setTimeout(function() {
				tips.removeClass( "ui-state-highlight", 1500 );
			}, 500 );*/
		}
		
		function checkRange(o, n, min, max) {	
			var value = parseInt(o.val(), 10);			
			if (value >= min && value <= max ) {
				return true;
			} else {
				o.addClass( "ui-state-error" );
				updateTips(  n + " must be between " +	min + " and " + max + "." );
				return false;				
			}		   
		}
		
		function checkLength( o, n, min, max ) {
			if ( o.val().length > max || o.val().length < min ) {
				o.addClass( "ui-state-error" );
				updateTips( "Length of " + n + " must be between " +
					min + " and " + max + "." );
				return false;
			} else {
				return true;
			}
		}

		function checkRegexp( o, regexp, n ) {
			if ( !( regexp.test( o.val() ) ) ) {
				o.addClass( "ui-state-error" );
				updateTips( n );
				return false;
			} else {
				return true;
			}
		}
		
		// End of Create Project Dialog Validation		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Create Project Dialog 
		
		$( "#dialog-form" ).dialog({
			autoOpen: false,
			height: 410,
			width: 510,
			modal: true,
			buttons: {
				"Create a Project": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );

					bValid = bValid && checkLength( projectName, "Project Name", 3, 16 );
					bValid = bValid && checkRange( regression, "Required Regression", 1, 100 );
					bValid = bValid && checkRange( newFeature, "Required New Feature", 1, 100 );
					bValid = bValid && checkRange( minSev1, "Maximum Sev 1 Defects", 1, 1000 );
					bValid = bValid && checkRange( minSev2, "Maximum Sev 2 Defects", 1, 1000 );
					bValid = bValid && checkRange( minSev3, "Maximum Sev 3 Defects", 1, 1000 );
					bValid = bValid && checkRange( minSev4, "Maximum Sev 4 Defects", 1, 1000 );
					//bValid = bValid && checkLength( email, "email", 6, 80 );		
					//bValid = bValid && checkRegexp( projectName, /^[a-z]([0-9a-z_])+$/i, "Project Name may consist of a-z, 0-9, underscores, begin with a letter." );
					// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
					//bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
					bValid = bValid && checkRegexp( regression, /^\d+$/, "Required Regression field only allows : 1-100" );
					bValid = bValid && checkRegexp( newFeature, /^\d+$/, "Required New Feature field only allows : 1-100" );


					if ( bValid ) {
						// Create New Project using Ajax
						$.post("newprojectAJAX",{companyID:'${company.companyID}',projectName:projectName.val(), regression:regression.val(), newFeature:newFeature.val(),
							minSev1:minSev1.val(),minSev2:minSev2.val(),minSev3:minSev3.val(),minSev4:minSev4.val()},
						function(result)
						{							
							if (result == 'ok') 
							{
								$( this ).dialog( "close" );
								//$("#report").trigger("reloadGrid");
								//$entityList0.trigger("reloadGrid", [{page:1}]);**
								window.location.reload();
							}
							else
							{
								updateTips( result );								
							}
						});
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});
		
		// End of Create Project Dialog 	
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Create Project Dialog Button
		
		$( "#create-project" )
			.button()
			.click(function() {
				$( "#dialog-form" ).dialog( "open" );
			});
		
		// End of Create Project Dialog Button	
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	});
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Load Main Grid
	
        jQuery(document).ready(function () {
			var grid = jQuery('#report');
			var subGrid = jQuery("#sub");
			function myTitle (value, rowObject, cm) 
			{
				//return "Required : " + rowObject.regressionRequiredPercent + ' | Current : ' + rowObject.regressionRequiredPercent;
				return "Required";
			};
            $("#report").jqGrid( 
			{
                url:        'resources/testcenter.json',
				//url:        'company/${company.companyID}',
                datatype:   'json',                
				colNames:   ['ID', 
							//'${company.projectDisplayName} Name',
							'Project Name', 
							'Regresion', 
							'New Feature', 
							'Parent <BR/> Projects', 
							'Child <BR/> Projects',
							'Min <BR/>Regression', 'Current<BR/>Regression',
							'Min <BR/> New Feature', 'Min <BR/> New Feature', 
							'Max <BR/> Sev 1s', 'Current <BR/> Sev 1s',
							'Max <BR/> Sev 2s', 'Current <BR/> Sev 2s', 
							'Max <BR/> Sev 3s', 'Current <BR/> Sev 3s', 
							'Max <BR/> Sev 4s', 'Current <BR/> Sev 4s', 
							'createdBy','creationDate', 
							'lastModifiedBy', 'lastModifiedDate', 
							//'${company.cycleDisplayName} Name',
							'Cycles',
							//'${company.testrunDisplayName} Name',
							'Test Runs',							
							//'${company.requirementDisplayName} Name',
							'Requirements',
							//'${company.environmentDisplayName} Name',
							'Environments'							 
							],
                colModel:   [ {name:'projectID', index:'projectID', width: 15, align:'center',sortable:true, resizable:true, search:true , sorttype:'number'},	                             
							  {name:'projectName', index:'projectName', width: 50, sortable:true, jsonmap: "projectName"},
							  {name:'regressionState', index:'regressionState', width: 50, sortable:false, search:false, formatter:setBarChart,
								cellattr: function (rowId, val, rawObject, cm, rdata) 
								{									
									return 'title=" Required : ' + rawObject.regressionRequiredPercent + ' % -  Current : ' + rawObject.regressionRequiredPercent + '%"';
								}								
							  },
							  {name:'newFeatureState', index:'newFeatureState', width: 50, sortable:false, search:false, formatter:setBarChart,
								cellattr: function (rowId, val, rawObject, cm, rdata) 
								{									
									return 'title=" Required : ' + rawObject.regressionRequiredPercent + ' % -  Current : ' + rawObject.regressionRequiredPercent + '%"';
								}								
							  },
							  {name:'parentProject', width: 20, align:'center',jsonmap: 
								function (item) 
								{	
									var isAparentProject = true;
									if (isAparentProject)
									{
										return "<img src='images/ok.png' />";
									}
									else
									{
										return "";
									}
								}
							  }, 
							  {name:'childProject', width: 20, align:'center',jsonmap: 
								function (item) 
								{	
									noOfChildProject = 3;									
									return noOfChildProject;
									
								}
							  }, 
							  // if instr (projectID,projectName...etc) in project view table field for a user show else hide
                              {name:'regressionRequiredPercent', hidden:true, align:'center',width: 20, sortable:true, sorttype:'number', formatter:percentFmatter, unformat:unformatPercent },
                              {name:'regressionCurrentPercent',hidden:true, align:'center',width: 20, sortable:true, sorttype:'number', formatter:percentFmatter, unformat:unformatPercent },                              
							  {name:'newFeatureRequiredPercent',hidden:true, align:'center',width: 24, sortable:true, sorttype:'number', formatter:percentFmatter, unformat:unformatPercent },
							  {name:'newFeatureCurrentPercent',hidden:true, align:'center',width: 24, sortable:true, sorttype:'number', formatter:percentFmatter, unformat:unformatPercent },
                              {name:'allowedSev1', width: 22, align:'center',sortable:true, sorttype:'number' },
							  {name:'currentSev1', width: 20, align:'center',jsonmap: 
								function (item) 
								{									
									var totalTestRuns = 0;
									var totalSev1s = 0;
									var totalCycles = item.cycles.length;
									for(i=0; i<totalCycles; i++)
									{ // All Cycles within a Project
										if (item.cycles[i].testRuns)
										{
											totalTestRuns = totalTestRuns + item.cycles[i].testRuns.length
											for(x=0; x<item.cycles[i].testRuns.length; x++)
											{ // All Test Runs within a Cycles
												if (item.cycles[i].testRuns[x].defects)
												{													
													for(y=0; y<item.cycles[i].testRuns[x].defects.length; y++)
													{ // All defects within a Test Run
														if (item.cycles[i].testRuns[x].defects[y].severity == 1)
														{
															totalSev1s = totalSev1s + 1;
														}
													}
												}
											}
										}										
									}
									return totalSev1s;
								}
							  },                               
                             {name:'allowedSev2', width: 25, align:'center',sortable:true, sorttype:'number' },
							 {name:'currentSev2', width: 20, align:'center',jsonmap: 
								function (item) 
								{									
									var totalTestRuns = 0;
									var totalSev2s = 0;
									var totalCycles = item.cycles.length;
									for(i=0; i<totalCycles; i++)
									{ // All Cycles within a Project
										if (item.cycles[i].testRuns)
										{
											totalTestRuns = totalTestRuns + item.cycles[i].testRuns.length
											for(x=0; x<item.cycles[i].testRuns.length; x++)
											{ // All Test Runs within a Cycles
												if (item.cycles[i].testRuns[x].defects)
												{													
													for(y=0; y<item.cycles[i].testRuns[x].defects.length; y++)
													{ // All defects within a Test Run
														if (item.cycles[i].testRuns[x].defects[y].severity == 2)
														{
															totalSev2s = totalSev2s + 1;
														}
													}
												}
											}
										}										
									}
									return totalSev2s;
								}
							  },            
                              {name:'allowedSev3', width: 25, align:'center',sortable:true, sorttype:'number' },
							  {name:'currentSev3', width: 20, align:'center',jsonmap: 
								function (item) 
								{									
									var totalTestRuns = 0;
									var totalSev3s = 0;
									var totalCycles = item.cycles.length;
									for(i=0; i<totalCycles; i++)
									{ // All Cycles within a Project
										if (item.cycles[i].testRuns)
										{
											totalTestRuns = totalTestRuns + item.cycles[i].testRuns.length
											for(x=0; x<item.cycles[i].testRuns.length; x++)
											{ // All Test Runs within a Cycles
												if (item.cycles[i].testRuns[x].defects)
												{													
													for(y=0; y<item.cycles[i].testRuns[x].defects.length; y++)
													{ // All defects within a Test Run
														if (item.cycles[i].testRuns[x].defects[y].severity == 3)
														{
															totalSev3s = totalSev3s + 1;
														}
													}
												}
											}
										}										
									}
									return totalSev3s;
								}
							  },            							  
                              {name:'allowedSev4', width: 25, align:'center',sortable:true, sorttype:'number' },
							  {name:'currentSev4', width: 20, align:'center',jsonmap: 
								function (item) 
								{									
									var totalTestRuns = 0;
									var totalSev4s = 0;
									var totalCycles = item.cycles.length;
									for(i=0; i<totalCycles; i++)
									{ // All Cycles within a Project
										if (item.cycles[i].testRuns)
										{
											totalTestRuns = totalTestRuns + item.cycles[i].testRuns.length
											for(x=0; x<item.cycles[i].testRuns.length; x++)
											{ // All Test Runs within a Cycles
												if (item.cycles[i].testRuns[x].defects)
												{													
													for(y=0; y<item.cycles[i].testRuns[x].defects.length; y++)
													{ // All defects within a Test Run
														if (item.cycles[i].testRuns[x].defects[y].severity == 4)
														{
															totalSev4s = totalSev4s + 1;
														}
													}
												}
											}
										}										
									}
									return totalSev4s;
								}
							  },          
                              {name:'createdBy',  width: 25,sortable:true, sorttype:'number' ,hidden:true},
                              {name:'creationDate',  width: 30, align:'center',sortable:true, sorttype:'number' ,hidden:true},
                              {name:'lastModifiedBy',  width: 30, sortable:true, sorttype:'number',hidden:true },
                              {name:'lastModifiedDate',  width: 30, align:'center',sortable:true, sorttype:'number',hidden:true } ,
							  {name:'cycles', width: 20, align:'center',jsonmap: "cycles.length" },
							  {name:'testRuns', width: 20, align:'center',jsonmap: 
								function (item) 
								{									
									var totalTestRuns = 0;
									if (item.cycles)
									{
										var totalCycles = item.cycles.length;										
										for(i=0; i<totalCycles; i++)
										{
											if (item.cycles[i].testRuns)
											{									
												totalTestRuns = totalTestRuns + item.cycles[i].testRuns.length;
											}
										}
									}
									return totalTestRuns;
								}
							  }, 							 
							  {name:'requirements', width: 20, align:'center',jsonmap: 
								function (item) 
								{									
									var totalTestRuns = 0;
									var totalReqs = 0;
									var totalCycles = item.cycles.length;
									for(i=0; i<totalCycles; i++)
									{
										if (item.cycles[i].testRuns)
										{
											totalTestRuns = totalTestRuns + item.cycles[i].testRuns.length
											for(x=0; x<item.cycles[i].testRuns.length; x++)
											{
												if (item.cycles[i].testRuns[x].requirements)
												{
													totalReqs = totalReqs + item.cycles[i].testRuns[x].requirements.length
												}
											}
										}										
									}
									return totalReqs;
								}
							  }, 
							   {name:'environments', width: 20, align:'center',jsonmap: 
								function (item) 
								{									
									var totalTestRuns = 0;
									var totalEnvs = 0;
									var totalCycles = item.cycles.length;
									for(i=0; i<totalCycles; i++)
									{
										if (item.cycles[i].testRuns)
										{
											totalTestRuns = totalTestRuns + item.cycles[i].testRuns.length
											for(x=0; x<item.cycles[i].testRuns.length; x++)
											{
												if (item.cycles[i].testRuns[x].requirements)
												{
													totalEnvs = totalEnvs + item.cycles[i].testRuns[x].environments.length
												}
											}
										}										
									}
									return totalEnvs;
								}
							  } 
							  ],							  
                             
                caption: "Projects",				
			//	altClass: 'altRow',
			//	altRows: true,
				//autowidth: true,
				emptyrecords: 'No Projects',
				scroll:1,
				loadonce:true,
				mtype: "GET",
				//rownumbers: true,
				//rownumWidth: 40,
				gridview: true,
				pager: '#pager',
				sortname: 'projectID',
				viewrecords: true,
				sortorder: "asc",
				height: 600,
				width: 900,
				sortable: true,
							
				ondblClickRow: function(id, rowid) {  
					var proj = grid.getRowData(id);
					// TODO :: Change URL to Details View 						
					window.location = '/Clients/Details/' + proj.projectID;  
				},     
                jsonReader: {
                    root: "projects",  
					id: "projectID",
                    repeatitems: false,
					page:  function(obj) { return 1; },
					total: function(obj) { return 1; },					
					records: function(obj) { return obj.projects.length; }                    
                },
				/*afterInsertRow : function(rowid, rowdata , rowelem) 
				{  // shows custom tooltip
					$('.item-multiplier').each(function() {
						var t = $(this).text().match(/\d+/);
						if (t) values.push(parseInt(t));
					});

					var maxValue = 100;
					var Value = 96;

					var newBar = $("<span>").html(Value)
					.css({
						  "display": "block",
						  "width": "0px",
						  "backgroundColor": "#465858",
						  "marginBottom": "5px",
						  "padding": "2px",
						  "color": "#ffffff"
						});

					$("#bar").append(newBar);
					var w = Math.floor((100 * Value / maxValue)) + "%";
					newBar.animate({"width":w}, "slow");			
				}, */
				onSelectRow: function(ids) 
				{
					if (ids != null) 
					{
						var rowData = grid.getRowData(ids);
						subGrid.setGridParam({ url: "resources/testcenter.json", page: 1 })
						//subGrid.setGridParam({ url: "company/${company.companyID}", page: 1 });						
						subGrid.setCaption(rowData['projectName'])						
						.trigger('reloadGrid');
					}
				}				
            })
			//.jqGrid ('navGrid', '#pager', { edit: false, add: false, del: false, refresh: true, view: true },{},{},{},{multipleSearch:true})			
			grid.jqGrid('navGrid','#pager',{del:false,add:false,edit:false,refresh:true},{},{},{},
			{
				multipleSearch:true, closeAfterSearch:true, closeAfterReset:true, Find : "Search Project List",
				drag: true, Reset :"Reset Project List",  caption: "Search Project List"
				
			})			
			grid.jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false})		
			// Select Columns
			grid.jqGrid ('navButtonAdd', '#pager', 
			{ 
				caption: "Columns", 
				//buttonicon: "ui-icon-calculator", 
				title: "Choose Project Columns",
                onClickButton: function() 
				{
                    grid.jqGrid('columnChooser');
                }
            })
			// Test Button 
			jQuery("#viewDetails").click( function(){
				var id = grid.jqGrid('getGridParam','selrow');
				if (id)	
				{
					window.location = '/Clients/Details/' + id; 
					//var proj= grid.jqGrid('getRowData',id);
					//jQuery("#dialog-modal" ).dialog( "open" );
					//alert("Project ID="+proj.projectID+" Project ID="+proj.projectName+"...");
				} else { alert("Please select row");}
			})
			
			
			// subgrid			
			subGrid.jqGrid
			({
				caption: "Project:",
				height: 163,
				width: 220,
				datatype: "json",
				colNames:   ['Related obj', 'No.'],
                colModel:   [ 
							  {name:'projectName',  sortable:true, jsonmap: "projectName"},
							  {name:'projectID', width: 20, align:'center',sortable:true, resizable:true, search:true , sorttype:'number'}	                             
							 
                            ],
				//rowNum: 5,
				//rowList: [5, 10, 20],
				pager: jQuery('#subpager'),
				sortname: 'Id',
				scroll:1,
				emptyrecords: '0',
				viewrecords: true,
				sortorder: "desc",
				jsonReader: {
                    root: "projects",  
					id: "projectID",
                    repeatitems: false,
					page:  function(obj) { return 1; },
					total: function(obj) { return 1; },					
					records: function(obj) { return obj.projects.length; }                    
                },
				ondblClickRow: function(id, rowid) {  
					var proj = grid.getRowData(id);  
					//alert(proj.projectName);	
					//window.location = '/Clients/Details/' + ret.ClientID;  
					window.location = '/Clients/Details/' + proj.projectID;  
				}  ,
			}).navGrid('#subpager', { add: false, edit: false, del: false, search: false });
        });
		function percentFmatter (cellvalue, options, rowObject)
		{
		 
		   return cellvalue + " %";
		}
		function  unformatPercent (cellvalue, options)
		{
		 
		   return cellvalue.replace(" %","");
		}
		
		function setBarChart (cellvalue, options, rowObject)
		{
			var targetreached = false;
			var currentPercent = rowObject.regressionRequiredPercent;
			//rowObject.regressionRequiredPercent - rowObject.regressionRequiredPercent
			var remainingPercent = 100 - currentPercent;		
			//title='"+rowObject.regressionRequiredPercent+"%'
			var barChartHtml = "<div style='width:100%;display:inline-block;'  >";
				var barChartHtml = "<div style='width:75%;display:inline-block;float: left;' class='progressBarDiv' id='progressBarDiv'  >";
				if (targetreached)
				{
					var barChartHtml =  barChartHtml + "<div  class='greenBar' style='width:60%; float: left;display:inline-block;'> </div>";
				}
				else
				{
					var barChartHtml =  barChartHtml + "<div  class='orangeBar' style='width:60%; float: left;display:inline-block;'> </div>";
				}
					var barChartHtml =  barChartHtml + "<div class='redBar' style='width:40%;float: left; display:inline-block;'> </div>";
					//var barChartHtml =  barChartHtml + "<div class='blueBar' style='width:20%;float: left;display:inline-block;'> </div>";
				var barChartHtml =  barChartHtml + "</div>";
				var barChartHtml =  barChartHtml + "<div id='spacer' style='width:5%;float: left;display:inline-block;'>&nbsp;</div>";			
				var barChartHtml =  barChartHtml + "<div class='pgPercent'>"+rowObject.regressionRequiredPercent+"% </div>";
			var barChartHtml =  barChartHtml + "</div>";
			
			return barChartHtml;

			//return "<div id='bar' style='min-width: auto;margin: 0 auto;'></div>";
		}		
		

    //]]>
    </script>
</head>
<body>
<table style="width:100%;">
	<tr>
	<td style="width:70%;">
		<table id="report"><tr><td/></tr></table>
		<div id="pager"/> </div>
		<br />
		
		<button id="create-project">Create</button>	
		<button id="viewDetails"> View Details </button>	
		<button id="deleteProject"> Delete </button>
	</td>
	<td valign="top">
		<table id="sub"></table>
		<div id="subpager"></div>
	</td>
	</tr>
</table>

<div id="dialog-form" title="Create new Project">
	<p class="validateTips">All form fields are required.</p>
	<form>
	<fieldset>
		<table width="100%" border="0">
		<tr>
			<td colspan="2" valign="top">
				<table width="100%">
				<tr>
				<td valign="top">
					<label for="projectName" style="float: left;">Project Name :</label>
				</td>
				<td>				
					<input type="text" name="projectName" id="projectName" style="width:200px;display:inline-block;float: left;" class="text ui-widget-content ui-corner-all" />		
				</td>
				</tr>
				</table>
			</td>
			
		</tr>
		<tr>
			<td colspan ="2">
				<hr />
			</td>
		</tr>
		<tr>
			<td valign="top">
				<table width="100%" border="0" cellpadding="5px">
				
				<tr>
				<td >
					<label for="regression">Required Regression </label>				
				</td>
				<td>	
					<table width="100%">				
					<tr>
					<td>
						<input type="text" name="newFeature" id="newFeature" value="94" style="width:20px;" class="text ui-widget-content ui-corner-all" />	
					</td>
					<td>
						%
					</td>
					</tr>
					</table>
				</td>
				<td >
					<label for="newFeature">Required New Feature %</label>			
				</td>
				<td >	
					<table width="100%">				
					<tr>
					<td>
						<input type="text" name="regression" id="regression" value="96" style="width:20px;" class="text ui-widget-content ui-corner-all" />			
					</td>
					<td>
						%
					</td>
					</tr>
					</table>
					
				</td>				
				</tr>
				<tr>
					<td colspan ="4">
						<hr />
					</td>
				</tr>
				<tr>
				<td valign="top">
					<label for="minSev1" >Maximum Allowed Sev 1 Defects</label>			
				</td>
				<td>	
					<table width="100%">				
					<tr>
					<td>
						<input type="text" name="minSev1" id="minSev1" value="10" style="width:20px;" class="text ui-widget-content ui-corner-all" />
					</td>
					<td>						
					</td>
					</tr>
					</table>
					
				</td>
				<td valign="top">
					<label for="minSev2" >Maximum Allowed Sev 2 Defects </label>	
				</td>
				<td>	
					<table width="100%">				
					<tr>
					<td>
						<input type="text" name="minSev2" id="minSev2" value="25" style="width:20px;" class="text ui-widget-content ui-corner-all" />
					</td>
					<td>						
					</td>
					</tr>
					</table>					
				</td>				
				</tr>
				
				<tr>
				<td valign="top">
					<label for="minSev3">Maximum Allowed Sev 3 Defects</label>	
				</td>
				<td>		
					<table width="100%">				
					<tr>
					<td>
						<input type="text" name="minSev3" id="minSev3" value="50" style="width:20px;" class="text ui-widget-content ui-corner-all" />
					</td>
					<td>						
					</td>
					</tr>
					</table>						
				</td>
				<td valign="top">
					<label for="minSev4">Maximum Allowed Sev 4 Defects</label>
				</td>
				<td>		
					<table width="100%">				
					<tr>
					<td>
						<input type="text" name="minSev4" id="minSev4" value="100" style="width:20px;" class="text ui-widget-content ui-corner-all" />
					</td>
					<td>						
					</td>
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


	<div class="contextMenu" id="myMenu1" style="display:none">
        <ul style="width: 200px">
            <li id="add">
                <span class="ui-icon ui-icon-plus" style="float:left"></span>
                <span style="font-size:11px; font-family:Verdana">Add Row</span>
            </li>
            <li id="edit">
                <span class="ui-icon ui-icon-pencil" style="float:left"></span>
                <span style="font-size:11px; font-family:Verdana">Edit Row</span>
            </li>
            <li id="del">
                <span class="ui-icon ui-icon-trash" style="float:left"></span>
                <span style="font-size:11px; font-family:Verdana">Delete Row</span>
            </li>
        </ul>
    </div>
</body>
</html>