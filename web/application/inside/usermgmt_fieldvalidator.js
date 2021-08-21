function loadValidator()
{

    $("#usermgmtfrm").validate({
        //ignore: "",
        rules: {
            firstname:
                    {
                        required: true,
                        minlength: 3,
                        maxlength: 25,
                    },
            lastname:
                    {
                        required: true,
                        minlength: 3,
                        maxlength: 25
                    },
            email:
                    {
                        required: true,
                        email: true,
                    },
            phone:
                    {
                        required: true,
                        minlength: 5,
                        maxlength: 20,
                        phoneUS: false,
                        integer: true
                    },
            zipcode:
                    {
                        minlength: 3,
                        maxlength: 15
               },
            cell:
                    {
                        minlength: 5,
                        maxlength: 20,
                        integer: true
               },
            fax:
                    {
                        minlength: 5,
                        maxlength: 20,
                        integer: true
               },
        },
        messages: {
            firstname:
                    {
                        required: "Enter your first name",
                        minlength: "First name should contain a minimum 3  characters",
                        maxlength: "First name should contain a maximum 25  characters"

                    },
            lastname:
                    {
                        required: "Enter your last name",
                        minlength: "Last name should contain a minimum 3  characters",
                        maxlength: "Last name should contain a maximum 25  characters"

                    },
            phone:
                    {
                        required: "Enter company phone number",
                        minlength: "Phone number should contain a minimum 5  characters",
                        maxlength: "Phone number should contain a maximum 20  characters",
                        phoneUS: "Enter valid phone number",
                        integer: "Phone number must be numeric"

             },
            zipcode:
                    {
                        minlength: "Zipcode should contain a minimum 3  characters",
                        maxlength: "Zipcode should contain a maximum 15  characters"
                    },
            cell:
                    {
                        minlength: "Cell number should contain a minimum 5  characters",
                        maxlength: "Cell number should contain a maximum 20  characters",
                        integer: "Cell number must be numeric"

                    },
            fax:
                    {
                        minlength: "Fax should contain a minimum 5  characters",
                        maxlength: "Fax should contain a maximum 20  characters",
                        integer: "Fax number must be numeric"

                    },
            email:
                    {
                        required: "Email cant be empty.",
                        email: "Please enter a valid email",
                    },
        }
    });

}
