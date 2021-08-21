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

package ctrl.regn;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;
import utils.UserStatus;
import utils.UserType;
import core.regn.CompanyRegnKey;
import core.regn.MailingAddressData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;

/**
 * File:  UserSignupDataConverter.java 
 *
 * Created on Mar 9, 2013 3:15:29 PM
 */

/*
 * This class is used to convert HttpServletRequest to RegnData
 */
public class UserSignupDataConverter
{
	/*
	 * Method : convert( ) 
	 * 
	 * Input  : HttpServletRequest object, UserSignupData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to 
	 * UserSignupData object.
	 */
	
	public int convert( HttpServletRequest request,UserProfileData userProfileData )
    {
		try
		{
			System.out.println( "converter Start");
			Map<String,String[]> reqMap=request.getParameterMap( );
			
			CompanyRegnKey companyRegnKey = new CompanyRegnKey( );
			
			if(reqMap.containsKey( "companyname" ))
			{
				companyRegnKey.companyPhoneNo_ = request.getParameter( "companyname" );
				userProfileData.companyRegnKey_ = companyRegnKey;
			}
			

			if(reqMap.containsKey( "email" ))
			{
				
				UserProfileKey userProfileKey = new UserProfileKey( );
				userProfileKey.email_ = request.getParameter( "email" );
				userProfileData.userProfileKey_ = userProfileKey;
			}
			

			MailingAddressData mailingAddressObj = new MailingAddressData( );
			
			if(reqMap.containsKey( "address" ))
			{
				
				mailingAddressObj.address_ = request.getParameter( "address" );
			}
			
			if(reqMap.containsKey( "city" ))
			{
				
				mailingAddressObj.city_ = request.getParameter( "city" );
			}
			
			
			if(reqMap.containsKey( "zipcode" ))
			{
				
				mailingAddressObj.zipcode_ = request.getParameter( "zipcode" );
			}
			
			if(reqMap.containsKey( "countryregion" ))
			{
				
				mailingAddressObj.countryRegion_ = request.getParameter( "countryregion" );
				
				
				if(mailingAddressObj.countryRegion_.equals( "United States" ))
				{
					if(reqMap.containsKey( "state" ))
					{
						
						mailingAddressObj.state_ = request.getParameter( "state" );
					}
						
						
				}
				else
				{
					if(reqMap.containsKey( "state_text" ))
					{
						mailingAddressObj.state_ = request.getParameter( "state_text" );
					}
						
					
				}
			}

			
			
			
			if(reqMap.containsKey( "email" ))
			{
			
				mailingAddressObj.emailid_ = request.getParameter( "email" );
			}
			
			

			mailingAddressObj.companyRegnKey_ = companyRegnKey;
			userProfileData.mailingAddr_ = mailingAddressObj;
			
			
			if(reqMap.containsKey( "firstname" ))
			{
				
				userProfileData.firstName_ = request.getParameter( "firstname" );
			}
			
			if(reqMap.containsKey( "lastname" ))
			{
				
				userProfileData.lastName_ = request.getParameter( "lastname" );
			}
			
			if(reqMap.containsKey( "title" ))
			{
				
				userProfileData.title_ = request.getParameter( "title" );
			}
			
			if(reqMap.containsKey( "department" ))
			{
				
				userProfileData.department_ = request.getParameter( "department" );
			}
			
			if(reqMap.containsKey( "managersupervisor" ))
			{
				
				userProfileData.managerSupervisor_ = request.getParameter( "managersupervisor" );
			}
			if(reqMap.containsKey( "phone" ))
			{
				
				userProfileData.phoneNo_ = request.getParameter( "phone" );
			}
			
			
			if( userProfileData.phoneNo_!=null )
			{
				
			    userProfileData.phoneNo_ = userProfileData.phoneNo_.replaceAll( "-", "" );
				
			    userProfileData.phoneNo_ = userProfileData.phoneNo_.replaceAll("\\s","");
			}

			if(reqMap.containsKey( "cell" ))
			{
				
				userProfileData.cellNo_ = request.getParameter( "cell" );
			}
			if(reqMap.containsKey( "fax" ))
			{
				
				userProfileData.faxNo_ = request.getParameter( "fax" );
			}
			if(reqMap.containsKey( "email" ))
			{
				
				userProfileData.emailId_ = request.getParameter( "email" );
			}
			
			
			
		
			if(reqMap.containsKey( "password" ))
			{
				
				userProfileData.password_ = request.getParameter( "password" );
			}
			
			if(reqMap.containsKey( "usertype" ))
			{
				
				userProfileData.userType_ = request.getParameter( "usertype" );
			}
			
			
			
			if( request.getParameter( "RequestType" ).equals( "UserStatusUpdate" ) )
			{
				userProfileData.userStatus_ = request.getParameter( "status" );
			}
			
			if( request.getParameter( "RequestType" ).equals( "AddUser" ) || 
					request.getParameter( "RequestType" ).equals( "NewSignup" )) 
			{
				userProfileData.userStatus_ = UserStatus.status.PENDING.getValue( );
			}
			
			System.out.println( "ReqType = " + request.getParameter( "RequestType" ));
			
			if( request.getParameter( "RequestType" ).equals( "UpdatePolicies" ))
			{
				userProfileData.accountExpireDays_ = Integer.parseInt( request.getParameter( "ExpireEnd" ) );

				userProfileData.changePasswordFlag_ = Integer.parseInt( request.getParameter ( "ChangePassword" ) );
	
				userProfileData.disableAccountFlag_ = Integer.parseInt( request.getParameter( "AccDisable" ) );
				
				userProfileData.userStatus_ = request.getParameter( "status" );
				
			}
			
			if( request.getParameter( "RequestType" ).equals( "AddUser" ))
			{
				userProfileData.userType_ = UserType.type.TRANSACTIONUSER.getValue( );
			}
			
			if( request.getParameter( "RequestType" ).equals( "UserActivate" ) )
			{
				userProfileData.profileUuid_ = request.getParameter( "uuid" );
				
				userProfileData.password_ = request.getParameter( "password" );
			}
			System.out.println( "converter End");

			return 0;
		}

		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			errLogger_.logMsg( "Exception :: UserSignupDataConverter : convert - " + ex );

			return -1;
		}
    }
}
