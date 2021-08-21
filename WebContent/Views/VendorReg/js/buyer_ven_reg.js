function buyerOnload()
{
	
	$("#buyer_ven_reg_content").show();

	$("#content_loader").hide();
	
	$('select.selectbox').customSelect();
	
	
	$("#req_queue_tab").click(regQueueTabClicked);
	
	$("#add_supplier_tab").click(addsupplierTabClicked);
	
	$("#supplier_reg_tab").click(supplierReqTabClicked);
	
	$("#my_reg_tab").click(myRegTabClicked);
	
	
	$("#buyer_form_popup_content").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
	

	if($("#add_vendor_flag").val() == 0 )
	{
		$("#add_supplier_tab").hide();
	
	}
	
	$('body').click(function() {
		$("#ven_search_result").hide();
		});
	
	
	fetchAd( "contentcontainer" );  // It is available in common.js
	
}


/* This method is called when user click the Request Queue tab */
function regQueueTabClicked()
{
	$("#mainpage").empty();
	
	$("#content_loader").show();
	
	$("#mainpage").load("Views/VendorReg/jsp/buyer_ven_reg.jsp", function() 
	{
		$("#content_loader").hide();
		$("#req_queue_content").show();
		$("#add_supplier_content").hide();
		
		$("#req_queue_tab").removeClass("main_tab_unselect");
		$("#req_queue_tab").addClass("main_tab_select");
		
		$("#add_supplier_tab").removeClass("main_tab_select");
		$("#add_supplier_tab").addClass("main_tab_unselect");
	});;
	
	//getBuyerPendingRegReq();
	
	//getBuyerMyPendingRegReq();
	
	//customizeBuyerPopUp( )
}


/* This method is called when user click the Add supplier tab */
function addsupplierTabClicked()
{
	$("#req_queue_content").hide();
	$("#add_supplier_content").show();
	
	$("#req_queue_tab").addClass("main_tab_unselect");
	$("#req_queue_tab").removeClass("main_tab_select");
	
	$("#add_supplier_tab").addClass("main_tab_select");
	$("#add_supplier_tab").removeClass("main_tab_unselect");
}

/* This method is called when user click the supplier Request tab */
function supplierReqTabClicked()
{
	$("#supplier_req_content").show();
	$("#my_req_content").hide();
	
	$("#supplier_reg_tab").removeClass("normal");
	$("#supplier_reg_tab").addClass("highlight");
	
	$("#my_reg_tab").removeClass("highlight");
	$("#my_reg_tab").addClass("normal");
}

/* This method is called when user click the My Request tab */
function myRegTabClicked()
{
	$("#supplier_req_content").hide();
	$("#my_req_content").show();
	
	$("#supplier_reg_tab").addClass("normal");
	$("#supplier_reg_tab").removeClass("highlight");
	
	$("#my_reg_tab").addClass("highlight");
	$("#my_reg_tab").removeClass("normal");
}


