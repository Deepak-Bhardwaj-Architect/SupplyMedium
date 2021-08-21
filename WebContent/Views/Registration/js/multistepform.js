
	var prevLink = '<a class="prev"  href="#"><input type="button" value="Previous" class="gen-btn-LGray"/></a>';
	var nextLink = '<a class="next"  href="#"><input type="button" value="Next" class="gen-btn-Orange"/></a>';
	var navHTML = '<div class="prev-next">' + prevLink + nextLink + '</div>';
	$(function() {
		// init
		$('#registrationform > div').hide().append(navHTML);
		$('#companyinfo .prev').remove();
		$('#pricing .next').remove();

		// show first step
		$('#companyinfo').show();

		$('a.next').click(function() 
		{
			var whichStep = $(this).parent().parent().attr('id');

			if (whichStep == 'companyinfo') 
			{
				var isvalid=$("#registrationform").valid();
				
				if(!isvalid)
					return;
				//alert( $('#fileerror').val() );
				
				if($('#fileerror').val()== "1" )
				{
						return;
				}
				
				

				$('#step1').removeClass('stepprocess');
				$('#step1text').removeClass('stepprocesstext');
				$('#step1').addClass('stepcompleted');
				$('#step1text').addClass('stepcompletedtext');

				$('#step2').removeClass('stepnext');
				$('#step2text').removeClass('stepnexttext');
				$('#step2').addClass('stepprocess');
				$('#step2text').addClass('stepprocesstext');

			} else if (whichStep == 'userinfo') {
				
				var isvalid=$("#registrationform").valid();
				
				if(!isvalid)
					return;
				
				if($('#phoneexist').val()== "1" || $('#emailexist').val()== "1")
				{
					return;
				}
				// validate second-step
				$('#step2').removeClass('stepprocess');
				$('#step2text').removeClass('stepprocess');
				$('#step2').addClass('stepcompleted');
				$('#step2text').addClass('stepcompletedtext');

				$('#step3').removeClass('stepnext');
				$('#step3text').removeClass('stepnexttext');
				$('#step3').addClass('stepprocess');
				$('#step3text').addClass('stepprocesstext');

				$("#registrationform").validate();

			} else if (whichStep == 'pricing') {
				
				var isvalid=$("#registrationform").valid();
				
				if(!isvalid)
					return;
				// validate last-step
				$('#step3').removeClass('stepprocess');
				$('#step3text').removeClass('stepprocess');
				$('#step3').addClass('stepcompleted');
				$('#step3text').addClass('stepcompletedtext');

				$("#registrationform").validate();
			}

			$(this).parent().parent().hide().next().show();
			
			 $( "#usertitle" ).trigger('update');
		});

		$('a.prev').click(function() {
			
			var whichStep = $(this).parent().parent().attr('id');
			
			if (whichStep == 'pricing') 
			{
				$('#step3').removeClass('stepprocess');
				$('#step3text').removeClass('stepprocesstext');
				$('#step3').addClass('stepnext');
				$('#step3text').addClass('stepnexttext');

				$('#step2').removeClass('stepcompleted');
				$('#step2text').removeClass('stepcompletedtext');
				$('#step2').addClass('stepprocess');
				$('#step2text').addClass('stepprocesstext');

			} else if (whichStep == 'userinfo') {
				
				$('#step2').removeClass('stepprocess');
				$('#step2text').removeClass('stepprocess');
				$('#step2').addClass('stepnext');
				$('#step2text').addClass('stepnexttext');

				$('#step1').removeClass('stepcompleted');
				$('#step1text').removeClass('stepcompletedtext');
				$('#step1').addClass('stepprocess');
				$('#step1text').addClass('stepprocesstext');


			} else if (whichStep == 'companyinfo') {
				$('#step3').removeClass('stepprocess');
				$('#step3text').removeClass('stepprocess');
				$('#step3').addClass('stepcompleted');
				$('#step3text').addClass('stepcompletedtext');
			}

			$(this).parent().parent().hide().prev().show();
		});
	});
