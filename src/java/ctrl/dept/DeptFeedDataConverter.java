package ctrl.dept;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;
import core.dept.DeptKey;
import core.feed.DeptFeedData;
import utils.ErrorMaster;

public class DeptFeedDataConverter
{
	
	/*
	 * Method : convert()
	 * 
	 * Input  : HTTPServletRequest and DeptFeedData object 
	 * 
	 * Return : int
	 * 
	 * Purpose: It it used to convert the HTTPServletRequest to DeptFeeddata object.
	 * So DeptFeedData object copied to caller classes.
	 */
	public int convert( HttpServletRequest request, DeptFeedData feedData )
	{
		try
        {
			String feedIdStr = request.getParameter( "FeedId" );
                        
                        ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
			
			if( feedIdStr !=null)
				feedData.deptFeedId_ 	= Integer.parseInt( feedIdStr );
			
			feedData.deptFeed_		= request.getParameter( "Feed" );
			
			feedData.feedTitle_ 	= request.getParameter( "FeedTitle" );
	
			
			feedData.regnKey_.companyPhoneNo_ = request.getParameter( "RegnKey" );
			
			feedData.userKey_.email_ 			= request.getParameter( "UserKey" );
			
			String deptKeyStr = request.getParameter( "DeptKey" );
			
			errorMaster_.insert( " company feed flag="+ request.getParameter( "CompanyFeedFlag" ));
			
			if( request.getParameter( "CompanyFeedFlag" ) != null)
			
				feedData.companyFeedFlag_ = Integer.parseInt( request.getParameter( "CompanyFeedFlag" ) );
			
			
    		
    		if( deptKeyStr != null )
    		{
    			 String [] strArr = deptKeyStr.split( ":" );
	 	            
	 	            if( strArr.length > 1)
	 	            {
	 	            	DeptKey deptKey = new DeptKey( );
	 	            	
	 	            	deptKey.companyRegnKey_ = feedData.regnKey_;
	 	            	
	 	            	deptKey.deptName_ = strArr[1];
	 	            	
	 	            	feedData.deptKey_ = deptKey;
	 	            }
    		}
			
			
        }
        catch( Exception ex )
        {
            System.out.print("DeptFeedDataConverter exception: "+ex);
//        	ErrorLogger errorLogger = ErrorLogger.instance( );
//        	
//        	String msg = "Exception::DeptFeedDataConverter.convert() - " + ex;
//
//			errorLogger.logMsg( msg );
			
			return -1;
        }
		
		return 0;
	}
	
}
