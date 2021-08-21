package core.login;

/*
 * Class  : UserKeyUuidMapperData
 *
 * Purpose: It contains the data variables related to userkey_uuid_mapper table
 */

public class UserkeyUuidMapperData
{
	public String userRelKey_;
	public String uuid_;

	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "userRelKey_	= " + userRelKey_ );
		System.out.println( "uuid_		= " + uuid_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: It is used to reset all class variable values
	 */

	public void clear( )
	{
		this.userRelKey_ = null;
		this.uuid_ = null;
	}

}
