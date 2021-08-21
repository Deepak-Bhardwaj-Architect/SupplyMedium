

function generate_captcha(id)
{
	var captcha_a = Math.ceil(Math.random() * 10);
	var captcha_b = Math.ceil(Math.random() * 10);       
	var captcha_c = captcha_a + captcha_b;
	
	var id = (id) ? id : 'lcaptcha';
	$("#"+id).html(captcha_a + " + " + captcha_b );
	
	var id1 = (id1) ? id1 : 'captchavalue';
	$("#"+id1).val(captcha_c);
}


//Our validation script will go here.
//$(document).ready(function(){
//	
//	//addMethod( name, method, [message] ) 
//	alert("captcha");
//	jQuery.validator.addMethod(
//	"math", 
//	function(value, element, params) { return this.optional(element) || value == params[0] + params[1]; },
//	jQuery.format("Please enter the correct value for {0} + {1}")
//	);
//
//	
//    //validation implementation will go here.
//	$("#registrationform").validate({
//	rules: {
//        captcha: {
//            //math: [2, 3]
//			math: [captcha_a, captcha_b]
//        }
//    }
//	
//	});
//});