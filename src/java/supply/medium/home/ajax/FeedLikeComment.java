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
import javax.servlet.http.HttpSession;
import supply.medium.home.bean.FeedCommentBean;
import supply.medium.home.bean.FeedCommentMaster;
import supply.medium.home.database.FeedLikeMaster;
import supply.medium.home.database.UserMaster;
import supply.medium.utility.TestMemory;

/**
 *
 * @author Intel
 */
public class FeedLikeComment extends HttpServlet {

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
            if (request.getParameter("id") != null) {
                HttpSession session = request.getSession(true);
                int likes = FeedLikeMaster.showFeedLikesCount(request.getParameter("id"));
                int likeStatus = FeedLikeMaster.showFeedLikedByUser(request.getParameter("id"), session.getAttribute("userKey").toString());
                int comments = FeedCommentMaster.showFeedCommentCount(request.getParameter("id"));
                out.print(likes + "*" + comments + "*" + likeStatus);
            }
            if (request.getParameter("cmnt") != null) {
                HttpSession session = request.getSession(true);
                if (request.getParameter("cmnt").trim().equals("like")) {
                    int result = FeedLikeMaster.insert(request.getParameter("ids"), session.getAttribute("userKey").toString());
                } else {
                    int result = FeedCommentMaster.insert(request.getParameter("ids"), session.getAttribute("userKey").toString(), request.getParameter("cmnt"));
                }

            }
            if (request.getParameter("slct_cmnt") != null) {

                ArrayList feedCommentList = FeedCommentMaster.showAll(request.getParameter("ids"));
                FeedCommentBean scb = null;
                for (int i = 0; i < feedCommentList.size(); i++) {
                    scb = (FeedCommentBean) feedCommentList.get(i);
                    out.print("<li style='background:#FFFFFF;margin-top:10px;padding:10px;height:auto;border-radius:10px;'><p style='color:#2E2E2E'><b>"+UserMaster.showUserDetails(scb.getUserKey()).getFirstName()+" : </b>" + scb.getComment() + "</p><p style='color:#A4A4A4;margin-left:450px;'>" + scb.getCommentedOn() + "</p></li>");
                }
            }
            if (request.getParameter("selectInternalFeedComment") != null) {

                ArrayList feedCommentList = FeedCommentMaster.showAll(request.getParameter("ids"));
                FeedCommentBean scb = null;
                for (int i = 0; i < feedCommentList.size(); i++) {
                    scb = (FeedCommentBean) feedCommentList.get(i);
                    out.print("<li style='background:#FFFFFF;margin-top:10px;padding:10px;height:auto;border-radius:10px;'><p style='color:#2E2E2E'><b>"+UserMaster.showUserDetails(scb.getUserKey()).getFirstName()+" : </b>" + scb.getComment() + "</p><p style='color:#A4A4A4;margin-left:330px;'>" + scb.getCommentedOn() + "</p></li>");
                }
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
