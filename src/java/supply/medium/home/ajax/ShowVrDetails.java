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
import javax.servlet.http.HttpSession;
import supply.medium.home.bean.CompanyDetailsForVrBean;
import supply.medium.home.bean.InquiryBean;
import supply.medium.home.bean.VendorRegistrationBean;
import supply.medium.home.database.CompanyBusinessCategoryMaster;
import supply.medium.home.database.CompanyMaster;
import supply.medium.home.database.CountryMaster;
import supply.medium.home.database.InquiryMaster;
import supply.medium.home.database.StateMaster;
import supply.medium.home.database.VendorRegistrationMaster;
import supply.medium.utility.SmProperties;
import supply.medium.utility.TestMemory;

/**
 *
 * @author LenovoB560
 */
public class ShowVrDetails extends HttpServlet {

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
            String companyKey = session.getAttribute("companyKey").toString();
            String userKey = session.getAttribute("userKey").toString();
            String vrKey = request.getParameter("vrKey");
            VendorRegistrationBean vrb = VendorRegistrationMaster.showByVrKey(vrKey);
            CompanyDetailsForVrBean crb = null;
            if (companyKey.equals(vrb.getCompanyKeyFrom())) {
                crb = CompanyMaster.showCompanyDetailsForVrProcess(vrb.getCompanyKeyTo());
            } else if (companyKey.equals(vrb.getCompanyKeyTo())) {
                crb = CompanyMaster.showCompanyDetailsForVrProcess(vrb.getCompanyKeyFrom());
            }
            String businessCategory = CompanyBusinessCategoryMaster.showAllValuesByCompanyKey(crb.getCompanyKey());

            String result = "";
            result += "           <input type='hidden' name='vrKey' value='" + vrKey + "'>";
            result += "           <input type='hidden' id='vrAction' name='vrAction'>";
            result += "           <input type='hidden' id='toCompanyKey' name='toCompanyKey' value='" + crb.getCompanyKey() + "'>";
            result += "           <div class='ven_reg_left'>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> Company Name </label>";
            result += "                   <input type='text' id='company_name_popup' name='company_name' class='textbox_disable' disabled='' value='" + crb.getCompanyName() + "'>";
            result += "               </div>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> Branch </label>";
            result += "                   <input type='text' id='branch_0_popup' name='branch_0' class='textbox_disable' disabled='' value='" + crb.getBranch() + "'>";
            result += "               </div>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> Country/Region </label>";
            result += "                   <input type='text' id='countryregion_0_popup' name='countryregion_0' class='textbox_disable' disabled='' value='" + CountryMaster.showCountryFromKey(crb.getCountry()) + "'>";
            result += "               </div>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> Address </label>";
            result += "                   <input type='text' id='address_popup' name='address' class='textbox_disable' disabled='' value='" + crb.getAddress() + "'>";
            result += "               </div>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> City </label>";
            result += "                   <input type='text' id='city_popup' name='city' class='textbox_disable' disabled='' value='" + crb.getCity() + "'>";
            result += "               </div>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> State/Province </label>";
            result += "                   <input type='text' id='state_0_popup' name='state_0' class='textbox_disable' disabled='' value='" + StateMaster.showStateFromKey(crb.getState()) + "'>";
            result += "               </div>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> Zip Code/Postal Code </label>";
            result += "                   <input type='text' id='zipcode_popup' name='zipcode' class='textbox_disable' disabled='' value='" + crb.getZipCode() + "'>";
            result += "               </div>";

            result += "               <div style='height: 100px;' class='row' id='type'>";
            result += "                   <div class='label'>";
            result += "                       Type<span class='mandatory'>";
            result += "                       </span>";
            result += "                   </div>";
            result += "                   <fieldset>";
            result += "                       <legend> Type </legend>";
            if (vrb.getCompanyType().equals("Individual/Sole Proprietor")) {
                result += "                       <input type='radio' checked disabled='' value='Individual' class='radiobtn required' id='internetuser_popup' name='comtype' style='vertical-align: middle; font-size: 12px;margin:0px;line-height:25px;'>";
                result += "                       <label style='vertical-align: middle; font-size: 12px; color: #282828; line-height:25px;' for='internetuser'>";
                result += "                           Individual/Sole Proprietor &nbsp;</label>";
                result += "                       <br>";
                result += "                       <input type='radio' disabled='' value='Corporation' style='vertical-align: middle; font-size: 12px;margin:0px;line-height:25px;' class='radiobtn' name='comtype' id='transuser_popup'>";
                result += "                       <label style='vertical-align: middle; font-size: 12px; color: #282828;line-height:25px;' for='transuser'>";
                result += "                           Corporation, Partnerships, other&nbsp;</label>";
                result += "                       <label style='margin-left: 0px !important; width: 150px;' class='error' generated='true' for='usertype'>";
                result += "                       </label>";
            } else if (vrb.getCompanyType().equals("Individual/Sole Proprietor")) {
                result += "                       <input type='radio' disabled='' value='Individual' class='radiobtn required' id='internetuser_popup' name='comtype' style='vertical-align: middle; font-size: 12px;margin:0px;line-height:25px;'>";
                result += "                       <label style='vertical-align: middle; font-size: 12px; color: #282828; line-height:25px;' for='internetuser'>";
                result += "                           Individual/Sole Proprietor &nbsp;</label>";
                result += "                       <br>";
                result += "                       <input type='radio' checked disabled='' value='Corporation' style='vertical-align: middle; font-size: 12px;margin:0px;line-height:25px;' class='radiobtn' name='comtype' id='transuser_popup'>";
                result += "                       <label style='vertical-align: middle; font-size: 12px; color: #282828;line-height:25px;' for='transuser'>";
                result += "                           Corporation, Partnerships, other&nbsp;</label>";
                result += "                       <label style='margin-left: 0px !important; width: 150px;' class='error' generated='true' for='usertype'>";
                result += "                       </label>";
            }
            result += "                   </fieldset>";
            result += "               </div>";
            result += "           </div>";
            result += "           <div class='ven_reg_right'>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> Business Contact Name </label>";
            result += "                   <input type='text' id='contact_name_popup' name='cname' class='textbox_disable' disabled='' value='" + crb.getContactName() + "'>";
            result += "               </div>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> Contact Title/Department </label>";
            result += "                   <input type='text' id='titledept_popup' name='titledept' class='textbox_disable' disabled='' value='" + crb.getDepartment() + "'>";
            result += "               </div>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> Contact Email </label>";
            result += "                   <input type='text' id='email_popup' name='email' class='textbox_disable' disabled='' value='" + crb.getEmail() + "'>";
            result += "               </div>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> Phone </label>";
            result += "                   <input type='text' id='phone_popup' name='phone' class='textbox_disable' disabled='' value='" + crb.getPhone() + "'>";
            result += "               </div>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> Cell </label>";
            result += "                   <input type='text' id='cell_popup' name='cell' class='textbox_disable' disabled='' value='" + crb.getCell() + "'>";
            result += "               </div>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> Fax </label>";
            result += "                   <input type='text' id='fax_popup' name='fax' class='textbox_disable' disabled='' value='" + crb.getFax() + "'>";
            result += "               </div>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> Type of Business </label>";
            result += "                   <input type='text' id='typeofbusiness_popup' name='typeofbusiness' class='textbox_disable' disabled='' value='" + crb.getTypeOfBusiness() + "'>";
            result += "               </div>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> Business Category </label>";
            result += "                   <input type='text' id='businesscategory_popup' name='businesscategory' class='textbox_disable' disabled='' value='" + businessCategory + "'>";
            result += "               </div>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> Business Tax ID </label>";
            result += "                   <input type='text' id='taxid_popup' name='taxid' class='textbox' disabled='' value='" + vrb.getBusinessTaxId() + "'>";
            result += "               </div>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> NAICS Code </label>";
            result += "                   <input type='text' id='naicscode_popup_text' name='naicscode_popup_text' class='textbox' disabled='' value='" + vrb.getNaicsCode() + "'>";
            result += "               </div>";
            result += "               <div class='row'>";
            result += "                   <label class='label'> W9 Tax Form </label>";
            if(!vrb.getW9taxFormPath().trim().equals(""))
            {    
            result += "                   <a download target='_blank' href='" +SmProperties.urlPath+"company-"+session.getAttribute("companyKey")+"/w9forms/"+ vrb.getW9taxFormPath() + "' id='w9form_link' style='font-weight:underline;color:blue;'> Click here to download the W9Form</a>";
            }
            result += "               </div>";
            result += "           </div>";
            result += "           <div class='buss_size_container'>";
            result += "               <div class='side_heading'> Business Size ( Select One )";
            result += "               </div>";
            if (vrb.getBusinessSize().equals("Large")) {
                result += "               <div class='buss_size_large'>";
                result += "                   <div class='checkContainer'>";
                result += "                       <input type='radio' checked disabled='' name='buss_size_popup' id='buss_large_popup' value='Large'>";
                result += "                       <div>";
                result += "                       </div>";
                result += "                   </div>";
                result += "                   <label class='des_text' for='buss_large_popup'>";
                result += "                       <b>Large:<br>";
                result += "                       </b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A domestic concren which, including domestic and foreign divisions and affiliates, normally ";
                result += "                       employs 500 or more persons, is independently -or- publicly-owned or controlled and operated, ";
                result += "                       and which may be division of another domestic or foreign concern.</label>";
                result += "               </div>";
                result += "               <div class='buss_size_small'>";
                result += "                   <div class='checkContainer'>";
                result += "                       <input type='radio' disabled='' name='buss_size_popup' id='buss_small_popup' value='Small'>";
                result += "                       <div>";
                result += "                       </div>";
                result += "                   </div>";
                result += "                   <label class='des_text' for='buss_small_popup'>";
                result += "                       <b>Small:<br>";
                result += "                       </b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'Small business cocren' means a concren, including its affiliates, that is independently ";
                result += "                       owned and operated, not dominant in the field of operation in which it is bidding on government";
                result += "                       contracts, and qualified as a small business under the criteria in 13 CFR Part 121 and the";
                result += "                       size standard in FAR Clause 52.219-1, as well as the Small Business Act, Section 3.</label>";
                result += "               </div>";
            } else if (vrb.getBusinessSize().equals("Large")) {
                result += "               <div class='buss_size_large'>";
                result += "                   <div class='checkContainer'>";
                result += "                       <input type='radio' disabled='' name='buss_size_popup' id='buss_large_popup' value='Large'>";
                result += "                       <div>";
                result += "                       </div>";
                result += "                   </div>";
                result += "                   <label class='des_text' for='buss_large_popup'>";
                result += "                       <b>Large:<br>";
                result += "                       </b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A domestic concren which, including domestic and foreign divisions and affiliates, normally ";
                result += "                       employs 500 or more persons, is independently -or- publicly-owned or controlled and operated, ";
                result += "                       and which may be division of another domestic or foreign concern.</label>";
                result += "               </div>";
                result += "               <div class='buss_size_small'>";
                result += "                   <div class='checkContainer'>";
                result += "                       <input type='radio' checked disabled='' name='buss_size_popup' id='buss_small_popup' value='Small'>";
                result += "                       <div>";
                result += "                       </div>";
                result += "                   </div>";
                result += "                   <label class='des_text' for='buss_small_popup'>";
                result += "                       <b>Small:<br>";
                result += "                       </b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'Small business cocren' means a concren, including its affiliates, that is independently ";
                result += "                       owned and operated, not dominant in the field of operation in which it is bidding on government";
                result += "                       contracts, and qualified as a small business under the criteria in 13 CFR Part 121 and the";
                result += "                       size standard in FAR Clause 52.219-1, as well as the Small Business Act, Section 3.</label>";
                result += "               </div>";
            }
            result += "           </div>";
            result += "           <div class='buss_clari_container2'>";
            result += "               <div class='side_heading'> Business Classification per the Federal Acquisition Regulations. Section 2.101,";
            result += "                   where applicable: (Select all that apply)";
            result += "               </div>";
            result += "               <div class='checkContainer'>";
            result += "                   <input type='checkbox' ";
            if ((vrb.getBusinessClassification().charAt(0) + "").equals("1")) {
                result += "checked";
            }
            result += " disabled='' name='Disadvantaged' id='Disadvantaged_popup'>";
            result += "                   <div>";
            result += "                   </div>";
            result += "               </div>";
            result += "               <label class='des_text' for='Disadvantaged_popup'>";
            result += "                   <b>Disadvantaged:</b> In addition to meeting the requirements of the FAR definition, ";
            result += "                   any business classfying them selves as Disadvantaged must be certified by the Small Business ";
            result += "                   Administration and have that certification in good standing.";
            result += "               </label>";
            result += "               <div class='checkContainer'>";
            result += "                   <input type='checkbox' ";
            if ((vrb.getBusinessClassification().charAt(1) + "").equals("1")) {
                result += "checked";
            }
            result += " disabled='' name='HubZone' id='HubZone_popup'>";
            result += "                   <div>";
            result += "                   </div>";
            result += "               </div>";
            result += "               <label class='des_text' for='HubZone_popup'>";
            result += "                   <b>HUBZone:</b> In addition to meeting the requirements of the FAR definition, any business";
            result += "                   classifying themselves as a HUBZ one must be certified by the small Business Administraiotn and";
            result += "                   have that certification in good standing.";
            result += "               </label>";
            result += "               <div class='checkContainer'>";
            result += "                   <input type='checkbox' ";
            if ((vrb.getBusinessClassification().charAt(2) + "").equals("1")) {
                result += "checked";
            }
            result += " disabled='' name='WomenOwned' id='WomenOwned_popup'>";
            result += "                   <div>";
            result += "                   </div>";
            result += "               </div>";
            result += "               <label class='des_text' for='WomenOwned_popup'>Women-Owned";
            result += "               </label>";
            result += "               <div class='checkContainer'>";
            result += "                   <input type='checkbox' ";
            if ((vrb.getBusinessClassification().charAt(3) + "").equals("1")) {
                result += "checked";
            }
            result += " disabled='' name='HandicappedOwned' id='HandicappedOwned_popup'>";
            result += "                   <div>";
            result += "                   </div>";
            result += "               </div>";
            result += "               <label class='des_text' for='HandicappedOwned_popup'>handicapped-Owned";
            result += "               </label>";
            result += "               <div class='checkContainer'>";
            result += "                   <input type='checkbox' ";
            if ((vrb.getBusinessClassification().charAt(4) + "").equals("1")) {
                result += "checked";
            }
            result += " disabled='' name='VETERANOWNED' id='VETERANOWNED_popup'>";
            result += "                   <div>";
            result += "                   </div>";
            result += "               </div>";
            result += "               <label class='des_text' for='VETERANOWNED_popup'>Veteran-Owned";
            result += "               </label>";
            result += "               <div class='checkContainer'>";
            result += "                   <input type='checkbox' ";
            if ((vrb.getBusinessClassification().charAt(5) + "").equals("1")) {
                result += "checked";
            }
            result += " disabled='' name='SDVETERANOWNED' id='SDVETERANOWNED_popup'>";
            result += "                   <div>";
            result += "                   </div>";
            result += "               </div>";
            result += "               <label class='des_text' for='SDVETERANOWNED_popup'>Service-Disabled Veteran-Owned";
            result += "               </label>";
            result += "               <div class='checkContainer'>";
            result += "                   <input type='checkbox' ";
            if ((vrb.getBusinessClassification().charAt(6) + "").equals("1")) {
                result += "checked";
            }
            result += " disabled='' name='HBCORMI' id='HBCORMI_popup'>";
            result += "                   <div>";
            result += "                   </div>";
            result += "               </div>";
            result += "               <label class='des_text' for='HBCORMI_popup'>Historically-Back College or Minority Institution";
            result += "               </label>";
            result += "               <div class='checkContainer'>";
            result += "                   <input type='checkbox' ";
            if ((vrb.getBusinessClassification().charAt(7) + "").equals("1")) {
                result += "checked";
            }
            result += " disabled='' name='MBE' id='MBE_popup'>";
            result += "                   <div>";
            result += "                   </div>";
            result += "               </div>";
            result += "               <label class='des_text' for='MBE_popup'>Minority Business Enterprise";
            result += "               </label>";
            result += "               <div class='checkContainer'>";
            result += "                   <input type='checkbox' ";
            if ((vrb.getBusinessClassification().charAt(8) + "").equals("1")) {
                result += "checked";
            }
            result += " disabled='' name='NonProfit' id='NonProfit_popup'>";
            result += "                   <div>";
            result += "                   </div>";
            result += "               </div>";
            result += "               <label class='des_text' for='NonProfit_popup'>";
            result += "                   <b>Non-Profit:</b> Any business or organization that has received non-profit status";
            result += "                   under IRs Regulation 501(c)(3).";
            result += "               </label>";
            result += "               <div class='checkContainer'>";
            result += "                   <input type='checkbox' ";
            if ((vrb.getBusinessClassification().charAt(9) + "").equals("1")) {
                result += "checked";
            }
            result += " disabled='' name='Foreign' id='Foreign_popup'>";
            result += "                   <div>";
            result += "                   </div>";
            result += "               </div>";
            result += "               <label class='des_text' for='Foreign_popup'>";
            result += "                   <b>Foreign:</b> A concern which is not incorporated in the United States or an ";
            result += "                   unincorporated concern having its principle place of business outside the United States.";
            result += "               </label>";
            result += "               <div class='checkContainer'>";
            result += "                   <input type='checkbox' ";
            if ((vrb.getBusinessClassification().charAt(10) + "").equals("1")) {
                result += "checked";
            }
            result += " disabled='' name='PublicSector' id='PublicSector_popup'>";
            result += "                   <div>";
            result += "                   </div>";
            result += "               </div>";
            result += "               <label class='des_text' for='PublicSector_popup'>";
            result += "                   <b>Public Sector:</b> An agency of the Federal or State Government, or a local municipality.";
            result += "               </label>";
            result += "               <div class='checkContainer'>";
            result += "                   <input type='checkbox' ";
            if ((vrb.getBusinessClassification().charAt(11) + "").equals("1")) {
                result += "checked";
            }
            result += " disabled='' name='ANCORITNSB' id='ANCORITNSB_popup'>";
            result += "                   <div>";
            result += "                   </div>";
            result += "               </div>";
            result += "               <label class='des_text' for='ANCORITNSB_popup'>Alaska Native Corporations Indian Tripe that is not a small business.";
            result += "               </label>";
            result += "               <div class='checkContainer'>";
            result += "                   <input type='checkbox' ";
            if ((vrb.getBusinessClassification().charAt(12) + "").equals("1")) {
                result += "checked";
            }
            result += " disabled='' name='ANCORITNCD' id='ANCORITNCD_popup'>";
            result += "                   <div>";
            result += "                   </div>";
            result += "               </div>";
            result += "               <label class='des_text' for='ANCORITNCD_popup'>";
            result += "                   <b>Alaska Native Corporations or Indian Tribe:</b> Not certified by the Small Business Administration as disadvantaged.";
            result += "               </label>";
            result += "           </div>";
            result += "           <div class='addl_det_container'>";
            result += "               <div class='side_heading'> Additional Details";
            result += "               </div>";
            result += "               <textarea class='addr_det_text' id='additional_det_popup'>" + vrb.getAdditionalDetails() + "</textarea>";
            result += "           </div>";
            
            String display="none";
            String name="";
            ArrayList inquiryMessageList = InquiryMaster.showAllInquiryByTypeAndKey("VR", vrKey);
            InquiryBean ib = null;
            if(inquiryMessageList.size()>0)
            {
              display="block";  
            }
            for (int i = 0; i < inquiryMessageList.size(); i++) {
                ib = (InquiryBean) inquiryMessageList.get(i);
                               
                    name=CompanyMaster.getCompanyNameFromKey(ib.getInquire_from());
                 
                result += "           <div class='addl_det_container' id='inquiry_details' style='display:"+display+";'>";
                result += "                   <label class='inquire_by'> "+name+" </label>";
                result += "                   <textarea class='inquire_det' readonly>"+ib.getInquire_details()+"</textarea>";
                result += "           </div>";
            }
            
            result += "           <div class='addl_det_container' style='display:none;' id='add_inquiry_content'>";
            result += "               <div class='inquire_row' style='margin-bottom:20px;'>";
            result += "                   <label class='inquire_by'> Inquire: </label>";
            result += "                   <textarea class='inquire_det' id='new_inquire_message' name='inquireMessage'></textarea>";
            result += "               </div>";
            
            //forwarding to buyer page or supplier page transmission detail goes here
            result += "               <input type='hidden' value='Buyer' name='othersPageType' >";
//            result += "               <input type='hidden' value='Supplier' name='othersPageType' >";
            
            result += "               <input type='button' class='gen-btn-Orange' style=' margin-left:300px;' onclick=\"hideVREnquireBox();\" value='Cancel' id='cancel_btn'>";
            result += "               <input type='submit' class='gen-btn-Orange' style='margin-left:30px;' value='Send' id='save_btn' onclick=\"javascript:$('#vrAction').val('Enquired');return validateInquireMessage();\">";
            result += "           </div>";
            result += "           <div class='ven_reg_btns' id='cntrl_btns' style='margin-left:200px;width:630px;'>";
//                     result+="               <input type='button' class='gen-btn-Orange' style='margin-right:50px;' value='Cancel' id='cancel_regn_btn'>";
            if (!vrb.getUserKeyFrom().equals(userKey) && (vrb.getRequestStatus().equals("Form Sent")||vrb.getRequestStatus().equals("Enquired"))) {
                result += "               <input type='button' class='gen-btn-Orange' style='margin-right:50px;' value='Close' onclick=\"javascript:$('#supplier_form_popup').hide();\">";
                result += "               <input type='submit' class='gen-btn-Orange' style='margin-right:50px;' value='Accept' id='send_regn_btn' onclick=\"javascript:$('#vrAction').val('Accepted');\" >";
                result += "               <input type='submit' class='gen-btn-Orange' style='margin-right:50px;' value='Reject' id='reject_regn_btn' onclick=\"javascript:$('#vrAction').val('Rejected');\">";
                result += "               <input type='button' onclick=\"javascript:showVREnquireBox();\" class='gen-btn-Orange' style='width:130px!important;padding-left:10px!important;padding-right:10px!important;' value='Send Message' id='inquire_regn_btn'>";
            }
            else if (!vrb.getUserKeyTo().equals(userKey) && (vrb.getRequestStatus().equals("Form Sent")||vrb.getRequestStatus().equals("Enquired"))) {
                result += "               <input type='button' class='gen-btn-Orange' style='margin-right:50px;' value='Close' onclick=\"javascript:$('#supplier_form_popup').hide();\">";
                result += "               <input type='button' onclick=\"javascript:showVREnquireBox();\" class='gen-btn-Orange' style='width:130px!important;padding-left:10px!important;padding-right:10px!important;' value='Send Message' id='inquire_regn_btn'>";
            }
            else {
                result += "               <input type='button' class='gen-btn-Orange' style='margin-left:200px;margin-right:50px;' value='Close' onclick=\"javascript:$('#supplier_form_popup').hide();\">";
            }
            result += "           </div>";
//                     result+="           <div class='ven_reg_btns' id='cntrl_btns_ok'>";
//                     result+="               <input type='button' class='gen-btn-Orange' style='margin-right:50px;' value='ok' onclick='$('#supplier_form_popup').hide();'>";
//                     result+="           </div>";                     
            out.println(result);
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
