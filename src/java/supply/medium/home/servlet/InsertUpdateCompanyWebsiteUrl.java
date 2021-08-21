/*
 * 
 * 
 * 
 */

package supply.medium.home.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import supply.medium.home.database.CompanyWebsiteMaster;
import supply.medium.utility.MemoryTest;

/**
 *
 * @author Intel
 */
public class InsertUpdateCompanyWebsiteUrl extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        String companyKey=session.getAttribute("companyKey").toString();
        String urlName=request.getParameter("urlName");
        if(urlName.equals("") || urlName.equals(null) || urlName==null)
            urlName="Supply Medium";
        String websiteUrl=request.getParameter("websiteUrl");
        if(websiteUrl.equals("") || websiteUrl.equals(null) || websiteUrl==null)
            websiteUrl="http://SupplyMedium.com";
        websiteUrl=websiteUrl.toLowerCase();
        if(!websiteUrl.startsWith("http://") && !websiteUrl.startsWith("https://"))
            websiteUrl="http://"+websiteUrl;
        PrintWriter out = response.getWriter(); try { 
            ArrayList al=new ArrayList(); 
            al=CompanyWebsiteMaster.showWebsiteUrl(companyKey);
            if(al.size()==0)
            {
                int result=CompanyWebsiteMaster.insert(companyKey,urlName,websiteUrl);
            }
            else
            {
                int result2=CompanyWebsiteMaster.update(companyKey,urlName,websiteUrl);
            }
            MemoryTest.test("footer start");
                System.gc();
                MemoryTest.test("footer end");
            response.sendRedirect("adminCompanyWebsite.jsp");
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
