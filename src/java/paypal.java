/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import core.login.SessionData;
import db.utils.DBConnect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lokesh
 */
public class paypal extends HttpServlet {

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
            HttpSession session = request.getSession();
            SessionData sessionObjp = (SessionData)session.getAttribute("UserSessionData");
            if(request.getParameter("result")!=null)
            {
            if(request.getParameter("result").startsWith(sessionObjp.pypl_scs))
            {
              if(request.getParameter("result").endsWith("Basic")||request.getParameter("result").endsWith("Plus")||request.getParameter("result").endsWith("Premium"))  
              {
              String plan="Basic"; 
              if(request.getParameter("result").endsWith("Basic"))
              {
                  plan="Basic";
              }
              else if(request.getParameter("result").endsWith("Plus"))
              {
                  plan="Plus";
              }
              else if(request.getParameter("result").endsWith("Premium"))
              {
                  plan="Premium";
              }
              Statement statement = null;
                Connection con = null;

                try {
                    con = DBConnect.instance().getConnection();
                    statement = con.createStatement();                    
                    statement.executeUpdate("update company_registration set pricing_option='" + plan + "' where regn_key='" + sessionObjp.companyRegnKeyStr_ + "'");
                    out.print("updated");
                } catch (Exception e) {
                    //return e+"";
                    System.out.print("error" + e);
                } finally {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
              }
              if(request.getParameter("result").contains("trns"))  
              {
                  String ids="0"; 
                  ids=request.getParameter("result").substring(request.getParameter("result").lastIndexOf("s")+1,request.getParameter("result").length());
                  out.print("id"+ids);
                  String str = "update invoice set status ='Payment Done' where invoice_id=" + ids + "";

                Statement stmt = null;
                //ResultSet rs = null;
                Connection con = null;
                try {
                    con = DBConnect.instance().getConnection();
                    stmt = con.createStatement();
                    stmt.executeUpdate(str);
                } catch (Exception ex) {
                    out.print("problem to clear notifications" + ex);
                } finally {
                    try {
                        
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }
            
              }    
              sessionObjp.pypl_rslt="Transaction completed";  
              response.sendRedirect("user_home.jsp");
            }
            else
            {
              sessionObjp.pypl_rslt="Transaction not completed";   
              response.sendRedirect("user_home.jsp");  
            }
            }
            else
            {
              sessionObjp.pypl_rslt="Transaction not completed";   
              response.sendRedirect("user_home.jsp");   
            }    
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
