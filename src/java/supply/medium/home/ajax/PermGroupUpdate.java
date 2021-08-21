/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supply.medium.home.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supply.medium.home.database.GroupPermMaster;

/**
 *
 * @author VIC
 */
public class PermGroupUpdate extends HttpServlet {

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
        PrintWriter out = response.getWriter(); try { 
            /* TODO output your page here. You may use following sample code. */
            
            String groupKey=request.getParameter("groupKey");
            String a=request.getParameter("addusers");
            String b=request.getParameter("delusers");
            String c=request.getParameter("addbuyers");
            String d="yes";//request.getParameter("delbuyers");
            String e=request.getParameter("accessusermgmt");
            String f=request.getParameter("postanncemnt");
            String g="yes";//request.getParameter("ratevendor");
            String h=request.getParameter("creategroup");
            String i=request.getParameter("delgroup");
            if(a.equalsIgnoreCase("true"))
                a="yes";
            else
                a="no";
            if(b.equalsIgnoreCase("true"))
                b="yes";
            else
                b="no";
            if(c.equalsIgnoreCase("true"))
                c="yes";
            else
                c="no";
            if(d.equalsIgnoreCase("true"))
                d="yes";
            else
                d="no";
            if(e.equalsIgnoreCase("true"))
                e="yes";
            else
                e="no";
            if(f.equalsIgnoreCase("true"))
                f="yes";
            else
                f="no";
            if(g.equalsIgnoreCase("true"))
                g="yes";
            else
                g="no";
            if(h.equalsIgnoreCase("true"))
                h="yes";
            else
                h="no";
            if(i.equalsIgnoreCase("true"))
                i="yes";
            else
                i="no";
            GroupPermMaster.delete(groupKey);
            GroupPermMaster.insert(groupKey, a, b, c, d, e, f, g, h, i, "no", "no", "no", "no", "no");
            out.println("");
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
