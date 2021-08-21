
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
public class quote_pdf_download extends HttpServlet {

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
        response.setHeader("Content-disposition", "inline; filename='SM-Quote-" + request.getParameter("trans_id") + ".pdf'");
        Document document = new Document();
        Font font = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
        Font font2 = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);
        Font font3 = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
        Font font4 = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
        Font font5 = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
        Font font6 = new Font(Font.TIMES_ROMAN, 8, Font.NORMAL);
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            Paragraph paragraph = new Paragraph("Quote", font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setSpacingAfter(25);
            document.add(paragraph);

            if (request.getParameter("typ") != null) {
                String to_query = "", from_query = "", to = "", from = "";
                to_query = "select * from mailing_address where regn_key=(select to_regn_key from transaction where trans_id=" + request.getParameter("trans_id") + " LIMIT 1)";
                from_query = "select * from mailing_address where regn_key=(select from_regn_key from transaction where trans_id=" + request.getParameter("trans_id") + " LIMIT 1)";
                if (request.getParameter("typ").equals("sent")) {
                    to = request.getParameter("from");
                    from = request.getParameter("to");
                } else if (request.getParameter("typ").equals("rec")) {
            //to_query="select * from mailing_address where regn_key=(select from_regn_key from transaction where trans_id=" + request.getParameter("trans_id") + " LIMIT 1)";    
                    //from_query="select * from mailing_address where regn_key=(select to_regn_key from transaction where trans_id=" + request.getParameter("trans_id") + " LIMIT 1)";    
                    to = request.getParameter("to");
                    from = request.getParameter("from");
                }
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
                    rs = statement.executeQuery(to_query);
                    if (rs.next()) {
                        cntry = rs.getString(8);
                        st = rs.getString(6);
                        cty = rs.getString(5);
                        adrs = rs.getString(4);
                        zpcd = rs.getString(7);
                    }
                    else
                {
                   cntry = request.getParameter("country");
                    st = request.getParameter("state");
                    cty = request.getParameter("city");
                    adrs = request.getParameter("address");
                    zpcd = request.getParameter("zipcode"); 
                }

//            
                    cell = new PdfPCell(new Paragraph("Name : " + to + " \n"
                            + "Country : " + cntry + "\n"
                            + "State/Province : " + st + "\n"
                            + "City : " + cty + "\n"
                            + "Address : " + adrs + "\n"
                            + "Zip Code : " + zpcd + "\n", font3));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorderWidth((float) 2.0);
                    cell.setPadding(10.0f);
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph("Trans Id : " + request.getParameter("trans_id") + "\n"
                            + "Trans Date : " + request.getParameter("date") + "\n", font3));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorderWidth((float) 2.0);
                    cell.setPadding(10.0f);
                    table.addCell(cell);
                    table.setSpacingAfter(25);
                    document.add(table);
                //document.add(image);
                    //image.scaleAbsolute(500.0f, 500.0f);
                    //image.setBorder(1);  
                } catch (SQLException e) {
                    //return e+"";
                    System.out.print("error 1" + e);
                } finally {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.print("error 2" + ex);
                        // Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
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
                int i = 1;
                try {
                    con2 = DBConnect.instance().getConnection();
                    statement2 = con2.createStatement();
                    rs2 = statement2.executeQuery("select * from quote_item where trans_id=" + request.getParameter("trans_id") + "");
                    while (rs2.next()) {
                        cell = new PdfPCell(new Paragraph(i + "", font4));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorderWidth((float) 1.0);
                        cell.setPadding(5.0f);
                        table.addCell(cell);

                        cell = new PdfPCell(new Paragraph(rs2.getString(4) + "", font4));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorderWidth((float) 1.0);
                        cell.setPadding(5.0f);
                        table.addCell(cell);

                        cell = new PdfPCell(new Paragraph(rs2.getString(5) + "", font4));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorderWidth((float) 1.0);
                        cell.setPadding(5.0f);
                        table.addCell(cell);

                        cell = new PdfPCell(new Paragraph(rs2.getString(6) + "", font4));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorderWidth((float) 1.0);
                        cell.setPadding(5.0f);
                        table.addCell(cell);

                        cell = new PdfPCell(new Paragraph(rs2.getString(7) + "", font4));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorderWidth((float) 1.0);
                        cell.setPadding(5.0f);
                        table.addCell(cell);

                        cell = new PdfPCell(new Paragraph(rs2.getString(9) + "", font4));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorderWidth((float) 1.0);
                        cell.setPadding(5.0f);
                        table.addCell(cell);

                        cell = new PdfPCell(new Paragraph(Float.parseFloat(rs2.getString(11)+"") + "", font4));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
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
                
                Statement statement4 = null;
                Connection con4 = null;
                ResultSet rs4 = null;
                try {
                    con4 = DBConnect.instance().getConnection();
                    statement4 = con4.createStatement();
                    rs4 = statement4.executeQuery("select total_list_price,tax_percentage,total_price from quote where trans_id=" + request.getParameter("trans_id") + "");
                    if (rs4.next()) {
                        cell = new PdfPCell(new Paragraph("", font2));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setColspan(5);
                cell.setBorderWidth((float) 1.0);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Total", font4));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(5.0f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(rs4.getString(1)+"", font4));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(5.0f);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("", font2));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setColspan(5);
                cell.setBorderWidth((float) 1.0);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Tax("+Float.parseFloat(rs4.getString(2))+"%)", font4));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(2.0f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(Float.parseFloat(rs4.getString(1))*Float.parseFloat(rs4.getString(2))/100+"", font4));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(5.0f);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("", font2));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setColspan(5);
                cell.setBorderWidth((float) 1.0);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Grand Total", font4));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(5.0f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(rs4.getString(3)+"", font4));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(5.0f);
                table.addCell(cell);
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
                Statement statement3 = null;
                Connection con3 = null;
                ResultSet rs3 = null;
                try {
                    con3 = DBConnect.instance().getConnection();
                    statement3 = con3.createStatement();
                    rs3 = statement3.executeQuery(from_query);
                    if (rs3.next()) {
                        cntry = rs3.getString(8);
                        st = rs3.getString(6);
                        cty = rs3.getString(5);
                        adrs = rs3.getString(4);
                        zpcd = rs3.getString(7);
                    }
                    table = new PdfPTable(2);
                    cell = new PdfPCell(new Paragraph("From", font2));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setColspan(2);
                    cell.setBorderWidth((float) 2.0);
                    cell.setPadding(10.0f);
                    table.addCell(cell);
//            
                    cell = new PdfPCell(new Paragraph("Name : " + from + " \n"
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
                    table.setSpacingAfter(50);
                    document.add(table);
                } catch (SQLException e) {
                    //return e+"";
                    System.out.print("error 3" + e);
                } finally {
                    try {
                        con3.close();
                    } catch (SQLException ex) {
                        System.out.print("error 4" + ex);
                    }
                }

                Paragraph Terms_conditions = new Paragraph("Terms & Conditions", font5);
                Terms_conditions.setAlignment(Element.ALIGN_LEFT);
                Terms_conditions.setSpacingAfter(5);
                document.add(Terms_conditions);

                try {
                    con2 = DBConnect.instance().getConnection();
                    statement2 = con2.createStatement();
                    rs2 = statement2.executeQuery("select tc from trans_tc where regn_rel_key=(select to_regn_key from transaction where trans_id=" + request.getParameter("trans_id") + " LIMIT 1)");
                    if (rs2.next()) {
                        Paragraph Terms_conditions_data = new Paragraph(rs2.getString(1), font6);
                        Terms_conditions_data.setAlignment(Element.ALIGN_LEFT);
                        document.add(Terms_conditions_data);
                    }
                } catch (SQLException e) {
                    //return e+"";
                    System.out.print("error 1" + e);
                } finally {
                    try {
                        con2.close();
                    } catch (SQLException ex) {
                        System.out.print("error 2" + ex);
                        // Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
            document.close();
        } catch (Exception ex) {
            System.out.print("error 5" + ex);
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
