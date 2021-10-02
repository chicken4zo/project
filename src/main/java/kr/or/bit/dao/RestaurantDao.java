package kr.or.bit.dao;


import kr.or.bit.dto.RestaurantBoard;
import kr.or.bit.util.ConnectionHelper;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//맛집게시판, 댓글O, 답글X
public class RestaurantDao {

    //글쓰기
    public int writeOk(RestaurantBoard board) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int row = 0;
        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "insert into Restaurant(idx, title, content, hit, writeDate, fileName, filePath, id) " +
                    "values(RESTAURANT_SEQ.nextval,?,?,0,sysdate,?,?,?)";
            pstmt = conn.prepareStatement(sql);


            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setString(3, board.getFileName());
            pstmt.setString(4, board.getFilePath());
            pstmt.setString(5, board.getId());
            System.out.println(pstmt);
            row = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("오류 : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return row;
    }

    //목록보기
    public List<RestaurantBoard> list(int cpage, int pagesize) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<RestaurantBoard> list = null;
        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select idx, title, content, hit, writedate, filename, id, rownum rn from Restaurant where rownum between ? and ? order by idx desc";
            pstmt = conn.prepareStatement(sql);

            int start = cpage * pagesize - (pagesize - 1);
            int end = cpage * pagesize;

            pstmt.setInt(1, start);
            pstmt.setInt(2, end);

            rs = pstmt.executeQuery();
            list = new ArrayList<RestaurantBoard>();

            while (rs.next()) {
                RestaurantBoard restaurantBoard = new RestaurantBoard();
                restaurantBoard.setIdx(rs.getInt("idx"));
                restaurantBoard.setTitle(rs.getString("title"));
                restaurantBoard.setContent(rs.getString("content"));
                restaurantBoard.setHit(rs.getInt("hit"));
                restaurantBoard.setWriteDate(rs.getDate("writedate"));
                restaurantBoard.setFileName(rs.getString("filename"));
                restaurantBoard.setId(rs.getString("id"));
                System.out.println(restaurantBoard);

                list.add(restaurantBoard);
            }

        } catch (Exception e) {
            System.out.println("dao오류 : " + e.getMessage());

        } finally {
            try {
                ConnectionHelper.close(rs);
                ConnectionHelper.close(pstmt);
                ConnectionHelper.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("오류 : " + e.getMessage());
            }
        }
        return list;
    }

    //게시물 총 건수 구해야되나?
    public int totalBoardCount() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalcount = 0;
        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select count(*) cnt from Restaurant";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                totalcount = rs.getInt("cnt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionHelper.close(rs);
                ConnectionHelper.close(pstmt);
                ConnectionHelper.close(conn);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return totalcount;
    }

    //게시물 상세보기
    public RestaurantBoard getContent(int idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        RestaurantBoard board = null;
        String message = null;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select title, content, hit, writedate, filename, filepath, id from Restaurant where idx=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idx);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                int hit = rs.getInt("hit");
                java.sql.Date writedate = rs.getDate("writedate");
                String filename = rs.getString("filename");
                String filepath = rs.getString("filepath");
                String id = rs.getString("id");

                board = new RestaurantBoard(idx, title, content, hit, writedate, filename, filepath, id);
                System.out.println("Dao board : " + board);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionHelper.close(rs);
                ConnectionHelper.close(pstmt);
                ConnectionHelper.close(conn);
            } catch (Exception e) {
                message = "empty";
                System.out.println(message);
                e.printStackTrace();
            }
        }
        return board;
    }

    //게시글 조회수 증가가
    public boolean getReadNum(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "update Restaurant set hit = hit + 1 where idx = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idx);

            int row = pstmt.executeUpdate();
            if (row > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return result;
    }

    //게시물 수정하기 화면
    public RestaurantBoard getEditContent(String idx) {
        return this.getContent(Integer.parseInt(idx));
    }

    //게시물 수정처리
    public int boardEditOk(HttpServletRequest board) {
        int idx = Integer.parseInt(board.getParameter("idx"));
        String title = board.getParameter("title");
        String content = board.getParameter("content");
        int hit = Integer.parseInt(board.getParameter("hit"));
        String Date = board.getParameter("writedate");
        String filename = board.getParameter("filename");
        String id = board.getParameter("id");

        Connection conn = null;
        PreparedStatement pstmt = null;
        int row = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "update Restaurant set title=?, content=?, filename=? where idx=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setString(3, filename);
            pstmt.setInt(4, idx);

            row = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return row;
    }

    //게시글삭제
    public int RestaurantDelete(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "delete from Restaurant where idx = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idx);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, idx);
                row = pstmt.executeUpdate();
            } else {
                row = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return row;
    }

    //댓글 입력하기
    public int commentWrite(int idx, String id, String content) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int row = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "insert into Restaurant_Comment(no, content, writedate, id, idx) values(comment_no.nextval,?,sysdate,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, content);
            pstmt.setString(2, id);
            pstmt.setInt(3, idx);

            row = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return row;
    }

    //댓글삭제하기
    public int commentDelete(String no) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;

        try {
            String sql = "delete from Restaurant_comment where no=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(no));

            rs = pstmt.executeQuery();
            if (rs.next()) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(no));

                row = pstmt.executeUpdate();
            } else {
                row = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return row;
    }


}















