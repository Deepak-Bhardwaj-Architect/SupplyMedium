/*
This method is used to get the registered vendor list using search string
*/
var newVendorCount=0;

function getRegVendor()
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
           url: getBaseURL()+"/SearchVendorServlet",
           data:{ 
                'RequestType':'ListRegVendors', 
                'RegnKey':regnKey,
                'SearchStr':searchStr,
            } ,
           cache:false,
           success: function( vendorsJSON )
           {
               if( vendorsJSON.result == "success")
               {
                   var vendorArr = new Array(vendorsJSON.Rvendors.length);

                   vendorArr = vendorsJSON.Rvendors;
                   
                   parseVendors( vendorArr );
                   
                   newVendorCount=vendorArr.length();
               }
               else
               {
                  
               }
               
           },
            error : function(xhr, textStatus, errorThrown) 
            {
                alert("failed");
            }
         });
}


/* it is a helper method for getRegVendor(). It is used to parse the 
 * vendor deatails and add in search result div.
 */
function parseVendors( vendorArr )
{
    
    $("#ven_search_result").empty();
    
    for ( var i = 0; i < vendorArr.length; i++) 
    {
        
        var vendor = vendorArr[i];

        var vendorKey = vendor.companykey;
        
        var vendorName = vendor.companyname;
        
        var vendorid=vendor.companyid;

        var venDiv = '<div id="ven_'+vendorKey+'" class="filter_comp" onclick=document.getElementById("selected_ven_id").value="'+vendorid+'"; >';
        
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
                
                
                // It is used to get the selected suppliers address details
                
                getRFQSupplierAddress( vendorKey ); 
                
                // Used to fetch the terms and condition for selected company
                getTC( vendorKey,"RFQ" );
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
    
    
   /* $(document).keydown(function(e) // 38-up, 40-down
    { 

    if (e.keyCode == 40) 
    { 
    	alert("key down");
    	
       if( currentSelection == "RFQ Form" )
       {
    	   var arr=idSplitArr;
    	   
    	   var i=arr[2];
    	   
    	   if(i==newVendorCount)
    		   return false;
    	   
    	   var newId=$("#selected_ven_key")+(parseInt(i) + 1);
    		 var companyName=$("#compName").val();
    		 
    		 if(divid!=companyName)
    		 {

 				$("#" + divid).addClass("com_search_result");
 				
 				$("#" + divid).removeClass("com_search_result_selected");

				$("#" + newId).removeClass("com_search_result");

				$("#" + newId).addClass("com_search_result_selected");
				
				divid=newId;
    			}
       }
       
    }
    
    if(e.keyCode==38)
    {
    	if(currentSelection=="RFQ Form")
    	{
    		var arr=divid.split("_");
    		
    		var i=arr[2];
    		
    		if(i==0)
    			return false;
    		
    		 var newId=$("#selected_ven_key")+(parseInt(i) -1);
    		 var companyName=$("#compName").val();
    		 
    		 if(divid!=companyName)
    		 {

 				$("#" + divid).addClass("com_search_result");
 				
 				$("#" + divid).removeClass("com_search_result_selected");

				$("#" + newId).removeClass("com_search_result");

				$("#" + newId).addClass("com_search_result_selected");
				
				divid=newId;
    			}
    		
    	}
   	}
  });*/
}
