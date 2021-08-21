/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.home.database;

import java.sql.*;
import supply.medium.home.bean.BankInfoBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class BankInfoMaster {

    public static int insert(String companyKey,String acName,String acNo,String swiffCode,
            String ifscCode,String branchAddress,String paypalName,String paypalEmail)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "bank_master "
                    + "values('"+companyKey+"','"+acName+"','"+acNo+"',"
                    + "'"+swiffCode+"','"+ifscCode+"','"+branchAddress+"','"+paypalName+"','"+paypalEmail+"') ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in BankInfoMaster : "+ex.getMessage());
        }
        finally
        {
            try
            {
                st.close();
                con.close();
            }
            catch(Exception ex)
            {
                ErrorMaster.insert("Exception at closing insert in BankInfoMaster : "+ex.getMessage());
            }
        }
        return result;
    }

    public static int delete(String companyKey)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="delete from "
                    + "bank_master "
                    + "where company_key='"+companyKey+"'";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at delete in BankInfoMaster : "+ex.getMessage());
        }
        finally
        {
            try
            {
                st.close();
                con.close();
            }
            catch(Exception ex)
            {
                ErrorMaster.insert("Exception at closing delete in BankInfoMaster : "+ex.getMessage());
            }
        }
        return result;
    }

    public static int update(String companyKey,String acName,String acNo,String swiffCode,
            String ifscCode,String branchAddress,String paypalName,String paypalEmail)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            delete(companyKey);
            result=insert(companyKey, acName, acNo, swiffCode, ifscCode, branchAddress, paypalName, paypalEmail);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at update in BankInfoMaster : "+ex.getMessage());
        }
        finally
        {
            try
            {
                st.close();
                con.close();
            }
            catch(Exception ex)
            {
                ErrorMaster.insert("Exception at closing update in BankInfoMaster : "+ex.getMessage());
            }
        }
        return result;
    }

    public static int showBankInfo(String companyKey)
    {
        int result=0;Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select count(*) "
                    + "from bank_master where company_key='"+companyKey+"'";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                result=rs.getInt(1);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showBankInfo in BankInfoMaster : "+ex.getMessage());
        }
        finally
        {            
            try
            {
                rs.close();
                st.close();
                con.close();
            }
            catch(Exception ex)
            {
                ErrorMaster.insert("Exception at closing showBankInfo in BankInfoMaster : "+ex.getMessage());
            }
        }
        return result;
    }

    public static BankInfoBean showBankInfoDetail(String companyKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        BankInfoBean obj=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from bank_master where company_key='"+companyKey+"'";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                obj=new BankInfoBean();
                obj.setCompanyKey(rs.getString(1));
                obj.setAcName(rs.getString(2));
                obj.setAcNo(rs.getString(3));
                obj.setSwiffCode(rs.getString(4));
                obj.setIfscCode(rs.getString(5));
                obj.setBranchAddress(rs.getString(6));
                obj.setPaypalAcName(rs.getString(7));
                obj.setPaypalEmail(rs.getString(8));
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showBankInfoDetail in BankInfoMaster : "+ex.getMessage());
        }
        finally
        {
            try
            {
                rs.close();
                st.close();
                con.close();
            }
            catch(Exception ex)
            {
                ErrorMaster.insert("Exception at closing showBankInfoDetail in BankInfoMaster : "+ex.getMessage());
            }
        }
        return obj;
    }

}
