<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	<div class="T_banner">
			<div class="T_banner_container">
				<div class="T_company_logo">
					@#CMPY_NAME
				</div>
				<div class="T_menu">
					<ul>
						<li id="T_menu_home"><a href="Home.jsp">Home</a></li>
						#@MENU_PRODUCT
						#@MENU_SOLUTIONS
						#@MENU_SERVICE
						<li id="T_menu_abouts"><a href="About.jsp">Abouts Us</a></li>
						<li id="T_menu_contact"><a href="Contact.jsp">Contacts</a></li>
					</ul>
				</div>
			</div>
		</div>