

//import core.dashboard.CustomerBasedData;
//import core.edi.RFQEDICreator;
//import core.trans.RFQData;
//import core.trans.RFQItemData;

import tester.dashboard.CustomerBasedReportTest;
//import tester.dashboard.TimeBasedReportTest;
import utils.AppStartupInitializer;
import utils.ErrorLogger;
import utils.ErrorMaster;



public class SMTest
{

	/**
	 * @param args
	 */
    
         private static ErrorMaster errorMaster_ = null;
         
         
	public static void main( String [ ] args )
	{
		// TODO Auto-generated method stub
		
		String msg = "Info::StartupUtil:contextInitialized-Application started";
		
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );

                errorMaster_.insert( "Startup" );

		AppStartupInitializer appInitializer = new AppStartupInitializer( );

		appInitializer.initAppConfigProperty( );

		appInitializer.initDBConfigProperty( );

		appInitializer.initErrorCodeConfigProperty( );

		appInitializer.initErrorLogger( );
		
		appInitializer.initAccountPolicies( );
		
		errorMaster_.insert( msg );
		
		
		
		/*TimeBasedReportTest test = new TimeBasedReportTest( );
		
		test.execute( );
		
		test = null;*/
		
		CustomerBasedReportTest test = new CustomerBasedReportTest( );
		
		test.execute( );
		
		test = null;
		
		
		
	}
	
	
}

