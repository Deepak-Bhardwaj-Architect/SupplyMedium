/*
 * 
 * 
 * 
 */
package supply.medium.home.ajax;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import supply.medium.home.database.DepartmentFolderMaster;
import supply.medium.home.database.FolderMaster;
import supply.medium.utility.SmProperties;
import supply.medium.utility.DeleteFolder;
import supply.medium.utility.MemoryTest;

/**
 *
 * @author Intel
 */
public class FolderManagementCorporatePage extends HttpServlet {

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
            String eventType = request.getParameter("eventType");
            HttpSession session = request.getSession(true);
            String companyKey = session.getAttribute("companyKey").toString();
            if (eventType.equals("createFolder")) {
                String departmentKey = request.getParameter("departmentKey");
                String folderName = request.getParameter("folderName");
                SmProperties.folderPath = request.getRealPath("")+ File.separator + "cropData" + File.separator;
                String folderPath = SmProperties.folderPath + "company-" + companyKey +File.separator+ "department feed";
                File uploadDir = new File(folderPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                File dir = new File(folderPath +File.separator + folderName.trim());
                if (dir.exists()) {
                    out.print("Folder already exist");
                } else {
                    dir.mkdir();
                    out.print("Folder created");
                }
                String result = FolderMaster.insertCorporateDepartmentFolder(companyKey, folderName);
                DepartmentFolderMaster.insert(companyKey, departmentKey, result);
            }
            if (eventType.equals("deleteFolder")) {
                String departmentKey = request.getParameter("departmentKey");
                String folderName = request.getParameter("folderName");
                String folderKey = request.getParameter("folderKey");
                SmProperties.folderPath = request.getRealPath("")+ File.separator + "cropData" + File.separator;
                String folderPath = SmProperties.folderPath + "company-" + companyKey +File.separator+ "department feed";
                File f = new File(folderPath + File.separator+ folderName);
                DeleteFolder df = new DeleteFolder();
                df.delete(f);
                FolderMaster.delete(folderKey);
                DepartmentFolderMaster.delete(companyKey, departmentKey, folderKey);
            }
            if (eventType.equals("deleteFile")) {
                String folderName = request.getParameter("folderName");
                String fileName = request.getParameter("fileName");
                SmProperties.folderPath = request.getRealPath("")+ File.separator + "cropData" + File.separator;
                String folderPath = SmProperties.folderPath + "company-" + companyKey +File.separator+ "department feed";
                File f = new File(folderPath + File.separator + folderName + File.separator + fileName);
                if (f.exists()) {
                    f.delete();
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
