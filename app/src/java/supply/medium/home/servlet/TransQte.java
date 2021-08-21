/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.home.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import supply.medium.home.bean.TransactionQteBean;
import supply.medium.home.database.CurrencyMaster;
import supply.medium.home.database.NotificationMaster;
import supply.medium.home.database.QuantityTypeMaster;
import supply.medium.home.database.TransactionQteItemMaster;
import supply.medium.home.database.TransactionQteMaster;
import supply.medium.home.database.UserMaster;
import supply.medium.home.pdf.GenerateQTE;
import supply.medium.utility.SmProperties;
import supply.medium.utility.MemoryTest;

/**
 *
 * @author Lenovo
 */
public class TransQte extends HttpServlet {

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
            String transQteKey=""+System.currentTimeMillis();
            String companyKeyFrom = session.getAttribute("companyKey").toString();
            String companyKeyTo = request.getParameter("companyKeyTo");
            String userKeyFrom = session.getAttribute("userKey").toString();
            String userKeyTo = UserMaster.showAdminKeyFromCompanyKey(companyKeyTo)+"";
            String transactionType="Quote";
            String qTransRfqKey="0";
            String qteNo = "QTE" + transQteKey;
            String transStatus = "QTE Sent";
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
            }
            String recurring = request.getParameter("recurring");
            String totalAmount = request.getParameter("tot_list_price_amt");
            String taxPercent = request.getParameter("qt_tx");
            if(taxPercent==null)
                taxPercent="0";
            String billingAmount = request.getParameter("tot_price_amt");
            String isPoCreated = "no";

            transQteKey = TransactionQteMaster.insertQte(transQteKey,companyKeyFrom,companyKeyTo,
                    userKeyFrom,userKeyTo,transactionType,qTransRfqKey,qteNo, transStatus, transAction, quoteRef,
                    isOutside, isOutsideAddress,recurring,totalAmount,taxPercent,billingAmount,isPoCreated);
            NotificationMaster.insert(userKeyFrom, userKeyTo, companyKeyFrom, companyKeyTo, "Quote", transQteKey, "Quote Generated");
            String itemKey[] =request.getParameterValues("item_key");
            String partNo[] = request.getParameterValues("part_no");
            String barcode[] = request.getParameterValues("brcd_no");
            String quantity[] = request.getParameterValues("quantity");
            String quantityUnitKey[] = request.getParameterValues("quantityUnitKey");
            String shipDate[] = request.getParameterValues("ship_date");
            String price[] = request.getParameterValues("price");
            String currencyKey[] = request.getParameterValues("currencyKey");
            String multiplier[] = request.getParameterValues("discount");

            
            //String allRfQItems[] = request.getParameter("allQteItems").trim().split("\\,");

            for (int i = 0; i < itemKey.length; i++) {
//                allRfQItems[i] = allRfQItems[i].substring(allRfQItems[i].indexOf("(") + 1, allRfQItems[i].lastIndexOf(")"));
//                String itemDetail[] = allRfQItems[i].split("\\^");
//                if (itemDetail.length == 9) {
//                    itemKey = itemDetail[0];
//                    partNo = itemDetail[1];
//                    barcode = itemDetail[2];
//                    quantity = itemDetail[3];
//                    quantityUnitKey = itemDetail[4];
//                    shipDate = itemDetail[5];
//                    price = itemDetail[6];
//                    currencyKey = itemDetail[7];
//                    multiplier = itemDetail[8];
                                quantityUnitKey[i]=QuantityTypeMaster.showKeyByCode(quantityUnitKey[i]);
                currencyKey[i]=CurrencyMaster.showKeyByCode(currencyKey[i]);

                    TransactionQteItemMaster.insertQteItem(transQteKey, qteNo, itemKey[i], partNo[i], barcode[i], quantity[i], quantityUnitKey[i], shipDate[i],price[i],currencyKey[i],multiplier[i]);
               // }

            }
            TransactionQteBean trb = TransactionQteMaster.showByKey(transQteKey);
            SmProperties.folderPath = request.getRealPath("")+ File.separator + "cropData" + File.separator;
            SmProperties.folderPath=SmProperties.folderPath.replace(File.separator+"app"+File.separator, File.separator+"zData"+File.separator);
            GenerateQTE.generate(SmProperties.folderPath,trb.getQ_trans_rqf_key(),trb.getTrans_key() , trb.getCompany_key_from(), trb.getCompany_key_to());
            MemoryTest.test("footer start");
                System.gc();
                MemoryTest.test("footer end");

            response.sendRedirect("transactionsQuote.jsp");
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
