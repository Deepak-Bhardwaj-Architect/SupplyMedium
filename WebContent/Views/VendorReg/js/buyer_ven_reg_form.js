/*
	This method is used to get the all pending vendor registration request recieved 
	by the buyer.
*/

function getBuyerMyPendingRegReq()
{
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/VendorRegnServlet",
		   data:{ 
		        'RequestType': 'ListMyRequests', 
		        'RegnKey':regnKey,
		        'RequestSenderType': 'Buyer',
		    } ,
		   cache:false,
		   success: function( pendingMyVenRegReqJSON )
		   {
			   if(pendingMyVenRegReqJSON.result == "success")
				{
					
				   var myPendingVenReqArr = new Array(pendingMyVenRegReqJSON.vendors.length);

				   myPendingVenReqArr = pendingMyVenRegReqJSON.vendors;
				   
				   parseMyPendingVenReq( myPendingVenReqArr );
				}

				else 
				{
					ShowToast( pendingMyVenRegReqJSON.message,2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
		    	
			}
		 });
}

/*
This method is used to get the all pending vendor registration request sent
by the buyer
*/

function getBuyerPendingRegReq()
{
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/VendorRegnServlet",
		   data:{ 
		        'RequestType': 'ListOtherVendorsRequest', 
		        'RegnKey':regnKey,
		        'RequestSenderType': 'Supplier',
		    } ,
		   cache:false,
		   success: function( pendingVenRegReqJSON )
		   {
			   if(pendingVenRegReqJSON.result == "success")
				{
					
				   var pendingReqArr = new Array(pendingVenRegReqJSON.vendors.length);
	
				   pendingReqArr = pendingVenRegReqJSON.vendors;
				   
				   parsePendingVenReq( pendingReqArr );
				}
	
				else 
				{
					 ShowToast( pendingVenRegReqJSON.message,2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
		    	
			}
		 });
}


function parseMyPendingVenReq( pendingReqArr )
{
	myReqTable.fnClearTable();
	
	for ( var i = 0; i < pendingReqArr.length; i++) 
	{
		
		var pendingReq = pendingReqArr[i];
		
		var companyname = pendingReq.companyname;
		
		var vendorcompanyname = pendingReq.vendorcompanyname;
		
		
		var phoneno = pendingReq.phoneno;
		
		var vendorphoneno = pendingReq.vendorphoneno;
		
		
		var emailid = pendingReq.emailid;
		
		var vendoremailid = pendingReq.vendoremailid;
		
		
		var businesscontact = pendingReq.businesscontact;
		
		var vendorbusinesscontact = pendingReq.vendorbusinesscontact;
		
		
		var regnstatus = pendingReq.regnstatus;
		
		
		var venRegnKey = pendingReq.vendorregnkey;
		
		
		var companytype = pendingReq.companytype;
		
		var vendorcompanytype = pendingReq.vendorcompanytype;
		
		
		var businesstype = companytype;
		
		var vendorbusinesstype = vendorcompanytype;
		
		
		var department = pendingReq.department;
		
		var vendordepartment = pendingReq.vendordepartment;
		
		
		var cellno = pendingReq.cellno;
		
		var vendorcellno = pendingReq.vendorcellno;
		
		
		var faxno = pendingReq.faxno;
		
		var vendorfaxno = pendingReq.vendorfaxno;
		
		
		var businesstaxid = pendingReq.businesstaxid;
		
		
		var businesscategory = pendingReq.businesscategory;
		
		var vendorbusinesscategory = pendingReq.vendorbusinesscategory;
		
		
		var addresstype = pendingReq.addresstype;
		
		var vendoraddresstype = pendingReq.vendoraddresstype;

		
		var address = pendingReq.address;
		
		var vendoraddress = pendingReq.vendoraddress;
		
		
		var city = pendingReq.city;
		
		var vendorcity = pendingReq.vendorcity;
		
		
		var country = pendingReq.country;
		
		var vendorcountry = pendingReq.vendorcountry;
		
		
		var state = pendingReq.state;
		
		var vendorstate = pendingReq.vendorstate;
		
		
		var zipcode = pendingReq.zipcode;
		
		var vendorzipcode = pendingReq.vendorzipcode;
		
		
		var naicscode = pendingReq.naicscode;
		
		var w9taxformflag = pendingReq.w9taxformflag;
		
		var w9taxformpath = pendingReq.w9taxformpath;
		
		var businesssize = pendingReq.businesssize;
		
		var businessclassification = pendingReq.businessclassification;
		
		var additionaldetails = pendingReq.additionaldetails;

		var venReqId = pendingReq.vendorregnid;
	
		var regnKey = pendingReq.regnkey;
		
		var createdDate = pendingReq.createdtimestamp;
		
		
		//var companylevel = pendingReq.companylevel;
		
		var requestsendertype = pendingReq.requestsendertype;
		
		var inquiryarr	= pendingReq.vendorinquirearr;
		
		
		myReqTable.fnAddData( [
		                       	vendorcompanyname, 	//aData[0]
		                       	vendorphoneno,		//aData[1]
		                       	vendorcountry,		//aData[2]
		                       	vendoremailid,		//aData[3]
		                       	vendorbusinesscontact,	//aData[4]
		                       	regnstatus,			//aData[5]
		                       	
		                        createdDate,        //aData[6]
		                       
		                       	vendoraddresstype,	//aData[7]
		                       	vendoraddress,		//aData[8]
		                       	vendorcity,			//aData[9]
		                       	vendorstate,		//aData[10]
		                       	vendorzipcode,		//aData[11]
		                       	
		                        vendorcompanytype,	//aData[12]
		                        vendordepartment,	//aData[13]
		                        vendorcellno,		//aData[14]
		                        vendorfaxno,		//aData[15]
		                        
		                        businesstaxid,//aData[16]
		                        businesstype, //aData[17]
		                        businesscategory,	//aData[18]
		                        
		                        naicscode,			//aData[19]
		                        w9taxformflag,		//aData[20]
		                        w9taxformpath,		//aData[21]
		                        businesssize,		//aData[22]
		                        businessclassification,//aData[23]
		                        additionaldetails,	//aData[24]
		                        
		                        venReqId,			//aData[25]
		                        regnKey,			//aData[26]
		                        
		                    	companyname, 	//aData[27]
		                       	phoneno,		//aData[28]
		                       	country,		//aData[29]
		                       	emailid,		//aData[30]
		                       	businesscontact,	//aData[31]
		                       	
		                       	addresstype,	//aData[32]
		                       	address,		//aData[33]
		                       	city,			//aData[34]
		                       	state,		//aData[35]
		                       	zipcode,		//aData[36]
		                       	
		                        companytype,	//aData[37]
		                        department,	//aData[38]
		                        cellno,		//aData[39]
		                        faxno,		//aData[40]
		                        
		                        //businesstaxid,//aData[16]
		                        vendorbusinesstype, //aData[41]
		                        vendorbusinesscategory,	//aData[42]
		                        
		                        requestsendertype,	//aData[43]
		                        inquiryarr, //aData[44]
		                    	venRegnKey			//aData[45]
		                        
		                     ] );
	}
}

function parsePendingVenReq( pendingReqArr )
{
	supplierReqTable.fnClearTable();
	
	for ( var i = 0; i < pendingReqArr.length; i++) 
	{
		var pendingReq = pendingReqArr[i];
		
		var companyname = pendingReq.companyname;
		
		var vendorcompanyname = pendingReq.vendorcompanyname;
		
		
		var phoneno = pendingReq.phoneno;
		
		var vendorphoneno = pendingReq.vendorphoneno;
		
		
		var emailid = pendingReq.emailid;
		
		var vendoremailid = pendingReq.vendoremailid;
		
		
		var businesscontact = pendingReq.businesscontact;
		
		var vendorbusinesscontact = pendingReq.vendorbusinesscontact;
		
		
		var regnstatus = pendingReq.regnstatus;
		
		
		var venRegnKey = pendingReq.vendorregnkey;
		
		
		var companytype = pendingReq.companytype;
		
		var vendorcompanytype = pendingReq.vendorcompanytype;
		
		
		var businesstype = companytype;
		
		var vendorbusinesstype = vendorcompanytype;
		
		
		var department = pendingReq.department;
		
		var vendordepartment = pendingReq.vendordepartment;
		
		
		var cellno = pendingReq.cellno;
		
		var vendorcellno = pendingReq.vendorcellno;
		
		
		var faxno = pendingReq.faxno;
		
		var vendorfaxno = pendingReq.vendorfaxno;
		
		
		var businesstaxid = pendingReq.businesstaxid;
		
		
		var businesscategory = pendingReq.businesscategory;
		
		var vendorbusinesscategory = pendingReq.vendorbusinesscategory;
		
		
		var addresstype = pendingReq.addresstype;
		
		var vendoraddresstype = pendingReq.vendoraddresstype;

		
		var address = pendingReq.address;
		
		var vendoraddress = pendingReq.vendoraddress;
		
		
		var city = pendingReq.city;
		
		var vendorcity = pendingReq.vendorcity;
		
		
		var country = pendingReq.country;
		
		var vendorcountry = pendingReq.vendorcountry;
		
		
		var state = pendingReq.state;
		
		var vendorstate = pendingReq.vendorstate;
		
		
		var zipcode = pendingReq.zipcode;
		
		var vendorzipcode = pendingReq.vendorzipcode;
		
		
		var naicscode = pendingReq.naicscode;
		
		var w9taxformflag = pendingReq.w9taxformflag;
		
		var w9taxformpath = pendingReq.w9taxformpath;
		
		var businesssize = pendingReq.businesssize;
		
		var businessclassification = pendingReq.businessclassification;
		
		var additionaldetails = pendingReq.additionaldetails;

		var venReqId = pendingReq.vendorregnid;
	
		var regnKey = pendingReq.regnkey;
		
		 var createdDate = pendingReq.createdtimestamp;
		
		//var companylevel = pendingReq.companylevel;
		
		var requestsendertype = pendingReq.requestsendertype;
		
		var inquiryarr	= pendingReq.vendorinquirearr;
		
		supplierReqTable.fnAddData( [
		                       	vendorcompanyname, 	//aData[0]
		                       	vendorphoneno,		//aData[1]
		                       	vendorcountry,		//aData[2]
		                       	vendoremailid,		//aData[3]
		                       	vendorbusinesscontact,	//aData[4]
		                       	regnstatus,			//aData[5]
		                        createdDate,		 //aData[6]
		                       	
		                       	
		                       	vendoraddresstype,	//aData[7]
		                       	vendoraddress,		//aData[8]
		                       	vendorcity,			//aData[9]
		                       	vendorstate,		//aData[10]
		                       	vendorzipcode,		//aData[11]
		                       	
		                        vendorcompanytype,	//aData[12]
		                        vendordepartment,	//aData[13]
		                        vendorcellno,		//aData[14]
		                        vendorfaxno,		//aData[15]
		                        
		                        businesstaxid,//aData[16]
		                        businesstype, //aData[17]
		                        businesscategory,	//aData[18]
		                        
		                        naicscode,			//aData[19]
		                        w9taxformflag,		//aData[20]
		                        w9taxformpath,		//aData[21]
		                        businesssize,		//aData[22]
		                        businessclassification,//aData[23]
		                        additionaldetails,	//aData[24]
		                        
		                        venReqId,			//aData[25]
		                        regnKey,			//aData[26]
		                        
		                    	companyname, 	//aData[27]
		                       	phoneno,		//aData[28]
		                       	country,		//aData[29]
		                       	emailid,		//aData[30]
		                       	businesscontact,	//aData[31]
		                       	
		                       	addresstype,	//aData[32]
		                       	address,		//aData[33]
		                       	city,			//aData[34]
		                       	state,		//aData[35]
		                       	zipcode,		//aData[36]
		                       	
		                        companytype,	//aData[37]
		                        department,	//aData[38]
		                        cellno,		//aData[39]
		                        faxno,		//aData[40]
		                        
		                        //businesstaxid,//aData[16]
		                        vendorbusinesstype, //aData[41]
		                        vendorbusinesscategory,	//aData[42]
		                        
		                        requestsendertype,	//aData[43]
		                        
		                        inquiryarr,	//aData[44]
		                        venRegnKey	//aData[45]
		                       
		                        
		                     ] );
		
	}
}

/*
This method is used to get the non registered the buyer from 
typing search string.
*/

function getNRVendor()
{
	var regnKey = $('#regnkey').val();
	
	var searchStr = $("#to_company").val();
	
	if(searchStr.length==0)
	{
		$("#ven_search_result").hide();
		return;
	}
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/VendorRegnServlet",
		   data:{
		        'RequestType': 'ListNRVendors', 
		        'RegnKey':regnKey,
		        'SearchStr':searchStr,
		        'RequestSenderType':'Supplier'
		    } ,
		   cache:false,
		   success: function( vendorsJSON )
		   {
			   if( vendorsJSON.result == "success")
			   {
				   var vendorArr = new Array(vendorsJSON.NRvendors.length);

				   vendorArr = vendorsJSON.NRvendors;
				   
				   parseVendors( vendorArr );
			   }
			   else
			   {
				  
			   }
			   
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	//alert("failed");
			}
		 });
}


function parseVendors( vendorArr )
{
	
	$("#ven_search_result").empty();
	
	for ( var i = 0; i < vendorArr.length; i++) 
	{
		
		var vendor = vendorArr[i];

		var vendorKey = vendor.companykey;
		
		var vendorName = vendor.companyname;

		var venDiv = '<div id="ven_'+vendorKey+'" class="filter_comp">';
		
		venDiv += vendorName +'</div>';
		
		$("#ven_search_result").append( venDiv );
		
		$("#ven_search_result").show();
		
		
		$("#ven_" + vendorKey).on('click', function(event) {

			var divid = event.target.id;

			var idSplitArr = divid.split("_");

			if (idSplitArr.length > 1) 
			{
				var vendorKey = idSplitArr[1];

				$("#selected_ven_key").val(vendorKey);
				
				$("#ven_search_result").hide();
				
				$("#to_company").val( $("#"+divid).text() );

			}

		});
	}
	
	if( vendorArr.length == 0 )
	{
		var venDiv = '<div id="ven_empty" class="filter_comp">';
		
		venDiv += 'No result found</div>';
		
		$("#ven_search_result").append( venDiv );
		
		$("#ven_search_result").show();
	}
}

function addSupplier()
{
	
	var regnKey = $("#regnkey").val();
	
	var vendorKey = $("#selected_ven_key").val();
	
	if(vendorKey=="")
	{
		$("#vendor_reg_error").text("Select supplier to proceed.");
		
		return;
	}
	
	
	
	
	var W9TaxFormFlag = 0;
	
	if ($('#yes').is(':checked')) 
	{
		W9TaxFormFlag = 1;
	}
	else
	{
		W9TaxFormFlag = 0;
	}
	

	showAjaxLoader();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/VendorRegnServlet",
		   data:{ 
		        'RequestType': 'AddVendor', 
		        'RegnKey':regnKey,
		        'VendorKey':vendorKey,
		        'W9TaxFormFlag':W9TaxFormFlag,
		        'RequestSenderType' : 'Buyer',
		        'Action': 'newrequest',
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if (data.result == "success")
				{
					
					ShowToast( data.message,2000 );
					
					$("#selected_ven_key").val("");
					
					$("#vendor_reg_error").text("");
					
					$("#to_company").val("");
					
				}

				else 
				{
					 ShowToast( data.message,2000 );
					 
					 $("#vendor_reg_error").text("");
				}
			   
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
		    	
		    	//alert("failed");
			}
		 });
}
