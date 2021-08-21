<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Dashboard/css/dashboard.css" />
	
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />
	
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
	
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Dashboard/css/jquery.jqplot.min.css" />
	
	
	
	

	
<title>Supply Medium</title>
</head>

<body onload="">

<script type="text/JavaScript">
$("#content_loader").hide();


$(function()
{
	
	$( "#start_date" ).datepicker(
	{
		dateFormat: "dd-M-yy",
		
	});
	
	$( "#end_date" ).datepicker(
	{
		dateFormat: "dd-M-yy",
				
	});
}	
);
</script>


	<div class="pagetitlecontainer">
		<div class="pagetitle">Dashboard</div>
	</div>
	<div class="page">
		<div class="dashboardcontainer contentcontainer" style="min-height:834px;">
			
			<div class="main_tab_container">  <!-- This is the main tab bar container -->
			
				<div id="time_based_report_tab" class="main_tab_select">  <!-- This is the Request Queue tab -->
				Time Based Report </div>
				
				<div id="company_based_report_tab" class="main_tab_unselect">	<!-- This is the Add Buyer tab -->
				Company Based Report </div>
				
			</div>
			<div style="clear: both;"></div>
			<div class="main_tab_sep"> <!-- This is the seperator div  -->
			</div>
			
			<div id="divtimebasedreport">
			
				<div class="chartTitlebuttons" style="float:left;">
				
					<div style="float:left;margin-top:5px;margin-right:10px;">
					<select name="title" class="selectbox" id="time_report_type">
							<option value="Daily">Daily</option>
							<option value="Monthly">Monthly</option>
							<option value="Quarterly">Quarterly</option>
							<option value="Yearly">Yearly</option>
					</select>
					</div>
					
					<div id="time_year_div" style="float:left;margin-top:5px;margin-right:10px;">
					<select name="title" class="selectbox" id="time_year">
							<option value="2013">2013</option>
					</select>
					</div>
					
					<div id="time_month_div" style="float:left;margin-top:5px;margin-right:10px;">
					<select name="title" class="selectbox" id="time_month">
							<option value="Jan">Jan</option>
							<option value="Feb">Feb</option>
							<option value="Mar">Mar</option>
							<option value="Apr">Apr</option>
							<option value="May">May</option>
							<option value="Jun">Jun</option>
							<option value="Jul">Jul</option>
							<option value="Aug">Aug</option>
							<option value="Sep">Sep</option>
							<option value="Oct">Oct</option>
							<option value="Nov">Nov</option>
							<option value="Dec">Dec</option>
							
					</select>
					</div>
					
				</div>
				
				<div class="generate_btn_container">
					<input type="button" class="gen-btn-blue generate_btn" value="Generate" id="time_based_report_btn"  >
				</div>
				
				<div class="report_type">
					<div id="time_report_table" class="singleLine ReportTabUnSelect">Report</div>
					<div id="time_report_chart" class="singleLine ReportTabSelected">Chart</div>
				</div>
				
				<div class="clearboth"></div>
				<div class="charTitle" > 
				</div>
				<div class="clearboth"></div>
				<div id="barchart1" style="width:1000px;height:300px;float:left;"></div>
				<div id="piechart1" style="width:1000px;height:300px;float:left;"></div>
				<div class="table" style="display:none;float:left;" id="table1">
				</div>
				
			</div>
			
			
			<div id="divcompanybasedreport" style="display:none;"> <!-- Company Based Report -->
			
			<div class="chartTitlebuttons">
					
					<div style="float:left;margin-top:5px;margin-right:10px;">
					<select name="title" class="selectbox" id="cus_report_type">
							<option value="Daily">Daily</option>
							<option value="Monthly">Monthly</option>
							<option value="Period">Period</option>
							
					</select>
					</div>
					
					<div id="cus_year_div" style="float:left;margin-top:5px;margin-right:10px;">
					<select name="title" class="selectbox" id="cus_year">
							<option value="2013">2013</option>
					</select>
					</div>
					
					<div id="cus_month_div" style="float:left;margin-top:5px;margin-right:10px;">
					<select name="title" class="selectbox" id="cus_month">
							<option value="Jan">Jan</option>
							<option value="Feb">Feb</option>
							<option value="Mar">Mar</option>
							<option value="Apr">Apr</option>
							<option value="May">May</option>
							<option value="Jun">Jun</option>
							<option value="Jul">Jul</option>
							<option value="Aug">Aug</option>
							<option value="Sep">Sep</option>
							<option value="Oct">Oct</option>
							<option value="Nov">Nov</option>
							<option value="Dec">Dec</option>
							
					</select>
					</div>
					
					<input type="text" class="textbox" id="start_date" style="float:left;margin-top:5px;margin-right:10px;display:none;" readonly />
					<input type="text" class="textbox" id="end_date" style="float:left;margin-top:5px;margin-right:10px;display:none;" readonly />
				
				</div>
				
				<div class="generate_btn_container">
					<input type="button" class="gen-btn-blue generate_btn" value="Generate" id="cus_based_report_btn"  >
				</div>
				
				<div class="report_type">
					<div id="cus_report_table" class="singleLine ReportTabUnSelect">Report</div>
					<div id="cus_report_chart" class="singleLine ReportTabSelected">Chart</div>
				</div>
				
				<div class="clearboth"></div>
				<div class="charTitle" > 
				</div>
				<div class="clearboth"></div>
				<div id="barchart2" style="width:1000px;height:300px;float:left;"></div>
				<div id="piechart2" style="width:1000px;height:300px;float:left;"></div>
				<div class="table" style="display:none;float:left;" id="table2">
				</div>
			</div>
			<div id="no_records" class="no_records" style="display:none;float:left;"> No Result Found </div>
			
		</div>
	</div>
	
	<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
	<%@include file="../../Utils/jsp/ajax_loader.jsp"%>

	<%@include file="../../Utils/jsp/footer.jsp"%>

	<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery.customSelect.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/common.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Dashboard/js/jquery.jqplot.min.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Dashboard/js/jqplot.barRenderer.min.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Dashboard/js/jqplot.canvasAxisLabelRenderer.min.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Dashboard/js/jqplot.canvasAxisTickRenderer.min.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Dashboard/js/jqplot.canvasTextRenderer.min.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Dashboard/js/jqplot.categoryAxisRenderer.min.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Dashboard/js/jqplot.highlighter.min.js"></script>
	
		<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Dashboard/js/jqplot.cursor.min.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Dashboard/js/jqplot.pointLabels.min.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Dashboard/js/jqplot.pieRenderer.min.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Dashboard/js/jqplot.donutRenderer.min.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Dashboard/js/timereport.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Dashboard/js/customerreport.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Dashboard/js/dashboard.js"></script>


</body>

</html>