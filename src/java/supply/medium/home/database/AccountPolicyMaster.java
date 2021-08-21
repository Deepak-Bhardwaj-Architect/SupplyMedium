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
import supply.medium.home.bean.AccountPolicyBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class AccountPolicyMaster {

    public static int insert(String companyKey, String userKey) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into account_policy_master (company_key,user_key,"
                    + "enforce_password_history,"
                    + "max_password_age,min_password_age,min_password_length,"
                    + "send_email_before_password_expire,"
                    + "send_daily_reminder_after_date,password_complexity,"
                    + "upper_lower_case_letter,numerical_characters,"
                    + "non_alphanummeric_characters,account_lock_after_attempts,"
                    + "lockout_duration,reset_lockout_counter_after,"
                    + "account_unlocked_by_admin,session_end_time) "
                    + "values('" + companyKey + "','" + userKey + "','1','5000','1','7','3',"
                    + "'yes','yes','yes','yes','yes','5','10','5','yes','30')";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in AccountPolicyMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in AccountPolicyMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static ArrayList showAccountPolicy(String companyKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        AccountPolicyBean apb = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select * "
                    + "from account_policy_master "
                    + "where company_key='" + companyKey + "';";
            rs = st.executeQuery(query);
            if (rs.next()) {
                apb = new AccountPolicyBean();
                apb.setAccountPolicyKey(rs.getString(1));
                apb.setEnforcePasswordHistory(rs.getString(4));
                apb.setMaxPasswordAge(rs.getString(5));
                apb.setMinPasswordAge(rs.getString(6));
                apb.setMinPasswordLength(rs.getString(7));
                apb.setSendEmailBeforePasswordExpire(rs.getString(8));
                apb.setSendDailyReminderAfterDate(rs.getString(9));
                apb.setPasswordComplexity(rs.getString(10));
                apb.setUpperLowerCaseLetter(rs.getString(11));
                apb.setNumericalCharacters(rs.getString(12));
                apb.setNonAlphanummericCharacters(rs.getString(13));
                apb.setAccountLockAfterAttempts(rs.getString(14));
                apb.setLockoutDuration(rs.getString(15));
                apb.setResetLockoutCounterAfter(rs.getString(16));
                apb.setAccountUnlockedByAdmin(rs.getString(17));
                apb.setSessionEndTime(rs.getString(18));
                al = new ArrayList();
                al.add(apb);
                apb = null;
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showAccountPolicy in AccountPolicyMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showAccountPolicy in AccountPolicyMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static int update(String accountpolicyKey, String enforcePasswordHistory,
            String maxPasswordAge,String minPasswordAge,String minPasswordLength,
            String sendEmailBeforePasswordExpire,String sendDailyReminderAfterDate,
            String passwordComplexity,String upperLowerCaseLetter,String numericalCharacters,
            String nonAlphanummericCharacters,String accountLockAfterAttempts,
            String lockoutDuration,String resetLockoutCounterAfter,
            String accountUnlockedByAdmin,String sessionEndTime) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update account_policy_master set "
                    + "enforce_password_history='"+enforcePasswordHistory+"',"
                    + "max_password_age='"+maxPasswordAge+"',"
                    + "min_password_age='"+minPasswordAge+"',"
                    + "min_password_length='"+minPasswordLength+"',"
                    + "send_email_before_password_expire='"+sendEmailBeforePasswordExpire+"',"
                    + "send_daily_reminder_after_date='"+sendDailyReminderAfterDate+"',"
                    + "password_complexity='"+passwordComplexity+"',"
                    + "upper_lower_case_letter='"+upperLowerCaseLetter+"',"
                    + "numerical_characters='"+numericalCharacters+"',"
                    + "non_alphanummeric_characters='"+nonAlphanummericCharacters+"',"
                    + "account_lock_after_attempts='"+accountLockAfterAttempts+"',"
                    + "lockout_duration='"+lockoutDuration+"',"
                    + "reset_lockout_counter_after='"+resetLockoutCounterAfter+"',"
                    + "account_unlocked_by_admin='"+accountUnlockedByAdmin+"',"
                    + "session_end_time='"+sessionEndTime+"' "
                    + "where account_policy_key='"+accountpolicyKey+"'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at update in AccountPolicyMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing update in AccountPolicyMaster : " + ex.getMessage());
            }
        }
        return result;
    }
}
