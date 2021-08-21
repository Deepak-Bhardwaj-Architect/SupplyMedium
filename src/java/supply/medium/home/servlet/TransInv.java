/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supply.medium.home.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import supply.medium.home.bean.TransactionInvBean;
import supply.medium.home.bean.TransactionPoBean;
import supply.medium.home.database.NotificationMaster;
import supply.medium.home.database.TransactionInvItemMaster;
import supply.medium.home.database.TransactionInvMaster;
import supply.medium.home.database.TransactionPoItemMaster;
import supply.medium.home.database.TransactionPoMaster;
import supply.medium.home.database.UserMaster;
import supply.medium.home.pdf.GenerateINV;
import supply.medium.home.pdf.GeneratePO;
import supply.medium.utility.SmProperties;
import supply.medium.utility.TestMemory;

/**
 *
 * @author Lenovo
 */
public class TransInv extends HttpServlet {

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
            HttpSession session = request.getSession(true);
            String transKey = "" + System.currentTimeMillis();
            String invoice_due_date = request.getParameter("invoice_due_date");
            String non_po_invoice = request.getParameter("non_po_invoice");
            if (non_po_invoice == null) {
                non_po_invoice = "no";
            } else {
                non_po_invoice = "yes";
            }
            String is_diff_addrss = request.getParameter("is_diff_addrss");
            if (is_diff_addrss == null) {
                is_diff_addrss = "no";
            } else {
                is_diff_addrss = "yes";
            }
            String diff_addr_email = request.getParameter("diff_addr_email");
            String companyKeyFrom = session.getAttribute("companyKey").toString();
            String companyKeyTo = request.getParameter("companyKeyTo");
            String userKeyFrom = session.getAttribute("userKey").toString();
            String userKeyTo = UserMaster.showAdminKeyFromCompanyKey(companyKeyTo) + "";
            String transactionType = "Invoice";
            String qTransRfqKey = "0";
            String invNo = "INV" + transKey;
            String transStatus = "INV Sent";
            String transAction = "Pending";
            String quoteRef = "";

            String isOutside = request.getParameter("outside_supplier");
            String isOutsideAddress = "";

            if (isOutside != null) {
                isOutside = "yes";
                String outsideSupplierName = request.getParameter("otsd_splr_nm");
                String outsideSupplierCountry = request.getParameter("outsideSupplierCountry");
                String outsideSupplierState = request.getParameter("state_0");
                String outsideSupplierCity = request.getParameter("otsd_splr_cty");
                String outsideSupplierAddress = request.getParameter("otsd_splr_adrs");
                String outsideSupplierZipcode = request.getParameter("otsd_splr_zpcd");
                String outsideSupplierEmail = request.getParameter("email");
                isOutsideAddress = outsideSupplierName + "@#@#@" + outsideSupplierCountry + "@#@#@"
                        + outsideSupplierState + "@#@#@" + outsideSupplierCity + "@#@#@" + outsideSupplierAddress + "@#@#@"
                        + outsideSupplierZipcode + "@#@#@" + outsideSupplierEmail;

            } else {
                isOutside = "no";
            }
            String recurring = "";
            String totalAmount = request.getParameter("tot_list_price_amt");
            String taxPercent = request.getParameter("qt_tx");
            if (taxPercent == null) {
                taxPercent = "0";
            }
            String frei_hand_amt = request.getParameter("frei_hand_amt");
            String billingAmount = request.getParameter("tot_price_amt");
            String isPoCreated = "no";
            String billOfLanding = request.getParameter("bill_of_landing");
            String freightWeight = request.getParameter("freight_weight");
            if (freightWeight.trim().equals("")) {
                freightWeight = "0";
            }
            String quantityFreightUnit = request.getParameter("quantity_freight_unit");
            String dateShipped = request.getParameter("date_shipped");
            if (dateShipped.equals("")) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                dateShipped = formatter.format(new java.util.Date());
            }
            //System.out.println("dateShipped"+dateShipped);
            TransactionInvMaster.insertInv(transKey, companyKeyFrom, companyKeyTo,
                    userKeyFrom, userKeyTo, transactionType, qTransRfqKey,
                    invNo, transStatus, transAction, quoteRef, isOutside,
                    isOutsideAddress, recurring, totalAmount, taxPercent,
                    billingAmount, isPoCreated, frei_hand_amt, "0",
                    "0", invoice_due_date, invNo, "0",
                    billOfLanding, freightWeight, quantityFreightUnit, dateShipped,
                    "", non_po_invoice, is_diff_addrss, diff_addr_email);
            NotificationMaster.insert(userKeyFrom, userKeyTo, companyKeyFrom, companyKeyTo, "INV", transKey, "INV Genrated");
            String itemKey[] = request.getParameterValues("item_key");
            String partNo[] = request.getParameterValues("part_no");
            String barcode[] = request.getParameterValues("brcd_no");
            String quantity[] = request.getParameterValues("quantity");
            String quantityUnitKey[] = request.getParameterValues("quantityUnitKey");
            String shipedQuantity[] = request.getParameterValues("quantityShipped");
            String shipedQuantityUnitKey[] = request.getParameterValues("quantityUnitKey");
            String price[] = request.getParameterValues("price");
            String currencyKey[] = request.getParameterValues("currencyKey");
            String multiplier[] = request.getParameterValues("discount");
           // String allRfQItems[] = request.getParameter("allInvItems").trim().split("\\,");

            for (int i = 0; i < itemKey.length; i++) {
//                allRfQItems[i] = allRfQItems[i].substring(allRfQItems[i].indexOf("(") + 1, allRfQItems[i].lastIndexOf(")"));
//                String itemDetail[] = allRfQItems[i].split("\\^");
//                if (itemDetail.length == 10) {
//                    itemKey = itemDetail[0];
//                    partNo = itemDetail[1];
//                    barcode = itemDetail[2];
//                    quantity = itemDetail[3];
//                    quantityUnitKey = itemDetail[4];
//                    shipedQuantity = itemDetail[5];
//                    shipedQuantityUnitKey = itemDetail[6];
//                    price = itemDetail[7];
//                    currencyKey = itemDetail[8];
//                    multiplier = itemDetail[9];

                    TransactionInvItemMaster.insertInvItem(transKey, invNo, itemKey[i], partNo[i], barcode[i], quantity[i], quantityUnitKey[i], shipedQuantity[i], shipedQuantityUnitKey[i], "2014-10-10", price[i], currencyKey[i], multiplier[i]);
                //}

            }
            TransactionInvBean trb = TransactionInvMaster.showByKey(transKey);
            SmProperties.folderPath = request.getServletContext().getRealPath("") + File.separator + "cropData" + File.separator;
            GenerateINV.generate(SmProperties.folderPath, trb.getInv_trans_rqf_key(), trb.getTrans_key(), trb.getCompany_key_from(), trb.getCompany_key_to());
            TestMemory.test("footer start");
            System.gc();
            TestMemory.test("footer end");

            response.sendRedirect("transactionsInvoice.jsp");
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
