/*
 * 
 * 
 * 
 */
package supply.medium.home.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import supply.medium.home.bean.CompanyDetailsForVrBean;
import supply.medium.home.bean.UserBean;
import supply.medium.home.database.CompanyMaster;
import supply.medium.home.database.InquiryMaster;
import supply.medium.home.database.NotificationMaster;
import supply.medium.home.database.UserMaster;
import supply.medium.home.database.VendorRegistrationMaster;
import supply.medium.utility.TestMemory;

/**
 *
 * @author Intel
 */
public class VrAction extends HttpServlet {

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
            String vrKey = request.getParameter("vrKey");
            String vrAction = request.getParameter("vrAction");
            
            VendorRegistrationMaster.vrAction(vrKey, vrAction);
            
            HttpSession session = request.getSession(true);
                String inquireType = "VR";
                String rfqKey = "0";
                String transactionKey = "0";
                String inquireFrom = session.getAttribute("companyKey").toString();
                String userKeyFrom = session.getAttribute("userKey").toString();
                String inquireTo = request.getParameter("toCompanyKey");
                String userKeyTo=UserMaster.showAdminKeyFromCompanyKey(inquireTo)+"";
                String inquireDetails = request.getParameter("inquireMessage");
                String othersPageType=request.getParameter("othersPageType");
                
                
            if (vrAction.equals("Enquired")) {                
                
                InquiryMaster.insert(inquireType, vrKey, rfqKey, transactionKey, inquireFrom, inquireTo, inquireDetails);
                
            }
            NotificationMaster.insert(userKeyFrom, userKeyTo, inquireFrom, inquireTo, "Vendor Registration@#@"+othersPageType, vrKey, "VR "+vrAction);
            if(vrAction.equals("Accepted"))
            {
                String companyType="Individual/Sole Proprietor";
                UserBean ub=UserMaster.showUserDetailsByUserKey(userKeyFrom);
                String businessContactName=ub.getFirstName()+" "+ub.getLastName();
                VendorRegistrationMaster.insert(inquireFrom, inquireTo, userKeyFrom, userKeyTo, othersPageType, companyType, businessContactName, "", "0", "no", "", "Large", "0000000000000", "","Accepted");
            }
            TestMemory.test("footer start");
                System.gc();
                TestMemory.test("footer end");
            if(othersPageType.equals("buyer"))
                response.sendRedirect("suppliersVR.jsp");
            else
                response.sendRedirect("buyersVR.jsp");
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
