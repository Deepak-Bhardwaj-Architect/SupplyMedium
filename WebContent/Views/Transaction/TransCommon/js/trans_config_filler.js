$(document).ready(function() 
{
	getQuantityUnits();
	
	getCurrency();
	
	getTaxPercentage();	
});

var quantityUnits = new Array();

var currencyList = new Array();

var mainTaxPer = 0.0;

/* this method is used to get the quantity units from server */
function getQuantityUnits()
{
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/TransConfigServlet",
		data : {
			'TransConfigType' : 'QuantityType',
		},
		dataType : 'json',
		cache : false,
		success : function(data) {
			if( data.result == "success")
			   {
				
					$.each(data, function(key, value) 
					{
						if( value != "success")
						{
							quantityUnits.push(key);
						}
							
					});
					
					quantityUnits.sort();
					
			   }
			   else
			   {
				  
			   }

			
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			
		}
	});
}

/* this method is used to get the currency list from server */
function getCurrency()
{
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/TransConfigServlet",
		data : {
			'TransConfigType' : 'GetCurrencies',
		},
		dataType : 'json',
		cache : false,
		success : function(data) {
			if( data.result == "success")
			   {
					currencyList[0] = "AAA";
				
					$.each(data, function(key, value) 
					{
						if( value != "success")
						{
							if( key != "USD")
								currencyList.push(key);
						}
							
					});
					
					currencyList.sort();
					
					currencyList[0] = "USD";
					
			   }
			   else
			   {
				  
			   }

			
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			
		}
	});
}

/* this method is used to get the tax percentage from server */
function getTaxPercentage()
{
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/SMConfigServlet",
		dataType : 'json',
		cache : false,
		success : function(data) {
			if( data.result == "success")
			   {
				 mainTaxPer = data.taxPercentage;
			   }
			   else
			   {
				  
			   }

			
		},
		error : function(xhr, textStatus, errorThrown) {
			
		}
	});
}


function showQuantityUnits(  )
{
	var divId = this.id;
	
	$("#units_"+divId).toggle();
	
	$("#units_"+divId).empty();
	
	for( var i=0;i<quantityUnits.length;i++ )
	{
		var quantity = quantityUnits[i];
		
		var quantityUnitDiv = "";
		
		quantityUnitDiv = '<div class="quan_item" id="'+quantity+'_'+divId+'">'+quantity+'</div>';
				
		$("#units_"+divId).append(quantityUnitDiv);
		
		$("#"+quantity+"_"+divId).on('click', function(event) {

			var divid = event.target.id;

			var idSplitArr = divid.split("_");
			
			if (idSplitArr.length > 0) 
			{
				var quantity = idSplitArr[0];
				
				

				$("#"+divId).text( quantity );
				
				$("#units_"+divId).toggle();

			}

		});
	}
}


/* this method is used to Currency list drop down */

function showCurrencyList()
{
	var divId = this.id;
	
	$("#currency_"+ divId).toggle();
	
	$("#currency_"+divId).empty();
	
	//$("#currency_"+divId).mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
	
	for( var i=0;i<currencyList.length;i++ )
	{
		var currency = currencyList[i];
		
		var currencyUnitDiv = "";
		
		currencyUnitDiv = '<div class="currency_name" id="'+currency+'_'+divId+'">'+currency+'</div>';
		
		//$("#currency_"+divId+" .mCSB_container").append(connStr);
		
		$("#currency_"+divId).append(currencyUnitDiv);
		
		$("#"+currency+"_"+divId).on('click', function(event) {

			var divid = event.target.id;

			var idSplitArr = divid.split("_");

			if (idSplitArr.length > 0) 
			{
				var quantity = idSplitArr[0];

				$("#"+divId).text( quantity );
				
				$("#currency_"+divId).hide();

			}

		});
	}
	
	$( "#currency_"+divId ).mCustomScrollbar("update");
}

