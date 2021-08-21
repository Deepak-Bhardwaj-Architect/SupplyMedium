var piechart1, barchart1;

function generateTimeBasedReport()
{
$("#no_records").hide();
	
	$("#barchart1").hide();
	if( barchart1 )
		 barchart1.destroy();
	
	$("#piechart1").hide();
	if( piechart1 )
		 piechart1.destroy();
	
	$("#table1").hide();
	$("#table1").empty();
	
	var reportType = $("#time_report_type").val();
	var month     = $("#time_month").val();
	var year	  = $("#time_year").val();
	var regnKey   = $("#regnkey").val();
	
	var filterDate = "01-"+month+"-"+year;
	
	
	
	yAxisLbl = "Amount";
	
	if( reportType == "Daily" )
	{
		xMax = 31;
		
		xMin = 1;
		
		xAxisLbl = "Date";
		
	}
	else if( reportType == "Monthly" )
	{
		xMax = 12;
		
		xAxisLbl = "Month";
		
		xMin = 1;
	}
	else if( reportType == "Quarterly" )
	{
		xMax = 4;
		
		xMin = 1;
		
		xAxisLbl = "Quarter";
	}
	else if( reportType=="Yearly" )
	{
		xMax = "";
		
		xMin = "";
		
		xAxisLbl = "Year";
	}
	
	//alert("xMax="+xMax);
	
	$.ajax(
			{
				type : 'POST',
				url : getBaseURL() + '/TimeBasedReportServlet',
				data :
				{
					'RegnKey':regnKey,
					'RequestType': reportType,
					'FilterDate':filterDate
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
							
							if( $("#time_report_table").hasClass("ReportTabSelected"))
							{
								$("#table1").show();
							}
							else 
							{
								$("#piechart1").show();
								$("#barchart1").show();
							}
							
							generateTimeBarChart( recordArr, "barchart1" );
							
							generateTimePieChart( recordArr, "piechart1" );
							
							fillTimeTable( recordArr, "table1");
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

function generateTimeBarChart( recordArr, divId )
{
	
	var dataArr = new Array();
	
	var yearMax = 0;
	
	var yearMin = 0;
	
	var reportType = $("#time_report_type").val();
	
	for( var i = 0; i<recordArr.length;i++ )
	{
		var record = recordArr[ i ];
		
		var amount = record.transactionAmount;
		
		var currencyType = record.currencyType;
			
		var lbl = record.label;
		
		infoArr = lbl.split("-");
		
		if(infoArr.length == 3 )
			lbl = infoArr[2];
		
		/*if( reportType == "Monthly" )
		{
			lbl = monthArr[ parseInt(lbl)-1 ];
			
			//alert(lbl);
		}
		else if( reportType == "Quarterly" )
		{
			lbl = quarterArr[ parseInt(lbl)-1 ];
		}*/
		 if( reportType == "Yearly" )
		{
			if( i==0 )
			{
				yearMax = parseInt(lbl);
				
				yearMin = parseInt(lbl);
			}
			else
			{
				yearMax = yearMax <= parseInt(lbl)?parseInt(lbl):yearMax;
				
				yearMin = yearMin >= parseInt(lbl)?parseInt(lbl):yearMin;
			}
				
		}
		
		
		
		var arr = new Array( lbl, parseInt( amount ) );
		
		dataArr.push( arr );
		
		 
	}
	
	 if( reportType == "Yearly" )
	{
		 xMin = yearMin;
		 xMax = yearMax;
	}
	
	if( barchart1 )
		 barchart1.destroy();

	 barchart1 = $.jqplot( divId, [ dataArr ], 
	{
		// Turns on animatino for all series in this plot.
		animate : true,
		// Will animate plot on calls to
		// plot1.replot({resetAxes:true})
		animateReplot : true,
		cursor : 
		{
			show : true,
			zoom : true,
			looseZoom : true,
			showTooltip : false
		},
		series : [ 
		         {
			pointLabels : 
			{
				show : true
			},
			renderer : $.jqplot.BarRenderer,
			showHighlight : false,
			rendererOptions : 
			{
				// Speed up the animation a little bit.
				// This is a number of milliseconds.
				// Default for bar series is 3000.
				animation :
				{
					speed : 2500
				},
				barWidth : 15,
				barPadding : -15,
				barMargin : 0,
				highlightMouseOver : false
			}
		}, 
		{
			rendererOptions : 
			{
				// speed up the animation a little bit.
				// This is a number of milliseconds.
				// Default for a line series is 2500.
				animation : 
				{
					speed : 2000
				}
			}
		} ],
		axesDefaults : {
			pad : 0
		},
		axes : {
			// These options will set up the x axis like a category
			// axis.
			xaxis : 
			{
				tickInterval : 1,
				drawMajorGridlines : false,
				drawMinorGridlines : false,
				drawMajorTickMarks : false,
				min: xMin,
				max: xMax,
				label:xAxisLbl,
				rendererOptions : {
					tickInset : 0.5,
					minorTicks : 1
				}
				
			},
			yaxis : {
				label:yAxisLbl+" ("+currencyType+")",
				tickOptions :
				{
					formatString : ""
				},
				
			}
		},
		highlighter : 
		{
			show : true,
			showLabel : true,
			tooltipAxes : 'y',
			sizeAdjust : 7.5,
			tooltipLocation : 'ne'
		}
	});
}

function generateTimePieChart( recordArr, divId )
{
	
	var dataArr = new Array();
	
	var reportType = $("#time_report_type").val();
	
	var length = 0;
	
	if( recordArr.length > 5 )
	{
		length = 5;
	}
	else
	{
		length = recordArr.length;
	}
	
	for( var i = 0; i< length ;i++ )
	{
		var record = recordArr[ i ];
		
		var amount = record.transactionAmount;
		
		var lbl 	= record.label;
		
		if( reportType == "Daily" )
		{
			infoArr = lbl.split("-");
			
			if(infoArr.length == 3 )
			{
				var year = infoArr[0];
				
				var month = parseInt( infoArr[1] );
				
				month = monthArr[ month-1 ];
				
				var date = infoArr[2];
				
				lbl = date+"-"+month+"-"+year;
			}
				
		}
		else if( reportType == "Monthly" )
		{
			lbl = monthArr[ (parseInt(lbl))-1 ];
			
		}
		else if( reportType == "Quarterly" )
		{
			lbl = quarterArr[ (parseInt(lbl))-1 ];
		}
		
		var arr = new Array( lbl, parseInt( amount ) );
		
		dataArr.push( arr );
	}
	
	
	if( piechart1 )
		piechart1.destroy();

	 
	  piechart1 = jQuery.jqplot (divId, [dataArr], 
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

function fillTimeTable( recordArr, tableId )
{
	
	$("#"+tableId ).empty();
	
	var reportType = $("#time_report_type").val();
	
	var tableHead = "";
	
	if( reportType == "Daily" )
	{
		tableHead = "Date";
			
	}
	else if( reportType == "Monthly" )
	{
		tableHead = "Month";
		
	}
	else if( reportType == "Quarterly" )
	{
		tableHead = "Quarter";
		
	}
	else if( reportType == "Yearly" )
	{
		tableHead = "Year";
	}
	
	var table = '<div class="tablehead">';
	table 	 += '<div style="float:left; width:49px; height:30px; border-right:1px solid #c8c8c8;padding-top:10px;">S.No</div>';
	table 	 += '<div style="float:left; width:149px; height:30px; border-right:1px solid #c8c8c8;padding-top:10px;">'+tableHead+'</div>';
	table 	 += '<div style="float:left; width:198px; height:30px; padding-top:10px;">Transactions Amount</div>';
	table 	 += '</div>';
	
	for( var i = 0; i<recordArr.length;i++ )
	{
		var record = recordArr[ i ];
		
		var amount = record.transactionAmount;
		
		var lbl 	= record.label;
		
		var currencyType = record.currencyType;
		
		if( reportType == "Daily" )
		{
			infoArr = lbl.split("-");
			
			if(infoArr.length == 3 )
			{
				var year = infoArr[0];
				
				var month = parseInt( infoArr[1] );
				
				month = monthArr[ month-1 ];
				
				var date = infoArr[2];
				
				lbl = date+"-"+month+"-"+year;
			}
				
		}
		else if( reportType == "Monthly" )
		{
			lbl = monthArr[ (parseInt(lbl))-1 ];
			
		}
		else if( reportType == "Quarterly" )
		{
			lbl = quarterArr[ (parseInt(lbl))-1 ];
		}
		
		
		amountArr = amount.split(".");
		
		var numeric = amountArr[0];
		
		var floatingPoint = amountArr[1];
		
		if(floatingPoint.length==1 )
		{
			floatingPoint = floatingPoint+"0";
		}
		
		amount =currencyType+" "+numeric+"."+floatingPoint;
		
		table 	 += '<div class="tabledata">';
		table 	 += '<div style="float:left; width:49px; height:30px; border-right:1px solid #c8c8c8;padding-top:10px;">'+(parseInt(i)+1)+'</div>';
		table 	 += '<div style="float:left; width:149px; height:30px; border-right:1px solid #c8c8c8;padding-top:10px;">'+lbl+'</div>';
		table 	 += '<div style="float:left; width:170px; height:30px; padding-top:10px;text-align:right;">'+amount+'</div>';
		table 	 += '</div>';

	}
	
	$("#"+tableId ).append( table );
	
}
