<%@page import="core.privilege.UserPrivilegesData"%>
<%@page import="core.privilege.DeptPrivilegesData"%>
<%@page import="core.privilege.AllPrivilegesData"%>
<%@page import="core.login.SessionData"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@ page import="java.util.*,core.login.SessionData,core.privilege.AllPrivilegesData;"%>

<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies. 
   SessionData sessionObj = (SessionData)session.getAttribute("UserSessionData");
   String firstName = "";
   String lastName = "";
   String emailAddress = "";
   String comRegnKey = "";
   String companyName = "";
   String companyType = "" ;
   String userType = "";
   String userImageURL = "";
   
   String companyLogoUrl="";
   
   AllPrivilegesData allPrivilegesData = null;
   List<DeptPrivilegesData> deptPrivilegesArr = null;
   
   UserPrivilegesData userPrivilegesData = null;
   
   if (sessionObj != null) 
   {
    firstName = sessionObj.firstName_;
    lastName = sessionObj.lastName_;
    emailAddress = sessionObj.username_;
    comRegnKey = sessionObj.companyRegnKeyStr_;
    companyName = sessionObj.companyName_;
    companyType = sessionObj.companyType_;
    userType = sessionObj.userType_;
    userImageURL = sessionObj.userImageURL_;
    
    companyLogoUrl=sessionObj.companyLogoUrl_;
    
    allPrivilegesData = sessionObj.allPrivilegesData_;
   
    if( allPrivilegesData != null )
    {
    	deptPrivilegesArr = allPrivilegesData.deptPrivilegesDataArr_;
    	
    	userPrivilegesData = allPrivilegesData.userPrivilegesdata_;
  
    }%>
    
    <input type="hidden" id="regnkey" value="<%=comRegnKey %>"/>
	
	<input type="hidden" id="EmailAddress" value="<%=emailAddress %>"/> 
	<input type="hidden" id="firstName" value="<%=firstName %>"/>
	
	<input type="hidden" id="lastName" value="<%=lastName%>"/>
	<input type="hidden" id="compName" value="<%=companyName%>"/>
	
	<input type="hidden" id="companytype" value="<%=companyType%>">
	<input type="hidden" id="usertype_for_menu" value="<%=userType %>"/>
	
	<input type="hidden" id="user_image_url" value="<%=userImageURL%>">
	
	<input type="hidden" id="company_logo_url" value="<%=companyLogoUrl %>">
 
 <%if( userPrivilegesData != null ) 
 {%>
	<%  int addNewUser = userPrivilegesData.addNewUser_  ? 1 : 0; %>
	<input type="hidden" id="add_user_flag" value="<%=addNewUser %>"/>
	
	<%  int deleteUser = userPrivilegesData.deleteUser_  ? 1 : 0; %>
	<input type="hidden" id="delete_user_flag" value="<%=deleteUser %>"/>
	
	<%  int accessUserMgmt = userPrivilegesData.accessUserMgmt_  ? 1 : 0; %>
	<input type="hidden" id="access_user_mgmt_flag" value="<%=accessUserMgmt %>"/>
	
	<%  int createUserGroup = userPrivilegesData.createUserGroup_  ? 1 : 0; %>
	<input type="hidden" id="create_group_flag" value="<%=createUserGroup %>"/>
	
	<%  int deleteUserGroup = userPrivilegesData.deleteUserGroup_  ? 1 : 0; %>
	<input type="hidden" id="delete_group_flag" value=<%=deleteUserGroup %>>
	
	<%  int postAnnouncement = userPrivilegesData.postAnnouncement_  ? 1 : 0; %>
	<input type="hidden" id="post_annomt_flag" value="<%=postAnnouncement %>"/>	
	
	<%  int deleteAnnouncement = userPrivilegesData.deleteAnnouncement_  ? 1 : 0; %>
	<input type="hidden" id="delete_annomt_flag" value="<%=deleteAnnouncement %>"/>
	
	<%  int addBuySupplier = userPrivilegesData.addBuySupplier_  ? 1 : 0; %>
	<input type="hidden" id="add_vendor_flag" value="<%=addBuySupplier %>"/>
	
	<%  int deleteBuySupplier = userPrivilegesData.deleteBuySupplier_  ? 1 : 0; %>
	<input type="hidden" id="delete_vendor_flag" value="<%=deleteBuySupplier %>"/>
 
 	<%  int manageFolder = userPrivilegesData.manageFolder_  ? 1 : 0; %>
	<input type="hidden" id="manage_folder_flag" value="<%=manageFolder %>"/>
 	
 <%} 
 }

 else//(sessionObj == null)
{ %>
	   <%@ page autoFlush="true" buffer="1094kb"%>  
	   <jsp:forward page="/index.jsp" />
	<!-- response.sendRedirect("/SupplyMedium/index.jsp"); -->
<% }   %>
	
</body>
</html>