<%-- 
    Document   : forgotPassword
    Created on : Sep 18, 2014, 1:37:45 PM
    Author     : LenovoB560
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
    <HEAD>
        <META content="text/html; charset=ISO-8859-1" http-equiv="Content-Type">
        <LINK 
            rel="stylesheet" href="inside/layout.css">
        <LINK rel="stylesheet" 
              href="inside/elements.css">
        <LINK rel="stylesheet" href="inside/login.css">
        <LINK rel="stylesheet" href="inside/Custome_Buttons.css">
        <SCRIPT type="text/JavaScript" src="inside/jquery-1.9.0.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery-ui-1.10.0.custom.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.validate.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/login.js">
        </SCRIPT>
        <TITLE>Forgot Password - SupplyMedium Inc.</TITLE>
        <META name="GENERATOR" content="MSHTML 9.00.8112.16561">
    </HEAD>
    <BODY>
    <LINK rel="stylesheet" href="inside/header.css">
    <DIV id="regnheader" class="header">
        <DIV class="headercontent">
            <DIV class="logo">
                <A href="index.jsp">
                    <IMG 
                        style="border: none; margin-top: 10px;" src="inside/logo.png" 
                        width="325" height="100">
                </A>
            </DIV>
        </DIV>
    </DIV>
    <DIV class="pagetitlecontainer">
        <DIV class="pagetitle">Forgot Password</DIV>
    </DIV>
    <DIV class="logincontentcontainer">
        <DIV class="logincontent">
            <DIV style="margin-bottom: 50px;" class="title">Forgot your password?</DIV>
            <FORM id="forgetpwdfrm" method="post" name="forgetpwdfrm" action="PasswordForgot">
                <DIV style="width: 650px; height: 50px;" class="row">
                    <DIV style="width: 650px; text-align: center;" class="label">Enter your Email 
                        address</DIV>
                </DIV>
                <DIV style="height: 70px; margin-left: 100px;" class="row">
                    <DIV style="text-align: center;" class="label">Email Address</DIV>
                    <INPUT class="textbox required email" 
                           name="email" type="text" autocomplete="off">
                </DIV>
                <DIV style="height: 70px; margin-left: 280px;" class="row">
                    <INPUT class="gen-btn-Orange" name="send" value="Send" type="submit">
                </DIV>
                <DIV style="width: 100%;" id="loginerr" 
                     class="loginerr">
                </DIV>
            </FORM>
        </DIV>
    </DIV>
    <LINK rel="stylesheet" href="inside/regnfooter.css">
    <LINK rel="stylesheet" href="inside/footer.css">
    <TITLE>Insert 
        title here</TITLE>
    <DIV class="footer">
        <DIV class="footercontent">
            <DIV class="copyrights">Copyright 2015. <A style="color: rgb(255, 255, 255); text-decoration: none;"
                                                       href="http://supplymedium.com/" target="_blank">Supply Medium,
                    Inc.</A>
            </DIV>
        </DIV>
    </DIV>
    <LINK rel="stylesheet" href="inside/privacy.css">
    <TITLE>
    </TITLE>
    <SCRIPT type="text/javascript">
        $(function() {

            $(".Gen_Cus_Popup_Close").click(function() {
                $(".Custome_Popup_Window").hide();
            });
            $("#policiesdeclinebtn").click(function() {
                $(".Custome_Popup_Window").hide();
            });

        }
        );

    </SCRIPT>
    <DIV style="display: none;" id="privacy_commonpopup" class="Custome_Popup_Window">
        <DIV style="left: 50%; top: 50%; width: 671px; height: 500px; margin-top: -250px; margin-left: -335px; position: fixed;" 
             class="Cus_Popup_Outline">
            <DIV class="Popup_Tilte_NewGroup Gen_Popup_Title">
                <DIV style="padding: 0px 0px 0px 15px; float: left;">Accept privacy &amp; 
                    security policies</DIV>
                <DIV class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                </DIV>
            </DIV>
            <DIV style="height: 445px; overflow: auto; margin-right: 10px; margin-left: 10px;" 
                 id="group_content">
                <DIV style="margin: 0px;" class="privacyhead">Privacy Policy 
                    SupplyMedium</DIV>
                <BR>
                <DIV style="margin: 0px;" class="privacyhead">What we collect	</DIV>
                <BR>
                <P class="paraalign">
                    <B>What personal information do we collect on the Site and 
                        why?</B>
                    <BR>
                    <BR> We collect several types of information on our Site including 
                    "Personal Information," which generally is information that can be used to 
                    identify you individually,  such as your name and email address. The information 
                    we collect depends on what services you use on our Site(s). The following is a 
                    more detailed explanation of these types of information, as well as when and how 
                    we use it.<BR>
                </P>
                <P class="paraalign">
                    <B>Contact Information -</B> We may collect information 
                    that identifies you personally,  such as your name and e-mail address,when You 
                    submit a form, such as, but not limited to, a request for information, a request 
                    for an SupplyMedium White Paper,  a purchase of a subscription to one of our 
                    solutions, or to register for the Connect.SupplyMedium.Com site.We collect 
                    additional information if You choose to submit it,  including Your title and 
                    place of employment, business telephone number, mailing and facsimile address, 
                    as well as the contents of Your questions and/or comments   submitted along with 
                    this information.In the case of purchasing a subscription or a service some of 
                    this additional information is required.  In general, we may use this 
                    information to respond to Your inquiry, request Your participation in a survey, 
                    provide you with the service that youâve purchased , and contact You about 
                    SupplyMedium product or partner offerings that may be of interest to 
                    You.<BR>
                    <BR>Here are some details about certain features of our Site(s).<BR>
                </P>
                <P class="paraalign">
                    <B>Newsletters-</B>If You register for an SupplyMedium 
                    newsletter, we will use Your email address to send the newsletter to You and you 
                    may be added to a list  to receive additional promotions or communications.<BR>
                <P>
                <P class="paraalign">
                    <B>Investor Inquiries-</B> SupplyMedium's investor 
                    relations department accepts requests for corporate and securities information 
                    about SupplyMedium through the Site. Personal information submitted with such 
                    requests is only used by SupplyMedium to respond to the investor's request as 
                    long as the request is for investor information.<BR>
                <P>
                <P class="paraalign">
                    <B>IP Addresses-</B>Each visitor to the Site is identified 
                    by an IP address during their visit and IP addresses are automatically captured 
                    by the Site management software.  SupplyMedium uses this information to help 
                    improve the operation of the Site and may at some time use this information to 
                    help adjust the content of the Site to the general geographic location of the 
                    visitor and for reporting purposes.<BR>
                </P>
                <BR>
                <DIV style="margin: 0px;" class="privacyhead">Security</DIV>
                <BR>
                <P class="paraalign">We are committed to ensuring that your information is 
                    secure. In order to prevent unauthorized access or disclosure we have put in 
                    place suitable physical, electronic  and managerial procedures to safeguard and 
                    secure the information we collect online.<BR>
                </P>
                <P class="paraalign">
                    <B>How we use Cookies</B>
                    <BR>  When you access our website 
                    or use SupplyMedium, we (including companies we work with) may place small data 
                    files on your computer or other device. These data files  may be cookies, pixel 
                    tags, "Flash cookies," or other local storage provided by your browser or 
                    associated applications ("Cookies"). We use these technologies to: recognize 
                    you as a customer; customize SupplyMedium, content, and advertising; measure 
                    promotional effectiveness; help ensure that your account security is not 
                    compromised; mitigate risk and prevent fraud; and to promote trust and safety 
                    across our sites and SupplyMedium.<BR>We use both session and persistent 
                    Cookies. Session Cookies expire and no longer have any effect when you log out 
                    of your account or close your browser. Persistent Cookies  remain on your device 
                    until you erase them or they expire.<BR>We encode our Cookies so that we can 
                    interpret the information stored in them. You are free to decline our Cookies if 
                    your browser or browser add-on permits, but doing so may  interfere with your 
                    use of our website and SupplyMedium. Refer to the help section of your browser, 
                    browser extensions, or installed applications for instructions on blocking, 
                    deleting, or disabling Cookies. You may encounter PayPal Cookies on websites 
                    that we do not control. For example, if you view a web page created by a third 
                    party or use an application developed by a third party, there may be a Cookie 
                    placed by the web page or application. Likewise, these third parties may place 
                    their own Cookies that are not subject to our control and the PayPal  Privacy 
                    Policy does not cover their use.	</P>
                <BR>
                <DIV class="privacyhead">Links to other websites</DIV>
                <BR>
                <P class="paraalign">We are not responsible for the practices employed by any 
                    websites or services linked to or from our Service, including the information or 
                    content contained within them. 	Please remember that when you use a link to go 
                    from our Service to another website or service, our Privacy Policy does not 
                    apply to those third-party websites or services. 	Your browsing and interaction 
                    on any third-party website or service, including those that have a link on our 
                    website, are subject to that third party's own rules and policies. 	In addition, 
                    you agree that we are not responsible and do not have control over any 
                    third-parties that you authorize to access your User Content. If you are using a 
                    third-party 	website or service and you allow them to access your User Content 
                    you do so at your own risk.<BR>
                </P>
                <BR>
                <P class="paraalign">
                    <B>How does SupplyMedium use the information it collects 
                        about me?</B>
                    <BR>
                    <BR>SupplyMedium uses the information we collect about you in a 
                    number of ways, such as:
                <UL class="paraalign">
                    <LI>Providing you with the SupplyMedium websites and applications for which 
                        you have registered, as well as any services, support, or information you have 
                        requested<BR>
                        <BR>
                    </LI>
                    <LI>Better understanding how our websites and applications are being used so 
                        we can improve them<BR>
                        <BR>
                    </LI>
                    <LI>Diagnosing problems in our websites and applications<BR>
                        <BR>
                    </LI>
                    <LI>Tailoring a website, application, or SupplyMedium marketing to your likely 
                        interests<BR>
                        <BR>
                    </LI>
                    <LI>Sending you business messages such as those related to payments or 
                        expiration of your subscription<BR>
                        <BR>
                    </LI>
                    <LI>Sending you information about SupplyMedium, new application releases, 
                        special offers, and similar information<BR>
                        <BR>
                    </LI>
                    <LI>Conducting market research about our customers, their interests, and the 
                        effectiveness of our marketing campaigns<BR>
                        <BR>
                    </LI>
                    <LI>Reducing fraud, software piracy, and protecting our customers as well as 
                        SupplyMedium<BR>
                        <BR>
                    </LI>
                    <LI>As further described for a specific SupplyMedium website or 
                        application<BR>
                        <BR>
                    </LI>
                    <LI>
                        <B>To contact you.</B> We may contact you with service-related 
                        announcements from time to time. You may opt out of all communications except 
                        essential updates on your account notifications page. We may include content 
                        you see on SupplyMedium in the emails we send to you<BR>
                        <BR>
                    </LI>
                    <LI>
                        <B>To serve personalized advertising to you.</B> We donât share your 
                        information with advertisers without your consent. (An example of consent 
                        would be if you asked us to provide  your shipping address to an advertiser to 
                        receive a free sample.) We allow advertisers to choose the characteristics of 
                        users who will see their advertisements and we may use   any of the 
                        non-personally identifiable attributes we have collected (including 
                        information you may have decided not to show to other users, such as your 
                        birth year   or other sensitive personal information or preferences) to select 
                        the appropriate audience for those advertisements. Even though we do not share 
                        your information with advertisers   without your consent, when you click on or 
                        otherwise interact with an advertisement there is a possibility that the 
                        advertiser may place a cookie in your browser and note that   it meets the 
                        criteria they selected.<BR>
                        <BR>
                    </LI>
                    <LI>
                        <B>To make Suggestions.</B> We use your profile information and other 
                        relevant information, to help you connect with other companies, including 
                        making suggestions   to you and other users that you connect with on 
                        SupplyMedium. You want to limit your visibility in suggestions we make to 
                        other companies or block specific companies    from being suggested to you and 
                        you from being suggested to them.<BR>
                        <BR>
                    </LI>
                </UL>
                <P>
                </P>
                <DIV style="margin: 0px;" class="privacyhead">Conditions of Use, Notices, and 
                    Revisions</DIV>
                <BR>
                <P class="paraalign">If you choose to visit SupplyMedium, your visit and any 
                    dispute over privacy is subject to this Notice and our Terms Of Use, including 
                    limitations on damages,  resolution of disputes, and application of the law of 
                    the state of Ohio. If you have any concern about privacy at SupplyMedium, please 
                    contact us with a thorough description, and we will try to resolve it. Our 
                    business changes constantly, and our Privacy Policy and the Terms of Use will 
                    change also. We may e-mail periodic  reminders of our notices and conditions, 
                    but you should check our Web site frequently to see recent changes. Unless 
                    stated otherwise, our current Privacy Notice  applies to all information that we 
                    have about you and your account. We stand behind the promises we make, however, 
                    and will never materially change our policies  and practices to make them less 
                    protective of customer information collected in the past without the consent of 
                    affected customers. </P>
            </DIV>
        </DIV>
    </DIV>
    <DIV>
    </DIV>
</BODY>
</HTML>
