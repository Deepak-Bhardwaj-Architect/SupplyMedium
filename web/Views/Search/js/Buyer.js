/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any.
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 */

/* ON PAGE LOAD */

var advancedSearch=true;

var searchType = "";

var selectedRegnkey = "";

var selectedCompanyName = "";

var regFlag = "true";

var itemArr;

var searchItemCount = 1;

$(function(){
	
	$("#search_buyer_content").show();
	$("#searchTextBox").focus();
	
	function stopRKey(evt) { 
		  var evt = (evt) ? evt : ((event) ? event : null); 
		  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
		} 

	document.onkeypress = stopRKey; 
	
	$("#content_loader").hide();
	
	$(".advancedSearchContainer").hide();
	
	$(".searchHeading").hide();
	
	$(".searchRegistertedResult").hide();
	
	$(".searchNonRegistertedResult").hide();
	
	$("#basic_search_form").validate({
    	//ignore: "",
    	rules: {
    		searchTextBox : 
    		{
    				//required:true,
    				minlength:2
    		},
    		basic_search_cat: 
    		{
    			required:true
    		}
    		
      },
      messages: {
    	  
    	  searchTextBox:  
    	  {
    		  //required:"Enter search text",
    		  minlength: "Search text should contain minium 2 characters"
    	  },
    	  
    	  basic_search_cat: 
    	  {
    		 required: "Select business category"
    	  }
  
      }
    });
	
	$("#advance_search_form").validate({
    	//ignore: "",
    	rules: {
    		
    		Advanced_Selectbox: 
    		{
    			required:true
    		}
    		
      },
      messages: {
    	  
    	  
    	  Advanced_Selectbox: 
    	  {
    		 required: "Select business category"
    	  }
  
      }
    });
	
	$("#searchTextBox").keydown(function(e) // 38-up, 40-down
			{ 
		
				if(e.keyCode == 13)
				{				
					//$("#searchbtn").click();
					basicSearch();
					return false;
				}
			});
	
	fetchCategory();
	
	fetchAd( "pageContent" );  // It is available in common.js
	
	$('select.selectbox').customSelect();
	
	// Advance Search Button function
	
	$("#btnadvancedSearch").click(function()
			{
		
				$(this).toggleClass("btn-Advanced-side");
				
				$(".advancedSearchContainer").slideToggle();
				
				if(advancedSearch)
					{
						//$(".SearchResult").animate({height:'470px'},1000,function(){
							 // $(this).mCustomScrollbar("update");
						//});
						advancedSearch=false;
					}
				else
					{
						//$(".SearchResult").animate({height:'270px'},1000,function(){
							  //$(this).mCustomScrollbar("update");
						//});
						advancedSearch=true;
					}
				
			
				
			});
	
	$("#regSearchResult").mCustomScrollbar({
		theme:"dark-thick",
		scrollInertia:150
	});
	
	$("#nonregSearchResult").mCustomScrollbar({
		theme:"dark-thick",
		scrollInertia:150
	});
	
	
	
	$("#btnAddItem").click(function(){		
		$("#txtPopupPartNumber").val("");
		$("#txtPopupPartName").val("");
		$("#Popup_AddItem").show();		
	});
	
	$("#Popup_AddItem_Add").click(function()
	{
		searchItemCount += 1;
		
		var temp='<div class="advancedRow">'
			+'<label class="advacedLabel" for="">Part Number</label>'
			+'<input value="'+$("#txtPopupPartNumber").val()+'" type="text" id="advanPartNumber'+searchItemCount+'" class="advan_textbox" />'
			+'<input type="button" class="removePartNumber" onclick="removePartNumber(this);">'
			+'</div><div class="advancedRow">'
			+'<label class="advacedLabel" for="">Name</label>'
			+'<input value="'+$("#txtPopupPartName").val()+'" type="text" id="advanName'+searchItemCount+'"  class="advan_textbox" />'
			+'</div>';
		$("#PartNumbercontainer").after(temp);
		$("#Popup_AddItem").hide();
	});
	
	
	
	$("#btnnonGenerateRFQ").click( function ( ) 
	{
		if( selectedRegnkey == "" ) return;
		
		if( regFlag == "false" ) return;
		
		$("#Search").removeClass("navmainSelected");
		$("#Transactions").addClass("navmainSelected");
		
		$("#SubSearch").hide();
		$("#SubTransactions").show();
		
		$("#SubTransactions_Transaction").addClass("navsubSelected");
		
		$("#mainpage").empty();
		
		$("#mainpage").load("Views/Transaction/TransCommon/jsp/transaction.jsp",  function() 
		{
			$("#trans_content").empty();
			
			$('#trans_content').load('Views/Transaction/Quote/jsp/quote.jsp', function() 
			{
				if( searchType == "AdvanceSearch" )
				{
					loadAdvanced( );
				}
				else if( searchType == "BasicSearch" )
				{
					loadBasic( );
				}
				else
				{
					//Do Nothing
				}
			});
		});
	});
	
	$("#btnContact").click( function ( ) 
	{
		if( selectedRegnkey == "" ) return;
		
		if( regFlag == "true" ) return;
		
		if( $("#add_vendor_flag").val() == 0 )
		{
			  ShowToast( "You are not privileged to do this operation",2000 );
			  
			  return;
		}
		
		$("#Search").removeClass("navmainSelected");
		$("#Buyers").addClass("navmainSelected");
		
		$("#SubSearch").hide();
		$("#SubBuyers").show();
		
		$("#SubBuyers_Vendor_Registration").addClass("navsubSelected");
		
		$("#mainpage").empty();
		
		$("#mainpage").load("Views/VendorReg/jsp/supplier_ven_reg.jsp",  function() 
		{
			$("#add_buyer_tab").removeClass("main_tab_unselect");
			$("#add_buyer_tab").addClass("main_tab_select");
			
			$("#add_buyer_content").show( );
			
			$("#req_queue_tab").removeClass("main_tab_select");
			$("#req_queue_tab").addClass("main_tab_unselect");
			
			$("#req_queue_content").hide( );
			
			$("#selected_ven_key").val(selectedRegnkey);
			$("#to_company").val(selectedCompanyName);
			
			//loadVRPage( );	
		});
	});
	
	fetchAd();
});

function SerachResultContainor(vendor,autoNumber, regnKey,rowClass, noOfRatings,avgRatings,bit )
{
	var ratings;
	var dlt="";
	if( noOfRatings == 0 )
	{
		ratings = "No Ratings";
	}
	else
	{
		 ratings = (avgRatings/2)+" out of 5 ( "+noOfRatings+" Ratings )";
	}
        
        if(bit==="delete")
        {
           dlt="Remove"; 
        }
	
	var SerachResult=
		'<div class="searchRow '+rowClass+'" id= '+regnKey+'>'
		+'<div class="searchCol1">'+autoNumber+'</div>'
		+'<div class="searchCol2"><div class="checkContainer">'
		+'<input type="checkbox" id="'+regnKey+'chk" onchange= "setCompany(\''+vendor.companyName+'\', '+regnKey+', \''+vendor.isRegn+'\');"/> <div></div></div></div>'
		+'<div class="searchCol3"><ul>'
				+'<li class="searchCmpName">'+vendor.companyName+'</li>'
				+'<li class="searchCmpAddr"  style="cursor:pointer;" onclick=rmv_frm_ntwrk("'+regnKey+'","Buyer");>'+dlt+'</li>'
				+'<li><span class="searchRattings">'+ratings+'</li>'
			+'</ul></div>'
		+'</div>';
	
	return SerachResult;
}

function removePartNumber(str)
{
	$(str).parent().next().remove();
	$(str).parent().remove();
	
	searchItemCount -= 1;
}

function basicSearch()
{
	searchType = "BasicSearch";
	
	if(!$("#basic_search_form").valid())
		return;
	
	
	var category = $("#basic_search_cat").val();
	
	var keyword = $("#searchTextBox").val();
	
	var searchRegd = '0';
	
	if ($('#searchRegistered').is(':checked'))
	{
		searchRegd = '1';
	} 
	else
	{
		searchRegd = '0';
	}
	
	var regnKey = $("#regnkey").val();
	
	showAjaxLoader();
	
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/SearchBuyerServlet",
		data : {
			'SearchType' : 'BasicSearch',
			'RegnKey' : regnKey,
			'Category' : category,
			'Keyword' : keyword,
			'SearchRegd' : searchRegd,
		},
		cache : false,
		success : function(resultJSON) {
			
			hideAjaxLoader();
			
			$("#regSearchResult .mCSB_container").empty();
			
			$("#nonregSearchResult .mCSB_container").empty();
			
			
			 if(resultJSON.result == "success")
			{
			  	var vendorArr = new Array( resultJSON.vendors.length );
			  	
			  	vendorArr = resultJSON.vendors;
			  	
			  	$("#searchResults").hide( );
			  	
			  	$(".searchHeading").show();
				
				$(".searchRegistertedResult").show();
				
				$(".searchNonRegistertedResult").show();
			  	
			  	if( vendorArr.length == 0 )
			  	{
			  		displayNoResult( "Both" );
			  		return;
			  	}
			  	
			  	var j=0, k=0,className="";
			  	
			  	for( var i=0; i<vendorArr.length; i++ )
				 {
				  		var vendor = vendorArr[i];
				  		
				  		if(vendor.regnKey==$("#regnkey").val())
				  		{
				  			continue;
				  		}

				  		if( vendor.isRegn == "true" )
				  		{
				  			j=j+1;
				  			
				  			if( j%2 == 0 )
				  				className = "evenrow";
				  			else 
				  				className = "oddrow";
				  			
				  			$("#regSearchResult .mCSB_container").append(SerachResultContainor(vendor,j, vendor.regnKey,className,vendor.noOfRatings,vendor.avgRatings,"delete"));
				  			
				  			$("#regSearchResult").mCustomScrollbar("update");
				  		}
				  		else
				  		{
				  			k=k+1;
				  			
				  			if( k%2 == 0 )
				  				className = "evenrow";
				  			else 
				  				className = "oddrow";
				  			
				  			$("#nonregSearchResult .mCSB_container").append(SerachResultContainor(vendor,k, vendor.regnKey,className,vendor.noOfRatings,vendor.avgRatings,"not_delete" ));
				  			
				  			$("#nonregSearchResult").mCustomScrollbar("update");
				  		}
						
				  }
			  	
			  	if( j == 0 ) displayNoResult( "Reg" );
			  	
			  	if( k == 0 ) displayNoResult( "NonReg" );
			  	
			  	 var countString = "";
				  
				  j==1? (countString = " Buyer found"):(countString = " Buyers found");
				  
				  $("#reg_comp_count").text( "( "+ j + countString +" )" );
				  
				  k==1? (countString = " Buyer found"):(countString = " Buyers found");
				  
				  $("#non_reg_comp_count").text( "( "+ k + countString + " )" );
			}

			else 
			{
				//alert("else part");
			}
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			
		}
	});
}


function advanceSearch()
{
	searchType = "AdvanceSearch";
	
	if(!$("#advance_search_form").valid())
		return;
	
	
	var category = $("#Advanced_Selectbox").val();
        
        var cntry = $("#cntry").val();
	
	var regnKey = $("#regnkey").val();
	
	var checkAll = '0';
	
	var regnBuyer = '0';
	
	if ($('#advancedCheck').is(':checked'))
	{
		checkAll = '1';
	} 
	else
	{
		checkAll = '0';
	}
	
	if ($('#regBuyerCheck').is(':checked'))
	{
		regnBuyer = '1';
	} 
	else
	{
		regnBuyer = '0';
	}
	
	
	var advanceSearchObj = new Object();
	
	advanceSearchObj.category = category;
	advanceSearchObj.supplierKey = regnKey;
	advanceSearchObj.checkAll  = checkAll;
	advanceSearchObj.searchRegd  = regnBuyer;
        advanceSearchObj.cntry=cntry;
//        alert(cntry);
	
	var searchItemArr = new Array();
	
	for(var i=1;i<=searchItemCount;i++ )
	{
		
		var searchItem = new Object();
		searchItem.partNo = $("#advanPartNumber"+i).val();
		searchItem.name =$("#advanName"+i).val();
		searchItemArr.push( searchItem );
	}
	
	advanceSearchObj.searchItemArr = searchItemArr;
	
	itemArr = searchItemArr;
	
	var advanceSearchData = JSON.stringify(advanceSearchObj);
	
	showAjaxLoader();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/SearchBuyerServlet",
		data : {
			'SearchType' : 'AdvanceSearch',
			'AdvanceSearchData' : advanceSearchData,
		},
		cache : false,
		success : function(resultJSON) {
			
//			$("#regSearchResult").empty();
//			
//			$("#nonregSearchResult").empty();
			
			hideAjaxLoader();
			
			$("#regSearchResult .mCSB_container").empty();
			
			$("#nonregSearchResult .mCSB_container").empty();
			
			 if(resultJSON.result == "success")
			{
			  	var vendorArr = new Array( resultJSON.vendors.length );
                                
//			  	alert(vendorArr);
                                
			  	vendorArr = resultJSON.vendors;
			  	
			  	$("#searchResults").hide( );
			  	$(".searchHeading").show();
				$(".searchRegistertedResult").show();
				$(".searchNonRegistertedResult").show();
			  	
			  	if( vendorArr.length == 0 )
			  	{
			  		displayNoResult( "Both" );
			  		return;
			  	}
			  	
			  	var j=0, k=0;
			  	
			  	for( var i=0; i<vendorArr.length; i++ )
				  {
//                                      alert(i);
				  		var vendor = vendorArr[i];
				  		if(vendor.regnKey==$("#regnkey").val())
				  		{
				  			continue;
				  		}

				  		if( vendor.isRegn == "true" )
				  		{
				  			j=j+1;
				  			
				  			if( j%2 == 0 )
				  				className = "evenrow";
				  			else 
				  				className = "oddrow";
				  			
				  			$("#regSearchResult .mCSB_container").append(SerachResultContainor(vendor,j, vendor.regnKey,className,vendor.noOfRatings,vendor.avgRatings,"delete" ));
				  			
				  			$("#regSearchResult").mCustomScrollbar("update");
				  		}
				  		else
				  		{
				  			k=k+1;
				  			
				  			if( k%2 == 0 )
				  				className = "evenrow";
				  			else 
				  				className = "oddrow";
				  			
				  			$("#nonregSearchResult .mCSB_container").append(SerachResultContainor(vendor,k, vendor.regnKey,className,vendor.noOfRatings,vendor.avgRatings,"not_delete" ));
				  			
				  			$("#nonregSearchResult").mCustomScrollbar("update");
				  		}
                                                
				  }
			  	
			  	if( j == 0 ) displayNoResult( "Reg" );
			  	
			  	if( k == 0 ) displayNoResult( "NonReg" );
			  	
			  	var countString = "";
				  
				  j==1? (countString = " Buyer found"):(countString = " Buyers found");
				  
				  $("#reg_comp_count").text( "( "+ j + countString +" )" );
				  
				  k==1? (countString = " Buyer found"):(countString = " Buyers found");
				  
				  $("#non_reg_comp_count").text( "( "+ k + countString + " )" );
			}

			else 
			{
				//alert("else part 1");
			}
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			//alert("else part 2");
		}
	});
}


//Fetch all business category for supply medium using ajax request (Using jQuery)
function fetchCategory() {
	
	$.ajax({
				type : "POST",
				url : getBaseURL() + "/EntityLoaderServlet", // servlet name
				data : {
					'entityname' : 'businesscategory',  // request partameters
				},
				dataType : 'json',
				cache : false,
				success : function(data) {
					
					var $basicSelect = $('#basic_search_cat');
					
					var $advSelect	 = $('#Advanced_Selectbox');
					
					// Remove the old drop down box datas
					$basicSelect.find('option').remove();
					
					$advSelect.find('option').remove();

					if( data.result == "success") // success result
					   {
							$.each(data, function(key, value) // get the key,value one by one 
							{
								if( value != "success")  // leave 'result':'success' pair
								{
									$('<option>').val(key).text(value).appendTo(
											$basicSelect);
									
									$('<option>').val(key).text(value).appendTo(
											$advSelect);
									
									$('#businesscategoryerr').text("");
								}
									
							});
							
							sortSelectBox('basic_search_cat');
							sortSelectBox('Advanced_Selectbox');
							
							
							
							$("<option value='All'>All</option>").insertBefore($('#basic_search_cat option:first-child'));
							
							$("<option value='All'>All</option>").insertBefore($('#Advanced_Selectbox option:first-child'));
							
							$("#basic_search_cat option:first-child").attr("selected", true);
							$("#Advanced_Selectbox option:first-child").attr("selected", true);
							
					   }
					   else  // failed result
					   {
						 
						   $basicSelect.find('option').remove();
						   
						   $('<option>').val("").text('--N/A--').appendTo(
								   $basicSelect); 
						   
						   $advSelect.find('option').remove();
						   
						   $('<option>').val("").text('--N/A--').appendTo(
								   $advSelect); 
						  
					   }
					
						$basicSelect.trigger('update');
						
						$advSelect.trigger('update');

				},
				error : function(xhr, textStatus, errorThrown)  // other errors
				{
					$('#businesscategoryerr').text("Request failed. Try again");
				}
			});
}


/* This will load the empty Quote form when a Registered buyer is selected
 * as a result of basic search and generate button is clicked
 * 
 * Then, the Company Name of the selected company will be filled in the Quote form
 * */
function loadBasic( )
{
		$("#rfq_tab").removeClass("main_tab_select");
		$("#rfq_tab").addClass("main_tab_unselect");
		
		$("#quote_tab").removeClass("main_tab_unselect");
		$("#quote_tab").addClass("main_tab_select");
		
		$("#po_tab").removeClass("main_tab_select");
		$("#po_tab").addClass("main_tab_unselect");
		
		$("#invoice_tab").removeClass("main_tab_select");
		$("#invoice_tab").addClass("main_tab_unselect");
	
		$("#quote_request_content").hide();
		$("#quote_form_content").show();
		
		$("#quote_request_tab").addClass("normal");
		$("#quote_request_tab").removeClass("highlight");
		
		$("#quote_form_tab").addClass("highlight");
		$("#quote_form_tab").removeClass("normal");
		
		$( "#to_company" ).val( selectedCompanyName ); 
		$( "#selected_ven_key" ).val( selectedRegnkey );
		getQuoteSupplierAddress( selectedRegnkey );
	
}

/* This will load the empty Quote form when a Registered supplier is selected
 * as a result of Advanced search and generate button is clicked,
 * 
 * 
 * 
 *  Then, the items entered in the Advanced search will be filled in the Quote form
 */
function loadAdvanced( )
{
	loadBasic( );
	for(var i=0;i<searchItemCount;i++ )
	{
		var	itemDet = "";
		var searchItem = itemArr[i];
		
		sNo=sNo+1;
		
		itemsCount = itemsCount+1;
		

 		
 		itemDet += '<div id="item'+itemsCount+'" class="item" style="width:900px;float:left;margin-left:40px;margin-right:20px;position:relative;">';
 		
 		itemDet += '<input type="text" class="textbox" id="sno'+itemsCount+'" style="width:30px;margin-right:20px;" disabled value="'+sNo+'";/>';
 		
 		itemDet += '<input type="text" class="textbox" id="item_desc'+itemsCount+'" style="width:170px;margin-right:20px;" disabled  value="'+searchItem.name+'";/>';
 		itemDet += '<input type="text" class="textbox" id="part_no'+itemsCount+'" style="width:90px;margin-right:20px;" disabled value="'+searchItem.partNo +'";/>';
 		
 		itemDet += '<input type="text" class="textbox" id="quantity'+itemsCount+'" style="width:60px;"  value="'+""+'";/>';
 		itemDet += '<div class="quantity_unit" id="quantity_unit'+itemsCount+'" >'+"KG"+'</div>';
 		itemDet += '<div class="quan_units" id="units_quantity_unit'+itemsCount+'" style="display:none;left:452px;"></div>';
 		
 		itemDet += '<input type="text" class="textbox" id="ship_date'+itemsCount+'" style="width:85px;margin-right:20px;" readonly/>';
 		
 	
 		itemDet += '<input type="text" class="textbox" id="price'+itemsCount+'" style="width:60px;" onblur="calculateTotal()"/>';
 		itemDet += '<div class="quantity_unit" id="currency'+itemsCount+'" >'+"USD"+'</div>';
 		itemDet += '<div class="currency_list" id="currency_currency'+itemsCount+'" style="display:none;left:689px;" ></div>';
 		
 		itemDet += '<input type="text" class="textbox" id="multiplier'+itemsCount+'" style="width:85px;margin-right:20px;" />';
 		
 		itemDet += '<input type="button" class="del_btn" id="del_btn_'+itemsCount+'" style="width:110px;margin-right:20px;" onclick="deleteItem('+itemsCount+');"/></div>';
 		
 		$("#items").append(itemDet);
 		
		

		$("#quantity_unit" + itemsCount).click(showQuantityUnits);
		 $( "#ship_date" + itemsCount  ).datepicker({ dateFormat: "dd-M-yy" });
	}
}

function setCompany( companyName, regnKey, isRegn )
{
	//alert( isRegn );
	if( $("#"+regnKey+"chk").attr( 'checked' ) )
	{
		selectedRegnkey = regnKey;	
		
		selectedCompanyName = companyName;
	}
	else
	{
		selectedRegnkey = "";
		
		selectedCompanyName = "";
	}

	if( isRegn == "true" )
		regFlag = "true";
	else
		regFlag = "false";
}

function displayNoResult( displayType )
{
	if( displayType == "Reg" )
	{		
		$("#regSearchResult").empty( );
				
		var divRow = '<div class="searchRow" style="text-align:center; width:500px;font-size:16px;font-color:#464646;">No registered buyers found for this search criteria</div>';
			
		$("#regSearchResult").append( divRow );
	}
	else if( displayType == "NonReg" )
	{
		$("#nonregSearchResult").empty( );
		
		var divRow = '<div class="searchRow" style="text-align:center; width:500px;font-size:16px;font-color:#464646;">No registered buyers found for this search criteria</div>';
			
		$("#nonregSearchResult").append( divRow );
	}
	else if( displayType == "Both" )
	{
		$("#searchResults").show( );
		
		$(".searchHeading").hide();
		
		$(".searchRegistertedResult").hide();
		
		$(".searchNonRegistertedResult").hide();
	}
}



