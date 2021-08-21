/**
 * 
 */

var monthArr = [ "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" ];

var quarterArr = ["I Quarter", "II Quarter", "III Quarter", "IV Quarter"];

var xMin = 1;

var xMax = 0;

var xAxisLbl = "";

var yAxisLbl = "";

$(document).ready(function()
{
	hideAjaxLoader();
	
	
	
	$("#time_based_report_tab").click(function(){
		
		$("#divtimebasedreport").show();
		$("#divcompanybasedreport").hide();
		
		$("#time_based_report_tab").removeClass("main_tab_unselect");
		$("#time_based_report_tab").addClass("main_tab_select");
		
		$("#company_based_report_tab").removeClass("main_tab_select");
		$("#company_based_report_tab").addClass("main_tab_unselect");
		
	});
	
	$("#company_based_report_tab").click(function(){
		
		$("#divcompanybasedreport").show();
		$("#divtimebasedreport").hide();
		
		$("#company_based_report_tab").removeClass("main_tab_unselect");
		$("#company_based_report_tab").addClass("main_tab_select");
		
		$("#time_based_report_tab").removeClass("main_tab_select");
		$("#time_based_report_tab").addClass("main_tab_unselect");
		
	});
	
	$("#time_report_chart").click(function(){
		
		$("#time_report_chart").removeClass("ReportTabUnSelect");
		$("#time_report_chart").addClass("ReportTabSelected");
		
		$("#time_report_table").removeClass("ReportTabSelected");
		$("#time_report_table").addClass("ReportTabUnSelect");	
		
		$("#barchart1").show();
		$("#piechart1").show();
		$("#table1").hide();
		
	});
	
	$("#time_report_table").click(function(){		

		$("#time_report_table").removeClass("ReportTabUnSelect");
		$("#time_report_table").addClass("ReportTabSelected");
		
		$("#time_report_chart").removeClass("ReportTabSelected");
		$("#time_report_chart").addClass("ReportTabUnSelect");
		
		$("#barchart1").hide();
		$("#piechart1").hide();
		$("#table1").show();
		
	});
	
	
	
	$("#cus_report_chart").click(function(){
		
		$("#cus_report_chart").removeClass("ReportTabUnSelect");
		$("#cus_report_chart").addClass("ReportTabSelected");
		
		$("#cus_report_table").removeClass("ReportTabSelected");
		$("#cus_report_table").addClass("ReportTabUnSelect");
		
		$("#barchart2").show();
		$("#piechart2").show();
		$("#table2").hide();
		
	});
	
	$("#cus_report_table").click(function(){		

		$("#cus_report_table").removeClass("ReportTabUnSelect");
		$("#cus_report_table").addClass("ReportTabSelected");
		
		$("#cus_report_chart").removeClass("ReportTabSelected");
		$("#cus_report_chart").addClass("ReportTabUnSelect");
		
		$("#barchart2").hide();
		$("#piechart2").hide();
		$("#table2").show();
		
	});
	
	
	
	$('#time_report_type').live("change",function() 
	{
		   if( $("#time_report_type").val() == "Daily" )
		   {
			   $("#time_year_div").show();
			   
			   $("#time_month_div").show();
		   }
		   else if( $("#time_report_type").val() == "Monthly" )
		   {
			   $("#time_year_div").show();
			   
			   $("#time_month_div").hide();
		   }
		   else if( $("#time_report_type").val() == "Quarterly" )
		   {
			   $("#time_year_div").show();
			   
			   $("#time_month_div").hide();
		   }
		   else if( $("#time_report_type").val() == "Yearly" )
		   {
			   $("#time_year_div").hide();
			   
			   $("#time_month_div").hide();
		   }
			    
	});
	
	
	$('#cus_report_type').live("change", function()
	{
		if ($("#cus_report_type").val() == "Daily") 
		{
			$("#cus_year_div").show();

			$("#cus_month_div").show();
			
			$("#start_date").hide();
			
			$("#end_date").hide();
		} 
		else if ($("#cus_report_type").val() == "Monthly") 
		{
			$("#cus_year_div").show();

			$("#cus_month_div").hide();
			
			$("#start_date").hide();
			
			$("#end_date").hide();
		} 
		else if ($("#cus_report_type").val() == "Period") 
		{
			$("#cus_year_div").hide();

			$("#cus_month_div").hide();
			
			$("#start_date").show();
			
			$("#end_date").show();
		} 
	});
	
	$("#time_based_report_btn").click( generateTimeBasedReport );
	
	$("#cus_based_report_btn").click( generateCusBasedReport );
	
	/*for (var i = new Date().getFullYear(); i > 2013; i--)
	{
	    $('#time_year').append($('<option />').val(i).html(i));
	    $('#cus_year').append($('<option />').val(i).html(i));
	}*/
	
	 $('select.selectbox').customSelect();
	
});



