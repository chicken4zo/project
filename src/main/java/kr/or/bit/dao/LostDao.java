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
    String database = "mysql";
    // String database에 oracle, mysql, myoracle 둘 중에 하나 입력

    public LostDao() {
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/" + database);
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<LostBoard> getLostList(int cpage, int pagesize) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String listSql = null;

        ArrayList<LostBoard> list = new ArrayList<>();

        String address = null;

        // SELECT * FROM (SELECT ROWNUM rn, IDX, TITLE, CONTENT, HIT, WRITEDATE, FILENAME, FILEPATH, REFER, DEPTH, STEP, ID FROM (SELECT IDX, TITLE, CONTENT, HIT, WRITEDATE, FILENAME, FILEPATH, REFER, DEPTH, STEP, ID FROM LOST ORDER BY REFER DESC, STEP ASC)TB, WHERE rn BETWEEN ? AND ?

        try {

            conn = ConnectionHelper.getConnection(database);

            if (database.equals("mysql")) {
                listSql = "SELECT IDX, TITLE, CONTENT, HIT, WRITEDATE, FILENAME, FILEPATH, REFER, DEPTH, STEP, PASSWORD, ADDRESS, BIRTH, NAME, ID, (@ROWNUM:=@ROWNUM+1) RN FROM (SELECT IDX, TITLE, CONTENT, HIT, WRITEDATE, FILENAME, FILEPATH, REFER, DEPTH, STEP, PASSWORD, ADDRESS, BIRTH, NAME, LOST.ID FROM LOST,MEMBER WHERE LOST.ID=MEMBER.ID ORDER BY REFER DESC, STEP ASC) L, (SELECT @ROWNUM:=0) R LIMIT ?,?";
            } else {
                listSql = "SELECT IDX, TITLE, CONTENT, HIT, WRITEDATE, FILENAME, FILEPATH, REFER, DEPTH, STEP, PASSWORD, ADDRESS, BIRTH, NAME, ID, ROWNUM FROM (SELECT IDX, TITLE, CONTENT, HIT, WRITEDATE, FILENAME, FILEPATH, REFER, DEPTH, STEP, PASSWORD, ADDRESS, BIRTH, NAME, LOST.ID FROM LOST,MEMBER WHERE LOST.ID=MEMBER.ID ORDER BY REFER DESC, STEP ASC) L WHERE ROWNUM BETWEEN ? AND ?";
            }
            pstmt = conn.prepareStatement(listSql);

            int start = cpage * pagesize - (pagesize - 1); //1 * 5 - (5 - 1) >> 1
            int end = cpage * pagesize; // 1 * 5 >> 5;
            System.out.println(pagesize);
            System.out.println(start - 1);

            if (database.equals("mysql")) {
                pstmt.setInt(1, start - 1);
                pstmt.setInt(2, pagesize);
            } else {
                pstmt.setInt(1, start);
                pstmt.setInt(2, end);
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                LostBoard lost = new LostBoard();
                System.out.println(rs.getInt("idx"));
                lost.setIdx(rs.getInt("idx"));
                lost.setId(rs.getString("id"));
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
        String sql = null;

        if (database.equals("mysql")) {
            sql = "INSERT INTO LOST(IDX,TITLE,CONTENT,HIT,WRITEDATE,FILENAME,FILEPATH,REFER,DEPTH,STEP,ID) VALUES (IDX,?,?,0,current_timestamp,?,?,?,0,0,?)";
        } else {
            sql = "INSERT INTO LOST(IDX,TITLE,CONTENT,HIT,WRITEDATE,FILENAME,FILEPATH,REFER,DEPTH,STEP,ID) VALUES (LOST_SEQ.nextval,?,?,0,SYSDATE,?,?,?,0,0,?)";
        }

        Connection conn = null;

        try {
            conn = ConnectionHelper.getConnection(database);

            pstmt = conn.prepareStatement(sql);
            int refermax = getMaxRefer();
            int refer = refermax + 1;
            System.out.println(lostBoard);
            pstmt.setString(1, lostBoard.getTitle());
            pstmt.setString(2, lostBoard.getContent());
            if (lostBoard.getFileName() != null) {
                pstmt.setString(3, lostBoard.getFileName());
                pstmt.setString(4, lostBoard.getFilePath());
            } else {
                pstmt.setString(3, "");
                pstmt.setString(4, "");
            }
//            pstmt.setString(3, "test");
//            pstmt.setString(4, "test");
            pstmt.setInt(5, refer);
            pstmt.setString(6, lostBoard.getId());

            resultRow = pstmt.executeUpdate();
            System.out.println("resultRow" + resultRow);


        } catch (SQLException e) {
            System.out.println("글쓰기 오류: " + e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
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
            conn = ConnectionHelper.getConnection(database);

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
            ConnectionHelper.close(conn);
        }
        return lost;
    }

    private int getMaxRefer() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int refer_max = 0;
        try {
            conn = ConnectionHelper.getConnection(database);
            String sql = "";
            if (database.equals("mysql")) {
                sql = "select ifnull(max(refer),0) from LOST";
            } else {
                sql = "select nvl(max(refer),0) from LOST";
            }
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
            ConnectionHelper.close(conn);
        }
        return refer_max;
    }

    public boolean getHit(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
            conn = ConnectionHelper.getConnection(database);

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
            ConnectionHelper.close(conn);
        }
        return result;
    }

    public int totalLostCount() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalcount = 0;

        try {
            conn = ConnectionHelper.getConnection(database);
            String sql = "SELECT Count(*) cnt from LOST";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                totalcount = rs.getInt("cnt");
//                System.out.println(totalcount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return totalcount;


    }

    public List<LostComment> getLostCommentList(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<LostComment> commentList = null;

        try {

            conn = ConnectionHelper.getConnection(database);

            String sql = "SELECT NO,CONTENT,WRITEDATE,ID,IDX FROM LOST_COMMENT WHERE IDX=? ORDER BY NO DESC";

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
            ConnectionHelper.close(conn);
        }
        return commentList;
    }

    public int writeLostComment(String id, String content, String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int row = 0;
        try {
            conn = ConnectionHelper.getConnection(database);
            String sql = null;
            if (database.equals("mysql")) {
                sql = "INSERT INTO LOST_COMMENT(NO,CONTENT,WRITEDATE,ID,IDX) VALUES (NO,?,CURRENT_TIMESTAMP,?,?)";
            } else {
                sql = "INSERT INTO LOST_COMMENT(NO,CONTENT,WRITEDATE,ID,IDX) VALUES (LOST_COMMENT_SEQ.nextval,?,SYSDATE,?,?)";
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, content);
            pstmt.setString(2, id);
            pstmt.setInt(3, Integer.parseInt(idx));

            row = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return row;
    }

    public int deleteLostComment(int no) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM LOST_COMMENT WHERE no=?";

        try {
            conn = ConnectionHelper.getConnection(database);

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);
            result = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return result;
    }

    public int modifyLost(LostBoard lost) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE LOST SET TITLE=?, CONTENT=?, FILENAME=?, FILEPATH=? WHERE IDX = ?";

        try {
            conn = ConnectionHelper.getConnection(database);

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, lost.getTitle());
            pstmt.setString(2, lost.getContent());
            pstmt.setString(3, lost.getFileName());
            pstmt.setString(4, lost.getFilePath());
            pstmt.setInt(5, lost.getIdx());
            result = pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("글쓰기 오류: " + e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return result;
    }

    public int deleteLost(String idx) {
        int resultRow = 0;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM LOST WHERE IDX = ?";
        Connection conn = null;

        try {
            conn = ConnectionHelper.getConnection(database);

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

    public int replyLost(LostBoard lostBoard) {
        int resultRow = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = ConnectionHelper.getConnection(database);
            int idx = lostBoard.getIdx();
            String id = lostBoard.getId();
            String title = lostBoard.getTitle();
            String content = lostBoard.getContent();
            String fileName = lostBoard.getFileName();
            String filePath = lostBoard.getFilePath();

            String refer_depth_step_sal = "select refer , depth , step from LOST where idx=?";
            String find_step_sql = "SELECT MIN(STEP)step FROM LOST WHERE REFER = ? and STEP > ? and DEPTH <= ?";
            String set_step_sql = "";
            String reply_sql = "";
            if (database.equals("mysql")) {
                reply_sql = "INSERT INTO LOST(IDX,TITLE,CONTENT,HIT,WRITEDATE,FILENAME,FILEPATH,ID,REFER,DEPTH,STEP) VALUES (IDX,?,?,0,current_timestamp,?,?,?,?,?,?)";
            } else {
                reply_sql = "INSERT INTO LOST(IDX,TITLE,CONTENT,HIT,WRITEDATE,FILENAME,FILEPATH,ID,REFER,DEPTH,STEP) VALUES (IDX,?,?,0,SYSDATE,?,?,?,?,?,?)";
            }

            pstmt = conn.prepareStatement(refer_depth_step_sal);
            pstmt.setInt(1, idx);
            rs = pstmt.executeQuery();
            System.out.println("here");

            if (rs.next()) {
                int refer = rs.getInt("refer");
                int step = rs.getInt("step");
                int depth = rs.getInt("depth");
                System.out.println(refer);
                System.out.println(step);
                System.out.println(depth);

                pstmt = conn.prepareStatement(find_step_sql);
                pstmt.setInt(1, refer);
                pstmt.setInt(2, step);
                pstmt.setInt(3, depth);
                ResultSet rs2 = pstmt.executeQuery();
                int newStep = 0;

                if (rs2.next()) {
                    if (rs2.getInt("step") != 0) {
                        int minStep = rs2.getInt("step");
                        set_step_sql = "UPDATE LOST SET STEP=STEP+1 WHERE REFER=? and STEP>?";
                        pstmt = conn.prepareStatement(set_step_sql);
                        pstmt.setInt(1, refer);
                        pstmt.setInt(2, minStep);
                        resultRow = pstmt.executeUpdate();
                        System.out.println(resultRow);
                        newStep = step;
                    } else {
                        set_step_sql = "SELECT MAX(STEP)+1 FROM LOST WHERE REFER = ?";
                        pstmt = conn.prepareStatement(set_step_sql);
                        pstmt.setInt(1, refer);
                        ResultSet rs3 = pstmt.executeQuery();
                        if (rs3.next()) {
                            newStep = rs3.getInt("MAX(STEP)+1");
                            System.out.println(newStep);
                        }
                    }
                }
                int newDepth = depth + 1;

                pstmt = conn.prepareStatement(reply_sql);
                pstmt.setString(1, title);
                pstmt.setString(2, content);
                pstmt.setString(3, fileName);
                pstmt.setString(4, filePath);
                pstmt.setString(5, id);
                pstmt.setInt(6, refer);
                pstmt.setInt(7, newDepth);
                pstmt.setInt(8, newStep);

                int row = pstmt.executeUpdate();
                if (row > 0) {
                    resultRow = row;
                } else {
                    resultRow = -1;
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return resultRow;
    }
}
