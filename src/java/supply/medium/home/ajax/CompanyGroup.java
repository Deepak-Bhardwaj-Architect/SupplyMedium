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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supply.medium.home.bean.GroupBean;
import supply.medium.home.database.GroupMaster;
import supply.medium.home.database.GroupPermMaster;
import supply.medium.utility.TestMemory;

/**
 *
 * @author LenovoB560
 */
public class CompanyGroup extends HttpServlet {

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
            int result = 0;
            if (request.getParameter("eventType").endsWith("addGroup")) {
                String gkey=System.currentTimeMillis()+"";
                result = GroupMaster.insert(gkey,request.getParameter("companyKey"), request.getParameter("groupName"));
                result=GroupPermMaster.insert(gkey, "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "0", "0", "0", "0");
            } else if (request.getParameter("eventType").endsWith("updateGroup")) {
                result = GroupMaster.update(request.getParameter("groupKey"), request.getParameter("groupName"));
            } else if (request.getParameter("eventType").endsWith("deleteGroup")) {
                result = GroupMaster.delete(request.getParameter("groupKey"));
            } else if (request.getParameter("eventType").endsWith("showCompanyGroup")) {
                ArrayList countryList = GroupMaster.showCompanyGroup(request.getParameter("companyKey"));
                GroupBean scb = null;
                for (int i = 0; i < countryList.size(); i++) {
                    scb = (GroupBean) countryList.get(i);
                    out.print("<option value=" + scb.getGroupKey() + ">" + scb.getGroupName() + "</option>");
                }
            } else if (request.getParameter("eventType").endsWith("searchGroup")) {
                ArrayList countryList = GroupMaster.searchCompanyGroup(request.getParameter("companyKey"), request.getParameter("groupName"));
                GroupBean scb = null;
                for (int i = 0; i < countryList.size(); i++) {
                    scb = (GroupBean) countryList.get(i);
                    out.print("<option value=" + scb.getGroupKey() + ">" + scb.getGroupName() + "</option>");
                }
            }
            TestMemory.test("footer start");
                System.gc();
                TestMemory.test("footer end");
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
