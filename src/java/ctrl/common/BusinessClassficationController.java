package ctrl.common;

import java.util.List;

import utils.BusinessClassification;
import utils.ErrorLogger;

public class BusinessClassficationController
{
	
	private ErrorLogger errorLogger_;
	
	public BusinessClassficationController( )
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	public int getBusinessClassfications( List<String> businessClassficationList )
	{
		try
        {
			for( BusinessClassification.status status: BusinessClassification.status.values( ))
			{
				businessClassficationList.add( status.getValue( ) );
			}	
			return 0;
			
        } catch( Exception e )
        {
        	errorLogger_.logMsg( "Exception::BusinessClassficationController.getBusinessClassifactions( ) -" + e );
        	
        	return -1;
        }
		
	}
}
