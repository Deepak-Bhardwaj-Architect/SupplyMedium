/**
 * 
 */

package core.searchsupplier;

public class SearchItem 
{
	public String partno_;
	public String name_;
	
	/**
	 * 
	 */
	public void clear( )
	{
		partno_ = null;
		name_ = null;
	}
	
	/**
	 * 
	 */
	public void show( )
	{
		System.out.println( "partno_	= " + partno_ );
		System.out.println( "name_	= " + name_ );
	}

}
