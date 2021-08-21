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
import supply.medium.home.database.DepartmentPermMaster;

/**
 *
 * @author VIC
 */
public class PermDepartmentUpdate extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String deptKey=request.getParameter("deptKey");
            String a=request.getParameter("addfolder");
            String b=request.getParameter("delfolder");
            String c=request.getParameter("addfile");
            String d=request.getParameter("delfile");
            String e=request.getParameter("postAnno");
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
            DepartmentPermMaster.delete(deptKey);
            DepartmentPermMaster.insert(deptKey, a, b, c, d, e,"no");
            out.println("");
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
