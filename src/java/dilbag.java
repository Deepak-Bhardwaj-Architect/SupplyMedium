/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import core.login.SessionData;
import db.utils.DBConnect;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.AppConfigReader;

/**
 *
 * @author intel-i5
 */
public class dilbag extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet dilbag</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet dilbag at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        PrintWriter out = response.getWriter();
        String a = "";
        if (request.getParameter("id") != null) {
            //out.print("id"+request.getParameter("id"));
            like_comment_count lc = new like_comment_count();
            out.print(lc.like_comment_count2(Integer.parseInt(request.getParameter("id"))));
        }
        if (request.getParameter("cmnt") != null) {
            out.print(request.getParameter("cmnt") + "nd" + request.getParameter("ids"));
            Statement statement = null;
            Connection con = null;

            try {
                con = DBConnect.instance().getConnection();
                statement = con.createStatement();
                //rs	        = statement.executeQuery( "insert into news_feed_like_comment_master (comment_detail,news_feed_id) values('"+request.getParameter("cmnt")+"',"+request.getParameter("id")+")");
                statement.executeUpdate("insert into news_feed_like_comment_master (comment_detail,news_feed_id) values('" + request.getParameter("cmnt") + "'," + request.getParameter("ids") + ")");
            } catch (SQLException e) {
                //return e+"";
                //System.out.print("error"+e);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (request.getParameter("slct_cmnt") != null) {
            // out.print("ids"+request.getParameter("ids"));
            Statement statement = null;
            Connection con = null;
            ResultSet rs = null;
            try {
                con = DBConnect.instance().getConnection();
                statement = con.createStatement();
                rs = statement.executeQuery("SELECT comment_detail,commented_on FROM news_feed_like_comment_master where comment_detail !='like' and news_feed_id=" + request.getParameter("ids") + " order by id desc ");
                while (rs.next()) {
                    a = "javascript:alert(\'hello\'" + rs.getString(1) + ")";
                    out.print("<li style='background:#FFFFFF;margin-top:10px;padding:10px;height:auto;border-radius:10px;'><p onload='" + a + "' style='color:#2E2E2E'>" + rs.getString(1) + "</p><p style='color:#A4A4A4;margin-left:450px;'>" + new SimpleDateFormat("dd MMM yy, hh:mm a").format(Timestamp.valueOf(rs.getString(2).toString())) + "</p></li>");
                }

            } catch (SQLException e) {
                //return e+"";
                //System.out.print("error"+e);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (request.getParameter("typ") != null) {
            if (request.getParameter("typ").equals("sv_usr_dtl")) {
                //out.print("eml"+request.getParameter("eml"));
                Statement statement = null;
                Connection con = null;

                try {
                    con = DBConnect.instance().getConnection();
                    statement = con.createStatement();
                    //rs	        = statement.executeQuery( "insert into news_feed_like_comment_master (comment_detail,news_feed_id) values('"+request.getParameter("cmnt")+"',"+request.getParameter("id")+")");
                    statement.executeUpdate("update user_profile set first_name='" + request.getParameter("frstnm") + "',last_name='" + request.getParameter("lstnm") + "' ,phone='" + request.getParameter("phn") + "',cell='" + request.getParameter("mbl") + "',department='" + request.getParameter("dprtmnt") + "',user_role='" + request.getParameter("usr_rl") + "',fax='" + request.getParameter("fx") + "',user_type='" + request.getParameter("usr_tp") + "' where email='" + request.getParameter("eml") + "'");
                    statement.executeUpdate("update mailing_address set city_name='" + request.getParameter("cty") + "',zipcode='" + request.getParameter("zpcd") + "' where email='" + request.getParameter("eml") + "'");
                    out.print("updated");
                } catch (Exception e) {
                    //return e+"";
                    System.out.print("error" + e);
                } finally {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (request.getParameter("typ").equals("sv_usr_dtl2")) {
                //out.print("eml"+request.getParameter("eml"));   
                Statement statement = null;
                Connection con = null;

                try {
                    con = DBConnect.instance().getConnection();
                    statement = con.createStatement();
                    //rs	        = statement.executeQuery( "insert into news_feed_like_comment_master (comment_detail,news_feed_id) values('"+request.getParameter("cmnt")+"',"+request.getParameter("id")+")");
                    statement.executeUpdate("update user_profile set first_name='" + request.getParameter("frstnm") + "',last_name='" + request.getParameter("lstnm") + "' ,phone='" + request.getParameter("phn") + "',cell='" + request.getParameter("mbl") + "',fax='" + request.getParameter("fx") + "' where email='" + request.getParameter("eml") + "'");
                    statement.executeUpdate("update mailing_address set city_name='" + request.getParameter("cty") + "',zipcode='" + request.getParameter("zpcd") + "',address='" + request.getParameter("adrs") + "',state_name='" + request.getParameter("st") + "' where email='" + request.getParameter("eml") + "'");
                    out.print("updated");
                } catch (Exception e) {
                    //return e+"";
                    System.out.print("error" + e);
                } finally {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (request.getParameter("typ").equals("cmpny_fd_lk_cmnt")) {
                like_comment_count lc = new like_comment_count();
                out.print(lc.like_comment_count3(Integer.parseInt(request.getParameter("cmpny_id"))));
            }
            if (request.getParameter("typ").equals("sv_cmpny_fd_lk_cmnt")) {
                Statement statement = null;
                Connection con = null;

                try {
                    con = DBConnect.instance().getConnection();
                    statement = con.createStatement();
                    //rs	        = statement.executeQuery( "insert into news_feed_like_comment_master (comment_detail,news_feed_id) values('"+request.getParameter("cmnt")+"',"+request.getParameter("id")+")");
                    statement.executeUpdate("insert into company_feed_like_comment_master (comment_detail,news_feed_id) values('" + request.getParameter("cmpny_cmnt") + "'," + request.getParameter("cmpny_id") + ")");
                } catch (SQLException e) {
                    //return e+"";
                    //System.out.print("error"+e);
                } finally {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (request.getParameter("typ").equals("opn_cmpny_fd_cmnt")) {

                // out.print("ids"+request.getParameter("ids"));
                Statement statement = null;
                Connection con = null;
                ResultSet rs = null;
                try {
                    con = DBConnect.instance().getConnection();
                    statement = con.createStatement();
                    rs = statement.executeQuery("SELECT comment_detail,commented_on FROM company_feed_like_comment_master where comment_detail !='like' and news_feed_id=" + request.getParameter("cmpny_id") + " order by id desc ");
                    while (rs.next()) {
                        a = "javascript:alert(\'hello\'" + rs.getString(1) + ")";
                        out.print("<li style='background:#FFFFFF;margin-top:10px;padding:10px;height:auto;border-radius:10px;'><p style='color:#2E2E2E' onload='" + a + "'>" + rs.getString(1) + "</p><p style='color:#A4A4A4;margin-left:450px;'>" + new SimpleDateFormat("dd MMM yy, hh:mm a").format(Timestamp.valueOf(rs.getString(2).toString())) + "</p></li>");
                    }

                } catch (SQLException e) {
                    //return e+"";
                    //System.out.print("error"+e);
                } finally {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (request.getParameter("typ").equals("dptmnt_fd_lk_cmnt")) {
                like_comment_count lc = new like_comment_count();
                out.print(lc.like_comment_count4(Integer.parseInt(request.getParameter("cmpny_id"))));
            }
            if (request.getParameter("typ").equals("sv_dptmnt_fd_lk_cmnt")) {
                Statement statement = null;
                Connection con = null;

                try {
                    con = DBConnect.instance().getConnection();
                    statement = con.createStatement();
                    //rs	        = statement.executeQuery( "insert into news_feed_like_comment_master (comment_detail,news_feed_id) values('"+request.getParameter("cmnt")+"',"+request.getParameter("id")+")");
                    statement.executeUpdate("insert into department_feed_like_comment_master (comment_detail,news_feed_id) values('" + request.getParameter("cmpny_cmnt") + "'," + request.getParameter("cmpny_id") + ")");
                } catch (SQLException e) {
                    //return e+"";
                    //System.out.print("error"+e);
                } finally {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (request.getParameter("typ").equals("opn_dptmnt_fd_cmnt")) {

                // out.print("ids"+request.getParameter("ids"));
                Statement statement = null;
                Connection con = null;
                ResultSet rs = null;
                try {
                    con = DBConnect.instance().getConnection();
                    statement = con.createStatement();
                    rs = statement.executeQuery("SELECT comment_detail,commented_on FROM department_feed_like_comment_master where comment_detail !='like' and news_feed_id=" + request.getParameter("cmpny_id") + " order by id desc ");
                    while (rs.next()) {
                        a = "javascript:alert(\'hello\'" + rs.getString(1) + ")";
                        out.print("<li style='background:#FFFFFF;margin-top:10px;padding:10px;height:auto;border-radius:10px;'><p style='color:#2E2E2E' onload='" + a + "'>" + rs.getString(1) + "</p><p style='color:#A4A4A4;margin-left:250px;'>" + new SimpleDateFormat("dd MMM yy, hh:mm a").format(Timestamp.valueOf(rs.getString(2).toString())) + "</p></li>");
                    }

                } catch (SQLException e) {
                    //return e+"";
                    //System.out.print("error"+e);
                } finally {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (request.getParameter("typ").equals("splr_al_srch")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                String str = "SELECT COUNT(*) AS rowcount FROM company_registration where email!='" + sessionObj.username_ + "' and company_type like '%Suppliers%'";

                Statement stmt = null;
                ResultSet rs = null;
                Connection con = null;
                try {
                    con = DBConnect.instance().getConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(str);
                    if (rs.next()) {
                        out.print(rs.getInt("rowcount"));
                    }
                } catch (Exception ex) {
                    out.print("problem to get countries" + ex);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }
            }
            if (request.getParameter("typ").equals("splr_slctd_ctgry_srch")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                String str = "SELECT COUNT(*) AS rowcount FROM company_registration where business_category_name like '%" + request.getParameter("ctgry") + "%' and email!='" + sessionObj.username_ + "' and company_type like '%Suppliers%'";

                Statement stmt = null;
                ResultSet rs = null;
                Connection con = null;
                try {
                    con = DBConnect.instance().getConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(str);
                    if (rs.next()) {
                        out.print(rs.getInt("rowcount"));
                    }
                } catch (Exception ex) {
                    out.print("problem to get countries" + ex);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }
            }
            if (request.getParameter("typ").equals("splr_slctd_cntry_srch")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                String str = "SELECT COUNT(*) AS rowcount FROM company_registration where business_category_name like '%" + request.getParameter("slctd_ctgry") + "%' and email!='" + sessionObj.username_ + "' and company_type like '%Suppliers%' and regn_key in ( select regn_key from mailing_address where country_name='" + request.getParameter("cntry") + "' )";

                Statement stmt = null;
                ResultSet rs = null;
                Connection con = null;
                try {
                    con = DBConnect.instance().getConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(str);
                    if (rs.next()) {
                        out.print(rs.getInt("rowcount"));
                    }
                } catch (Exception ex) {
                    out.print("problem to get countries" + ex);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }
            }

            if (request.getParameter("typ").equals("byr_al_srch")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                String str = "SELECT COUNT(*) AS rowcount FROM company_registration where email!='" + sessionObj.username_ + "' and company_type like '%Buyer%'";

                Statement stmt = null;
                ResultSet rs = null;
                Connection con = null;
                try {
                    con = DBConnect.instance().getConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(str);
                    if (rs.next()) {
                        out.print(rs.getInt("rowcount"));
                    }
                } catch (Exception ex) {
                    out.print("problem to get countries" + ex);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }
            }
            if (request.getParameter("typ").equals("byr_slctd_ctgry_srch")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                String str = "SELECT COUNT(*) AS rowcount FROM company_registration where business_category_name like '%" + request.getParameter("ctgry") + "%' and email!='" + sessionObj.username_ + "' and company_type like '%Buyer%'";

                Statement stmt = null;
                ResultSet rs = null;
                Connection con = null;
                try {
                    con = DBConnect.instance().getConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(str);
                    if (rs.next()) {
                        out.print(rs.getInt("rowcount"));
                    }
                } catch (Exception ex) {
                    out.print("problem to get countries" + ex);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }
            }
            if (request.getParameter("typ").equals("byr_slctd_cntry_srch")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                String str = "SELECT COUNT(*) AS rowcount FROM company_registration where business_category_name like '%" + request.getParameter("slctd_ctgry") + "%' and email!='" + sessionObj.username_ + "' and company_type like '%Buyer%' and regn_key in ( select regn_key from mailing_address where country_name='" + request.getParameter("cntry") + "' )";

                Statement stmt = null;
                ResultSet rs = null;
                Connection con = null;
                try {
                    con = DBConnect.instance().getConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(str);
                    if (rs.next()) {
                        out.print(rs.getInt("rowcount"));
                    }
                } catch (Exception ex) {
                    out.print("problem to get countries" + ex);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }
            }
            if (request.getParameter("typ").equals("clr_ntfctn_cnt")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                out.print("receiver_key='" + sessionObj.username_ + "'");
                String str = "update notification set status =0 where receiver_key='" + sessionObj.username_ + "' and status=1 ";

                Statement stmt = null;
                ResultSet rs = null;
                Connection con = null;
                try {
                    con = DBConnect.instance().getConnection();
                    stmt = con.createStatement();
                    stmt.executeUpdate(str);
                } catch (Exception ex) {
                    out.print("problem to clear notifications" + ex);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }
            }
            if (request.getParameter("typ").equals("gt_ntfctn_cnt")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                String str = "SELECT COUNT(*) AS rowcount FROM notification where receiver_key='" + sessionObj.username_ + "' and status=1 ";

                Statement stmt = null;
                ResultSet rs = null;
                Connection con = null;
                try {
                    con = DBConnect.instance().getConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(str);
                    if (rs.next()) {
                        out.print(rs.getInt("rowcount"));
                    }
                    else
                    {
                       out.print("0"); 
                    }    
                } catch (Exception ex) {
                    out.print("problem to get countries" + ex);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }
            }
            if (request.getParameter("typ").equals("rmv_frm_ntwrk")) {

                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                String str = "delete from vendor_registration where regn_rel_key='" + sessionObj.companyRegnKeyStr_ + "' and regn_rel_map='" + request.getParameter("ky") + "'";
                Statement stmt = null;
                ResultSet rs = null;
                Connection con = null;
                try {
                    con = DBConnect.instance().getConnection();
                    stmt = con.createStatement();
                    stmt.executeUpdate(str);
                } catch (Exception ex) {
                    out.print("problem to get countries" + ex);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }
                String str2 = "delete from regn_vendor_mapper where regn_rel_key_map='" + sessionObj.companyRegnKeyStr_ + "' and regn_rel_key='" + request.getParameter("ky") + "' ";
                Statement stmt2 = null;
                ResultSet rs2 = null;
                Connection con2 = null;
                try {
                    con2 = DBConnect.instance().getConnection();
                    stmt2 = con2.createStatement();
                    stmt2.executeUpdate(str2);
                } catch (Exception ex) {
                    out.print("problem to get countries" + ex);
                } finally {
                    try {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (stmt2 != null) {
                            stmt2.close();
                        }
                        if (con2 != null) {
                            con2.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }
            }
            if (request.getParameter("typ").equals("mx_mn_lst_sld")) {

                String str = "select min(price),max(price) from invoice_item where invoice_id in(select invoice_id from invoice where to_regn_key='" + request.getParameter("ven_key") + "') and part_no='" + request.getParameter("prt_n") + "'";

                Statement stmt = null;
                ResultSet rs = null;
                Connection con = null;
                try {
                    con = DBConnect.instance().getConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(str);
                    if (rs.next()) {
                        out.print("Minimum sold price: " + rs.getInt(1) + " and Maximum sold price: " + rs.getInt(2) + " of that product for selected vendor");
                    }
                } catch (Exception ex) {
                    out.print("problem to get countries" + ex);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }

            }
            if (request.getParameter("typ").equals("crt_usr_fldr")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                File uploadDir = new File( request.getServletContext().getRealPath("/").replace("\\", "/")+"/"+ AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" )+"/user_folder"+sessionObj.companyRegnKeyStr_);
                if( !uploadDir.exists( ) )
		{
			uploadDir.mkdirs( );
		}
                File dir = new File(request.getServletContext().getRealPath("/").replace("\\", "/")+"/"+ AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" )+"/user_folder"+sessionObj.companyRegnKeyStr_+"/" + request.getParameter("fldr_nm").trim());
                if (dir.exists()) {
                    out.print("Folder already exist");
                } else {
                    dir.mkdir();
                    out.print("Folder created");
                }
            }
            if (request.getParameter("typ").equals("gt_usr_fldrs")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                File f = new File(request.getServletContext().getRealPath("/").replace("\\", "/")+"/"+ AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" )+"/user_folder"+sessionObj.companyRegnKeyStr_);
                File[] files = f.listFiles();

                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        File file = files[i];
                        if (file.isDirectory()) {
                            out.print("<li id=fldr_" + i + " onclick=\"javascript:opn_fldr_fls('" + file.getName() + "','fldr_" + i + "')\";>&nbsp;&nbsp;<div class='folder folder_closed_top_one'></div>" + file.getName() + "</li>");
                        }
                    }
                }
            }
            if (request.getParameter("typ").equals("gt_usr_fldrs_fls")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                File f = new File(request.getServletContext().getRealPath("/").replace("\\", "/")+"/"+ AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" )+"/user_folder"+sessionObj.companyRegnKeyStr_+"/" + request.getParameter("fldr_nm"));
                File[] files = f.listFiles();

                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        File file = files[i];
                        out.print("<li id='fl" + i + "' onclick=\"javascript:slct_fldr_fl('fl" + i + "','"+file.getName()+"')\";>&nbsp;&nbsp;<div class='file file_closed_top_one'></div>" + file.getName() + "</li>");
                    }
                    out.print("<input type='hidden' value='"+AppConfigReader.instance( ).get( "TEMP_PATH" )+"/"+ AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" )+"/user_folder"+sessionObj.companyRegnKeyStr_+"/" + request.getParameter("fldr_nm")+"' id='slected_user_folder_url' />");
                }
            }
            if (request.getParameter("typ").equals("crt_cmpny_fldr")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                File uploadDir = new File( request.getServletContext().getRealPath("/").replace("\\", "/")+"/"+ AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" )+"/company_folder"+sessionObj.companyRegnKeyStr_);
                if( !uploadDir.exists( ) )
		{
			uploadDir.mkdirs( );
		}
                File dir = new File(request.getServletContext().getRealPath("/").replace("\\", "/")+"/"+ AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" )+"/company_folder"+sessionObj.companyRegnKeyStr_+"/" + request.getParameter("fldr_nm").trim());
                if (dir.exists()) {
                    out.print("Folder already exist");
                } else {
                    dir.mkdir();
                    out.print("Folder created");
                }
            }
            
            if (request.getParameter("typ").equals("gt_cmpny_fldrs")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                File f = new File(request.getServletContext().getRealPath("/").replace("\\", "/")+"/"+ AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" )+"/company_folder"+sessionObj.companyRegnKeyStr_);
                File[] files = f.listFiles();

                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        File file = files[i];
                        if (file.isDirectory()) {
                            out.print("<li style='width:165px;' id=cmpny_fldr_" + i + " onclick=\"javascript:opn_cmpny_fldr_fls('" + file.getName() + "','cmpny_fldr_" + i + "')\";>&nbsp;&nbsp;<div class='folder folder_closed_top_one'></div>" + file.getName() + "</li>");
                        }
                    }
                }
            }
            if (request.getParameter("typ").equals("gt_cmpny_fldrs_fls")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                File f = new File(request.getServletContext().getRealPath("/").replace("\\", "/")+"/"+ AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" )+"/company_folder"+sessionObj.companyRegnKeyStr_+"/" + request.getParameter("fldr_nm"));
                File[] files = f.listFiles();

                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        File file = files[i];
                        out.print("<li style='width:165px;' id='fl" + i + "' onclick=\"javascript:slct_fldr_fl('fl" + i + "','"+file.getName()+"')\";>&nbsp;&nbsp;<div class='file file_closed_top_one'></div>" + file.getName() + "</li>");
                    }
                out.print("<input type='hidden' value='"+AppConfigReader.instance( ).get( "TEMP_PATH" )+"/"+ AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" )+"/company_folder"+sessionObj.companyRegnKeyStr_+"/" + request.getParameter("fldr_nm")+"' id='slected_company_folder_url' />");    
                }
            }
            if (request.getParameter("typ").equals("dlt_usr_fldr")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                File f = new File(request.getServletContext().getRealPath("/").replace("\\", "/")+"/"+ AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" )+"/user_folder"+sessionObj.companyRegnKeyStr_+"/" + request.getParameter("fldr_nm"));
                delete_folder df=new delete_folder();
                df.delete(f);
            }
            if (request.getParameter("typ").equals("dlt_cmpny_fldr")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                File f = new File(request.getServletContext().getRealPath("/").replace("\\", "/")+"/"+ AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" )+"/company_folder"+sessionObj.companyRegnKeyStr_+"/" + request.getParameter("fldr_nm"));
                delete_folder df=new delete_folder();
                df.delete(f);
            }
            if (request.getParameter("typ").equals("dlt_usr_fl")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                File f = new File(request.getServletContext().getRealPath("/").replace("\\", "/")+"/"+ AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" )+"/user_folder"+sessionObj.companyRegnKeyStr_+"/" + request.getParameter("fldr_nm")+"/" + request.getParameter("fl_nm"));
                if(f.exists())
                {
                    f.delete();
                }
            }
            if (request.getParameter("typ").equals("dlt_cmpny_fl")) {
                HttpSession session = request.getSession(true);
                SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");
                File f = new File(request.getServletContext().getRealPath("/").replace("\\", "/")+"/"+ AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" )+"/company_folder"+sessionObj.companyRegnKeyStr_+"/" + request.getParameter("fldr_nm")+"/" + request.getParameter("fl_nm"));
                if(f.exists())
                {
                    f.delete();
                }
            }
            if (request.getParameter("typ").equals("updt_shpng_adrs")) {
                String str = "update invoice set diff_email ='" + request.getParameter("address") + "' where invoice_id=" + request.getParameter("auto") + "";

                Statement stmt = null;
                ResultSet rs = null;
                Connection con = null;
                try {
                    con = DBConnect.instance().getConnection();
                    stmt = con.createStatement();
                    stmt.executeUpdate(str);
                } catch (Exception ex) {
                    out.print("problem to clear notifications" + ex);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }
            }
            if (request.getParameter("typ").equals("gt_shpng_adrs")) {
                String recievercompanykey=request.getParameter("ky").trim();
                String data="<thead style=\"background: #65B0c4;color: #fff\">\n" +
"				<tr>					\n" +
"                                    <th class=\"rowBorder\" style=\"border-left:1px solid #c8c8c8\">Select\n" +
"					<th class=\"rowBorder\">Addresss</th>\n" +
"                                        <th class=\"rowBorder\">Citys</th>\n" +
"                                        <th class=\"rowBorder\">States</th>\n" +
"					<th class=\"rowBorder\">countrys</th>\n" +
"                                        <th class=\"rowBorder\">Zip Codes</th>\n" +
"					<th class=\"rowBorder\" style=\"border-right:1px solid #c8c8c8\">Emails</th>\n" +
"				</tr>\n" +
"			</thead>";
                if(!recievercompanykey.equals("")) 
                {
                String str = "select * from mailing_address where regn_key='" + request.getParameter("ky") + "'";

                Statement stmt = null;
                ResultSet rs = null;
                Connection con = null;
                try {
                    con = DBConnect.instance().getConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(str);
                    
                    while (rs.next()) {
                        
                        data+="<tr style='font-family: -webkit-body;font-size: medium;'>					\n" +
"                                    <td class=\"rowBorder\" style=\"border-left:1px solid #c8c8c8\"><input type=\"radio\" name=\"diff_address\" value=\""+rs.getString(4)+", "+rs.getString(5)+", "+rs.getString(6)+", "+rs.getString(8)+", "+rs.getString(7)+", "+rs.getString(9)+"\" onclick=\"javascript:document.getElementById('selected_address').value=this.value;\"/></td>\n" +
"					<td class=\"rowBorder\">"+rs.getString(4)+"</td>\n" +
"                                        <td class=\"rowBorder\">"+rs.getString(5)+"</td>\n" +
"					<td class=\"rowBorder\">"+rs.getString(6)+"</td>\n" +
"                                        <td class=\"rowBorder\">"+rs.getString(8)+"</td>\n" +
"                                        <td class=\"rowBorder\">"+rs.getString(7)+"</td>\n" +                                        
"					<td class=\"rowBorder\" style=\"border-right:1px solid #c8c8c8\">"+rs.getString(9)+"</td>\n" +
"				</tr>";
                    }
                    //out.print(data.toString());
                } catch (Exception ex) {
                    out.print("problem to get countries" + ex);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }
                }
                //if(recievercompanykey.equals(""))
                //{
                 HttpSession session = request.getSession(true);
                 SessionData sessionObj = (SessionData) session.getAttribute("UserSessionData");   
                 String str = "select diff_address,diff_email from invoice where from_regn_key='" + sessionObj.companyRegnKeyStr_ + "' and is_diff_address='1'";

                Statement stmt = null;
                ResultSet rs = null;
                Connection con = null;
                try {
                    con = DBConnect.instance().getConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(str);                    
                    String outsidersupplierdifferntaddress=null;
                    String address=null;
                    String city=null;
                    String state=null;
                    String country=null;
                    String zipcode=null;
                    String email=null;
                    while (rs.next()) {
                        outsidersupplierdifferntaddress=rs.getString("diff_address");
                        email=rs.getString("diff_email");
                        //out.print(outsidersupplierdifferntaddress);
                        if(outsidersupplierdifferntaddress!=null)
                        {
                        String outsidersupplierdifferntaddressvalues[]=outsidersupplierdifferntaddress.split(",");
                        //out.print(outsidersupplierdifferntaddressvalues.length);
                        if(outsidersupplierdifferntaddressvalues.length==5)
                        {
                           address=outsidersupplierdifferntaddressvalues[0];
                           city=outsidersupplierdifferntaddressvalues[1];
                           state=outsidersupplierdifferntaddressvalues[2];
                           country=outsidersupplierdifferntaddressvalues[3];
                           zipcode=outsidersupplierdifferntaddressvalues[4];
                        }    
                        data+="<tr style='font-family: -webkit-body;font-size: medium;'>					\n" +
"                                    <td class=\"rowBorder\" style=\"border-left:1px solid #c8c8c8\"><input type=\"radio\" name=\"diff_address\" value=\""+address+", "+city+", "+state+", "+country+", "+zipcode+", "+email+"\" onclick=\"javascript:document.getElementById('selected_address').value=this.value;\"/></td>\n" +
"					<td class=\"rowBorder\">"+address+"</td>\n" +
"                                        <td class=\"rowBorder\">"+city+"</td>\n" +
"					<td class=\"rowBorder\">"+state+"</td>\n" +
"                                        <td class=\"rowBorder\">"+country+"</td>\n" +
"                                        <td class=\"rowBorder\">"+zipcode +"</td>\n" +                                        
"					<td class=\"rowBorder\" style=\"border-right:1px solid #c8c8c8\">"+email+"</td>\n" +
"				</tr>";
                    }                    
                } 
                    data+="<tr style='font-family: -webkit-body;font-size: medium;'>					\n" +
"                                    <td class=\"rowBorder\" style=\"border-left:1px solid #c8c8c8\"><input type=\"radio\" name=\"diff_address\" id='diff_address' onclick=\"javascript:document.getElementById('selected_address').value=document.getElementById('diffaddress').value+','+document.getElementById('diffcity').value+','+document.getElementById('diffstate').value+','+document.getElementById('diffcountry').value+','+document.getElementById('diffpincode').value;\"/></td>\n" +
"					<td class=\"rowBorder\" style='padding:1px;'><input type='text' style='width:70px;padding:1px;' id='diffaddress'/></td>\n" +
"                                        <td class=\"rowBorder\" style='padding:1px;'><input type='text' style='width:70px;padding:1px;' id='diffcity'/></td>\n" +
"					<td class=\"rowBorder\" style='padding:1px;'><input type='text' style='width:70px;padding:1px;' id='diffstate'/></td>\n" +
"                                        <td class=\"rowBorder\" style='padding:1px;'><input type='text' style='width:70px;padding:1px;' id='diffcountry'/></td>\n" +
"                                        <td class=\"rowBorder\" style='padding:1px;'><input type='text' style='width:70px;padding:1px;' id='diffpincode'/></td>\n" +                                        
"					<td class=\"rowBorder\"  style='padding:1px;'>\n" +
"				</tr>";
                    out.print(data.toString());
                }catch (Exception ex) {
                    out.print("problem to get countries" + ex);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }
                //}                 
}
        if (request.getParameter("typ").equals("clr_ntfctn_hstry")) {
            
            out.println("request.getParameter(\"typ\")   "+request.getParameter("val"));
            
            String str = "delete from notification where receiver_key='" + request.getParameter("val") + "'";
                Statement stmt = null;
                //ResultSet rs = null;
                Connection con = null;
                try {
                    con = DBConnect.instance().getConnection();
                    stmt = con.createStatement();
                    stmt.executeUpdate(str);
                } catch (Exception ex) {
                    out.print("problem to get countries" + ex);
                } finally {
                    try {                       
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }
        }
                if (request.getParameter("typ").equals("update_user_privileges")) {
                  String str = "update user_privileges set privileges =" + request.getParameter("prvileges") + " where user_rel_key='" + request.getParameter("val") + "'";

                Statement stmt = null;
                ResultSet rs = null;
                Connection con = null;
                try {
                    con = DBConnect.instance().getConnection();
                    stmt = con.createStatement();
                    stmt.executeUpdate(str);
                } catch (Exception ex) {
                    out.print("problem to update privileges" + ex);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sQLException) {
                    }
                }  
                }

        }
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
