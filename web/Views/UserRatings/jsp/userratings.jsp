<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/UserRatings/css/userratings.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/UserRatings/css/jRating.jquery.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />

	
<title>Supply Medium</title>
</head>


<body onload="">


<%@include file="../../../session_check.jsp"%>

<script type="text/JavaScript">
$("#content_loader").hide();
hideAjaxLoader();
</script>

	<div class="pagetitlecontainer">
		<div class="pagetitle">Ratings</div>
	</div>
	<div class="page">
		<div class="rattingscontainer contentcontainer" >
			<!-- <input type="text" class="ratingstextbox textbox" placeholder="Search for first name, last name, email and business" /> -->
			<div class="ratingscompanylist">
				<div class="ratingscompanylistInner">
					<div id="ratingscompanylistcontainer">						
					</div>
				</div>
			</div>
			<div class="ratingsreview" style="display:none;">
				<div class="ratingcompanyinfo">
					<div class="rattingcompanydetails">
						<div class="ratting_row">
							<label class="ratingcompanyname"></label>
						</div>
						<div class="ratting_row">
							<div class="rattingvaluebigmain" data-average=""></div>
						</div>
						<div class="ratting_row">
							<label class="rating_number">Based on <span class="rating_number_value_main"></span> ratings</label>
						</div>
						<div class="ratting_row">
							<input id="btnratingswritereview" class="general-button ratngs-btn-review" type="button" value="Write a review" />
						</div>
					</div>
					<div class="ratingcompanyimage">
						<div id="rattingcompanyimagefilemain" class="ratingcompanyimageholder">
							<img  src="${pageContext.request.contextPath}/Views/UserRatings/images/ratings_company_nologo_Large.png" />
						</div>
					</div>
				</div>
					
				<div class="ratingreviewlistcontainer">
					<div class="reviewlistheading">
						Review
					</div>
					<div id="ratingreviewlist">
						<div id="ratingreviewlistplaceholder">
							
						</div>							
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="../../UserRatings/jsp/review_edit_popup.jsp"%>
	<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
	<%@include file="../../Utils/jsp/ajax_loader.jsp"%>

	<%@include file="../../Utils/jsp/footer.jsp"%>
	
<script>



$.getScript( "${pageContext.request.contextPath}/Views/UserRatings/js/jRating.jquery.js");
$.getScript( "${pageContext.request.contextPath}/Views/UserRatings/js/userratings.js", function( data, textStatus, jqxhr ) {
	userRatingsOnload();
	 
	});

</script>


</body>

</html>