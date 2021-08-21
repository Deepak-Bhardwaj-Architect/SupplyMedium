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
import supply.medium.home.bean.MessagesBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class MessageMaster {

    public static int insert(String messageKey, String userKeyFrom, String userKeyTo,
            String message) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into "
                    + "message_master(message_key,user_key_from,user_key_to,"
                    + "message,delete_from,delete_to,message_on) "
                    + "values('" + messageKey + "','" + userKeyFrom + "',"
                    + "'" + userKeyTo + "','" + message + "','0',"
                    + "'0',sysdate()) ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in MessageMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in MessageMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
    public static int delete(String my, String others) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "delete from "
                    + "message_master where "
                    + "user_key_from='"+others+"' "
                    + "and user_key_to='"+my+"'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at delete in MessageMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing delete in MessageMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
    public static ArrayList showMessages(String toUserKey,String fromUserKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        MessagesBean messageBean=null;
        ArrayList messageList=null;
        String query="";
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            query="select message_key,user_key_from,user_key_to,"
                    + "message,delete_from,delete_to,message_on "
                    + "from message_master "
                    + "where user_key_to='"+toUserKey+"' and user_key_from='"+fromUserKey+"' ";
                    //+ "or (user_key_to='"+fromUserKey+"' and user_key_from='"+toUserKey+"')" ;
            rs=st.executeQuery(query);
            messageList=new ArrayList();
            while(rs.next())
            {
                messageBean=new MessagesBean();
                messageBean.setMessageKey(rs.getString(1));
                messageBean.setUserKeyFrom(rs.getString(2));
                messageBean.setUserKeyTo(rs.getString(3));
                messageBean.setMessage(rs.getString(4));
                messageBean.setDeleteFrom(rs.getString(5));
                messageBean.setDeleteTo(rs.getString(6));
                messageBean.setMessageOn(rs.getString(7));
                messageList.add(messageBean);
            }
            query="update message_master set mstatus='read' "
                    + "where user_key_to='"+toUserKey+"' and user_key_from='"+fromUserKey+"' "
//                    + "or (user_key_to='"+fromUserKey+"' and user_key_from='"+toUserKey+"') "
                    + "and mstatus='unread'" ;
              st.executeUpdate(query);
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
        return messageList;
    }
    
    public static ArrayList showUnreadMessages(String toUserKey,String fromUserKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        MessagesBean messageBean=null;
        ArrayList messageList=null;
        String query="";
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            query="select message_key,user_key_from,user_key_to,"
                    + "message,delete_from,delete_to,message_on "
                    + "from message_master "
                    + "where user_key_to='"+toUserKey+"' and user_key_from='"+fromUserKey+"' "
                    //+ "or (user_key_to='"+fromUserKey+"' and user_key_from='"+toUserKey+"')) "
                    + "and mstatus='unread'" ;
            rs=st.executeQuery(query);
            messageList=new ArrayList();
            while(rs.next())
            {
                messageBean=new MessagesBean();
                messageBean.setMessageKey(rs.getString(1));
                messageBean.setUserKeyFrom(rs.getString(2));
                messageBean.setUserKeyTo(rs.getString(3));
                messageBean.setMessage(rs.getString(4));
                messageBean.setDeleteFrom(rs.getString(5));
                messageBean.setDeleteTo(rs.getString(6));
                messageBean.setMessageOn(rs.getString(7));
                messageList.add(messageBean);
            }
            query="update message_master set mstatus='read' "
                    + "where user_key_to='"+toUserKey+"' and user_key_from='"+fromUserKey+"' "
//                    + "or (user_key_to='"+fromUserKey+"' and user_key_from='"+toUserKey+"') "
                    + "and mstatus='unread'" ;
              st.executeUpdate(query);
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
        return messageList;
    }

    public static int showUnreadMessagesIfAny(String toUserKey,String fromUserKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        MessagesBean messageBean=null;
        int messageList=0;
        String query="";
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            query="select count(*) "
                    + "from message_master "
                    + "where user_key_to='"+toUserKey+"' and user_key_from='"+fromUserKey+"' "
                    //+ "or (user_key_to='"+fromUserKey+"' and user_key_from='"+toUserKey+"')) "
                    + "and mstatus='unread'" ;
            rs=st.executeQuery(query);
            while(rs.next())
            {
                messageList=rs.getInt(1);
            }
            query="update message_master set mstatus='read' "
                    + "where user_key_to='"+toUserKey+"' and user_key_from='"+fromUserKey+"' "
//                    + "or (user_key_to='"+fromUserKey+"' and user_key_from='"+toUserKey+"') "
                    + "and mstatus='unread'" ;
              st.executeUpdate(query);
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
        return messageList;
    }
    
//    public static int delete(String userKeyFrom, String userKeyTo) {;
//        int result = 0;
//        Connection con = null;
//        Statement st = null;
//        try {
//            con = MyConnection.connect();
//            st = con.createStatement();
//            String query = "update message_master set delete_from='1' "
//                    + "message_master(message_key,user_key_from,user_key_to,"
//                    + "message,delete_from,delete_to,message_on) "
//                    + "values('" + messageKey + "','" + userKeyFrom + "',"
//                    + "'" + userKeyTo + "','" + message + "','0',"
//                    + "'0',sysdate()) ";
//            result = st.executeUpdate(query);
//        } catch (Exception ex) {
//            ErrorMaster.insert("Exception at insert in MessageMaster : " + ex.getMessage());
//        } finally {
//            try {
//                st.close();
//                con.close();
//            } catch (Exception ex) {
//                ErrorMaster.insert("Exception at closing insert in MessageMaster : " + ex.getMessage());
//            }
//        }
//        return result;
//    }
}
