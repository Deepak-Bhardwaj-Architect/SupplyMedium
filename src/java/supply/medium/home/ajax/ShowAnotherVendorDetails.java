/*
 * 
 * 
 * 
 */
package supply.medium.home.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supply.medium.home.bean.CompanyDetailAddressBean;
import supply.medium.home.database.CompanyDetailAddressMaster;
import supply.medium.home.database.CountryMaster;
import supply.medium.home.database.StateMaster;
import supply.medium.utility.MemoryTest;

/**
 *
 * @author LenovoB560
 */
public class ShowAnotherVendorDetails extends HttpServlet {

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

            CompanyDetailAddressBean cdab = (CompanyDetailAddressBean) (CompanyDetailAddressMaster.showCompanyDetailAddress(request.getParameter("companyKey"))).get(0);
            String result = "";
            result += CountryMaster.showCountryFromKey(cdab.getCountry()) + "@#@";
            result += StateMaster.showStateFromKey(cdab.getState()) + "@#@";
            result += cdab.getCity() + "@#@";
            result += cdab.getAddress() + "@#@";
            result += cdab.getZipcode();
            out.println(result);
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
