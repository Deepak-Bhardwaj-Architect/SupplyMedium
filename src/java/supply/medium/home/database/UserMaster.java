/*
 * 
 * 
 * 
 */
package supply.medium.home.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import supply.medium.home.bean.CompleteUserBean;
import supply.medium.home.bean.UserBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class UserMaster {

    public static int insert(String companyKey, String userType, String firstName,
            String lastName, String userPicPath, String title, String department,
            String managerSupervisor, String phone, String cell, String fax,
            String email, String password, String confirmationValue) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into "
                    + "user_master(company_key,user_type,first_name,"
                    + "last_name,user_pic_path,title,department,"
                    + "manager_supervisor,phone,cell,fax,email,pass_word,"
                    + "account_timestamp,account_status,account_activated_on,created_on) "
                    + "values('" + companyKey + "','" + userType + "','" + firstName + "',"
                    + "'" + lastName + "','" + userPicPath + "','" + title + "','" + department + "',"
                    + "'" + managerSupervisor + "','" + phone + "','" + cell + "','" + fax + "',"
                    + "'" + email + "','" + password + "',"
                    + "'" + confirmationValue + "','Pending',sysdate(),sysdate()) ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in UserMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in UserMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static int checkExistUserPhoneNo(String phoneNo) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select phone "
                    + "from user_master "
                    + "where phone='" + phoneNo + "';";
            rs = st.executeQuery(query);
            if (rs.next()) {
                result = 1;
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at checkExistUserPhoneNo in UserMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing checkExistUserPhoneNo in UserMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static int checkExistUserEmail(String email) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select email "
                    + "from user_master "
                    + "where email='" + email + "';";
            rs = st.executeQuery(query);
            if (rs.next()) {
                result = 1;
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at checkExistUserEmail in UserMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing checkExistUserEmail in UserMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static String getAdminEmail(String key) {
        String result = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select email "
                    + "from user_master "
                    + "where company_key='" + key + "';";
            rs = st.executeQuery(query);
            if (rs.next()) {
                result = rs.getString(1);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at getAdminEmail in UserMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing getAdminEmail in UserMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static int activateAccount(String email, String key) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update user_master "
                    + "set account_activated_on=sysdate(), "
                    + "account_status='Activated' "
                    + "where email='" + email + "' "
                    + "and account_timestamp='" + key + "';";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at activateAccount in UserMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing activateAccount in UserMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static int activateUserAccountAndPassword(String email, String key, String password) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update user_master "
                    + "set account_activated_on=sysdate(),"
                    + "pass_word='" + password + "',"
                    + "account_status='Activated' "
                    + "where email='" + email + "' "
                    + "and account_timestamp='" + key + "';";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at activateUserAccountAndPassword in UserMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing activateUserAccountAndPassword in UserMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static int showLastUserKeyByEmail(String email) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select user_key "
                    + "from user_master "
                    + "where email='" + email + "';";
            rs = st.executeQuery(query);
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showLastUserKeyByEmail in UserMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showLastUserKeyByEmail in UserMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static ArrayList showAllUserOfCompany(String companyKey, String userKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        CompleteUserBean cub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select um.user_key,um.first_name,um.last_name, um.email,"
                    + "um.account_status,umam.city,umam.state from user_master um "
                    + "join user_mailing_address_master umam on um.user_key=umam.user_key"
                    + " where um.company_key=" + companyKey + " and um.user_key!=" + userKey + "";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                cub = new CompleteUserBean();
                cub.setUserKey(rs.getString(1));
                cub.setFirstName(rs.getString(2));
                cub.setLastName(rs.getString(3));
                cub.setEmail(rs.getString(4));
                cub.setAccountStates(rs.getString(5));
                cub.setCity(rs.getString(6));
                cub.setState(rs.getString(7));
                al.add(cub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showAllUserOfCompany in UserMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showAllUserOfCompany in UserMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static ArrayList showAllUserOfCompanyByAsending(String companyKey, String userKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        CompleteUserBean cub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select um.user_key,um.first_name,um.last_name, um.email,"
                    + "um.account_status,umam.city,umam.state from user_master um "
                    + "join user_mailing_address_master umam on um.user_key=umam.user_key"
                    + " where um.company_key=" + companyKey + " and um.user_key!=" + userKey + " "
                    + "order by um.user_key asc";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                cub = new CompleteUserBean();
                cub.setUserKey(rs.getString(1));
                cub.setFirstName(rs.getString(2));
                cub.setLastName(rs.getString(3));
                cub.setEmail(rs.getString(4));
                cub.setAccountStates(rs.getString(5));
                cub.setCity(rs.getString(6));
                cub.setState(rs.getString(7));
                al.add(cub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showAllUserOfCompanyByAsending in UserMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showAllUserOfCompanyByAsending in UserMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static ArrayList showAllUserOfCompanyByDesending(String companyKey, String userKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        CompleteUserBean cub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select um.user_key,um.first_name,um.last_name, um.email,"
                    + "um.account_status,umam.city,umam.state from user_master um "
                    + "join user_mailing_address_master umam on um.user_key=umam.user_key"
                    + " where um.company_key=" + companyKey + " and um.user_key!=" + userKey + " "
                    + "order by um.user_key desc";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                cub = new CompleteUserBean();
                cub.setUserKey(rs.getString(1));
                cub.setFirstName(rs.getString(2));
                cub.setLastName(rs.getString(3));
                cub.setEmail(rs.getString(4));
                cub.setAccountStates(rs.getString(5));
                cub.setCity(rs.getString(6));
                cub.setState(rs.getString(7));
                al.add(cub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showAllUserOfCompanyByDesending in UserMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showAllUserOfCompanyByDesending in UserMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static ArrayList showUserDetailToAdminForSetting(String userKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        CompleteUserBean cub = null;
        ArrayList al = null;
        try {

            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select um.user_type,um.first_name,um.last_name,"
                    + "um.user_pic_path,um.title,um.department,"
                    + "um.manager_supervisor,um.phone,um.cell,"
                    + "um.fax,um.email,um.pass_word,um.account_status,umam.country,umam.address,"
                    + "umam.city,umam.state,umam.zipcode"
                    + " from user_master um "
                    + "join user_mailing_address_master umam on "
                    + "um.user_key=umam.user_key"
                    + " where um.user_key=" + userKey + " and "
                    + "um.user_key=" + userKey + "";
            rs = st.executeQuery(query);
            al = new ArrayList();
            if (rs.next()) {
                cub = new CompleteUserBean();
                cub.setUserType(rs.getString(1));
                cub.setFirstName(rs.getString(2));
                cub.setLastName(rs.getString(3));
                cub.setUserPicPath(rs.getString(4));
                cub.setTitle(rs.getString(5));
                cub.setDepartment(rs.getString(6));
                cub.setManagerSupervisor(rs.getString(7));
                cub.setPhone(rs.getString(8));
                cub.setCell(rs.getString(9));
                cub.setFax(rs.getString(10));
                cub.setEmail(rs.getString(11));
                cub.setPassword(rs.getString(12));
                cub.setAccountStates(rs.getString(13));
                cub.setCountry(rs.getString(14));
                cub.setAddress(rs.getString(15));
                cub.setCity(rs.getString(16));
                cub.setState(rs.getString(17));
                cub.setZipcode(rs.getString(18));
                al.add(cub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showUserDetailToAdminForSetting in UserMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showUserDetailToAdminForSetting in UserMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static ArrayList showNonExistingUserInGroup(String companyKey, String groupKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        UserBean ub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "SELECT user_key,first_name,last_name FROM user_master where company_key='" + companyKey + "' and "
                    + "user_key not in(SELECT user_key FROM group_user_master where group_key='" + groupKey + "')";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                ub = new UserBean();
                ub.setUserKey(rs.getString(1));
                ub.setFirstName(rs.getString(2));
                ub.setLastName(rs.getString(3));
                al.add(ub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showNonExistingUserInGroup in UserMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showNonExistingUserInGroup in UserMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static ArrayList showExistingUserInGroup(String companyKey, String groupKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        UserBean ub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "SELECT user_key,first_name,last_name FROM user_master where company_key='" + companyKey + "' and "
                    + "user_key in(SELECT user_key FROM group_user_master where group_key='" + groupKey + "')";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                ub = new UserBean();
                ub.setUserKey(rs.getString(1));
                ub.setFirstName(rs.getString(2));
                ub.setLastName(rs.getString(3));
                al.add(ub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showExistingUserInGroup in UserMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showExistingUserInGroup in UserMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static ArrayList showNonExistingUserInDepartment(String companyKey, String departmentKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        UserBean ub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "SELECT user_key,first_name,last_name FROM user_master where company_key='" + companyKey + "' and "
                    + "user_key not in(SELECT user_key FROM department_user_master where company_key='" + companyKey + "')";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                ub = new UserBean();
                ub.setUserKey(rs.getString(1));
                ub.setFirstName(rs.getString(2));
                ub.setLastName(rs.getString(3));
                al.add(ub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showNonExistingUserInDepartment in UserMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showNonExistingUserInDepartment in UserMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static ArrayList showExistingUserInDepartment(String companyKey, String departmentKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        UserBean ub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "SELECT user_key,first_name,last_name FROM user_master where company_key='" + companyKey + "' and "
                    + "user_key in(SELECT user_key FROM department_user_master where department_key='" + departmentKey + "')";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                ub = new UserBean();
                ub.setUserKey(rs.getString(1));
                ub.setFirstName(rs.getString(2));
                ub.setLastName(rs.getString(3));
                al.add(ub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showExistingUserInDepartment in UserMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showExistingUserInDepartment in UserMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static UserBean showUserDetailsByUserKey(String userKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        UserBean ub = ub = new UserBean();
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "SELECT user_key,first_name,last_name "
                    + "FROM user_master "
                    + "where user_key='" + userKey + "' "
                    + "or email='" + userKey + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                ub.setUserKey(rs.getString(1));
                ub.setFirstName(rs.getString(2));
                ub.setLastName(rs.getString(3));
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showUserDetailsByUserKey in UserMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showUserDetailsByUserKey in UserMaster : " + ex.getMessage());
            }
        }
        return ub;
    }

    public static int updateUserStatusByAdmin(String userKey, String status) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update user_master "
                    + "set account_activated_on=sysdate(), "
                    + "account_status='" + status + "' "
                    + "where user_key='" + userKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at updateUserStatusByAdmin in UserMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing updateUserStatusByAdmin in UserMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static int updateUserSettingByAdmin(String userKey, String firstName,
            String lastName, String phone, String mobile, String fax, String userType,
            String department, String userRole) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update user_master "
                    + "set first_name='" + firstName + "',last_name='" + lastName + "',"
                    + "phone='" + phone + "',cell='" + mobile + "',fax='" + fax + "',"
                    + "user_type='" + userType + "',department='" + department + "',"
                    + "manager_supervisor='" + userRole + "'"
                    + "where user_key='" + userKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at updateUserSettingByAdmin in UserMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing updateUserSettingByAdmin in UserMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static int deleteUser(String userkey) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "delete from user_master "
                    + "where user_key='" + userkey + "';";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at deleteUser in UserMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing deleteUser in UserMaster : " + ex.getMessage());
            }
        }
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "delete from user_mailing_address_master "
                    + "where user_key='" + userkey + "';";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at deleteUser in UserMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing deleteUser in UserMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static int updateUserPassword(String userKey, String password) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update user_master "
                    + "set pass_word='" + password + "'"
                    + "where user_key='" + userKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at updateUserPassword in UserMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing updateUserPassword in UserMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static int showAdminKeyFromCompanyKey(String companyKey) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select user_key from user_master where user_type='Admin' "
                    + "and company_key='" + companyKey + "';";
            rs = st.executeQuery(query);
//            System.out.println(query);
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showAdminKeyFromCompanyKey in UserMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showAdminKeyFromCompanyKey in UserMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static ArrayList showAllUser(String value,String userKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        UserBean ub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select user_key,first_name,last_name,email,"
                    + "company_master.company_key,company_name "
                    + "from user_master,company_master "
                    + "where company_master.company_key=user_master.company_key "
                    + "and user_key not in('"+userKey+"') "
                    + "and (first_name like '" + value + "%' "
                    + "or last_name like '" + value + "%' "
                    + "or email like '" + value + "%' "
                    + "or company_name like '" + value + "%')";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                ub = new UserBean();
                ub.setUserKey(rs.getString(1));
                ub.setFirstName(rs.getString(2));
                ub.setLastName(rs.getString(3));
                ub.setEmail(rs.getString(4));
                ub.setCompanyKey(rs.getString(5));
                ub.setDepartment(rs.getString(6));
                al.add(ub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showExistingUserInDepartment in UserMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showExistingUserInDepartment in UserMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static UserBean showUserDetails(String userKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        UserBean ub = new UserBean();
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select first_name,last_name,email,department,manager_supervisor,"
                    + "company_master.company_key,company_name,company_type,phone,cell,fax "
                    + "from user_master,company_master where company_master.company_key=user_master.company_key "
                    + "and user_key='"+userKey+"'";
//            ErrorMaster.insert(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
                ub.setFirstName(rs.getString(1));
                ub.setLastName(rs.getString(2));
                ub.setEmail(rs.getString(3));
                ub.setDepartment(rs.getString(4));
                ub.setManagerSupervisor(rs.getString(5));
                ub.setCompanyKey(rs.getString(6));
                ub.setTitle(rs.getString(7));
                ub.setUserPicPath(rs.getString(8));
                ub.setPhone(rs.getString(9));
                ub.setCell(rs.getString(10));
                ub.setFax(rs.getString(11));
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showUserDetails in UserMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showUserDetails in UserMaster : " + ex.getMessage());
            }
        }
        return ub;
    }

}
