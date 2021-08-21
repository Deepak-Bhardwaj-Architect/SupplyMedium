<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0048)/SupplyMedium/user_home.jsp -->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
        <link rel="stylesheet" href="inside/jquery-ui-1.9.2.custom_spinner.css">
        <link rel="stylesheet" href="inside/commonlayout.css">
        <link rel="stylesheet" href="inside/elements.css">
        <link rel="stylesheet" href="inside/Custome_Buttons.css">
        <link rel="stylesheet" href="inside/jquery.mCustomScrollbar.css">
        <link rel="stylesheet" href="inside/user_home.css">
        <link rel="stylesheet" href="inside/dilbag.css">
        <!-- Chat JS style -->
        <link rel="stylesheet" type="text/css" href="inside/jquery.chatjs.css">
        <script type="text/JavaScript" src="inside/SMNamespace.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery-1.9.0.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery-ui-1.10.0.custom.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery-ui-1.9.2.custom_spinner.js">
        </script>
        <script type="text/JavaScript" src="inside/filterlist.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.customSelect.js">
        </script>
        <script type="text/javascript" src="inside/jquery.mCustomScrollbar.js">
        </script>
        <script src="inside/jquery.mousewheel.min.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.tooltipster.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.dataTables.js">
        </script>
        <script type="text/JavaScript" src="inside/common.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.validate.js">
        </script>
        <script type="text/JavaScript" src="inside/additional-methods.js">
        </script>
        <script type="text/JavaScript" src="inside/dropdownfiller.js">
        </script>
        <script type="text/JavaScript" src="inside/SMNamespace(1).js">
        </script>
        <script type="text/JavaScript" src="inside/footer.js">
        </script>
        <script type="text/JavaScript" src="inside/ajax_loader.js">
        </script>
        <!-- ChatJS and dependencies -->
        <script src="inside/jquery.chatjs.longpollingadapter.js" type="text/javascript">
        </script>
        <script src="inside/jquery.autosize.min.js" type="text/javascript">
        </script>
        <script src="inside/jquery.activity-indicator-1.0.0.min.js" type="text/javascript">
        </script>
        <script src="inside/jquery.chatjs.js" type="text/javascript">
        </script>
        <script type="text/JavaScript" src="inside/user_home.js">
        </script>
        <script type="text/JavaScript" src="inside/dilbag.js">
        </script>
        <script>
            $(function () {
                $("#start_date").datepicker({dateFormat: 'yy-mm-dd'});
                $("#end_date").datepicker({dateFormat: 'yy-mm-dd'});
            });
        </script>
        <style>
            body 
            {
                font-size: 80%;
            }
        </style>
        <title>Supply Medium</title>
        <!--<script>
        Usr_anlys('Admin');    
        </script>-->
    </head>
    <body onLoad="lockUnlock('webkrit_content_loader');">
    <link rel="stylesheet" href="inside/userheader.css">
    <link rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
    <div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
        <link rel="stylesheet" href="inside/commonlayout.css">
        <link rel="stylesheet" href="inside/Custome_Buttons.css">
        <link rel="stylesheet" href="inside/elements.css">
        <link rel="stylesheet" href="inside/productcatalog.css">
        <link rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
        <link rel="stylesheet" href="inside/Custome_Popup.css">
        <link rel="stylesheet" href="inside/Custome_Buttons.css">
        <link rel="stylesheet" href="inside/tablestyle.css">
        <link rel="stylesheet" href="inside/jRating.jquery.css">
        <link rel="stylesheet" href="inside/dashboard.css">
        <link rel="stylesheet" href="inside/jplot/jquery.jqplot.min.css">
        <div class="pagetitlecontainer">
            <div class="pagetitle">Dashboard</div>
        </div>
        <div class="page">
            <div class="dashboardcontainer contentcontainer" style="min-height:834px;">

                <div class="main_tab_container">  <!-- This is the main tab bar container -->

                    <div id="time_based_report_tab" class="main_tab_unselect">  <!-- This is the Request Queue tab -->
                        <a href="homeDashboard.jsp" style="color:#fff;">Time Based Report</a></div>

                    <div id="company_based_report_tab" class="main_tab_select">	<!-- This is the Add Buyer tab -->
                        <a href="homeDashboardCR.jsp" style="color:#fff;">Company Based Report</a></div>

                </div>
                <div style="clear: both;"></div>
                <div class="main_tab_sep"> <!-- This is the seperator div  -->
                </div>

                <div id="divcompanybasedreport" style="display: block;"> <!-- Company Based Report -->

                    <div class="chartTitlebuttons">

                        <div style="float:left;margin-top:5px;margin-right:10px;">
                            Select Time Period : 
                        </div>
                        <div style="float:left;margin-top:5px;margin-right:10px;">
                            From Date 
                        </div>
                        <input type="text" class="textbox" id="start_date" style="float: left; margin-top: 0px; margin-right: 10px; display: block;" readonly="">
                        <div style="float:left;margin-top:5px;margin-right:10px;">
                            To Date 
                        </div>
                        <input type="text" class="textbox" id="end_date" style="float: left; margin-top: 0px; margin-right: 10px; display: block;" readonly="">
                        <input type="button" style="margin-left:20px;" class="gen-btn-blue generate_btn" value="Generate" id="cus_based_report_btn">
                    </div>

                    <div class="generate_btn_container">
                    </div>

                    <div class="report_type">
                        <div id="time_report_table" class="singleLine ReportTabUnSelect"><a href="homeDashboardCR.jsp" style="color:#000;">Report<a/></div>
                        <div id="time_report_chart" class="singleLine ReportTabSelected"><a href="homeDashboardCC.jsp" style="color:#000;">Chart</a></div>

                        <div id="chart1" style="margin:120px -150px;display:none; width:500px; height:500px;"></div>
                        <!--<pre class="code brush:js"></pre>-->



                        <script type="text/javascript">$(document).ready(function () {
                                $.jqplot.config.enablePlugins = true;
                                var s1 = [30, 55, 15];
                                var ticks = ['hostkrit', 'edukrit', 'jobskrit'];

                                plot1 = $.jqplot('chart1', [s1], {
                                // Only animate if we're not using excanvas (not in IE 7 or IE 8)..
                                animate: !$.jqplot.use_excanvas,
                                    seriesDefaults: {
                                        renderer: $.jqplot.BarRenderer,
                                    pointLabels: {show: true}
                                    },
                                        axes: {
                                        xaxis: {
                                    renderer: $.jqplot.CategoryAxisRenderer,
                                    ticks: ticks
                                        }
                                            },
                                    highlighter: {show: false}
                                            });

                                    $('#chart1').bind('jqplotDataClick',
                                    function (ev, seriesIndex, pointIndex, data) {
                                        $('#info1').html('series: ' + seriesIndex + ', point: ' + pointIndex + ', data: ' + data);
                                        }
                                            );
                            });</script>
<!--                        <div id="pie1" style="margin:120px 0; width:500px; height:500px;"></div>
                        <pre class="code brush:js"></pre>



                        <script type="text/javascript">$(document).ready(function () {
                                var plot1 = $.jqplot('pie1', [[['hostkrit', 30], ['edukrit', 55], ['jobskrit', 15]]], {
                                gridPadding: {top: 0, bottom: 38, left: 0, right: 0},
                                    seriesDefaults: {
                                    renderer: $.jqplot.PieRenderer,
                                        trendline: {show: false},
                                        rendererOptions: {padding: 8, showDataLabels: true}
                                    },
                                        legend: {
                                        show: true,
                                        placement: 'outside',
                                    rendererOptions: {
                                        numberRows: 1
                                        },
                                        location: 's',
                                            marginTop: '15px'
                                    }
                                        });
                                        });</script>-->
                    </div>

                    <div class="clearboth"></div>
                    <div class="charTitle"> 
                    </div>
                    <div class="clearboth"></div>


                </div>

            </div>
        </div>
        <title>Toast Window </title>
        <link rel="stylesheet" href="inside/Cus_Toast.css">
        <!--  <input type="button" id="Open_Toast" value="AAAAAAAAAAAAAAAAAAAA"/>-->
        <div id="Toast_Window" style="display:none;">
            <p class="Toast_Data">
            </p>
        </div>
        <%@include file="_footer.jsp"%>
        <div>
        </div>        
    </div>
    <%@include file="_invite.jsp"%>
    <script type="text/JavaScript" src="inside/restrict_menu.js"></script>
    <script type="text/JavaScript" src="inside/jplot/jquery.customSelect.js"></script>
    <script class="include" type="text/javascript" src="inside/jplot/jquery.jqplot.min.js"></script>
    <script type="text/javascript" src="inside/jplot/shCore.min.js"></script>
    <script type="text/javascript" src="inside/jplot/shBrushJScript.min.js"></script>
    <script type="text/javascript" src="inside/jplot/shBrushXml.min.js"></script>
    <!-- Additional plugins go here -->

    <script class="include" type="text/javascript" src="inside/jplot/jqplot.barRenderer.min.js"></script>
    <script class="include" type="text/javascript" src="inside/jplot/jqplot.pieRenderer.min.js"></script>
    <script class="include" type="text/javascript" src="inside/jplot/jqplot.categoryAxisRenderer.min.js"></script>
    <script class="include" type="text/javascript" src="inside/jplot/jqplot.pointLabels.min.js"></script>
</script>
</body>
</html>