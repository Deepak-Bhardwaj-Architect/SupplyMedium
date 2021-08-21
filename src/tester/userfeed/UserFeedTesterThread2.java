package tester.userfeed;

import core.feed.UserFeedData;
import core.feed.UserFeedManager;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;

public class UserFeedTesterThread2 implements Runnable
{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for( int i = 300; i < 600; i++ )
        {
			
			UserFeedData userFeedData = new UserFeedData( );
			
			userFeedData.feed_ = "This is the user feed description"+i;
			
			userFeedData.feedTitle_ = "This is the user feed title"+i;
			
			CompanyRegnKey regnKey = new CompanyRegnKey( );
			
			regnKey.companyPhoneNo_ = "4044081111";
			
			userFeedData.regnKey_ = regnKey;
			
			UserProfileKey userProfileKey = new UserProfileKey( );
			
			userProfileKey.email_ = "jaffdevil@gmail.com";
			
			userFeedData.userKey_ = userProfileKey;
			
			userFeedData.relativePath_ = "";
			
			userFeedData.userFeedId_ = -1;
			
			
			UserFeedManager userfeedManager = new UserFeedManager( );
			
			int result = userfeedManager.add(userFeedData );
			
			if( result == 1800)
				System.out.println( " Feed inserted test successfully completed" );
			else 
				System.out.println( " Feed inserted test failed." );	
			
			
        }
		
	}

}
