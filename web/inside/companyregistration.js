
//$(function() {
//    $( document ).tooltip();
//});

$(document).ready(function() 
{
	
    $("#pay").prop("disabled","disabled");
	
    $('#addone_lastHiddenElement').focus(function(){
        $('#branch_1').focus();
    });
	 
    $('#addtwo_lastHiddenElement').focus(function(){
        $('#branch_2').focus();
    });
	
    // use to save the addess1
    $('#saveaddr1btn').click(function() {
        saveAddress1();
    });
    // use to save the addess1
    $('#saveaddr2btn').click(function() {
        saveAddress2();
    });
        
//        $('#addaddressbtn1').click(function() {
//		addAddress1();
//	});
	
});


$(document).ready(function() 
{
    $('#logo').bind('change', function() 
    {
        var uploadErrDiv = $("#fileupload_err");
    	
        uploadErrDiv.show();
    	
        uploadErrDiv.text("");
    	
        $('#fileerror').val("0");
    	 
    	
    	 
        var ext = $('#logo').val().split('.').pop().toLowerCase();
    	
        if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1)
        {
            uploadErrDiv.text('Invalid extension!');
    		
            $('#fileerror').val("1");
    		
            return;
    		
        }
//        if(this.files[0].size > (1024 * 1024) )  // Value in bytes
//        {
//            $("#fileupload_err").text("File size more than 1MB");
//
//            $('#fileerror').val("1");
//
//            return;
//        }

        //        var file = input.files[0];
        //        var fr = new FileReader();
        //        fr.onload = createImage;
        //        fr.readAsDataURL(file);
        //        $('#fileerror').val("0");
        //        var img = document.createElement('img');
        //        img.onload = imageLoaded;
        //        img.style.display = 'none'; // If you don't want it showing
        //        img.src = fr.result;
        //        document.body.appendChild(img);
        //        if(img.width > 500 || img.height > 500 )
        //        {
        //            uploadErrDiv.text("Image dimension sholud be lesser then or equal to 500x500");
        //
        //            $('#fileerror').val("1");
        //
        //            return;
        //        }
        
//        validateImage();
    	 
    //            validateImage( this );
    	
     
    });
});


$(document).ready(function()
{
    
    $('#complogo').bind('change', function()
    {
        
        //alert("hi");
        //        uploadErrDiv.show();
        //
        //        uploadErrDiv.text("");

        $('#fileupload_err').html("");
        $('#fileupload_err').hide();



        var ext = $('#complogo').val().split('.').pop().toLowerCase();
        var img = document.createElement('img');

        if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1)
        {
            //            uploadErrDiv.text('Invalid extension!');

            $('#fileupload_err').html("Invalid extension of File. Upload Image File only!");
            $('#fileupload_err').show();
            return;

        }
//        else if(this.files[0].size > (1024 * 1024) )  // Value in bytes
//        {
//            //            $("#fileupload_err").text("File size more than 1MB");
//
//            $('#fileupload_err').html("File size exceeded. It should not be more than 1MB");
//            $('#fileupload_err').show();
//            return;
//        }
//        else
//        {
//            validateImage2();
//        }

        if( !$('#fileupload_err').html()== "")
        {
            alert("File Error : "+$('#fileupload_err').html());
            $('#fileupload_err').show();
            return;
        }

    });
});

function checkImageError()
{
    if( !$('#fileupload_err').html()== "")
    {
        $('#fileupload_err').show();
        return false;
    }
    else
    {
        showLoading();
        return true;
    }
}


//function validateImage( input )
//{
//    var uploadErrDiv = $("#fileupload_err");
//
//    var ext = input.files[0].name.split('.').pop().toLowerCase();
////    alert(ext);
//
//    if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
//
//        uploadErrDiv.text('Invalid extension!');
//
//        $('#fileerror').val("1");
//
//        return false;
//
//        var file = input.files[0];
//        var fr = new FileReader();
//        fr.onload = createImage;
//        fr.readAsDataURL(file);
//        $('#fileerror').val("0");
//        var img = document.createElement('img');
//        img.onload = imageLoaded;
//        img.style.display = 'none'; // If you don't want it showing
//        img.src = fr.result;
//        document.body.appendChild(img);
//        if(img.width > 500 || img.height > 500 )
//        {
//            uploadErrDiv.text("Image dimension sholud be lesser then or equal to 500x500");
//
//            $('#fileerror').val("1");
//
//            return false;
//        }
//
//    var form			= $("#registrationform");
//
//    var data			= new FormData();
//
//    //showAjaxLoader();
//
//    data.append('Size',"1");  // Size specified in MB
//
//    data.append('Width',"500");  // in px
//
//    data.append('Height',"500");  // in px
//
//    data.append('IsExactDimension',"No");
//
//    data.append('Image',document.registrationform.logo.files[0]);
//
//    $.ajax({
//        type : "POST",
//        url : form.attr('action'),
//        data : data,
//        contentType : false,
//        processData : false,
//        success : function(data)
//        {
//            //hideAjaxLoader();
//
//            if (data.result == "success")
//            {
//                uploadErrDiv.text( data.message );
//
//                $('#fileerror').val("0");
//
//                var file = $('#logo')[0].files[0];
//
//                $("#logo_file_name").text(file.name);
//            }
//            else
//            {
//                uploadErrDiv.text( data.message );
//
//                $('#fileerror').val("1");
//            }
//        },
//        error : function(xhr, textStatus, errorThrown)
//        {
//        //hideAjaxLoader();
//
//        }
//    });
//    return true;
//}

function submitRegnForm()

{
    var isvalid=$("#registrationform").valid();
	
	
    if(!isvalid)
        return;
	
    if( $('#fileerror').val()== "1" || $('#phoneexist').val()== "1" || $('#emailexist').val()== "1")
    {
        alert("File Error : "+$('#fileerror').val());
        return;
    }
	
    $("#registrationform").submit();
//return true;
}


function validateImage()
{
	
    var input, file, fr, img;
    
    var uploadErrDiv = $("#fileupload_err");
    
    uploadErrDiv.show();

    //    if (typeof window.FileReader !== 'function')
    //    {
    //    	uploadErrDiv.text("The file API isn't supported on this browser yet.");
    //
    //    	$('#fileerror').val("1");
    //
    //        return;
    //    }

    input = document.getElementById('logo');
 
    if (!input)
    {
        uploadErrDiv.text("Um, couldn't find the imgfile element.");
    	
        $('#fileerror').val("1");
    	
        return;
    	
    }
    //    else if (!input.files)
    //    {
    //    	uploadErrDiv.text("This browser doesn't seem to support the `files` property of file inputs.");
    //
    //    	$('#fileerror').val("1");
    //
    //    	return;
    //
    //    }
    else if (!input.files[0])
    {
        uploadErrDiv.text("Please select a file before clicking 'Load'");
    	
        $('#fileerror').val("1");
    	
        return;
    }
    else
    {
       
        file = input.files[0];
        fr = new FileReader();
        fr.onload = createImage;
        fr.readAsDataURL(file);
        
        $('#fileerror').val("0");
    }
    

    function createImage()
    {
        img = document.createElement('img');
        img.onload = imageLoaded;
        img.style.display = 'none'; // If you don't want it showing
        img.src = fr.result;
        document.body.appendChild(img);
    }

    function imageLoaded()
    {
        // write(img.width + "x" + img.height);
        
        if(img.width > 500 || img.height > 500 )
        {
            uploadErrDiv.text("Image dimension sholud be lesser then or equal to 500x500");
    	   
            $('#fileerror').val("1");
    	   
            return;
        }
        // This next bit removes the image, which is obviously optional -- perhaps you want
        // to do something with it!
        img.parentNode.removeChild(img);
        img = undefined;
    }

    function write(msg) {
        var p = document.createElement('p');
        p.innerHTML = msg;
        document.body.appendChild(p);
    }
}

function validateImage2()
{

    var input, file, fr, img;

    //    var uploadErrDiv = $("#fileupload_err");

    //    uploadErrDiv.show();
    $('#fileupload_err').html("");
    $('#fileupload_err').hide();

    //    if (typeof window.FileReader !== 'function')
    //    {
    //    	uploadErrDiv.text("The file API isn't supported on this browser yet.");
    //
    //    	$('#fileerror').val("1");
    //
    //        return;
    //    }

    input = document.getElementById('complogo');

    if (!input)
    {
        //        uploadErrDiv.text("Um, couldn't find the imgfile element.");

        $('#fileupload_err').html("Um, couldn't find the imgfile element.");
        $('#fileupload_err').show();

        return;

    }
    //    else if (!input.files)
    //    {
    //    	uploadErrDiv.text("This browser doesn't seem to support the `files` property of file inputs.");
    //
    //    	$('#fileerror').val("1");
    //
    //    	return;
    //
    //    }
    else if (!input.files[0])
    {
        //        uploadErrDiv.text("Please select a file before clicking 'Load'");

        $('#fileupload_err').html("Please select a file before clicking 'Load'");
        $('#fileupload_err').show();
        //        $('#fileupload_err').val("Please select a file before clicking 'Load'");

        return;
    }
    else
    {

        file = input.files[0];
        fr = new FileReader();
        fr.onload = createImage;
        fr.readAsDataURL(file);

    //$('#fileupload_err').html("");
    }


    function createImage()
    {
        img = document.createElement('img');
        img.onload = imageLoaded;
        img.style.display = 'none'; // If you don't want it showing
        img.src = fr.result;
        document.body.appendChild(img);
    }

    function imageLoaded()
    {
        // write(img.width + "x" + img.height);

        if(img.width > 500 || img.height > 500 )
        {
            //            uploadErrDiv.text("Image dimension sholud be lesser then or equal to 500x500");

            //            $('#fileupload_err').val("Image dimension sholud be lesser then or equal to 500x500");
            $('#fileupload_err').html("Image dimension sholud be less than/equal 500x500");
            $('#fileupload_err').show();
            return;
        }
        // This next bit removes the image, which is obviously optional -- perhaps you want
        // to do something with it!
        img.parentNode.removeChild(img);
        img = undefined;
    }

    function write(msg) {
        var p = document.createElement('p');
        p.innerHTML = msg;
        document.body.appendChild(p);
    }
}



var maxcount=0;



function setRows(rowcount)
{
    maxcount=rowcount;
	
}

function addAddress1()
{
    
    //$("#address_popup1").first().focus();
	
    $('#address_popup1').focus();
	
    //$('#branch_1').focus();
	 
    $( "#branch_1" ).val("");
    $( "#countryregion_1" ).val("");
    $( "#address_1" ).val("");
    $( "#city_1" ).val("");
    $( "#state_1" ).val("");
    $( "#zipcode_1" ).val("");

    $( "#address_popup1" ).show();
	 
    $( "#branch_1" ).trigger('update');
    $( "#countryregion_1" ).trigger('update');
    $( "#state_1" ).trigger('update');
}

function addAddress2()
{
    //$("#address_popup2").first().focus();
	
    $('#address_popup2').focus();
	
    //$('#branch_2').focus();
	
    $( "#branch_2" ).val("");
    $( "#countryregion_2" ).val("");
    $( "#address_2" ).val("");
    $( "#city_2" ).val("");
    $( "#state_2" ).val("");
    $( "#zipcode_2" ).val("");
	
    $( "#address_popup2" ).show();
	 
    $( "#branch_2" ).trigger('update');
    $( "#countryregion_2" ).trigger('update');
    $( "#state_2" ).trigger('update');
}



function saveAddress1()
{
    if($("#countryregion_1").val() != "")
    {
		
        var branch = $( "#branch_1" ).val();
        var country = $( "#countryregion_1" ).val();
        var addr    =$( "#address_1" ).val();
        var city = $( "#city_1" ).val();
        var state = $( "#state_1" ).val();
        var zipcode = $( "#zipcode_1" ).val();
		
		
        var fullAddress = branch+', '+country;
		
        if(addr !='')
        {
            fullAddress = fullAddress+","+addr;
        }
        if(city !='')
        {
            fullAddress = fullAddress+","+city;
        }
        if(state !='')
        {
            fullAddress = fullAddress+","+state;
        }
        if(zipcode !='')
        {
            fullAddress = fullAddress+","+zipcode;
        }
		
        $( "#address_popup1" ).hide();

        $("#addaddrlbl1").text(fullAddress);
		
        $("#addrright1").show();
		
        $("#addrleft1").hide();
		
        $("#address2detail").show();
	
        $("#addrleft2").show();
		
        document.getElementById("addresscount").value = 2;
		 
        $('#compseparator').css({
            'height':'475px',
            'margin-bottom':'50px'
        });
        $('#canBtnDiv').css({
            'top':'165px'
        });
    }
    else
    {
        $('#countryregion_1err').show();
        $('#countryregion_1err').text("Select Country");
    }
	
}



function saveAddress2()
{
    if($("#countryregion_2").val() != "")
    {
        var branch = $( "#branch_2" ).val();
        var country = $( "#countryregion_2" ).val();
        var addr    =$( "#address_2" ).val();
        var city = $( "#city_2" ).val();
        var state = $( "#state_2" ).val();
        var zipcode = $( "#zipcode_2" ).val();
	
        var fullAddress = branch+', '+country;
		
        if(addr !='')
        {
            fullAddress = fullAddress+","+addr;
        }
        if(city !='')
        {
            fullAddress = fullAddress+","+city;
        }
        if(state !='')
        {
            fullAddress = fullAddress+","+state;
        }
        if(zipcode !='')
        {
            fullAddress = fullAddress+","+zipcode;
        }
		

        $("#addaddrlbl2").text(fullAddress);
	
        $( "#address_popup2" ).hide();
	 
        $("#addrright2").show();
	
        $("#addrleft2").hide();
	
        document.getElementById("addresscount").value = 3;
	
        $('#compseparator').css({
            'height':'495px',
            'margin-bottom':'50px'
        });
        $('#canBtnDiv').css({
            'top':'185px'
        });
    }
    else
    {
        $('#countryregion_2err').show();
        $('#countryregion_2err').text("Select Country");
    }
	
}

function removeAddress1()
{
	
	
    if( document.getElementById("addresscount").value == 3 )
    {
        $("#addaddrlbl1").text($("#addaddrlbl2").text());
		 
        $( "#branch_1" ).val($( "#branch_2" ).val());
        $( "#countryregion_1" ).val($( "#countryregion_2" ).val());
        $( "#address_1" ).val($( "#address_2" ).val());
        $( "#city_1" ).val($( "#city_2" ).val());
        $( "#state_1" ).val($( "#state_2" ).val());
        $( "#zipcode_1" ).val($( "#zipcode_2" ).val());
		
        removeAddress2();
    }
    else
    {
        $("#addrright1").hide();
        $("#addrleft2").hide();
        $("#addrleft1").show();
        $("#address2detail").hide();
	
		
        $('#compseparator').css({
            'height':'375px',
            'margin-bottom':'100px'
        });
        $('#canBtnDiv').css({
            'top':'115px'
        });
		
        document.getElementById("addresscount").value = 1;
    }
	
	
}

function removeAddress2()
{
    $("#addrright2").hide();
    $("#addrleft2").show();
	
    document.getElementById("addresscount").value = 2;
	
    $('#compseparator').css({
        'height':'475px',
        'margin-bottom':'50px'
    });
    $('#canBtnDiv').css({
        'top':'165px'
    });

}


function changeStateDropDown(countryName)
{
	
    if(countryName=="United States")
    {
        $("#select_0_container").show();
        $("state_text_0").hide();
    }
    else
    {
		
        $("#state_text_0").show();
        $("#select_0_container").hide();
    }

}

function stateDropDown(countryName)
{
	
    if(countryName=="United States")
    {
		
        $("#select_1_container").show();
        $("state_text_1").hide();
    }
    else
    {
        $("#state_text_1").show();
        $("#select_1_container").hide();
    }

}

function changeDropDown(countryName)
{
	
    if(countryName=="United States")
    {
		
        $("#select_2_container").show();
        $("state_text_2").hide();
    }
    else
    {
		
        $("#state_text_2").show();
        $("#select_2_container").hide();
    }

}





//}