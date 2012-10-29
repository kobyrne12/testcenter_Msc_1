
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />

	<title>Droppable Between Panes</title>

	<link rel="stylesheet" type="text/css" media="screen" href="css/droppable_layout.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="css/droppable_widget.js.css" />

	<script type="text/javascript" src="../lib/js/jquery-latest.js"></script>
	<script type="text/javascript" src="../lib/js/jquery-ui-latest.js"></script>
	<script type="text/javascript" src="../lib/js/jquery.layout-latest.js"></script>

	<script>
	var outerLayout;

	/*
	*#######################
	*		 ON PAGE LOAD
	*#######################
	*/
	$(document).ready( function() {

		// create the OUTER LAYOUT
		outerLayout = $("body").layout();

		// init the Sortables
		$(".column").sortable({
			connectWith:	$(".column")
		,	placeholder:	'widget-placeholder'
		,	cursor:			'move'
		//	use a helper-clone that is append to 'body' so is not 'contained' by a pane
		,	helper:			function (evt, ui) { return $(ui).clone().appendTo('body').show(); }
		,	over:			function (evt, ui) {
								var
									$target_UL	= $(ui.placeholder).parent()
								,	targetWidth	= $target_UL.width()
								,	helperWidth	= ui.helper.width()
								,	padding		= parseInt( ui.helper.css('paddingLeft') )
												+ parseInt( ui.helper.css('paddingRight') )
												+ parseInt( ui.helper.css('borderLeftWidth') )
												+ parseInt( ui.helper.css('borderRightWidth') )
								;
								//if (( (helperWidth + padding) - targetWidth ) > 20)
									ui.helper
										.height('auto')
										.width( targetWidth - padding )
									;
							}
		});

		$("#draggable").draggable({
		//	use a helper-clone that is append to 'body' so is not 'contained' by a pane
			helper:	function () { return $(this).clone().appendTo('body').css('zIndex',5).show(); }
		,	cursor:	'move'
		});

		$('#droppable').droppable({ 
		   accept:	'#draggable'
		,  drop:	function () { alert('The Draggable was Dropped!'); } 
		}); 

	});

	</script>

</head>
<body>

<div class="ui-layout-center">
	<ul id="column1" class="column">
		<li class="widget color-green" id="widget_100>">
			<div class="widget-head">
				<h3>MAIN 1</h3>
			</div>
			<div class="widget-content">
				Lorem ipsum fez kfez f	ezf ez
			</div>
		</li>
		<li class="widget color-green" id="widget_101>">
			<div class="widget-head">
				<h3>MAIN 2</h3>
			</div>
			<div class="widget-content">
				Lorem ipsum fez kfez f	ezf ez
			</div>
		</li>
	</ul>
	<ul id="column3" class="column">
		<li class="widget color-green" id="widget_102>">
			<div class="widget-head">
				<h3>MAIN 3</h3>
			</div>
			<div class="widget-content">
				Lorem ipsum fez kfez f	ezf ez
			</div>
		</li>
		<li class="widget color-green" id="widget_103>">
			<div class="widget-head">
				<h3>MAIN 4</h3>
			</div>
			<div class="widget-content">
				Lorem ipsum fez kfez f	ezf ez
				Lorem ipsum fez kfez f	ezf ez
				Lorem ipsum fez kfez f	ezf ez
				Lorem ipsum fez kfez f	ezf ez
				Lorem ipsum fez kfez f	ezf ez
				Lorem ipsum fez kfez f	ezf ez
				Lorem ipsum fez kfez f	ezf ez
				Lorem ipsum fez kfez f	ezf ez
				Lorem ipsum fez kfez f	ezf ez
				Lorem ipsum fez kfez f	ezf ez
				Lorem ipsum fez kfez f	ezf ez
			</div>
		</li>
	</ul>

	<div id="droppable" style="margin: 0 1em; padding: 1.5em; text-align: center; border: 2px solid #666; background: #666;">
		Drop-target for draggable-div in North-pane
	</div>

</div>


<div class="ui-layout-east">
	<ul id="column4" class="column">
		<li class="widget color-green" id="widget_201>">
			<div class="widget-head">
				<h3>EAST 1</h3>
			</div>
			<div class="widget-content">
				Lorem ipsum fez kfez f	ezf ez
			</div>
		</li>
		<li class="widget color-green" id="widget_202>">
			<div class="widget-head">
				<h3>EAST 2</h3>
			</div>
			<div class="widget-content">
				Lorem ipsum fez kfez f	ezf ez
			</div>
		</li>
	</ul>
</div>


<div class="ui-layout-west">
	<ul id="column1" class="column">
		<li class="widget color-green" id="widget_300>">
			<div class="widget-head">
				<h3>WEST 1</h3>
			</div>
			<div class="widget-content">
				Lorem ipsum fez kfez f	ezf ez
			</div>
		</li>
		<li class="widget color-green" id="widget_301>">
			<div class="widget-head">
				<h3>WEST 2</h3>
			</div>
			<div class="widget-content">
				Lorem ipsum fez kfez f	ezf ez
				Lorem ipsum fez kfez f	ezf ez
				Lorem ipsum fez kfez f	ezf ez
				Lorem ipsum fez kfez f	ezf ez
				Lorem ipsum fez kfez f	ezf ez
			</div>
		</li>
	</ul>
	<ul id="column2" class="column">
		<li class="widget color-green" id="widget_302>">
			<div class="widget-head">
				<h3>WEST 3</h3>
			</div>
			<div class="widget-content">
				Lorem ipsum fez kfez f	ezf ez
			</div>
		</li>
		<li class="widget color-green" id="widget_303>">
			<div class="widget-head">
				<h3>WEST 4</h3>
			</div>
			<div class="widget-content">
				Lorem ipsum fez kfez f	ezf ez
			</div>
		</li>
	</ul>
</div>


<div class="ui-layout-north">
	North
	<div id="draggable" style="width: 30ex; border: 2px solid #CCC; background: #009; padding: 10px;">
	I'm a draggable-div...<br />drop me on the target below</div>
</div>

<div class="ui-layout-south">South</div>


</body>
</html>