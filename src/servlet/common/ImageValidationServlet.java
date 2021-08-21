package servlet.common;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.google.gson.Gson;


import utils.ErrorLogger;


@WebServlet("/ImageValidationServlet")
public class ImageValidationServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	
	public ImageValidationServlet()
	{
		super( );
		
	}

	
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		
		
	}

	

	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It is used to validate the selected image with given constraints. If 
	 * Image vales are not satisfy the constraints it send the error message to view.
	 * It mainly check the image size and dimension.
	 */
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
	
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		
		try
        {
			Object fileObject = request.getAttribute( "Image" );
			
			System.out.println( "Image"+fileObject );
			
			double maxSize = Double.parseDouble( request.getParameter( "Size" ) );
			
			int width 	= Integer.parseInt( request.getParameter( "Width" ) );
			
			int height  = Integer.parseInt( request.getParameter( "Height" ) );
			
			String isExactDim = request.getParameter( "IsExactDimension" );
			
			if( fileObject != null )
			{
				FileItem fileItem = (FileItem)fileObject;
				
				BufferedInputStream is = new BufferedInputStream ( fileItem.getInputStream() );  
				BufferedImage image = ImageIO.read ( is );  
				
				// Finding image size in mb
				double imageSize = fileItem.getSize( );
				
				imageSize = imageSize / (1024 * 1024);
				
				// Finding image dimension
				
				long imageWidth 	= image.getWidth( );
				
				long imageHeight  	= image.getHeight( );
				
				String result = "success";
				
				String message = "";
				
				if( maxSize < imageSize  )
				{
					result = "failed";
					
					message += "Image size should be less than or equal to "+maxSize+"MB";
				}
				if( isExactDim.equals( "Yes" ))
				{
					
					if( width != imageWidth || height != imageHeight )
					{
						result = "failed";
						
						message += "Image dimension should be "+width+"x"+height;
					}
					
				}
				else
				{
					if( width < imageWidth || height < imageHeight )
					{
						result = "failed";
						
						message += "Image dimension should be less than or equal to "+width+"x"+height;
					}
				}
				
			
				Map<String, String> jsonMap = new HashMap<String, String>( );
				
				jsonMap.put( "result",result );
				
				jsonMap.put( "message", message );
				
				String jsonStr = new Gson( ).toJson( jsonMap );
				
				jsonMap = null;
				
				response.getWriter( ).write( jsonStr );
			}
	        
        }
        catch( Exception e )
        {
        	String msg = "Exception::ImageValidation.doPost() - "+e+"\r\n\n";

			ErrorLogger.instance( ).logMsg( msg );
			
			Map<String, String> jsonMap = new HashMap<String, String>( );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			jsonMap = null;
			
			response.getWriter( ).write( jsonStr );
	        
        }
		
	}

}
