<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <div class="tempreview_container">
    	<div class="tempreview_title">
    		<label>Choose a Template</label>
    		<input id="btn_tempreview_preview" class="general-button gen-btn-Green" type="button" value="Preview" />	
    	</div>
    	<div class="tempreview_list">
	    	<ul>    	
	    		<li mainType="T1" SubType="red"><img src="${pageContext.request.contextPath}/Views/ExternalPage/images/template1.png" /></li>
	    		<li mainType="T1" SubType="blue"><img src="${pageContext.request.contextPath}/Views/ExternalPage/images/template2.png" /></li>
	    		<li mainType="T1" SubType="green"><img src="${pageContext.request.contextPath}/Views/ExternalPage/images/template3.png" /></li>
	    		<li mainType="T1" SubType="orange"><img src="${pageContext.request.contextPath}/Views/ExternalPage/images/template4.png" /></li>	    	
	    		<li mainType="T2" SubType="green"><img src="${pageContext.request.contextPath}/Views/ExternalPage/images/template5.png" /></li>
	    		<li mainType="T2" SubType="orange"><img src="${pageContext.request.contextPath}/Views/ExternalPage/images/template6.png" /></li>
	    		<li mainType="T2" SubType="red"><img src="${pageContext.request.contextPath}/Views/ExternalPage/images/template7.png" /></li>
	    	</ul>
    	</div>
    </div>
    <div style="margin: 30px 0px 30px 364px;">
		<input id="btn_tempreview_previous" class="general-button gen-btn-Gray" type="button" value="Previous" />
		<input style="margin-left: 71px;" id="btn_tempreview_finish" class="general-button gen-btn-Orange" type="button" value="Finish" />
	</div>