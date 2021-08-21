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
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import supply.medium.utility.SmProperties;
import supply.medium.utility.DeleteFolder;
import supply.medium.utility.MemoryTest;

/**
 *
 * @author Intel
 */
//@WebServlet(name = "CompanyFolder", urlPatterns = {"/CompanyFolder"})
public class CompanyFolder extends HttpServlet {

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
            HttpSession session = request.getSession(true);
            SmProperties.folderPath = request.getRealPath("") + File.separator + "cropData" + File.separator;
            String userFolderPath = SmProperties.folderPath + "company-" + session.getAttribute("companyKey") + File.separator + "user feed" + File.separator + "user-" + session.getAttribute("userKey");
            String userDownloadUrl = SmProperties.urlPath + "company-" + session.getAttribute("companyKey") + File.separator + "user feed" + File.separator + "user-" + session.getAttribute("userKey");

            String companyFolderPath = SmProperties.folderPath + "company-" + session.getAttribute("companyKey") + File.separator + "internal feed" + File.separator;
            String companyDownloadUrl = SmProperties.urlPath + "company-" + session.getAttribute("companyKey") + File.separator + "internal feed" + File.separator;
            if (request.getParameter("typ").equals("crt_usr_fldr")) {
                File uploadDir = new File(userFolderPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                File dir = new File(userFolderPath + File.separator + request.getParameter("fldr_nm").trim());
                if (dir.exists()) {
                    out.print("Folder already exist");
                } else {
                    dir.mkdir();
                    out.print("Folder created");
                }
            }
            if (request.getParameter("typ").equals("gt_usr_fldrs")) {
                String style = "style='color: rgb(255, 255, 255); background: rgb(3, 155, 194);'";
                String folderName = request.getParameter("folder_name");
                String selectedFolderName = "";
                String selectedFolderId = "";
                File f = new File(userFolderPath);
                File[] files = f.listFiles();

                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        File file = files[i];
                        if (file.isDirectory()) {
                            if (request.getParameter("folder_name") != null) {
                                if (folderName.equals("none") && i == 0) {
                                    selectedFolderName = file.getName();
                                    selectedFolderId = "fldr_" + i + "";
                                    out.print("<li " + style + " id=fldr_" + i + " onclick=\"javascript:opn_fldr_fls('" + file.getName() + "','fldr_" + i + "')\";>&nbsp;&nbsp;<div class='folder folder_closed_top_one'></div>" + file.getName() + "</li>");
                                } else if (folderName.equals(file.getName())) {
                                    selectedFolderName = file.getName();
                                    selectedFolderId = "fldr_" + i + "";
                                    out.print("<li " + style + " id=fldr_" + i + " onclick=\"javascript:opn_fldr_fls('" + file.getName() + "','fldr_" + i + "')\";>&nbsp;&nbsp;<div class='folder folder_closed_top_one'></div>" + file.getName() + "</li>");
                                } else {
                                    out.print("<li id=fldr_" + i + " onclick=\"javascript:opn_fldr_fls('" + file.getName() + "','fldr_" + i + "')\";>&nbsp;&nbsp;<div class='folder folder_closed_top_one'></div>" + file.getName() + "</li>");
                                }
                            }

                        }
                    }
                    out.print("<input type='hidden' id='selected_folder_name' value='" + selectedFolderName + "' >");
                    out.print("<input type='hidden' id='selected_folder_id' value='" + selectedFolderId + "' >");
                }
            }
            if (request.getParameter("typ").equals("gt_usr_fldrs_fls")) {

                File f = new File(userFolderPath + File.separator + request.getParameter("fldr_nm"));
                File[] files = f.listFiles();

                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        File file = files[i];
                        out.print("<li title='"+ file.getName()+"' id='fl" + i + "' onclick=\"javascript:slct_fldr_fl('fl" + i + "','" + file.getName() + "')\";>&nbsp;&nbsp;<div class='file file_closed_top_one'></div>" + file.getName().substring(0, 10) + "</li>");
                    }
                    out.print("<input type='hidden' value='" + userDownloadUrl + "/" + request.getParameter("fldr_nm") + "' id='slected_user_folder_url' />");
                }
            }
            if (request.getParameter("typ").equals("crt_cmpny_fldr")) {

                File uploadDir = new File(companyFolderPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                File dir = new File(companyFolderPath + File.separator + request.getParameter("fldr_nm").trim());
                if (dir.exists()) {
                    out.print("Folder already exist");
                } else {
                    dir.mkdir();
                    out.print("Folder created");
                }
            }

            if (request.getParameter("typ").equals("gt_cmpny_fldrs")) {
                String style = "style='color: rgb(255, 255, 255); background: rgb(3, 155, 194);'";
                String folderName = request.getParameter("folder_name");
                String selectedFolderName = "";
                String selectedFolderId = "";
                File f = new File(companyFolderPath);
                File[] files = f.listFiles();

                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        File file = files[i];
                        if (file.isDirectory()) {
                            if (request.getParameter("folder_name") != null) {
                                if (folderName.equals("none") && i == 0) {
                                    selectedFolderName = file.getName();
                                    selectedFolderId = "cmpny_fldr_" + i + "";
                                    out.print("<li " + style + " id=cmpny_fldr_" + i + " onclick=\"javascript:opn_cmpny_fldr_fls('" + file.getName() + "','cmpny_fldr_" + i + "')\";>&nbsp;&nbsp;<div class='folder folder_closed_top_one'></div>" + file.getName() + "</li>");
                                } else if (folderName.equals(file.getName())) {
                                    selectedFolderName = file.getName();
                                    selectedFolderId = "cmpny_fldr_" + i + "";
                                    out.print("<li " + style + " id=cmpny_fldr_" + i + " onclick=\"javascript:opn_cmpny_fldr_fls('" + file.getName() + "','cmpny_fldr_" + i + "')\";>&nbsp;&nbsp;<div class='folder folder_closed_top_one'></div>" + file.getName() + "</li>");
                                } else {
                                    out.print("<li id=cmpny_fldr_" + i + " onclick=\"javascript:opn_cmpny_fldr_fls('" + file.getName() + "','cmpny_fldr_" + i + "')\";>&nbsp;&nbsp;<div class='folder folder_closed_top_one'></div>" + file.getName() + "</li>");
                                }
                            }

                        }
                    }
                    out.print("<input type='hidden' id='selected_folder_name' value='" + selectedFolderName + "' >");
                    out.print("<input type='hidden' id='selected_folder_id' value='" + selectedFolderId + "' >");
                }
            }
            if (request.getParameter("typ").equals("gt_cmpny_fldrs_fls")) {

                File f = new File(companyFolderPath + File.separator + request.getParameter("fldr_nm"));
                File[] files = f.listFiles();

                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        File file = files[i];
                        out.print("<li title='"+ file.getName()+"' style='width:165px;' id='fl" + i + "' onclick=\"javascript:slct_fldr_fl('fl" + i + "','" + file.getName() + "')\";>&nbsp;&nbsp;<div class='file file_closed_top_one'></div>" + file.getName().substring(0, 10) + "</li>");
                    }
                    out.print("<input type='hidden' value='" + companyDownloadUrl + "/" + request.getParameter("fldr_nm") + "' id='slected_company_folder_url' />");
                }
            }
            if (request.getParameter("typ").equals("dlt_usr_fldr")) {

                File f = new File(userFolderPath + File.separator + request.getParameter("fldr_nm"));
                DeleteFolder df = new DeleteFolder();
                df.delete(f);
            }
            if (request.getParameter("typ").equals("dlt_cmpny_fldr")) {

                File f = new File(companyFolderPath + File.separator + request.getParameter("fldr_nm"));
                DeleteFolder df = new DeleteFolder();
                df.delete(f);
            }
            if (request.getParameter("typ").equals("dlt_usr_fl")) {

                File f = new File(userFolderPath + File.separator + request.getParameter("fldr_nm") + File.separator + request.getParameter("fl_nm"));
                if (f.exists()) {
                    f.delete();
                }
            }
            if (request.getParameter("typ").equals("dlt_cmpny_fl")) {

                File f = new File(companyFolderPath + File.separator + request.getParameter("fldr_nm") + File.separator + request.getParameter("fl_nm"));
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
