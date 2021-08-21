/*
 * 
 * 
 * 
 */
package supply.medium.home.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import supply.medium.home.database.UserSettingMaster;
import supply.medium.utility.MemoryTest;

/**
 *
 * @author Intel
 */
public class UpdateWorkingHours extends HttpServlet {

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
            String workingDays = "";
            String sunday = request.getParameter("SunWorkingFlag");
            String monday = request.getParameter("MonWorkingFlag");
            String tuesday = request.getParameter("TueWorkingFlag");
            String wednesday = request.getParameter("WedWorkingFlag");
            String thursday = request.getParameter("ThuWorkingFlag");
            String friday = request.getParameter("FriWorkingFlag");
            String saturday = request.getParameter("SatWorkingFlag");
            String sundayFrom = request.getParameter("SunFromTime");
            String sundayTo = request.getParameter("SunToTime");
            String mondayFrom = request.getParameter("MonFromTime");
            String mondayTo = request.getParameter("MonToTime");
            String tuesdayFrom = request.getParameter("TueFromTime");
            String tuesdayTo = request.getParameter("TueToTime");
            String wednesdayFrom = request.getParameter("WedFromTime");
            String wednesdayTo = request.getParameter("WedToTime");
            String thursdayFrom = request.getParameter("ThuFromTime");
            String thursdayTo = request.getParameter("ThuToTime");
            String fridayFrom = request.getParameter("FriFromTime");
            String fridayTo = request.getParameter("FriToTime");
            String saturdayFrom = request.getParameter("SatFromTime");
            String saturdayTo = request.getParameter("SatToTime");
            if (sunday.equals("1")) {
                workingDays += "1";
            } else {
                workingDays += "0";
            }
            if (monday.equals("1")) {
                workingDays += "2";
            } else {
                workingDays += "0";
            }
            if (tuesday.equals("1")) {
                workingDays += "3";
            } else {
                workingDays += "0";
            }
            if (wednesday.equals("1")) {
                workingDays += "4";
            } else {
                workingDays += "0";
            }
            if (thursday.equals("1")) {
                workingDays += "5";
            } else {
                workingDays += "0";
            }
            if (friday.equals("1")) {
                workingDays += "6";
            } else {
                workingDays += "0";
            }
            if (saturday.equals("1")) {
                workingDays += "7";
            } else {
                workingDays += "0";
            }
            HttpSession session = request.getSession(true);
            UserSettingMaster.updateTiming(session.getAttribute("userKey").toString(),
                    workingDays, sundayFrom, sundayTo, mondayFrom, mondayTo, tuesdayFrom,
                    tuesdayTo, wednesdayFrom, wednesdayTo, thursdayFrom, thursdayTo, fridayFrom,
                    fridayTo, saturdayFrom, saturdayTo);
            out.println("done");
            MemoryTest.test("footer start");
                System.gc();
                MemoryTest.test("footer end");
//            response.sendRedirect("userAccountSettings.jsp");
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
