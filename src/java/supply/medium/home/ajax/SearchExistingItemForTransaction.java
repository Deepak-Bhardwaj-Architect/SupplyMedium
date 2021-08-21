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
import javax.servlet.http.HttpSession;
import supply.medium.home.bean.GlobalProductItemBean;
import supply.medium.home.database.GlobalProductItemMaster;
import supply.medium.utility.TestMemory;

/**
 *
 * @author Lenovo
 */
public class SearchExistingItemForTransaction extends HttpServlet {

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
            HttpSession session=request.getSession(true);
            String stratValue=request.getParameter("startVal");
            String companyKey=session.getAttribute("companyKey").toString();
            ArrayList itemList = GlobalProductItemMaster.showSearchRecordsForTransaction(companyKey, stratValue);
            GlobalProductItemBean cb = null;
            String pic=null;
            int picCount=0;
            for (int i = 0; i < itemList.size(); i++) {
            cb = (GlobalProductItemBean) itemList.get(i);
            picCount=cb.getPicsCount().split("@#@").length;
            if(picCount==0 || cb.getPicsCount()==null || cb.getPicsCount().equals(""))
                pic="<img src='inside/no-product-image.jpg' "
                        + "style='margin:5px;padding:5px;border:1px solid #e5e5e5;' "
                        + "height='55' />";
            else
                pic="<img style='margin:5px;padding:5px;border:1px solid #e5e5e5;' "
                        + "height='55' "
                        + "src='cropData/products/"+cb.getPicsCount().split("@#@")[0].replace("@#@","")+"' />";
            
            out.print("<div id='"+cb.getCompanyKey()+"' class='filter_comp' "
                    + "style='cursor:pointer;background:#fff;height:auto;width:455px;'"
                    + "onclick=\"javascript:document.getElementById('resulted-data-head').style.display='block';"
                    + "document.getElementById('resulted-data').style.display='block';"
                    + "document.getElementById('selectedItemKey').value="+cb.getItemKey()+";"
                    + "document.getElementById('item_search_result').style.display='none';"
                    + "$('#popup_item_desc').val('"+cb.getItemName()+"');"
                    + "$('#lblin').html('"+cb.getItemName()+"');"
                    + "$('#lblid').html('"+cb.getItemDescription()+"');"
                    + "$('#lblpn').html('"+cb.getPartNo()+"');"
                    + "$('#lblsn').html('"+cb.getSKUno()+"');"
                    + "$('#lblbc').html('"+cb.getBarcode()+"');"
                    + " \">"
                    + "<table style='margin:0;'><tr>"
                    + "<td style='padding:0;'>"+pic+"</td>"
                    + "<td style='font-size: 12px;padding:0;'><b>Product Name : </b>"+cb.getItemName()+"<br/>"
                    + "<b>Description : </b>"+cb.getItemDescription()+"<br/>"
                    + "<b>Batch No. : </b>"+cb.getPartNo()+""
                    + "&nbsp;&nbsp;&nbsp;<b>SKU No. : </b>"+cb.getSKUno()+"<b>"
                    + "&nbsp;&nbsp;&nbsp;Barcode : </b>"+cb.getBarcode()+"<br/>"
                    + "</td></tr></table><br/></div>");
            }
            TestMemory.test("footer start");
                System.gc();
                TestMemory.test("footer end");
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
