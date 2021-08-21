/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import db.utils.DBConnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author LenovoB560
 */
public class ErrorMaster {

    public int insertError(String errorMessage)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        errorMessage=errorMessage.replace("'","@#@#@");
        try
        {
//            con = DBConnect.instance( ).getConnection( );
//            st=con.createStatement();
//            String query="insert into"
//                    + " _error_master(error_date,error_time,error_message)"
//                    + " values(curdate(),curtime(),'"+errorMessage+"');";
//            result=st.executeUpdate(query);
            System.out.println("Error : "+errorMessage);
        }
        catch(Exception ex)
        {
            System.out.println("Exception at insertError() of ErrorMaster : "+ex.getMessage());
        }
        finally
        {
            try
            {
//                st.close();
//                con.close();
            }
            catch(Exception ex)
            {
                System.out.println("Exception at closing of insertError() of ErrorMaster : "+ex.getMessage());
            }
        }
        System.out.println(errorMessage);
        return result;
    }
    
        public int insert(String loggerMessage)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        loggerMessage=loggerMessage.replace("'","@#@#@");
        try
        {
//            con = DBConnect.instance( ).getConnection( );
//            st=con.createStatement();
//            String query="insert into"
//                    + " _logger_master(logger_date,logger_time,logger_message)"
//                    + " values(curdate(),curtime(),'"+loggerMessage+"');";
//            result=st.executeUpdate(query);
            System.out.println("Logger : "+loggerMessage);
        }
        catch(Exception ex)
        {
            System.out.println("Exception at insert() of ErrorMaster : "+ex.getMessage());
        }
        finally
        {
            try
            {
//                st.close();
//                con.close();
            }
            catch(Exception ex)
            {
                System.out.println("Exception at closing of insert() of ErrorMaster : "+ex.getMessage());
            }
        }
        return result;
    }

    public ArrayList showAllError()
    {
        ArrayList al=new ArrayList();
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        ErrorBean ib=null;
        try
        {
            con = DBConnect.instance( ).getConnection( );
            st=con.createStatement();
            String query="select *"
                    + " from _error_master"
                    + " order by error_id desc;";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                ib=new ErrorBean();
                ib.setErrorId(rs.getString(1));
                ib.setErrorDate(rs.getString(2));
                ib.setErrorTime(rs.getString(3));
                ib.setErrorMessage(rs.getString(4));
                al.add(ib);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception at showAllError() of ErrorMaster : "+ex.getMessage());
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
                System.out.println("Exception at closing of showAllError() of ErrorMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public ArrayList showAllLogger()
    {
        ArrayList al=new ArrayList();
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        ErrorBean ib=null;
        try
        {
            con = DBConnect.instance( ).getConnection( );
            st=con.createStatement();
            String query="select *"
                    + " from _logger_master"
                    + " order by logger_id desc;";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                ib=new ErrorBean();
                ib.setErrorId(rs.getString(1));
                ib.setErrorDate(rs.getString(2));
                ib.setErrorTime(rs.getString(3));
                ib.setErrorMessage(rs.getString(4));
                al.add(ib);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception at showAllLogger() of ErrorMaster : "+ex.getMessage());
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
                System.out.println("Exception at closing of showAllLogger() of ErrorMaster : "+ex.getMessage());
            }
        }
        return al;
    }

    public ArrayList showAllErrorByDate(String dt)
    {
        ArrayList al=new ArrayList();
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        ErrorBean ib=null;
        try
        {
            con = DBConnect.instance( ).getConnection( );
            st=con.createStatement();
            String query="select *"
                    + " from _error_master"
                    + " where error_date='"+dt+"'"
                    + " order by error_id desc;";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                ib=new ErrorBean();
                ib.setErrorId(rs.getString(1));
                ib.setErrorDate(rs.getString(2));
                ib.setErrorTime(rs.getString(3));
                ib.setErrorMessage(rs.getString(4));
                al.add(ib);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception at showAllErrorByDate() of ErrorMaster : "+ex.getMessage());
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
                System.out.println("Exception at closing of showAllErrorByDate() of ErrorMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public ArrayList showAllLoggerByDate(String dt)
    {
        ArrayList al=new ArrayList();
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        ErrorBean ib=null;
        try
        {
            con = DBConnect.instance( ).getConnection( );
            st=con.createStatement();
            String query="select *"
                    + " from _logger_master"
                    + " where logger_date='"+dt+"'"
                    + " order by logger_id desc;";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                ib=new ErrorBean();
                ib.setErrorId(rs.getString(1));
                ib.setErrorDate(rs.getString(2));
                ib.setErrorTime(rs.getString(3));
                ib.setErrorMessage(rs.getString(4));
                al.add(ib);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception at showAllLoggerByDate() of ErrorMaster : "+ex.getMessage());
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
                System.out.println("Exception at closing of showAllLoggerByDate() of ErrorMaster : "+ex.getMessage());
            }
        }
        return al;
    }
}
