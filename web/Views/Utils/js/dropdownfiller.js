// Fetch all business category for supply medium using ajax request (Using jQuery)
function fetchCategory() {
	//alert(getBaseURL());
	$.ajax({
				type : "POST",
				url : getBaseURL() + "/EntityLoaderServlet", // servlet name
                                
				data : {
					'entityname' : 'businesscategory',  // request partameters
				},
				dataType : 'json',
				cache : false,
				success : function(data) {
//                                    alert("Business Category : "+data.value);
					
					var $select = $('#businesscategory');
					// Remove the old drop down box datas
					$select.find('option').remove();

					if( data.result == "success") // success result
					   {
						
						$('<option>').val('').text('--Select Category--').appendTo(
								$select);
						
							$.each(data, function(key, value) // get the key,value one by one 
							{
								if( value != "success")  // leave 'result':'success' pair
								{
									$('<option>').val(key).text(value).appendTo(
											$select);
									$('#businesscategoryerr').text("");
								}
									
							});
							
							sortSelectBox('businesscategory');
							
							$("#businesscategory option:first-child").attr("selected", true);
							
							 $('#businesscategory option').prop('disabled', false);
							
					   }
					   else  // failed result
					   {
						 
						   $select.find('option').remove();
						   
						   $('<option>').val("").text('--N/A--').appendTo(
									$select); 
						   
						   
						   $('#businesscategory option').prop('disabled', true);
						  
					   }
					
					  $select.trigger('update');

				},
				error : function(xhr, textStatus, errorThrown)  // other errors
				{
					$('#businesscategoryerr').text("Request failed. Try again");
				}
			});
}

function fetchCountry() {
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/EntityLoaderServlet",
		data : {
			'entityname' : 'country',
		},
		dataType : 'json',
		cache : false,
		success : function(data) {
			
			// Set value for country0 select box
			var $select0 = $('#countryregion_0');
			$select0.find('option').remove();
			
			
			//  Set value for country1 select box
			var $select1 = $('#countryregion_1');
			$select1.find('option').remove();
			
			
			
		//  Set value for country2 select box
			var $select2 = $('#countryregion_2');
			$select2.find('option').remove();
			
			

			if( data.result == "success")
			   {
				$('<option>').val('').text('--Select Country--').appendTo($select0);
				
				$('<option>').val('').text('--Select Country--').appendTo($select1);
				
				$('<option>').val('').text('--Select Country--').appendTo($select2);
				
					$.each(data, function(key, value) 
					{
						if( value != "success")
						{
							$('<option>').val(key).text(value).appendTo($select0);
							$('<option>').val(key).text(value).appendTo($select1);
							$('<option>').val(key).text(value).appendTo($select2);
							
							$('#countryregion_0err').text("");
							$('#countryregion_1err').text("");
							$('#countryregion_2err').text("");
							
						}
							
					});
					
					// Sort the country in alphabetical order
					
					sortSelectBox('countryregion_0');
					sortSelectBox('countryregion_1');
					sortSelectBox('countryregion_2');
					
					$("#countryregion_0 option[value='United States']").remove();
					$("#countryregion_1 option[value='United States']").remove();
					$("#countryregion_2 option[value='United States']").remove();
					
					
					$("<option value='United States'>United States</option>").insertAfter("#countryregion_0 option:first");
					$("<option value='United States'>United States</option>").insertAfter("#countryregion_1 option:first");
					$("<option value='United States'>United States</option>").insertAfter("#countryregion_2 option:first");
					
					
					$('#countryregion_0 option').prop('disabled', false);
					$('#countryregion_0 option').prop('disabled', false);
					$('#countryregion_0 option').prop('disabled', false);
					 
			   }
			   else
			   {
				   $select0.find('option').remove();
				   
				   $('<option>').val('').text('--N/A--').appendTo(
							$select0);
				   
				   $select1.find('option').remove();
				   
				   $('<option>').val('').text('--N/A--').appendTo(
							$select1);
				   
				   $select2.find('option').remove();
				   
				   $('<option>').val('').text('--N/A--').appendTo(
							$select2);
				   
				   $('#countryregion_0 option').prop('disabled', true);
				   $('#countryregion_0 option').prop('disabled', true);
				   $('#countryregion_0 option').prop('disabled', true);
					
				  
			   }
			
			$("#countryregion_0 option:first-child").attr("selected", true);
			$("#countryregion_1 option:first-child").attr("selected", true);
			$("#countryregion_2 option:first-child").attr("selected", true);

			 $select0.trigger('update');
			   
			 $select1.trigger('update');
			   
			 $select2.trigger('update');
			
		},
		error : function(xhr, textStatus, errorThrown) {
			$('#countryregion_0err').text("Request failed. Try again.");
		}
	});
}

function fetchCompany(country) {

	$.ajax({
		type : "POST",
		url : getBaseURL() + "/EntityLoaderServlet",
		data : {
			'country' : country,
			'entityname' : 'company',
		},
		dataType : 'json',
		cache : false,
		success : function(data) {
			var $select = $('#companyname');

			$select.find('option').remove();

			
			
			if( data.result == "success")
			   {
					
				$('<option>').val('').text('--Select Company--').appendTo($select);
				
					$.each(data, function(key, value) 
					{
						if( value != "success")
						{
							$('<option>').val(key).text(value).appendTo($select);
							$('#companyerr').text("");
						}
							
					});
					
					sortSelectBox('companyname');
					
					$("#companyname option:first-child").attr("selected", true);
					
					$('#companyname option').prop('disabled', false);
					
					
			   }
			   else
			   {
				   $select.find('option').remove();
				   
				   $('<option>').val('').text('--N/A--').appendTo(
							$select);
				  
				   $('#companyname option').prop('disabled', true);
				  
			   }

			 $select.trigger('update');
			
		},
		error : function(xhr, textStatus, errorThrown) {
			$('#companyerr').text("Request failed. Try again.");
		}
	});
}

function fetchState(id) {
	
	
	var strArr = id.split("_");

	var rowNo = 0;

	//alert("Array length="+strArr.length);

	if (strArr.length > 1) {
		rowNo = strArr[1];
	}

	var countryDivID = "#countryregion_" + rowNo;

	var stateDivId = "#state_" + rowNo;
	
	var $select = $(stateDivId);

	var country = $(countryDivID).val();
	
	$select.find('option').remove();
	 
	 
	if(country=="")
		return;
	
	var countryErrDiv=countryDivID+"err";
	
	$(countryErrDiv).text('');
	
	var stateErrDiv = stateDivId+"err";

	//alert("CountryName="+country);

	$.ajax({
		type : "POST",
		url : getBaseURL() + "/EntityLoaderServlet",
		data : {
			'country' : country,
			'entityname' : 'state',
		},
		dataType : 'json',
		cache : false,
		success : function(data) 
		{
			 if( data.result == "success")
			   {
				 $('<option>').val('').text('--Select State--').appendTo($select);
				 
					$.each(data, function(key, value) 
					{
						if( value != "success")
						{
							$('<option>').val(key).text(value).appendTo($select);
							
							$(stateErrDiv).text("");
						}
							
					});
					
					sortSelectBox("state_" + rowNo);
					
					$select.prop('disabled', false);
			   }
			   else
			   {
				   
				   $('<option>').val('').text('--N/A--').appendTo(
							$select);
				   
				   $select.prop('disabled', true);
				  
			   }
			 
			 $(stateDivId+" option:first-child").attr("selected", true);
			 
			 $select.trigger('update');
		},
		error : function(xhr, textStatus, errorThrown) {
			$('#'+stateErrDiv).text("Request failed. Try again.");
		}
	});
}
