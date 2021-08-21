<%@page import="db.utils.DBConnect"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />

    </head>
    <body>

        <div style="display: none;" id="policies_popup" class="Custome_Popup_Window">

            <table>
                <tr>
                    <td style="vertical-align: middle;">
                        <div class="Popup_Outline_NewGroup Cus_Popup_Outline popuplayout">
                            <div class="Popup_Tilte_NewGroup Gen_Popup_Title ">
                                User Settings
                                <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
                            </div>
                            <div style="width:100%;height:auto;z-index:999999;" id="user_settings">
                            </div>
                        </div>

                    </td>
                </tr>
            </table>

        </div>
        <input type="hidden" id="emailId" />
        <input type="hidden" id="rowno" />

        <script type="text/javascript">
            $(function() {

            $(".Gen_Cus_Popup_Close").click(function() {
            $("#policies_popup").hide();
            });
            //$("#rename_cancel").click(function(){$("#edit_group_popup").hide();});

            var spinner = $("#policies_spinner").spinner();
            $('#policies_spinner').spinner('option', 'min', 0);
            $('#policies_spinner').spinner('option', 'max', 2013);
            $('#policies_spinner').val("0");
            }
            );
        </script>
    </body>
</html>