/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.home.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import supply.medium.home.bean.CompanyBean;
import supply.medium.home.database.CompanyMaster;
import supply.medium.utility.TestMemory;

/**
 *
 * @author Intel
 */
public class CompaniesNonVendorSearch extends HttpServlet {

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
            HttpSession session=request.getSession(true);
            String companyName=request.getParameter("companyName");
            String companyType=request.getParameter("companyType");
            String companyKey=session.getAttribute("companyKey").toString();
            ArrayList vendorList = CompanyMaster.showNonVendorSearch(companyName, companyType,companyKey);
            CompanyBean cb = null;
            for (int i = 0; i < vendorList.size(); i++) {
            cb = (CompanyBean) vendorList.get(i);
            if(companyType.equals("Supplier"))
            out.print("<div id='"+cb.getCompanyKey()+"' class='filter_comp' style='cursor:pointer;' onclick=\"javascript:document.getElementById('selectedVendorKey').value="+cb.getCompanyKey()+";showAnotherVendorDetails("+cb.getCompanyKey()+",'Supplier');document.getElementById('ven_search_result').style.display='none';$('#to_company').val('"+cb.getCompanyName()+"');document.getElementById('outside_supplier').checked=false ;$('#outsideSupplier').hide();$('#insideSupplier').show(); \">"+cb.getCompanyName()+"</div>");
            else if(companyType.equals("Buyer"))
            out.print("<div id='"+cb.getCompanyKey()+"' class='filter_comp' style='cursor:pointer;' onclick=\"javascript:document.getElementById('selectedVendorKey').value="+cb.getCompanyKey()+";showAnotherVendorDetails("+cb.getCompanyKey()+",'Buyer');document.getElementById('ven_search_result').style.display='none';$('#to_company').val('"+cb.getCompanyName()+"');document.getElementById('outside_supplier').checked=false ;$('#outsideBuyer').hide();$('#insideBuyer').show(); \">"+cb.getCompanyName()+"</div>");
            }
            TestMemory.test("footer start");
                System.gc();
                TestMemory.test("footer end");
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
