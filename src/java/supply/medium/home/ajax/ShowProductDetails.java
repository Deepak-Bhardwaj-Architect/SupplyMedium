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
import supply.medium.home.bean.GlobalProductItemBean;
import supply.medium.home.database.CurrencyMaster;
import supply.medium.home.database.GlobalProductItemMaster;
import supply.medium.home.database.QuantityTypeMaster;
import supply.medium.utility.TestMemory;

/**
 *
 * @author Lenovo
 */
public class ShowProductDetails extends HttpServlet {

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
            String itemKey=request.getParameter("itemKey");
            GlobalProductItemBean pb=GlobalProductItemMaster.showByItemKey(itemKey);
            String result = "";
            result += "<div class='popupcontent'>"
                        + "<form action='UpdateProductDetails' id='ProductCatlogForm' method='post' novalidate='novalidate'>"
                            + "<div style='width:400px;float:left;'>"
                                + "<div class='div_row'>"
                                    + "<label class='popup_label' for='popup_item_name'> Item </label>"
                                    + "<input type='hidden' value='"+pb.getItemKey()+"' name='txtItemKey' />"
                                    + "<input id='popup_item_name' name='popup_item_name' readonly value='"+pb.getItemName()+"' type='text' autocomplete='off' class='catalog_textbox_popup textbox'>"
                                + "</div>"
                                + "<div class='div_row'>"
                                    + "<label class='popup_label' for='popup_item_description'> Description </label>"
                                    + "<input id='popup_item_description' value='"+pb.getItemDescription()+"' name='popup_item_description' type='text' autocomplete='off' class='catalog_textbox_popup textbox'>"
                                + "</div>"
                                + "<div class='div_row'>"
                                    + "<label class='popup_label' for='popup_item_part_no'> Part </label>"
                                    + "<input id='popup_item_part_no' value='"+pb.getPartNo()+"' name='popup_item_part_no' type='text' autocomplete='off' class='catalog_textbox_popup textbox'>"
                                + "</div>"
                                + "<div class='div_row'>"
                                    + "<label class='popup_label' for='popup_item_sku'> SKU </label>"
                                    + "<input id='popup_item_sku' name='popup_item_sku' value='"+pb.getSKUno()+"' type='text' autocomplete='off' class='catalog_textbox_popup textbox'>"
                                + "</div>"
                                + "<div class='div_row' style='position:relative;'>"
                                    + "<label class='popup_label' for='popup_item_quantity'> Quantity </label>"
                                    + "<input id='popup_item_quantity' value='"+pb.getQuantity()+"' style='width: 146px;top: 223px' name='popup_item_quantity' type='text' autocomplete='off' class='catalog_textbox_popup textbox'>"
                                + "<div class='currency' id='popup_quantity_unit'>"+QuantityTypeMaster.showCodeByKey(pb.getQuantityKey())
                                + "</div>"
                                + "<div class='currency_list' id='units_popup_quantity_unit' style='display:none;left:258px;'>"
                                + "</div>"
                                + "</div>"
                                + "<div class='div_row' style='position:relative;'>"
                                    + "<label class='popup_label' for='popup_item_price'> Price </label>"
                                    + "<input id='popup_item_price' style='width: 146px' value='"+pb.getPrice()+"' name='popup_item_price' type='text' autocomplete='off' class='catalog_textbox_popup textbox'>"
                                    + "<div class='currency' id='popup_currency'>"+CurrencyMaster.showCodeByKey(pb.getCurrencyKey())
                                    + "</div>"
                                    + "<div class='currency_list' id='popup_currency_popup_currency' style='display:none;left:258px;'>"
                                    + "</div>"
                                + "</div>"
                                + "<div class='div_row' style='margin-bottom:30px;'>"
                                    + "<label class='popup_label' for='popup_item_tax'> Tax </label>"
                                    + "<input id='popup_item_tax' name='popup_item_tax' value='"+pb.getTax()+"' type='text' autocomplete='off' class='catalog_textbox_popup textbox'>"
                                + "</div>"
                                + "<div class='div_row' style='display:none'>"
                                    + "<label class='popup_label' style='line-height: 18px'> Hide Price </label>"
                                    + "<div class='checkContainer'>"
                                        + "<input type='checkbox' class='checkbox' id='popup_HidePrice' value='false'>"
                                        + "<div>"
                                        + "</div>"
                                    + "</div>"
                                + "</div>"
                                + "<input style='margin-top:-5px;margin-left:140px;' type='button' value='Close' class='gen-btn-Gray' onclick=\"$('#ProdCatalogEdit').fadeOut();\">"
//                                + "<input style='margin-top:-5px;margin-left:20px;' type='submit' value='Save' class='gen-btn-Orange'>"
                            + "</div>"
                            + "<div style='width:400px;float:left;'><b>Product Images</b><br/>";
                    String pics[]=pb.getPicsCount().split(", ");
                    if(pics.length==0)
                     result += "<img src='inside/no-product-image.jpg' style='margin:10px;' title='"+pb.getItemName()+"' height='120' />";
                    else for(int i=0;i<pics.length;i++)
                     result += "<img src='cropData/products/"+pics[i].replace("@#@","")+"' style='margin:10px;' title='"+pb.getItemName()+"' height='120' />";
                     result += "</div>"
                        + "</form>"
                    + "</div>";
            out.println(result);
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
