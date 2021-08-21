<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Transaction/TransCommon/css/transaction.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/tablestyle.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />

<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Transaction/TransCommon/js/trans_config_filler.js"></script>

<title></title>
</head>
<body>

<script type="text/JavaScript">
$("#content_loader").hide();
</script>

	 <%@include file="../../../../session_check.jsp"%>
	<div class="pagetitlecontainer">
		<!-- Page header -->
		<div class="pagetitle">Transaction</div>
	</div>
	<div class="page">
		<div class="contentcontainer" style="min-height:709px;">
			<div id="trans_super_container" style="display:none;">
			<div class="main_tab_container">
				<!-- This is the main tab bar container -->

				<div id="rfq_tab" class="main_tab_select">
					<!-- This is the RFQ tab -->
					RFQ
				</div>

				<div id="quote_tab" class="main_tab_unselect">
					<!-- This is the Quotation tab -->
					Quote
				</div>

				<div id="po_tab" class="main_tab_unselect">
					<!-- This is the Purchase order tab -->
					Purchase Order
				</div>

				<div id="invoice_tab" class="main_tab_unselect">
					<!-- This is the Invoice tab -->
					Invoice
				</div>
                                <div id="payment_tab" class="main_tab_unselect">
					<!-- This is the Invoice tab -->
					Payment
				</div>

			</div>

			<div class="main_tab_sep">
				<!-- This is the seperator div  -->
			</div>

			<div id="trans_content" class="trans_content">
				<%@include file="../../RFQ/jsp/rfq.jsp"%>
				
			</div>

		</div>
		</div>
	</div>
	
		<%@include file="tc_popup.jsp"%>

		<%@include file="../../../Utils/jsp/Cus_Toast.jsp"%>
	
	  	<%@include file="../../../Utils/jsp/ajax_loader.jsp"%>
	  
	  	<%@include file="../../../Utils/jsp/footer.jsp"%>
	  	
	  	<%@include file="../../../Utils/jsp/Popup_Warning.jsp"%>
	  	
	
<script>

/*$.getScript( "${pageContext.request.contextPath}/Views/Transaction/TransCommon/js/transaction_flow.js");
$.getScript( "${pageContext.request.contextPath}/Views/Transaction/TransCommon/js/transaction.js", function( data, textStatus, jqxhr ) 
{
	transOnload();
		 
});*/


</script>

<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Transaction/TransCommon/js/transaction_flow.js"></script>
	
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Transaction/TransCommon/js/transaction.js"></script>

	
	
</body>
</html>