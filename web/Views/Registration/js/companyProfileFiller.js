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

function fetchBuisnessCategory(setValstr) {

	var isSuccess = true;


	$.ajax({
				type : "POST",
				url : "EntityLoaderServlet",
				data : {
					'entityname' : 'businesscategory',
				},
				dataType : 'json',
				cache : false,
				success : function(result) {
					

					var $select = $('#businesscategory');

					$select.find('option').remove();

					$('<option>').val('').text('--Select Category--').appendTo(
							$select);

					$.each(result, function(key, value) {
						if (key == "result") {

							if (value == 'success') {
								isSuccess = true;
							} else {
								isSuccess = false;
							}

						} else {
							if (isSuccess) {
								$('<option>').val(key).text(value).appendTo($select);
								$('#businesscategoryerr').text("");
							} else {
								$('#businesscategoryerr').text(value);
								return;
							}
						}
					});
					
					sortSelectBox('businesscategory');
					
					
					$("#businesscategory2").val(setValstr); // Setting the Selected Value
					$("#businesscategory").trigger('update');
					
				        var ctgry_lst=setValstr.split(",");                                        
                                        for(var i=0;i<ctgry_lst.length;i++)
                                        {   
                                            if(ctgry_lst[i].replace(/^\s+|\s+$/g,'')!=="")
                                            {    
                                            document.getElementById('slctd_ctgry_lst').innerHTML+="<li id='cat"+i+"' style='background:#B0B0B0;'>"+ctgry_lst[i]+" <span style='cursor:pointer;color:red;' onclick=rmv_ctgry('"+ctgry_lst[i]+"','cat"+i+"')>X</span></li>";
                                            }
                                        }		
				},
				error : function(xhr, textStatus, errorThrown) {
					$('#businesscategoryerr').text("Request failed. Try again");
				}
			});
}


function fetchCountryStateList(coutrysetVal,statesetVal) {

	var isSuccess = true;

	$.ajax({
		type : "POST",
		url : getBaseURL() + "/EntityLoaderServlet",
		data : {
			'entityname' : 'country',
		},
		dataType : 'json',
		cache : false,
		success : function(result) {

			var $select = $('#countryregion_0');
			var $countrySelect=$("#countryregion_pop");

			$select.find('option').remove();
			$countrySelect.find('option').remove();

			$('<option>').val('').text('--Select Country--').appendTo($select);
			$('<option>').val('').text('--Select Country--').appendTo($countrySelect);

			$.each(result, function(key, value) {
				if (key == "result") {

					if (value == 'success') {
						isSuccess = true;
					} else {
						isSuccess = false;
					}

				} else {

					if (isSuccess) {
						$('<option>').val(key).text(value).appendTo($select);
						$('<option>').val(key).text(value).appendTo($countrySelect);
						$('#countryregion_0err').text("");
					} else {
						$('#countryregion_0err').text(value);
						return;
					}
				}
			});

			sortSelectBox('countryregion_0');
			sortSelectBox('countryregion_pop');
			
			$("#countryregion_0 option[value='United States']").remove();
			$("<option value='United States'>United States</option>").insertAfter("#countryregion_0 option:first");

			$("#countryregion_0").val(coutrysetVal); // Setting the Selected Value
			
			$("#countryregion_0").trigger('update');
			
			$("#countryregion_pop option[value='United States']").remove();
			$("<option value='United States'>United States</option>").insertAfter("#countryregion_pop option:first");

			$("#countryregion_pop").val(coutrysetVal); // Setting the Selected Value
			
			$("#countryregion_pop").trigger('update');
			
			$countrySelect.trigger('update');


			fetchStateList("countryregion_0",statesetVal);
			fetchStateList("countryregion_pop",statesetVal);
			
		},
		error : function(xhr, textStatus, errorThrown) {
			$('#countryregion_0err').text("Request failed. Try again.");
		}
	});	
	
}

function fetchStateList(id,stateval) 
{
	var isSuccess = true;

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
	
	$('<option>').val('').text('--Select State--').appendTo($select);
	
	if(country=="")
		return;
	
	var countryErrDiv=countryDivID+"err";
	
	$(countryErrDiv).text('');

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
		success : function(result) {

			$.each(result, function(key, value) {
				if (key == "result") {
					if (value == 'success') {
						isSuccess = true;
					} else {
						isSuccess = false;
					}

				} else {
					if (isSuccess) {
						$('<option>').val(key).text(value).appendTo($select);
						$('#state_0err').text("");
					} else {
						$('#state_0err').text(value);

						return;
					}
				}
			});
			
			sortSelectBox('state_'+rowNo);
			$(stateDivId).val(stateval); // Set the Value
			$(stateDivId).trigger('update');
		},
		error : function(xhr, textStatus, errorThrown) {
			$('#state_0err').text("Request failed. Try again.");
		}
	});
}

//fetchThemeList

function fetchBranchList(branchsetVal) {

	var isSuccess = true;
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/EntityLoaderServlet",
		data : {
			'entityname' : 'branch',
		},
		dataType : 'json',
		cache : false,
		success : function(result) {

			var $select = $('#branch_0');
			var $branchpop = $('#branch_pop');

			$select.find('option').remove();

			$('<option>').val('').text('--Select Branch--').appendTo($select);
			$('<option>').val('').text('--Select Branch--').appendTo($branchpop);

			$.each(result, function(key, value) {
				if (key == "result") {

					if (value == 'success') {
						isSuccess = true;
					} else {
						isSuccess = false;
					}

				} else {

					if (isSuccess) {
						$('<option>').val(key).text(value).appendTo($select);
						$('<option>').val(key).text(value).appendTo($branchpop);
						$('#countryregion_0err').text("");
					} else {
						$('#countryregion_0err').text(value);
						return;
					}
				}
			});

			
			$("#branch_0").val(branchsetVal); // Setting the Selected Value		
			
			$("#branch_0").trigger('update');
			
			$branchpop.trigger('update');
		},
		error : function(xhr, textStatus, errorThrown) {
			$('#countryregion_0err').text("Request failed. Try again.");
		}
	});	
	
}

function fetchThemeList(themesetVal) {

	var isSuccess = true;

	$.ajax({
		type : "POST",
		url : getBaseURL() + "/EntityLoaderServlet",
		data : {
			'entityname' : 'theme',
		},
		dataType : 'json',
		cache : false,
		success : function(result) {

			var $select = $('#themeSelect');

			$select.find('option').remove();

			$('<option>').val('').text('--Select Theme--').appendTo($select);

			$.each(result, function(key, value) {
				if (key == "result") {

					if (value == 'success') {
						isSuccess = true;
					} else {
						isSuccess = false;
					}

				} else {

					if (isSuccess) {
						$('<option>').val(key).text(value).appendTo($select);
						$('#countryregion_0err').text("");
					} else {
						$('#countryregion_0err').text(value);
						return;
					}
				}
			});


			$("#themeSelect").val(themesetVal); // Setting the Selected Value	
			
			$("#themeSelect").trigger('update');
		},
		error : function(xhr, textStatus, errorThrown) {
			$('#countryregion_0err').text("Request failed. Try again.");
		}
	});	
	
}

