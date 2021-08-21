package servlet.prodcatalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ErrorLogger;
import utils.IntHolder;
import utils.StringHolder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import core.externalpage.enumTemplateAction;
import core.login.SessionData;
import core.prodcatalog.ProductCatalogData;
import core.prodcatalog.enumProductCatalogOperation;
import ctrl.prodcatalog.ProdCatalogController;

/**
 * @FileName : ProdCatalogMgmtServlet.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Jul 27, 2013
 * 
 * @Purpose : It Will handle all the Product Catalog Related function
 * 
 */
@WebServlet("/ProdCatalogMgmtServlet")
public class ProdCatalogMgmtServlet extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	public ProdCatalogMgmtServlet()
	{
		super( );
	}

	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		enumProductCatalogOperation action = enumProductCatalogOperation.valueOf( request.getParameter( "Action" ) );

		ErrorLogger errLogger = ErrorLogger.instance( );
		// HttpSession session = request.getSession( );
		// SessionData sessionData = (SessionData)session.getAttribute(
		// "UserSessionData" );

		JsonObject responseJson = new JsonObject( );

		try
		{

			/*
			 * String msg =
			 * "Info::ProdCatalogMgmtServlet.doPost() - Request to Fetch " +
			 * action + " Catalog" + " for the company " + "<" +
			 * sessionData.companyRegnKeyStr_ + ">, " + "<" +
			 * sessionData.companyName_ + "> by user <" + sessionData.username_
			 * + ">";
			 */
			// errLogger.logMsg( msg );

			ProdCatalogController prodcatalog = new ProdCatalogController( );
			int Result = 0;
			switch( action )
			{

			case RemoveItem:
				StringHolder removingItem = new StringHolder( );
				Result = prodcatalog.RemoveItem( request, removingItem );
				if( Result == 15010 )
				{
					responseJson.addProperty( "result", "success" );
					responseJson.addProperty( "message", "Item removed successfully" );
				}
				else
				{
					responseJson.addProperty( "result", "failed" );
					responseJson.addProperty( "message", "Error occurred while removing the item. Try again" );
				}
				break;

			case UpdateItem:
				Result = prodcatalog.UpdateItem( request );
				if( Result == 15020 )
				{
					responseJson.addProperty( "result", "success" );
					responseJson.addProperty( "message", "Item updated successfully" );
				}
				else
				{
					responseJson.addProperty( "result", "failed" );
					responseJson.addProperty( "message", "Error occurred while updating the item. Try again" );
				}

				break;
			case AddItem:
				Result = prodcatalog.AddItem( request );
				if( Result == 15030 )
				{
					responseJson.addProperty( "result", "success" );
					responseJson.addProperty( "message", "Item Added Successfully" );
					responseJson.addProperty( "ItemKey", "Item Added Successfully" );
				}
				else
				{
					responseJson.addProperty( "result", "failed" );
					responseJson.addProperty( "message", "Error occurred while updating the item. Try again" );
				}

				break;

			case ProductKeyExist:

				IntHolder result = new IntHolder( );
				Result = prodcatalog.ProductKeyExist( request, result );
				if( Result == 15040 )
				{
					responseJson.addProperty( "result", "success" );
					responseJson.addProperty( "message", "Part number already exist" );
					responseJson.addProperty( "keyExist", result.value );
				}
				else
				{
					responseJson.addProperty( "result", "failed" );
					responseJson.addProperty( "message", "Error occurred while checking the produckey validation" );
				}

				break;

			case CSVImportor:
				IntHolder TotalNumberofRecord = new IntHolder( );
				IntHolder TotalNumberofRecordAdded = new IntHolder( );
				Result = prodcatalog.CSVimport( request, TotalNumberofRecord, TotalNumberofRecordAdded );
				System.out.println("Result of Request :"+ Result);
				
			   if( Result == 15051 )
				{
					responseJson.addProperty( "result", "success" );
					responseJson.addProperty( "message", "CSV file not in correct format. Use the valid file" );
				}
				else if( Result == 15052 )
				{
					responseJson.addProperty( "result", "success" );
					responseJson.addProperty( "message", "Error occured while processing the file" );
				}
				else if(Result == 15054)
				{
					responseJson.addProperty( "result", "success" );
					responseJson.addProperty( "message", "Upload failed" );
				}
				else if( TotalNumberofRecord.value == 0 )
				{
					responseJson.addProperty( "result", "success" );
					responseJson.addProperty( "message", " No records found" );
				}
				else if( Result == 15055 || Result == 15050 )
				{
					System.out.println(TotalNumberofRecord.value+" : "+TotalNumberofRecordAdded.value);
					
					if( TotalNumberofRecord.value > 0 && TotalNumberofRecordAdded.value == 0 )
					{
						responseJson.addProperty( "result", "success" );
						responseJson.addProperty( "message", "Uploading operation failed. Try again." );
					}
					else if( TotalNumberofRecord.value == TotalNumberofRecordAdded.value )
					{
						responseJson.addProperty( "result", "success" );
						responseJson.addProperty( "message", TotalNumberofRecordAdded.value + " item(s)  uploaded successfully." );
					}
					else
					{
						int failed = TotalNumberofRecord.value - TotalNumberofRecordAdded.value;
						
						responseJson.addProperty( "result", "success" );
						responseJson.addProperty( "message", TotalNumberofRecordAdded.value + " of "+TotalNumberofRecord.value+"item(s) uploaded. <br />" + failed
						        + " item(s) skipped" );
					}
				}
				else
				{
					responseJson.addProperty( "result", "failed" );
					responseJson.addProperty( "message", "Error occurred while updating the item. Try again" );
				}

				break;

			default:
				responseJson.addProperty( "result", "failed" );
				break;

			}

		}
		catch( Exception e )
		{
			String msg = "Exception::FetchProdCatalogServlet.doPost() - " + e + "\r\n\n\n";
			errLogger.logMsg( msg );
			responseJson.addProperty( "result", "failed" );
		}

		System.out.println( "json str=" + responseJson );
		response.setContentType( "application/json" );
		response.setCharacterEncoding( "UTF-8" );
		response.getWriter( ).write( responseJson.toString( ) );

	}

}
