package ctrl.regn;

import core.notification.NotificationCenterData;
import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import core.regn.UserProfileData;
import core.regn.UserProfileFind;
import core.regn.UserProfileKey;
import core.regn.UserSignupFactory;
import core.regn.UserSignupProcess;
import db.notification.NotificationTable;
import db.regn.CompanyRegnTable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import utils.ErrorLogger;
import utils.ErrorMaster;

/*
 * This is the controller class user signup process. It get the 
 * call from servlet and forward that call to corresponding core classes.
 */
public class UserSignupController {

    private ErrorLogger errLogger_;

    /*
     * Method : UserSignupController( ) - constructor
     * 
     * Input :none
     * 
     * Return :none
     * 
     * Purpose: It is used intialize the ErrorLogger object
     */
    public UserSignupController() {

        errLogger_ = ErrorLogger.instance();
    }

    /*
     * Method : processRequest( ) Input : HttpServletRequest object Return :
     * String - success or failed message
     * 
     * Purpose: It is used to get the HttpServletrequest object as a parameter.
     * Then parse the request object to user signup data object. From the
     * requesttype parameter it decide what type of request is. And do the
     * cooresponding operation using UserSignupFactory and UserSignupProcess
     * classes.
     * 
     * The request types are 1. "AddUser" request 2. "UserSignup" request 3.
     * "DeleteUser" request 4. "AllUsers" request 5. "UpdateUser" request
     */
    public int processRequest(HttpServletRequest request) {

        UserProfileData profileDataObj = new UserProfileData();

        UserSignupDataConverter signupConverter = new UserSignupDataConverter();

        // Convert to profileDataObj object from HttpServletRequest
        int parseResponse = signupConverter.convert(request, profileDataObj);

        signupConverter = null;

        if (parseResponse != 0) {
            String errMsg = "Error::UserSignupController.processRequest() - Can't parse the "
                    + "User signup request";

            errLogger_.logMsg(errMsg);

            profileDataObj = null;

            return 301;  // user signup failed.
        }

        String requestType = request.getParameter("RequestType");

        return processUserSignupReq(profileDataObj, requestType);

    }

    /*
     * Method: processUserSignupReq
     * 
     * Input:
     * 
     * Return:
     * 
     * Purpose: This is the helper for processRequest method
     * 
     */
    public int processUserSignupReq(UserProfileData userSignupData, String requestType) {
        ErrorMaster errorMaster_ = null;
        if (errorMaster_ == null) {
            errorMaster_ = new ErrorMaster();
        }

        UserSignupFactory userSignupFactoryObj = new UserSignupFactory();

        UserSignupProcess userSignupProcessObj = userSignupFactoryObj
                .createRegnObj(requestType);

        userSignupFactoryObj = null;

        if (userSignupProcessObj == null) {
            String errMsg = "Error::UserSignupController.processRequest() - Request type error in "
                    + "User signup request ";

            errLogger_.logMsg(errMsg);

            errorMaster_.insert("process User Signup req End");
            return 301;  // user signup failed
        }

        try {

            int regnResultCode = 0;

			//profileDataObj.show( );
            String errMsg = "Info::UserSignupController.processRequest() - "
                    + requestType + " in process ";

            errLogger_.logMsg(errMsg);

            regnResultCode = userSignupProcessObj.process(userSignupData);
            
            if(requestType.equals("NewSignup"))
            {
            /* Send notification when new user added */
            NotificationTable notificationTable = new NotificationTable();

            NotificationCenterData notiCenterData = new NotificationCenterData();

            RegnData regnData = new RegnData();

            CompanyRegnTable regnTbl = new CompanyRegnTable();

            int result = regnTbl.find(userSignupData.companyRegnKey_, regnData);

            UserProfileKey companyProfileKey = new UserProfileKey();

        //System.out.println("userSignupData.emailId_"+userSignupData.emailId_);
            companyProfileKey.email_ = regnData.emailId_;

        //System.out.println("companyProfileKey.email_"+companyProfileKey.email_);
        //System.out.println("companyProfileKey.email_"+companyProfileKey.email_);
            UserProfileKey newUserProfileKey = new UserProfileKey();
            newUserProfileKey.email_ = userSignupData.emailId_;

        //System.out.println("newUserProfileKey.email_"+newUserProfileKey.email_);
            CompanyRegnKey newUserRegnKey = new CompanyRegnKey();
            newUserRegnKey.companyPhoneNo_ = userSignupData.phoneNo_ + "";

            notiCenterData.receiverKey_ = companyProfileKey;

            notiCenterData.receiverRegnKey_ = userSignupData.companyRegnKey_;

            notiCenterData.senderKey_ = newUserProfileKey;

            notiCenterData.senderRegnKey_ = newUserRegnKey;

            notiCenterData.notificationType_ = "User";

            notiCenterData.notificationTypeId_ = "1";

            notiCenterData.notificationMessage_ = userSignupData.firstName_ + " "
                    + userSignupData.lastName_ + " requested you to add ";

            // Insert new notification
            notificationTable.insert(notiCenterData);
            }

            userSignupData = null;

            userSignupProcessObj = null;
            errorMaster_.insert("try block End");
            return regnResultCode;

        } catch (Exception ex) {

            errLogger_.logMsg("Exception::UserSignupController.processRequest() - " + ex);

            userSignupData = null;

            userSignupProcessObj = null;
            ;
            return 301;  // user signup failed
        }
    }

    public int getAllUsers(HttpServletRequest request, List< UserProfileData> userProfileList) {

        UserProfileFind findUsers = new UserProfileFind();

		//List<UserProfileData> userProfileList = new ArrayList<UserProfileData>( );
        CompanyRegnKey key = new CompanyRegnKey();

        key.companyPhoneNo_ = request.getParameter("CompanyRegnKey");

        String errMsg = "Info::UserSignupController.getAllUsers() - Get All Users Request In Process "
                + "for company <" + request.getParameter("CompanyRegnKey") + ">";

        errLogger_.logMsg(errMsg);

        int response = findUsers.getUsers(key, userProfileList);

        return response;
    }

}
