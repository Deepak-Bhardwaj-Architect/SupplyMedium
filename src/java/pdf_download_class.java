
import com.darwinsys.spdf.MoveTo;
import com.darwinsys.spdf.PDF;
import com.darwinsys.spdf.Page;
import com.darwinsys.spdf.Text;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lokesh
 */
public class pdf_download_class {
    
    public void download(HttpServletResponse response)
    {
            
        Document document=new Document();
        try {
         PdfWriter.getInstance(document,response.getOutputStream());
         document.open();

            PdfPTable table=new PdfPTable(2);
            PdfPCell  cell = new PdfPCell (new Paragraph ("Name"));
            cell.setHorizontalAlignment (Element.ALIGN_CENTER);
            cell.setBorderWidth((float) 2.0);
            cell.setPadding (10.0f);
            table.addCell (cell);
            cell = new PdfPCell (new Paragraph ("Place"));
            cell.setHorizontalAlignment (Element.ALIGN_CENTER);
            cell.setBorderWidth((float) 2.0);
            cell.setPadding (10.0f);
            table.addCell (cell);
            table.addCell("NewsTrack");
            table.addCell("Delhi");
            table.addCell("RoseIndia");
            table.addCell("Delhi");
            document.add(table);
            document.close();
        } 
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        } 
    }
    
}
