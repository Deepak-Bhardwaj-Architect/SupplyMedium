package ctrl.dept;


import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.dept.DeptFolderKey;
import core.dept.DeptKey;
import core.dept.FileData;
import core.regn.CompanyRegnKey;

public class DeptPageDataConverter
{

	/*
	 * Method : getDeptKey( ) 
	 * 
	 * Input  : HttpServletRequest object, DeptKey object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to DeptKey object.
	 */
	
	public int getDeptkey( HttpServletRequest request, DeptKey deptKey )
    {
		try
		{
			
			CompanyRegnKey regnKey = new CompanyRegnKey( );

			String deptKeyStr = request.getParameter( "DeptKey" );

			if( deptKeyStr != null )
			{
				String [ ] strArr = deptKeyStr.split( ":" );

				if( strArr.length > 1 )
				{
					String deptName = strArr[1];

					String compPhoneNo = strArr[0];

					regnKey.companyPhoneNo_ = compPhoneNo;

					deptKey.companyRegnKey_ = regnKey;

					deptKey.deptName_ = deptName;
				}

			}
	        
	        return 0;
        }
        catch( Exception ex )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
        	String errMsg = "Exception:DeptPageDataConverter.getDeptkey() - "+ex;
			
			errLogger.logMsg( errMsg );
			
	        return -1;  // failed
        }
    }
	
	
	public int getFilesData(HttpServletRequest request, FileData fileData )
	{
		
		String regnKeyStr 	= request.getParameter( "RegnKey" );
		
		String deptKeyStr 	= request.getParameter( "DeptKey");
		
		String folderKeyStr = request.getParameter( "FolderKey" );
		
		Object file 		= request.getAttribute( "UploadFile" );
		
		long fileId = 0;
		
		if( request.getParameter( "FileId" ) != null )
		
			fileId 		= Integer.parseInt( request.getParameter( "FileId" ) );
		
	
		String deptName = null;
		
		String folderName = null;
		
		CompanyRegnKey regnKey = null;
		
		
		
		
		
		/* Parsing deptkey string to deptkey object*/
		
		if( deptKeyStr !=null )
		{
			String[] deptKeyStrArr = deptKeyStr.split( ":" );
			
			
			if( deptKeyStrArr.length >1 )
			{
				deptName = deptKeyStrArr[1];
			}
		}
		
		
		
		
		/* Parsing deptfolderkey string to deptfolderkey object*/
		
		if( folderKeyStr != null )
		{
			String[] folderKeyStrArr = folderKeyStr.split( ":" );
			
			
			if( folderKeyStrArr.length >1 )
			{
				folderName = folderKeyStrArr[1];
			}
		}
		
		
		
		
		/* creating company registration key */
		
		if( regnKeyStr != null )
		{
			regnKey = new CompanyRegnKey( );
			
			regnKey.companyPhoneNo_ = regnKeyStr;
		}
		
		
		
		
		/* Creating Deptkey */
		
		DeptKey deptKey = new DeptKey( );
		
		deptKey.companyRegnKey_ = regnKey;
		
		deptKey.deptName_ = deptName;
		
		
		/* Creating DeptFolderkey */
		
		DeptFolderKey folderKey = new DeptFolderKey( );
		
		folderKey.companyRegnKey_ = regnKey;
		
		folderKey.folderName_ = folderName;
		
		
		
		/* assigning parse data to filedata object */
		
		fileData.file_ = file;
		
		fileData.attrData_.regnKey_ = regnKey;
		
		fileData.attrData_.deptKey_ = deptKey;
		
		fileData.attrData_.deptFolderKey_ = folderKey;
		
		fileData.attrData_.deptFileId_ = fileId;
		
		
		return 0;
	}
	
	
	
}
