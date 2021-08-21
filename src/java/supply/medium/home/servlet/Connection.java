/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supply.medium.home.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import supply.medium.home.bean.ConnectionBean;
import supply.medium.home.database.ConnectionMaster;
import supply.medium.home.database.NotificationMaster;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MemoryTest;

/**
 *
 * @author Intel
 */
public class Connection extends HttpServlet {

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

            String actionType = request.getParameter("actionType");
            HttpSession session = request.getSession(true);
            String userKeyFrom = session.getAttribute("userKey").toString();
            String userKeyTo = request.getParameter("userKeyTo");
            String companyKeyFrom = session.getAttribute("companyKey").toString();
            String companyKeyTo = request.getParameter("companyKeyTo");
            String connectionKey = System.currentTimeMillis() + "";
            if(actionType.equals("insert")) {

                

                    int res = ConnectionMaster.areConnection(userKeyFrom, userKeyTo);
//                    ErrorMaster.insert("res : "+res);
                    if (res == 0) {

                        ConnectionMaster.insert(connectionKey, userKeyFrom, userKeyTo, companyKeyFrom, companyKeyTo);

                        NotificationMaster.insert(userKeyFrom, userKeyTo, companyKeyFrom, companyKeyTo, "My Connection", connectionKey, "Connection Request");
                    }

        } else if(actionType.equals("statusUpdate")) {

                

                    String connectionId = request.getParameter("connectionId");
                    ErrorMaster.insert(connectionId);
                    ConnectionBean cb = ConnectionMaster.showByConnectionKey(connectionId);
                    userKeyFrom = cb.getUserKeyFrom();
                    userKeyTo = cb.getUserKeyTo();
                    companyKeyFrom = cb.getCompanyKeyFrom();
                    companyKeyTo = cb.getCompanyKeyTo();

                    String connectionKey2 = cb.getConnectionKey();
                    String status = request.getParameter("status");
                    if (status.equals("Accepted")) {
                        ConnectionMaster.statusUpdate(status, connectionKey2);
                        NotificationMaster.insert(userKeyTo, userKeyFrom, companyKeyTo, companyKeyFrom, "My Connection", connectionKey2, "Connection Request Accepted");
                        ConnectionMaster.insert(connectionKey,userKeyTo, userKeyFrom, companyKeyTo, companyKeyFrom);
                        ConnectionMaster.statusUpdate(status, connectionKey);
                    } else {
                        ConnectionMaster.delete(connectionKey2);
                        NotificationMaster.insert(userKeyTo, userKeyFrom, companyKeyTo, companyKeyFrom, "My Connection", connectionKey2, "Connection Request Deleted");
                    }
            }
            
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
