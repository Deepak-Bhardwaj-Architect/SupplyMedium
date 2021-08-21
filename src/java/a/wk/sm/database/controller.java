/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.wk.sm.database;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author lokesh
 */
public class controller extends HttpServlet {

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
        //out.print("cnfrm_pswrd"+request.getParameter("cnfrm_pswrd")+"  eml"+"cnfrm_pswrd"+request.getParameter("eml")+" pswrd"+"cnfrm_pswrd"+request.getParameter("pswrd"));
        try {
            int i;
            if (request.getParameter("typ").trim().equals("admn_ad_nw_usr")) {
                db_queries db_qrs = new db_queries();
                i = db_qrs.insert_update_delete("insert into wk_user(email,password) values('" + request.getParameter("eml") + "','" + request.getParameter("pswrd") + "')");
                response.sendRedirect("/SupplyMedium/SM-admin/home.jsp");
            }
            if (request.getParameter("typ").trim().equals("admn_updt_usr")) {
                //out.print("<br>usr_eml :="+request.getParameter("usr_eml"));
                db_queries db_qrs = new db_queries();
                i = db_qrs.insert_update_delete("update wk_user set email='" + request.getParameter("usr_eml") + "',password='" + request.getParameter("usr_pswrd") + "',status=" + request.getParameter("usr_sts") + " where wk_user_id=" + request.getParameter("id") + "");
            }
            if (request.getParameter("typ").trim().equals("admn_dlt_usr")) {
                db_queries db_qrs = new db_queries();
                i = db_qrs.insert_update_delete("delete from wk_user where wk_user_id=" + request.getParameter("id") + " and status !=2");
            }
            if (request.getParameter("typ").equals("cmpny_mr_dtl")) {

                //out.print("path"+request.getServletContext().getRealPath("/")+"/webapps/CropData/"+request.getParameter("nm")+"-"+request.getParameter("nmbr"));
                String directory = request.getServletContext().getRealPath("/") + "/webapps/CropData/" + request.getParameter("nm") + "-" + request.getParameter("nmbr");
                long size = FileUtils.sizeOfDirectory(new File(directory));
                // System.out.println("The size of directory " + directory + " is " + size + " bytes");
                out.print(" <span><b>Disk Quote:</b> " + size + " </span>");

                int usr = 0;
                db_queries db_qrs = new db_queries();
                ResultSet rs = db_qrs.select("select count(*) as user_count from user_profile up where regn_key='" + request.getParameter("nmbr") + "'");
                if (rs.next()) {
                    usr = rs.getInt("user_count");
                }
                out.print(" <span><b>Users:</b> " + usr + " </span>");
                usr = 0;
                ResultSet rs2 = db_qrs.select("select count(*) as group_count from user_group_mapping up where user_rel_key='" + request.getParameter("nmbr") + "'");
                if (rs2.next()) {
                    usr = rs2.getInt("group_count");
                }
                out.print(" <span><b>Groups:</b> " + usr + " </span>");
                usr = 0;
                ResultSet rs3 = db_qrs.select("select count(*) as dept_count from user_dept_mapping up where user_rel_key='" + request.getParameter("nmbr") + "'");
                if (rs3.next()) {
                    usr = rs3.getInt("dept_count");
                }
                out.print(" <span><b>Dept: </b> " + usr + " </span>");
            }
            if (request.getParameter("typ").equals("vldt_usr_eml")) {
                //out.print("val" + request.getParameter("val"));
                db_queries db_qrs = new db_queries();
                ResultSet rs = db_qrs.select("select wk_user_id from wk_user where email='"+request.getParameter("val")+"'");
                if (rs.next()) {
                    out.print("1");
                }
                else
                {
                    out.print("0");
                }    
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
