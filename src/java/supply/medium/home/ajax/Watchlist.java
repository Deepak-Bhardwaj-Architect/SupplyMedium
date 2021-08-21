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
import supply.medium.home.bean.UserBean;
import supply.medium.home.bean.WatchlistBean;
import supply.medium.home.database.WatchlistMaster;
import supply.medium.home.database.WatchlistMemberMaster;
import supply.medium.utility.SmProperties;
import supply.medium.utility.TestMemory;

/**
 *
 * @author Intel
 */
public class Watchlist extends HttpServlet {

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
            String eventType = request.getParameter("eventType");
            HttpSession session = request.getSession();
            if (eventType.equals("createWatchlist")) {
                String watchlistName = request.getParameter("watchlistName");
                WatchlistMaster.insert(session.getAttribute("userKey").toString(), watchlistName);
            } else if (eventType.equals("showWatchlist")) {
                String htmlContent = "";
                ArrayList watchlists = WatchlistMaster.showWatchlist(session.getAttribute("userKey").toString());
                WatchlistBean wb = null;
                String watchlistKey = null;
                String watchlistName = null;
                for (int i = 0; i < watchlists.size(); i++) {
                    wb = (WatchlistBean) watchlists.get(i);
                    watchlistKey = wb.getWatchlistKey();
                    watchlistName = wb.getWatchListName();
                    htmlContent += "<div class='content_label_sub' id='watchlist_" + watchlistKey + "' onclick=\"javascript:watchlistKey=" + watchlistKey + ";showWatchlistMember('" + watchlistKey + "');\" onmouseover=\"javascript:document.getElementById('watchlist_del_" + watchlistKey + "').style.display='block';\" onmouseout=\"javascript:document.getElementById('watchlist_del_" + watchlistKey + "').style.display='none';\" style='margin-left:20px; width:160px;'>";
                    htmlContent += "<div class='watchlist_name_lbl' onclick=\"javascript:document.getElementById('user_feed_frm').style.display='none';document.getElementById('feeds').style.display='none';document.getElementById('watchlist_head').style.display='block';\" id='watchlist_" + watchlistKey + "_name'>" + watchlistName + "</div>";
                    htmlContent += "<input type='button' style='display: none;' class='delete_watchlist_btn' id='watchlist_del_" + watchlistKey + "' onclick=\"javascript:deleteWatchlist(" + watchlistKey + ");\">";
                    htmlContent += "<div></div>";
                    htmlContent += "</div>";
                }
                out.print(htmlContent);
            } else if (eventType.equals("deleteWatchlist")) {
                String watchlistKey = request.getParameter("watchlistKey");
                WatchlistMaster.delete(watchlistKey);
            } else if (eventType.equals("addWatchlistMember")) {
                String watchlistKey = request.getParameter("watchlistKey");
                String userKey = request.getParameter("userKey");
                WatchlistMemberMaster.insert(watchlistKey, userKey);
            } else if (eventType.equals("showWatchlistMember")) {
                String htmlContent = "";
                String watchlistKey = request.getParameter("watchlistKey");
                ArrayList watchlistMember = WatchlistMemberMaster.showWatchlistMember(watchlistKey);
                UserBean ub = null;
                for (int i = 0; i < watchlistMember.size(); i++) {
                    ub = (UserBean) watchlistMember.get(i);
                    htmlContent += "<div class='memberlist_cont' id='member_" + i + "' onclick=\"javascript:$('#member_" + i + "').addClass('memberlist_cont_selected');document.getElementById('watchlistKey').value='"+watchlistKey+"';document.getElementById('txtDeleteMember').value+='" + ub.getUserKey() + "';\">"
                            + "<img src='"+SmProperties.pathUrl+"/users/"+ub.getUserKey()+".png' height='30' class='img_none'>"
                            + "<div class='member_right'>"
                            + "<div class='text_label' style='font-size:15px; color:#262626;'>" + ub.getFirstName() + " " + ub.getLastName() + "</div>"
                            + "<div class='text_label'>" + ub.getCompanyKey() + "</div>"
                            + "<div class='text_label' id='member_1_email'>" + ub.getEmail() + "</div>"
                            + "<input type='hidden' id='txtMemberId" + i + "' value='" + ub.getFax() + "'/>"
                            + "</div></div>";
                }
                if(watchlistMember.size()!=0)
                htmlContent += "<input type='hidden' id='watchlistKey' value='0' /><input type='hidden' id='txtDeleteMember' value='0' /><input type='hidden' id='watchlistMemberCount' value='" + watchlistMember.size() + "'> ";
                out.print(htmlContent);
            } else if (eventType.equals("deleteWatchlistMember")) {
                String watchlistMemberKey = request.getParameter("watchlistMemberKey");
                WatchlistMemberMaster.delete(watchlistMemberKey,request.getParameter("watchlistKey"));
            } else if (eventType.equals("searchMemberToAdd")) {
                String userPartialName = request.getParameter("value");
                String htmlContent = "";
//                session=request.getSession(true);
                String companyKey = session.getAttribute("companyKey").toString();
                String watchlistKey = request.getParameter("watchlistKey");
                ArrayList watchlists = WatchlistMaster.showMembersToAddInWatchListByCompanyKey(companyKey, userPartialName, session.getAttribute("userKey").toString());
                UserBean wb = null;
                for (int i = 0; i < watchlists.size(); i++) {
                    wb = (UserBean) watchlists.get(i);
                    htmlContent += "<div class='listcontainer' id='search_conn_0' onclick=\"javascript:addWatchlistMember('" + watchlistKey + "','" + wb.getUserKey() + "')\">";
                    htmlContent += "<div class='img_right'>";
                    htmlContent += "<img src='" + SmProperties.pathUrl + "/users/" + wb.getUserKey() + ".png' class='listimg'>";
                    htmlContent += "</div>";
                    htmlContent += "<div class='list_right'>";
                    htmlContent += "<div class='name' id='search_conn_0_name'>" + wb.getFirstName() + " " + wb.getLastName() + "</div>";
                    htmlContent += "<div class='cont' id='search_conn_0_compName'>" + wb.getDepartment() + "</div>";
                    htmlContent += "<div class='cont' id='search_conn_0_email'>" + wb.getEmail() + "</div>";
                    htmlContent += "</div>";
                    htmlContent += "<div class='listseperator'></div>";
                    htmlContent += "</div>";
                }
                out.print(htmlContent);
            } else if (eventType.equals("searchAddedMemberInWatchlist")) {
                String userPartialName = request.getParameter("userPartialMemberName");
                String htmlContent = "";
//                session=request.getSession(true);
//                String companyKey = session.getAttribute("companyKey").toString();
                String watchlistKey = request.getParameter("watchlistKey");
                ArrayList watchlistMember = WatchlistMemberMaster.showMembersAddedInWatchlist(watchlistKey, userPartialName);
                UserBean ub = null;
                for (int i = 0; i < watchlistMember.size(); i++) {
                    ub = (UserBean) watchlistMember.get(i);
                    htmlContent += "<div class='memberlist_cont' id='member_" + i + "' onclick=\"javascript:$('#member_" + i + "').addClass('memberlist_cont_selected');document.getElementById('txtDeleteMember').value+=','+document.getElementById('txtMemberId" + i + "').value;\">"
                            + "<img src='"+SmProperties.pathUrl+"/users/"+ub.getUserKey()+".png' class='img_none'>"
                            + "<div class='member_right'>"
                            + "<div class='text_label' style='font-size:15px; color:#262626;'>" + ub.getFirstName() + " " + ub.getLastName() + "</div>"
                            + "<div class='text_label'>" + ub.getCompanyKey() + "</div>"
                            + "<div class='text_label' id='member_1_email'>" + ub.getEmail() + "</div>"
                            + "<input type='hidden' id='txtMemberId" + i + "' value='" + ub.getFax() + "'/>"
                            + "</div></div>";
                }
                htmlContent += "<input type='hidden' id='txtDeleteMember' value='0' /><input type='hidden' id='watchlistMemberCount' value='" + watchlistMember.size() + "'> ";
                out.print(htmlContent);
                TestMemory.test("footer start");
                System.gc();
                TestMemory.test("footer end");
            }

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
