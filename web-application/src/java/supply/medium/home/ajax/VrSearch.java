/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supply.medium.home.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supply.medium.home.bean.CompanyBean;
import supply.medium.home.database.UserMaster;
import supply.medium.home.database.VrSearchMaster;
import supply.medium.utility.MemoryTest;

/**
 *
 * @author Lenovo
 */
public class VrSearch extends HttpServlet {

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
            String actionType = request.getParameter("actionType");
            String businessType = request.getParameter("businessType");
            String companyName = request.getParameter("companyName");
            String businessKey = request.getParameter("businessKey");
            String companyKey = request.getParameter("companyKey");
            String vrType = request.getParameter("actionType");
            String result = "";
            int count = 0;
            if (businessType.equals("inNetwork")) {
                ArrayList vrList = VrSearchMaster.showCompaniesInNetwork(companyName, businessKey, companyKey, vrType);
                CompanyBean cb = null;
                for (int i = 0; i < vrList.size(); i++) {
                    cb = (CompanyBean) vrList.get(i);
                    result += "<div class='mCSB_container mCS_no_scrollbar' style='position: relative; top: 0px;'>";
                    result += "<div class='searchRow oddrow' id='" + cb.getCompanyKey() + "'>";
                    result += "<div class='searchCol1'>" + i + "</div>";
                    result += "<div class='searchCol2'>";
                    result += "<div class='checkContainer'>";
                    result += "<input type='checkbox' id='1234567chk'>";
                    result += "<div>";
                    result += "</div>";
                    result += "</div>";
                    result += "</div>";
                    result += "<div class='searchCol3'>";
                    result += "<ul>";
                    result += "<li class='searchCmpName'>" + cb.getCompanyName() + "</li>";
                    result += "<li class='searchCmpAddr' style='cursor:pointer;' onclick='rmv_frm_ntwrk('1234567','Supplier');'></li>";
                    result += "<li><span class='searchRattings'>Admin e-mail : "+UserMaster.getAdminEmail(cb.getCompanyKey())+"</span></li>";
                    result += "</ul>";
                    result += "</div>";
                    result += "</div>";
                    result += "</div>";
                }
                out.println(result);
                count += vrList.size();
                //out.println("<input type='hidden' id='in_network_advance_search_count' value='"+vrList.size()+"'");
            } else if (businessType.equals("outNetwork")) {
                result = "<input type='hidden' id='selected_out_network_id' />";
                result += "<input type='hidden' id='selected_out_network_name' />";
                ArrayList vrList = VrSearchMaster.showCompaniesOutOfNetwork(companyName, businessKey, companyKey, vrType);
                CompanyBean cb = null;
                for (int i = 0; i < vrList.size(); i++) {
                    cb = (CompanyBean) vrList.get(i);
                    result += "<div class='mCSB_container mCS_no_scrollbar' style='position: relative; top: 0px;'>";
                    result += "<div class='searchRow oddrow' id='" + cb.getCompanyKey() + "'>";
                    result += "<div class='searchCol1'>" + (i + 1) + "</div>";
                    result += "<div class='searchCol2'>";
                    result += "<div class='checkContainer'>";
                    result += "<input type='checkbox' id='1234567chk' onclick=\"javascript:$('#selected_out_network_id').val('" + cb.getCompanyKey() + "');$('#selected_out_network_name').val('" + cb.getCompanyName() + "');\">";
                    result += "<div>";
                    result += "</div>";
                    result += "</div>";
                    result += "</div>";
                    result += "<div class='searchCol3'>";
                    result += "<ul>";
                    result += "<li class='searchCmpName'>" + cb.getCompanyName() + "</li>";
                    result += "<li class='searchCmpAddr' style='cursor:pointer;' onclick='rmv_frm_ntwrk('1234567','Supplier');'></li>";
                    result += "<li><span class='searchRattings'>Admin e-mail : "+UserMaster.getAdminEmail(cb.getCompanyKey())+"</span></li>";
                    result += "</ul>";
                    result += "</div>";
                    result += "</div>";
                    result += "</div>";
                }
                out.println(result);
                count += vrList.size();
                //out.println("<input type='hidden' id='out_network_advance_search_count' value='"+vrList.size()+"'");
            }

            String country = request.getParameter("country");
            String part_number = request.getParameter("part_number");
            String product_name = request.getParameter("product_name");
            if (businessType.equals("inNetworkAdvance")) {
                ArrayList vrList = VrSearchMaster.showCompaniesInNetworkInAdvanceSearch(companyKey, vrType, businessKey, country, part_number, product_name);
                CompanyBean cb = null;
                for (int i = 0; i < vrList.size(); i++) {
                    cb = (CompanyBean) vrList.get(i);
                    result += "<div class='mCSB_container mCS_no_scrollbar' style='position: relative; top: 0px;'>";
                    result += "<div class='searchRow oddrow' id='" + cb.getCompanyKey() + "'>";
                    result += "<div class='searchCol1'>" + (i + 1) + "</div>";
                    result += "<div class='searchCol2'>";
                    result += "<div class='checkContainer'>";
                    result += "<input type='checkbox' id='1234567chk'>";
                    result += "<div>";
                    result += "</div>";
                    result += "</div>";
                    result += "</div>";
                    result += "<div class='searchCol3'>";
                    result += "<ul>";
                    result += "<li class='searchCmpName'>" + cb.getCompanyName() + "</li>";
                    result += "<li class='searchCmpAddr' style='cursor:pointer;' onclick='rmv_frm_ntwrk('1234567','Supplier');'></li>";
                    result += "<li><span class='searchRattings'>Admin e-mail : "+UserMaster.getAdminEmail(cb.getCompanyKey())+"</span></li>";
                    result += "</ul>";
                    result += "</div>";
                    result += "</div>";
                    result += "</div>";
                }
                out.println(result);
                count += vrList.size();
                out.println("<input type='hidden' id='in_advance_search_count' value='" + count + "'>");
            } else if (businessType.equals("outNetworkAdvance")) {
                ArrayList vrList = VrSearchMaster.showCompaniesOutOfNetworkInAdvanceSearch(companyKey, vrType, businessKey, country, part_number, product_name);
                CompanyBean cb = null;
                for (int i = 0; i < vrList.size(); i++) {
                    cb = (CompanyBean) vrList.get(i);
                    result += "<div class='mCSB_container mCS_no_scrollbar' style='position: relative; top: 0px;'>";
                    result += "<div class='searchRow oddrow' id='" + cb.getCompanyKey() + "'>";
                    result += "<div class='searchCol1'>" + (i + 1) + "</div>";
                    result += "<div class='searchCol2'>";
                    result += "<div class='checkContainer'>";
                    result += "<input type='checkbox' id='1234567chk'>";
                    result += "<div>";
                    result += "</div>";
                    result += "</div>";
                    result += "</div>";
                    result += "<div class='searchCol3'>";
                    result += "<ul>";
                    result += "<li class='searchCmpName'>" + cb.getCompanyName() + "</li>";
                    result += "<li class='searchCmpAddr' style='cursor:pointer;' onclick='rmv_frm_ntwrk('1234567','Supplier');'></li>";
                    result += "<li><span class='searchRattings'>Admin e-mail : "+UserMaster.getAdminEmail(cb.getCompanyKey())+"</span></li>";
                    result += "</ul>";
                    result += "</div>";
                    result += "</div>";
                    result += "</div>";
                }
                out.println(result);
                count += vrList.size();
                out.println("<input type='hidden' id='out_advance_search_count' value='" + count + "'>");
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
