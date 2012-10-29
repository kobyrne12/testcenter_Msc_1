<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<title>TestCenter - Enter Results</title>
		<link rel="stylesheet" href="../css/style.css" type="text/css" media="screen" />
		<style>
		 .opaqueLayer
            {
                display:none;
                position:absolute;
                top:0px;
                left:0px;
                opacity:0.6;
                filter:alpha(opacity=60);
                background-color: #000000;
                z-Index:1000;
            }
            </style>
		 <script type="text/javascript" src="../resources/js/jquery/jquery-1.4.4.min.js"></script>
	 	<script type="text/javascript">
	     var jq = jQuery.noConflict();
		 </script>
		<script type="text/javascript">
			function loadTestDetails(TD_ID)
			{
				var t = "TestDetails_"+TD_ID;
				var i = "DropDownImg_"+TD_ID;
				var c = "TestDetailsCell_"+TD_ID;
				
				var f = document.getElementById(t);
				if(f)
				{ 
					if (document.getElementById(t).style.display == "")
					{
						document.getElementById(t).style.display = 'none';	
						document.getElementById(i).src = '../images/down.png';	
						document.getElementById(c).style.color = '#707070';						
					}
					else 
					{
						document.getElementById(t).style.display = '';	
						document.getElementById(i).src = '../images/up.png';	
						document.getElementById(c).style.color = '#2554C7'; 							
					}		
				}	
			}
		</script>
		<script type="text/javascript"> 	
	
				
		
// 			function passed_Ajax(tc_ID,tp_ID) {		
// 				jq(function() 
// 					{							
// 				        if(jq("#passed_"+ tc_ID).attr("src") == "../images/ok_blank.PNG")
// 				        {	        
// 				        	var src = jq("#passed_"+ tc_ID).attr("src").replace("ok_blank.PNG", "green_load.gif");
// 				        	jq("#passed_"+ tc_ID).attr("src", src);				        	
// 							jq.post("passed_Ajax",
// 							{   testCaseid: tc_ID,
// 								testPlanID: tp_ID		
// 								//var str = $("form").serialize();
// 								//$("#results").text(str);
// 							},
// 							jq(function() {	
// 								document.getElementById("passed_"+ tc_ID).src = '../images/arrows.gif';	
// 								jq.post("notrun_Ajax",
// 								{   testCaseid: tc_ID,
// 									testPlanID: tp_ID									  	
// 								},
// 								function(returnedText){	
// 									 var returnedTextArray = returnedText.split("_splithere_");
// 									 var tc_ID = returnedTextArray[0];
// 									 var totalTests = returnedTextArray[1];
// 									 var totalNotRun = returnedTextArray[2];
// 									 var totalPassed = returnedTextArray[3];
// 									 var totalFailed = returnedTextArray[4];
// 									 var totalInprogress = returnedTextArray[5];
// 									 var totalDeferred = returnedTextArray[6];
// 									 var totalBlocked = returnedTextArray[7];
									 
// 									 document.getElementById("notrun_"+ tc_ID).src = '../images/NA_blank.PNG';	
// 									 document.getElementById("failed_"+ tc_ID).src = '../images/no_blank.PNG';	
// 									 document.getElementById("inprogress_"+ tc_ID).src = '../images/IP_blank.PNG';	
// 									 document.getElementById("deferred_"+ tc_ID).src = '../images/def_blank.PNG';	
// 									 document.getElementById("blocked_"+ tc_ID).src = '../images/block_blank.png';
									 								 
// 									 document.getElementById("totalNotRun").innerHTML = "Not Run ("+totalNotRun+"/"+totalTests+")";
// 									 document.getElementById("totalPassed").innerHTML = "Passed ("+totalPassed+"/"+totalTests+")";
// 									 document.getElementById("totalFailed").innerHTML = "Failed ("+totalFailed+"/"+totalTests+")";
// 									 document.getElementById("totalInprogress").innerHTML = "In Progress ("+totalInprogress+"/"+totalTests+")";
// 									 document.getElementById("totalDeferred").innerHTML = "Deferred ("+totalDeferred+"/"+totalTests+")";
// 									 document.getElementById("totalBlocked").innerHTML = "Blocked ("+totalBlocked+"/"+totalTests+")";
									 
// 									 document.getElementById("passed_"+ tc_ID).src = '../images/ok.png';
									 
// 								 // data contains the result
// 								 // Assign result to the sum id
// 								// jq("#sum").replaceWith('<span id="sum"> Updated :'+ tc_ID +'</span>');
								 
								
									
// 								// document.getElementById("testState_"+tc_ID).style.display = '';
// 								// var src = $(this).attr("src").replace("over.gif", ".gif");
// 						         // $(this).attr("src", src);
// 			// 					 jq("#passed_"+ tc_ID).src('../images/ok.png');
// 			// 					 jq("#notRun_"+ tc_ID).src('../images/NA_blank.PNG');
// 			// 					 jq("#failed_"+ tc_ID).src('../images/no_blank.PNG');
// 			// 					 jq("#inprogress_"+ tc_ID).src('../images/IP_blank.PNG');
// 			// 					 jq("#deferred_"+ tc_ID).src('../images/def_blank.PNG');
// 			// 					 jq("#blocked_"+ tc_ID).src('../images/block_blank.png');
// 								});
// 							 });
// 						}
					
// 			function notrun_Ajax(tc_ID,tp_ID) {
// 				 jq("#notRun_"+ tc_ID).ajaxStart(function(){ 
// 					  //$('#ajaxBusy').show(); 
// 					  //jq("#ajaxBusy").show();
// 					  jq("#sum").replaceWith('<span id="sum"> Updating...</span>');
// 					}).ajaxStop(function(){ 
// 					  //jq("#ajaxBusy").hide();
// 					  jq("#sum").replaceWith('<span id="sum"> Done </span>');
// 					  //$('#ajaxBusy').hide();
// 					});
// 				jq(function() {	
// 					document.getElementById("notrun_"+ tc_ID).src = '../images/arrows.gif';	
// 					jq.post("notrun_Ajax",
// 					{   testCaseid: tc_ID,
// 						testPlanID: tp_ID									  	
// 					},
// 					function(returnedText){	
// 						 var returnedTextArray = returnedText.split("_splithere_");
// 						 var tc_ID = returnedTextArray[0];
// 						 var totalTests = returnedTextArray[1];
// 						 var totalNotRun = returnedTextArray[2];
// 						 var totalPassed = returnedTextArray[3];
// 						 var totalFailed = returnedTextArray[4];
// 						 var totalInprogress = returnedTextArray[5];
// 						 var totalDeferred = returnedTextArray[6];
// 						 var totalBlocked = returnedTextArray[7];
						 
// 						 document.getElementById("passed_"+ tc_ID).src = '../images/ok_blank.PNG';							
// 						 document.getElementById("failed_"+ tc_ID).src = '../images/no_blank.PNG';	
// 						 document.getElementById("inprogress_"+ tc_ID).src = '../images/IP_blank.PNG';	
// 						 document.getElementById("deferred_"+ tc_ID).src = '../images/def_blank.PNG';	
// 						 document.getElementById("blocked_"+ tc_ID).src = '../images/block_blank.png';
						 								 
// 						 document.getElementById("totalNotRun").innerHTML = "Not Run ("+totalNotRun+"/"+totalTests+")";
// 						 document.getElementById("totalPassed").innerHTML = "Passed ("+totalPassed+"/"+totalTests+")";
// 						 document.getElementById("totalFailed").innerHTML = "Failed ("+totalFailed+"/"+totalTests+")";
// 						 document.getElementById("totalInprogress").innerHTML = "In Progress ("+totalInprogress+"/"+totalTests+")";
// 						 document.getElementById("totalDeferred").innerHTML = "Deferred ("+totalDeferred+"/"+totalTests+")";
// 						 document.getElementById("totalBlocked").innerHTML = "Blocked ("+totalBlocked+"/"+totalTests+")";
						 
						
// 						 // data contains the result
// 						 // Assign result to the sum id
// 						 //jq("#sum").replaceWith('<span id="sum"> Updated :'+ tc_ID +'</span>');						
// 						});
// 					 });
// 				}
			
			function notrun_Ajax(tc_ID,tp_ID) {		
				jq(function() 
					{							
				        if(jq("#notrun_"+ tc_ID).attr("src") == "../images/NA_blank.PNG")
				        {	        
				        	var src = jq("#notrun_"+ tc_ID).attr("src").replace("NA_blank.PNG", "arrows.gif");
				        	jq("#notrun_"+ tc_ID).attr("src", src);				        	
							jq.post("notrun_Ajax",{testCaseid:tc_ID, testPlanID:tp_ID},
							function(returnedText){	
								 var returnedTextArray = returnedText.split("_splithere_");
								 var tc_ID = returnedTextArray[0];
								 var totalTests = returnedTextArray[1];
								 var totalNotRun = returnedTextArray[2];
								 var totalPassed = returnedTextArray[3];
								 var totalFailed = returnedTextArray[4];
								 var totalInprogress = returnedTextArray[5];
								 var totalDeferred = returnedTextArray[6];
								 var totalBlocked = returnedTextArray[7];
								 
								 document.getElementById("passed_"+ tc_ID).src = '../images/ok_blank.PNG';							
								 document.getElementById("failed_"+ tc_ID).src = '../images/no_blank.PNG';	
								 document.getElementById("inprogress_"+ tc_ID).src = '../images/IP_blank.PNG';	
								 document.getElementById("deferred_"+ tc_ID).src = '../images/def_blank.PNG';	
								 document.getElementById("blocked_"+ tc_ID).src = '../images/block_blank.png';
								 								 
								 document.getElementById("totalNotRun").innerHTML = "Not Run ("+totalNotRun+"/"+totalTests+")";
								 document.getElementById("totalPassed").innerHTML = "Passed ("+totalPassed+"/"+totalTests+")";
								 document.getElementById("totalFailed").innerHTML = "Failed ("+totalFailed+"/"+totalTests+")";
								 document.getElementById("totalInprogress").innerHTML = "In Progress ("+totalInprogress+"/"+totalTests+")";
								 document.getElementById("totalDeferred").innerHTML = "Deferred ("+totalDeferred+"/"+totalTests+")";
								 document.getElementById("totalBlocked").innerHTML = "Blocked ("+totalBlocked+"/"+totalTests+")";
								 
								 document.getElementById("notrun_"+ tc_ID).src = '../images/NA.png';							 	
							});
				        }
				});				
			}
			function failed_Ajax(tc_ID,tp_ID) {		
				jq(function() 
					{							
				        if(jq("#failed_"+ tc_ID).attr("src") == "../images/no_blank.PNG")
				        {	        
				        	var src = jq("#failed_"+ tc_ID).attr("src").replace("no_blank.PNG", "red_load.gif");
				        	jq("#failed_"+ tc_ID).attr("src", src);				        	
							jq.post("failed_Ajax",
							{   testCaseid: tc_ID,
								testPlanID: tp_ID									
							},
							function(returnedText){	
								 var returnedTextArray = returnedText.split("_splithere_");
								 var tc_ID = returnedTextArray[0];
								 var totalTests = returnedTextArray[1];
								 var totalNotRun = returnedTextArray[2];
								 var totalPassed = returnedTextArray[3];
								 var totalFailed = returnedTextArray[4];
								 var totalInprogress = returnedTextArray[5];
								 var totalDeferred = returnedTextArray[6];
								 var totalBlocked = returnedTextArray[7];
								 
								 document.getElementById("notrun_"+ tc_ID).src = '../images/NA_blank.PNG';									
								 document.getElementById("inprogress_"+ tc_ID).src = '../images/IP_blank.PNG';	
								 document.getElementById("deferred_"+ tc_ID).src = '../images/def_blank.PNG';	
								 document.getElementById("blocked_"+ tc_ID).src = '../images/block_blank.png';
								 document.getElementById("passed_"+ tc_ID).src = '../images/ok_blank.PNG';
								 								 
								 document.getElementById("totalNotRun").innerHTML = "Not Run ("+totalNotRun+"/"+totalTests+")";
								 document.getElementById("totalPassed").innerHTML = "Passed ("+totalPassed+"/"+totalTests+")";
								 document.getElementById("totalFailed").innerHTML = "Failed ("+totalFailed+"/"+totalTests+")";
								 document.getElementById("totalInprogress").innerHTML = "In Progress ("+totalInprogress+"/"+totalTests+")";
								 document.getElementById("totalDeferred").innerHTML = "Deferred ("+totalDeferred+"/"+totalTests+")";
								 document.getElementById("totalBlocked").innerHTML = "Blocked ("+totalBlocked+"/"+totalTests+")";
								 
								 document.getElementById("failed_"+ tc_ID).src = '../images/no.png';								 	
							});
				        }
				});				
			}
			function passed_Ajax(tc_ID,tp_ID) {		
				jq(function() 
					{							
				        if(jq("#passed_"+ tc_ID).attr("src") == "../images/ok_blank.PNG")
				        {	        
				        	var src = jq("#passed_"+ tc_ID).attr("src").replace("ok_blank.PNG", "green_load.gif");
				        	jq("#passed_"+ tc_ID).attr("src", src);				        	
							jq.post("passed_Ajax",
							{   testCaseid: tc_ID,
								testPlanID: tp_ID									
							},
							function(returnedText){	
								 var returnedTextArray = returnedText.split("_splithere_");
								 var tc_ID = returnedTextArray[0];
								 var totalTests = returnedTextArray[1];
								 var totalNotRun = returnedTextArray[2];
								 var totalPassed = returnedTextArray[3];
								 var totalFailed = returnedTextArray[4];
								 var totalInprogress = returnedTextArray[5];
								 var totalDeferred = returnedTextArray[6];
								 var totalBlocked = returnedTextArray[7];
								 
								 document.getElementById("notrun_"+ tc_ID).src = '../images/NA_blank.PNG';	
								 document.getElementById("failed_"+ tc_ID).src = '../images/no_blank.PNG';	
								 document.getElementById("inprogress_"+ tc_ID).src = '../images/IP_blank.PNG';	
								 document.getElementById("deferred_"+ tc_ID).src = '../images/def_blank.PNG';	
								 document.getElementById("blocked_"+ tc_ID).src = '../images/block_blank.png';
								 								 
								 document.getElementById("totalNotRun").innerHTML = "Not Run ("+totalNotRun+"/"+totalTests+")";
								 document.getElementById("totalPassed").innerHTML = "Passed ("+totalPassed+"/"+totalTests+")";
								 document.getElementById("totalFailed").innerHTML = "Failed ("+totalFailed+"/"+totalTests+")";
								 document.getElementById("totalInprogress").innerHTML = "In Progress ("+totalInprogress+"/"+totalTests+")";
								 document.getElementById("totalDeferred").innerHTML = "Deferred ("+totalDeferred+"/"+totalTests+")";
								 document.getElementById("totalBlocked").innerHTML = "Blocked ("+totalBlocked+"/"+totalTests+")";
								 
								 document.getElementById("passed_"+ tc_ID).src = '../images/ok.png';						 	
							});
				        }
				});				
			}
			function inprogress_Ajax(tc_ID,tp_ID) {		
				jq(function() 
					{							
				        if(jq("#inprogress_"+ tc_ID).attr("src") == "../images/IP_blank.PNG")
				        {	        
				        	var src = jq("#inprogress_"+ tc_ID).attr("src").replace("IP_blank.PNG", "yellow_load.gif");
				        	jq("#inprogress_"+ tc_ID).attr("src", src);				        	
							jq.post("inprogress_Ajax",
							{   testCaseid: tc_ID,
								testPlanID: tp_ID									
							},
							function(returnedText){	
								 var returnedTextArray = returnedText.split("_splithere_");
								 var tc_ID = returnedTextArray[0];
								 var totalTests = returnedTextArray[1];
								 var totalNotRun = returnedTextArray[2];
								 var totalPassed = returnedTextArray[3];
								 var totalFailed = returnedTextArray[4];
								 var totalInprogress = returnedTextArray[5];
								 var totalDeferred = returnedTextArray[6];
								 var totalBlocked = returnedTextArray[7];
								 
								 document.getElementById("notrun_"+ tc_ID).src = '../images/NA_blank.PNG';									 
								 document.getElementById("deferred_"+ tc_ID).src = '../images/def_blank.PNG';	
								 document.getElementById("blocked_"+ tc_ID).src = '../images/block_blank.png';
								 document.getElementById("passed_"+ tc_ID).src = '../images/ok_blank.PNG';
								 document.getElementById("failed_"+ tc_ID).src = '../images/no_blank.PNG';	
								 								 
								 document.getElementById("totalNotRun").innerHTML = "Not Run ("+totalNotRun+"/"+totalTests+")";
								 document.getElementById("totalPassed").innerHTML = "Passed ("+totalPassed+"/"+totalTests+")";
								 document.getElementById("totalFailed").innerHTML = "Failed ("+totalFailed+"/"+totalTests+")";
								 document.getElementById("totalInprogress").innerHTML = "In Progress ("+totalInprogress+"/"+totalTests+")";
								 document.getElementById("totalDeferred").innerHTML = "Deferred ("+totalDeferred+"/"+totalTests+")";
								 document.getElementById("totalBlocked").innerHTML = "Blocked ("+totalBlocked+"/"+totalTests+")";
								 
								 document.getElementById("inprogress_"+ tc_ID).src = '../images/IP.png';								 	
							});
				        }
				});				
			}
			function deferred_Ajax(tc_ID,tp_ID) {		
				jq(function() 
					{							
				        if(jq("#deferred_"+ tc_ID).attr("src") == "../images/def_blank.PNG")
				        {	        
				        	var src = jq("#deferred_"+ tc_ID).attr("src").replace("def_blank.PNG", "purple_load.gif");
				        	jq("#deferred_"+ tc_ID).attr("src", src);				        	
							jq.post("deferred_Ajax",
							{   testCaseid: tc_ID,
								testPlanID: tp_ID									
							},
							function(returnedText){	
								 var returnedTextArray = returnedText.split("_splithere_");
								 var tc_ID = returnedTextArray[0];
								 var totalTests = returnedTextArray[1];
								 var totalNotRun = returnedTextArray[2];
								 var totalPassed = returnedTextArray[3];
								 var totalFailed = returnedTextArray[4];
								 var totalInprogress = returnedTextArray[5];
								 var totalDeferred = returnedTextArray[6];
								 var totalBlocked = returnedTextArray[7];
								 
								 document.getElementById("notrun_"+ tc_ID).src = '../images/NA_blank.PNG';									
								 document.getElementById("blocked_"+ tc_ID).src = '../images/block_blank.png';
								 document.getElementById("passed_"+ tc_ID).src = '../images/ok_blank.PNG';
								 document.getElementById("failed_"+ tc_ID).src = '../images/no_blank.PNG';	
								 document.getElementById("inprogress_"+ tc_ID).src = '../images/IP_blank.PNG';	
								 								 
								 document.getElementById("totalNotRun").innerHTML = "Not Run ("+totalNotRun+"/"+totalTests+")";
								 document.getElementById("totalPassed").innerHTML = "Passed ("+totalPassed+"/"+totalTests+")";
								 document.getElementById("totalFailed").innerHTML = "Failed ("+totalFailed+"/"+totalTests+")";
								 document.getElementById("totalInprogress").innerHTML = "In Progress ("+totalInprogress+"/"+totalTests+")";
								 document.getElementById("totalDeferred").innerHTML = "Deferred ("+totalDeferred+"/"+totalTests+")";
								 document.getElementById("totalBlocked").innerHTML = "Blocked ("+totalBlocked+"/"+totalTests+")";
								 
								 document.getElementById("deferred_"+ tc_ID).src = '../images/def.png';								
								
							});
				        }
				});				
			}
			function blocked_Ajax(tc_ID,tp_ID) {		
				jq(function() 
					{							
				        if(jq("#blocked_"+ tc_ID).attr("src") == "../images/block_blank.png")
				        {	        
				        	var src = jq("#blocked_"+ tc_ID).attr("src").replace("block_blank.png", "black_load.gif");
				        	jq("#blocked_"+ tc_ID).attr("src", src);				        	
							jq.post("blocked_Ajax",
							{   testCaseid: tc_ID,
								testPlanID: tp_ID									
							},
							function(returnedText){	
								 var returnedTextArray = returnedText.split("_splithere_");
								 var tc_ID = returnedTextArray[0];
								 var totalTests = returnedTextArray[1];
								 var totalNotRun = returnedTextArray[2];
								 var totalPassed = returnedTextArray[3];
								 var totalFailed = returnedTextArray[4];
								 var totalInprogress = returnedTextArray[5];
								 var totalDeferred = returnedTextArray[6];
								 var totalBlocked = returnedTextArray[7];
								 
								 document.getElementById("notrun_"+ tc_ID).src = '../images/NA_blank.PNG';								 
								 document.getElementById("passed_"+ tc_ID).src = '../images/ok_blank.PNG';
								 document.getElementById("failed_"+ tc_ID).src = '../images/no_blank.PNG';	
								 document.getElementById("inprogress_"+ tc_ID).src = '../images/IP_blank.PNG';	
								 document.getElementById("deferred_"+ tc_ID).src = '../images/def_blank.PNG';
								 								 
								 document.getElementById("totalNotRun").innerHTML = "Not Run ("+totalNotRun+"/"+totalTests+")";
								 document.getElementById("totalPassed").innerHTML = "Passed ("+totalPassed+"/"+totalTests+")";
								 document.getElementById("totalFailed").innerHTML = "Failed ("+totalFailed+"/"+totalTests+")";
								 document.getElementById("totalInprogress").innerHTML = "In Progress ("+totalInprogress+"/"+totalTests+")";
								 document.getElementById("totalDeferred").innerHTML = "Deferred ("+totalDeferred+"/"+totalTests+")";
								 document.getElementById("totalBlocked").innerHTML = "Blocked ("+totalBlocked+"/"+totalTests+")";
								 
								 document.getElementById("blocked_"+ tc_ID).src = '../images/block.png';								
								 
							});
				        }
				});				
			}
			// ajaxSetup is useful for setting general defaults:
// 			$.ajaxSetup({
// 				url			: '/ajax/',
// 				dataType	: 'json'
// 			});

// 			$.ajaxStart(function(){
// 				showIndicator();
// 				disableButtons();
// 			});

// 			$.ajaxComplete(function(){
// 				hideIndicator();
// 				enableButtons();
// 			});

			/*
				// Additional methods you can use:
				$.ajaxStop();
				$.ajaxError();
				$.ajaxSuccess();
				$.ajaxSend();
			*/
// 		jQuery(document).ready(function ($) {
// 			$('#clickme').click(function() {
// 				  $('#book').animate({
// 				    opacity: 0.25,
// 				    left: '+=50',
// 				    height: 'toggle'
// 				  }, 5000, function() {
// 				    // Animation complete.
// 				  });
// 				});
// 		});
		
// 			// prepare the form when the DOM is ready 
// 			jQuery(document).ready(function() { 
// 			});

// 			// Ajax activity indicator bound to ajax start/stop document events
// 			jQuery(document).ajaxStart(function(){ 
// 			  //$('#ajaxBusy').show(); 
// 			  //jq("#ajaxBusy").show();
// 			 // jq("#sum").replaceWith('<span id="sum"> Updating...</span>');
// 			}).ajaxStop(function(){ 
// 			  //jq("#ajaxBusy").hide();
// 			 // jq("#sum").replaceWith('<span id="sum"> Done </span>');
// 			  //$('#ajaxBusy').hide();
// 			});
		
			
// 				jQuery.preloadImagesInWebPage = function() {
// 				for(var ctr = 0; ctr<arguments.length; ctr++)
// 				{
// 				jQuery("<img>").attr("src", arguments[ctr]);
// 				}
// 				}
// 				//To use the above method, you can use the following piece of code:
// 				$.preloadImages("image1.gif", "image2.gif", "image3.gif");
// 				//To check whether an image has been loaded, you can use the following piece of code:
// 				$('#imageObject').attr('src', 'image1.gif').load(function() {
// 				alert('The image has been loaded…');
// 				});
		</script>
	</head>
	<body>

  
<div id="container">
	<div id="header">
		<a href="../index.html"><img src="../images/title.png"></a>			
		
	</div>
	<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
		<div id="loggedin">
			<security:authentication property="principal.username"/>
		</div>
	</security:authorize>
	<div >
		<ul id="menu">	
			<li><span><a href="../index.html">Home</a></span></li>			
			<li><span><a href="../viewtests/viewtestplan.html">Test Plans</a></span></li>
			<security:authorize ifAllGranted="ROLE_ADMIN">
				<li><span><a href="newtestplan.html">New Test Plan</a></span></li> 				
				<li><span><a href="newtestcase.html?testplanID=${testplan.id}">New Test Case</a></span></li>
			</security:authorize>	
			<li><span class="current">Enter Results</span></li>		
			<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">	
				<li><span><a href="../j_spring_security_logout">Logout</a></span></li>	
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
		
		
		<table width="100%" >
		<tr>
			<td> 
				<a href="../viewtests/viewtestplan.html">${testplan.testplanName}</a> <font color="#7F94B0"> -  ${testplan.testerName} :</font>
			</td>
			<td>
			<table width="100%" border="0" style="font-size:10px; color:#3a4f63;">
	           <tr>
	           		 <td width="14%" align="center" >
	                     <img alt="Not Run" title="Not Run" src="../images/NA.png" />
	                     <span id="totalNotRun">Not Run (${testplan.totalNotRun}/${testplan.totalTests})</span>
	                  </td>
					<td width="14%" align="center">
	               		<img alt="Passed" title="Passed" src="../images/ok.png" />
	              		 <span id="totalPassed">Passed (${testplan.totalPassed}/${testplan.totalTests})</span>
	            	 </td>	                  
	                   <td width="14%" align="center">
	                     <img alt="Failed" title="Failed" src="../images/no.png" />
	                      <span id="totalFailed">Failed (${testplan.totalFailed}/${testplan.totalTests})</span>
	                  </td>
	                  <td width="14%" align="center">
	                      <img alt="In Progress" title="In Progress" src="../images/IP.png" />
	                       <span id="totalInprogress">In Progress (${testplan.totalInProgress}/${testplan.totalTests})</span>
	                  </td>
	                  <td width="14%" align="center">
	                      <img alt="Deferred" title="Deferred" src="../images/def.png" />
	                       <span id="totalDeferred">Deferred (${testplan.totaldeferred}/${testplan.totalTests})</span>
	                  </td> 
	                  <td width="14%" align="center">
	                      <img alt="Blocked" title="Blocked" src="../images/block.png" />
	                       <span id="totalBlocked">Blocked (${testplan.totalBlocked}/${testplan.totalTests})</span>
	                  </td> 
	             </tr>
	         </table>
	         </td>
         </tr>
         </table>
			<BR />
			<span id="sum"></span>
		<table  width="90%" align="center">
		<c:forEach var="testcase" items="${testcases}" varStatus="index">
		<tr>
			<td >
			<div id="boxshadow" >
				<table border="0" width="100%" style="border-collapse: collapse;"> 
				<tr>	
					<security:authorize ifAllGranted="ROLE_ADMIN">		
					<td width="15" valign="top" >
					
						<form name="edittestcase" action="edittestcase.html" method="get">					
							<input type="hidden" name="testplanID" value="${testplan.id}">	
							<input type="hidden" name="testcaseID" value="${testcase.id}">						
							<input type="image" src="../images/edit.png" width="17" alt="Edit Testcase" title="Edit Testcase">
						</form>		
					</td>
					</security:authorize>
					<!--  <td>${testcase.id}</td>-->					
					<td width="10" valign="top">
					</td>
					<td id="TestDetailsCell_${testplan.id}" style="color:#707070;" valign="top" >
						${testcase.testcasename}
					</td>				
					<td width="30">
					</td>
					<td align="right" width="115" valign="top">
						<font color="#808080">${testcase.testcaseOS}</font>
					</td>	
					<td width="15">
					</td>
					<td align="right" width="115" valign="top">
					
					<security:authorize ifAnyGranted="ROLE_ADMIN">
						<!-- Set states table -->
						<table>
						<tr >
						<td valign="top" >
						<c:choose>
							<c:when test="${testcase.notrun}">
								<img style="cursor: pointer;" id="notrun_${testcase.id}" src="../images/NA.png" alt="Not Run" title="Not Run" onclick="notrun_Ajax(${testcase.id},${testplan.id})" >
							</c:when>
							<c:otherwise>
								<img style="cursor: pointer;" id="notrun_${testcase.id}" src="../images/NA_blank.PNG" alt="Not Run" title="Not Run" onclick="notrun_Ajax(${testcase.id},${testplan.id})" >
							</c:otherwise>
						</c:choose>	
						</td>
						<td valign="top">
						<c:choose>
							<c:when test="${testcase.passed}">
								<img style="cursor: pointer;" id="passed_${testcase.id}" src="../images/ok.png" alt="Passed" title="Passed" onclick="passed_Ajax(${testcase.id},${testplan.id})" >
							</c:when>
							<c:otherwise>
								<img style="cursor: pointer;" id="passed_${testcase.id}" src="../images/ok_blank.PNG" alt="Passed" title="Passed" onclick="passed_Ajax(${testcase.id},${testplan.id})" >								
							</c:otherwise>
						</c:choose>	
						</td>
						<td valign="top">
						<c:choose>
							<c:when test="${testcase.failed}">
								<img style="cursor: pointer;" id="failed_${testcase.id}" src="../images/no.png" alt="Failed" title="Failed" onclick="failed_Ajax(${testcase.id},${testplan.id})" >								
							</c:when>
							<c:otherwise>
								<img style="cursor: pointer;" id="failed_${testcase.id}" src="../images/no_blank.PNG" alt="Failed" title="Failed" onclick="failed_Ajax(${testcase.id},${testplan.id})" >						
							</c:otherwise>
						</c:choose>	
						</td>
						<td valign="top">
						<c:choose>
							<c:when test="${testcase.inprogress}">								
								<img style="cursor: pointer;" id="inprogress_${testcase.id}" src="../images/IP.png" alt="In Progress" title="In Progress" onclick="inprogress_Ajax(${testcase.id},${testplan.id})" >								
							</c:when>
							<c:otherwise>
								<img style="cursor: pointer;" id="inprogress_${testcase.id}" src="../images/IP_blank.PNG" alt="In Progress" title="In Progress" onclick="inprogress_Ajax(${testcase.id},${testplan.id})" >								
							
							</c:otherwise>
						</c:choose>
						</td>
						<td valign="top">
						<c:choose>
							<c:when test="${testcase.deferred}">								
								<img style="cursor: pointer;" id="deferred_${testcase.id}" src="../images/def.png" alt="Deferred" title="Deferred" onclick="deferred_Ajax(${testcase.id},${testplan.id})" >							
							</c:when>
							<c:otherwise>
								<img style="cursor: pointer;" id="deferred_${testcase.id}" src="../images/def_blank.PNG" alt="Deferred" title="Deferred" onclick="deferred_Ajax(${testcase.id},${testplan.id})" >	
							</c:otherwise>
						</c:choose>
						</td>
						<td valign="top">	
						<c:choose>
							<c:when test="${testcase.blocked}">								
								<img style="cursor: pointer;" id="blocked_${testcase.id}" src="../images/block.png" alt="Blocked" title="Blocked" onclick="blocked_Ajax(${testcase.id},${testplan.id})" >	
							</c:when>
							<c:otherwise>
								<img style="cursor: pointer;" id="blocked_${testcase.id}" src="../images/block_blank.png" alt="Blocked" title="Blocked" onclick="blocked_Ajax(${testcase.id},${testplan.id})" >	
							</c:otherwise>
						</c:choose>						
						</td>																		
						</tr>
						</table>
						<!-- End of Set states table -->
						</security:authorize>
						<security:authorize ifAllGranted="ROLE_USER">							
							<c:choose>
								<c:when test="${testcase.passed}">
									<font color="green">Passed</font>
								</c:when>
								<c:when test="${testcase.failed}">
									<font color="red">Failed</font>
								</c:when>
								<c:when test="${testcase.inprogress}">
									<font color="orange">In Progress</font>
								</c:when>
								<c:when test="${testcase.deferred}">
									<font color="purple">Deferred</font>
								</c:when>
								<c:when test="${testcase.blocked}">
									<font color="#505050">Blocked</font>
								</c:when>
								<c:otherwise>
									<font color="blue">Not Run</font>
								</c:otherwise>
							</c:choose>	
						</security:authorize>
					</td>	
					<td width="15">
					</td>
					<security:authorize ifAllGranted="ROLE_ADMIN">
					<td align="right" width="15" valign="top">	
						<form name="deletetestcase" action="deletetestcase.html" method="get">
							<input type="hidden" name="testcaseID" value="${testcase.id}">
							<input type="hidden" name="testplanID" value="${testplan.id}">
							<INPUT type="image" src="../images/delete.png" alt="Delete Test" title="Delete Test"  onclick="return confirm('Are you sure you want to delete : \n\n  ${testcase.testcasename} ?')">
						</form>										
					</td>	
					</security:authorize>					
				</tr>
				<tr>
					<td colspan="9" align="center">
						<img style="cursor: pointer;" alt="Expand/Close Test Details" title="Expand/Close Test Details" src="../images/down.png"  id="DropDownImg_${testcase.id}" onclick="loadTestDetails(${testcase.id});">
					</td>
				</tr>						
				</table>
			</div>
			</td>
		</tr>
		<tr>				
			<td id="TestDetails_${testcase.id}" style="display:none;"  align="center"> 
				<table border="0" width="95%">
				<tr>					
					<td width="90%" style="background-color:#E6E8FA;border-color: #707070; border-width: 1px 2px 2px 1px; " >
						<table width="100%" border="0" class="maintable" cellpadding="6" >														
						<tr>
							<td>                           
								<font color="#707070"><b>Test Summary:</b></font><BR>
								<textarea class="enterresults" name="Summary_${testcase.id}" id="Summary_${testcase.id}"  readonly="readonly"  style="overflow:visible;background-color:#FAF8E5;border:none;color:#707070;font-family: Verdana;">${testcase.testcasesummary}</textarea>
							</td>									
						</tr>	
						<tr>
							<td>                           
								<font color="#707070"><b>Test Pre Conditions:</b></font><BR>
								<textarea class="enterresults" name="PreCond${testcase.id}" id="PreCond${testcase.id}"  readonly="readonly" style="overflow:visible;background-color:#FAF8E5;border:none;color:#707070;font-family: Verdana;">${testcase.testcasepre}</textarea>
							</td>									
						</tr>									
						<tr>
							<td>
								<font color="#707070"><b>Test Steps:</b></font><BR>
								<textarea class="enterresultssteps" name="Steps${testcase.id}" id="Steps${testcase.id}"  readonly="readonly"  style="overflow:visible;background-color:#FAF8E5;border:none;color:#707070;font-family: Verdana;">${testcase.testcasesteps}</textarea>
							</td>									
						</tr>		
						<tr>
							<td>                           
								<font color="#707070"><b>Test Pass Conditions:</b></font><BR>
								<textarea  class="enterresults" name="PassCond${testcase.id}" id="PassCond${testcase.id}"  readonly="readonly"style="overflow:visible;background-color:#FAF8E5;border:none;color:#707070;font-family: Verdana;">${testcase.testcasepass}</textarea>
							</td>									
						</tr>													
						</table>
					</td>					
				</tr>
				</table>					
			</td>
		</tr>			
		</c:forEach>
		<security:authorize ifAllGranted="ROLE_ADMIN">
		<tr>			
			<td>
				<br>
				<a href="newtestcase.html?testplanID=${testplan.id}"><img src="../images/plus.png" width="15"> <font color="#707070">New Test Case</font></a>
			</td>
		</tr>
		</security:authorize>
		</table>
	</div>
	<div id="footer">
		Copyright © Testcenter, 2011
	</div>
	<div id="preload">
		<img src="../images/title.png">
		<img src="../images/delete.png" alt="Delete Test Case" title="Delete Test Case" /> 
   		<img src="../images/edit.png" alt="Edit Test Case" title="Edit Test Case" />   				
  		<img src="../images/NA.png" alt="Not Run" title="Not Run">
		<img src="../images/ok.png" alt="Passed" title="Passed" >
		<img src="../images/no.png" alt="Failed" title="Failed">
		<img src="../images/IP.png" alt="In Progress" title="In Progress">
		<img src="../images/def.png" alt="Deferred" title="Deferred">
		<img src="../images/block.png" alt="Blocked" title="Blocked">
		<img src="../images/NA_blank.PNG" alt="Not Run" title="Not Run">
		<img src="../images/ok_blank.PNG" alt="Passed" title="Passed" >
		<img src="../images/no_blank.PNG" alt="Failed" title="Failed">
		<img src="../images/IP_blank.PNG" alt="In Progress" title="In Progress">
		<img src="../images/def_blank.PNG" alt="Deferred" title="Deferred">
		<img src="../images/block_blank.png" alt="Blocked" title="Blocked">	
	</div>
</div>
</body>
</html>