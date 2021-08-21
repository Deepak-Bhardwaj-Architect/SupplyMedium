package utils;

public class Test implements Runnable
{

	@Override
	public void run( )
	{
		// TODO Auto-generated method stub
                ErrorMaster errorMaster_ = null;

                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );

		errorMaster_.insert( "Timer" );

	}

}
