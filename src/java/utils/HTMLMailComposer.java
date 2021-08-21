/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any.
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 */


package utils;

import core.trans.InvoiceData;
import core.trans.InvoiceItemData;
import core.trans.POData;
import core.trans.POItemData;
import core.trans.QuoteData;
import core.trans.QuoteItemData;
import core.trans.RFQItemData;
import java.util.List;

/*
 * This is used to compose the HTML Mail for given data.
 */

public class HTMLMailComposer
{

	/*
	 * Method : composeRegActivationMail( ) 
	 * 
	 * Input : company admin email address,company name and uuid 
	 * 
	 * Return : HTML mail String 
	 * 
	 * Purpose: It is used to create the registration activation HTML mail content 
	 * using given data.
	 */
	
	public String composeRegActivationMail( String uuid, String emailId,String firstName, String companyName )
	{
		String path = AppConfigReader.instance( ).get( "SERVLET_PATH" );
		
		StringBuffer message = new StringBuffer( "" );
		
		message.append( "<html><body style='font-family:Verdana;min-width:800px;'>" );
		message.append( "<div style='width:100%;background-color:#fff;float:left;'> " );
		message.append( "<div style='height:120px;width:800px;margin-left:auto;margin-right:auto;'> " );
		
		// Logo image
		message.append( "<a href='" );
		message.append( path+"/index.jsp" );
		message.append( "'><img src='" );
		message.append( path+"/Views/Utils/images/logo.png" );
		message.append( "' width='325px' height='100px' style='margin-top:10px;'/></a></div>" );
		
		// Border and background color 
		message.append( "<div style='width:100%;background-color:#f27f02;float:left'>");
		message.append( "<div style='height:10px;width:800px;margin-left:auto;margin-right:auto;'></div></div>" );
		message.append( "<div style='width:100%;background-color:#EFF4F7;'>" );
		message.append( "<div style='height:800px;width:800px;margin-left:auto;margin-right:auto;'>" );
		
		// Greeting message.
		message.append( "<div style='height:130px;width:800px;font-size:25px;color:#199096;text-align:center;line-height:50px;'> Welcome " );
		message.append( firstName+"<BR/> Greetings from Supply Medium !" );
		message.append( "</div>" );
		
		// Supply Medium description
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'>" );
		message.append( "SupplyMedium is a quick and smart way to get connected with small business network.</div>" );
		
		// User name details
		message.append( "<div style='height:120px;width:600px;color:#434446;text-align:center;" );
		message.append( "border:1px solid #c8c8c8;background-color:#fff;margin-top:80px;margin-left:100px;'>" );
		message.append( "<div style='text-align:center;color:#199096;font-size:20px;margin-top:20px;'>" );
		message.append( " Your user name is</div>" );
		
		message.append( "<div style='color:#f27f02;font-size:25px;margin-top:10px;text-decoration:none;'>" );
		message.append( emailId );
		message.append( "</div></div>" );
		
		
		// Link 
		message.append( "<a style='text-decoration:none;' href='" );
		message.append( path+"/RegnActivateServlet?RequestType=RegistrationActivate&name="+companyName+"&key=" + uuid+"'>" );
		message.append( "<input type='button' style='cursor: pointer;border-style:none;height:60px;width:324px;margin-left:225px;background-color:#f27f02;" );
		message.append( "margin-top:40px;font-size:25px;color:#fff;' value='Activate Your Account' />" );
		message.append( "</a>" );
		
		// Support Link
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;" );
		message.append( "text-align:left;margin-top:100px;margin-left:50px;'> " );
		message.append( "If you are having problems signing up please contact a member of our support staff at " );
		message.append( "webmaster@supplymedium.com</div>" );
		
		message.append( "<div style='height:1px;width:100%;background-color:#d2d3d5'> </div>" );
		message.append( "<div style='height:1px;width:100%;background-color:#fff'> </div>" );
		
		// Footer
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;margin-left:50px;" );
		message.append( "text-align:left;margin-top:50px;'> " );
		message.append( "&#169; 2013 Supply Medium Inc,<BR/>" );
		message.append( "All Rights Reserved,<BR/>" );
		message.append( "New York. USA<BR/>" );
		message.append( "Redefining Social Commerce!" );
		
		message.append( "</div></div></div></div>" );
		message.append( "</body></html>" );
				
		System.out.println( "message="+message.toString( ) );
		
		System.out.println("WEBKRIT====>>>>"+message.toString( ));return message.toString( );
	}
	

	/*
	 * Method : composeUserSignupMail( ) 
	 * 
	 * Input : user email id and first name
	 * 
	 * Return : HTML mail String 
	 * 
	 * Purpose: It is used to create the user signup html mail after successful signup
	 */
	
	public String composeUserSignupMail( String emailId, String firstName )
	{
		String path = AppConfigReader.instance( ).get( "SERVLET_PATH" );
		
		StringBuffer message = new StringBuffer( "" );
		
		message.append( "<html><body style='font-family:Verdana;min-width:800px;'>" );
		message.append( "<div style='width:100%;background-color:#fff;float:left;'> " );
		message.append( "<div style='height:120px;width:800px;margin-left:auto;margin-right:auto;'> " );
		
		// Logo image
		message.append( "<a href='" );
		message.append( path+"/index.jsp" );
		message.append( "'><img src='" );
		message.append( path+"/Views/Utils/images/logo.png" );
		message.append( "' width='325px' height='100px' style='margin-top:10px;'/></a></div>" );
		
		// Border and background color 
		message.append( "<div style='width:100%;background-color:#f27f02;float:left'>");
		message.append( "<div style='height:10px;width:800px;margin-left:auto;margin-right:auto;'></div></div>" );
		message.append( "<div style='width:100%;background-color:#EFF4F7;'>" );
		message.append( "<div style='height:800px;width:800px;margin-left:auto;margin-right:auto;'>" );
		
		// Greeting message.
		message.append( "<div style='height:130px;width:800px;font-size:25px;color:#199096;text-align:center;line-height:50px;'> Welcome " );
		message.append( firstName+"<BR/> Greetings from Supply Medium !" );
		message.append( "</div>" );
		
		// Supply Medium description
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px'>" );
		message.append( "Supply Medium is a quick and smart way to get connected with small business network.</div>" );
		
		// User name details
		message.append( "<div style='height:120px;width:600px;color:#434446;text-align:center;" );
		message.append( "border:1px solid #c8c8c8;background-color:#fff;margin-top:80px;margin-left:100px;'>" );
		message.append( "<div style='text-align:center;color:#199096;font-size:20px;margin-top:20px;'>" );
		message.append( " Your user name is</div>" );
		
		message.append( "<div style='color:#f27f02;font-size:25px;margin-top:10px;text-decoration:none;'>" );
		message.append( emailId );
		message.append( "</div></div>" );
		
		// Message
		message.append( "<div style='height:70px;width:600px;font-size:22px;color:#035458;text-align:center;padding-left:100px;margin-top:70px;'>" );
		message.append( "Once your account is approved by your Administrator, you shall receive a confirmation email with Login details." );
		message.append( "</div>" );
		
		// Support Link
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;" );
		message.append( "text-align:left;margin-top:100px;margin-left:50px;'> " );
		message.append( "If you are having problems signing up please contact a member of our support staff at " );
		message.append( "webmaster@supplymedium.com</div>" );
		
		message.append( "<div style='height:1px;width:100%;background-color:#d2d3d5'> </div>" );
		message.append( "<div style='height:1px;width:100%;background-color:#fff'> </div>" );
		
		// Footer
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;margin-left:50px;" );
		message.append( "text-align:left;margin-top:50px;'> " );
		message.append( "&#169; 2013 Supply Medium Inc,<BR/>" );
		message.append( "All Rights Reserved,<BR/>" );
		message.append( "New York. USA<BR/>" );
		message.append( "Redefining Social Commerce!" );
		
		message.append( "</div></div></div></div>" );
		message.append( "</body></html>" );
				
		System.out.println( "message="+message.toString( ) );
		
		System.out.println("WEBKRIT====>>>>"+message.toString( ));return message.toString( );
	}
	
	
	/*
	 * Method : sendUserStatusChangeMail( ) 
	 * 
	 * Input : user email id and first name
	 * 
	 * Return : HTML mail String 
	 * 
	 * Purpose: It is used to create the user signup html mail after successful signup
	 */
	
	public String sendUserStatusChangeMail( String emailId, String firstName, String status )
	{
		String path = AppConfigReader.instance( ).get( "SERVLET_PATH" );
		
		StringBuffer message = new StringBuffer( "" );
		
		message.append( "<html><body style='font-family:Verdana;min-width:800px;'>" );
		message.append( "<div style='width:100%;background-color:#fff;float:left;'> " );
		message.append( "<div style='height:120px;width:800px;margin-left:auto;margin-right:auto;'> " );
		
		// Logo image
		message.append( "<a href='" );
		message.append( path+"/index.jsp" );
		message.append( "'><img src='" );
		message.append( path+"/Views/Utils/images/logo.png" );
		message.append( "' width='325px' height='100px' style='margin-top:10px;'/></a></div>" );
		
		// Border and background color 
		message.append( "<div style='width:100%;background-color:#f27f02;float:left'>");
		message.append( "<div style='height:10px;width:800px;margin-left:auto;margin-right:auto;'></div></div>" );
		message.append( "<div style='width:100%;background-color:#EFF4F7;'>" );
		message.append( "<div style='height:950px;width:800px;margin-left:auto;margin-right:auto;'>" );
		
		// Greeting message.
		message.append( "<div style='height:130px;width:800px;font-size:25px;color:#199096;text-align:center;line-height:50px;'> Welcome " );
		message.append( firstName+"<BR/> Greetings from Supply Medium !" );
		message.append( "</div>" );
		
		// Supply Medium description
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px'>" );
		message.append( "Supply Medium is a quick and smart way to get connected with small business network.</div>" );
		
		// User name details
		message.append( "<div style='height:120px;width:600px;color:#434446;text-align:center;" );
		message.append( "border:1px solid #c8c8c8;background-color:#fff;margin-top:80px;margin-left:100px;'>" );
		message.append( "<div style='text-align:center;color:#199096;font-size:20px;margin-top:20px;'>" );
		message.append( " Your user name is</div>" );
		
		message.append( "<div style='color:#f27f02;font-size:25px;margin-top:10px;text-decoration:none;'>" );
		message.append( emailId );
		message.append( "</div></div>" );
		
		if( status.equals( UserStatus.status.ACTIVE.getValue( ) ))  // Mail content for account activated successfully
		{
			// Message
			message.append( "<div style='height:70px;width:600px;font-size:22px;color:#035458;text-align:center;padding-left:100px;margin-top:70px;'>" );
			message.append( "Your account has been successfully activated. Click on the below button to Login to SupplyMedium." );
			message.append( "</div>" );
			
			// Link 
			message.append( "<a style='text-decoration:none;' href='" );
			message.append( path+"/index.jsp'>" );
			message.append( "<input type='button' style='cursor: pointer;border-style:none;height:60px;width:324px;margin-left:225px;background-color:#f27f02;" );
			message.append( "margin-top:40px;font-size:25px;color:#fff;' value='Login' />" );
			message.append( "</a>" );
		}
		else if( status.equals( UserStatus.status.BLOCKED.getValue( ) ) ) // Mail content for account blocked
		{
			// Message
			message.append( "<div style='height:70px;width:600px;font-size:22px;color:#035458;text-align:center;padding-left:100px;margin-top:70px;'>" );
			message.append( "Your account has been blocked by Administrator." );
			message.append( "</div>" );
			
		}
		else if( status.equals( "Reject" )) // Mail content for user signup rejected by admin
		{
			// Message
			message.append( "<div style='height:70px;width:600px;font-size:22px;color:#035458;text-align:center;padding-left:100px;margin-top:70px;'>" );
			message.append( "Your signup request ha been rejected by Administrator" );
			message.append( "</div>" );
		}
		
		
		// Support Link
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;" );
		message.append( "text-align:left;margin-top:100px;margin-left:50px;'> " );
		message.append( "If you are having problems signing up please contact a member of our support staff at " );
		message.append( "webmaster@supplymedium.com</div>" );
		
		message.append( "<div style='height:1px;width:100%;background-color:#d2d3d5'> </div>" );
		message.append( "<div style='height:1px;width:100%;background-color:#fff'> </div>" );
		
		// Footer
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;margin-left:50px;" );
		message.append( "text-align:left;margin-top:50px;'> " );
		message.append( "&#169; 2013 Supply Medium Inc,<BR/>" );
		message.append( "All Rights Reserved,<BR/>" );
		message.append( "New York. USA<BR/>" );
		message.append( "Redefining Social Commerce!" );
		
		message.append( "</div></div></div></div>" );
		message.append( "</body></html>" );
				
		System.out.println( "message="+message.toString( ) );
		
		System.out.println("WEBKRIT====>>>>"+message.toString( ));return message.toString( );
	}
	
	/*
	 * Method : composeUserActivationLinkMail( ) 
	 * 
	 * Input :  uuid,  emailId,  firstName
	 * 
	 * Return : HTML mail String 
	 * 
	 * Purpose: Admin add new user, then user activation mail send to user. This method compose that mail
	 * HTML mail message.
	 */
	
	public String composeUserActivationLinkMail( String uuid, String emailId, String firstName, String companyName )
	{
		String path = AppConfigReader.instance( ).get( "SERVLET_PATH" );
		
		StringBuffer message = new StringBuffer( "" );
		
		message.append( "<html><body style='font-family:Verdana;min-width:800px;'>" );
		message.append( "<div style='width:100%;background-color:#fff;float:left;'> " );
		message.append( "<div style='height:120px;width:800px;margin-left:auto;margin-right:auto;'> " );
		
		// Logo image
		message.append( "<a href='" );
		message.append( path+"/index.jsp" );
		message.append( "'><img src='" );
		message.append( path+"/Views/Utils/images/logo.png" );
		message.append( "' width='325px' height='100px' style='margin-top:10px;'/></a></div>" );
		
		// Border and background color 
		message.append( "<div style='width:100%;background-color:#f27f02;float:left'>");
		message.append( "<div style='height:10px;width:800px;margin-left:auto;margin-right:auto;'></div></div>" );
		message.append( "<div style='width:100%;background-color:#EFF4F7;'>" );
		message.append( "<div style='height:950px;width:800px;margin-left:auto;margin-right:auto;'>" );
		
		// Greeting message.
		message.append( "<div style='height:130px;width:800px;font-size:25px;color:#199096;text-align:center;line-height:50px;'> Welcome " );
		message.append( firstName+"<BR/> Greetings from Supply Medium !" );
		message.append( "</div>" );
		
		// Supply Medium description
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px'>" );
		message.append( "Supply Medium is a quick and smart way to get connected with small business network.</div>" );
		
		// User name details
		message.append( "<div style='height:120px;width:600px;color:#434446;text-align:center;" );
		message.append( "border:1px solid #c8c8c8;background-color:#fff;margin-top:80px;margin-left:100px;'>" );
		message.append( "<div style='text-align:center;color:#199096;font-size:20px;margin-top:20px;'>" );
		message.append( " Your user name is</div>" );
		
		message.append( "<div style='color:#f27f02;font-size:25px;margin-top:10px;text-decoration:none;'>" );
		message.append( emailId );
		message.append( "</div></div>" );
		
		
		// Message
		message.append( "<div style='height:70px;width:600px;font-size:22px;color:#035458;text-align:center;padding-left:100px;margin-top:70px;'>" );
		message.append( "You have been invited by "+companyName+" to join Supply Medium." );
		message.append( "You can activate your membership by clicking the below button.</div>" );
				
		
		// Link 
		message.append( "<a style='text-decoration:none;' href='" );
		message.append( path+"/Views/Registration/jsp/useractivate.jsp?key=" + uuid+"'>" );
		message.append( "<input type='button' style='cursor: pointer;border-style:none;height:60px;width:324px;margin-left:225px;background-color:#f27f02;" );
		message.append( "margin-top:40px;font-size:25px;color:#fff;' value='Activate Your Account' />" );
		message.append( "</a>" );
		
		// Support Link
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;" );
		message.append( "text-align:left;margin-top:100px;margin-left:50px;'> " );
		message.append( "If you are having problems signing up please contact a member of our support staff at " );
		message.append( "webmaster@supplymedium.com</div>" );
		
		message.append( "<div style='height:1px;width:100%;background-color:#d2d3d5'> </div>" );
		message.append( "<div style='height:1px;width:100%;background-color:#fff'> </div>" );
		
		// Footer
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;margin-left:50px;" );
		message.append( "text-align:left;margin-top:50px;'> " );
		message.append( "&#169; 2013 Supply Medium Inc,<BR/>" );
		message.append( "All Rights Reserved,<BR/>" );
		message.append( "New York. USA<BR/>" );
		message.append( "Redefining Social Commerce!" );
		
		message.append( "</div></div></div></div>" );
		message.append( "</body></html>" );
				
		System.out.println( "message="+message.toString( ) );
		
		System.out.println("WEBKRIT====>>>>"+message.toString( ));return message.toString( );
	}
	
	
	/*
	 * Method : composeResetPasswordtMail( ) 
	 * 
	 * Input :  String uuid, String emailId,String firstName
	 * 
	 * Return : HTML mail String 
	 * 
	 * Purpose:It is used to compose reset password link HTML mail.
	 */
	
	public String composeResetPasswordtMail( String uuid, String emailId,String firstName )
	{
		String path = AppConfigReader.instance( ).get( "SERVLET_PATH" );
		
		StringBuffer message = new StringBuffer( "" );
		
		message.append( "<html><body style='font-family:Verdana;min-width:800px;'>" );
		message.append( "<div style='width:100%;background-color:#fff;float:left;'> " );
		message.append( "<div style='height:120px;width:800px;margin-left:auto;margin-right:auto;'> " );
		
		// Logo image
		message.append( "<a href='" );
		message.append( path+"/index.jsp" );
		message.append( "'><img src='" );
		message.append( path+"/Views/Utils/images/logo.png" );
		message.append( "' width='325px' height='100px' style='margin-top:10px;'/></a></div>" );
		
		// Border and background color 
		message.append( "<div style='width:100%;background-color:#f27f02;float:left'>");
		message.append( "<div style='height:10px;width:800px;margin-left:auto;margin-right:auto;'></div></div>" );
		message.append( "<div style='width:100%;background-color:#EFF4F7;'>" );
		message.append( "<div style='height:700px;width:800px;margin-left:auto;margin-right:auto;'>" );
		
		// Greeting message.
		message.append( "<div style='height:130px;width:800px;font-size:25px;color:#199096;text-align:center;line-height:50px;'> Welcome " );
		message.append( firstName+"<BR/> Greetings from Supply Medium !" );
		message.append( "</div>" );
		
	/*	// Supply Medium description
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px'>" );
		message.append( "Welcome to Supply Medium, the quick and smart way to get connected with small business network.</div>" );
		
		// User name details
		message.append( "<div style='height:120px;width:600px;color:#434446;text-align:center;" );
		message.append( "border:1px solid #c8c8c8;background-color:#fff;margin-top:80px;margin-left:100px;'>" );
		message.append( "<div style='text-align:center;color:#199096;font-size:20px;margin-top:20px;'>" );
		message.append( " Your user name is</div>" );
		
		message.append( "<div style='color:#f27f02;font-size:25px;margin-top:10px;text-decoration:none;'>" );
		message.append( emailId );
		message.append( "</div></div>" );*/
		
		
		// Message
		message.append( "<div style='height:70px;width:600px;font-size:22px;color:#035458;text-align:center;padding-left:100px;margin-top:70px;'>" );
		message.append( "We have received a request to reset your password. Please click on the button below to reset your password." );
		message.append( "</div>" );
				
		
		// Link 
		message.append( "<a style='text-decoration:none;' href='" );
		message.append( path+"/Views/Registration/jsp/passwordreset.jsp?key=" + uuid+"'>" );
		message.append( "<input type='button' style='cursor: pointer;border-style:none;height:60px;width:324px;margin-left:225px;background-color:#f27f02;" );
		message.append( "margin-top:40px;font-size:25px;color:#fff;' value='Reset Your Password' />" );
		message.append( "</a>" );
		
		// Support Link
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;" );
		message.append( "text-align:left;margin-top:100px;margin-left:50px;'> " );
		message.append( "If you are having problems signing up please contact a member of our support staff at " );
		message.append( "webmaster@supplymedium.com</div>" );
		
		message.append( "<div style='height:1px;width:100%;background-color:#d2d3d5'> </div>" );
		message.append( "<div style='height:1px;width:100%;background-color:#fff'> </div>" );
		
		// Footer
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;margin-left:50px;" );
		message.append( "text-align:left;margin-top:50px;'> " );
		message.append( "&#169; 2013 Supply Medium Inc,<BR/>" );
		message.append( "All Rights Reserved,<BR/>" );
		message.append( "New York. USA<BR/>" );
		message.append( "Redefining Social Commerce!" );
		
		message.append( "</div></div></div></div>" );
		message.append( "</body></html>" );
				
		System.out.println( "message="+message.toString( ) );
		
		System.out.println("WEBKRIT====>>>>"+message.toString( ));return message.toString( );
	}
	
	public String composeSurveytMail(  )
	{
		String path = AppConfigReader.instance( ).get( "SERVLET_PATH" );
		
		StringBuffer message = new StringBuffer( "" );
		
		message.append( " <html><body style='font-family:Verdana;min-width:800px;'> " );
		message.append( " <div style='width:100%;background-color:#fff;float:left;'>  " );
		message.append( "<div style='height:120px;width:800px;margin-left:auto;margin-right:auto;'> " ); 
		
		
		message.append( "<a href=' '><img src='logo.png'  width='325px' height='100px' style='margin-top:10px;'/></a></div> " );
		
		
		message.append( "<div style='width:100%;background-color:#f27f02;float:left'>" );
		message.append( " <div style='height:10px;width:800px;margin-left:auto;margin-right:auto;'></div></div> " );
		message.append( "<div style='width:100%;background-color:#EFF4F7;'> " );
		message.append( " <div style='height:900px;width:800px;margin-left:auto;margin-right:auto;'> " );
		
		
		message.append( "<div style='height:100px;width:800px;font-size:25px;color:#199096;text-align:center;line-height:50px;padding-top:20px;'>  Greetings from Supply Medium ! " );
		message.append( "</div> " );
		
		
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'> " );
		message.append( "SupplyMedium is a quick and smart way to get connected with small business network. We are excited about launching in November 2013.</div> " );
		
		
		message.append( "<div style='height:150px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'> " );
		message.append( "We are conducting a brief survey of 5 questions only to evaluate existing small businesses. If you submit our completed survey, " );
		message.append( "we will record your submission and provide you an upgraded membership when we launch by the year end!</div> " );
		 
		
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'> " );
		message.append( " Thanks for taking couple minutes for submitting the survey.</div> " );
		 
		 
		
		
		
		message.append( " <a style='text-decoration:none;' href='http://www.surveymonkey.com/s/8S9T52T'> " );
		message.append( "<input type='button' style='cursor: pointer;border-style:none;height:60px;width:324px;margin-left:225px;background-color:#f27f02; " );
		message.append( " margin-top:40px;font-size:25px;color:#fff;' value='Take Survey' /> </a> " );
		
		
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446; " );
		message.append( "text-align:left;margin-top:100px;margin-left:50px;'>  " );
		message.append( " If you are having problems signing up please contact a member of our support staff at  " );
		message.append( "webmaster@supplymedium.com</div> " );
		
		message.append( "<div style='height:1px;width:100%;background-color:#d2d3d5'> </div> " );
		message.append( "<div style='height:1px;width:100%;background-color:#fff'> </div> " );
		
		
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;margin-left:50px; " );
		message.append( "text-align:left;margin-top:50px;'>  " );
		message.append( "&#169; 2013 Supply Medium Inc,<BR/> " );
		message.append( "All Rights Reserved,<BR/> " );
		message.append( "New York. USA<BR/> " );
		message.append( "Redefining Social Commerce! " );
		
		message.append( "</div></div></div></div> " );
		message.append( "</body></html> " );
		
		System.out.println("WEBKRIT====>>>>"+message.toString( ));return message.toString( );
	}
	
	
	/*This method composes the new vendor request mail. 
	 * This will be called whenever a new vendor request is sent. */
	
	public String composeVRMail( String companyName, String content, String inquiry )
	{
		String path = AppConfigReader.instance( ).get( "SERVLET_PATH" );
		
		StringBuffer message = new StringBuffer( "" );
		
		message.append( " <html><body style='font-family:Verdana;min-width:800px;'> " );
		message.append( " <div style='width:100%;background-color:#fff;float:left;'>  " );
		message.append( "<div style='height:120px;width:800px;margin-left:auto;margin-right:auto;'> " ); 
		
		message.append( "<a href=' '><img src= '"+path+"/Views/Utils/images/logo.png'  width='325px' height='100px' style='margin-top:10px;'/></a></div> " );
		
		message.append( "<div style='width:100%;background-color:#f27f02;float:left'>" );
		message.append( " <div style='height:10px;width:800px;margin-left:auto;margin-right:auto;'></div></div> " );
		message.append( "<div style='width:100%;background-color:#EFF4F7;'> " );
		message.append( " <div style='height:700px;width:800px;margin-left:auto;margin-right:auto;'> " );
		
		message.append( "<div style='height:100px;width:800px;font-size:25px;color:#199096;text-align:center;line-height:50px;padding-top:20px;'>  Vendor Registration " );
		message.append( "</div> " );
		
		//content = content.replace( "$", companyName );
		
		if( inquiry != "" || !(inquiry.equals( "" )) )
		{
			inquiry = inquiry.trim( );
			inquiry = "<br/><br/>'"+inquiry+"'";
		}
		
		String [ ] valArr = { companyName, inquiry };
		
		content = get( content, valArr );
		
		message.append( "<div style='height:150px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'> " );
		message.append( ""+ content +"</div> " );
		
		message.append( " <a style='text-decoration:none;' href='"+ path +"'> " );
		message.append( "<input type='button' style='cursor: pointer;border-style:none;height:60px;width:324px;margin-left:225px;background-color:#f27f02; " );
		message.append( " margin-top:40px;font-size:25px;color:#fff;' value='Click Here to Login' /> </a> " );
		
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446; " );
		message.append( "text-align:left;margin-top:100px;margin-left:50px;'>  " );
		message.append( " If you are having problems signing up please contact a member of our support staff at  " );
		message.append( "webmaster@supplymedium.com</div> " );
		
		message.append( "<div style='height:1px;width:100%;background-color:#d2d3d5'> </div> " );
		message.append( "<div style='height:1px;width:100%;background-color:#fff'> </div> " );
		
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;margin-left:50px; " );
		message.append( "text-align:left;margin-top:50px;'>  " );
		message.append( "&#169; 2013 Supply Medium Inc,<BR/> " );
		message.append( "All Rights Reserved,<BR/> " );
		message.append( "New York. USA<BR/> " );
		message.append( "Redefining Social Commerce! " );
		
		message.append( "</div></div></div></div> " );
		message.append( "</body></html> " );
		
		System.out.println("WEBKRIT====>>>>"+message.toString( ));return message.toString( );
	}
	
	/*Helper method for composeVRMail( .. ) */
	private String get( String msg, String [] valueArr )
	{
		String value = ""; 
		
		String [] msgArr = msg.split( "\\$" );
		
		int i = 0;
		
		for( String arrValue : msgArr )
        {
	        value = value + arrValue;
	        
	        if( i < valueArr.length )
	        {
	        	value = value + valueArr[i];
	        }
	        
	        i++;
        }
		
		return value;
	}
	
	
	
	
	/*
	 * Method : composeInvitationMail( ) 
	 * 
	 * Input :  none
	 * 
	 * Return : HTML mail String 
	 * 
	 * Purpose: It is used to create the Invitation HTML mail content 
	 * using given data.
	 */
	public String composeInvitationMail(String [ ] customerKeysArray, String companyName, String senderName)
	{
			String path = AppConfigReader.instance( ).get( "SERVLET_PATH" );
			
			StringBuffer message = new StringBuffer( "" );
			
			message.append( "<html><body style='font-family:Verdana;min-width:800px;'>" );
			message.append( "<div style='width:100%;background-color:#fff;float:left;'> " );
			message.append( "<div style='height:120px;width:800px;margin-left:auto;margin-right:auto;'> " );
			
			// Logo image
			message.append( "<a href='" );
			message.append( path+"/index.jsp" );
			message.append( "'><img src='" );
			message.append( path+"/Views/Utils/images/logo.png" );
			message.append( "' width='325px' height='100px' style='margin-top:10px;'/></a></div>" );
			
			// Border and background color 
			message.append( "<div style='width:100%;background-color:#f27f02;float:left'>");
			message.append( "<div style='height:10px;width:800px;margin-left:auto;margin-right:auto;'></div></div>" );
			message.append( "<div style='width:100%;background-color:#EFF4F7;'>" );
			message.append( "<div style='height:800px;width:800px;margin-left:auto;margin-right:auto;'>" );
			
			// Greeting message.
			message.append( "<div style='height:130px;width:800px;font-size:25px;color:#199096;text-align:center;line-height:50px;'> Welcome " );
			message.append( "<BR/> Greetings from Supply Medium !" );
			message.append( "</div>" );
			
			// Supply Medium description
			message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'>" );
			message.append( senderName+" of "+companyName+" has invited to join to SupplyMedium.</div>" );
						
			
			// Supply Medium description
			message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'>" );
			message.append( "SupplyMedium is a quick and smart way to get connected with small business network.</div>" );
			
			
			// Link 
			message.append( "<a style='text-decoration:none;' href='" );
			message.append( path+"/Views/Registration/jsp/companyregistration.jsp'>" );
			message.append( "<input type='button' style='cursor: pointer;border-style:none;height:60px;width:324px;margin-left:225px;background-color:#f27f02;" );
			message.append( "margin-top:40px;font-size:25px;color:#fff;' value='Register' />" );
			message.append( "</a>" );
			
			// Support Link
			message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;" );
			message.append( "text-align:left;margin-top:100px;margin-left:50px;'> " );
			message.append( "If you are having problems signing up please contact a member of our support staff at " );
			message.append( "webmaster@supplymedium.com</div>" );
			
			message.append( "<div style='height:1px;width:100%;background-color:#d2d3d5'> </div>" );
			message.append( "<div style='height:1px;width:100%;background-color:#fff'> </div>" );
			
			// Footer
			message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;margin-left:50px;" );
			message.append( "text-align:left;margin-top:50px;'> " );
			message.append( "&#169; 2013 Supply Medium Inc,<BR/>" );
			message.append( "All Rights Reserved,<BR/>" );
			message.append( "New York. USA<BR/>" );
			message.append( "Redefining Social Commerce!" );
			
			message.append( "</div></div></div></div>" );
			message.append( "</body></html>" );
					
			System.out.println( "message="+message.toString( ) );
			
			System.out.println("WEBKRIT====>>>>"+message.toString( ));return message.toString( );
	}
	
	
	public String composeTransactionMail(String transactionMessage)
	{
		String path = AppConfigReader.instance( ).get( "SERVLET_PATH" );
		
		StringBuffer message = new StringBuffer( "" );
		
		message.append( "<html><body style='font-family:Verdana;min-width:800px;'>" );
		message.append( "<div style='width:100%;background-color:#fff;float:left;'> " );
		message.append( "<div style='height:120px;width:800px;margin-left:auto;margin-right:auto;'> " );
		
		// Logo image
		message.append( "<a href='" );
		message.append( path+"/index.jsp" );
		message.append( "'><img src='" );
		message.append( path+"/Views/Utils/images/logo.png" );
		message.append( "' width='325px' height='100px' style='margin-top:10px;'/></a></div>" );
		
		// Border and background color 
		message.append( "<div style='width:100%;background-color:#f27f02;float:left'>");
		message.append( "<div style='height:10px;width:800px;margin-left:auto;margin-right:auto;'></div></div>" );
		message.append( "<div style='width:100%;background-color:#EFF4F7;'>" );
		message.append( "<div style='height:800px;width:800px;margin-left:auto;margin-right:auto;'>" );
		
		// Greeting message.
		message.append( "<div style='height:130px;width:800px;font-size:25px;color:#199096;text-align:center;line-height:50px;'> Welcome " );
		message.append( "<BR/> Greetings from Supply Medium !" );
		message.append( "</div>" );
		
		// Supply Medium description
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'>" );
		message.append( transactionMessage+"</div>" );
					
		
		// Supply Medium description
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'>" );
		message.append( "SupplyMedium is a quick and smart way to get connected with small business network.</div>" );
		
		
		// Link 
		message.append( "<a style='text-decoration:none;' href='" );
		message.append( path+"/Views/Registration/jsp/companyregistration.jsp'>" );
		message.append( "<input type='button' style='cursor: pointer;border-style:none;height:60px;width:324px;margin-left:225px;background-color:#f27f02;" );
		message.append( "margin-top:40px;font-size:25px;color:#fff;' value='Register' />" );
		message.append( "</a>" );
		
		// Support Link
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;" );
		message.append( "text-align:left;margin-top:100px;margin-left:50px;'> " );
		message.append( "If you are having problems signing up please contact a member of our support staff at " );
		message.append( "webmaster@supplymedium.com</div>" );
		
		message.append( "<div style='height:1px;width:100%;background-color:#d2d3d5'> </div>" );
		message.append( "<div style='height:1px;width:100%;background-color:#fff'> </div>" );
		
		// Footer
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;margin-left:50px;" );
		message.append( "text-align:left;margin-top:50px;'> " );
		message.append( "&#169; 2013 Supply Medium Inc,<BR/>" );
		message.append( "All Rights Reserved,<BR/>" );
		message.append( "New York. USA<BR/>" );
		message.append( "Redefining Social Commerce!" );
		
		message.append( "</div></div></div></div>" );
		message.append( "</body></html>" );
				
		System.out.println( "message="+message.toString( ) );
		
		System.out.println("WEBKRIT====>>>>"+message.toString( ));return message.toString( );
		
	}
        
        public String composeTransactionMail2(String transactionMessage,List<RFQItemData> RFQItemList)
	{
        StringBuffer items = new StringBuffer( "" );
        items.append( "<div style='margin-top:30px;'><table style='margin:auto;' frame='box' rules='all'>");
    items.append( "<tr><th>SR NO.</th>");
    items.append( "<th>ITEM DESC</th>");
    items.append( "<th>PART NO.</th>");
    items.append( "<th>BAR CODE</th>");
    items.append( "<th>QUANTITY</th>");
    items.append( "<th>SHIP DATE</th></tr>");
    int count=1;
            for( RFQItemData rfqItemData : RFQItemList )
	        {                    
            items.append( "<tr>");            
            items.append( "<td>"+count+"</td>");
            items.append( "<td>"+rfqItemData.itemDesc_+"</td>");
            items.append( "<td>"+rfqItemData.partNo_+"</td>");
            items.append( "<td>"+rfqItemData.brcdNo_+"</td>");
            items.append( "<td>"+rfqItemData.quantity_+" "+rfqItemData.quantityUnit_+"</td>");
            items.append( "<td>"+rfqItemData.shipDate_+"</td>");
            items.append( "</tr>");  
            count++;
        
    }
    items.append( "</table></div>");
    
    String path = AppConfigReader.instance( ).get( "SERVLET_PATH" );
		
		StringBuffer message = new StringBuffer( "" );
		
		message.append( "<html><body style='font-family:Verdana;min-width:800px;'>" );
		message.append( "<div style='width:100%;background-color:#fff;float:left;'> " );
		message.append( "<div style='height:120px;width:800px;margin-left:auto;margin-right:auto;'> " );
		
		// Logo image
		message.append( "<a href='" );
		message.append( path+"/index.jsp" );
		message.append( "'><img src='" );
		message.append( path+"/Views/Utils/images/logo.png" );
		message.append( "' width='325px' height='100px' style='margin-top:10px;'/></a></div>" );
		
		// Border and background color 
		message.append( "<div style='width:100%;background-color:#f27f02;float:left'>");
		message.append( "<div style='height:10px;width:800px;margin-left:auto;margin-right:auto;'></div></div>" );
		message.append( "<div style='width:100%;background-color:#EFF4F7;'>" );
		message.append( "<div style='height:800px;width:800px;margin-left:auto;margin-right:auto;'>" );
		
		// Greeting message.
		message.append( "<div style='height:130px;width:800px;font-size:25px;color:#199096;text-align:center;line-height:50px;'> Welcome " );
		message.append( "<BR/> Greetings from Supply Medium !" );
		message.append( "</div>" );
		
		// Supply Medium description
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'>" );
		message.append( transactionMessage+"</div>" );
					
		
		// Supply Medium description
                message.append(items);
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'>" );
		message.append( "SupplyMedium is a quick and smart way to get connected with small business network.</div>" );
		
		
		// Link 
		message.append( "<a style='text-decoration:none;' href='" );
		message.append( path+"/Views/Registration/jsp/companyregistration.jsp'>" );
		message.append( "<input type='button' style='cursor: pointer;border-style:none;height:60px;width:324px;margin-left:225px;background-color:#f27f02;" );
		message.append( "margin-top:40px;font-size:25px;color:#fff;' value='Register' />" );
		message.append( "</a>" );
		
		// Support Link
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;" );
		message.append( "text-align:left;margin-top:100px;margin-left:50px;'> " );
		message.append( "If you are having problems signing up please contact a member of our support staff at " );
		message.append( "webmaster@supplymedium.com</div>" );
		
		message.append( "<div style='height:1px;width:100%;background-color:#d2d3d5'> </div>" );
		message.append( "<div style='height:1px;width:100%;background-color:#fff'> </div>" );
		
		// Footer
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;margin-left:50px;" );
		message.append( "text-align:left;margin-top:50px;'> " );
		message.append( "&#169; 2013 Supply Medium Inc,<BR/>" );
		message.append( "All Rights Reserved,<BR/>" );
		message.append( "New York. USA<BR/>" );
		message.append( "Redefining Social Commerce!" );
		
		message.append( "</div></div></div></div>" );
		message.append( "</body></html>" );
				
		System.out.println( "message="+message.toString( ) );
		
		System.out.println("WEBKRIT====>>>>"+message.toString( ));return message.toString( );
        }
        
        public String composeTransactionMail3(String transactionMessage,QuoteData quoteData)
	{
        List<QuoteItemData> quoteItemList=quoteData.quoteItems_;
         StringBuffer items = new StringBuffer( "" );
        items.append( "<div style='margin-top:30px;'><table style='margin:auto;' frame='box' rules='all'>");
    items.append( "<tr><th>SR NO.</th>");
    items.append( "<th>ITEM DESC</th>");
    items.append( "<th>PART NO.</th>");
    items.append( "<th>BAR CODE</th>");
    items.append( "<th>QUANTITY</th>");
    items.append( "<th>PRICE</th>");
    items.append( "<th>CURRENCY</th>");
    items.append( "<th>MULTIPLIER</th>");
    items.append( "<th>TOTAL</th></tr>");
    int count=1;
            for( QuoteItemData quoteItemData : quoteItemList )
	        {                    
            items.append( "<tr>");            
            items.append( "<td>"+count+"</td>");
            items.append( "<td>"+quoteItemData.itemDesc_+"</td>");
            items.append( "<td>"+quoteItemData.partNo_+"</td>");
            items.append( "<td>"+quoteItemData.brcd_+"</td>");
            items.append( "<td>"+quoteItemData.quantity_+" "+quoteItemData.quantityUnit_+"</td>");
            items.append( "<td>"+quoteItemData.price_+"</td>");
            items.append( "<td>"+quoteItemData.currency_+"</td>");
            items.append( "<td>"+quoteItemData.multiplier_/quoteItemData.price_/quoteItemData.quantity_+"</td>");
            items.append( "<td>"+quoteItemData.multiplier_+"</td>");
            items.append( "</tr>");  
            count++;
        
    }
    
            items.append( "<tr>");            
    items.append( "<td colspan='8' style='text-align: right;'>LIST TOTAL</td>");
    items.append( "<td>"+quoteData.totalListPrice_+"</td>");
    items.append( "</tr>");
    items.append( "<tr>");            
    items.append( "<td colspan='8' style='text-align: right;'>TAX("+quoteData.taxPercentage_+")</td>");
    items.append( "<td>"+(quoteData.totalListPrice_*quoteData.taxPercentage_/100)+"</td>");
    items.append( "</tr>");
    items.append( "<tr>");            
    items.append( "<td colspan='8' style='text-align: right;'>GRAND TOTAL</td>");
    items.append( "<td>"+quoteData.totalPrice_+"</td>");
    items.append( "</tr>");        
    items.append( "</table></div>");
            
    items.append( "</table></div>");
    
    String path = AppConfigReader.instance( ).get( "SERVLET_PATH" );
		
		StringBuffer message = new StringBuffer( "" );
		
		message.append( "<html><body style='font-family:Verdana;min-width:800px;'>" );
		message.append( "<div style='width:100%;background-color:#fff;float:left;'> " );
		message.append( "<div style='height:120px;width:800px;margin-left:auto;margin-right:auto;'> " );
		
		// Logo image
		message.append( "<a href='" );
		message.append( path+"/index.jsp" );
		message.append( "'><img src='" );
		message.append( path+"/Views/Utils/images/logo.png" );
		message.append( "' width='325px' height='100px' style='margin-top:10px;'/></a></div>" );
		
		// Border and background color 
		message.append( "<div style='width:100%;background-color:#f27f02;float:left'>");
		message.append( "<div style='height:10px;width:800px;margin-left:auto;margin-right:auto;'></div></div>" );
		message.append( "<div style='width:100%;background-color:#EFF4F7;'>" );
		message.append( "<div style='height:800px;width:800px;margin-left:auto;margin-right:auto;'>" );
		
		// Greeting message.
		message.append( "<div style='height:130px;width:800px;font-size:25px;color:#199096;text-align:center;line-height:50px;'> Welcome " );
		message.append( "<BR/> Greetings from Supply Medium !" );
		message.append( "</div>" );
		
		// Supply Medium description
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'>" );
		message.append( transactionMessage+"</div>" );
					
		
		// Supply Medium description
                message.append(items);
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'>" );
		message.append( "SupplyMedium is a quick and smart way to get connected with small business network.</div>" );
		
		
		// Link 
		message.append( "<a style='text-decoration:none;' href='" );
		message.append( path+"/Views/Registration/jsp/companyregistration.jsp'>" );
		message.append( "<input type='button' style='cursor: pointer;border-style:none;height:60px;width:324px;margin-left:225px;background-color:#f27f02;" );
		message.append( "margin-top:40px;font-size:25px;color:#fff;' value='Register' />" );
		message.append( "</a>" );
		
		// Support Link
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;" );
		message.append( "text-align:left;margin-top:100px;margin-left:50px;'> " );
		message.append( "If you are having problems signing up please contact a member of our support staff at " );
		message.append( "webmaster@supplymedium.com</div>" );
		
		message.append( "<div style='height:1px;width:100%;background-color:#d2d3d5'> </div>" );
		message.append( "<div style='height:1px;width:100%;background-color:#fff'> </div>" );
		
		// Footer
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;margin-left:50px;" );
		message.append( "text-align:left;margin-top:50px;'> " );
		message.append( "&#169; 2013 Supply Medium Inc,<BR/>" );
		message.append( "All Rights Reserved,<BR/>" );
		message.append( "New York. USA<BR/>" );
		message.append( "Redefining Social Commerce!" );
		
		message.append( "</div></div></div></div>" );
		message.append( "</body></html>" );
				
		System.out.println( "message="+message.toString( ) );
		
		System.out.println("WEBKRIT====>>>>"+message.toString( ));return message.toString( );
        }
        public String composeTransactionMail4(String transactionMessage,POData poData)
	{
        List<POItemData> poItemList=poData.poItems_;    
        StringBuffer items = new StringBuffer( "" );
        items.append( "<div style='margin-top:30px;'><table style='margin:auto;' frame='box' rules='all'>");
    items.append( "<tr><th>SR NO.</th>");
    items.append( "<th>ITEM DESC</th>");
    items.append( "<th>PART NO.</th>");
    items.append( "<th>BAR CODE</th>");
    items.append( "<th>QUANTITY</th>");
    items.append( "<th>PRICE</th>");
    items.append( "<th>CURRENCY</th>");
    items.append( "<th>MULTIPLIER</th>");
    items.append( "<th>TOTAL</th>");
    //items.append( "<th>SHIP DATE</th></tr>");
    int count=1;
            for( POItemData poItemData : poItemList )
	        {                    
            items.append( "<tr>");            
            items.append( "<td>"+count+"</td>");
            items.append( "<td>"+poItemData.itemDesc_+"</td>");
            items.append( "<td>"+poItemData.partNo_+"</td>");
            items.append( "<td>"+poItemData.brcd_+"</td>");
            items.append( "<td>"+poItemData.quantity_+" "+poItemData.quantityUnit_+"</td>");
            items.append( "<td>"+poItemData.price_+"</td>");
            items.append( "<td>"+poItemData.currency_+"</td>");
            items.append( "<td>"+poItemData.multiplier_/poItemData.price_/poItemData.quantity_+"</td>");
            items.append( "<td>"+poItemData.multiplier_+"</td>");
            items.append( "</tr>");  
            count++;
        
    }
    items.append( "<tr>");            
    items.append( "<td colspan='8' style='text-align: right;'>LIST TOTAL</td>");
    items.append( "<td>"+poData.totalListPrice_+"</td>");
    items.append( "</tr>");
    items.append( "<tr>");            
    items.append( "<td colspan='8' style='text-align: right;'>TAX("+poData.taxPercentage_+")</td>");
    items.append( "<td>"+(poData.totalListPrice_*poData.taxPercentage_/100)+"</td>");
    items.append( "</tr>");
    items.append( "<tr>");            
    items.append( "<td colspan='8' style='text-align: right;'>GRAND TOTAL</td>");
    items.append( "<td>"+poData.totalPrice_+"</td>");
    items.append( "</tr>");        
    items.append( "</table></div>");
    
    String path = AppConfigReader.instance( ).get( "SERVLET_PATH" );
		
		StringBuffer message = new StringBuffer( "" );
		
		message.append( "<html><body style='font-family:Verdana;min-width:800px;'>" );
		message.append( "<div style='width:100%;background-color:#fff;float:left;'> " );
		message.append( "<div style='height:120px;width:800px;margin-left:auto;margin-right:auto;'> " );
		
		// Logo image
		message.append( "<a href='" );
		message.append( path+"/index.jsp" );
		message.append( "'><img src='" );
		message.append( path+"/Views/Utils/images/logo.png" );
		message.append( "' width='325px' height='100px' style='margin-top:10px;'/></a></div>" );
		
		// Border and background color 
		message.append( "<div style='width:100%;background-color:#f27f02;float:left'>");
		message.append( "<div style='height:10px;width:800px;margin-left:auto;margin-right:auto;'></div></div>" );
		message.append( "<div style='width:100%;background-color:#EFF4F7;'>" );
		message.append( "<div style='height:800px;width:800px;margin-left:auto;margin-right:auto;'>" );
		
		// Greeting message.
		message.append( "<div style='height:130px;width:800px;font-size:25px;color:#199096;text-align:center;line-height:50px;'> Welcome " );
		message.append( "<BR/> Greetings from Supply Medium !" );
		message.append( "</div>" );
		
		// Supply Medium description
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'>" );
		message.append( transactionMessage+"</div>" );
					
		
		// Supply Medium description
                message.append(items);
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'>" );
		message.append( "SupplyMedium is a quick and smart way to get connected with small business network.</div>" );
		
		
		// Link 
		message.append( "<a style='text-decoration:none;' href='" );
		message.append( path+"/Views/Registration/jsp/companyregistration.jsp'>" );
		message.append( "<input type='button' style='cursor: pointer;border-style:none;height:60px;width:324px;margin-left:225px;background-color:#f27f02;" );
		message.append( "margin-top:40px;font-size:25px;color:#fff;' value='Register' />" );
		message.append( "</a>" );
		
		// Support Link
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;" );
		message.append( "text-align:left;margin-top:100px;margin-left:50px;'> " );
		message.append( "If you are having problems signing up please contact a member of our support staff at " );
		message.append( "webmaster@supplymedium.com</div>" );
		
		message.append( "<div style='height:1px;width:100%;background-color:#d2d3d5'> </div>" );
		message.append( "<div style='height:1px;width:100%;background-color:#fff'> </div>" );
		
		// Footer
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;margin-left:50px;" );
		message.append( "text-align:left;margin-top:50px;'> " );
		message.append( "&#169; 2013 Supply Medium Inc,<BR/>" );
		message.append( "All Rights Reserved,<BR/>" );
		message.append( "New York. USA<BR/>" );
		message.append( "Redefining Social Commerce!" );
		
		message.append( "</div></div></div></div>" );
		message.append( "</body></html>" );
				
		System.out.println( "message="+message.toString( ) );
		
		System.out.println("WEBKRIT====>>>>"+message.toString( ));return message.toString( );
        }
        public String composeTransactionMail5(String transactionMessage,InvoiceData invoiceData)
	{
        List<InvoiceItemData> invoiceItemList=invoiceData.invoiceItems_ ;
            StringBuffer items = new StringBuffer( "" );
        items.append( "<div style='margin-top:30px;'><table style='margin:auto;' frame='box' rules='all'>");
    items.append( "<tr><th>SR NO.</th>");
    items.append( "<th>ITEM DESC</th>");
    items.append( "<th>PART NO.</th>");
    items.append( "<th>BAR CODE</th>");
    items.append( "<th>OREDER QUANTITY</th>");
    items.append( "<th>SHIPPED QUANTITY</th>");
    items.append( "<th>PRICE</th>");
    items.append( "<th>CURRENCY</th>");
    items.append( "<th>MULTIPLIER</th>");
    items.append( "<th>TOTAL</th></tr>");
    int count=1;
            for( InvoiceItemData invoiceItemData : invoiceItemList )
	        {                    
            items.append( "<tr>");            
            items.append( "<td>"+count+"</td>");
            items.append( "<td>"+invoiceItemData.itemDesc_+"</td>");
            items.append( "<td>"+invoiceItemData.partNo_+"</td>");
            items.append( "<td>"+invoiceItemData.brcd_+"</td>");
            items.append( "<td>"+invoiceItemData.quantityOrdered_+" "+invoiceItemData.quantityOrderedUnit_+"</td>");
            items.append( "<td>"+invoiceItemData.quantityShipped_+" "+invoiceItemData.quantityShippedUnit_+"</td>");
            items.append( "<td>"+invoiceItemData.price_+"</td>");
            items.append( "<td>"+invoiceItemData.currency_+"</td>");
            items.append( "<td>"+invoiceItemData.multiplier_+"</td>");
            items.append( "<td>"+invoiceItemData.price_*invoiceItemData.multiplier_*invoiceItemData.quantityShipped_+"</td>");
            items.append( "</tr>");  
            count++;
        
    }
    items.append( "<tr>");            
    items.append( "<td colspan='9' style='text-align: right;'>LIST TOTAL</td>");
    items.append( "<td>"+invoiceData.totalListPrice_+"</td>");
    items.append( "</tr>");
    items.append( "<tr>");            
    items.append( "<td colspan='9' style='text-align: right;'>TAX("+invoiceData.taxPercentage_+")</td>");
    items.append( "<td>"+(invoiceData.totalListPrice_*invoiceData.taxPercentage_/100)+"</td>");
    items.append( "</tr>");
    items.append( "<tr>");            
    items.append( "<td colspan='9' style='text-align: right;'>GRAND TOTAL</td>");
    items.append( "<td>"+invoiceData.totalPrice_+"</td>");
    items.append( "</tr>");
            
    items.append( "</table></div>");
    
    String path = AppConfigReader.instance( ).get( "SERVLET_PATH" );
		
		StringBuffer message = new StringBuffer( "" );
		
		message.append( "<html><body style='font-family:Verdana;min-width:800px;'>" );
		message.append( "<div style='width:100%;background-color:#fff;float:left;'> " );
		message.append( "<div style='height:120px;width:800px;margin-left:auto;margin-right:auto;'> " );
		
		// Logo image
		message.append( "<a href='" );
		message.append( path+"/index.jsp" );
		message.append( "'><img src='" );
		message.append( path+"/Views/Utils/images/logo.png" );
		message.append( "' width='325px' height='100px' style='margin-top:10px;'/></a></div>" );
		
		// Border and background color 
		message.append( "<div style='width:100%;background-color:#f27f02;float:left'>");
		message.append( "<div style='height:10px;width:800px;margin-left:auto;margin-right:auto;'></div></div>" );
		message.append( "<div style='width:100%;background-color:#EFF4F7;'>" );
		message.append( "<div style='height:800px;width:800px;margin-left:auto;margin-right:auto;'>" );
		
		// Greeting message.
		message.append( "<div style='height:130px;width:800px;font-size:25px;color:#199096;text-align:center;line-height:50px;'> Welcome " );
		message.append( "<BR/> Greetings from Supply Medium !" );
		message.append( "</div>" );
		
		// Supply Medium description
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'>" );
		message.append( transactionMessage+"</div>" );
					
		
		// Supply Medium description
                message.append(items);
		message.append( "<div style='height:70px;width:600px;font-size:20px;color:#434446;text-align:center;padding-left:100px;margin-top:30px;'>" );
		message.append( "SupplyMedium is a quick and smart way to get connected with small business network.</div>" );
		
		
		// Link 
		message.append( "<a style='text-decoration:none;' href='" );
		message.append( path+"/Views/Registration/jsp/companyregistration.jsp'>" );
		message.append( "<input type='button' style='cursor: pointer;border-style:none;height:60px;width:324px;margin-left:225px;background-color:#f27f02;" );
		message.append( "margin-top:40px;font-size:25px;color:#fff;' value='Register' />" );
		message.append( "</a>" );
		
		// Support Link
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;" );
		message.append( "text-align:left;margin-top:100px;margin-left:50px;'> " );
		message.append( "If you are having problems signing up please contact a member of our support staff at " );
		message.append( "webmaster@supplymedium.com</div>" );
		
		message.append( "<div style='height:1px;width:100%;background-color:#d2d3d5'> </div>" );
		message.append( "<div style='height:1px;width:100%;background-color:#fff'> </div>" );
		
		// Footer
		message.append( "<div style='height:50px;width:700px;font-size:12px;color:#434446;margin-left:50px;" );
		message.append( "text-align:left;margin-top:50px;'> " );
		message.append( "&#169; 2013 Supply Medium Inc,<BR/>" );
		message.append( "All Rights Reserved,<BR/>" );
		message.append( "New York. USA<BR/>" );
		message.append( "Redefining Social Commerce!" );
		
		message.append( "</div></div></div></div>" );
		message.append( "</body></html>" );
				
		System.out.println( "message="+message.toString( ) );
		
		System.out.println("WEBKRIT====>>>>"+message.toString( ));return message.toString( );
        }
}

