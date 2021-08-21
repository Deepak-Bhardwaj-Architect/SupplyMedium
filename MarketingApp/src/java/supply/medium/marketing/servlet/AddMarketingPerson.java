/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.marketing.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supply.medium.marketing.database.MarketingAssociateMaster;
import supply.medium.marketing.database.MarketingPersonMaster;

/**
 *
 * @author Intel8GB
 */
public class AddMarketingPerson extends HttpServlet {

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
            String txtName=request.getParameter("txtName");
            String txtAddress=request.getParameter("txtAddress");
            String txtBank=request.getParameter("txtBank");
            String txtContact=request.getParameter("txtContact");
            String txtEmail=request.getParameter("txtEmail");
            String txtPassword=request.getParameter("txtPassword");
            //String txtStatus=request.getParameter("txtStatus");
            
            String marketing_associate_key=""+System.currentTimeMillis();
            String marketing_person_key=request.getParameter("marketing_person_key");
            
            MarketingPersonMaster.insert(marketing_associate_key,txtName, txtAddress, txtBank, txtContact, txtEmail, txtPassword);
            MarketingAssociateMaster.insert(marketing_associate_key, marketing_person_key);
            
            response.sendRedirect("listMarketingPerson.jsp");
            
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
