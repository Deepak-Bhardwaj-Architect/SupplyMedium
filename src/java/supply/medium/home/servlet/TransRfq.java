/*
 * 
 * 
 * 
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
import supply.medium.home.bean.TransactionRfqBean;
import supply.medium.home.database.FeedMaster;
import supply.medium.home.database.NotificationMaster;
import supply.medium.home.database.TransactionRfqItemMaster;
import supply.medium.home.database.TransactionRfqMaster;
import supply.medium.home.database.UserMaster;
import supply.medium.home.pdf.GenerateRFQ;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.SmProperties;
import supply.medium.utility.TestMemory;

/**
 *
 * @author Intel
 */
public class TransRfq extends HttpServlet {

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
            String transRfqKey = "" + System.currentTimeMillis();
            String rfqNo = "RFQ" + transRfqKey;
            String companyKeyFrom = session.getAttribute("companyKey").toString();
            String companyKeyTo = request.getParameter("companyKeyTo");
            String userKeyFrom = session.getAttribute("userKey").toString();
            String userKeyTo = UserMaster.showAdminKeyFromCompanyKey(companyKeyTo) + "";
            String transStatus = "RFQ Sent";
            String transAction = "Pending";
            String quoteRef = request.getParameter("quoteRef");
            String isOutside = request.getParameter("isOutside");
            String isOutsideAddress = "";
            String recurring = request.getParameter("recurring");
            String isQuoteCreated = "no";
            //out.printf("isOutside"+isOutside);
            if (isOutside != null) {
                isOutside = "yes";
                String outsideSupplierName = request.getParameter("outsideSupplierName");
                String outsideSupplierCountry = request.getParameter("outsideSupplierCountry");
                String outsideSupplierState = request.getParameter("state_0");
                String outsideSupplierCity = request.getParameter("outsideSupplierCity");
                String outsideSupplierAddress = request.getParameter("outsideSupplierAddress");
                String outsideSupplierZipcode = request.getParameter("outsideSupplierZipcode");
                String outsideSupplierEmail = request.getParameter("outsideSupplierEmail");
                isOutsideAddress = outsideSupplierName + "@#@#@" + outsideSupplierCountry + "@#@#@"
                        + outsideSupplierState + "@#@#@" + outsideSupplierCity + "@#@#@" + outsideSupplierAddress + "@#@#@"
                        + outsideSupplierZipcode + "@#@#@" + outsideSupplierEmail;

            } else {
                isOutside = "no";
            }

            String transRqfKey = TransactionRfqMaster.insertRfq(transRfqKey, rfqNo, companyKeyFrom, companyKeyTo, userKeyFrom, userKeyTo, transStatus, transAction, quoteRef, isOutside, isOutsideAddress, recurring, isQuoteCreated);
            NotificationMaster.insert(userKeyFrom, userKeyTo, companyKeyFrom, companyKeyTo, "RFQ", transRfqKey, "RFQ Generated");
            String itemKey[] = request.getParameterValues("item_key");
            String partNo[] = request.getParameterValues("item_part_no");
            String barcode[] = request.getParameterValues("item_barcode");
            String quantity[] = request.getParameterValues("item_quantity");
            String quantityUnitKey[] = request.getParameterValues("item_quantity_unit");
            String shipDate[] = request.getParameterValues("item_ship_date");

            //String allRfQItems[] = request.getParameter("allRfQItems").trim().split("\\,");
            for (int i = 0; i < itemKey.length; i++) {
                   // allRfQItems[i] = allRfQItems[i].substring(allRfQItems[i].indexOf("(") + 1, allRfQItems[i].lastIndexOf(")"));
                // String itemDetail[] = allRfQItems[i].split("\\^");
                //out.println("itemDetail"+itemDetail.length);
                // if (itemDetail.length == 6) {
//                    itemKey = itemDetail[0];
//                    partNo = itemDetail[1];
//                    barcode = itemDetail[2];
//                    quantity = itemDetail[3];
//                    quantityUnitKey = itemDetail[4];
//                    shipDate = itemDetail[5];
                TransactionRfqItemMaster.insertRfqItem(transRqfKey, rfqNo, itemKey[i], partNo[i], barcode[i], quantity[i], quantityUnitKey[i], shipDate[i]);
                // }

            }
            String rfqToFeed=request.getParameter("rfqToFeed");
            if(rfqToFeed.equals("yes"))
            {
                String fileDownloadRFQ="@#@#@"+companyKeyFrom+"@#@transaction@#@RFQ"+transRqfKey+".pdf";
                FeedMaster.insert("user feed", companyKeyFrom, userKeyFrom, "0", "no", "RFQ", "<a onclick=\"window.location.href=&#39;&#39;;\" target=\"_blank\" href=\""+fileDownloadRFQ+"\">Download RQF</a>", null);
            }
            TransactionRfqBean trb = TransactionRfqMaster.showByKey(transRqfKey);
            SmProperties.folderPath = request.getRealPath("") + File.separator + "cropData" + File.separator;
            GenerateRFQ.generate(SmProperties.folderPath, trb.getTransactionNo(), trb.getTransactionKey(), trb.getBuyerCompanyKey(), trb.getSupplierCompanyKey());
            TestMemory.test("footer start");
            System.gc();
            TestMemory.test("footer end");

            response.sendRedirect("transactions.jsp");
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at doPost() in TranRfq : " + ex.getMessage());
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
