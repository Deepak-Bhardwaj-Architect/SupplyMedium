$(document).ready(function() {
	
    $("#registrationform").validate({
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
            maxlength:60	
    	},
    	firstname:
    	{
    		required: true,
            minlength:3,
            maxlength:25
    	},
    	lastname:
    	{
    		required: true,
            minlength:3,
            maxlength:25
    	},
    	phone:
    	{
    		required: true,
            minlength:5,
            maxlength:20,
           
            
    	},
    	companyid:
    	{
    		required: true,
            minlength:3,
            maxlength:15
    	},
    	divisionname:
    	{
            minlength:3,
            maxlength:50
    	},
    	unitname:
    	{
    		minlength:3,
            maxlength:50
    	},
    	address_0:
    	{
    		minlength:3,
            maxlength:55
    	},
    	address_1:
    	{
    		minlength:3,
            maxlength:55
    	},
    	address_2:
    	{
    		minlength:3,
            maxlength:55
    	},
    	city_0:
    	{
    		minlength:3,
            maxlength:30
    	},
    	city_1:
    	{
    		minlength:3,
            maxlength:30
    	},
    	city_2:
    	{
    		minlength:3,
            maxlength:30
    	},
    	zipcode_0:
    	{
    		minlength:3,
            maxlength:15
    	},
    	zipcode_1:
    	{
    		minlength:3,
            maxlength:15
    	},
    	zipcode_2:
    	{
    		minlength:3,
            maxlength:15
    	},
    	department:
    	{
    		minlength:3,
            maxlength:30
    	},
    	managersupervisor:
    	{
    		minlength:3,
            maxlength:30	
    	},
    	cell:
    	{
    		minlength:5,
            maxlength:20,		
            integer:true
    	},
    	fax:
    	{
    		minlength:5,
            maxlength:20,
            integer:true
    	},
    	
    	 password: 
        { 
              required: true,
              minlength:8,
              maxlength:15,
              startwithalphabet:true,
              containspecialchar:true,
              containnumeric:true
        }, 
        retype: 
        { 
              required: true, equalTo: "#password"
        },
        captcha: 
        { 
              required: true, equalTo: "#captchavalue"
        },
        
      },
      messages: {
    	  signupas: "Select company type",
    	  businesscategory: "Select Business Category",
    	  countryregion_0: "Select Country",
    	  countryregion_1: "Select Country",
    	  countryregion_2: "Select Country",
    	  companyid:"Enter company id",
    	  
    	 companyname:
      	{
      		required: "Enter your company name",
              minlength:"Company name should contain a minimum 3  characters",
              maxlength:"Company name should contain a maximum 60  characters"
      	},
      	companyid:
       	{
       		   required: "Enter your company ID",
               minlength:"Company ID should contain a minimum 3  characters",
               maxlength:"Company ID should contain a maximum 60  characters"
       	},
      	firstname:
      	{
      		required: "Enter your first name",
      		 minlength:"First name should contain a minimum 3  characters",
             maxlength:"First name should contain a maximum 25  characters"

      	},
      	lastname:
      	{
      		required: "Enter your last name",
      		 minlength:"Last name should contain a minimum 3  characters",
             maxlength:"Last name should contain a maximum 25  characters"

      	},
      	phone:
      	{
      		required: "Enter company phone number",
      		 minlength:"Phone number should contain a minimum 5  characters",
             maxlength:"Phone number should contain a maximum 20  characters",
             integer:"Phone number must be numeric"            	 

      	},
      	companyid:
      	{
      		 minlength:"Company Id should contain a minimum 3  characters",
             maxlength:"Company Id should contain a maximum 15  characters"

      	},
      	divisionname:
      	{
      		 minlength:"Division name should contain a minimum 3  characters",
             maxlength:"Division name should contain a maximum 50  characters"

      	},
      	unitname:
      	{
      		 minlength:"Unit name should contain a minimum 3  characters",
             maxlength:"Unit name should contain a maximum 50  characters"
      	},
      	address_0:
      	{
     		 minlength:"Address should contain a minimum 3  characters",
             maxlength:"Address should contain a maximum 55  characters"
      	},
      	address_1:
      	{
     		 minlength:"Address should contain a minimum 3  characters",
             maxlength:"Address should contain a maximum 55  characters"
      	},
      	address_2:
      	{
      		 minlength:"Address should contain a minimum 3  characters",
             maxlength:"Address should contain a maximum 55  characters"

      	},
      	city_0:
      	{
      		 minlength:"City name should contain a minimum 3  characters",
             maxlength:"City name should contain a maximum 30  characters"
      	},
      	city_1:
      	{
      		
      		 minlength:"City name should contain a minimum 3  characters",
             maxlength:"City name should contain a maximum 30  characters"
      	},
      	city_2:
      	{
      		 minlength:"City name should contain a minimum 3  characters",
             maxlength:"City name should contain a maximum 30  characters"

      	},
      	zipcode_0:
      	{
      		 minlength:"Zipcode should contain a minimum 3  characters",
             maxlength:"Zipcode should contain a maximum 15  characters"
      	},
      	zipcode_1:
      	{
      		 minlength:"Zipcode should contain a minimum 3  characters",
             maxlength:"Zipcode should contain a maximum 15  characters"
      	},
      	zipcode_2:
      	{
      		 minlength:"Zipcode should contain a minimum 3  characters",
             maxlength:"Zipcode should contain a maximum 15  characters"

      	},
      	department:
      	{
      		 minlength:"Department should contain a minimum 3  characters",
             maxlength:"Department should contain a maximum 30  characters"
      	},
      	managersupervisor:
      	{
      		 minlength:"Manager/Supervisor should contain a minimum 3  characters",
             maxlength:"Manager/Supervisor should contain a maximum 30  characters"

      	},
      	cell:
      	{
      		 minlength:"Cell number should contain a minimum 5  characters",
             maxlength:"Cell number should contain a maximum 20  characters",
             integer:"Cell number must be numeric"
	
      	},
      	fax:
      	{
      		 minlength:"Fax should contain a minimum 5  characters",
             maxlength:"Fax should contain a maximum 20  characters",
             integer:"Fax must be numeric"
      	},
    	  
    	  email:   {
              required        :   "Email cant be empty.",
              email           :   "Please enter a valid email",
                   },
    	  
          retype:   {
                required        :   "Retype password",
                equalTo           :   "Password mismatch",
                    },
          password:   {
                 required        	:   "Enter password",
                 minlength           :   "Password should contain minimum 8 and maximum 15 characters",
                 minlength           :   "Password should contain minimum 8 and maximum 15 characters",
                   },
    	  
    	  
    	  pricingoption:"Select any pricing option",
    	  termsconditions:"Accept terms and conditions",
    	  policies:"privacy and security policies", 
    	  
    	  captcha: {
    		  required        :   "Enter captcha value",
              equalTo         :   "Enter correct captha value",
          }
      }
    });
  });