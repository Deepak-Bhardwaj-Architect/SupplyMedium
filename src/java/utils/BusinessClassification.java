package utils;

/*
 * Class: BusinessClassification
 * 
 * Purpose: This holds the config value for Business Classfication per the Federal
 * Acquisition Regulations.  Section 2.101, where applicable: (Select all that apply)
 */

public class BusinessClassification
{
	
	public enum status
	{
		/*Disadvantaged: In addition to meeting the requirements
		 * of the FAR definition, any business classifying themselves
		 * as Disadvantaged must be certified by the small business
		 * administration and hvat that certification in good standing*/
		DISADVANTAGED( "Disadvantaged" ),
		
		/*HUBZone: In addition to meeting the requirements of the FAR 
		 * definition, any business classifying themselves as a HUBZone
		 * one must be certified by the small business administration
		 * and have that certification in good standing*/
		HUBZONE( "HubZone" ),
		
		/*Women-Owned*/
		WOMENOWNED( "WomenOwned" ),

		/*Handicapped-owned*/
		HANDICAPPEDOWNED( "HandicappedOwned" ),
		
		/*veteran-owned*/
		VETERANOWNED( "VETERANOWNED" ),
		
		/*Service-Disabled veteran-owned*/
		SDVETERANOWNED( "SDVETERANOWNED" ),
		
		/*Historically-back college or minority institution*/
		HBCORMI( "HBCORMI" ),
		
		/*Minority Business Enterprise*/
		MBE( "MBE" ),
		
		/*Non-Profit: Any business or organization that has
		 * received non-profit status under JRs Regulation
		 * 501(c)(3).*/
		NONPROFIT( "NonProfit" ),
		
		/*Foriegn: A concern which is not incorporated in the
		 * united states or an unincorporated concern having its
		 * principle place of business outside the united states*/
		FOREiGN( "Foreign" ),
		
		/*Public Sector: An agency of the Federal or state government
		 * or a local municipality*/
		PUBLICSECTOR( "PublicSector" ),
		
		/*Alaska native corporations or indian tribe that is not
		 * a small business*/
		ANCORITNSB( "ANCORITNSB" ),
		
		/*Alaska Native Corporations or Indian Tribe: Not certified
		 * by the small business administration as disadvantaged*/
		ANCORITNCD( "ANCORITNCD" );
		
		private status(String value)
		{
			this.value = value;
		}

		private final String value;

		public String getValue( )
		{
			return value;
		}
	}
}
