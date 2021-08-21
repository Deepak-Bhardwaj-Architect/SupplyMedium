/**
 *  Company Profile Window Validator
 */

$(function(){

	/* Validation */
	jQuery.validator.addMethod("notEqual", function(value, element, param) {
		  return this.optional(element) || value !== param;
		}, "Please choose a value!");
	
	
	/* Company Profile Window Validation */
	var formValidator=$("#companyProfile").validate({
	    	//ignore: "",
	    	rules: {
	    		companyname:
	        	{
	        		required: true,
	                minlength:3,
	                maxlength:60	
	        	},
	        	companyid:
	        	{
	        		required: true,
	                minlength:3,
	                maxlength:15
	        	},
	        	city_0:
	        	{
	        		required: true,
	                minlength:3,
	                maxlength:15
	        	},
	        	businesscategory:
			 	{
			         notEqual:"--Select Country--",
			 	},
			 	branch_0:
			 	{
			         notEqual:"--Select Country--",
			 	},
			 	countryregion_0_value:
			 	{
			         notEqual:"--Select Country--",
			 	}
	    	},
	    	messages:{
	    		countryregion_0_value:
		    	{
	    			notEqual:"Select Country",
		    	}	    		
	    	}
	 });
	
	
	// Popup Window Validation
	$("#Popup_Address").validate({
	    	//ignore: "",
	    	rules: {
	    		city_pop:
		    	{
	    			required: true,
		            minlength:3,
		            maxlength:60	
		    	},
		    	countryregion_pop:
			 	{
			         notEqual:"--Select Country--",
			 	}
	    	},
	    	messages:{
	    		countryregion_pop:
		    	{
	    			notEqual:"Select Country",
		    	}	    		
	    	}
	 });
});