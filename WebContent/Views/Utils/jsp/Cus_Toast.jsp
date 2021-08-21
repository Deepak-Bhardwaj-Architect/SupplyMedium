<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Toast Widnow </title>


<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Cus_Toast.css" />
<script type="text/javascript">
$(function(){
	$("#Open_Toast").click(function()
	{		
		ShowToast("Inforamtion Saved Successfully...",1000);
	});
	
});

function ShowToast(Message,ShowTime)
{
	
	if(typeof(ShowTime)==='undefined') ShowTime = 2000;

	var Toast_Obj=$("#Toast_Window");
	
	var outerWidth=$(Toast_Obj).outerWidth()/2;
	
	//Toast_Obj.show(500);
	
	Toast_Obj.fadeIn(500);
	
	//Toast_Obj.slideToggle(500)
	
	$(".Toast_Data").html(Message);
	
	Toast_Obj.delay(ShowTime);
	
	//Toast_Obj.hide(500);
	
	Toast_Obj.fadeOut(2000);
	
	//Toast_Obj.slideToggle(500)
	
}

</script>
</head>
<body>

<!--  <input type="button" id="Open_Toast" value="AAAAAAAAAAAAAAAAAAAA"/>-->

<div id="Toast_Window" style="display:none;">	
		<p class="Toast_Data">	
		</p>
</div>


</body>
</html>