/* ------------------------------------------------------------------------------ */

var lastSelectedRow = 0;
var lastSelectedRowStatus = "";

var deleteRow = 0;

$('body').click(function()
{ 
	
	if( $("#units_add_quantity_unit").is(":visible") )
	{
		$("#units_add_quantity_unit").hide();
	}
	if( $("#add_currency_add_currency").is(":visible") )
	{
		$("#add_currency_add_currency").hide();
	}
		
});


/* Get the Product */
SM.Corporate.ProductCatalog.getAllCatalogs = function()
{

	showAjaxLoader();

	var regnKey = $('#regnkey').val();

	$.ajax(
	{
		type : "POST",
		url : getBaseURL() + "/FetchProdCatalogServlet",
		data :
		{
			'CompanyRegnKey' : regnKey,
		},
		cache : false,
		success : function(data)
		{
			hideAjaxLoader();

			if (data == null)
				return;

			if (data.result == "success")
			{
				for ( var i = 0; i < data.CatalogList.length; i++)
				{
					var CatalogItem = data.CatalogList[i];

					var ItemName = CatalogItem.itemName_;
					var ItemDes = CatalogItem.itemDesc_;
					var ItemPartNumber = CatalogItem.itemPartNo_;
					var ItemSKU = CatalogItem.itemsku_;
					var ItemQuantity = CatalogItem.itemQuan_ + " " + CatalogItem.itemQuanTypeRelKey_.quanTypeKey_;
					var ItemPrice = CatalogItem.itemCrcyRelKey_.currencyKey_ + " " + CatalogItem.itemPrice_;
					var ItemHidePrice = CatalogItem.hidePrice_;
					var ItemKey = CatalogItem.itemKey_;
					var ItemTax = CatalogItem.itemTaxRate_;

					SM.Corporate.ProductCatalog.Table.fnAddData([ ItemName, ItemDes, ItemPartNumber, ItemSKU, ItemQuantity,
							ItemPrice, ItemTax, "", ItemKey ]);

				}
			}

			else
			{
				SM.Corporate.ProductCatalog.setErrorInfo(data.message);
			}
		},
		error : function(xhr, textStatus, errorThrown)
		{
			hideAjaxLoader();

			SM.Corporate.ProductCatalog.setErrorInfo("Unexcepted error. Please try again.");
		}
	});
};

/* ------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------ */

SM.Corporate.ProductCatalog.showProductCatalogTab = function()
{
	$("#productcatalog_content").show();
	$("#newcatalog_content").hide();
	$("#CatalogImport_content").hide();

	$('#catalog_details').removeClass('normal');
	$('#catalog_details').addClass('highlight');
	$('#new_items').removeClass('highlight');
	$('#new_items').addClass('normal');
	$('#add_csv_items').removeClass('highlight');
	$('#add_csv_items').addClass('normal');
};

SM.Corporate.ProductCatalog.showNewCatalogTab = function()
{
	
	$("#newcatalog_content").show();
	$("#productcatalog_content").hide();
	$("#CatalogImport_content").hide();

	$('#catalog_details').removeClass('highlight');
	$('#catalog_details').addClass('normal');
	$('#new_items').removeClass('normal');
	$('#new_items').addClass('highlight');
	$('#add_csv_items').removeClass('highlight');
	$('#add_csv_items').addClass('normal');
	
	
	resetForm("ProductCatlogFormAddWindow");
	
	$("#units_add_quantity_unit").hide();
	
	$("#add_currency_add_currency").hide();
	
	
};

SM.Corporate.ProductCatalog.showCSVImport = function()
{
	$("#newcatalog_content").hide();
	$("#productcatalog_content").hide();
	$("#CatalogImport_content").show();

	$('#catalog_details').removeClass('highlight');
	$('#catalog_details').addClass('normal');
	$('#new_items').removeClass('highlight');
	$('#new_items').addClass('normal');
	$('#add_csv_items').removeClass('normal');
	$('#add_csv_items').addClass('highlight');

};

function highlight_options(field)
{
	field.options[field.selectedIndex].className = 'test';
}

/* ------------------------------------------------------------------------------ */
// Initiate the DataGrid
/* ------------------------------------------------------------------------------ */
SM.Corporate.ProductCatalog.initDataTable = function()
{
	SM.Corporate.ProductCatalog.Table = $('#ProductCatalogList').dataTable(
	{

		"aoColumnDefs" : [
		{
			"bVisible" : false,
			"aTargets" : [ 9, 10 ]
		}, ],
		sDom : 'lfr<"fixed_height"t>ip',

		"sPaginationType" : "full_numbers",
		"bAutoWidth" : false,
		"aoColumnDefs" : [
		{
			"sWidth" : "200px",
			"aTargets" : [ 0 ]
		},
		{
			"sWidth" : "250px",
			"aTargets" : [ 1 ]
		},
		{
			"sWidth" : "150px",
			"aTargets" : [ 2 ]
		},
		{
			"sWidth" : "150px",
			"aTargets" : [ 3 ]
		},
		{
			"sWidth" : "129px",
			"aTargets" : [ 4 ]
		},
		{
			"sWidth" : "100px",
			"aTargets" : [ 5 ]
		},
		{
			"sWidth" : "80px",
			"aTargets" : [ 6 ]
		},
		{
			"sWidth" : "80px",
			"aTargets" : [ 7 ]
		},
		 ],

		"bLengthChange" : false,
		"oLanguage" :
		{
			"sSearch" : "Search",
			"sEmptyTable" : "No Items found"
		},
		"bSort" : true,
		"aaSorting" : [ [ 0, 'asc' ] ],

		"bPaginate" : true,

		"bFilter" : true,

		"bInfo" : true,

		"iDisplayLength" : 15,

		"oLanguage" :
		{
			"sSearch" : ""
		},

		"fnRowCallback" : function(nRow, aData, iDisplayIndex, iDisplayIndexFull)
		{

			$("td", nRow).addClass('rowBorder');

			$(nRow).click(function()
			{

				var aTrs = SM.Corporate.ProductCatalog.Table.fnGetNodes();

				var RowPosition = SM.Corporate.ProductCatalog.Table.fnGetPosition(this);

				// if row already selected then just return
				if ($(aTrs[RowPosition]).hasClass('row_selected'))
				{
					return;
				}

				for ( var i = 0; i < aTrs.length; i++)
				{
					if ($(aTrs[i]).hasClass('row_selected'))
					{
						$(aTrs[i]).removeClass('row_selected');
					}

				}

				$(nRow).addClass('row_selected');

				SM.Corporate.ProductCatalog.Table.fnUpdate("", lastSelectedRow, 7, false, false);

				var aDataNew = SM.Corporate.ProductCatalog.Table.fnGetData(RowPosition);

				lastSelectedRow = RowPosition;

				lastSelectedRowStatus = aDataNew[3];

				addCtrlBtns(RowPosition, aDataNew[2]);

			});

			return nRow;
		}
	});
};

function addCtrlBtns(rowNo, userId)
{

	// Add edit and delete button

	var buttons = "<img src='Views/UserMgmt/images/user_edit_btn.png'style='cursor:pointer;z-index:100;margin-right:10px;' title='Edit Item' onclick='SM.Corporate.ProductCatalog.OpenEditView("
			+ rowNo + ")'/>";
	buttons += "<img src='Views/UserMgmt/images/user_delete_btn.png' style='cursor:pointer;z-index-101;' title='Delete Item' onclick='SM.Corporate.ProductCatalog.deleteItemConfirmation(";
	buttons += rowNo + ");'/>";

	SM.Corporate.ProductCatalog.Table.fnUpdate(buttons, rowNo, 7, false, false);
}

SM.Corporate.ProductCatalog.LastOpentIteminEditView = 0;

SM.Corporate.ProductCatalog.OpenEditView = function(rowNo)
{
	SM.Corporate.ProductCatalog.LastOpentIteminEditView = rowNo;

	var aData = SM.Corporate.ProductCatalog.Table.fnGetData(rowNo);
	$("#popup_item_name").val(aData[0]);
	$("#popup_item_description").val(aData[1]);
	$("#popup_item_part_no").val(aData[2]);
	$("#popup_item_sku").val(aData[3]);
	var value = aData[4].split(' ');
	$("#popup_item_quantity").val(value[0]);
	$("#popup_quantity_unit").text(value[1]);
	value = aData[5].split(' ');
	$("#popup_item_price").val(value[1]);
	$("#popup_currency").text(value[0]);
	$("#popup_item_tax").val(aData[6]);

	if (aData[7].toLowerCase() == "false")
	{
		$('#popup_HidePrice').prop('checked', false);
	}
	else
	{
		$('#popup_HidePrice').prop('checked', true);
	}

	$("#ProdCatalogEdit").show();
};

SM.Corporate.ProductCatalog.RevertInfo = function()
{
	SM.Corporate.ProductCatalog.popupFormValidator.resetForm();
	SM.Corporate.ProductCatalog.OpenEditView(SM.Corporate.ProductCatalog.LastOpentIteminEditView);
};

SM.Corporate.ProductCatalog.deleteItemConfirmation = function(rowNo)
{
	deleteRow = rowNo;
	var aData = SM.Corporate.ProductCatalog.Table.fnGetData(rowNo);
	var name = aData[0];
	message = "This operation will delete the Item '" + name + "' permanently. Do you want to continue?";
	showWarning(message);
};

function deleteConfirm()
{
	showAjaxLoader();

	SM.Corporate.ProductCatalog.clearErrorInfo();

	var aData = SM.Corporate.ProductCatalog.Table.fnGetData(deleteRow);

	$.ajax(
	{
		type : "POST",
		url : getBaseURL() + "/ProdCatalogMgmtServlet",
		data :
		{
			'Action' : 'RemoveItem',
			'RemoveItemID' : aData[8],
		},
		cache : false,
		success : function(data)
		{
			hideAjaxLoader();

			if (data.result == "success")
			{
				ShowToast(data.message, 2000);

				SM.Corporate.ProductCatalog.Table.fnDeleteRow(deleteRow);

				SM.Corporate.ProductCatalog.Table.fnDraw();

				lastSelectedRowStatus = "";
			}
			else
			{
				SM.Corporate.ProductCatalog.setErrorInfo(data.message);
			}
		},
		error : function(xhr, textStatus, errorThrown)
		{
			hideAjaxLoader();

			SM.Corporate.ProductCatalog.setErrorInfo("Unexcepted error. Please try again.");
		}
	});
}

SM.Corporate.ProductCatalog.saveCatalogInfo = function()
{

	if (!$("#ProductCatlogForm").valid())
	{
		return;
	}

	showAjaxLoader();

	var aData = SM.Corporate.ProductCatalog.Table.fnGetData(SM.Corporate.ProductCatalog.LastOpentIteminEditView);

	SM.Corporate.ProductCatalog.clearErrorInfo();

	var hideprice = 0;

	if ($("#popup_HidePrice").is(":checked"))
	{
		hideprice = 1;
	}

	var regnkey = $("#regnkey").val();
	
	$.ajax(
	{
		type : "POST",
		url : getBaseURL() + "/ProdCatalogMgmtServlet",
		data :
		{
			'Action' : 'UpdateItem',
			'cmp_regn_key' : regnkey,
			'item_key' : aData[8],
			'item_name' : $("#popup_item_name").val(),
			'item_description' : $("#popup_item_description").val(),
			'item_part_no' : $("#popup_item_part_no").val(),
			'item_sku' : $("#popup_item_sku").val(),
			'item_quantity' : $("#popup_item_quantity").val(),
			'quantity_unit' : $("#popup_quantity_unit").text(),
			'item_price' : $("#popup_item_price").val(),
			'item_currency' : $("#popup_currency").text(),
			'item_tax' : $("#popup_item_tax").val(),
			'HidePrice' : 0
		},
		cache : false,
		success : function(data)
		{
			hideAjaxLoader();

			if (data.result == "success")
			{
				ShowToast(data.message, 2000);

				$("#ProdCatalogEdit").hide();

				lastSelectedRowStatus = "";

				SM.Corporate.ProductCatalog.Table.fnClearTable();

				SM.Corporate.ProductCatalog.getAllCatalogs();
			}

			else
			{
				SM.Corporate.ProductCatalog.setErrorInfo(data.message);
				$("#ProdCatalogEdit").hide();
			}
		},
		error : function(xhr, textStatus, errorThrown)
		{
			hideAjaxLoader();

			SM.Corporate.ProductCatalog.setErrorInfo("Unexcepted error. Please try again.");

			$("#ProdCatalogEdit").hide();
		}
	});

};

var NumberofElement=0;
SM.Corporate.ProductCatalog.AddCatalogInfo = function()
{
	if (!$("#ProductCatlogFormAddWindow").valid(
	{
		errorPlacement : function(error, element)
		{
		}
	}))
	{
		return;
	}
	
	var i=1;
	var isexist=false;
	for(i=1;i<=NumberofElement;i++)
	{
		var j=0;
		$("#tempDiv_"+i).children("td").each(function() {
			
				if(j==4)
				{
					if($("#item_part_no").val() == $(this).text())
					{
						ShowToast("Product ID Alredy Exist");
						isexist=true;
						return;
					}
				}
				
				j=j+1;
			    
		});
	}
	
	if(isexist)
	{
		return;
	}
	
	showAjaxLoader();
	
	var regnkey = $("#regnkey").val();
	$.ajax(
			{
				type : "POST",
				url : getBaseURL() + "/ProdCatalogMgmtServlet",
				data :
				{
					'Action' : 'ProductKeyExist',
					'cmpregnKey' : regnkey,
					'PartNumber' :$("#item_part_no").val()
				},
				cache : false,
				success : function(data)
				{
					hideAjaxLoader();

					if (data.result == "success")
					{
						if(data.keyExist == "0")
						{
							 var hideprice = "False";
							 
								if($("#HidePrice").is(":checked")) 
								{ 
									hideprice = "True"; 
								}
								
								NumberofElement = NumberofElement+1;

								var	TemHTML = '<tr id="tempDiv_'+NumberofElement+'">';
								TemHTML += '<td style="width: 40px"><img src="Views/Corporate/images/remove_item.png" onclick="removeTheTempElement(&#39;tempDiv_'+NumberofElement+'&#39;)" style="cursor:pointer;z-index:100;margin-right:10px;" title="Remove" /></td>';
								TemHTML += '<td style="font-size: 13px;width: 50px">'+NumberofElement+'</td>';
								TemHTML += '<td style="font-size: 13px;width: 100px">'+ $("#item_name").val()+'</td>';
								TemHTML += '<td style="font-size: 13px;width: 130px;">'+$("#item_description").val()+'</td>';
								TemHTML += '<td style="font-size: 13px;width: 100px;">'+$("#item_part_no").val()+'</td>';
								TemHTML += '<td style="font-size: 13px;width: 100px;">'+$("#item_sku").val()+'</td>';
								TemHTML += '<td style="font-size: 13px;width: 100px;">'+$("#item_quantity").val()+" "+$("#add_quantity_unit").text()+'</td>';
								TemHTML += '<td style="font-size: 13px;width: 100px;">'+$("#add_currency").text()+" "+$("#item_price").val()+'</td>';
								TemHTML += '<td style="font-size: 13px;width: 100px;">'+$("#item_tax").val()+'</td>';
								TemHTML += '<td style="font-size: 13px;width: 140px;display:none;">false</td>';
								TemHTML += '</tr>';	
								
								$("#TempValueHolderDiv").append(TemHTML);
								
								resetAddForm();
						}
						else
							{
								ShowToast(data.message, 2000);
							}
					}

					else
					{
						ShowToast("Unexcepted error. Please try again.");
					}
				},
				error : function(xhr, textStatus, errorThrown)
				{
					hideAjaxLoader();

					ShowToast("Unexcepted error. Please try again.");

				}
			});
};

function resetAddForm()
{
	 $("#item_name").val("");
	 $("#item_description").val("");
	 $("#item_part_no").val("");
	 $("#item_sku").val("");
	 $("#item_quantity").val("");
	 $("#item_price").val("");
	 $("#item_tax").val("");
	 $("#HidePrice").attr("checked",false);
	
}

SM.Corporate.ProductCatalog.SaveAllCatalogInfos=function()
{
	/*if (!$("#ProductCatlogFormAddWindow").valid())
	{
		return;
	}*/
	
	if( NumberofElement == 0 )
	{
		ShowToast("Add any one of item");
		
		return;
	}

	// showAjaxLoader();

	var regnkey = $("#regnkey").val();
	
	var saveProductInfo="";
	
	var i=1;
	for(i=1;i<=NumberofElement;i++)
	{
		saveProductInfo+="|";
		$("#tempDiv_"+i).children("td").each(function() {
			    saveProductInfo+=$(this).text()+"^";
		});
	}
		

	$.ajax(
			{
				type : "POST",
				url : getBaseURL() + "/ProdCatalogMgmtServlet",
				data :
				{
					'Action' : 'AddItem',
					'cmp_regn_key' : regnkey,
					'values' :saveProductInfo,
				},
				cache : false,
				success : function(data)
				{
					hideAjaxLoader();

					if (data.result == "success")
					{
						ShowToast(data.message, 2000);

						lastSelectedRowStatus = "";

						SM.Corporate.ProductCatalog.Table.fnClearTable();

						SM.Corporate.ProductCatalog.getAllCatalogs();
						
						SM.Corporate.ProductCatalog.ResetAddForm();
						
					}

					else
					{
						SM.Corporate.ProductCatalog.setErrorInfo(data.message);
					}
				},
				error : function(xhr, textStatus, errorThrown)
				{
					hideAjaxLoader();
					ShowToast("Unexcepted error. Please try again.");
				}
			});
};


SM.Corporate.ProductCatalog.ResetAddForm=function()
{
	var i=0;
	for(i =1;i<=NumberofElement; i++)
	{
		removeTheTempElement("tempDiv_"+i);
	}
	
	NumberofElement=0;
};


function removeTheTempElement(removingID)
{
	$("#"+removingID).remove();
}

SM.Corporate.ProductCatalog.clearErrorInfo = function()
{
	$('#ProductCatalogErrorID').text("");
};

SM.Corporate.ProductCatalog.setErrorInfo = function(Error)
{
	$('#ProductCatalogErrorID').text(Error);
};

SM.Corporate.ProductCatalog.currencyList = new Array();
SM.Corporate.ProductCatalog.quantityUnitsList = new Array();

SM.Corporate.ProductCatalog.getCurrency = function()
{
	$.ajax(
	{
		type : "POST",
		url : getBaseURL() + "/TransConfigServlet",
		data :
		{
			'TransConfigType' : 'GetCurrencies',
		},
		dataType : 'json',
		cache : false,
		success : function(data)
		{
			if (data.result == "success")
			{

				$.each(data, function(key, value)
				{
					if (value != "success")
					{
						SM.Corporate.ProductCatalog.currencyList.push(key);

						if (SM.Corporate.ProductCatalog.currencyList.length > 0)
						{
							$("#add_currency").text(SM.Corporate.ProductCatalog.currencyList[0]);
						}
					}

				});
				
				SM.Corporate.ProductCatalog.currencyList.sort();

			}
			else
			{

			}

		},
		error : function(xhr, textStatus, errorThrown)
		{

		}
	});
};

SM.Corporate.ProductCatalog.showCurrencyList = function(divId, TargetListID)
{

	$(TargetListID).toggle();

	$(TargetListID).empty();

	for ( var i = 0; i < SM.Corporate.ProductCatalog.currencyList.length; i++)
	{
		var currency = SM.Corporate.ProductCatalog.currencyList[i];

		var currencyUnitDiv = "";

		currencyUnitDiv = '<div class="currency_name" id="' + currency + '_' + divId + '">' + currency + '</div>';

		$(TargetListID).append(currencyUnitDiv);

		$("#" + currency + "_" + divId).on('click', function(event)
		{
			
			
			var divid = event.target.id;

			var idSplitArr = divid.split("_");

			if (idSplitArr.length > 0)
			{
				var quantity = idSplitArr[0];

				$("#" + divId).text(quantity);

				$(TargetListID).hide();

			}

		});
	}
};

/* This method is used to get the quantity units from server */
SM.Corporate.ProductCatalog.getQuantityUnitList = function()
{
	$.ajax(
	{
		type : "POST",
		url : getBaseURL() + "/TransConfigServlet",
		data :
		{
			'TransConfigType' : 'QuantityType',
		},
		dataType : 'json',
		cache : false,
		success : function(data)
		{
			if (data.result == "success")
			{

				$.each(data, function(key, value)
				{
					if (value != "success")
					{
						SM.Corporate.ProductCatalog.quantityUnitsList.push(key);
						if (SM.Corporate.ProductCatalog.quantityUnitsList.length > 0)
						{
							$("#add_quantity_unit").text(SM.Corporate.ProductCatalog.quantityUnitsList[0]);
						}
					}

				});
				
				SM.Corporate.ProductCatalog.quantityUnitsList.sort();

			}
			else
			{

			}

		},
		
		error : function(xhr, textStatus, errorThrown)
		{

		}
	});
};

SM.Corporate.ProductCatalog.showQuantityList = function(divId, TargetListID)
{

	$(TargetListID).toggle();
	$(TargetListID).empty();

	for ( var i = 0; i < SM.Corporate.ProductCatalog.quantityUnitsList.length; i++)
	{
		var quantity = SM.Corporate.ProductCatalog.quantityUnitsList[i];

		var quantityUnitDiv = "";

		quantityUnitDiv = '<div class="quan_item" id="' + quantity + '_' + divId + '">' + quantity + '</div>';

		$(TargetListID).append(quantityUnitDiv);

		$("#" + quantity + "_" + divId).on('click', function(event)
		{

			var divid = event.target.id;

			var idSplitArr = divid.split("_");

			if (idSplitArr.length > 0)
			{
				var quantity = idSplitArr[0];

				$("#" + divId).text(quantity);

				$(TargetListID).toggle();

			}

		});
	}

};

$(document).ready(function()
{
	
	$("#helpbtn").click(function(){
		 location.href='Views/Corporate/js/Product_catalog_template.csv';
	});
	
	$('.tooltip').tooltipster(
			{
					interactive: false
	});
	
	hideAjaxLoader();
	
	$("#catalog_content").show();
	
	$("#content_loader").hide();

	SM.Corporate.ProductCatalog.loadValidator();

	SM.Corporate.ProductCatalog.getAllCatalogs();

	$(document).ready(function()
	{
		$('#ProductCatalogList').dataTable();
	});

	SM.Corporate.ProductCatalog.initDataTable();

	$("#productcatalog_content").show();
	$("#newcatalog_content").hide();
	$("#CatalogImport_content").hide();
	$('#catalog_details').click(function()
	{
		SM.Corporate.ProductCatalog.showProductCatalogTab();
		SM.Corporate.ProductCatalog.clearErrorInfo();
	});
	$('#new_items').click(function()
	{
		SM.Corporate.ProductCatalog.showNewCatalogTab();
		SM.Corporate.ProductCatalog.clearErrorInfo();
	});

	$('#add_csv_items').click(function()
	{
		SM.Corporate.ProductCatalog.showCSVImport();
		SM.Corporate.ProductCatalog.clearErrorInfo();
	});

	$("#catalog_import_upload_control").change(function()
	{
		var ext = this.files[0].name.split('.').pop().toLowerCase();
		
		if ($.inArray(ext, [ 'csv' ]) == -1)
		{

			alert('Only CSV Format Valid!');
			input.files[0] = "";
			return		

		}

		$("#catalog_import_text").val(this.files[0].name);
		
		$("#Upload_Result").html("");

	});

	$("#exp_never").click(function()
	{
		if ($('#exp_never').is(':checked'))
		{
			$("#exp_end").attr("checked", false);
			$('#policies_spinner').val(0);
			// $("#policies_spinner").attr("disable",true);
		}
		else
		{
			// $("#policies_spinner").attr("disable",false);
		}
	});

	$("#exp_end").click(function()
	{
		if ($('#exp_end').is(':checked'))
		{
			$("#exp_never").attr("checked", false);

		}
	});

	SM.Corporate.ProductCatalog.getCurrency();
	SM.Corporate.ProductCatalog.getQuantityUnitList();

	$("#add_currency").click(function( e )
	{
		e.stopPropagation();
		
		SM.Corporate.ProductCatalog.showCurrencyList(this.id, "#add_currency_add_currency");
	});

	$("#add_quantity_unit").click(function( e )
	{
		
		e.stopPropagation();
		
		SM.Corporate.ProductCatalog.showQuantityList(this.id, "#units_add_quantity_unit");
	});
	
	
});


SM.Corporate.ProductCatalog.uploadtheCSV = function ()
{
	if($("#catalog_import_upload_control").val() == "")
		{
		ShowToast("Upload the file First");
			return;
		}
	
	showAjaxLoader();
		
	var formdata = new FormData();
	formdata.append('Action', 'CSVImportor');
	formdata.append('cmpRegnKey',  $("#regnkey").val());
	formdata.append("csv_data", file_upload_form_1.catalog_import_upload_control.files[0]);
	
	
	$.ajax(
			{
				type : "POST",
				url : getBaseURL() + "/ProdCatalogMgmtServlet",
				data : formdata,
				contentType : false,
				processData : false,
				success : function(ResultObj)
				{
					hideAjaxLoader();
						if(ResultObj.result == "success")
						{
							
							ShowToast( "File uploaded Successfully", 2000 );
							
							file_upload_form_1.catalog_import_upload_control.files[0] = null;
							$("#catalog_import_upload_control").val("");
							$("#catalog_import_text").val("");
							$("#Upload_Result").html(ResultObj.message);
							
							SM.Corporate.ProductCatalog.Table.fnClearTable();
							SM.Corporate.ProductCatalog.getAllCatalogs();
						}	
						else
						{
							$("#Upload_Result").html("Upload Failed Pl try again");
						}
				},
				error : function()
				{
					hideAjaxLoader();
					$("#Upload_Result").html("Upload Failed Pl try again");
				}
			});
	
};
