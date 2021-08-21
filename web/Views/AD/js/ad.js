
$(function() {
    $( document ).tooltip();
  });

function adOnload()
{
	hideAjaxLoader();
	
	$("#home_file_upload_control_1").click(function(){
	
	});
	$("#btn_save").click(function(){
		save();
		
	});
	
	/* Picture Upload - 1 */
	$('#ad_file').bind('change', function() 
	{
                this.title='';
		validateImage( this, "#preview_image" );

	});
	
}


function readURLImage(input,imageDivID)
{
	if (input.files && input.files[0])
	{
		var reader = new FileReader();

		reader.onload = function(e)
		{
			$(imageDivID).attr('src', e.target.result);
			
			$(imageDivID+"_del").show();
			
		};

		reader.readAsDataURL(input.files[0]);
	}
	
	$("#ad_error").text("");
}




function validateImage( input, imageDivID )
{
	$('#ad_file').removeAttr('title');
       var ext = input.files[0].name.split('.').pop().toLowerCase();
	
	if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
	    
		$("#ad_error").text( "Invalid file format" );
		
		input.files[0]="";
	    return
	}
	
	var form			= $("#hidden_form");
	
	var data			= new FormData();
	
	showAjaxLoader();
	
	data.append('Size',"1");  // Size specified in MB
	
	data.append('Width',"200");  // in px
	
	data.append('Height',"150");  // in px
	
	data.append('IsExactDimension',"Yes");
	
	data.append('Image',document.form_ad.ad_file.files[0]);
	
	$.ajax({
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
				readURLImage(input,imageDivID);
			}
			else
			{
				$("#ad_error").text( data.message );
			}
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			hideAjaxLoader();
			
		}
	});
}

function save()
{
	var form			= $("#form_ad");
	
	var data			= new FormData();
	
	var companyKey 		= $('#regnkey').val();

	var customerKey 	= $('#EmailAddress').val();
	
	var alternateText	= $("#alternateText" ).val();
	
	var link			= $("#link").val();
	
	var imagePath		= $("#ad_file").val();
	
	
	if( alternateText =="" )
	{
		alert( "Enter the alternate Text" );
		
		return;
	}
	
	if( link == "")
	{
		alert( "Enter the Link" );
		
		return;
	}
	
	if( imagePath =="" )
	{
		alert( "Select the Ad image to upload" );
		
		return;
	}
	
	showAjaxLoader();
	
	data.append('RequestType','AddAdvertisement');
	
	data.append('AlternateText',alternateText);
	
	data.append('Link',link);
	
	data.append('RegnKey',companyKey);
	
	data.append('UserKey',customerKey);
	
	data.append('AdImage',document.form_ad.ad_file.files[0]);
	
	$.ajax({
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
				
				
				$("#alternateText").val("");
				
				$("#link").val("");
				
				document.form_ad.reset();
				
				ShowToast( data.message, 2000 );
			}
			else
			{
				ShowToast( data.message, 2000 );
			}
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			hideAjaxLoader();
			
		}
	});
}
	