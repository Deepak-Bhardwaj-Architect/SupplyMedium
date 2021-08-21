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
                
                int usr = 0,rfq_inside=0,qte_inside=0,po_inside=0,inv_inside=0,rfq_outside=0,qte_outside=0,po_outside=0,inv_outside=0,no_of_payments=0;
                double qte_inside_grand=0,po_inside_grand=0,inv_inside_grand=0,qte_outside_grand=0,po_outside_grand=0,inv_outside_grand=0,grand_total_amount=0;
                DecimalFormat df = new DecimalFormat("#.##");
                SuperAdminQuery db_qrs = new SuperAdminQuery();

                ResultSet user_count = db_qrs.select("select count(*) as user_count from user_master up where company_key='" + request.getParameter("nmbr") + "'");
                if (user_count.next()) {
                    usr = user_count.getInt("user_count");
                }
                db_qrs.close();
                out.print(" <span><b>Users:</b> " + usr + " </span>");

                usr = 0;
                ResultSet group_count = db_qrs.select("select count(*) as group_count from group_master up where company_key='" + request.getParameter("nmbr") + "'");
                if (group_count.next()) {
                    usr = group_count.getInt("group_count");
                }
                db_qrs.close();
                out.print(" <span><b>Groups:</b> " + usr + " </span>");

                usr = 0;
                ResultSet dept_count = db_qrs.select("select count(*) as dept_count from department_master up where company_key='" + request.getParameter("nmbr") + "'");
                if (dept_count.next()) {
                    usr = dept_count.getInt("dept_count");
                }
                db_qrs.close();
                out.print(" <span><b>Dept: </b> " + usr + " </span>");






                
                ResultSet rfq_insides = db_qrs.select("SELECT count(*) from trans_rfq_master where is_outside='no' and (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null');");
                if (rfq_insides.next()) {
                    rfq_inside= rfq_insides.getInt(1);
                }
                db_qrs.close();

                ResultSet rfq_outsides = db_qrs.select("SELECT count(*) from trans_rfq_master where is_outside='yes' and (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null');");
                if (rfq_outsides.next()) {
                    rfq_outside= rfq_outsides.getInt(1);
                }
                db_qrs.close();







                ResultSet qte_insides = db_qrs.select("SELECT count(*) from transaction_master where q_is_outside='no' and (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null');");
                if (qte_insides.next()) {
                    qte_inside= qte_insides.getInt(1);
                }
                db_qrs.close();

                ResultSet qte_outsides = db_qrs.select("SELECT count(*) from transaction_master where q_is_outside='yes' and (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null');");
                if (qte_outsides.next()) {
                    qte_outside= qte_outsides.getInt(1);
                }
                db_qrs.close();

                ResultSet rs24 = db_qrs.select("SELECT sum(q_total_billing_amount) from transaction_master where q_is_outside='no' and (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null');");
                if (rs24.next()) {
                    qte_inside_grand= rs24.getInt(1);
                }
                db_qrs.close();

                ResultSet rs25 = db_qrs.select("SELECT sum(q_total_billing_amount) from transaction_master where q_is_outside='yes' and (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null');");
                if (rs25.next()) {
                    qte_outside_grand= rs25.getInt(1);
                }
                db_qrs.close();







                ResultSet po_insides = db_qrs.select("SELECT count(*) from transaction_master where po_is_outside='no' and (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null');");
                if (po_insides.next()) {
                    po_inside= po_insides.getInt(1);
                }
                db_qrs.close();

                ResultSet po_outsides = db_qrs.select("SELECT count(*) from transaction_master where po_is_outside='yes' and (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null');");
                if (po_outsides.next()) {
                    po_outside= po_outsides.getInt(1);
                }
                db_qrs.close();

                ResultSet rs26 = db_qrs.select("SELECT sum(po_total_billing_amount) from transaction_master where po_is_outside='no' and (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null');");
                if (rs26.next()) {
                    po_inside_grand= rs26.getInt(1);
                }
                db_qrs.close();

                ResultSet rs27 = db_qrs.select("SELECT sum(po_total_billing_amount) from transaction_master where po_is_outside='yes' and (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null');");
                if (rs27.next()) {
                    po_outside_grand= rs27.getInt(1);
                }
                db_qrs.close();







                ResultSet inv_insides = db_qrs.select("SELECT count(*) from transaction_master where inv_is_outside='no' and (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null');");
                if (inv_insides.next()) {
                    inv_inside= inv_insides.getInt(1);
                }
                db_qrs.close();

                ResultSet inv_outsides = db_qrs.select("SELECT count(*) from transaction_master where inv_is_outside='yes' and (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null');");
                if (inv_outsides.next()) {
                    inv_outside= inv_outsides.getInt(1);
                }
                db_qrs.close();

                ResultSet rs28 = db_qrs.select("SELECT sum(inv_total_billing_amount) from transaction_master where inv_is_outside='no' and (company_key_from=" + request.getParameter("nmbr") + " and company_key_to!='null');");
                if (rs28.next()) {
                    inv_inside_grand= rs28.getInt(1);
                }
                db_qrs.close();

                ResultSet rs29 = db_qrs.select("SELECT sum(inv_total_billing_amount) from transaction_master where inv_is_outside='yes' and (company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null');");
                if (rs29.next()) {
                    inv_outside_grand= rs29.getInt(1);
                }
                db_qrs.close();







                ResultSet rs30 = db_qrs.select("SELECT count(inv_total_billing_amount) from transaction_master where company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null' and is_inv_paid='yes';");
                if (rs30.next()) {
                    no_of_payments= rs30.getInt(1);
                }
                db_qrs.close();

                ResultSet rs31 = db_qrs.select("SELECT sum(inv_total_billing_amount) from transaction_master where company_key_from=" + request.getParameter("nmbr") + " and company_key_to='null' and is_inv_paid='yes';");
                if (rs31.next()) {
                    grand_total_amount= rs31.getInt(1);
                }
                db_qrs.close();
                
out.print("<table class='admin_companies_trans'>\n"
    + "  <tr> \n"
    + "<td style='width:15%;text-align: center;'><b>Type</b></td>\n"
    + "<td style='width:45%;text-align: center;border-left:black 1px solid;border-right:black 1px solid;'><b>No. of Transactions</b></td>\n"
    + "<td style='width:40%;text-align: center;'><b>Total Amount of all Transactions</b></td>\n"
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
    + "<td style='width:50%;border-right:black 1px solid;'>Inside</td>\n"
    + "<td style='width:50%'>Outside</td>\n"
    + "  </tr>\n"
    + "  <tr style='border: none;'>\n"
    + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>"+rfq_inside +"</td>\n"
    + "<td style='border-top:black 1px solid;'>"+rfq_outside +"</td>\n"
    + "  </tr>\n"
    + " </table>\n"
    + "</td>\n"
    + "<td style='padding:0px;'>\n"
    + " <table style='width:100%;'>\n"
    + "  <tr style='border: none;'>\n"
    + "<td style='width:50%;border-right:black 1px solid;'>&nbsp;</td>\n"
    + "<td style='width:50%'>&nbsp;</td>\n"
    + "  </tr>\n"
    + "  <tr style='border: none;'>\n"
    + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>&nbsp;</td>\n"
    + "<td style='border-top:black 1px solid;'>&nbsp;</td>\n"
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
    + "<td style='width:50%;border-right:black 1px solid;'>Inside</td>\n"
    + "<td style='width:50%'>Outside</td>\n"
    + "  </tr>\n"
    + "  <tr style='border: none;'>\n"
    + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>"+qte_inside +"</td>\n"
    + "<td style='border-top:black 1px solid;'>"+qte_outside +"</td>\n"
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
    + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>"+df.format(qte_inside_grand) +"</td>\n"
    + "<td style='border-top:black 1px solid;'>"+df.format(qte_outside_grand) +"</td>\n"
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
    + "<td style='width:50%;border-right:black 1px solid;'>Inside</td>\n"
    + "<td style='width:50%'>Outside</td>\n"
    + "  </tr>\n"
    + "  <tr style='border: none;'>\n"
    + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>"+po_inside +"</td>\n"
    + "<td style='border-top:black 1px solid;'>"+po_outside +"</td>\n"
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
    + "<td style='width:50%;border-right:black 1px solid;'>Inside</td>\n"
    + "<td style='width:50%'>Outside</td>\n"
    + "  </tr>\n"
    + "  <tr style='border: none;'>\n"
    + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>"+inv_inside +"</td>\n"
    + "<td style='border-top:black 1px solid;'>"+inv_outside +"</td>\n"
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
    + "<td style='border-right:black 1px solid;border-top:black 1px solid;'>"+df.format(inv_inside_grand) +"</td>\n"
    + "<td style='border-top:black 1px solid;'>"+df.format(inv_outside_grand) +"</td>\n"
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
