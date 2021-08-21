package servlet.vendorregn;

import java.text.SimpleDateFormat;
import java.util.List;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import core.regn.RegnData;
import core.vendorregn.VendorInquireData;
import core.vendorregn.VendorRegnData;
import utils.ErrorMaster;

public class VRJSONComposer
{

	private String companyName_;
        private static ErrorMaster errorMaster_ = null;



	
	/*Constructor*/
	
	public VRJSONComposer( )
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( ); 
	}
	
/*Constructor*/
	
	public VRJSONComposer( String companyName )
	{
		companyName_ = companyName;
	}
	
	/*
	 * Method: composeVendorListJSON
	 * 
	 * Input: int response, List<VendorRegnData> vendorRegnDataList
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the vendorRegnDataList and compose JSON string
	 */
	
	public String composeVendorListJSON( int responseCode, List<VendorRegnData> vendorRegnDataList )
	{
		/*VendorRegnDataList contains, logged in company's regnData, profile data, 
		 * vendor's regnData, vendor's profile data and inquire data*/
		//System.out.print("enterrrrrrrrrrrrrrrrrr==========================>");
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		//System.out.print("enterrrrrrrrrrrrrrrrrr5==========================>");
		//errorMaster_.insert( "Response Code="+responseCode );
                //System.out.print("enterrrrrrrrrrrrrrrrrr2==========================>");
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 3630 )
		{
                    if(vendorRegnDataList.toString().equals("[]"))
                    {
                        jsonStr = "{ \"result\" : \"success\" } "; 
                        //System.out.println("final1===============>"+jsonStr);
                    }
                    else
                    {
                   //System.out.print("enterrrrrrrrrrrrrrrrrr3==========================>"+vendorRegnDataList );
			//jsonMap.put( "result", "success" );
			
			String msg = "Info::VendorRegnServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			//String responseString = ErrorCodeConfigReader.instance( ).get(
			  //      "" + responseCode );
			
			jsonStr = "{ \"result\" : \"success\",  ";
			//jsonStr = jsonStr + "\"message\" : \""+responseString+"\", ";
			jsonStr = jsonStr + "\"vendors\" : [";
			
			int iterator = 0;
			
			for( VendorRegnData vendorRegnData : vendorRegnDataList )
            {
                //System.out.print("enterrrrrrrrrrrrrrrrrr3==========================>"+iterator);
				/*Vendor regn data*/
				jsonStr = jsonStr + "{ \"vendorregnid\" : \"" + vendorRegnData.vendorRegnId_ + "\", ";
				jsonStr = jsonStr + " \"regnkey\" : \"" + vendorRegnData.regnKey_.toString( ) + "\", ";
				jsonStr = jsonStr + " \"vendorregnkey\" : \"" + vendorRegnData.vendorRegnKey_.toString( ) + "\", ";
				jsonStr = jsonStr + " \"companylevel\" : \"" + vendorRegnData.companyLevel_ + "\", ";
				jsonStr = jsonStr + " \"businesscontact\" : \"" + vendorRegnData.businessContact_ + "\", ";
				jsonStr = jsonStr + " \"businesstaxid\" : \"" + vendorRegnData.businessTaxId_ + "\", ";
				jsonStr = jsonStr + " \"naicscode\" : \"" + vendorRegnData.NAICSCode_ + "\", ";
				jsonStr = jsonStr + " \"w9taxformflag\" : \"" + vendorRegnData.w9TaxFormFlag_ + "\", ";
				jsonStr = jsonStr + " \"w9taxformpath\" : \"" + vendorRegnData.w9TaxFormPath_ + "\", ";
				jsonStr = jsonStr + " \"businesssize\" : \"" + vendorRegnData.businessSize_ + "\", ";
				jsonStr = jsonStr + " \"businessclassification\" : \"" + vendorRegnData.businessClassification_ + "\", ";
				jsonStr = jsonStr + " \"additionaldetails\" : \"" + vendorRegnData.additionalDetails_.trim( ) + "\", ";
				jsonStr = jsonStr + " \"regnstatus\" : \"" + vendorRegnData.regnStatus_ + "\", ";
				jsonStr = jsonStr + " \"requestsendertype\" : \"" + vendorRegnData.requestSenderType_ + "\", ";
				
				SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MMM-yyyy ");
	            String createdTimeStamp =	dateFormat.format( vendorRegnData.createdTimestamp_  );
	            jsonStr = jsonStr + " \"createdtimestamp\" : \"" + createdTimeStamp + "\", ";
				 
				/*Logged in company's Regn Data*/
				jsonStr = jsonStr + " \"businesscategory\" : \"" + vendorRegnData.regnData_.businessCategory_ + "\", ";
				jsonStr = jsonStr + " \"companyname\" : \"" + vendorRegnData.regnData_.companyName_ + "\", ";
				jsonStr = jsonStr + " \"companytype\" : \"" + vendorRegnData.regnData_.companyType_ + "\", ";
				
				/*Logged in company's profile data*/
				jsonStr = jsonStr + " \"department\" : \"" + vendorRegnData.profileData_.department_ + "\", ";
				jsonStr = jsonStr + " \"phoneno\" : \"" + vendorRegnData.profileData_.phoneNo_ + "\", ";
				jsonStr = jsonStr + " \"cellno\" : \"" + vendorRegnData.profileData_.cellNo_ + "\", ";
				jsonStr = jsonStr + " \"faxno\" : \"" + vendorRegnData.profileData_.faxNo_ + "\", ";
				jsonStr = jsonStr + " \"emailid\" : \"" + vendorRegnData.profileData_.emailId_ + "\", ";
				
				/*logged in user's mailing address data*/
				jsonStr = jsonStr + " \"address\" : \"" + vendorRegnData.profileData_.mailingAddr_.address_.trim( ) + "\", ";
				jsonStr = jsonStr + " \"city\" : \"" + vendorRegnData.profileData_.mailingAddr_.city_ + "\", ";
				jsonStr = jsonStr + " \"country\" : \"" + vendorRegnData.profileData_.mailingAddr_.countryRegion_ + "\", ";
				jsonStr = jsonStr + " \"addresstype\" : \"" + vendorRegnData.profileData_.mailingAddr_.addressType_ + "\", ";
				jsonStr = jsonStr + " \"state\" : \"" + vendorRegnData.profileData_.mailingAddr_.state_ + "\", ";
				jsonStr = jsonStr + " \"zipcode\" : \"" + vendorRegnData.profileData_.mailingAddr_.zipcode_ + "\", ";
				
				
				/*Logged in company's vendor's Regn Data*/
				jsonStr = jsonStr + " \"vendorbusinesscategory\" : \"" + vendorRegnData.vendorRegnData_.businessCategory_ + "\", ";
				jsonStr = jsonStr + " \"vendorcompanyname\" : \"" + vendorRegnData.vendorRegnData_.companyName_ + "\", ";
				jsonStr = jsonStr + " \"vendorcompanytype\" : \"" + vendorRegnData.vendorRegnData_.companyType_ + "\", ";
				
				/*Logged in company's vendor's profile data*/
				jsonStr = jsonStr + " \"vendorbusinesscontact\" : \"" + vendorRegnData.vendorContact_ + "\", ";
				jsonStr = jsonStr + " \"vendordepartment\" : \"" + vendorRegnData.vendorProfileData_.department_ + "\", ";
				jsonStr = jsonStr + " \"vendorphoneno\" : \"" + vendorRegnData.vendorProfileData_.phoneNo_ + "\", ";
				jsonStr = jsonStr + " \"vendorcellno\" : \"" + vendorRegnData.vendorProfileData_.cellNo_ + "\", ";
				jsonStr = jsonStr + " \"vendorfaxno\" : \"" + vendorRegnData.vendorProfileData_.faxNo_ + "\", ";
				jsonStr = jsonStr + " \"vendoremailid\" : \"" + vendorRegnData.vendorProfileData_.emailId_ + "\", ";
				
				/*logged in user's vendor's mailing address data*/
				jsonStr = jsonStr + " \"vendoraddress\" : \"" + vendorRegnData.vendorProfileData_.mailingAddr_.address_.trim( ) + "\", ";
				jsonStr = jsonStr + " \"vendorcity\" : \"" + vendorRegnData.vendorProfileData_.mailingAddr_.city_ + "\", ";
				jsonStr = jsonStr + " \"vendorcountry\" : \"" + vendorRegnData.vendorProfileData_.mailingAddr_.countryRegion_ + "\", ";
				jsonStr = jsonStr + " \"vendoraddresstype\" : \"" + vendorRegnData.vendorProfileData_.mailingAddr_.addressType_ + "\", ";
				jsonStr = jsonStr + " \"vendorstate\" : \"" + vendorRegnData.vendorProfileData_.mailingAddr_.state_ + "\", ";
				jsonStr = jsonStr + " \"vendorzipcode\" : \"" + vendorRegnData.vendorProfileData_.mailingAddr_.zipcode_ + "\", ";
				
				
				/*Inquire data*/
								
				jsonStr = jsonStr + " \"vendorinquirearr\" : [";
                
				int iter = 0;
				
				for( VendorInquireData vendorInquireData: vendorRegnData.vendorInquireDataList_ )
                {
					jsonStr = jsonStr + "{ \"vendorregnid\" : \"" + vendorInquireData.vendorRegnId_+ "\", ";
					jsonStr = jsonStr + " \"inquirefromregnkey\" : \"" + vendorInquireData.regnKey_.companyPhoneNo_ + "\", ";
					jsonStr = jsonStr + " \"inquiretoregnkey\" : \"" + vendorInquireData.vendorRegnKey_.companyPhoneNo_ + "\", ";
					jsonStr = jsonStr + " \"createdts\" : \"" + vendorInquireData.createdTs_ + "\", ";
					jsonStr = jsonStr + " \"inquiredetails\" : \"" + vendorInquireData.inquireDetails_ + "\"";
					
					jsonStr = jsonStr + "}";
					iter = iter + 1;
	                
	                if( vendorRegnData.vendorInquireDataList_.size( ) > iter )
	                {
	                	jsonStr = jsonStr + ",";
	                }
                }
				
				jsonStr = jsonStr + "]}";
				
				iterator = iterator + 1;

				if( vendorRegnDataList.size( ) > iterator )
				{
					jsonStr = jsonStr + ",";
				}
				//jsonStr = jsonStr + "}";
			}
				
			jsonStr = jsonStr + "]}";
                    }
                    //System.out.println("final===============>"+jsonStr);
                        
		}
		else 
		{
			//jsonMap.put( "result", "failed" );
			
			jsonStr = "{ \"result\" : \"failed\",  ";
			
			String msg = "Info::VendorRegnServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
			
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
			
			//jsonMap.put( "message", responseString );	
		
		}
		//System.out.println("final===============>"+jsonStr);
		//jsonStr = new Gson( ).toJson( jsonMap );
		
		//errorMaster_.insert( "json str="+jsonStr );

		return jsonStr;
	}
	
	/*
	 * Method: composeNRListJSON
	 * 
	 * Input: int response, List<RegnData> regnDataList
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the regnDataList and compose JSON string
	 */
	
	public String composeNRListJSON( int responseCode, List<RegnData> regnDataList )
	{
            //System.out.println("regnDataList==============>"+regnDataList);
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		//errorMaster_.insert( "Response Code="+responseCode );
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 3640 )
		{
                       
			//jsonMap.put( "result", "success" );
			
			String msg = "Info::VendorRegnServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );
			
			jsonStr = "{ \"result\" : \"success\",  ";
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\", ";
			jsonStr = jsonStr + "\"NRvendors\" : [";
			
			int iterator = 0;
			
			for( RegnData regnData : regnDataList )
            {
				/*Vendor regn data*/
				jsonStr = jsonStr + "{ \"companykey\" : \"" + regnData.companyRegnKey_.toString( ) + "\", ";
				jsonStr = jsonStr + "\"companyname\" : \"" + regnData.companyName_ + "\"} ";
				
				iterator = iterator + 1;

				if( regnDataList.size( ) > iterator )
				{
					jsonStr = jsonStr + ",";
				}
			}
				
			jsonStr = jsonStr + "]}";
                    }		
		else 
		{
			//jsonMap.put( "result", "failed" );
			
			jsonStr = "{ \"result\" : \"failed\",  ";
			
			String msg = "Info::VendorRegnServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
			
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
			
			//jsonMap.put( "message", responseString );	
		
		}
		
		//jsonStr = new Gson( ).toJson( jsonMap );
		
		//errorMaster_.insert( "json str="+jsonStr );
                //System.out.println("jsonStr==============>"+jsonStr);
		return jsonStr;
	}
	
	/*
	 * Method: composeProcessResult
	 * 
	 * Input: int response
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the response and compose JSON string
	 */
	
	public String composeProcessResult( int responseCode )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		//errorMaster_.insert( "Response Code="+responseCode );
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 3600 || responseCode == 3650 || responseCode == 3610 || responseCode == 3620 )
		{
			//jsonMap.put( "result", "success" );
			
			String msg = "Info::VendorRegnServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String[ ] valueArr = { companyName_ };
			
			String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
			
			jsonStr = "{ \"result\" : \"success\",  ";
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";

		}
		else 
		{
			//jsonMap.put( "result", "failed" );
			
			jsonStr = "{ \"result\" : \"failed\",  ";
			
			String msg = "Info::VendorRegnServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String[ ] valueArr = { companyName_ };
			
			String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
			
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
			
			//jsonMap.put( "message", responseString );	
		
		}
		
		//jsonStr = new Gson( ).toJson( jsonMap );
		
		//errorMaster_.insert( "json str="+jsonStr );

		return jsonStr;
	}
	
}
