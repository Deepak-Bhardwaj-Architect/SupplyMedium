/*
 * 
 * 
 * 
 */

package supply.medium.home.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import supply.medium.home.database.UserMailingAddressMaster;
import supply.medium.home.database.UserMaster;
import supply.medium.home.database.UserSettingMaster;
import supply.medium.home.mailing.ActivationMailing;
import supply.medium.home.mailing.HTMLMailComposer;
import supply.medium.utility.FileCopy;
import supply.medium.utility.SmProperties;
import supply.medium.utility.MemoryTest;

/**
 *
 * @author !!ULTIMATE
 */
public class AdminAddUser extends HttpServlet {

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
            String companyKey = session.getAttribute("companyKey").toString();
            String country = request.getParameter("countryregion");
            String address = "";
            String city = request.getParameter("city");
            String state = request.getParameter("state_0");
            String zipcode = request.getParameter("zipcode");
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String title = request.getParameter("title");
            String userType= request.getParameter("usertype");
            String department = request.getParameter("department");
            String managerSupervisor = request.getParameter("managersupervisor");
            String phone = request.getParameter("phone");
            String cell = request.getParameter("cell");
            String fax = request.getParameter("fax");
            String email = request.getParameter("email");
            String password = "123456@a";           
            
           String confirmationValue=System.currentTimeMillis()+""; 
           int result2 = UserMaster.insert(companyKey, userType, firstName, lastName, " ",
                    title, department, managerSupervisor, phone, cell, fax, email,
                    password,confirmationValue);
           //String adminEmail=UserMaster.getAdminEmail(companyKey);
           String [ ] to = { email };

		String sub = "SupplyMedium login activation link";

		HTMLMailComposer composer = new HTMLMailComposer( );              
                
		
		String message = composer.composeUserActivationLinkMail(UUID.randomUUID( ).toString( ),email,
                        firstName,"",confirmationValue );
		
		composer = null;
				  
		ActivationMailing am = new ActivationMailing( );
		
                
		am.composeAndSendMail( to, sub, message.toString( ),"SM Registration" );

            int userKey=UserMaster.showLastUserKeyByEmail(email);
            UserSettingMaster.insert(userKey+"");

            SmProperties.folderPath = request.getRealPath("")+ File.separator + "cropData" + File.separator;
            SmProperties.folderPath=SmProperties.folderPath.replace(File.separator+"app"+File.separator, File.separator+"zData"+File.separator);
            String filePath = SmProperties.folderPath + "company-" + companyKey + File.separator;            
            
            String source = SmProperties.folderPath + "mugshot.png";
            String target = filePath + "company-" + companyKey + ".png";
            FileCopy.copyCompleteFile(source, target);

            target = SmProperties.folderPath + "users" + File.separator + userKey + ".png";
            source = SmProperties.folderPath + "no_image_icon.png";
            FileCopy.copyCompleteFile(source, target);            
            
            int result=UserMailingAddressMaster.insert(userKey+"",country, address, city, state, zipcode); 
            MemoryTest.test("footer start");
                System.gc();
                MemoryTest.test("footer end");
            if(result>0)
                response.sendRedirect("adminListUsers.jsp");
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
