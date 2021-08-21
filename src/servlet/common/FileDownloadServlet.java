package servlet.common;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/FileDownloadServlet")
public class FileDownloadServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	 private String filePath;
	 
	 private static final int BUFSIZE = 4096;

	public FileDownloadServlet()
	{
		super( );
		
	}

	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		
		filePath = getServletContext().getRealPath("") + File.separator + "sample.txt";
		
		File file = new File(filePath);
        int length   = 0;
        ServletOutputStream outStream = response.getOutputStream();
        ServletContext context  = getServletConfig().getServletContext();
        String mimetype = context.getMimeType(filePath);
        
        // sets response content type
        if (mimetype == null) {
            mimetype = "application/octet-stream";
        }
        response.setContentType(mimetype);
        response.setContentLength((int)file.length());
        String fileName = (new File(filePath)).getName();
        
        // sets HTTP header
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        
        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        
        // reads the file's bytes and writes them to the response stream
        while ((in != null) && ((length = in.read(byteBuffer)) != -1))
        {
            outStream.write(byteBuffer,0,length);
        }
        
        in.close();
        outStream.close();
        
        System.out.println( "Download file request completed" );
	}

	

	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		
	}
}
