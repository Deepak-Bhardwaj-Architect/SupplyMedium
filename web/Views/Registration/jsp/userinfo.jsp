<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div class="leftcontent">

		<div class="row">
			<div class="label">First Name<span class="mandatory">*</span></div>
			<input type="text" name="firstname" id="firstname" class="required textbox" />
		</div>
		<div class="row">
			<div class="label">Last Name<span class="mandatory">*</span></div>
			<input type="text" name="lastname" id="lastname" class="required textbox" />
		</div>
		<div class="row">
			<div class="label">Title</div>
			<select name="title" class="selectbox" id="usertitle">
				<option value="MR">MR</option>
				<option value="MRS">MRS</option>
                                <option value="MS">MS</option>
                                <option value="M/S">M/S</option>
			</select>
		</div>
		<div class="row">
			<div class="label">Department</div>
			<input type="text" name="department" id="department" class="textbox" />
		</div>
		<div class="row">
			<div class="label">Manager/Supervisor</div>
			<input type="text" name="managersupervisor" id="managersupervisor" class="textbox" />
		</div>
	</div>
	<div id="userseparator" class="formseparator" style="height:290px;margin-bottom:147px;height:150px"></div>
	<div class="rightcontent">

		<div class="row">
			<div class="label">Primary Phone<span class="mandatory">*</span></div>
			<input type="text" id="countrycode" class="textbox" disabled style="float:left;width:25px;margin-right:5px;"/>
			<input type="text" name="phone" id="phone" onblur="validatePhoneNo(this.value);"
				class="required textbox" style="width:137px;"/> <div id="phonenocheck"></div>
				<label for="phone" generated="true" class="error" id="phoneerr"></label>
				<input type="hidden" name="phoneexist" id="phoneexist"  />
		</div>
		<div class="row">
			<div class="label">Cell</div>
			<input type="text" name="cell" id="cell" class="textbox" />
		</div>
		<div class="row">
			<div class="label">Fax</div>
			<input type="text" name="fax" id="fax" class="textbox" />
		</div>
		<div class="row">
			<div class="label">Email<span class="mandatory">*</span></div>
			<input type="text" name="email" id="email" class="email required textbox"
				onblur="validateEmail(this.value)" />
				<span id="emailcheck"></span>
				<label for="email" generated="true" class="error" id="emailerr"></label>
				<input type="hidden" name="emailexist" id="emailexist"  />
		</div>
		
		<div class="row">
			<div class="label">Password<span class="mandatory">*</span></div>
			<input type="password" name="password" id="password" class="required textbox" 
			title="Password should be of minimum 8 characters and maximum 15 characters. Password must contain at least one character,one number and one special character and starts with character (case sensitive)" />
		</div>
		
		<div class="row">
			<div class="label">Confirm Password<span class="mandatory">*</span></div>
			<input type="password" name="retype" id="retype" class="required textbox" />
		</div>
	</div>
        <div class="pricing" style="">

		<input type="hidden" name="RequestType" value="NewRegistration" id="RequestType"/> <input
			type="hidden" name="RequestCode" value="1001" />
			
			<!--div class="pricingheading">
			Select appropriate package
			</div>
			<img src = "${pageContext.request.contextPath}/Views/Registration/images/most_popular.png" class="mostpopularimg">
		<div class="pricingtag">
			<div class="bluetag_mo pricingtagimg" id="basic">
				<div class="tagtitle">Basic</div>
				<div class="tagdescmed">Free for 30 days</div>
				<div class="tagdesc">
					<div class="tagdescleft">
						<img
							src="${pageContext.request.contextPath}/Views/Registration/images/blue_tick.png"
							class="tick" />
					</div>
					<div class="tagdescright">2 Employee license</div>
				</div>
                                <div class="tagdesc">
					<div class="tagdescleft">
						<img
							src="${pageContext.request.contextPath}/Views/Registration/images/blue_tick.png"
							class="tick" />
					</div>
					<div class="tagdescright">10 GB cloud storage</div>
				</div>
				<div class="tagdesc">
				<div class="tagdescleft">
				<img
						src="${pageContext.request.contextPath}/Views/Registration/images/blue_tick.png"
						class="tick" />
				</div>
				<div class="tagdescright">
				Transaction volume <BR />up
					to $10K/month
				</div>
					
				</div>
				<div class="select">Select</div>
			</div>
			<div class="greentag pricingtagimg" id="plus">
				<div class="tagtitle">Advance</div>
				<div class="tagdescmed">$49/month</div>
				
				<div class="tagdesc">
				<div class="tagdescleft">
				<img
						src="${pageContext.request.contextPath}/Views/Registration/images/green_tick.png"
						class="tick" />
				</div>
				<div class="tagdescright">10 Employee license
				</div>
					
				</div>
                                <div class="tagdesc">
					<div class="tagdescleft">
						<img
							src="${pageContext.request.contextPath}/Views/Registration/images/green_tick.png"
							class="tick" />
					</div>
					<div class="tagdescright">100 GB cloud storage</div>
				</div>
				<div class="tagdesc">
				<div class="tagdescleft">
				<img
						src="${pageContext.request.contextPath}/Views/Registration/images/green_tick.png"
						class="tick" />
				</div>
				<div class="tagdescright">
				Transaction volume <BR />up
					to $100K/month
				</div>
					
				</div>
				<div class="select">Select</div>
			</div>
			<div class="orangetag pricingtagimg" id="premium">
				<div class="tagtitle">Premium</div>
				<div class="tagdescmed">$99/month</div>
				<div class="tagdesc">
				<div class="tagdescleft">
				<img
						src="${pageContext.request.contextPath}/Views/Registration/images/orange_tick.png"
						class="tick" />
				</div>
				<div class="tagdescright">
				50 Employee license
				</div>
					
				</div>
                                <div class="tagdesc">
					<div class="tagdescleft">
						<img
							src="${pageContext.request.contextPath}/Views/Registration/images/orange_tick.png"
							class="tick" />
					</div>
					<div class="tagdescright">500 GB cloud storage</div>
				</div>
				<div class="tagdesc">
				<div class="tagdescleft">
				<img
						src="${pageContext.request.contextPath}/Views/Registration/images/orange_tick.png"
						class="tick" />
				</div>
				<div class="tagdescright">
				Transaction volume <BR />up
					to $500K/month
				</div>
					
				</div>
				<div class="select">Select</div>
			</div>
                        <div class="redtag pricingtagimg" id="unlimited">
                                <div class="tagtitle">Unlimited</div>
				<div class="tagdescmed">Request a Quote</div>
				<div class="tagdesc">
                                <div class="tagdescleft"></div>
				<div class="tagdescright w133">at info@supply medium.com with no. of employees, required cloud storage, Transaction volume limit quantity/month
				</div>
                                </div>
				<div class="select">Select</div>
			</div>

		</div-->

		<input type="radio" id="free" name="pricingoption" class="pricingradiobox"
			value="Basic" style="display:none;" checked="checked"/>  
                <input type="radio" id="plus" name="pricingoption" class="pricingradiobox"
			value="Plus" style="display:none;" />  
                <input type="radio" id="premium" name="pricingoption" class="pricingradiobox"
			value="Premium" style="display:none;"/> 
                <input type="radio" id="unlimited" name="pricingoption" class="pricingradiobox"
			value="Unlimited" style="display:none;"/> 
			
		<div class="leftcontent">
			<fieldset class="fieldset" style="height:90px;">
						<legend style="margin-left:10px;">Are you human?</legend>
						<div class="row" style="padding-top:10px;width:350px;">
							<div >
								<label class="captchalabel captchabg" id="lcaptcha" for="captcha"
									></label><input
									type="captcha" name="captcha" id="captcha"
									class="required capthatextbox"  />
									<input type="button" class="captchareload" onclick="generate_captcha('lcaptcha');"><br /> <input
									type="hidden" id="captchavalue" name="captchavalue" />
									<label for="captcha" generated="true" class="error" style="margin-left:150px;"></label>
							</div>
						</div>
					</fieldset>
			<!--  
		<div id="captchacontainer">
			<label id="lcaptcha" for="captcha" style="float:left;height:30px;font-size:20px"></label>
			<input type="captcha" name="captcha" id="captcha" class="textbox required" style="width:50px;"/>
			<label for="captcha" generated="true" class="error" style="float:right;margin-left:5px;"></label>
			<input type="hidden" id="captchavalue" name="captchavalue" />
		</div>-->
		</div>
			
		<div class="rightcontent">
		<fieldset class="fieldset" style="height:90px;">
						<legend style="margin-left:10px;">Conditions</legend>
						<div class="row" style="height: 35px; width: 340px; margin-top:5px;"  id="condition1">
							<div class="checkContainer"><input type="checkbox" class="required"
								id="termsconditions" name="termsconditions"  style="float: left;margin-top:0px;cursor:pointer;" /><div></div></div>
							<!-- <label class="label" for="termsconditions" id="termsconditionslbl"
								style="width: 210px; height: 20px; line-height: 16px;margin-left:5px;text-decoration:underline;cursor:pointer;">Accept
								terms &amp; condition</label> -->
								
							<div class="label" for="termsconditions" id="termsconditionslbl"
								style="width: 210px; height: 20px; line-height: 16px;margin-left:5px;text-decoration:underline;cursor:pointer;">Accept
								terms &amp; condition</div>
								
							<label for="termsconditions" generated="true" class="error"
								style="margin-left: 0px; width: 250px;"></label>
						</div>

						<div class="row" style="height: 35px; width: 340px;cursor:pointer;" id="condition2">
							<div class="checkContainer"><input type="checkbox" name="policies" id="policies" 
								class="required" style="float: left;margin-top:0px;cursor:pointer;" /><div></div></div>
							<!-- <label class="label" for="policies" id="policieslbl"
								style="width: 280px; height: 20px; line-height: 16px;margin-left:5px;text-decoration:underline;cursor:pointer;">Accept
								privacy &amp; security policies</label> -->
							<div class="label" for="policies" id="policieslbl"
								style="width: 280px; height: 20px; line-height: 16px;margin-left:5px;text-decoration:underline;cursor:pointer;">Accept
								privacy &amp; security policies</div>
							<label for="policies" generated="true" class="error"
								style="margin-left: 0px; width: 250px;"></label>
						</div>
		</fieldset>
                <fieldset class="fieldset" style="height:55px;" id="couponfieldset">
						<legend style="margin-left:10px;">Coupon Code</legend>
						<div class="row" style="height: 35px; width: 340px; margin-top:5px;padding-top:5px;"  id="condition1">
                                                    Discount Coupon (if any) <input type="text" name="couponcode" id="couponcode"
									class="textbox" style="float:right;" />
						</div>
		</fieldset>
		
		</div>
		<div style="width:30px;height:70px;float:left">
		</div>
			
			<div >
				<input type="button" value="Register" class="gen-btn-Orange" style="position:absolute;left:370px;top:215px;" onclick="submitRegnForm();"/> 
			</div>
			
		
		<%@include file="termspopup.jsp"%>
		<%@include file="policypopup.jsp"%>

		</div>
</body>
</html>