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

package core.companyprofile;

import java.util.List;

import core.regn.MailingAddressData;

/**
 * @FileName : CompanyProfileData.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Mar 4, 2013
 * 
 * Purpose : It hold the Company Profile Data
 * 
 */

public class CompanyProfileData
{

	public String	         		companyRegnKey_;

	public String	                companyName_;
	public String	                businessCategory_;
	

	public String	                companyID_;

	public List<MailingAddressData>	mailingAddressArr_;

	public long	                    registeredEmployee_;
	public long	                    thisMonthTrasaction_;
	public long	                    remainingtrasaction_;
	
	
	
	public double 					totalSpace_;
	public double 					spaceOccupied_;
	public double 					spaceRemaining_;

	public String	                pricingOption_;
	public long	                    maxEmployee_;
	public long	                    maxTransaction_;
	
	public String					businessType_;
	
	/*Company Admin details*/
	public String					email_;
	public String					businessContactName_;
	public String					department_;
	
	public String					phone_;
	public String					cell_;
	public String 					fax_;
	/**/
	public String 					signupas_;
	public String	                theme_; // Not Implemented right Now

	public void Show( )
	{
		System.out.println( "%%%%%%%%%%%%%%%%%%%%%%%%%" );
		System.out.println( "companyRegnKey_ 		:" + companyRegnKey_ );
		System.out.println( "companyName_ 			:" + companyName_ );
		System.out.println( "signupas_:"+        signupas_);
		System.out.println( "businessCategory_ 		:" + businessCategory_ );
		
		System.out.println( "companyID_ 			:" + companyID_ );
		System.out.println( "registeredEmployee_ 	:" + registeredEmployee_ );
		System.out.println( "thisMonthTrasaction_ 	:" + thisMonthTrasaction_ );
		System.out.println( "remainingtrasaction_ 	:" + remainingtrasaction_ );
		
		System.out.println( "pricingOption_ 		:" + pricingOption_ );
		System.out.println( "maxEmployee_ 			:" + maxEmployee_ );
		System.out.println( "maxTransaction_ 		:" + maxTransaction_ );
		
		
		
		System.out.println( "totalSpace_ 			:" + totalSpace_ );
		System.out.println( "maxEmployee_ 			:" + maxEmployee_ );
		System.out.println( "maxTransaction_ 		:" + maxTransaction_ );
		
		System.out.println( "theme_ 				:" + theme_ );
		System.out.println( "businessType_ 			:" + businessType_ );
		
		System.out.println( "email_					:" + email_ );
		System.out.println( "businessContactName_	:" + businessContactName_ );
		System.out.println( "department_			:" + department_ );
		
		System.out.println( "phone_					:" + phone_ );
		System.out.println( "cell_					:" + cell_ );
		System.out.println( "fax_					:" + fax_ );
		
		System.out.println( "Mailing Address List" );
		if(this.mailingAddressArr_!=null)
		for( MailingAddressData data : this.mailingAddressArr_ )
		{
			System.out.println( "---------------------" );

			data.show( );

		}
	}
	
	/* Below getter and Setter Method used for JavaBeans Access */
	
	public String getCompanyID_( )
	{
		return companyID_;
	}
	
	public void setCompanyID_( String companyID_ )
	{
		this.companyID_ = companyID_;
	}

	public String getCompanyRegnKey_( )
	{
		return companyRegnKey_;
	}

	public void setCompanyRegnKey_( String companyRegnKey_ )
	{
		this.companyRegnKey_ = companyRegnKey_;
	}

	public String getCompanyName_( )
	{
		return companyName_;
	}

	public void setCompanyName_( String companyName_ )
	{
		this.companyName_ = companyName_;
	}
	
	public String getSignUpAs_()
	{
		return signupas_;
	}
	
	public void setSignUpAs_(String signUpAs_ )
    {
	    this.signupas_=signUpAs_;
    }

	public String getBusinessCategory_( )
	{
		return businessCategory_;
	}

	public void setBusinessCategory_( String businessCategory_ )
	{
		this.businessCategory_ = businessCategory_;
	}
	
	public List<MailingAddressData> getMailingAddressArr_( )
	{
		return mailingAddressArr_;
	}

	public void setMailingAddressArr_(
	        List<MailingAddressData> mailingAddressArr_ )
	{
		this.mailingAddressArr_ = mailingAddressArr_;
	}

	public long getRegisteredEmployee_( )
	{
		return registeredEmployee_;
	}

	public void setRegisteredEmployee_( long registeredEmployee_ )
	{
		this.registeredEmployee_ = registeredEmployee_;
	}

	public long getThisMonthTrasaction_( )
	{
		return thisMonthTrasaction_;
	}

	public void setThisMonthTrasaction_( long thisMonthTrasaction_ )
	{
		this.thisMonthTrasaction_ = thisMonthTrasaction_;
	}

	public long getRemainingtrasaction_( )
	{
		return remainingtrasaction_;
	}

	public void setRemainingtrasaction_( long remainingtrasaction_ )
	{
		this.remainingtrasaction_ = remainingtrasaction_;
	}

	public String getPricingOption_( )
	{
		return pricingOption_;
	}

	public void setPricingOption_( String pricingOption_ )
	{
		this.pricingOption_ = pricingOption_;
	}

	public long getMaxEmployee_( )
	{
		return maxEmployee_;
	}

	public void setMaxEmployee_( long maxEmployee_ )
	{
		this.maxEmployee_ = maxEmployee_;
	}

	public long getMaxTransaction_( )
	{
		return maxTransaction_;
	}

	public void setMaxTransaction_( long maxTransaction_ )
	{
		this.maxTransaction_ = maxTransaction_;
	}

	public String getTheme_( )
	{
		return theme_;
	}

	public void setTheme_( String theme_ )
	{
		this.theme_ = theme_;
	}
	
	
	
	public void setTotalSpace( double totalSpace )
    {
	    this.totalSpace_ = totalSpace;
    }
	
	public double getTotalSpace()
    {
	    return this.totalSpace_;
    }
	
	public void setSpaceOccupied( double spaceOccupied )
    {
	    this.spaceOccupied_ = spaceOccupied;
    }
	
	public double getSpaceOccupied()
    {
	    return this.spaceOccupied_;
    }
	
	public void setSpaceRemaining( double spaceRemaining )
    {
	    this.spaceRemaining_ = spaceRemaining;
    }
	
	public double getSpaceRemaining()
    {
	    return this.spaceRemaining_;
    }

}
