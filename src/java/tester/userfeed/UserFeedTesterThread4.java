package tester.userfeed;

import core.feed.UserFeedData;
import core.feed.UserFeedManager;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import utils.ErrorMaster;

public class UserFeedTesterThread4 implements Runnable
{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for( int i = 900; i < 1200; i++ )
        {
			
			UserFeedData userFeedData = new UserFeedData( );
			
			userFeedData.feed_ = "This is the user feed description"+i;
			
			userFeedData.feedTitle_ = "This is the user feed title"+i;
			
			CompanyRegnKey regnKey = new CompanyRegnKey( );
			
			regnKey.companyPhoneNo_ = "4044083333";
			
			userFeedData.regnKey_ = regnKey;
			
			UserProfileKey userProfileKey = new UserProfileKey( );
			
			userProfileKey.email_ = "";
			
			userFeedData.userKey_ = userProfileKey;
			
			userFeedData.relativePath_ = "";
			
			userFeedData.userFeedId_ = -1;
			
			
			UserFeedManager userfeedManager = new UserFeedManager( );
			
			int result = userfeedManager.add(userFeedData );
                        ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );

			
			if( result == 1800)
				errorMaster_.insert( " Feed inserted test successfully completed" );
			else 
				errorMaster_.insert( " Feed inserted test failed." );	
			
			
        }
		
	}

}
