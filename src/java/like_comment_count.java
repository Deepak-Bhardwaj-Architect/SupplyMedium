
//import com.sun.org.apache.bcel.internal.generic.RETURN;
import db.utils.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author intel-i5
 */
public class like_comment_count {
                Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
                String lk_cmnt="";
    String like_comment_count2(int id)
    {
          try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs	        = statement.executeQuery( "SELECT COUNT(*) AS rowcount FROM news_feed_like_comment_master where comment_detail ='like' and news_feed_id="+id+" ");
			if(rs.next())
                        {
                            lk_cmnt = rs.getInt("rowcount")+"*";
                        } 
                        else
                        {
                            lk_cmnt="0*";
                        } 
                        
                        //con 		= DBConnect.instance( ).getConnection( );
			//statement 	= con.createStatement( );
			rs	        = statement.executeQuery( "SELECT COUNT(*) AS rowcount FROM news_feed_like_comment_master where comment_detail !='like' and news_feed_id="+id+" ");
			if(rs.next())
                        {
                            lk_cmnt += rs.getInt("rowcount")+"";
                        } 
                        else
                        {
                            lk_cmnt +="0";
                        } 
        }
		catch( SQLException e )
		{
                        //return e+"";
			//System.out.print("error"+e);
		}
          finally
          {
              try { 
                  con.close();
              } catch (SQLException ex) {
                  Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
     return lk_cmnt;
    }
    String like_comment_count3(int id)
    {
          try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs	        = statement.executeQuery( "SELECT COUNT(*) AS rowcount FROM company_feed_like_comment_master where comment_detail ='like' and news_feed_id="+id+" ");
			if(rs.next())
                        {
                            lk_cmnt = rs.getInt("rowcount")+"*";
                        } 
                        else
                        {
                            lk_cmnt="0*";
                        } 
                        
                        //con 		= DBConnect.instance( ).getConnection( );
			//statement 	= con.createStatement( );
			rs	        = statement.executeQuery( "SELECT COUNT(*) AS rowcount FROM company_feed_like_comment_master where comment_detail !='like' and news_feed_id="+id+" ");
			if(rs.next())
                        {
                            lk_cmnt += rs.getInt("rowcount")+"";
                        } 
                        else
                        {
                            lk_cmnt +="0";
                        } 
        }
		catch( SQLException e )
		{
                        //return e+"";
			//System.out.print("error"+e);
		}
          finally
          {
              try { 
                  con.close();
              } catch (SQLException ex) {
                  Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
     return lk_cmnt;
    }
    String like_comment_count4(int id)
    {
          try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs	        = statement.executeQuery( "SELECT COUNT(*) AS rowcount FROM department_feed_like_comment_master where comment_detail ='like' and news_feed_id="+id+" ");
			if(rs.next())
                        {
                            lk_cmnt = rs.getInt("rowcount")+"*";
                        } 
                        else
                        {
                            lk_cmnt="0*";
                        } 
                        
                        //con 		= DBConnect.instance( ).getConnection( );
			//statement 	= con.createStatement( );
			rs	        = statement.executeQuery( "SELECT COUNT(*) AS rowcount FROM department_feed_like_comment_master where comment_detail !='like' and news_feed_id="+id+" ");
			if(rs.next())
                        {
                            lk_cmnt += rs.getInt("rowcount")+"";
                        } 
                        else
                        {
                            lk_cmnt +="0";
                        } 
        }
		catch( SQLException e )
		{
                        //return e+"";
			//System.out.print("error"+e);
		}
          finally
          {
              try { 
                  con.close();
              } catch (SQLException ex) {
                  Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
     return lk_cmnt;
    }
}
