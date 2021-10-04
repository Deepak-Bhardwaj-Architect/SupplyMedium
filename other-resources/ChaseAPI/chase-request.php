<?php
include_once('chase-function.php');

$status=pay();
if($status===true){
echo "payment success"	;
} else {
	// errior details in array format
echo "<pre>";
print_r($status);
echo "</pre>";
	
}
?>