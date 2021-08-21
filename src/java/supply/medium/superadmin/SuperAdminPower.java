/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.superadmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Intel
 */
public class SuperAdminPower extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controller</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
        PrintWriter out = response.getWriter();
        try {
            int i;
             //out.println("typ  "+request.getParameter("typ"));
            if (request.getParameter("typ").trim().equals("admn_ad_nw_usr")) {
                SuperAdminQuery db_qrs = new SuperAdminQuery();
                i = db_qrs.insert_update_delete("insert into wk_user(email,password,status) values('" + request.getParameter("eml") + "','" + request.getParameter("pswrd") + "',0)");
                db_qrs.close();
                response.sendRedirect("home.jsp");
            }
            if (request.getParameter("typ").trim().equals("admn_updt_usr")) {
                //out.print("<br>usr_eml :="+request.getParameter("usr_eml"));
                SuperAdminQuery db_qrs = new SuperAdminQuery();
                String qry="update wk_user set email='" + request.getParameter("usr_eml") + "',password='" + request.getParameter("usr_pswrd") + "',status=" + request.getParameter("usr_sts") + " where wk_user_id=" + request.getParameter("id") + "";
                i = db_qrs.insert_update_delete(qry);
                db_qrs.close();
            }
            if (request.getParameter("typ").trim().equals("admn_dlt_usr")) {
                SuperAdminQuery db_qrs = new SuperAdminQuery();
                i = db_qrs.insert_update_delete("delete from wk_user where wk_user_id=" + request.getParameter("id") + " and status !=2");
                db_qrs.close();
            }
            if (request.getParameter("typ").equals("cmpny_mr_dtl")) {

                //out.print("path"+request.getServletContext().getRealPath("/")+"/webapps/CropData/"+request.getParameter("nm")+"-"+request.getParameter("nmbr"));
                ///String directory = request.getServletContext().getRealPath("/") + "/webapps/CropData/" + request.getParameter("nm") + "-" + request.getParameter("nmbr");
                //long size = FileUtils.sizeOfDirectory(new File(directory));
                // System.out.println("The size of directory " + directory + " is " + size + " bytes");
                //out.print(" <span><b>Disk Quote:</b> " + size + " </span>");
                
                int usr = 0,rfq_inside=0,qte__inside=0,po_inside=0,inv__inside=0,rfq_outside=0,qte__outside=0,po_outside=0,inv__outside=0,rfq_inside_items=0,qte__inside_items=0,po_inside_items=0,inv__inside_items=0,rfq_outside_items=0,qte__outside_items=0,po_outside_items=0,inv__outside_items=0,no_of_payments=0;
                double qte__inside_grand=0,po_inside_grand=0,inv__inside_grand=0,qte__outside_grand=0,po_outside_grand=0,inv__outside_grand=0,grand_total_amount=0; 
                DecimalFormat df = new DecimalFormat("#.##");
                SuperAdminQuery db_qrs = new SuperAdminQuery();
                ResultSet rs = db_qrs.select("select count(*) as user_count from user_master up where company_key='" + request.getParameter("nmbr") + "'");
                if (rs.next()) {
                    usr = rs.getInt("user_count");
                }
                db_qrs.close();
                out.print(" <span><b>Users:</b> " + usr + " </span>");
                usr = 0;
                ResultSet rs2 = db_qrs.select("select count(*) as group_count from group_master up where company_key='" + request.getParameter("nmbr") + "'");
                if (rs2.next()) {
                    usr = rs2.getInt("group_count");
                }
                db_qrs.close();
                out.print(" <span><b>Groups:</b> " + usr + " </span>");
                usr = 0;
                ResultSet rs3 = db_qrs.select("select count(*) as dept_count from department_master up where company_key='" + request.getParameter("nmbr") + "'");
                if (rs3.next()) {
                    usr = rs3.getInt("dept_count");
                }
                db_qrs.close();
                ResultSet rs4 = db_qrs.select("SELECT count(*) from trans_rfq_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from!='null');");
                if (rs4.next()) {
                    rfq_inside= rs4.getInt(1);
                }
                db_qrs.close();
                ResultSet rs5 = db_qrs.select("SELECT count(*) from trans_rfq_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from='null');");
                if (rs5.next()) {
                    rfq_outside= rs5.getInt(1);
                }
                db_qrs.close();
                ResultSet rs6 = db_qrs.select("SELECT count(*) from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from!='null');");
                if (rs6.next()) {
                    qte__inside= rs6.getInt(1);
                }
                db_qrs.close();
                ResultSet rs7 = db_qrs.select("SELECT count(*) from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from='null');");
                if (rs7.next()) {
                    qte__outside= rs7.getInt(1);
                }
                db_qrs.close();
                ResultSet rs8 = db_qrs.select("SELECT count(*) from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from!='null');");
                if (rs8.next()) {
                    inv__inside= rs8.getInt(1);
                }
                db_qrs.close();
                ResultSet rs9 = db_qrs.select("SELECT count(*) from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from='null');");
                if (rs9.next()) {
                    inv__outside= rs9.getInt(1);
                }
                db_qrs.close();
                ResultSet rs10 = db_qrs.select("SELECT count(*) from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from!='null');");
                if (rs10.next()) {
                    po_inside= rs10.getInt(1);
                }
                db_qrs.close();
                ResultSet rs11 = db_qrs.select("SELECT count(*) from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from='null');");
                if (rs11.next()) {
                    po_outside= rs11.getInt(1);
                }
                db_qrs.close();
                ResultSet rs12 = db_qrs.select("SELECT count(*)  from transaction_item_master where trans_key in (SELECT trans_key from trans_rfq_master where(company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from!='null'));");
                if (rs12.next()) {
                    rfq_inside_items= rs12.getInt(1);
                }
                db_qrs.close();
                ResultSet rs13 = db_qrs.select("SELECT count(*)  from transaction_item_master where trans_key in (SELECT trans_key from trans_rfq_master where(company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from='null'));");
                if (rs13.next()) {
                    rfq_outside_items= rs13.getInt(1);
                }
                db_qrs.close();
                ResultSet rs14 = db_qrs.select("SELECT count(*)  from transaction_item_master where trans_key in (SELECT trans_key from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from!='null'));");
                if (rs14.next()) {
                    qte__inside_items= rs14.getInt(1);
                }
                db_qrs.close();
                ResultSet rs15 = db_qrs.select("SELECT count(*)  from transaction_item_master where trans_key in (SELECT trans_key from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from='null'));");
                if (rs15.next()) {
                    qte__outside_items= rs15.getInt(1);
                }
                db_qrs.close();
                ResultSet rs16 = db_qrs.select("SELECT count(*)  from transaction_item_master where trans_key in (SELECT trans_key from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from!='null'));");
                if (rs16.next()) {
                    qte__inside_items= rs16.getInt(1);
                }
                ResultSet rs17 = db_qrs.select("SELECT count(*)  from transaction_item_master where trans_key in (SELECT trans_key from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from='null'));");
                if (rs17.next()) {
                    qte__outside_items= rs17.getInt(1);
                }
                db_qrs.close();
                ResultSet rs18 = db_qrs.select("SELECT count(*)  from transaction_item_master where trans_key in (SELECT trans_key from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from!='null'));");
                if (rs18.next()) {
                    po_inside_items= rs18.getInt(1);
                }
                db_qrs.close();
                ResultSet rs19 = db_qrs.select("SELECT count(*)  from transaction_item_master where trans_key in (SELECT trans_key from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from='null'));");
                if (rs19.next()) {
                    po_outside_items= rs19.getInt(1);
                }
                db_qrs.close();
                ResultSet rs20 = db_qrs.select("SELECT count(*)  from transaction_item_master where trans_key in (SELECT trans_key from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from!='null'));");
                if (rs20.next()) {
                    inv__inside_items= rs20.getInt(1);
                }
                ResultSet rs21 = db_qrs.select("SELECT count(*)  from transaction_item_master where trans_key in (SELECT trans_key from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from='null'));");
                if (rs21.next()) {
                    inv__outside_items= rs21.getInt(1);
                }
                db_qrs.close();
                ResultSet rs24 = db_qrs.select("SELECT sum(inv_total_billing_amount) from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from!='null');");
                if (rs24.next()) {
                    qte__inside_grand= rs24.getInt(1);
                }
                db_qrs.close();
                ResultSet rs25 = db_qrs.select("SELECT sum(inv_total_billing_amount) from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from='null');");
                if (rs25.next()) {
                    qte__outside_grand= rs25.getInt(1);
                }
                db_qrs.close();
                ResultSet rs26 = db_qrs.select("SELECT sum(inv_total_billing_amount) from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from!='null');");
                if (rs26.next()) {
                    po_inside_grand= rs26.getInt(1);
                }
                db_qrs.close();
                ResultSet rs27 = db_qrs.select("SELECT sum(inv_total_billing_amount) from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from='null');");
                if (rs27.next()) {
                    po_outside_grand= rs27.getInt(1);
                }
                db_qrs.close();
                ResultSet rs28 = db_qrs.select("SELECT sum(inv_total_billing_amount) from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from!='null');");
                if (rs28.next()) {
                    inv__inside_grand= rs28.getInt(1);
                }
                db_qrs.close();
                ResultSet rs29 = db_qrs.select("SELECT sum(inv_total_billing_amount) from transaction_master where (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from='null');");
                if (rs29.next()) {
                    inv__outside_grand= rs29.getInt(1);
                }
                db_qrs.close();
                ResultSet rs30 = db_qrs.select("SELECT count(inv_total_billing_amount) from transaction_master where ((company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from='null') and (is_inv_paid='yes'));");
                if (rs30.next()) {
                    no_of_payments= rs30.getInt(1);
                }
                db_qrs.close();
                ResultSet rs31 = db_qrs.select("SELECT sum(inv_total_billing_amount) from transaction_master where ((company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null') or (company_key_to=" + request.getParameter("nmbr") + " and company_key_from='null') and (is_inv_paid='yes'));");
                if (rs31.next()) {
                    grand_total_amount= rs31.getInt(1);
                }
                db_qrs.close();
                out.print(" <span><b>Dept: </b> " + usr + " </span>");
                
                out.print("<table class='admin_companies_trans'>\n"
                        + "  <tr> \n"
                        + "<td style='width:15%;text-align: center;'><b>Type</b></td>\n"
                        + "<td style='width:45%;text-align: center;border-left:black 1px solid;border-right:black 1px solid;'><b>Transactions</b></td>\n"
                        + "<td style='width:40%;text-align: center;'><b>Total</b></td>\n"
                        + "  </tr>\n"
                        + "  <tr>\n"
                        + "<td style='text-align: center;'>\n"
                        + " <b> \n"
                        + "  RFQ\n"
                        + " </b>\n"
                        + "</td>\n"
                        + "<td style='border-left:black 1px solid;border-right:black 1px solid;padding:0px;'>\n"
                        + " <table style='width:100%;'>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='width:50%;border-right:black 1px solid;'>Inside( no of items )</td>\n"
                        + "<td style='width:50%'>Outside( no of items )</td>\n"
                        + "  </tr>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>"+rfq_inside +"("+rfq_inside_items +")</td>\n"
                        + "<td style='border-top:black 1px solid;'>"+rfq_outside +"("+rfq_outside_items +")</td>\n"
                        + "  </tr>\n"
                        + " </table>\n"
                        + "</td>\n"
                        + "<td style='padding:0px;'>\n"
                        + " <table style='width:100%;'>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='width:50%;border-right:black 1px solid;'>Transactions</td>\n"
                        + "<td style='width:50%'>Items</td>\n"
                        + "  </tr>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>"+(rfq_inside+rfq_outside)+"</td>\n"
                        + "<td style='border-top:black 1px solid;'>"+(rfq_inside_items+rfq_outside_items)+"</td>\n"
                        + "  </tr>\n"
                        + " </table> \n"
                        + "</td>\n"
                        + "  </tr>\n"
                        + "  <tr>\n"
                        + "<td  style='text-align: center;'>\n"
                        + " <b>\n"
                        + "  QTE  \n"
                        + " </b>\n"
                        + "</td>\n"
                        + "<td style='border-left:black 1px solid;border-right:black 1px solid;padding:0px;'>\n"
                        + " <table style='width:100%;'>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='width:50%;border-right:black 1px solid;'>Inside( no of items )</td>\n"
                        + "<td style='width:50%'>Outside( no of items )</td>\n"
                        + "  </tr>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>"+qte__inside +"("+qte__inside_items +")</td>\n"
                        + "<td style='border-top:black 1px solid;'>"+qte__outside +"("+qte__outside_items +")</td>\n"
                        + "  </tr>\n"
                        + " </table>\n"
                        + "</td>\n"
                        + "<td style='padding:0px;'>\n"
                        + " <table style='width:100%;'>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='width:50%;border-right:black 1px solid;'>Inside Grand Total</td>\n"
                        + "<td style='width:50%'>Outside Grand Total</td>\n"
                        + "  </tr>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>"+df.format(qte__inside_grand) +"</td>\n"
                        + "<td style='border-top:black 1px solid;'>"+df.format(qte__outside_grand) +"</td>\n"
                        + "  </tr>\n"
                        + " </table> \n"
                        + "</td>\n"
                        + "  </tr>\n"
                        + "  <tr>\n"
                        + "<td  style='text-align: center;'>\n"
                        + " <b>\n"
                        + "  PO \n"
                        + " </b>\n"
                        + "</td>\n"
                        + "<td style='border-left:black 1px solid;border-right:black 1px solid;padding:0px;'>\n"
                        + " <table style='width:100%;'>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='width:50%;border-right:black 1px solid;'>Inside( no of items )</td>\n"
                        + "<td style='width:50%'>Outside( no of items )</td>\n"
                        + "  </tr>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>"+po_inside +"("+po_inside_items +")</td>\n"
                        + "<td style='border-top:black 1px solid;'>"+po_outside +"("+po_outside_items +")</td>\n"
                        + "  </tr>\n"
                        + " </table>\n"
                        + "</td>\n"
                        + "<td style='padding:0px;'>\n"
                        + " <table style='width:100%;'>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='width:50%;border-right:black 1px solid;'>Inside Grand Total</td>\n"
                        + "<td style='width:50%'>Outside Grand Total</td>\n"
                        + "  </tr>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>"+df.format(po_inside_grand) +"</td>\n"
                        + "<td style='border-top:black 1px solid;'>"+df.format(po_outside_grand) +"</td>\n"
                        + "  </tr>\n"
                        + " </table> \n"
                        + "</td>\n"
                        + "  </tr>\n"
                        + "  <tr>\n"
                        + "<td  style='text-align: center;'>\n"
                        + " <b>\n"
                        + "  INV\n"
                        + " </b>\n"
                        + "</td>\n"
                        + "<td style='border-left:black 1px solid;border-right:black 1px solid;padding:0px;'>\n"
                        + " <table style='width:100%;'>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='width:50%;border-right:black 1px solid;'>Inside( no of items )</td>\n"
                        + "<td style='width:50%'>Outside( no of items )</td>\n"
                        + "  </tr>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>"+inv__inside +"("+inv__inside_items +")</td>\n"
                        + "<td style='border-top:black 1px solid;'>"+inv__outside +"("+inv__outside_items +")</td>\n"
                        + "  </tr>\n"
                        + " </table>\n"
                        + "</td>\n"
                        + "<td style='padding:0px;'>\n"
                        + " <table style='width:100%;'>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='width:50%;border-right:black 1px solid;'>Inside Grand Total</td>\n"
                        + "<td style='width:50%'>Outside Grand Total</td>\n"
                        + "  </tr>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>"+df.format(inv__inside_grand) +"</td>\n"
                        + "<td style='border-top:black 1px solid;'>"+df.format(inv__outside_grand) +"</td>\n"
                        + "  </tr>\n"
                        + " </table> \n"
                        + "</td>\n"
                        + "  </tr>\n"
                        + "  <tr>\n"
                        + "<td  style='text-align: center;'>\n"
                        + " <b>\n"
                        + "  PAYMENT \n"
                        + " </b>\n"
                        + "</td>\n"
                        + "<td style='border-left:black 1px solid;border-right:black 1px solid;padding:0px;'>\n"
                        + " <table style='width:100%;'>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='width:100%;'>No. of Payments</td>\n"
                        + "  </tr>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>"+no_of_payments +"</td>\n"
                        + "  </tr>\n"
                        + " </table>\n"
                        + "</td>\n"
                        + "<td style='padding:0px;'>\n"
                        + " <table style='width:100%;'>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='width:100%;'>Grand Total Amount</td>\n"
                        + "  </tr>\n"
                        + "  <tr style='border: none;'>\n"
                        + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>"+df.format(grand_total_amount) +"</td>\n"
                        + "\n"
                        + "  </tr>\n"
                        + " </table> \n"
                        + "</td>\n"
                        + "  </tr>\n"
                        + " </table>"); 
            }
            if (request.getParameter("typ").equals("vldt_usr_eml")) {
                //out.print("val" + request.getParameter("val"));
                SuperAdminQuery db_qrs = new SuperAdminQuery();
                ResultSet rs = db_qrs.select("select wk_user_id from wk_user where email='" + request.getParameter("val") + "'");
                if (rs.next()) {
                    out.print("1");
                } else {
                    out.print("0");
                } 
                db_qrs.close();
            }
            if (request.getParameter("typ").equals("updt_cmpny_sts")) {
                //out.print("val" + request.getParameter("val"));
                SuperAdminQuery db_qrs = new SuperAdminQuery();
                db_qrs.insert_update_delete("update company_registration set company_status='"+request.getParameter("cmpny_sts")+"' where regn_key='" + request.getParameter("cmpny_key") + "'");
                db_qrs.close();                
            }
        } catch (Exception e) {
            System.out.print("error on check login credential" + e);
        }
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
