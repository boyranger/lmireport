<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>报表测试</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
<SCRIPT LANGUAGE="JavaScript">

var now= new Date();
var year=now.getYear();
var month=now.getMonth()+1;
var day=now.getDate();
var hour=now.getHours();
var minute=now.getMinutes();
var second=now.getSeconds();
var t = year+"-"+month+"-"+day+" "+hour+":"+":"+minute+":"+second;
document.write('<div align="center">');
document.write('开始时间:'+t);
document.write('<div align="center">');
  </SCRIPT>
  <body>
<div align="center">
    <applet alt="1" code="com.chinacreator.ireport.Ireport.class" archive="ireport_fat11.jar"></applet>
</div>
  </body>
</html>
