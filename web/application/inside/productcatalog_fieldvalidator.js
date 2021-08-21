SM.Corporate.ProductCatalog.loadValidator = function()
{

	SM.Corporate.ProductCatalog.popupFormValidator = $("#ProductCatlogForm").validate(
	{
		// ignore: "",
		rules :
		{
			popup_item_name :
			{
				required : true,
				minlength : 1,
				maxlength : 80,

			},
			popup_item_description :
			{
				required : true,
				minlength : 1,
				maxlength : 80
			},
			popup_item_part_no :
			{
				required : true,
				minlength : 1,
				maxlength : 80
			},
			popup_item_sku :
			{
				required : true,
				minlength : 1,
				maxlength : 80
			},
			popup_item_quantity :
			{
				required : true,
				number : true
			},
			popup_item_price :
			{
				required : true,
				number : true
			},
			popup_item_tax :
			{
				required : true,
				number : true
			},

		},
		messages :
		{

			popup_item_name :
			{
				required : "Enter your Item",
				minlength : "Minimum 1  characters",
				maxlength : "Maximum 80  characters"

			},
			popup_item_description :
			{
				required : "Enter your Item Description",
				minlength : "Minimum 1  characters",
				maxlength : "Maximum 80  characters"

			},
			popup_item_part_no :
			{
				required : "Enter company Part number",
				minlength : "Minimum 1  characters",
				maxlength : "Maximum 80  characters"
			},
			popup_item_sku :
			{
				required : "Enter the SKU",
				minlength : "Minimum 1  characters",
				maxlength : "Maximum 80  characters"
			},

			popup_item_quantity :
			{
				required : "Enter the Quantity",
				integer : "Quantity number must be numeric"

			},
			popup_item_price :
			{
				required : "Enter the Price",
				integer : "Price number must be numeric"

			},
			popup_item_tax :
			{
				required : "Enter the Tax",
				integer : "Tax number must be numeric"

			},

		}
	});

	SM.Corporate.ProductCatalog.popupFormValidatorAddWindow = $("#ProductCatlogFormAddWindow").validate(
	{
		// ignore: "",
		rules :
		{
			item_name :
			{
				required : true

			},
			item_description :
			{
				required : true
			},
			item_part_no :
			{
				required : true
			},
			item_sku :
			{
				required : true
			},
			item_quantity :
			{
				required : true,
				number : true
			},
			item_price :
			{
				required : true,
				number : true
			},
			item_tax :
			{
				required : true,
				number : true
			}
		},
		messages :
		{
			popup_item_name :
			{
				required : " "

			},
			popup_item_description :
			{
				required : " "

			},
			popup_item_part_no :
			{
				required : " "
			},
			popup_item_sku :
			{
				required : " "
			},

			popup_item_quantity :
			{
				required : " ",
				integer : " "

			},
			popup_item_price :
			{
				required : " ",
				integer : " "

			},
			popup_item_tax :
			{
				required : " ",
				integer : " "

			},
		}

	});
};
