/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any. - - - - - - - - - - -
 * - - - - - - - - - - - - - - - - - - - - - - - - - -
 */
package servlet.companyprofile;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.companyprofile.CompanyProfileData;
import core.login.SessionData;
import core.regn.CompanyRegnKey;
import ctrl.companyprofile.CompanyProfileLoaderController;
import utils.ErrorMaster;

/**
 * @FileName : CompanyProfileLoaderServlet.java
 *
 * @author : Anbazhagan
 *
 * @Date : Mar 13, 2013
 *
 * @Purpose : Load the Company Profile for the given Company Key
 *
 */
@WebServlet("/CompanyProfileLoaderServlet")
public class CompanyProfileLoaderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static ErrorMaster errorMaster_ = null;

    public CompanyProfileLoaderServlet() {
        super();
        if (errorMaster_ == null) {
            errorMaster_ = new ErrorMaster();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        errorMaster_.insert(" Profile Info Get Servlet Method ");
        errorMaster_.insert(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");

        CompanyProfileData profiledata = new CompanyProfileData();

        // Assuming the Session Object have the Attribute of companyKey
        CompanyRegnKey companyKey = new CompanyRegnKey();

        HttpSession session = request.getSession();

        SessionData sessionData = (SessionData) session.getAttribute("UserSessionData");

        errorMaster_.insert("session data=" + sessionData);

        companyKey.companyPhoneNo_ = sessionData.companyRegnKeyStr_;

        CompanyProfileLoaderController cmpController = new CompanyProfileLoaderController();

        int Result = cmpController.getCompanyInfo(companyKey, profiledata);

        errorMaster_.insert("servlet category=" + profiledata.businessCategory_);

        errorMaster_.insert("Servlet result=" + Result);

        switch (Result) {
            case 1400: // Success
                errorMaster_.insert("CompanyProfileLoaderServlet :Company Profile Succesfully Loadded");

                request.setAttribute("cmpProfile", profiledata);

                RequestDispatcher dispat = request.getRequestDispatcher("/Views/Registration/jsp/companyProfile.jsp");
                dispat.forward(request, response);
                break;
            case 1401: // No Record
                errorMaster_.insert("CompanyProfileLoaderServlet :Company Profile Not Found for the Given Key");
                break;

            case 1402: // Error
                errorMaster_.insert("CompanyProfileLoaderServlet :Error While Accessing the Comapany Profile");
                break;

        }

        errorMaster_.insert(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");

    }

}
