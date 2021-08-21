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
package core.advertisement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import utils.CompanyType;

import core.regn.CompanyRegnKey;
import core.regn.MailingAddressData;
import core.regn.RegnData;


import db.advertisement.AdTable;
import db.regn.CompanyRegnTable;
import db.regn.MailingAddressTable;



/**
 * File:  CenterAd.java 
 *
 * Created on Oct 3, 2013 11:27:34 AM
 */
public class Advertisement
{

	/*
	 * Method : Advertisement -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public Advertisement( )
	{
		
	}
	
	
	/*
	 * Method : add
	 * 
	 * Input  : AdvertisementData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: Used to add the new Advertisement 
	 */
	
    public int add( AdData adData )
    {
	    
    	AdTable adTable = new AdTable( );
    	
		int result = adTable.insert( adData );
		
		adTable = null;
		
		if( result == 0 )
			return 10700;  // Advertisement successfully created
		else
			return 10703;  // Advertisement creation failed 
    }
       
    /*
	 * Method : delete
	 * 
	 * Input  : adId
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It delete the Advertisement using given adId. Also it remove the 
	 * Advertisement member relationship
	 */
	
    public int remove( long adId )
    {
    	AdTable adTable = new AdTable( );
		
		int result = adTable.delete( adId );
		
		adTable = null;
		
		if( result == 0 )
		{
			// delete the Advertisement members from Ad table
			
			return 10710;
		}
		else 
		{
			return 10711;
		}
	 	    
    }
   
	/*
	 * Method : get
	 * 
	 * Input  : AdvertisementData, list of AdvertisementData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It get all the Advertisement for given ad data object. And copied to adlists parameter.
	 * So it is available for caller classes
	 */

	
    public int get( AdData adData, List<AdData> adlists )
    {
    	
    	//System.out.println( "Advertisement get method" );
    	
    	int result  = 0 ;
    	
    	// Fetching company keys filtering with category and company type
    	
    	Set<CompanyRegnKey> regnKeysCat = new HashSet<CompanyRegnKey>( );
    	
    	result = getCompanyKeysForCatAndType( adData, regnKeysCat );
    	
    	System.out.println( "category set count="+regnKeysCat.size( ) );
    	
    	if( result !=0 )
    		return 10721;
    	
    	// Fetching company keys filtering with country
    	
		Set<CompanyRegnKey> regnKeysCountry = new HashSet<CompanyRegnKey>( );
    	
    	result = getCompanyKeysForCountry( adData, regnKeysCountry );
    	
    	System.out.println( "country set count="+regnKeysCountry.size( ) );
    	
    	if( result !=0 )
    		return 10721;
    	
    	// Intersect of two sets. This final set contain the keys with category,company type and country filter
    	
    	regnKeysCat.retainAll( regnKeysCountry );
    	
    	System.out.println( "intersect of category and country="+regnKeysCat.size( ) );
    	
    	// Fetching all the company keys in ad table
    	
    	Set<CompanyRegnKey> regnKeysAd = new HashSet<CompanyRegnKey>( );
    	
    	result = getCompanyKeysForAd( adData, regnKeysAd );
    	
    	if( result!= 0 )
    		return 10721;
	
    	System.out.println( "ad set count="+regnKeysAd.size( ) );
    	
    	// Intersect of two sets
    	
    	regnKeysCat.retainAll( regnKeysAd );
    	
    	System.out.println( "intersect of cat final and ad="+regnKeysCat.size( ) );
    	
    	if( regnKeysCat.size( ) == 0 )
    		return 10720; // There is no ad suitable for this company
		
		
		// Getting ads for filter companies
		
		List<AdData> allAds = new ArrayList<AdData>( );
		
		AdTable adTable = new AdTable( );
                
                System.out.println("=="+adData.regnKey_.toString());
		
		adTable.getAds( regnKeysCat, allAds ,adData.regnKey_.toString());
		
		Random random = new Random();
		
		AdStatistic adStatistic = new AdStatistic( );
		
		if( adData.noOfAds_ > allAds.size( ) )
		{
			for( AdData adData2 : allAds )
            {
	            adlists.add( adData2 );
	            
	            AdStatisticData adStatisticData = new AdStatisticData( );
	            
	            adStatisticData.adId_ = adData2.adId_;
	            
	            adStatistic.add( adStatisticData );
	            
	            adStatisticData = null;
	            
	            
            }
		}
		else
		{
			for( int i=0;i<adData.noOfAds_;i++ )
			{
				
				AdData aData = allAds.get(random.nextInt(allAds.size()));
				
				adlists.add( aData );
				
				 AdStatisticData adStatisticData = new AdStatisticData( );
		            
		         adStatisticData.adId_ = adData.adId_;
		            
		         adStatistic.add( adStatisticData );
		            
		         adStatisticData = null;
			}
	    	
		}
		
		adStatistic = null;
	
		return 10720;
    }
    
    
    /*
	 * Method : update()
	 * 
	 * Input  : AdData object 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update ad details to supply medium for
	 * Particular ad id.
	 */
    
	public int update( AdData adData )
	{
		int responseCode = 0;
		
		AdTable adTable = new AdTable( );
		
		responseCode = adTable.update( adData );
		
		adTable = null;
		
		if( responseCode == 0 ) // ad updated successfully in SupplyMedium
		{
			return 10730;
			
		}
		else // failed
		{
			return 10731;
		}
		
	}
	
	// Used to fetch the all the company registration keys for given category and CompanyType
	
	private int getCompanyKeysForCatAndType( AdData adData,  Set<CompanyRegnKey> regnKeys )
	{
    	
    	// Fetching company keys using category and company type filter
    	CompanyRegnTable regnTable = new CompanyRegnTable( );
    	
    	RegnData queryFilter = new RegnData( );
    	
    	RegnData regnData = new RegnData( );
    	
    	int result = regnTable.getCompany( adData.regnKey_, regnData );
    	
    	if( result != 0 )
    		return result;
    	
    	queryFilter.businessCategory_ = regnData.businessCategory_;
    	
    	if( regnData.companyType_.equals( CompanyType.type.BUYER.getValue( ) ))
    		queryFilter.signUpAs_  = CompanyType.type.SUPPLIER.getValue( );
    	
    	else if( regnData.companyType_.equals( CompanyType.type.SUPPLIER.getValue( ) ) )
    		queryFilter.signUpAs_  = CompanyType.type.BUYER.getValue( );
    	
    	result = regnTable.searchKeys( queryFilter, regnKeys );
    	
    	regnData = null;
    	
    	queryFilter = null;
    	
    	regnTable = null;
    	
    	return result;
	}
	
	// Used to fetch all the company registration keys for given country
	private int getCompanyKeysForCountry( AdData adData,  Set<CompanyRegnKey> regnKeys )
	{
		MailingAddressTable mailingAddressTable = new MailingAddressTable( );
		
		MailingAddressData mailingAddressData = new MailingAddressData( );
		
		int result = mailingAddressTable.find( adData.userProfileKey_, mailingAddressData );
		
		if( result != 0 )
			return result;
			
		
		result = mailingAddressTable.getAllRegnKeys( mailingAddressData.countryRegion_, regnKeys );
		
		mailingAddressData = null;
		
		mailingAddressTable = null;
		
		return result;
	}
	
	// Used to fetch all the company registration keys which are publish ads
	private int getCompanyKeysForAd( AdData adData,  Set<CompanyRegnKey> regnKeys )
	{
		AdTable adTable = new AdTable( );
    	
    	int result = adTable.getAllAdRegnKeys( regnKeys );
        result = adTable.getAllAdRegnKeys( regnKeys );
    	//System.out.println("ABNAHONA"+result);
    	adTable = null;
    	
    	return result;
		
	}
	
}
