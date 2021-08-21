<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/footer.css" />


	
</head>
	<body>
		<div class="footer">
	    			<div class="footercontainer">
		    			<div class="footerconttext">
		    				<div style="margin-left:auto;margin-right:auto;width:100%;">
		    				<ul class="footertext" style="margin:auto;">
		    					
		    					<li id="footer_admin" class="footertextlist">Admin</li>
			    				<li id="footer_admin_sep" class="footersep"></li>
			    				
			    				<li id="footer_corp" class="footertextlist">Corporate</li>
			    				<li id="footer_corp_sep" class="footersep"></li>
			    				
			    				<li id="footer_search" class="footertextlist">Search</li>
			    				<li id="footer_search_sep" class="footersep"></li>
			    			
			    				
			    				<li id="footer_supplier" class="footertextlist">Suppliers</li>
			    				<li id="footer_supplier_sep" class="footersep"></li>
			    				
			    				<li id="footer_buyers" class="footertextlist">Buyers</li>
			    				<li id="footer_buyers_sep" class="footersep"></li>
			    				
			    				<li id="footer_trans" class="footertextlist">Transactions</li>
			    				<li id="footer_trans_sep" class="footersep"></li>
			    				
			    				<li id="footer_network" class="footertextlist">Network</li>
			    				<li id="footer_network_sep" class="footersep"></li>
			    				
			    				<li id="footer_terms" class="footertextlist">Terms of Services</li>
			    				<li class="footersep"></li>
			    				
			    				<li id="footer_privacy" class="footertextlist">Privacy Policy</li>
		    				</ul>
		    			</div>
		    			</div>
	    				<div class="footerconttext" style="margin-top:10px;">
	    					
	    				</div>
	    				<div class="footerconttext">
	    				© 2013 Copyrights Supply Medium Inc 2013 | All rights reserved
	    				</div>
	    			</div>
		</div>
		
		<%@include file="terms.jsp"%>
		<%@include file="privacy.jsp"%>
	</body>
</html>