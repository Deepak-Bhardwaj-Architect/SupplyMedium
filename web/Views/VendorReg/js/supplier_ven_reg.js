function supplierOnload()
{
	$("#supplier_ven_reg_content").show();

	$("#content_loader").hide();
	
	$('select.selectbox').customSelect();
	
	
	$("#req_queue_tab").click(regQueueTabClicked);
	
	$("#add_buyer_tab").click(addBuyerTabClicked);
	
	$("#buyer_reg_tab").click(buyerReqTabClicked);
	
	$("#my_reg_tab").click(myRegTabClicked);
	
	
	if($("#add_vendor_flag").val() == 0 )
	{
		$("#add_buyer_tab").hide();
	
	}
	
	$("#supplier_form_popup_content").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
	
	$('body').click(function()
	{
		$("#ven_search_result").hide();
	});
	
	
	fetchAd( "contentcontainer" );  // It is available in common.js
	
}


/* This method is called when user click the Request Queue tab */
function regQueueTabClicked()
{
	$("#mainpage").empty();
	
	$("#content_loader").show();
	
	$("#mainpage").load("Views/VendorReg/jsp/supplier_ven_reg.jsp", function() 
	{
		$("#content_loader").hide();

		$("#req_queue_content").show();
		$("#add_buyer_content").hide();
		
		$("#req_queue_tab").removeClass("main_tab_unselect");
		$("#req_queue_tab").addClass("main_tab_select");
		
		$("#add_buyer_tab").removeClass("main_tab_select");
		$("#add_buyer_tab").addClass("main_tab_unselect");
		
	});;
	
	//getSupplierPendingRegReq();
	
	//getSupplierMyPendingRegReq();
	
	//customizeSupplierPopUp( );
	
}


/* This method is called when user click the Add Buyer tab */
function addBuyerTabClicked()
{
	$("#req_queue_content").hide();
	$("#add_buyer_content").show();
	
	$("#req_queue_tab").addClass("main_tab_unselect");
	$("#req_queue_tab").removeClass("main_tab_select");
	
	$("#add_buyer_tab").addClass("main_tab_select");
	$("#add_buyer_tab").removeClass("main_tab_unselect");
}

/* This method is called when user click the Buyer Request tab */
function buyerReqTabClicked()
{
	$("#buyer_req_content").show();
	$("#my_req_content").hide();
	
	$("#buyer_reg_tab").removeClass("normal");
	$("#buyer_reg_tab").addClass("highlight");
	
	$("#my_reg_tab").removeClass("highlight");
	$("#my_reg_tab").addClass("normal");
}

/* This method is called when user click the My Request tab */
function myRegTabClicked()
{
	$("#buyer_req_content").hide();
	$("#my_req_content").show();
	
	$("#buyer_reg_tab").addClass("normal");
	$("#buyer_reg_tab").removeClass("highlight");
	
	$("#my_reg_tab").addClass("highlight");
	$("#my_reg_tab").removeClass("normal");
}


