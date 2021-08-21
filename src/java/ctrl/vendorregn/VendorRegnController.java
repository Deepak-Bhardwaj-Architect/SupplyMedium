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

package ctrl.vendorregn;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import core.vendorregn.NRVendorSearchData;
import core.vendorregn.VendorRegn;
import core.vendorregn.VendorRegnData;
import utils.ErrorLogger;

/**
 * File:  VendorRegnCtrl.java 
 *
 * Created on May 21, 2013 3:55:17 PM
 */

/*
 * Class: VendorRegnController
 * 
 * Purpose: This class is used to control the vendor registration requests.
 * The various request types are,
 * 
 * 1. ListMyRequests - To list all the vendor requests sent by the logged in company
 * 2. ListOtherVendorsRequest - To list all the vendor requests received by the logged in company
 * 3. ListNRVendors - To list all the vendors who are not registered for 
 * 	  given CompanyRegnKey
 * 4. AddVendor	- To add new vendor to the company 
 * 5. UpdateStatus - To update status of the vendor registrations
 * 6. AddInquiry - To add new inquiry detail
 */

public class VendorRegnController
{
	ErrorLogger errLogger_;
	
	/*Constructor*/
	public VendorRegnController( )
	{
		errLogger_ = ErrorLogger.instance( );
	}
	
	
	/*
	 * Method: getVendors
	 * 
	 * Input: HttpServletRequest request, list<VendorRegnData> obj (As ref)
	 * 
	 * Return: int
	 * 
	 * Purpose: This method process the request type
	 * 'ListAllRequest',  and returns the response code
	 * 
	 */
	
	public int getVendorRequest( HttpServletRequest request, List<VendorRegnData> vendorRegnDataList )
	{
		String requestType = request.getParameter( "RequestType" );
		
		//CompanyRegnKey regnKey = new CompanyRegnKey( );
		
		VendorRegnData vendorRegnData = new VendorRegnData( );
		
		VendorRegnDataConverter converter = new VendorRegnDataConverter( );
		
		int result = converter.getVendorRegnData( request, vendorRegnData );
		
		converter = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::VendorRegnController.getVendors() - Failed " +
								"to parse '" + requestType + "' request ");
		
			//regnKey = null;
			
			return 3699; //Parse error
		}
		
		if( requestType.equals( "ListMyRequests" ) )
		{
			errLogger_.logMsg( "Info::VendorRegnController.getVendors() - Request " +
					"'" + requestType + "' for RegnKey <" + vendorRegnData.regnKey_ + " in process ");
			result = listMyVendorReq( vendorRegnData, vendorRegnDataList );
                        //System.out.print("resulting =================>"+result);
		}
		else if( requestType.equals( "ListOtherVendorsRequest" ))
		{
			errLogger_.logMsg( "Info::VendorRegnController.getVendors() - Request " +
					"'" + requestType + "' for RegnKey <" + vendorRegnData.regnKey_ + " in process ");
			result = listOtherVendorReq( vendorRegnData, vendorRegnDataList );
		}
		else 
		{
			errLogger_.logMsg( "Error::VendorRegnController.getVendors() - Failed " +
					"to parse '" + requestType + "' request ");
			
			result = 3699;
		}
		
		System.gc( );
		
		return result;
	}
	
	/*
	 * Method: getNRVendors
	 * 
	 * Input: HttpServletRequest request, list<RegnData> obj (As ref)
	 * 
	 * Return: int
	 * 
	 * Purpose: This method process the request type
	 * 'ListNRVendors' and returns the response code
	 * 
	 */
	
	public int getNRVendors( HttpServletRequest request, List<RegnData> vendorRegnDataList )
	{
		String requestType = request.getParameter( "RequestType" );
		
		//CompanyRegnKey regnKey = new CompanyRegnKey( );
		NRVendorSearchData nrVendorSrchData = new NRVendorSearchData( );
		
		VendorRegnDataConverter converter = new VendorRegnDataConverter( );
		
		int result = converter.getNRVendorSearchData( request, nrVendorSrchData );
		
		converter = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::VendorRegnController.getNRVendors() - Failed " +
								"to parse '" + requestType + "' request ");
		
			nrVendorSrchData = null;
			
			return 3699; //Parse error
		}
		
		if( requestType.equals( "ListNRVendors" ) )
		{
			errLogger_.logMsg( "Info::VendorRegnController.getNRVendors() - Request " +
					"'" + requestType + "' in process ");

			result = listNonRegVendors( nrVendorSrchData, vendorRegnDataList );
		}
		else 
		{
			errLogger_.logMsg( "Error::VendorRegnController.getNRVendors() - Failed " +
					"to parse '" + requestType + "' request ");
			
			result = 3699;
		}

		return result;
	}
	
	/*
	 * Method: processVendors
	 * 
	 * Input: HttpServletRequest request
	 * 
	 * Return: int
	 * 
	 * Purpose: This method process the request types, 
	 * 'AddVendor', 'UpdateStatus', and 'AddInquiry', then
	 * returns the response code
	 */
	
	public int processVendors( HttpServletRequest request )
	{
		String requestType = request.getParameter( "RequestType" );
		
		VendorRegnData vendorRegnData = new VendorRegnData( );
		
		VendorRegnDataConverter converter = new VendorRegnDataConverter( );
		
		int result = converter.getVendorRegnData( request, vendorRegnData );
		
		//errorMaster_.insert( "Request Sender type at process vendors in controller - " + vendorRegnData.requestSenderType_ );
		
		converter = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::VendorRegnController.processVendors() - Failed " +
								"to parse '" + requestType + "' request ");
		
			vendorRegnData = null;
			
			return 3699; //Parse error
		}
		
		if( requestType.equals( "AddVendor" ) )  // New add vendor request
		{
			errLogger_.logMsg( "Info::VendorRegnController.processVendors() - Request " +
					"'" + requestType + "' in process ");
			result = addVendor( vendorRegnData );
		}
		else if( requestType.equals( "Update" ))  // Buyer initiate the vendor registration request
		{
			errLogger_.logMsg( "Info::VendorRegnController.processVendors() - Request " +
					"'" + requestType + "' in process ");
			result = updateVendor( vendorRegnData );
		}
		else if( requestType.equals( "UpdateStatus" ) )  // 
		{
			errLogger_.logMsg( "Info::VendorRegnController.processVendors() - Request " +
					"'" + requestType + "' in process ");
			result = updateVendorStatus( vendorRegnData );
		}
		else if( requestType.equals( "AddInquiry" ) )  // 
		{
			errLogger_.logMsg( "Info::VendorRegnController.processVendors() - Request " +
					"'" + requestType + "' in process ");
			result = addInquiry( vendorRegnData );
		}
		else if( requestType.equals( "GetVendorRegnInfo" ) )  // This is used to get the 
		{
			errLogger_.logMsg( "Info::VendorRegnController.processVendors() - Request " +
					"'" + requestType + "' in process ");
			result = addInquiry( vendorRegnData );
		}
		else 
		{
			errLogger_.logMsg( "Error::VendorRegnController.getVendors() - Failed " +
					"to parse '" + requestType + "' request ");
			
			result = 3699;
		}
		
		return result;
	}
	
	
	/*This is called to list all vendor regn req sent by the logged in company 
	 * from VendorRegnController's getVendorRequest method*/
	
	public int listMyVendorReq( VendorRegnData vendorRegnData, List<VendorRegnData> vendorRegnDataList )
	{
		VendorRegn vendorRegn = new VendorRegn( );
		
		int result = vendorRegn.getMyVendorReq( vendorRegnData, vendorRegnDataList );
		//System.out.print("resultssssss =================>"+result);
		vendorRegn = null;
		
		return result;
	}
	
	/*This is called to list all vendor regn req received by the logged in company 
	 * from VendorRegnController's getVendorRequest method*/
	
	public int listOtherVendorReq( VendorRegnData vendorRegnData, List<VendorRegnData> vendorRegnDataList )
	{
		VendorRegn vendorRegn = new VendorRegn( );
		
		int result = vendorRegn.getOtherVendorReq( vendorRegnData, vendorRegnDataList );
		
		vendorRegn = null;
		
		return result;
	}
	
	/*This is called to list all non registered vendor for given CompanyRegnKey
	 * from VendorRegnController's getNRVendors method*/

	public int listNonRegVendors( NRVendorSearchData nrVendorSrchData, List<RegnData> NRVendorRegnDataList )
	{
		VendorRegn vendorRegn = new VendorRegn( );
		
		int result = vendorRegn.getNonRegList( nrVendorSrchData, NRVendorRegnDataList );
		
		vendorRegn = null;
		
		return result;
	}
	
	/*This is called to add new vendor from VendorRegnController's processVendors method*/
	
	public int addVendor( VendorRegnData vendorRegnData )
	{
		VendorRegn vendorRegn = new VendorRegn( );
		
		int result = vendorRegn.add( vendorRegnData );
		
		vendorRegn = null;
		
		return result;
	}
	
	/*This is called to update vendor regn from VendorRegnController's
	 * processVendors method*/
	
	public int updateVendor( VendorRegnData vendorRegnData )
	{
		VendorRegn vendorRegn = new VendorRegn( );
		
		int result = vendorRegn.update( vendorRegnData );
		
		vendorRegn = null;
		
		return result;
	}
	
	/*This is called to update vendor regn status from VendorRegnController's
	 * processVendors method*/
	
	public int updateVendorStatus( VendorRegnData vendorRegnData )
	{
		VendorRegn vendorRegn = new VendorRegn( );
		
		int result = vendorRegn.updateStatus( vendorRegnData );
		
		vendorRegn = null;
		
		return result;
	}
	
	/*This is called to add inquiry from VendorRegnController's
	 * processVendors method*/
	
	public int addInquiry( VendorRegnData vendorRegnData )
	{
		VendorRegn vendorRegn = new VendorRegn( );
		
		int result = vendorRegn.addInquiry( vendorRegnData );
		
		vendorRegn = null;
		
		return result;
	}
	
	/*This is used to fetch the company name*/
	
	public String get( CompanyRegnKey vendorRegnKey )
	{
		VendorRegn vendorRegn = new VendorRegn( );
		
		return vendorRegn.get( vendorRegnKey );
	}
}
