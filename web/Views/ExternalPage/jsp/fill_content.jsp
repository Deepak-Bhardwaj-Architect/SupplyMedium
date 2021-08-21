<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="fill_selection" style="margin: 0px">

	<div class="divrow" style="width:500px;margin-left:454px;height:35px;display:none;" >
		<label>Select a Page</label>
	</div>
	
	<div style="float:left;width:600px;margin-left:272px;display:none;">
		<div class="next_prev_container" id="btn_fillcontent_previews_small">
			<div class="prev_image"></div>
			<div class="next_prev_lbl">Previous</div>
		</div>
		
	
		<div class="select-container" style="float: left;margin:0px 20px 0px 20px;" >
			<select id="menu_selection_selectbox">
				<option value="fill_home">Home</option>
				<option value="fill_products">Products</option>
				<option value="fill_solutions">Solutions</option>
				<option value="fill_services">Service/Support</option>
				<option value="fill_aboutus">About Us</option>
				<option value="fill_contactus">Contact Us</option>		
			</select>
		</div>
	<div>
	
		<div class="next_prev_container" id="btn_fill_save">
			<div class="next_prev_lbl" style="text-align:right;">Next</div>
			<div class="next_image"></div>
		</div>
		
	</div>
	
		
	</div>
	
	<div id="steps">

	</div>
	
</div>

<div class="fill_sel_menu" id="fill_home" style="margin-left: 20px;">
	<%@ include file="fill_home.jsp" %>
</div>

<div  class="fill_sel_menu"  id="fill_products" style="display: none;">
	<%@ include file="fill_products.jsp" %>
</div>

<div  class="fill_sel_menu"  id="fill_services" style="display: none;">
	<%@ include file="fill_services.jsp" %>
</div>

<div  class="fill_sel_menu"  id="fill_solutions" style="display: none;">
	<%@ include file="fill_solutions.jsp" %>
</div>

<div  class="fill_sel_menu"  id="fill_aboutus" style="display: none;margin-left:20px;">
	<%@ include file="fill_aboutus.jsp" %>
</div>

<div  class="fill_sel_menu"  id="fill_contactus" style="display: none;">
	<%@ include file="fill_contactus.jsp" %>
</div>

<%@ include file="add_content.jsp" %>

<div id="fill_content_err" class="fill_content_err" style="display:none;">  </div>

<div style="margin: 10px 0px 30px 364px;float:left;">
		<input id="btn_fillcontent_previous" class="general-button gen-btn-Gray" type="button" value="Previous" />
		<input style="margin-left: 71px;" id="btn_fillcontent_next" class="general-button gen-btn-Orange" type="button" value="Next" />
</div>