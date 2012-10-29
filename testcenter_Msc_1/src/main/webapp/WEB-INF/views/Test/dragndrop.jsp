<!DOCTYPE HTML>
<html>
<head>
<style type="text/css">
#div1 {width:336px;height:70px;padding:10px;border:1px solid #aaaaaa;}
#div2 {
width: 336px;
height: 69px;
border:1px solid #aaaaaa;
-webkit-border-radius: 1050px;
-moz-border-radius: 1050px;
border-radius: 50px;
}
#div3 {
width: 100px;
height: 100px;
border:1px solid #aaaaaa;
-webkit-border-radius: 1050px;
-moz-border-radius: 1050px;
border-radius: 50px;
}
</style>
<script type="text/javascript">
function allowDrop(ev)
{
ev.preventDefault();
}
function drag(ev)
{
ev.dataTransfer.setData("Text",ev.target.id);
}
function drop(ev)
{
var data=ev.dataTransfer.getData("Text");
ev.target.appendChild(document.getElementById(data));
ev.preventDefault();
}
</script>
</head>
<body>
<div id="div1" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
<div id="div2" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
<div id="div3" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
<img id="mountain" src="mountain.png" draggable="true" ondragstart="drag(event)" width="336" height="69" />
<img id="circle" src="Circle.png" draggable="true" ondragstart="drag(event)" />
<img id="RoundedRectangle" src="RoundedRectangle.png" draggable="true" ondragstart="drag(event)" />
</body>
</html>