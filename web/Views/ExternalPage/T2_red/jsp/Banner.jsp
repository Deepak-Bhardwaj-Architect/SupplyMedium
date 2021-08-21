<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<script type="text/javascript">
	$(function()
	{

		var url=(document.URL);
		var slashIndex = url.lastIndexOf('/');
		var	dotIndex = url.lastIndexOf('.', slashIndex);
		var	filenameWithoutExtension;
		if (dotIndex == -1)
		{
			filenameWithoutExtension = url.substring(slashIndex + 1);
		}
		else
		{
			filenameWithoutExtension = url.substring(slashIndex + 1, dotIndex);
		}
		

		if (filenameWithoutExtension == "About.jsp")
		{
			$("#banner_title").html("Abouts Us");
		}
		else if(filenameWithoutExtension == "Home.jsp")
		{
			$("#banner_title").html("Home");
		} 
		else if(filenameWithoutExtension == "Contact.jsp")
		{
			$("#banner_title").html("Contacts");
		} 
		else if(filenameWithoutExtension == "Product.jsp")
		{
			$("#banner_title").html("Products");
		} 
		else if(filenameWithoutExtension == "Services.jsp")
		{
			$("#banner_title").html("Services");
		} 
		else if(filenameWithoutExtension == "Solutions.jsp")
		{
			$("#banner_title").html("Solutions");
		} 

	});
</script>

<div class="T_bannerContainer">
	<div class="T_banner">
		<div class="T_banner_container">
			<div class="T_company_log_image"></div>
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

	<div class="T_banner_Title T_banner_container">
		<label id="banner_title"
			style="position: absolute; margin-left: -60px;">Home</label>
	</div>
</div>