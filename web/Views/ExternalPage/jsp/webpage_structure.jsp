<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <div class="checkbox_atleast">
		Choose at least any of the following items on the list
	</div>
	<ul>
		<li>
			<div class="checkContainer">
				<input checked="checked" value="Home" disabled="disabled" type="checkbox" id="menu_home" />
				<div></div>
			</div>
			<label for="menu_home">Home</label>
		</li>
		<li>
			<div class="checkContainer">
				<input type="checkbox" value="Products" id="menu_products" />
				<div></div>
			</div>
			<label for="menu_products">Products</label>
		</li>
		<li>
			<div class="checkContainer">
				<input type="checkbox" value="Solutions" id="menu_solutions" />
				<div></div>
			</div>
			<label for="menu_solutions">Solutions</label>
		</li>
		<li>
			<div class="checkContainer">
				<input type="checkbox" value="Service" id="menu_service" />
				<div></div>
			</div>
			<label for="menu_service">Service/Support</label>
		</li>
		<li>
			<div class="checkContainer">
				<input type="checkbox" value="About_US" disabled="disabled" checked="checked" id="menu_about" />
				<div></div>
			</div>
			<label for="menu_about">About Us</label>
		</li>
		<li>
			<div class="checkContainer">
				<input type="checkbox" disabled="disabled" value="ContactUS" checked="checked" id="menu_contact" />
				<div></div>
			</div>
			<label for="menu_contact">Contact Us</label>
		</li>
		
	</ul>
	
	<div class="btn_webstructure_container">
		<input id="btn_webstructure_cancel" class="general-button gen-btn-Gray" type="button" value="Cancel" />
		<input style="margin-left: 71px;" id="btn_webstructure_next" class="general-button gen-btn-Orange" type="button" value="Next" />
	</div>
	
	