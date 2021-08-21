/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supply.medium.home.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import supply.medium.home.bean.TransactionInvBean;
import supply.medium.home.database.NotificationMaster;
import supply.medium.home.database.TransactionInvMaster;
import supply.medium.home.database.TransactionInvItemMaster;
import supply.medium.home.database.TransactionPoMaster;
import supply.medium.home.database.UserMaster;
import supply.medium.home.pdf.GenerateINV;
import supply.medium.utility.SmProperties;
import supply.medium.utility.MemoryTest;

/**
 *
 * @author Lenovo
 */
public class TransPoToInvoiceDecode extends HttpServlet {

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
            String transInvKey = "" + System.currentTimeMillis();            
            String invoice_due_date = request.getParameter("invoice_due_date");
            String non_po_invoice = request.getParameter("non_po_invoice");
            String sendEmail=request.getParameter("sendEmail");
            if (non_po_invoice == null) {
                non_po_invoice = "no";
            } else {
                non_po_invoice = "yes";
            }
            String is_diff_addrss = request.getParameter("is_diff_addrss");
            is_diff_addrss="no";
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
            String qteTransInvKey = request.getParameter("qTransRfqKey");
            String invNo = "INV" + transInvKey;
            String transStatus = "INV Sent";
            String transAction = "Pending";
            String quoteRef = request.getParameter("quoteRefNo");

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
            }String recurring = "";
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
            String dateShipped1 = request.getParameter("date_shipped");
            if (dateShipped1.equals("")) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                dateShipped1 = formatter.format(new java.util.Date());
            }
            TransactionInvMaster.insertInv(transInvKey, companyKeyFrom, companyKeyTo,
                    userKeyFrom, userKeyTo, transactionType, qteTransInvKey,
                    invNo, transStatus, transAction, quoteRef, isOutside,
                    isOutsideAddress, recurring, totalAmount, taxPercent,
                    billingAmount, isPoCreated, frei_hand_amt, "0",
                    "0", invoice_due_date, invNo, "0",
                    billOfLanding, freightWeight, quantityFreightUnit, dateShipped1,
                    "", non_po_invoice, is_diff_addrss, diff_addr_email);
            NotificationMaster.insert(userKeyFrom, userKeyTo, companyKeyFrom, companyKeyTo, "INV", transInvKey, "INV Generated");

            String itemKey[] = request.getParameterValues("item_key");
            String partNo[] = request.getParameterValues("part_no");
            String barcode[] = request.getParameterValues("brcd_no");
            String quantityOrdered[] = request.getParameterValues("quantity11");
            String quantityUnitKeyOrdered[] = request.getParameterValues("quantityUnitKey11");
            String quantity[] = request.getParameterValues("quantity");
            String quantityUnitKey[] = request.getParameterValues("quantityUnitKey");
            //String shipDate[] = request.getParameterValues("ship_date");
            String price[] = request.getParameterValues("price");
            String currencyKey[] = request.getParameterValues("currencyKey");
            String multiplier[] = request.getParameterValues("discount");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateShipped2 = formatter.format(new java.util.Date());
            for (int i = 0; i < itemKey.length; i++) {
                TransactionInvItemMaster.insertInvItem(transInvKey, invNo, itemKey[i], partNo[i], barcode[i], quantityOrdered[i], quantityUnitKeyOrdered[i],quantity[i], quantityUnitKey[i], dateShipped2, price[i], currencyKey[i], multiplier[i]);

            }
            TransactionInvBean trb = TransactionInvMaster.showByKey(transInvKey);
            SmProperties.folderPath = request.getRealPath("") + File.separator + "cropData" + File.separator;
            SmProperties.folderPath=SmProperties.folderPath.replace(File.separator+"app"+File.separator, File.separator+"zData"+File.separator);
            GenerateINV.generate(SmProperties.folderPath, trb.getInv_trans_rqf_key(), trb.getTrans_key(), trb.getCompany_key_from(), trb.getCompany_key_to(),sendEmail);
            TransactionPoMaster.updateInvGenerated(qteTransInvKey, "yes");
            MemoryTest.test("footer start");
            System.gc();
            MemoryTest.test("footer end");

            response.sendRedirect("transactionsInvoice.jsp");
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
