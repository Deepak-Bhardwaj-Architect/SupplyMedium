<%@page import="supply.medium.marketing.database.MarketingPersonMaster"%>
<%@page import="supply.medium.marketing.bean.MarketingPersonBean"%>
<%@page import="supply.medium.marketing.bean.MarketingAssociateBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="supply.medium.marketing.database.MarketingAssociateMaster"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="files/jquery-ui-1.10.0.custom.css">
        <link rel="stylesheet" href="files/jquery-ui-1.9.2.custom_spinner.css">
        <link rel="stylesheet" href="files/commonlayout.css">
        <link rel="stylesheet" href="files/elements.css">
        <link rel="stylesheet" href="files/Custome_Buttons.css">
        <link rel="stylesheet" href="files/jquery.mCustomScrollbar.css">
        <link rel="stylesheet" href="files/user_home.css">
        <link rel="stylesheet" href="files/dilbag.css">
        <script type="text/JavaScript" src="files/jquery-1.9.0.js"></script>  
        <script type="text/JavaScript" src="files/dilbag.js"></script>           
        <title>Supply Medium</title>  
        <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE" />
        <META HTTP-EQUIV="Expires" CONTENT="-1" />
        <META HTTP-EQUIV=3D"Cache-Control" content=3D"no-cache">
        <META HTTP-EQUIV=3D"Cache-Control" content=3D"no-store">
        <META HTTP-EQUIV=3D"Expires" CONTENT=3D"-1">
        <META HTTP-EQUIV=3D"Expires" CONTENT=3D"no-cache">
        <META HTTP-EQUIV=3D"Expires" CONTENT=3D"Mon, 01 Jan 1970 23:59:59 GMT">
        <META HTTP-EQUIV=3D"Pragma" Cache-Control=3D"no-cache">
        <link rel="stylesheet" href="files/userheader.css">
        <link rel="stylesheet" href="files/notifi_dropdown.css">
        <link rel="stylesheet" href="files/ResetCSS.css">
        <link rel="stylesheet" href="files/Cus_Menu.css">
        <link rel="stylesheet" href="files/commonlayout.css">
        <link rel="stylesheet" href="files/elements.css">
        <link rel="stylesheet" href="files/usermgmt.css">
        <link rel="stylesheet" href="files/jquery-ui-1.10.0.custom.css">
        <link rel="stylesheet" href="files/tablestyle.css">
        <link rel="stylesheet" href="files/Custome_Buttons.css">
        <link rel="stylesheet" href="files/usermgmt_popup.css">
        <link rel="stylesheet" href="files/Custome_Popup.css">
        <link rel="stylesheet" href="files/popup_warning.css">
        <link rel="stylesheet" href="files/Cus_Toast.css">
        <link rel="stylesheet" href="files/ajax_loader.css">
        <link rel="stylesheet" href="files/Custome_Popup.css">
        <link rel="stylesheet" href="files/footer.css">
        <link rel="stylesheet" href="files/term.css">
        <link rel="stylesheet" href="files/privacy.css">

    </head>
    <body onload="lst_id = 'admn_usrs'">
        <%@include file="header.jsp" %>
        <div class="contentcontainer admin_container">            
            <div class="admin_users" id="admn_ad_usr" style="display: block;">
                <h1 align="center">Marketing Associates (Marketing Persons)<br/><br/></h1>                
                <div class="admin_add_user_box" style="width:95%"> 
                    <table border="1">
                        <tr>
                            <th style="font-weight:bold;text-align:center;padding:5px;background:#d5d5d5;" width="150">Date</th>
                            <th style="font-weight:bold;text-align:center;padding:5px;background:#d5d5d5;" width="150">Name</th>
                            <th style="font-weight:bold;text-align:center;padding:5px;background:#d5d5d5;" width="150">Contact no</th>
                            <th style="font-weight:bold;text-align:center;padding:5px;background:#d5d5d5;" width="200">Email</th>
                            <th style="font-weight:bold;text-align:center;padding:5px;background:#d5d5d5;" width="300">Address</th>
                            <th style="font-weight:bold;text-align:center;padding:5px;background:#d5d5d5;" width="150">Account</th>
                            <th style="font-weight:bold;text-align:center;padding:5px;background:#d5d5d5;" width="150">Associate Under</th>
                        </tr>
                        <%
                            ArrayList al=MarketingAssociateMaster.showAllAssociatesOfMarketingPerson();
                            MarketingAssociateBean mab=null;
                            MarketingPersonBean mpb=null;
                            String style="";
                            
                            String under="";
                            for(int i=0;i<al.size();i++)
                            {
                                mab=(MarketingAssociateBean)al.get(i);
                                mpb=MarketingPersonMaster.showByKey(mab.getMarketing_associate_key());
                                if(i%2==0)
                                    style="background:#f0f0f0;";
                                else
                                    style="background:#ffffff;";
                                
                                if(mab.getMarketing_person_key().equals("0"))
                                    under="Supply Medium Inc.";
                                else
                                {
                                    under=MarketingPersonMaster.showByKey(mab.getMarketing_person_key()).getMarketing_person_name();
                                }
                        %>
                        <tr style="<%=style%>">
                            <td style="padding:5px;"><%=mpb.getDate_of_joining()%></td>
                            <td style="padding:5px;"><%=mpb.getMarketing_person_name()%></td>
                            <td style="padding:5px;"><%=mpb.getContact_number()%></td>
                            <td style="padding:5px;"><%=mpb.getE_mail()%></td>
                            <td style="padding:5px;"><%=mpb.getAddress_details()%></td>
                            <td style="padding:5px;"><%=mpb.getAccount_details()%></td>
                            <td style="padding:5px;"><%=under%></td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body></html>