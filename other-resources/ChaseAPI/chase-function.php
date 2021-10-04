<?php 
	/////////////===================chase payment function start ================///////////////
 
function pay(){
    
	 // print_r($_GET); die();
	    
		  
		   
    /// For Test
	  $url="https://orbitalvar1.paymentech.net";
    	$payment_info['username']= "XXXXXXXX";
		$payment_info['password'] ="XXXXXXXX";
		$payment_info['merchant_id']="XXXXXXXX";
		$payment_info['terminal_id']="001";
		
		// For Live
		// $url="https://orbital1.paymentech.net";
		//$payment_info['username']= "XXXXXX";
		//$payment_info['password'] ="XXXXXX";
		//$payment_info['merchant_id']="XXXXXX";
		//$payment_info['terminal_id']="001";
		
		$payment_info['amount']=str_replace('.','',sprintf('%.2f',trim($_POST['price']))); // formating based on chasepay documentation
	   // $payment_info['amount']='26';
		
		$payment_info['ccnumber']=trim($_POST['card_num']);
		$payment_info['ccexp']=trim($_POST['exp']);
		$payment_info['cvv']=trim($_POST['cvv']);
        $payment_info['CardBrand']='';
		
		//$payment_info['ccnumber']="4788250000028291";
		//$payment_info['ccexp']="0913";
		//$payment_info['cvv']="111";
		


		
		$payment_info['name']=trim($_POST['f_name']);
		$payment_info['address']=trim($_POST['address']);
		$payment_info['state']=trim($_POST['state']);
		$payment_info['city']=trim($_POST['city']);
		$payment_info['zip']=trim($_POST['zip']);
		$payment_info['country']=trim($_POST['country']);
		$payment_info['phone']=trim($_POST['phone']);
		$payment_info['email']=trim($_POST['email']);
		
		$payment_info['invoice_id']=trim($_POST['order_id']);
		
		$rawResponse=$this->chase_payment($url, $payment_info );

$parser = xml_parser_create('UTF-8');
xml_parse_into_struct($parser, $rawResponse, $response);
//var_dump($response);

$respArr=$this->parseXmlResponse($response);
//echo "<pre>";
//print_r($respArr);
//echo "</pre>";

return $respArr;

	}
	
	function get_chase_error($parsedResArr){
		
		$error=array();
		
	if($parsedResArr['PROCSTATUS'] == '0' && $parsedResArr['APPROVALSTATUS'] == '1' && $parsedResArr['RESPCODE'] == '00')
		{
			/*
			It is the only element that is returned in
			all response scenarios. It identifies whether transactions
			have successfully passed all of the Gateway edit checks.
			0 – Success
			*/
			//echo "successful";
			return true;			
		}
		else 
		{
			// First Track Varibales
			//$this->chase_QuickResponse 	= $parsedResArr['QuickResponse'];
			$this->chase_ProcStatus 	= $parsedResArr['PROCSTATUS'];
			$this->chase_StatusMsg		= $parsedResArr['STATUSMSG'];
			
			
			//echo "unsuccessful";
			switch($parsedResArr['RESPCODE'])
			{
				case '04' :
				{
					$error[] = 'Card is in Decline State';	
					break;
				}
				case '05' :
				{
					$error[] =  'Card is not Honored';
					break;
				}
				case '06' :
				{
					$error[] = 'Unsupported Error in Cart';
					break;
				}
				case '13' :
				{
					$error[] = 'Bad Amount';
					break;
				}
				case '42' :
				{
					$error[] = 'Account Not Active';
					break;
				}
				case '33' :
				{
					$error[] = 'Card is expired';
					break;
				}
				case '68' :
				{
					$error[] = 'Invalid CC Number';
					break;
				}
				default	:
				{
					break;
					//$this->error[] ='Resp Code - '.$parsedResArr['RespCode'];					
				}
			}
			
			if($parsedResArr['CVV2RESPCODE']!='M')
			{
				
				switch($parsedResArr['CVV2RESPCODE'])
				{
					case 'N' :
					{
						$error[] = 'The CVV is not matched  with the Card No';
						break;
					}
					case 'P' :
					{
						$error[] = 'The CVV is Not Processed';
						break;
					}
					case 'S' :
					{
						$error[] = 'The CVV Should have been present';
						break;
					}
					case 'U' :
					{
						$error[] = 'The CVV is Unsupported by Issuer/Issuer unable to process request';
						break;
					}
					case 'I' :
					{
						break;
					}
					case 'Y' :
					{
						$error[] = 'The CVV is Invalid';
						break;
					}
					default	: break; //$this->error[] = 'CVV2RespCode Code - '.$parsedResArr['CVV2RespCode']; 
								
				}			
			}
			
			switch($parsedResArr['AVSRESPCODE'])
			{
				case 'D' :	
				{
					$error[]	= 'The Zipcode is not Match with the Card';
					break;
				}
				case 'G' :
				{										   
					$error[]	= 'The Zipcode is not Match with the Card';						
					break;
				}
				case '4' :
				{
					$error[]	= 'Issuer does not participate in AVS';
					break;
				}				
				default	: break;//$this->error[] = 'AVSRespCode Code - '.$parsedResArr['AVSRespCode'];
			}	
			
			return $error;			
		}	
		
	}
	
	function parseXmlResponse($xmlResponse)
	{
		$newResArr = array();
		foreach($xmlResponse as $val)
		{
			$tagval=$val['tag'];
			if(($val['tag']!='Response') && ($val['tag']!='NewOrderResp'))	{	
				if(isset($val['value'])){
					$newResArr[$tagval]=$val['value'];
					}
					else{
					$newResArr[$tagval]='';
					}
				}
		}
	    return $newResArr;
	}
	
	function chase_payment($url, $payment_info ){
    $post_string = '<?xml version="1.0" encoding="UTF-8"?>
            <Request>
                        <NewOrder>
                                    <OrbitalConnectionUsername>'.$payment_info['username'].'</OrbitalConnectionUsername>
                                    <OrbitalConnectionPassword>'.$payment_info['password'].'</OrbitalConnectionPassword>
                                    <IndustryType>MO</IndustryType>
                                    <MessageType>AC</MessageType>
                                    <BIN>000002</BIN>
                                    <MerchantID>'.$payment_info['merchant_id'].'</MerchantID>
                                    <TerminalID>'.$payment_info['terminal_id'].'</TerminalID>
                                    <CardBrand>'.$payment_info['CardBrand'].'</CardBrand>
                                    <AccountNum>'.$payment_info['ccnumber'].'</AccountNum>
                                    <Exp>'.$payment_info['ccexp'].'</Exp>
                                   <CurrencyCode>840</CurrencyCode> 
                                    <CurrencyExponent>2</CurrencyExponent>   
                                   <CardSecVal>'.$payment_info['cvv'].'</CardSecVal>
                                    <AVSzip>'.$payment_info['zip'].'</AVSzip>
                                    <AVSaddress1>'.$payment_info['address'].'</AVSaddress1>                                   
                                    <AVScity>'.$payment_info['city'].'</AVScity>
                                    <AVSstate>'.$payment_info['state'].'</AVSstate>
                                    <AVSphoneNum>'.$payment_info['phone'].'</AVSphoneNum>
                                    <AVSname>'.$payment_info['name'].'</AVSname>
                                    
                                    <OrderID>'.$payment_info['invoice_id'].'</OrderID>
                                    <Amount>'.$payment_info['amount'].'</Amount>
                                    
                                      <CustomerEmail>'.$payment_info['email'].'</CustomerEmail>
                        </NewOrder>
            </Request>';
			// <Amount>'.$payment_info['amount'].'</Amount>
            // <CardSecVal>'.$payment_info['cvv'].'</CardSecVal>
          //  echo 'test: ';
            
            // Build header as array for cURL option
            $header = array("POST /AUTHORIZE HTTP/1.0");
            $header[] = "MIME-Version: 1.0";
            $header[] = "Content-type: application/PTI56";
            $header[] = "Content-length: ".strlen($post_string);
            $header[] = "Content-transfer-encoding: text";
            $header[] = "Request-number: 1";
            $header[] = "Document-type: Request";
            $header[] = "Interface-Version: 0.3";    

   
    // Define cURL options, then connect to server while saving response
            
            $ch = curl_init($url);
            curl_setopt($ch, CURLOPT_FRESH_CONNECT, TRUE);
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
            curl_setopt($ch, CURLOPT_HTTPHEADER, $header);
            curl_setopt($ch, CURLOPT_POST, TRUE);
            curl_setopt($ch, CURLOPT_POSTFIELDS, $post_string);
            
            $result = curl_exec($ch);
            
    /**
     * Close the handle
     */
    curl_close($ch);
   
   
   // echo 'RESULT: '.$result;  
   
    return $result;
}
 
 
 ///////// =================== chase payment ends//////////////////
	



