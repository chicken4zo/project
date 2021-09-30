package kr.or.bit.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

//POOL
public class ConnectionHelper {
    public static Connection getConnection(String dbname) {

        if (dbname.equalsIgnoreCase("oracle")) {
            try {
                Context initContext = new InitialContext();
//                DataSource source = (DataSource) initContext.lookup("java:comp/env/jdbc/oracle");
                // 오라클 사용 시 수정해야 하는 부분
                DataSource source = (DataSource) initContext.lookup("java:comp/env/jdbc/mysql");
//                DataSource source = (DataSource) initContext.lookup("java:comp/env/jdbc/oracle");
                // 오라클 사용 시, 아래 주석 처리 해제
                Connection conn = source.getConnection();
                return conn;
            } catch (Exception ex) {
                System.out.println("connection" + ex.getMessage());
                return null;
            }
        } else if (dbname.equalsIgnoreCase("mysql")) {
            try {
                // 1.
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PROJECT", "root",
                        "1004");

                // 2.
                /*
                 * Context initContext = new InitialContext(); Context context =
                 * (Context)initContext.lookup("java:/comp/env"); DataSource source =
                 * (DataSource)context.lookup("mysql/demoweb"); Connection conn =
                 * source.getConnection();
                 */
                return conn;
            } catch (Exception ex) {
                return null;
            }
        } else {
            return null;
        }

    }


    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {

            }
        }
    }

    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {

            }
        }
    }

    public static void close(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (Exception e) {

            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {

            }
        }
    }

}
