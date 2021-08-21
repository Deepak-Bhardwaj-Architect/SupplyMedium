<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
<?php

$order_id=time();
?>
<form name="registration_form" method="post" action="chase-request.php" >

<fieldset style="width:90%;">
<legend> Plan Details</legend>
<table class="reg_form" style="text-align:left;">

<tr>
<th colspan="2"> Order Id:  </th>
<td><?php echo $order_id; ?><input type="hidden" name="order_id" id="order_id" value="<?php echo $order_id; ?>"  /> </td>
<td id="plan_code_mark"></td>
</tr>
<tr>
<th colspan="2" > Price :  </th>
<td>
<input type="text" name="price" id="price"   > USD
 </td>
<td id="amount_mark"></td>
</tr>
</table>
</fieldset>
<fieldset style="width:90%;">
<legend> Card Details</legend>

<table class="reg_form" style="text-align:left;">
<tr>
<th> Card Number</th>
<td> <input type="text" name="card_num" id="card_num" value="4111111111111111" maxlength="16"  ></td>
<td id="card_num_mark"></td>
</tr>
<tr>
<th> CVV </th>
<td>  <input type="text" name="cvv" id="cvv" maxlength="3" value="111"  > </td>
<td id="cvv_mark"></td>
</tr>
<tr>
<th> Expiry Date</th>
<td>  <input type="text" name="exp" id="exp" maxlength="4" value="1013"  ><br/>(mmdd ex: 0213 i.e feb,2013 ) </td>
<td id="expiry_mark"></td>
</tr>
</table>

</fieldset>
<fieldset style="width:90%;">
<legend> Personal Details</legend>
					   <table style='width:100%;'>
                       
                       
					      <tr><td>Name</td><td><input type="text" name="f_name" id="f_name"  value="John Lewis" /></td></tr><tr><td colspan='2' align="right" id="fname_err"></td></tr>

						 
                           
						  <tr><td>Phone </td><td><input type="text" name="phone" id="phone"  value="1694929121" /></td></tr><tr><td id="phn_err" colspan='2' align="right"></td></tr>
 <tr><td>Email</td><td><input type="text" name="email" id="email" value="john@gmail.com"  /></td></tr><tr><td id="mail1_err" colspan='2' align="right"></td></tr>
						   <tr><td>Address </td><td><input type="text" name="address" id="address" value="27 MG Rd"  /></td></tr> 

						 
							 <tr><td> Country</td>
                                                             <td>
                                                              
                                                                 <select style="width:72%" name="country" id="country" >
                                                                    
                                                                     <option value="US" selected="selected" >US</option>
                                                                  
                                                                 </select>
                                                             </td>
                                                         </tr>

						   <tr><td> state</td><td><input type="text" name="state" id="state" value="CA" maxlength="2" />

						   <tr><td> City</td><td><input type="text" name="city" id="city" value="Los Angeles"  /></td></tr>

						   <tr><td> Zip</td><td><input type="text" name="zip" id="zip"  value="11111" /></td></tr>
                                                 
					
<tr><td></td><td><br/><input type="submit" name="sub" id="sub" value="Submit"  /><br/><br/></td></tr>
				     </table>
</fieldset>

					  </form>
</body>
</html>