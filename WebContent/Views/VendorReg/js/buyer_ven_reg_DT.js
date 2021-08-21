var supplierReqTable;
var myReqTable;
var businessClassificationArr;

/* It is used to initialize the My Request datatable */
function initsupplierReqDataTable()
{
	supplierReqTable=$('#supplier_req_list').dataTable( {
		 
		
							sDom: 'lfr<"fixed_height"t>ip',
							
							"sPaginationType": "full_numbers",
							"bAutoWidth": false,
							 "aoColumnDefs": [
							                 
								       	      { "sWidth": "150px", "aTargets": [ 0 ] },  // Vendor Company Name
								       	      { "sWidth": "150px", "aTargets": [ 1 ] },  // Vendor Phone Number
								       	      { "sWidth": "150px", "aTargets": [ 2 ] },  // Vendor Country
								       	      { "sWidth": "150px", "aTargets": [ 3 ] },	 // Vendor Email
								       	      { "sWidth": "150px", "aTargets": [ 4 ] },	 // Vendor Contact Name
								       	      { "sWidth": "150px", "aTargets": [ 5 ] },  // VR Status
								       	      { "sWidth": "150px", "aTargets": [ 6 ] },  // Created date
								       	    ],
								       	    
								       	 "aoColumnDefs":[
								       	                 
								       	                 { "bVisible":false,"aTargets":[7] }, // Vendor Branch
								       	                 { "bVisible":false,"aTargets":[8] }, // Vendor Address
								       	                 { "bVisible":false,"aTargets":[9] },	// Vendor City
								       	                 { "bVisible":false,"aTargets":[10] },	// Vendor State
								       	                 { "bVisible":false,"aTargets":[11] },	// Vendor Zipcode
								       	                 { "bVisible":false,"aTargets":[12] },	// Vendor Type
								       	                 { "bVisible":false,"aTargets":[13] },	// Vendor Department
								       	                 { "bVisible":false,"aTargets":[14] },	// Vendor Cell
								       	                 { "bVisible":false,"aTargets":[15] },	// Vendor Fax
								       	                 { "bVisible":false,"aTargets":[16] },	// Vendor Taxid
								       	                 { "bVisible":false,"aTargets":[17] },	// Vendor Type of Business
								       	                 { "bVisible":false,"aTargets":[18] },	// Vendor Business Category
								       	                 { "bVisible":false,"aTargets":[19] },	// Vendor NAICS Code
								       	                 { "bVisible":false,"aTargets":[20] },	// Vendor W9Form Flag
								       	                 { "bVisible":false,"aTargets":[21] },	// Vendor W9Form Path
								       	                 { "bVisible":false,"aTargets":[22] },	// Vendor Business size
								       	                 { "bVisible":false,"aTargets":[23] },	// Vendor Business Calssification
								       	                 { "bVisible":false,"aTargets":[24] },	// Vendor Additional Details
								       	                 { "bVisible":false,"aTargets":[25] },	// Vendor Regn id
								       	                 
								       	                 { "bVisible":false,"aTargets":[26] },	// regn key - request sender
								       	                
								       	                 { "bVisible":false,"aTargets":[27] }, 	// Company Name
								       	                 { "bVisible":false,"aTargets":[28] },   // Phone Number
								       	                 { "bVisible":false,"aTargets":[29] },	// Country
								       	                 { "bVisible":false,"aTargets":[30] },	// Email
								       	                 { "bVisible":false,"aTargets":[31] },	// Contact Name			       	                 
								       	                 
								       	                 { "bVisible":false,"aTargets":[32] }, // Branch
								       	                 { "bVisible":false,"aTargets":[33] }, // Address
								       	                 { "bVisible":false,"aTargets":[34] },	// City
								       	                 { "bVisible":false,"aTargets":[35] },	// State
								       	                 { "bVisible":false,"aTargets":[36] },	// Zipcode
								       	                 { "bVisible":false,"aTargets":[37] },	// Type
								       	                 { "bVisible":false,"aTargets":[38] },	// Department
								       	                 { "bVisible":false,"aTargets":[39] },	// Cell
								       	                 { "bVisible":false,"aTargets":[40] },	// Fax
								       	                 { "bVisible":false,"aTargets":[41] },	// Type of Business
								       	                 { "bVisible":false,"aTargets":[42] },	// Business Category
								       	                 
								       	                 { "bVisible":false,"aTargets":[43] },	// request sender type
								       	                 { "bVisible":false,"aTargets":[44] },	// inquiry array
								       	                 { "bVisible":false,"aTargets":[45] }, // VendorRegn Key - request receiver
								       	                 
								       	                // { "bVisible":false,"aTargets":[26] },
								       	                //{ "bVisible":false,"aTargets":[27] },
								       	                // { "bVisible":false,"aTargets":[28] },
								       	                 
								       	                 ],
			
			
			       	 "bLengthChange":false,
			       	"oLanguage": { "sSearch": "Search","sEmptyTable":"No Request found"},
			       	"bSort": true,
			       	"aaSorting": [[0,'asc']],
			
			"bPaginate": true,
			
			"bFilter": true,
			
			"bInfo": true,
			
			"iDisplayLength": 15,
			
			"fnRowCallback":function(nRow,aData,iDisplayIndex,iDisplayIndexFull){
				
				
				$("td",nRow).addClass('rowBorder');
				
				$(nRow).click(function()
				{
					var aTrs = supplierReqTable.fnGetNodes();
					
					for ( var i=0 ; i<aTrs.length ; i++ )
					{				
						if ( $(aTrs[i]).hasClass('row_selected') )
						{
							$(aTrs[i]).removeClass('row_selected');
						}
						
					}
					
					$(nRow).addClass('row_selected');
					
					var aPos = supplierReqTable.fnGetPosition( this );
					
					var aData = supplierReqTable.fnGetData( aPos );
					
					showBuyersForm( aData );
					
				});		
				
				return nRow;
			}
		} );
}

/* It is used to initialize the Buyer Request datatable */
function initBuyerMyReqDataTable()
{

	//alert( "Calling" );
	myReqTable = $('#my_req_list').dataTable(
			{

				sDom : 'lfr<"fixed_height"t>ip',

				"sPaginationType" : "full_numbers",
				"bAutoWidth" : false,
//				"aoColumnDefs" : [ {
//					"sWidth" : "180px",
//					"aTargets" : [ 0 ]
//				}, {
//					"sWidth" : "180px",
//					"aTargets" : [ 1 ]
//				}, {
//					"sWidth" : "180px",
//					"aTargets" : [ 2 ]
//				}, {
//					"sWidth" : "180px",
//					"aTargets" : [ 3 ]
//				}, {
//					"sWidth" : "180px",
//					"aTargets" : [ 4 ]
//				}, {
//					"sWidth" : "180px",
//					"aTargets" : [ 5 ]
//				} ],

				
				 "aoColumnDefs": [
				                
					       	      { "sWidth": "150px", "aTargets": [ 0 ] },  // Vendor Company Name
					       	      { "sWidth": "150px", "aTargets": [ 1 ] },  // Vendor Phone Number
					       	      { "sWidth": "150px", "aTargets": [ 2 ] },  // Vendor Country
					       	      { "sWidth": "150px", "aTargets": [ 3 ] },	 // Vendor Email
					       	      { "sWidth": "150px", "aTargets": [ 4 ] },	 // Vendor Contact Name
					       	      { "sWidth": "150px", "aTargets": [ 5 ] },   // VR Status
					       	      { "sWidth": "150px", "aTargets": [ 6 ] },  // Created date
					       	    ],
					       	    
					       	 "aoColumnDefs":[
					       	                
					       	                 { "bVisible":false,"aTargets":[7] }, // Vendor Branch
					       	                 { "bVisible":false,"aTargets":[8] }, // Vendor Address
					       	                 { "bVisible":false,"aTargets":[9] },	// Vendor City
					       	                 { "bVisible":false,"aTargets":[10] },	// Vendor State
					       	                 { "bVisible":false,"aTargets":[11] },	// Vendor Zipcode
					       	                 { "bVisible":false,"aTargets":[12] },	// Vendor Type
					       	                 { "bVisible":false,"aTargets":[13] },	// Vendor Department
					       	                 { "bVisible":false,"aTargets":[14] },	// Vendor Cell
					       	                 { "bVisible":false,"aTargets":[15] },	// Vendor Fax
					       	                 { "bVisible":false,"aTargets":[16] },	// Vendor Taxid
					       	                 { "bVisible":false,"aTargets":[17] },	// Vendor Type of Business
					       	                 { "bVisible":false,"aTargets":[18] },	// Vendor Business Category
					       	                 { "bVisible":false,"aTargets":[19] },	// Vendor NAICS Code
					       	                 { "bVisible":false,"aTargets":[20] },	// Vendor W9Form Flag
					       	                 { "bVisible":false,"aTargets":[21] },	// Vendor W9Form Path
					       	                 { "bVisible":false,"aTargets":[22] },	// Vendor Business size
					       	                 { "bVisible":false,"aTargets":[23] },	// Vendor Business Calssification
					       	                 { "bVisible":false,"aTargets":[24] },	// Vendor Additional Details
					       	                 { "bVisible":false,"aTargets":[25] },	// Vendor Regn id
					       	                 
					       	                 { "bVisible":false,"aTargets":[26] },	// regn key - request sender
					       	                
					       	                 { "bVisible":false,"aTargets":[27] }, 	// Company Name
					       	                 { "bVisible":false,"aTargets":[28] },   // Phone Number
					       	                 { "bVisible":false,"aTargets":[29] },	// Country
					       	                 { "bVisible":false,"aTargets":[30] },	// Email
					       	                 { "bVisible":false,"aTargets":[31] },	// Contact Name			       	                 
					       	                 
					       	                 { "bVisible":false,"aTargets":[32] }, // Branch
					       	                 { "bVisible":false,"aTargets":[33] }, // Address
					       	                 { "bVisible":false,"aTargets":[34] },	// City
					       	                 { "bVisible":false,"aTargets":[35] },	// State
					       	                 { "bVisible":false,"aTargets":[36] },	// Zipcode
					       	                 { "bVisible":false,"aTargets":[37] },	// Type
					       	                 { "bVisible":false,"aTargets":[38] },	// Department
					       	                 { "bVisible":false,"aTargets":[39] },	// Cell
					       	                 { "bVisible":false,"aTargets":[40] },	// Fax
					       	                 { "bVisible":false,"aTargets":[41] },	// Type of Business
					       	                 { "bVisible":false,"aTargets":[42] },	// Business Category
					       	                 
					       	                 { "bVisible":false,"aTargets":[43] },	// request sender type
					       	                 
					       	                 { "bVisible":false,"aTargets":[44] },	// inquiry array
					       	                 
					       	                 { "bVisible":false,"aTargets":[45] }, // VendorRegn Key - request receiver
					       	                 
					       	                // { "bVisible":false,"aTargets":[26] },
					       	                //{ "bVisible":false,"aTargets":[27] },
					       	                // { "bVisible":false,"aTargets":[28] },
					       	                 
					       	                 ],
					
				
				"bLengthChange" : false,
				"oLanguage" : {
					"sSearch" : "Search",
					"sEmptyTable" : "No Request found"
				},
				"bSort" : true,
				"aaSorting" : [ [ 0, 'asc' ] ],

				"bPaginate" : true,

				"bFilter" : true,

				"bInfo" : true,

				"iDisplayLength" : 15,

				"fnRowCallback" : function(nRow, aData, iDisplayIndex,
						iDisplayIndexFull) {

					$("td", nRow).addClass('rowBorder');

					$(nRow).click(function() 
					{
						var aTrs = myReqTable.fnGetNodes();
						
						for ( var i=0 ; i<aTrs.length ; i++ )
						{				
							if ( $(aTrs[i]).hasClass('row_selected') )
							{
								$(aTrs[i]).removeClass('row_selected');
							}
							
						}
						
						$(nRow).addClass('row_selected');
						
						var aPos = myReqTable.fnGetPosition( this );
						
						var aData = myReqTable.fnGetData( aPos );
						
						showBuyersForm( aData ); //This should be uneditable
					});

					return nRow;
				}
			});
}


function showBuyersForm( aData )
{
	//For both popups, the supplier details only will be shown as per the 
	//requirements.
	
	//If buyer tab is clicked, he will be supplier. Now the pop up will be filled
	// with vendor details since here vendor detail will have supplier details
	
	var companyname = aData[0];
	
	var phoneno = aData[1];
	
	var country = aData[2];
	
	var emailid = aData[3];
	
	var businesscontact = aData[4];
	
	var regnstatus = aData[5];
	
	var venRegnKey = aData[45];
	
	var branch = aData[7]=='null'?"":aData[7];
	
    var address = aData[8];
         
    var city = aData[9];
    
    var state = aData[10];
    
    var zipcode = aData[11];
	
	var companytype = aData[12]; 
	
	var department = aData[13];	
	
	var cellno = aData[14];
	
	var faxno = aData[15];
	
	var businesstaxid = aData[16]=='null'?"":aData[16];
	
	var businessType = aData[17];
	
	var businesscategory = aData[18];
	
	var naicscode = aData[19];
	
	var w9taxformflag = aData[20];
	
	var w9taxformpath = aData[21];
	
	var businesssize = aData[22];
	
	var businessclassification = aData[23];
	
	var additionaldetails = aData[24]=='null'?"":aData[24];

	var venReqId = aData[25];
	
	var regnKey = aData[26];
	
	var inquiryarr = aData[44];
	
	//var inquiryObjarr = new Array(inquiryarr.length);
	
	//alert( inquiryarr.length);
	
	if( inquiryarr.length > 0 )
	{
		$('#inquiry_details').css("display", "block");
		
		var inquiryDiv = "";
		
		inquiryDiv = inquiryDiv + '<div class="side_heading"> Inquiry Details</div>';
		
		var companyName = "";
		
		for( var i=0; i<inquiryarr.length; i++ )
		{
			if( i % 2 == 0) { companyName = aData[27]; } else { companyName = aData[0]; }
			
			inquiryDiv = inquiryDiv + '<div class="inquire_row">';
			inquiryDiv = inquiryDiv + '<label class="inquire_by">'+companyName+'</label>';
			inquiryDiv = inquiryDiv + '<div class="inquire_det">'+inquiryarr[i].inquiredetails+'</div>';
		}
		
		//alert(inquiryDiv);
		
		$('#inquiry_details').empty();
		
		$('#inquiry_details').append(inquiryDiv);
	}
	
	
	//var companylevel = aData[25];
	
	$('#company_name_popup').val(companyname);
	$('#branch_0_popup').val(branch);
	$('#countryregion_0_popup').val(country);
	
	$('#address_popup').val(address);
	$('#city_popup').val(city);
	$('#state_0_popup').val(state);
	
	$('#zipcode_popup').val(zipcode);
	
	if( companytype == 0 )
	{
		$('#internetuser_popup').attr('checked', false);
	}
	else
	{
		//$('#transuser').attr('checked', true);
		$('#internetuser_popup').attr('checked', true);
	}
	
	$('#contact_name_popup').val(businesscontact);
	
	$('#regn_status').val(regnstatus);
	
	$("#vendor_regn_id").val(venRegnKey);
	
	$("#regn_id").val(regnKey);
	
	$('#titledept_popup').val(department);
	
	$('#email_popup').val(emailid);
	
	$('#phone_popup').val(phoneno);
	
	$('#cell_popup').val(cellno);
	
	$('#fax_popup').val(faxno);
	
	$('#taxid_popup').val(businesstaxid);
	
	$('#typeofbusiness_popup').val(businessType);
	
	$('#businesscategory_popup').val(businesscategory);
	
	$('#naicscode_popup').val(naicscode);
	
	if( w9taxformflag == 0 )
	{
		$('#w9form_flag_popup').attr('checked', false);
	}
	else
	{
		$('#w9form_flag_popup').attr('checked', true);
	}
	if( regnstatus == "New Request" )
	{
		
		$("#w9form_upload_ctrl").show();
	
	}
	else
	{
		$("#w9form_upload_ctrl").hide();
	}
	
	
	if(  w9taxformpath.length == 0 || w9taxformpath == 'null' )
	{
		
		$("#w9form_download_ctrl").hide();
	}
	else
	{
		
		$("#w9form_download_ctrl").show();
		
		$("#w9FormPath").val(w9taxformpath);
		
	}
	
	if( businesssize == "Large" )
	{
		$('#buss_small_popup').attr('checked', false);
		$('#buss_large_popup').attr('checked', true);
	}
	else
	{
		$('#buss_small_popup').attr('checked', true);
		$('#buss_large_popup').attr('checked', false);
	}
	
	$('#additional_det_popup').val(additionaldetails);
	
	//$('#w9_tax').val(w9taxformpath);
	
	$('#vendor_regnrec_id').val(venReqId);
	
	parseBusinessClassfication( businessclassification );
	
	//alert( "To display popup" );
	
	$("#buyer_form_popup").show();
	
	customizeBuyerPopUp();
	
	$("#buyer_form_popup_content").mCustomScrollbar("update");
}

function getBusinessClassfication()
{
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/BusinessClassificationServlet",
		   cache:false,
		   success: function( resJSON )
		   {
			   if(resJSON.result == "success")
				{
				   businessClassificationArr = new Array( Object.keys(resJSON).length );
					
				   businessClassificationArr = Object.keys(resJSON);
				   
					//alert( businessClassificationArr.length );
				}

				else 
				{
					// ShowToast( resJSON.message,2000 );
					//alert( "Failed" );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
		    	//alert("Exception");
			}
		 });
}

function parseBusinessClassfication( businessclassification )
{
	var busiClassArr = businessclassification.split(":");
	
	for( var i=0; i < busiClassArr.length; i++ )
	{
	   if(isObjExistInJSON( busiClassArr[i], businessClassificationArr ) == 0 )
	   {
		   $('#'+busiClassArr[i]+'_popup').attr('checked', true);
	   }
	   else
	   {
		   $('#'+busiClassArr[i]+'_popup').attr('checked', false);
	   }
   }
}

function isObjExistInJSON( obj, resJSON )
{
	for ( var i=0; i<resJSON.length; i++ )
	{
		var resJSONVal = resJSON[i];
		if( obj == resJSONVal )
		{
			return 0;
		}
	}
	return -1;
}

function acceptBtnClicked()
{
	var regnKey = $('#regn_id').val();
	
	var venRegnKey = $("#vendor_regn_id").val();

	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/VendorRegnServlet",
		   data:{ 
		        'RequestType': 'UpdateStatus', 
		        'Action':'accept',
		        'VendorKey':venRegnKey,
		        'RegnKey':regnKey,
		    } ,
		   cache:false,
		   success: function( resJSON )
		   {
			   if(resJSON.result == "success")
				{
				   $("#buyer_form_popup").hide();
				   refreshDT( );
				   ShowToast( resJSON.message,2000 );
				}

				else 
				{
					$("#buyer_form_popup").hide();
					 ShowToast( resJSON.message,2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
		    	alert("Exception");
			}
		 });
}

function rejectBtnClicked()
{
	var regnKey = $('#regn_id').val();
	
	var venRegnKey = $("#vendor_regn_id").val();

	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/VendorRegnServlet",
		   data:{ 
		        'RequestType': 'UpdateStatus', 
		        'Action':'reject',
		        'VendorKey':venRegnKey,
		        'RegnKey':regnKey,
		    } ,
		   cache:false,
		   success: function( resJSON )
		   {
			   if(resJSON.result == "success")
				{
				   $("#buyer_form_popup").hide();
				   refreshDT( );
				   ShowToast( resJSON.message,2000 );
				}

				else 
				{
					$("#buyer_form_popup").hide();
					 ShowToast( resJSON.message,2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
		    	alert("Exception");
			}
		 });
}

function inquireBtnClicked()
{	
	$('#accept_btn').hide();
	$('#reject_btn').hide();
	$('#inquire_btn').hide();
	
	$('#add_inquiry_content').css("display", "block");
}

function cancelBtnClicked()
{
	$('#accept_btn').show();
	$('#reject_btn').show();
	$('#inquire_btn').show();
	
	$('#add_inquiry_content').css("display", "none");
}

function saveBtnClicked()
{
	changeToInquire();
}

function changeToInquire()
{
	var regnKey = $('#regn_id').val();
	
	var venRegnKey = $("#vendor_regn_id").val();
	
	var venRegnId = $('#vendor_regnrec_id').val();
	
	//alert(venRegnId);
//	alert( venRegnKey );
	//alert(regnKey);
	//return;
	var inquiryDetails = $("#new_inquire_message").val();

	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/VendorRegnServlet",
		   data:{ 
		        'RequestType': 'AddInquiry', 
		        'Action':'inquire',
		        'VendorKey':venRegnKey,
		        'RegnKey':regnKey,
		        'VendorRegnKey':venRegnKey,
		        'InquireDetails':inquiryDetails,
		        'VendorRegnId': venRegnId,
		    } ,
		   cache:false,
		   success: function( resJSON )
		   {
			   if(resJSON.result == "success")
				{
				   $('#add_inquiry_content').css("display", "none");
				   $("#buyer_form_popup").hide();
				   refreshDT( );
				   ShowToast( resJSON.message,2000 );
				}

				else 
				{
					 ShowToast( resJSON.message,2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
		    	alert("Exception");
			}
		 });
}

function downloadW9form()
{
	$('#w9form_download_frm').submit();
}

function refreshDT( )
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
}