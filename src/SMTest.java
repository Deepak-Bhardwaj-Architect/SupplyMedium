

import core.dashboard.CustomerBasedData;
import core.edi.RFQEDICreator;
import core.trans.RFQData;
import core.trans.RFQItemData;

import tester.dashboard.CustomerBasedReportTest;
import tester.dashboard.TimeBasedReportTest;
import utils.AppStartupInitializer;
import utils.ErrorLogger;



public class SMTest
{

	/**
	 * @param args
	 */
	public static void main( String [ ] args )
	{
		// TODO Auto-generated method stub
		

		ErrorLogger errorLogger = ErrorLogger.instance( );
	
		String msg = "Info::StartupUtil:contextInitialized-Application started";
		
		System.out.println( "Startup" );

		AppStartupInitializer appInitializer = new AppStartupInitializer( );

		appInitializer.initAppConfigProperty( );

		appInitializer.initDBConfigProperty( );

		appInitializer.initErrorCodeConfigProperty( );

		appInitializer.initErrorLogger( );
		
		appInitializer.initAccountPolicies( );
		
		errorLogger.logMsg( msg );
		
		
		
		/*TimeBasedReportTest test = new TimeBasedReportTest( );
		
		test.execute( );
		
		test = null;*/
		
		CustomerBasedReportTest test = new CustomerBasedReportTest( );
		
		test.execute( );
		
		test = null;
		
		
		
	}
	
	
}

