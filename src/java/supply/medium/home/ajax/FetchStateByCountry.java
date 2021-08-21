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
import supply.medium.home.bean.StateBean;
import supply.medium.home.database.StateMaster;
import supply.medium.utility.TestMemory;

/**
 *
 * @author Intel
 */
public class FetchStateByCountry extends HttpServlet {

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
            String fetchFor = request.getParameter("fetchFor");
            String countryKey = request.getParameter("countryCode");
            //out.print(countryKey);
            if (fetchFor.equals("country")) {
                ArrayList al = StateMaster.showAllStatesByCountryKey(countryKey);
                StateBean ssb = null;
                String result = "<SELECT style='margin-bottom: 5px; -webkit-appearance: menulist-button; width: 188px; position: absolute; height: 28px; font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);' id='state_0' "
                        + "class='selectbox hasCustomSelect' name='state_0'><OPTION>--Select State--</OPTION>";
                for (int i = 0; i < al.size(); i++) {
                    ssb = (StateBean) al.get(i);
                    result += "<option value='" + ssb.getStateKey() + "'>" + ssb.getStateName() + "</option>";
                }
                result += "</SELECT>"
                        + "<LABEL id='state_0err' class='error'></LABEL>";

                if (al.size() == 0) {
                    result = "<input type='text' class='textbox' id='state_0' name='state_0'/>"
                            + "<LABEL id='state_0err' class='error'></LABEL>";
                }

                out.println(result);
            }
            if (fetchFor.equals("address1")) {
                ArrayList al = StateMaster.showAllStatesByCountryKey(countryKey);
                StateBean ssb = null;
                String result = "<SELECT style='margin-bottom: 5px; -webkit-appearance: menulist-button; width: 188px; position: absolute; height: 28px; font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);' id='state_1' "
                        + "class='selectbox hasCustomSelect' name='state_1'><OPTION>--Select State--</OPTION>";
                for (int i = 0; i < al.size(); i++) {
                    ssb = (StateBean) al.get(i);
                    result += "<option value='" + ssb.getStateKey() + "'>" + ssb.getStateName() + "</option>";
                }
                result += "</SELECT>"
                        + "<LABEL id='state_0err' class='error'></LABEL>";

                if (al.size() == 0) {
                    result = "<input type='text' class='textbox' id='state_1' name='state_1'/>"
                            + "<LABEL id='state_0err' class='error'></LABEL>";
                }

                out.println(result);
            }
            if (fetchFor.equals("address2")) {
                ArrayList al = StateMaster.showAllStatesByCountryKey(countryKey);
                StateBean ssb = null;
                String result = "<SELECT style='margin-bottom: 5px; -webkit-appearance: menulist-button; width: 188px; position: absolute; height: 28px; font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);' id='state_2' "
                        + "class='selectbox hasCustomSelect' name='state_2'><OPTION>--Select State--</OPTION>";
                for (int i = 0; i < al.size(); i++) {
                    ssb = (StateBean) al.get(i);
                    result += "<option value='" + ssb.getStateKey() + "'>" + ssb.getStateName() + "</option>";
                }
                result += "</SELECT>"
                        + "<LABEL id='state_0err' class='error'></LABEL>";

                if (al.size() == 0) {
                    result = "<input type='text' class='textbox' id='state_2' name='state_2'/>"
                            + "<LABEL id='state_0err' class='error'></LABEL>";
                }

                out.println(result);
            }
            TestMemory.test("footer start");
                System.gc();
                TestMemory.test("footer end");
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
