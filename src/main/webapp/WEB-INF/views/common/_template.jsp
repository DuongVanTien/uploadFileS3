<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute></title>
<meta name="description" content="">
<meta name="author" content="templatemo">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700'
	rel='stylesheet' type='text/css'>
<link
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/templatemo-style.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/libs/jqplot/jquery.jqplot.min.css"
	rel="stylesheet">


<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.2.0.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/my-script.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/rate-chart.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/libs/jquery/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/libs/jqplot/jqplot.json2.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/libs/jqplot/jqplot.json2.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/libs/jqplot/jquery.jqplot.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/libs/jqplot/jqplot.dateAxisRenderer.js"></script>


</head>
<body>
	<div class="templatemo-flex-row">
		<tiles:insertAttribute name="menu"></tiles:insertAttribute>
		<!-- Main content -->
		<div class="templatemo-content col-1 light-gray-bg">
			<tiles:insertAttribute name="header"></tiles:insertAttribute>
			<div class="templatemo-content-container">
				<tiles:insertAttribute name="body"></tiles:insertAttribute>
			</div>
		</div>
	</div>
	<div>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>
</body>
</html>