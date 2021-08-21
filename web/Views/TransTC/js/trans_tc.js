var selectedTab = "";
var selectedTitle = "";

var rfqText = "";
var quoteText = "";
var poText = "";
var invoiceText = "";

function tcOnload()
{
	selectedTab = "RFQ";
	
	selectedTitle = "RFQ Terms & Conditions";
	
	hideAjaxLoader();
		
	$('#file_upload').bind('change', function() 
	{
		validateFile( this, "#text" );

	});
	
	$( "#rfq_tab" ).click( rfqTabClicked );
	$( "#quote_tab" ).click( quoteTabClicked );
	$( "#po_tab" ).click( poTabClicked );
	$( "#invoice_tab" ).click( invoiceTabClicked );
	
	$( "#edit_container" ).click( editBtnClicked );
	$("#content_save_btn").click(getTcText);
	$("#upload_txt_cont").click(function()
	{
		$("#content_txtarea").val("");
		$("#tc_content_popup").show();
		$("#content_txtarea").focus();
		
	});
	

	getAllTC();
	
	
	var allowed =/^[a-zA-Z0-9!@#$%^&*()_+\-=\[\]{};': \b\r"\\|,.<>\/?]*$/;
	
    var input = document.getElementById("content_txtarea");

    input.onkeypress = function ()
    {
        // Cross-browser
        var evt = arguments[0] || event;
        var char = String.fromCharCode(evt.which || evt.keyCode);
       
        // Is the key allowed?
        if (!allowed.test(char)) 
        {
        	
            // Cancel the original event
            evt.cancelBubble = true;
            return false;
        }
    };
	
			
}

function performClick(node) 
{
	   var evt = document.createEvent("MouseEvents");
	   evt.initEvent("click", true, false);
	   node.dispatchEvent(evt);
}


function getAllTC()
{
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/TransTcUploadServlet",
		   data:{ 
			   'RequestType' : 'FetchTransTc', 
			   'CompanyKey':regnKey,
		    } ,
		   cache:false,
		   success: function( resultJson )
		   {
			   hideAjaxLoader();
			  
			  if(resultJson.result == "success")
			  {
				  var transArr=new Array(resultJson.TransTc.length);

				  transArr=resultJson.TransTc;
				  
				  
				  
				  for(var i=0;i<transArr.length;i++)
				  {
					  var trans=transArr[i];
					  
					  if(trans.transType=="RFQ")
					  {
						 rfqText=trans.tc;
					  }
					  else if (trans.transType=="Quote" )
					  {
						  quoteText=trans.tc;
					  }
					  else if(trans.transType=="PO")
					  {
						  poText=trans.tc;
					  }
					  else if(trans.transType=="Invoice")
					  {
						  invoiceText=trans.tc;
					  }
				  }
				  if(rfqText=="")
					{
						 $("#no_tc").show();
						 $("#tc_content").hide();
					 }
					 else
					 {
						 $("#tc_content").show();
						 $("#no_tc").hide();
						 $("#transet_label_content").val(rfqText);
					 }
				 
				 
		
					  
			 }
	
	
		   	}
					});
}

function getTcText()
{
	var text			=$("#content_txtarea").val( );
	saveUpdate(text);
	
}

function saveUpdate(text)
{
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val( );
	
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/TransTcUploadServlet",
		   data:{ 
			   'RequestType' : 'UpdateTransTc', 
			   'TransType' : selectedTab,
			   'Tc':text,
			   'CompanyKey':regnKey,
		    } ,
		   cache:false,
		   success: function( resultJson )
		   {
			   hideAjaxLoader();
			   
			   if(resultJson.result == "success")
				  {
					 
					  $("#tc_content_popup").hide(); 
					  if(text=="")
						{
							 $("#no_tc").show();
							 $("#tc_content").hide();
						 }
						 else
						 {
							 $("#tc_content").show();
							 $("#no_tc").hide();
							 $("#transet_label_content").val(rfqText);
						 }
					  if(selectedTab=="RFQ")
					  {
						  rfqText=text;
						  $("#transet_label_content").val(rfqText);
					  }
					  else if(selectedTab=="Quote")
					  {
						  quoteText=text;
						  $("#transet_label_content").val(quoteText);
					  }
					  else if(selectedTab=="PO")
					  {
						  poText=text;
						  $("#transet_label_content").val(poText);
					  }
					  else if(selectedTab=="Invoice")
					  {
						  invoiceText=text;
						  $("#transet_label_content").val(invoiceText);
					  }
				  }
				  else 
				  {
						ShowToast( resultJson.message );
				  }
			},
			error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader( );
			  
			}
	});
	
}

// This method is called while user click the RFQ tab
function rfqTabClicked()
{
	$( "#rfq_tab" ).removeClass( 'tab_gray' );
	$( "#rfq_tab" ).addClass( 'tab_orange' );
	
	
	$( "#quote_tab" ).removeClass( 'tab_orange' );
	$( "#quote_tab" ).addClass( 'tab_gray' );
	
	$( "#po_tab" ).removeClass( 'tab_orange' );
	$( "#po_tab" ).addClass( 'tab_gray' );
	
	$( "#invoice_tab" ).removeClass( 'tab_orange' );
	$( "#invoice_tab" ).addClass( 'tab_gray' );
	
	selectedTab = "RFQ";
	
	 $("#transet_label_content").val(rfqText);
	$("#transet_titile").text("RFQ Terms and Conditions");
	 
	 if(rfqText=="")
	{
		 $("#no_tc").show();
		 $("#tc_content").hide();
	 }
	 else
	 {
		 $("#tc_content").show();
		 $("#no_tc").hide();
		 
	 }
	
}

//This method is called while user click the Quote tab
function quoteTabClicked()
{
	$( "#rfq_tab" ).removeClass( 'tab_orange' );
	$( "#rfq_tab" ).addClass( 'tab_gray' );
	
	$( "#quote_tab" ).removeClass( 'tab_gray' );
	$( "#quote_tab" ).addClass( 'tab_orange' );
	
	$( "#po_tab" ).removeClass( 'tab_orange' );
	$( "#po_tab" ).addClass( 'tab_gray' );
	
	$( "#invoice_tab" ).removeClass( 'tab_orange' );
	$( "#invoice_tab" ).addClass( 'tab_gray' );
	
	selectedTab = "Quote";
	
	 $("#transet_label_content").val(quoteText);
	 $("#transet_titile").text("Quote Terms and Conditions");
	 
	 if(quoteText=="")
	 {
		 $("#no_tc").show();
		 $("#tc_content").hide();
	 }
	 else
	 {
		 $("#tc_content").show(); 
		 $("#no_tc").hide();
	 }
		
}

//This method is called while user click the PO tab
function poTabClicked()
{
	$( "#rfq_tab" ).removeClass( 'tab_orange' );
	$( "#rfq_tab" ).addClass( 'tab_gray' );
	
	$( "#quote_tab" ).removeClass( 'tab_orange' );
	$( "#quote_tab" ).addClass( 'tab_gray' );
	
	$( "#po_tab" ).removeClass( 'tab_gray' );
	$( "#po_tab" ).addClass( 'tab_orange' );
	
	$( "#invoice_tab" ).removeClass( 'tab_orange' );
	$( "#invoice_tab" ).addClass( 'tab_gray' );
	
	selectedTab = "PO";
	
	 $("#transet_label_content").val(poText);
	 $("#transet_titile").text("PO Terms and Conditions");
		
	if(poText=="")
	{
		$("#no_tc").show();
		 $("#tc_content").hide();
	}
	else
	{
		$("#tc_content").show(); 
		 $("#no_tc").hide();
	}
}

//This method is called while user click the Quote tab
function invoiceTabClicked()
{
	$( "#rfq_tab" ).removeClass( 'tab_orange' );
	$( "#rfq_tab" ).addClass( 'tab_gray' );
	
	$( "#quote_tab" ).removeClass( 'tab_orange' );
	$( "#quote_tab" ).addClass( 'tab_gray' );
	
	$( "#po_tab" ).removeClass( 'tab_orange' );
	$( "#po_tab" ).addClass( 'tab_gray' );
	
	$( "#invoice_tab" ).removeClass( 'tab_gray' );
	$( "#invoice_tab" ).addClass( 'tab_orange' );
	
	selectedTab = "Invoice";
	
	 $("#transet_label_content").val(invoiceText);
	 $("#transet_titile").text("Invoice Terms and Conditions");
	 
	 if(invoiceText=="")
		{
			$("#no_tc").show();
			 $("#tc_content").hide();
		}
		else
		{
			$("#tc_content").show(); 
			 $("#no_tc").hide();
		}
}

// This method is called when user click the edit button


function editBtnClicked()
{
	$("#tc_content_popup").show();

	
	if( selectedTab == 'RFQ' )
	{
		$("#content_txtarea").val( rfqText );
	}
	else if( selectedTab == 'Quote' )
	{
		$("#content_txtarea").val( quoteText );
	}
	else if( selectedTab == 'PO' )
	{
		$("#content_txtarea").val( poText );
	}
	else if( selectedTab == 'Invoice' )
	{
		$("#content_txtarea").val( invoiceText );
	}
	
	$("#content_txtarea").focus();
	tmpStr = $("#content_txtarea").val();
	$("#content_txtarea").val('');
	$("#content_txtarea").val(tmpStr);
}

function validateFile()
{

	var form			= $("#tc_form");
	var data			= new FormData();
	
	var ext = $('#file_upload').val().split('.').pop().toLowerCase();
	
	if($.inArray(ext, ['txt']) == -1)
	{
		 ShowToast( "Select text document" );
		
		return;
		
	}
	

	showAjaxLoader();
	

	data.append('file',document.tc_form.file_upload.files[0]);
	$.ajax
	({
		type : "POST",
		url : form.attr('action'),
		data : data,
		contentType : false,
		processData : false,
		success : function(data) 
		{
			hideAjaxLoader();

			if (data.result == "success")
			{
				$("#no_tc").hide();
				
				$("#tc_content").show();
				
				$("#transet_label_content").val( data.message );
				
				saveUpdate(data.message);
			}
			else
			{
				alert("error");
			}
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			
			hideAjaxLoader();
		
		}
	});
}




