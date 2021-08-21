/*
 * 
 * 
 * 
 */
package supply.medium.home.ajax;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supply.medium.home.bean.FolderBean;
import supply.medium.home.bean.GroupBean;
import supply.medium.home.bean.UserBean;
import supply.medium.home.database.DepartmentFolderMaster;
import supply.medium.home.database.DepartmentGroupMaster;
import supply.medium.home.database.DepartmentUserMaster;
import supply.medium.home.database.FolderMaster;
import supply.medium.home.database.GroupMaster;
import supply.medium.home.database.UserMaster;
import supply.medium.utility.SmProperties;
import supply.medium.utility.MemoryTest;

/**
 *
 * @author LenovoB560
 */
public class DepartmentUser extends HttpServlet {

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
            int result = 0;
            if (request.getParameter("eventType").endsWith("addUserToDepartment")) {
                result = DepartmentUserMaster.insert(request.getParameter("companyKey"), request.getParameter("departmentKey"), request.getParameter("userKey"));
            } else if (request.getParameter("eventType").endsWith("deleteUserFromDepartment")) {
                result = DepartmentUserMaster.delete(request.getParameter("companyKey"), request.getParameter("departmentKey"), request.getParameter("userKey"));
            } else if (request.getParameter("eventType").endsWith("showNonExistingUserInDepartment")) {
                ArrayList userList = UserMaster.showNonExistingUserInDepartment(request.getParameter("companyKey"), request.getParameter("departmentKey"));
                UserBean ub = null;
                for (int i = 0; i < userList.size(); i++) {
                    ub = (UserBean) userList.get(i);
                    out.print("<option value=" + ub.getUserKey() + ">" + ub.getFirstName() + " " + ub.getLastName() + "</option>");
                }
            } else if (request.getParameter("eventType").endsWith("showExistingUserInDepartment")) {
                ArrayList userList = UserMaster.showExistingUserInDepartment(request.getParameter("companyKey"), request.getParameter("departmentKey"));
                UserBean ub = null;
                for (int i = 0; i < userList.size(); i++) {
                    ub = (UserBean) userList.get(i);
                    out.print("<option value=" + ub.getUserKey() + ">" + ub.getFirstName() + " " + ub.getLastName() + "</option>");
                }
            } else if (request.getParameter("eventType").endsWith("addGroupToDepartment")) {
                result = DepartmentGroupMaster.insert(request.getParameter("companyKey"), request.getParameter("departmentKey"), request.getParameter("groupKey"));
            } else if (request.getParameter("eventType").endsWith("deleteGroupFromDepartment")) {
                result = DepartmentGroupMaster.delete(request.getParameter("companyKey"), request.getParameter("departmentKey"), request.getParameter("groupKey"));
            } else if (request.getParameter("eventType").endsWith("showNonExistingGroupInDepartment")) {
                ArrayList groupList = GroupMaster.showNonExistingGroupInDepartment(request.getParameter("companyKey"), request.getParameter("departmentKey"));
                GroupBean ub = null;
                for (int i = 0; i < groupList.size(); i++) {
                    ub = (GroupBean) groupList.get(i);
                    out.print("<option value=" + ub.getGroupKey() + ">" + ub.getGroupName() + "</option>");
                }
            } else if (request.getParameter("eventType").endsWith("showExistingGroupInDepartment")) {
                ArrayList groupList = GroupMaster.showExistingGroupInDepartment(request.getParameter("companyKey"), request.getParameter("departmentKey"));
                GroupBean ub = null;
                for (int i = 0; i < groupList.size(); i++) {
                    ub = (GroupBean) groupList.get(i);
                    out.print("<option value=" + ub.getGroupKey() + ">" + ub.getGroupName() + "</option>");
                }
            } else if (request.getParameter("eventType").endsWith("addFolder")) {
                result = FolderMaster.insert(request.getParameter("companyKey"), request.getParameter("folderName"));
                SmProperties.folderPath = request.getRealPath("")+ File.separator + "cropData" + File.separator;
                String filePaths = SmProperties.folderPath + "company-" + request.getParameter("companyKey") +File.separator+ "department feed"+File.separator+ request.getParameter("folderName");
                File uploadDir = new File(filePaths);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
            } else if (request.getParameter("eventType").endsWith("deleteFolder")) {
                result = FolderMaster.delete(request.getParameter("folderKey"));
            } else if (request.getParameter("eventType").endsWith("showNonExistingFolderInDepartment")) {
                ArrayList groupList = FolderMaster.showNonExistingFolderInDepartment(request.getParameter("companyKey"), request.getParameter("departmentKey"));
                FolderBean ub = null;
                for (int i = 0; i < groupList.size(); i++) {
                    ub = (FolderBean) groupList.get(i);
                    out.print("<option value=" + ub.getFolderKey() + ">" + ub.getFolderName() + "</option>");
                }
            } else if (request.getParameter("eventType").endsWith("showExistingFolderInDepartment")) {
                ArrayList groupList = FolderMaster.showExistingFolderInDepartment(request.getParameter("companyKey"), request.getParameter("departmentKey"));
                FolderBean ub = null;
                for (int i = 0; i < groupList.size(); i++) {
                    ub = (FolderBean) groupList.get(i);
                    out.print("<option value=" + ub.getFolderKey() + ">" + ub.getFolderName() + "</option>");
                }
            } else if (request.getParameter("eventType").endsWith("addFolderToDepartment")) {
                result = DepartmentFolderMaster.insert(request.getParameter("companyKey"), request.getParameter("departmentKey"), request.getParameter("folderKey"));
            } else if (request.getParameter("eventType").endsWith("deleteFolderFromDepartment")) {
                result = DepartmentFolderMaster.delete(request.getParameter("companyKey"), request.getParameter("departmentKey"), request.getParameter("folderKey"));
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
