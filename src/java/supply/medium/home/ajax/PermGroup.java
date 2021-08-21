/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supply.medium.home.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supply.medium.home.bean.GroupPermBean;
import supply.medium.home.database.GroupPermMaster;

/**
 *
 * @author VIC
 */
public class PermGroup extends HttpServlet {

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
            
            String groupKey=request.getParameter("groupKey");
            GroupPermBean gpb=GroupPermMaster.showPermByGroupKey(groupKey);
            String data="";
            data+="<fieldset class='privfieldset'>";
            data+="    <legend>Privileges</legend>";
            data+="    <legend>&nbsp;</legend>";
            data+="    <div class='privleft'>";
            data+="        <div class='listitem'>";
            data+="            <div class='checkContainer'>";
            data+="                <input type='checkbox' ";
            if(gpb.getAdd_user().equalsIgnoreCase("yes"))
                data+="checked";
            data+=" class='checkbox' id='addusers'>";
            data+="                <div>";
            data+="                </div>";
            data+="            </div>";
            data+="            <label for='addusers' class='group_pri_lbl'>Add New Users</label>";
            data+="        </div>";
            data+="        <div class='listitem'>";
            data+="            <div class='checkContainer'>";
            data+="                <input type='checkbox'  ";
            if(gpb.getDelete_user().equalsIgnoreCase("yes"))
                data+="checked";
            data+=" class='checkbox' id='delusers'>";
            data+="                <div>";
            data+="                </div>";
            data+="            </div>";
            data+="            <label for='delusers' class='group_pri_lbl'>Delete Users</label>";
            data+="        </div>";
//            data+=" <!--                                        <div class='listitem'>";
//            data+="        <div class='checkContainer'>";
//            data+="        <input type='checkbox' class='checkbox'  id='uploaddoc'>";
//            data+="        <div>";
//            data+="        </div>";
//            data+="        </div>";
//            data+="        <label for='uploaddoc' class='group_pri_lbl'>Upload Documents</label>";
//            data+="        </div>";
//            data+="        <div class='listitem'>";
//            data+="        <div class='checkContainer'>";
//            data+="        <input type='checkbox' class='checkbox'  id='deldoc'>";
//            data+="        <div>";
//            data+="        </div>";
//            data+="        </div>";
//            data+="        <label for='deldoc' class='group_pri_lbl'>Delete Documents</label>";
//            data+="        </div>-->";
            data+="        <div class='listitem'>";
            data+="            <div class='checkContainer'>";
            data+="                <input type='checkbox' ";
            if(gpb.getAdd_vendor().equalsIgnoreCase("yes"))
                data+="checked";
            data+="  class='checkbox' id='addbuyers'>";
            data+="                <div>";
            data+="                </div>";
            data+="            </div>";
            data+="            <label for='addbuyers' class='group_pri_lbl'>Add Buyers/Suppliers</label>";
            data+="        </div>";
            data+="        <div class='listitem'>";
            data+="            <div class='checkContainer'>";
            data+="                <input type='checkbox' ";
            if(gpb.getDelete_vendor().equalsIgnoreCase("yes"))
                data+="checked";
            data+="  class='checkbox' id='delbuyers'>";
            data+="                <div>";
            data+="                </div>";
            data+="            </div>";
            data+="            <label for='delbuyers' class='group_pri_lbl'>Delete Buyers/Suppliers</label>";
            data+="        </div>";
            data+="        <div class='listitem'>";
            data+="            <div class='checkContainer'>";
            data+="                <input type='checkbox' ";
            if(gpb.getAccess_user_mngmt().equalsIgnoreCase("yes"))
                data+="checked";
            data+="  class='checkbox' id='accessusermgmt'>";
            data+="                <div>";
            data+="                </div>";
            data+="            </div>";
            data+="            <label for='accessusermgmt' class='group_pri_lbl'>Access User Management</label>";
            data+="        </div>";
            data+="    </div>";
            data+="    <div class='horizontalbar1' style='height:110px; margin-left:20px;'>";
            data+="    </div>";
            data+="    <div class='horizontalbar2' style='height:110px;'>";
            data+="    </div>";
            data+="    <div class='privright'>";
            data+="        <div class='listitem'>";
            data+="            <div class='checkContainer'>";
            data+="                <input type='checkbox' ";
            if(gpb.getPost_announcements().equalsIgnoreCase("yes"))
                data+="checked";
            data+="  class='checkbox' id='postanncemnt'>";
            data+="                <div>";
            data+="                </div>";
            data+="            </div>";
            data+="            <label for='postanncemnt' class='group_pri_lbl'>Post Announcements</label>";
            data+="        </div>";
//            data+=" <!--                                        <div class='listitem'>";
//            data+="        <div class='checkContainer'>";
//            data+="        <input type='checkbox' class='checkbox' id='delanncemnt'>";
//            data+="        <div>";
//            data+="        </div>";
//            data+="        </div>";
//            data+="        <label for='delanncemnt' class='group_pri_lbl'>Delete Announcements</label>";
//            data+="        </div>-->";
            data+="        <div class='listitem'>";
            data+="            <div class='checkContainer'>";
            data+="                <input type='checkbox' ";
            if(gpb.getRate_vendor().equalsIgnoreCase("yes"))
                data+="checked";
            data+="  class='checkbox' id='ratevendor'>";
            data+="                <div>";
            data+="                </div>";
            data+="            </div>";
            data+="            <label for='rate' class='group_pri_lbl'>Rate Buyers/Suppliers</label>";
            data+="        </div>";
            data+="        <div class='listitem'>";
            data+="            <div class='checkContainer'>";
            data+="                <input type='checkbox' ";
            if(gpb.getAdd_group().equalsIgnoreCase("yes"))
                data+="checked";
            data+=" class='checkbox' id='creategroup'>";
            data+="                <div>";
            data+="                </div>";
            data+="            </div>";
            data+="            <label for='creategroup' class='group_pri_lbl'>Create User Group</label>";
            data+="        </div>";
            data+="        <div class='listitem'>";
            data+="            <div class='checkContainer'>";
            data+="                <input type='checkbox' ";
            if(gpb.getDelete_group().equalsIgnoreCase("yes"))
                data+="checked";
            data+="  class='checkbox' id='delgroup'>";
            data+="                <div>";
            data+="                </div>";
            data+="            </div>";
            data+="            <label for='delgroup' class='group_pri_lbl'>Delete User Group</label>";
            data+="        </div>";
//            data+=" <!--                                        <div class='listitem'>";
//            data+="        <div class='checkContainer'>";
//            data+="        <input type='checkbox' class='checkbox'  id='applythemes'>";
//            data+="        <div>";
//            data+="        </div>";
//            data+="        </div>";
//            data+="        <label for='applythemes' class='group_pri_lbl'>Apply Themes and Borders</label>";
//            data+="        </div>";
//            data+="        <div class='listitem'>";
//            data+="        <div class='checkContainer'>";
//            data+="        <input type='checkbox' class='checkbox'  id='managefolder'>";
//            data+="        <div>";
//            data+="        </div>";
//            data+="        </div>";
//            data+="        <label for='managefolder' class='group_pri_lbl'>Manage Folder</label>";
//            data+="        </div>-->";
            data+="    </div>";
            data+="    <div class='groupupt'>";
            data+="        <input type='hidden' name='groupPermId' id='groupPermId' value='"+groupKey+"'>";
            data+="        <input onclick='updateGroupPrev()' type='button' id='updatepribtn' class='gen-btn-Orange' value='Update'>";
            data+="    </div>";
            data+="    <div id='groupprierr' class='grouperror'>";
            data+="    </div>";
            data+="</fieldset>";
            out.println(data);
        }
    }

    // <editor-fold defaultstate="collapseddata+=" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
