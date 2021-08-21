/*
 * 
 * 
 * 
 */

package supply.medium.home.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import supply.medium.home.database.GlobalProductItemMaster;
import supply.medium.utility.FileCopy;
import supply.medium.utility.SmProperties;
import supply.medium.utility.TestMemory;

/**
 *
 * @author Lenovo
 */
public class AddProductToCatalog extends HttpServlet {

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
            String companyKey=session.getAttribute("companyKey").toString();
            String itemName[]=request.getParameterValues("txtPname");
            String itemDesc[]=request.getParameterValues("txtPdesc");
            String partNo[]=request.getParameterValues("txtPpno");
            String SKUno[]=request.getParameterValues("txtPsku");
            String barcode[]=request.getParameterValues("txtPbrcd");
            String quantity[]=request.getParameterValues("txtPqty");
            String quantityKey[]=request.getParameterValues("txtPqtyunit");
            String price[]=request.getParameterValues("txtPprice");
            String currencyKey[]=request.getParameterValues("txtPcur");
            String tax[]=request.getParameterValues("txtPtax");
            String picsCount[]=request.getParameterValues("txtPfileNames");
            
//            String picsArr[]=null;
            String source=SmProperties.folderPath + "company-" + session.getAttribute("companyKey") + "\\products\\";
            String target=SmProperties.folderPath + "products\\";
            String fileNames[]=null;
            for(int i=0;i<itemName.length;i++)
            {
//                picsArr=picsCount[i].split(", ");
                //count=picsCount[i].split(", ").length;
                fileNames=picsCount[i].split(", ");
                GlobalProductItemMaster.insert(companyKey, itemName[i], itemDesc[i], partNo[i], SKUno[i], barcode[i], quantity[i], "PCS", price[i], "USD", tax[i], picsCount[i]);
                FileCopy.copyFiles(source, target, fileNames);
            }
            TestMemory.test("footer start");
                System.gc();
                TestMemory.test("footer end");
            response.sendRedirect("transactionsProductCatalog.jsp");
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
