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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import utils.CompanyStatus;
import utils.ErrorLogger;
import utils.PathBuilder;
import utils.PictureStore;
import utils.StringHolder;
import utils.Themes;
import utils.UserType;


import core.regn.CompanyRegnKey;
import core.regn.MailingAddressData;
import core.regn.RegnData;


/**
 * File:  RegnDataConverter.java 
 *
 * Created on Feb 6, 2013 2:39:16 PM
 */

/*
 * This class is used to convert HttpServletRequest to RegnData
 */

public class RegnDataConverter 
{

	/*
	 * Method : convert( ) 
	 * 
	 * Input  : HttpServletRequest object, RegnData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to RegnData object.
	 */
	
	public int convert( HttpServletRequest request,RegnData regnDataObj )
	{
		try
		{

			int addressCount = 0;
			
			if( request.getParameterMap( ).containsKey( "companyid" ) )
			{
				regnDataObj.companyId_ = request.getParameter( "companyid" );
			}

			if( request.getParameterMap( ).containsKey( "companyname" ) )
			{
				regnDataObj.companyName_ = request.getParameter( "companyname" );
			}

			if( request.getParameterMap( ).containsKey( "businesscategory" ) )
			{
				regnDataObj.businessCategory_ = request.getParameter( "businesscategory" );
			}

			if( request.getParameterMap( ).containsKey( "branch" ) )
			{
				regnDataObj.branch_ = request.getParameter( "branch" );
			}

			if( request.getParameterMap( ).containsKey( "addresscount" ) )
			{
				addressCount = Integer.parseInt( request.getParameter( "addresscount" ) );
			}

			List<MailingAddressData> mailingAddressListObjArr = new ArrayList<MailingAddressData>( );

			CompanyRegnKey companyRegnKey = new CompanyRegnKey( );

			companyRegnKey.companyPhoneNo_ = request.getParameter( "phone" );
			
			if( companyRegnKey.companyPhoneNo_ != null)
			{
			    companyRegnKey.companyPhoneNo_ = companyRegnKey.companyPhoneNo_.replaceAll( "-", "" );
				
			    companyRegnKey.companyPhoneNo_ = companyRegnKey.companyPhoneNo_.replaceAll("\\s","");
			}
			

			for( int i = 0; i < addressCount; i++ )
			{
				MailingAddressData mailingAddressObj = new MailingAddressData( );

				mailingAddressObj.address_ = request.getParameter( "address_" + i );

				mailingAddressObj.city_ = request.getParameter( "city_" + i  );

				

				mailingAddressObj.zipcode_ = request.getParameter( "zipcode_" + i );

				mailingAddressObj.countryRegion_ = request.getParameter( "countryregion_"
				        + i  );
				
				if(mailingAddressObj.countryRegion_ .equals( "United States" ))
				{
					
					mailingAddressObj.state_ = request.getParameter( "state_" + i  );
					
				}
				else 
				{
					mailingAddressObj.state_ = request.getParameter( "state_text_" + i );
					
				}

				mailingAddressObj.addressType_ = request.getParameter( "branch_" + i );

				mailingAddressObj.emailid_ = request.getParameter( "email" );

				mailingAddressObj.companyRegnKey_ = companyRegnKey;

				mailingAddressObj.show( );

				mailingAddressListObjArr.add( mailingAddressObj );
			}
			
			

			regnDataObj.mailingAddressArr_ = mailingAddressListObjArr;

			if( request.getParameterMap( ).containsKey( "signupas" ) )
			{
				regnDataObj.signUpAs_ = request.getParameter( "signupas" );
			}

			if( request.getParameterMap( ).containsKey( "firstname" ) )
			{
				regnDataObj.firstName_ = request.getParameter( "firstname" );
			}

			if( request.getParameterMap( ).containsKey( "lastname" ) )
			{
				regnDataObj.lastName_ = request.getParameter( "lastname" );
			}

			if( request.getParameterMap( ).containsKey( "title" ) )
			{
				regnDataObj.title_ = request.getParameter( "title" );
			}

			if( request.getParameterMap( ).containsKey( "department" ) )
			{
				regnDataObj.department_ = request.getParameter( "department" );
			}

			if( request.getParameterMap( ).containsKey( "managersupervisor" ) )
			{
				regnDataObj.managerSupervisor_ = request.getParameter( "managersupervisor" );
			}

			if( request.getParameterMap( ).containsKey( "phone" ) )
			{
				regnDataObj.phoneNo_ = request.getParameter( "phone" );
				
				if( regnDataObj.phoneNo_!=null )
				{
				    regnDataObj.phoneNo_ = regnDataObj.phoneNo_.replaceAll( "-", "" );
					
				    regnDataObj.phoneNo_ = regnDataObj.phoneNo_.replaceAll("\\s","");
				}
			
			}

			if( request.getParameterMap( ).containsKey( "cell" ) )
			{
				regnDataObj.cellNo_ = request.getParameter( "cell" );
			}

			if( request.getParameterMap( ).containsKey( "fax" ) )
			{
				regnDataObj.faxNo_ = request.getParameter( "fax" );
			}

			if( request.getParameterMap( ).containsKey( "email" ) )
			{
				regnDataObj.emailId_ = request.getParameter( "email" );
			}

			if( request.getParameterMap( ).containsKey( "password" ) )
			{
				regnDataObj.password_ = request.getParameter( "password" );
			}

			if( request.getParameterMap( ).containsKey( "divisionname" ) )
			{
				regnDataObj.segmentDivisionName_ = request.getParameter( "divisionname" );
			}

			if( request.getParameterMap( ).containsKey( "unitname" ) )
			{
				regnDataObj.businessUnitName_ = request.getParameter( "unitname" );
			}

			if( request.getParameterMap( ).containsKey( "pricingoption" ) )
			{
				regnDataObj.pricingOption_ = request.getParameter( "pricingoption" );
			}

			regnDataObj.companyRegnKey_ = companyRegnKey;


			/*PathBuilder pathBuilder = new PathBuilder( );
			
			StringHolder logoPath = new StringHolder( );
			
			int pathResult = pathBuilder.getCompanyLogoPath( regnDataObj.companyName_, regnDataObj.companyRegnKey_.companyPhoneNo_, logoPath );
			
			if( pathResult == 0 )  // Logo path fetch successfully
			{
				PictureStore pictureStoreObj = new PictureStore( );
				
				Object fileObject = request.getAttribute( "logo" );
				
				StringHolder storedPath = new StringHolder( );
				
				int storeResult = pictureStoreObj.storeImage( fileObject, logoPath.value, "logo",storedPath );
				
				if( storeResult == 0 )  // Logo stored in folder successfully
				{
					 regnDataObj.logo_ = storedPath.value;
				}
				 
				
			}*/
			if(  request.getAttribute( "logo" )!= null )
			{
				regnDataObj.logoImage_ = request.getAttribute( "logo" );
			}
			

			regnDataObj.theme_ = Themes.theme.DEFAULT.getValue( );
			regnDataObj.userType_ = UserType.type.ADMIN.getValue( );
			regnDataObj.companyStatus_ = CompanyStatus.status.PENDING.getValue( );

			if( request.getParameter( "RequestType" ).endsWith( "RegistrationActivate" ) )
			{
				regnDataObj.uuid_ = request.getParameter( "key" );
			}
			else
			{
				regnDataObj.uuid_ = UUID.randomUUID( ).toString( );
			}

			System.out.println( "Parse request completed" );

			return 0;

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			errLogger_.logMsg( "Exception :: RegnDataConverter : convert - " + ex );

			return -1;
		}
	}
}
