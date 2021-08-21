/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.home.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import supply.medium.home.bean.ConnectionBean;
import supply.medium.home.bean.FeedBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class ConnectionMaster {
 
public static int insert(String connectionKey,String userKeyFrom,String userKeyTo,
        String companyKeyFrom,String companyKeyTo) 
    {
        Connection con = null;
        Statement st = null;
        int result=0;
        
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into connection_master "
                    + "(connection_key,user_key_from,user_key_to,company_key_from,"
                    + "company_key_to,status,sent_on)"
                    + "values('"+connectionKey+"','"+userKeyFrom+"','"+userKeyTo+"','"+companyKeyFrom+"',"
                    + "'"+companyKeyTo+"','Pending',sysdate())";
            result=st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in ConnectionMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in ConnectionMaster : " + ex.getMessage());
            }
        }
        return result;
    }

public static ArrayList showByStatus(String userKey,String status)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        ConnectionBean connectionBean=null;
        ArrayList connectionList=null;
        String query="";
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            query="select connection_key,user_key_from,user_key_to,"
                    + "company_key_from,company_key_to,status,sent_on "
                    + "from connection_master "
                    + "where user_key_to='"+userKey+"' " ;
//                    + "where (user_key_to='"+userKey+"' "
//                    + "or user_key_from='"+userKey+"') " ;
            if(status.equals("Accepted"))
                query+="and status in('Accepted') ";
            else
                query+="and status not in('Accepted') ";
            query+= "order by connection_key desc";
            rs=st.executeQuery(query);
            connectionList=new ArrayList();
            while(rs.next())
            {
                connectionBean=new ConnectionBean();
                connectionBean.setConnectionKey(rs.getString(1));
                connectionBean.setUserKeyFrom(rs.getString(2));
                connectionBean.setUserKeyTo(rs.getString(3));
                connectionBean.setCompanyKeyFrom(rs.getString(4));
                connectionBean.setCompanyKeyTo(rs.getString(5));
                connectionBean.setStatus(rs.getString(6));
                connectionBean.setSentOn(rs.getString(7));
                connectionList.add(connectionBean);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showByStatus in ConnectionMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showByStatus in ConnectionMaster : "+ex.getMessage());
            }
        }
        return connectionList;
    }

public static ArrayList showConnectedList(String userKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        ConnectionBean connectionBean=null;
        ArrayList connectionList=null;
        String query="";
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            query="select connection_key,user_key_from,user_key_to,"
                    + "company_key_from,company_key_to,status,sent_on "
                    + "from connection_master "
                    + "where user_key_to='"+userKey+"' " ;
            ErrorMaster.insert("query "+query);
            rs=st.executeQuery(query);
            connectionList=new ArrayList();
            while(rs.next())
            {
                connectionBean=new ConnectionBean();
                connectionBean.setConnectionKey(rs.getString(1));
                connectionBean.setUserKeyFrom(rs.getString(2));
                connectionBean.setUserKeyTo(rs.getString(3));
                connectionBean.setCompanyKeyFrom(rs.getString(4));
                connectionBean.setCompanyKeyTo(rs.getString(5));
                connectionBean.setStatus(rs.getString(6));
                connectionBean.setSentOn(rs.getString(7));
                connectionList.add(connectionBean);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showByStatus in ConnectionMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showByStatus in ConnectionMaster : "+ex.getMessage());
            }
        }
        return connectionList;
    }

public static ConnectionBean showByConnectionKey(String connectionId)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        ConnectionBean connectionBean=null;
        String query="";
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            query="select connection_key,user_key_from,user_key_to,"
                    + "company_key_from,company_key_to,status,sent_on "
                    + "from connection_master "
                    + "where connection_key='"+connectionId+"' " ;
            rs=st.executeQuery(query);
            while(rs.next())
            {
                connectionBean=new ConnectionBean();
                connectionBean.setConnectionKey(rs.getString(1));
                connectionBean.setUserKeyFrom(rs.getString(2));
                connectionBean.setUserKeyTo(rs.getString(3));
                connectionBean.setCompanyKeyFrom(rs.getString(4));
                connectionBean.setCompanyKeyTo(rs.getString(5));
                connectionBean.setStatus(rs.getString(6));
                connectionBean.setSentOn(rs.getString(7));
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showByConnectionKey in ConnectionMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showByConnectionKey in ConnectionMaster : "+ex.getMessage());
            }
        }
        return connectionBean;
    }

public static int statusUpdate(String status,String connectionKey) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update connection_master set status='"+status+"' "
                    + "where connection_key='"+connectionKey+"'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at statusUpdate in ConnectionMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing statusUpdate in ConnectionMaster : " + ex.getMessage());
            }
        }
        return result;
    }
public static int delete(String connectionKey) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "delete from connection_master where connection_key='"+connectionKey+"'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at statusUpdate in ConnectionMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing statusUpdate in ConnectionMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static String checkConnection(String fromUserKey,String toUserKey) 
    {
        String result = "notConnected";
        Connection con = null;
        Statement st = null;
        ResultSet rs=null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "SELECT * FROM connection_master "
                    + "where user_key_from='"+fromUserKey+"' "
                    + "and user_key_to='"+toUserKey+"' "
                    + "and status='Accepted';";
//            ErrorMaster.insert(query);
            rs = st.executeQuery(query);
            if(rs.next())
            {
              result = "connected";  
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at statusUpdate in ConnectionMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing statusUpdate in ConnectionMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
    public static int areConnection(String fromUserKey,String toUserKey) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        ResultSet rs=null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "SELECT * FROM connection_master "
                    + "where user_key_from='"+fromUserKey+"' "
                    + "and user_key_to='"+toUserKey+"';";
            rs = st.executeQuery(query);
            if(rs.next())
            {
              result++;  
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at areConnection in ConnectionMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing areConnection in ConnectionMaster : " + ex.getMessage());
            }
        }
        return result;
    }    
}