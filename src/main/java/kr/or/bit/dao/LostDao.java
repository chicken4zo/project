package kr.or.bit.dao;

import kr.or.bit.dto.LostBoard;
import kr.or.bit.dto.LostComment;
import kr.or.bit.util.ConnectionHelper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LostDao {

    DataSource ds = null;

    public LostDao() {
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<LostBoard> getLostList(int cpage, int pagesize) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String listSql = "SELECT IDX, TITLE, CONTENT, HIT, WRITEDATE, FILENAME, FILEPATH, REFER, DEPTH, STEP, (@ROWNUM:=@ROWNUM+1) rn, M.ID, M.ADDRESS FROM (SELECT IDX, TITLE, CONTENT, HIT, WRITEDATE, FILENAME, FILEPATH, REFER, DEPTH, STEP FROM LOST ORDER BY REFER ASC) L RIGHT OUTER JOIN MEMBER M on ID = M.ID, (SELECT @rownum:=0) R LIMIT ?,?";

        ArrayList<LostBoard> list = new ArrayList<>();

        String address = null;

        // SELECT * FROM (SELECT ROWNUM rn, IDX, TITLE, CONTENT, HIT, WRITEDATE, FILENAME, FILEPATH, REFER, DEPTH, STEP, ID FROM (SELECT IDX, TITLE, CONTENT, HIT, WRITEDATE, FILENAME, FILEPATH, REFER, DEPTH, STEP, ID FROM LOST ORDER BY REFER DESC, STEP ASC)TB, WHERE rn BETWEEN ? AND ?

        try {
            conn = ConnectionHelper.getConnection("mysql");
            pstmt = conn.prepareStatement(listSql);

            int start = cpage * pagesize - (pagesize - 1) - 1; //1 * 5 - (5 - 1) >> 1
            int end = cpage * pagesize; // 1 * 5 >> 5;

            pstmt.setInt(1, start);
            pstmt.setInt(2, end);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                LostBoard lost = new LostBoard();
                lost.setIdx(rs.getInt("idx"));
                lost.setId(rs.getString("m.id"));
                lost.setTitle(rs.getString("title"));
                lost.setContent(rs.getString("content"));
                lost.setFileName(rs.getString("filename"));
                lost.setFilePath(rs.getString("filepath"));
                lost.setHit(rs.getInt("hit"));
                lost.setWriteDate(rs.getDate("writedate"));
                lost.setRefer(rs.getInt("refer"));
                lost.setDepth(rs.getInt("depth"));
                lost.setStep(rs.getInt("step"));
                lost.setAddress(rs.getString("address"));

                list.add(lost);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return list;
    }

    public int writeLost(LostBoard lostBoard) {
        int resultRow = 0;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO LOST(IDX,TITLE,CONTENT,HIT,WRITEDATE,FILENAME,FILEPATH,REFER,ID) VALUES (IDX,?,?,0,current_timestamp,?,?,?,?)";

        Connection conn = null;

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            int refermax = getMaxRefer();
            int refer = refermax + 1;
            pstmt.setString(1, lostBoard.getTitle());
            pstmt.setString(2, lostBoard.getContent());
            pstmt.setString(3, lostBoard.getFileName());
            pstmt.setString(4, lostBoard.getFilePath());
//            pstmt.setString(3, "test");
//            pstmt.setString(4, "test");
            pstmt.setInt(5, refer);
            pstmt.setString(6, lostBoard.getId());

            resultRow = pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
        }
        return resultRow;
    }

    public LostBoard viewLostContent(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LostBoard lost = null;
        String sql = "SELECT IDX, ID, TITLE, CONTENT, HIT, WRITEDATE, FILENAME, FILEPATH FROM LOST WHERE IDX = ?";

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idx);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                lost = new LostBoard();
                lost.setIdx(rs.getInt("idx"));
                lost.setId(rs.getString("id"));
                lost.setTitle(rs.getString("title"));
                lost.setContent(rs.getString("content"));
                lost.setFilePath(rs.getString("filepath"));
                lost.setFileName(rs.getString("filename"));
                lost.setWriteDate(rs.getDate("writedate"));
            } else {
                lost = null;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
        }
        return lost;
    }

    private int getMaxRefer() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int refer_max = 0;
        try {
            conn = ds.getConnection();
            String sql = "select ifnull(max(refer),0) from LOST";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                refer_max = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
        }
        return refer_max;
    }

    public boolean getHit(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
            conn = ds.getConnection();
            String sql = "UPDATE LOST SET HIT = HIT+1 WHERE IDX=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idx);

            int row = pstmt.executeUpdate();

            if (row > 0) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
        }
        return result;
    }

    public int totalLostCount() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalcount = 0;

        try {
            conn = ds.getConnection();
            String sql = "SELECT Count(*) cnt from LOST";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                totalcount = rs.getInt("cnt");
//                System.out.println(totalcount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
        }
        return totalcount;


    }

    public List<LostComment> getLostCommentList(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<LostComment> commentList = null;

        try {
            conn = ds.getConnection();
            String sql = "";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(idx));
            rs = pstmt.executeQuery();

            commentList = new ArrayList<>();

            while (rs.next()) {
                int no = rs.getInt("no");
                String content = rs.getString("content");
                Date writeDate = rs.getDate("writedate");
                String id = rs.getString("id");
                int idx_fk = rs.getInt("idx");

                LostComment comment = new LostComment(no, content, writeDate, id, idx_fk);
                commentList.add(comment);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
        }
        return commentList;
    }

    public int modifyLost(LostBoard lost) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE LOST SET TITLE=?, CONTENT=?, FILENAME=?, FILEPATH=? WHERE IDX = ?";

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, lost.getTitle());
            pstmt.setString(2, lost.getContent());
            pstmt.setString(3, lost.getFileName());
            pstmt.setString(4, lost.getFilePath());
            pstmt.setInt(5, lost.getIdx());
            result = pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
        }

        return result;
    }

    public int deleteLost(String idx) {
        int resultRow = 0;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM LOST WHERE IDX = ?";
        Connection conn = null;

        try {
            conn = ConnectionHelper.getConnection("mysql");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(idx));
            resultRow = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return resultRow;
    }
}
