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
import utils.ErrorMaster;

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
        public String                   logoPath_;
	public String	                businessCategory_;
        public String					businessType_;
        public Object					logoImage_;
	

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
        private static ErrorMaster errorMaster_ = null;



	public void Show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "%%%%%%%%%%%%%%%%%%%%%%%%%" );
		errorMaster_.insert( "companyRegnKey_ 		:" + companyRegnKey_ );
		errorMaster_.insert( "companyName_ 			:" + companyName_ );
		errorMaster_.insert( "signupas_:"+        signupas_);
		errorMaster_.insert( "businessCategory_ 		:" + businessCategory_ );
		
		errorMaster_.insert( "companyID_ 			:" + companyID_ );
		errorMaster_.insert( "registeredEmployee_ 	:" + registeredEmployee_ );
		errorMaster_.insert( "thisMonthTrasaction_ 	:" + thisMonthTrasaction_ );
		errorMaster_.insert( "remainingtrasaction_ 	:" + remainingtrasaction_ );
		
		errorMaster_.insert( "pricingOption_ 		:" + pricingOption_ );
		errorMaster_.insert( "maxEmployee_ 			:" + maxEmployee_ );
		errorMaster_.insert( "maxTransaction_ 		:" + maxTransaction_ );
		
		
		
		errorMaster_.insert( "totalSpace_ 			:" + totalSpace_ );
		errorMaster_.insert( "maxEmployee_ 			:" + maxEmployee_ );
		errorMaster_.insert( "maxTransaction_ 		:" + maxTransaction_ );
		
		errorMaster_.insert( "theme_ 				:" + theme_ );
		errorMaster_.insert( "businessType_ 			:" + businessType_ );
		
		errorMaster_.insert( "email_					:" + email_ );
		errorMaster_.insert( "businessContactName_	:" + businessContactName_ );
		errorMaster_.insert( "department_			:" + department_ );
		
		errorMaster_.insert( "phone_					:" + phone_ );
		errorMaster_.insert( "cell_					:" + cell_ );
		errorMaster_.insert( "fax_					:" + fax_ );
		
		errorMaster_.insert( "Mailing Address List" );
		if(this.mailingAddressArr_!=null)
		for( MailingAddressData data : this.mailingAddressArr_ )
		{
			errorMaster_.insert( "---------------------" );

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

    public String getBusinessType_() {
        return businessType_;
    }

    public void setBusinessType_(String businessType_) {
        this.businessType_ = businessType_;
    }
        

}
