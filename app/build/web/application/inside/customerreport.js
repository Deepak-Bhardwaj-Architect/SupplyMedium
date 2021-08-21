var piechart2, barchart2;

function generateCusBasedReport()
{
$("#no_records").hide();
	
	$("#barchart2").hide();
	if( barchart2 )
		 barchart2.destroy();
	
	$("#piechart2").hide();
	if( piechart2 )
		 piechart2.destroy();
	
	$("#table2").hide();
	$("#table2").empty();
	
	var reportTye = $("#cus_report_type").val();
	var month     = $("#cus_month").val();
	var year	  = $("#cus_year").val();
	var regnKey   = $("#regnkey").val();
	
	var startDate = $("#start_date").val();
	var endDate   = $("#end_date").val();
	
	var filterDate = "01-"+month+"-"+year;
	
	$.ajax(
			{
				type : 'POST',
				url : getBaseURL() + '/CustomerBasedReportServlet',
				data :
				{
					'RegnKey':regnKey,
					'RequestType': reportTye,
					'FilterDate':filterDate,
					'FromDate':startDate,
					'ToDate':endDate,
				},
				cache : false,
				success : function( jsonObj )
				{

					if ( jsonObj.result == "success")
					{
						if( jsonObj.records.length > 0 )
						{
							var recordArr = new Array(jsonObj.records.length);

							recordArr = jsonObj.records;
							
							if( $("#cus_report_table").hasClass("ReportTabSelected"))
							{
								$("#table2").show();
							}
							else 
							{
								$("#piechart2").show();
								$("#barchart2").show();
							}
							
							generateCustomerBarChart( recordArr, "barchart2" );
							
							generateCustomerPieChart( recordArr, "piechart2" );
							
							fillCustomerTable( recordArr, "table2");
						}
						else
						{
							$("#no_records").show();
						}
						
					}
					else
					{
						
					}

				},
				error : function()
				{

					ExternalPageID = 0;

				}
			});
}

function generateCustomerBarChart( recordArr, divId )
{
	
	var dataArr = new Array();
	
	var cusArr = new Array();
	

	if( recordArr.length > 30 )
	{
		length = 30;
	}
	else
	{
		length = recordArr.length;
	}
	
	for( var i = 0; i<length;i++ )
	{
		var record = recordArr[ i ];
		
		var amount = record.transactionAmount;
		
		var lbl = record.label;
		
		var currencyType = record.currencyType;
		
		//var arr = new Array( lbl, parseInt( amount ) );
		
		//dataArr.push( arr );
		
		dataArr.push( parseInt(amount) );
		
		cusArr.push(lbl);
	}
	
	
	if( barchart2 )
		 barchart2.destroy();
	
	

	 barchart2 = $.jqplot( divId, [ dataArr ], 
	{
		 title: '',
         seriesDefaults:{
             renderer: $.jqplot.BarRenderer,
             rendererOptions: {
                barPadding: 1,
                barMargin: 15,
                barDirection: 'vertical',
                barWidth: 20
            }, 
            pointLabels: { show: true }
        },
        axes: {
            xaxis: {                            
                    renderer:  $.jqplot.CategoryAxisRenderer,
                    ticks: cusArr,
                    label:"Customers"
            },
            yaxis: {
            	label:"Amount"+" ("+currencyType+")",
                tickOptions: {
                    //formatString: '$%.2f'
                }
            }
        },
        highlighter: {
            sizeAdjust: 7.5
        },
        cursor: {
            show: true
        }
	});
}


function generateCustomerPieChart( recordArr, divId )
{
	
	var dataArr = new Array();
	
	if( recordArr.length > 5 )
	{
		length = 5;
	}
	else
	{
		length = recordArr.length;
	}
	
	for( var i = 0; i<length;i++ )
	{
		var record = recordArr[ i ];
		
		var amount = record.transactionAmount;
		
		var lbl 	= record.label;
		
		var arr = new Array( lbl, parseInt( amount ) );
		
		dataArr.push( arr );
	}
	
	
	
	if( piechart2 )
		piechart2.destroy();
	
	
	 
	  piechart2 = jQuery.jqplot (divId, [dataArr], 
	 { 
			      seriesDefaults: 
			      {
			        // Make this a pie chart.
			        renderer: jQuery.jqplot.PieRenderer, 
			        rendererOptions: {
			          // Put data labels on the pie slices.
			          // By default, labels show the percentage of the slice.
			          showDataLabels: true
			        }
			      }, 
			      legend: 
			      { 
			    	  show:true, 
			    	  location: 'e' 
			      },
			      grid: {
			          drawGridLines: false,        // wether to draw lines across the grid or not.
			      }
			    }
			  );
}

function fillCustomerTable( recordArr, tableId )
{
	$("#"+tableId ).empty();
	
	var table = '<div class="tablehead">';
	table 	 += '<div style="float:left; width:49px; height:30px; border-right:1px solid #c8c8c8;padding-top:10px;">S.No</div>';
	table 	 += '<div style="float:left; width:149px; height:30px; border-right:1px solid #c8c8c8;padding-top:10px;">Customers</div>';
	table 	 += '<div style="float:left; width:198px; height:30px; padding-top:10px;">Transactions Amount</div>';
	table 	 += '</div>';
	
	for( var i = 0; i<recordArr.length;i++ )
	{
		var record = recordArr[ i ];
		
		var amount = record.transactionAmount;
		
		amountArr = amount.split(".");
		
		var currencyType = record.currencyType;
		
		var numeric = amountArr[0];
		
		var floatingPoint = amountArr[1];
		
		if(floatingPoint.length==1 )
		{
			floatingPoint = floatingPoint+"0";
		}
		
		amount = currencyType+" "+numeric+"."+floatingPoint;
		
		var lbl 	= record.label;
		
		table 	 += '<div class="tabledata">';
		table 	 += '<div style="float:left; width:49px; height:30px; border-right:1px solid #c8c8c8;padding-top:10px;">'+(parseInt(i)+1)+'</div>';
		table 	 += '<div style="float:left; width:149px; height:30px; border-right:1px solid #c8c8c8;padding-top:10px;">'+lbl+'</div>';
		table 	 += '<div style="float:left; width:170px; height:30px; padding-top:10px;text-align:right;">'+amount+'</div>';
		table 	 += '</div>';

	}
	
	$("#"+tableId ).append( table );
}

