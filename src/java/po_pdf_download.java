
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import db.utils.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nisha
 */
public class po_pdf_download extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline; filename='SM-PO-"+request.getParameter("trans_id")+".pdf'");
        Document document = new Document();
        Font font = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
        Font font2 = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);
        Font font3 = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
        Font font4 = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            //Image image = Image.getInstance("/SupplyMedium/Views/Utils/images/logo.png"); 

            Paragraph paragraph = new Paragraph("PO", font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setSpacingAfter(25);
            document.add(paragraph);

            PdfPTable table = new PdfPTable(2);
            PdfPCell cell = new PdfPCell(new Paragraph("To", font2));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            cell.setBorderWidth((float) 2.0);
            cell.setPadding(10.0f);
            table.addCell(cell);
//            cell = new PdfPCell(new Paragraph("From",font2));
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setBorderWidth((float) 2.0);
//            cell.setPadding(10.0f);
//            table.addCell(cell);
            Statement statement = null;
            Connection con = null;
            ResultSet rs = null;
            String nm = "", cntry = "", st = "", cty = "", adrs = "", zpcd = "";
            try {
                con = DBConnect.instance().getConnection();
                statement = con.createStatement();
                rs = statement.executeQuery("select * from mailing_address where regn_key=(select from_regn_key from transaction where trans_id="+request.getParameter("trans_id")+") or regn_key=(select to_regn_key from transaction where trans_id="+request.getParameter("trans_id")+")");
                if (rs.next()) {
                    cntry = rs.getString(8);
                    st = rs.getString(6);
                    cty = rs.getString(5);
                    adrs = rs.getString(4);
                    zpcd = rs.getString(7);
                }

//            
                cell = new PdfPCell(new Paragraph("Name : "+request.getParameter("to")+" \n"
                        + "Country : " + cntry + "\n"
                        + "State/Province : " + st + "\n"
                        + "City : " + cty + "\n"
                        + "Address : " + adrs + "\n"
                        + "Zip Code : " + zpcd + "\n", font3));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth((float) 2.0);
                cell.setPadding(10.0f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Trans Id : "+request.getParameter("trans_id")+"\n"
                        + "Trans Date : "+request.getParameter("date")+"\n", font3));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderWidth((float) 2.0);
                cell.setPadding(10.0f);
                table.addCell(cell);
                table.setSpacingAfter(25);
                document.add(table);
            //document.add(image);
                //image.scaleAbsolute(500.0f, 500.0f);
                //image.setBorder(1);  

                table = new PdfPTable(7);
                cell = new PdfPCell(new Paragraph("S.No", font3));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(5.0f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Item Description", font3));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(5.0f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Part Number", font3));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(5.0f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Quantity", font3));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(5.0f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Ship Date", font3));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(5.0f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Price per unit", font3));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(5.0f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Multiplier", font3));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(5.0f);
                table.addCell(cell);

                Statement statement2 = null;
                Connection con2 = null;
                ResultSet rs2 = null;
                int i=1;
                try {
                    con2 = DBConnect.instance().getConnection();
                    statement2 = con2.createStatement();
                    rs2 = statement2.executeQuery("select * from po_item where trans_id="+request.getParameter("trans_id")+"");
                    while (rs2.next()) {
                        cell = new PdfPCell(new Paragraph(i+"", font4));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorderWidth((float) 1.0);
                        cell.setPadding(5.0f);
                        table.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(rs2.getString(4)+"", font4));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorderWidth((float) 1.0);
                        cell.setPadding(5.0f);
                        table.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(rs2.getString(5)+"", font4));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorderWidth((float) 1.0);
                        cell.setPadding(5.0f);
                        table.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(rs2.getString(6)+"", font4));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorderWidth((float) 1.0);
                        cell.setPadding(5.0f);
                        table.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(rs2.getString(7)+"", font4));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorderWidth((float) 1.0);
                        cell.setPadding(5.0f);
                        table.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(rs2.getString(9)+"", font4));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorderWidth((float) 1.0);
                        cell.setPadding(5.0f);
                        table.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(rs2.getString(11)+"", font4));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorderWidth((float) 1.0);
                        cell.setPadding(5.0f);
                        table.addCell(cell);
                        
                        i++;
                    }

                } catch (SQLException e) {
                    //return e+"";
                    //System.out.print("error"+e);
                } finally {
                    try {
                        con2.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                table.setSpacingAfter(25);
                document.add(table);

                table = new PdfPTable(2);
                cell = new PdfPCell(new Paragraph("From", font2));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setColspan(2);
                cell.setBorderWidth((float) 2.0);
                cell.setPadding(10.0f);
                table.addCell(cell);
//            cell = new PdfPCell(new Paragraph("From",font2));
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setBorderWidth((float) 2.0);
//            cell.setPadding(10.0f);
//            table.addCell(cell);

                if (rs.next()) {
                    cntry = rs.getString(8);
                    st = rs.getString(6);
                    cty = rs.getString(5);
                    adrs = rs.getString(4);
                    zpcd = rs.getString(7);
                }
                cell = new PdfPCell(new Paragraph("Name : "+request.getParameter("from")+" \n"
                        + "Country : " + cntry + "\n"
                        + "State/Province : " + st + "\n"
                        + "City : " + cty + "\n"
                        + "Address : " + adrs + "\n"
                        + "Zip Code : " + zpcd + "\n", font3));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setColspan(2);
                cell.setBorderWidth((float) 2.0);
                cell.setPadding(10.0f);
                table.addCell(cell);                
                document.add(table);
            } catch (SQLException e) {
                    //return e+"";
                //System.out.print("error"+e);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            document.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
