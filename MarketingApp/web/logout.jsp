<%
session.removeAttribute("marketing_person_key");
session.removeAttribute("e_mail");
session.removeAttribute("person_name");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
response.addHeader("Cache-Control","no-store");
response.sendRedirect("index.jsp");
%>