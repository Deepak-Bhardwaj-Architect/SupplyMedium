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
import supply.medium.home.bean.DepartmentPermBean;
import supply.medium.home.database.DepartmentPermMaster;

/**
 *
 * @author VIC
 */
public class PermDepartment extends HttpServlet {

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
            String deptKey=request.getParameter("deptKey");
            DepartmentPermBean gpb=DepartmentPermMaster.showPermByDeptKey(deptKey);
            String data="";
                data+="<fieldset class='privfieldset'>";
                data+="<legend>Privileges</legend>";
                data+="<div class='listitem'>";
                data+="    <div class='checkContainer'>";
                data+="        <input type='checkbox' ";
            if(gpb.getAdd_folder().equalsIgnoreCase("yes"))
                data+="checked";
            data+=" class='checkbox' id='addfolder'>";
                data+="        <div>";
                data+="        </div>";
                data+="    </div>";
                data+="    <label for='addfolder' class='prilbl'>Add Folder on Department Page</label>";
                data+="    <br>";
                data+="</div>";
                data+="<div class='listitem'>";
                data+="    <div class='checkContainer'>";
                data+="        <input type='checkbox' ";
            if(gpb.getDelete_folder().equalsIgnoreCase("yes"))
                data+="checked";
            data+=" class='checkbox' id='delfolder'>";
                data+="        <div>";
                data+="        </div>";
                data+="    </div>";
                data+="    <label for='delfolder' class='prilbl'>Delete Folder on Department Page</label>";
                data+="    <br>";
                data+="</div>";
                data+="<div class='listitem'>";
                data+="    <div class='checkContainer'>";
                data+="        <input type='checkbox' ";
            if(gpb.getAdd_file().equalsIgnoreCase("yes"))
                data+="checked";
            data+=" class='checkbox' id='addfile'>";
                data+="        <div>";
                data+="        </div>";
                data+="    </div>";
                data+="    <label for='addfile' class='prilbl'>Add Files on the Department page</label>";
                data+="    <br>";
                data+="</div>";
                data+="<div class='listitem'>";
                data+="    <div class='checkContainer'>";
                data+="        <input type='checkbox' ";
            if(gpb.getDelete_file().equalsIgnoreCase("yes"))
                data+="checked";
            data+=" class='checkbox' id='delfile'>";
                data+="        <div>";
                data+="        </div>";
                data+="    </div>";
                data+="    <label for='delfile' class='prilbl'>Delete Files on the Department Page</label>";
                data+="    <br>";
                data+="</div>";
                data+="<div class='listitem'>";
                data+="    <div class='checkContainer'>";
                data+="        <input type='checkbox' ";
            if(gpb.getPost_announcements().equalsIgnoreCase("yes"))
                data+="checked";
            data+=" class='checkbox' id='postAnno'>";
                data+="        <div>";
                data+="        </div>";
                data+="    </div>";
                data+="    <label for='postAnno' class='prilbl'>Post Announcement (Department)</label>";
                data+="    <br>";
                data+="</div>";
//                data+="<!--                                    <div class='listitem'>";
//                data+="<div class='checkContainer'>";
//                data+="<input type='checkbox' class='checkbox' id='managefold'>";
//                data+="<div>";
//                data+="</div>";
//                data+="</div>";
//                data+="<label for='managefold' class='prilbl'>Manage Folder</label>";
//                data+="<BR />";
//                data+="</div>-->";
                data+="<div class='deptupt'>";
                data+="    <input type='hidden' name='deptPermId' id='deptPermId' value='"+deptKey+"'>";
                data+="    <input type='button' onclick='updateDeptPrev()' id='updatepribtn' value='Update' class='gen-btn-Orange'>";
                data+="</div>";
                data+="<div id='deptprierr' class='depterror'>";
                data+="</div>";
                data+="                                </fieldset>";
                out.println(data);
        } finally {
            out.close();
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
