/*
 *
 *
 *
 */
package supply.medium.home.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supply.medium.home.bean.CompanyBean;
import supply.medium.home.database.CompanyMaster;
import supply.medium.utility.MemoryTest;

/**
 *
 * @author Intel
 */
//@WebServlet(name = "FetchCompanyByCountry", urlPatterns = {"/FetchCompanyByCountry"})
public class FetchCompanyByCountry extends HttpServlet {

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
            String countryKey = request.getParameter("countryCode");
            ArrayList al = CompanyMaster.showAllCompaniesByCountryKey(countryKey);
            CompanyBean scb = null;
            String msg="";
//            msg+="<OPTION>--Select--</OPTION>";
            for (int i = 0; i < al.size(); i++) {
                scb = (CompanyBean) al.get(i);
                msg+="<option value='" + scb.getCompanyKey() + "'>" + scb.getCompanyName() + "</option>";
            }
            out.println(msg);
            MemoryTest.test("footer start");
                System.gc();
                MemoryTest.test("footer end");

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
