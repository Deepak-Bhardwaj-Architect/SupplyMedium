<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/commonlayout.css" />
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/elements.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/History/css/customerhistory.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/History/css/add_ratings.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/History/css/add_remainder.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/History/css/download_info.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/History/css/email_composer.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/History/css/remainders.css" />
<link rel="stylesheet" 
	href="${pageContext.request.contextPath}/Views/Utils/css/jquery-ui-1.10.0.custom.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />
	
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/tablestyle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/UserRatings/css/jRating.jquery.css" />

	


<title>Supply Medium</title>
</head>
<body>

<script type="text/JavaScript">
$("#content_loader").hide();
hideAjaxLoader();
</script>

 <%@include file="../../../session_check.jsp"%>

	<div class="pagetitlecontainer">
		<div class="pagetitle">Customer Transaction History</div>
	</div>
	
	<div class="page">
		<div class="contentcontainer" style="min-height: 0px;">
		<form name="customerhistoryfrm" id="customerhistoryfrm" style="min-height:600px;">
		<div id="customerhistory_container" >
			<div class="tabbar">
			<div class="customerhistoryerr" id="customerhistoryblerr"></div>
				<div class="highlight" id="customer_history" style="display:block;">Customer History</div>

				<div class="gray_disable" id="transaction_history" style="display:block;">Transaction History</div>
				</div>

			
		
				<div class="customerhistorycontent" id="customer_history_content" style="display:block;">
				<div class="tablecontent" id="table_content" style="position:relative;">
					<div class="DT_border" >
					</div>
						<table id="CustomerList" style="width: 997px;">
							<thead >
								<tr>
									<th class="rowBorder">Phone No</th>
									<th class="rowBorder">Company Name</th>
									<th class="rowBorder">Address</th>
									<th class="rowBorder">Email</th>
									<th class="rowBorder">History</th>
									<th class="rowBorder">Updates</th>
									<th class="rowBorder">Documents</th>
									<th class="rowBorder">Ratings</th>
								</tr>
								</thead>
								<tbody>
								</tbody>
						</table>
						
				</div>
			</div>
			
		
			<div class="transactionhistorycontent" id="transaction_history_content" style="display: none;">
				<div id="customer_info" class="customer_info">
					<div class="trans_history_row">
						<label class="trans_history_lbl">Customer Name: </label>
						<label class="trans_history_lbl" style="width:400px;" id="cusName"></label>
					</div>
					<div class="trans_history_row">
						<label class="trans_history_lbl">Customer Phone No: </label>
						<label class="trans_history_lbl" style="width:400px;" id="cusId"></label>
					</div>
					<div class="trans_history_row">
						<label class="trans_history_lbl">Address: </label>
						<label class="trans_history_lbl" style="width:400px;" id="cusAddress"></label>
					</div>
					<div class="trans_history_row">
						<label class="trans_history_lbl">City: </label>
						<label class="trans_history_lbl" style="width:400px;" id="city"></label>
					</div>
					<div class="trans_history_row">
						<label class="trans_history_lbl">State: </label>
						<label class="trans_history_lbl" style="width:400px;" id="state"></label>
					</div>
					<div class="trans_history_row">
						<label class="trans_history_lbl">Type: </label>
						<label class="trans_history_lbl" style="width:400px;" id="addType"></label>
					</div>
				</div>
				
				<div class="tablecontent" id="table_content" style="position:relative;margin-top:190px;">
					<div class="DT_border" >
					</div>
						<table id="TransList" style="width: 997px;">
							<thead >
								<tr>
									<th class="rowBorder">Transaction No</th>
									<th class="rowBorder">Date</th>
									<th class="rowBorder">Amount</th>
									<th class="rowBorder">Reminder</th>
									<th class="rowBorder">XML File</th>
									<th class="rowBorder">Status</th>
									<th class="rowBorder">Feedback</th>
								</tr>
							</thead>
								
							<tbody>
								
							</tbody>
							
						</table>
						
					</div>
			</div>
		
			
			</div>
			
			
		
			</form>
			
			<form name="recent_trans_edi_download_form" id="recent_trans_edi_download_form" action="${pageContext.request.contextPath}/EDIFileDownloadServlet" 
					method="post" enctype="multipart/form-data">
				<input type="hidden" id="recent_trans_id" name="TransId">
				<input type="hidden"  name="RequestType" value="LatestEDIFiles">
			</form>
			
			<form name="recent_trans_edi_download_form" id="specific_trans_edi_download_form" action="${pageContext.request.contextPath}/EDIFileDownloadServlet" 
					method="post" enctype="multipart/form-data">
				<input type="hidden" id="sepcific_trans_id" name="TransId" >
				<input type="hidden" id="trans_types" name="TransTypes" >
				<input type="hidden" name="RequestType" value="SpecificEDIFiles">
				
			</form>
			
		</div>
	</div>
	
	<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
	
	<%@include file="../../Utils/jsp/ajax_loader.jsp"%>
	  
	<%@include file="../../Utils/jsp/footer.jsp"%>
	
	<%@include file="add_ratings.jsp"%>
	
	<%@include file="add_remainder.jsp"%>
	
	
	<%@include file="download_info.jsp"%>
	
	<%@include file="email_composer.jsp"%>
	
	<%@include file="remainders.jsp"%>
	
	
<script>

$.getScript( "${pageContext.request.contextPath}/Views/Utils/js/jquery-ui-timepicker-addon.js", function( data, textStatus, jqxhr ) 
{
	$( "#remainder_due_date" ).datetimepicker( 
		{
			dateFormat: "dd-M-yy",
			showOn: "button",
			buttonImage: "/SupplyMedium/Views/History/images/calendar.png",
		    buttonImageOnly: true
		} );
});

$.getScript( "${pageContext.request.contextPath}/Views/History/js/ratings.js", function( data, textStatus, jqxhr ) 
{
	$.getScript( "${pageContext.request.contextPath}/Views/UserRatings/js/jRating.jquery.js", function( data, textStatus, jqxhr ) 
			{
			
				
				$("#add_ratings_star").jRating(
				{
					
							isDisabled : false,
							type : 'more_big',
							onClick : function(element, rate)
							{ 
								addRatings( rate );
							}

				});
			});
	 
});
	 



$.getScript( "${pageContext.request.contextPath}/Views/History/js/remainder.js", function( data, textStatus, jqxhr ) {
	$("#save_remainder_btn").click( addReminder );
	 
});
$.getScript( "${pageContext.request.contextPath}/Views/History/js/transhistory.js", function( data, textStatus, jqxhr ) {
	transHistoryOnload();
	 
});
$.getScript( "${pageContext.request.contextPath}/Views/History/js/customerhistory.js", function( data, textStatus, jqxhr ) {
	cusHistoryOnload();
		 
});



</script>
	

<!-- <script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/UserRatings/js/jRating.jquery.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/History/js/ratings.js"></script> 

<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/History/js/remainder.js"></script> 
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/History/js/transhistory.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/History/js/customerhistory.js"></script> -->

	
	

</body>
</html>