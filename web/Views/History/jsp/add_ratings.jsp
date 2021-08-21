<script>
$(".Popup_Close_NewGroup").click(function()
{
	$("#add_ratings").hide();
});
</script>

<div class="Custome_Popup_Window" style="display:none;" id="add_ratings">
<div class="Cus_Popup_Outline add_ratings_outline">
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title"
				style="border-radius: 0px;">
				<div style="padding: 0px 0px 0px 15px; float: left">Ratings</div>
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
			</div>
		
		<div class="star_container">
			<!--  <img src="images/great_star_full.png" class="star_prop">
			
			<img src="images/great_star_full.png" class="star_prop">
			<img src="images/great_star_full.png" class="star_prop">
			<img src="images/great_star_full.png" class="star_prop">
			<img src="images/great_star_empty.png" class="star_prop">-->
			
			<div id="add_ratings_star" style="float: left;" data-average="0"></div>
		</div>
	</div>
</div>	