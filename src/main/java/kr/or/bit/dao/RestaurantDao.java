package kr.or.bit.dao;


import kr.or.bit.dto.RestaurantBoard;
import kr.or.bit.dto.RestaurantComment;
import kr.or.bit.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
            String sql = "select * from (select rownum  rn, idx, title, content, hit, writedate, filename, id from (select * from RESTAURANT order by idx desc) where rownum <=? ) where rn >=? ";
            pstmt = conn.prepareStatement(sql);

            int start = cpage * pagesize - (pagesize - 1);
            int end = cpage * pagesize;

            pstmt.setInt(1, end);
            pstmt.setInt(2, start);

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
    public RestaurantBoard getContent(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        RestaurantBoard board = null;
        String message = null;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select idx, id, title, content, hit, writedate, filename, filepath from Restaurant where idx=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idx);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                board = new RestaurantBoard();
                board.setIdx(rs.getInt("idx"));
                board.setId(rs.getString("id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setWriteDate(rs.getDate("writedate"));
                board.setFileName(rs.getString("filename"));
                board.setFilePath(rs.getString("filepath"));
                System.out.println("Dao board : " + board);
            } else {
                System.out.println("출력할 데이터 없음");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

                ConnectionHelper.close(rs);
                ConnectionHelper.close(pstmt);
                ConnectionHelper.close(conn);

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
        return this.getContent(idx);
    }

    //게시물 수정처리
    public int boardEditOk(RestaurantBoard board) {
        int idx = board.getIdx();
        String id = board.getId();
        String title = board.getTitle();
        String content = board.getContent();
        String filename = board.getFileName();
        String filepath = board.getFilePath();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "update Restaurant title=?, content=?, filename=?, filepath=? where idx=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setString(3, board.getFileName());
            pstmt.setString(4, board.getFilePath());
            pstmt.setInt(5, board.getIdx());

            row = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("글쓰기 오류 : " + e.getMessage());
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

    //맛집 댓글목록 가져오기
    public List<RestaurantComment> getRestaurantCommentList(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<RestaurantComment> commentList = null;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select no, content, writedate, id, idx from Restaurant_comment where idx=? order by no desc";
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

                RestaurantComment comment = new RestaurantComment(no, content, writeDate, id, idx_fk);
                commentList.add(comment);
            }
        } catch (Exception e) {
            System.out.println("맛집DAO 댓글 목록 불러오기  오류 : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return commentList;
    }

    //댓글삭제하기
    public int commentDelete(int no) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = "delete from Restaurant_comment where no=?";

        try {
            conn = ConnectionHelper.getConnection("oracle");
            System.out.println("no : " + no);
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);

            result = pstmt.executeUpdate();


        } catch (Exception e) {
            System.out.println("삭제 왜안돼 " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return result;
    }

    //게시물 검색하기
    public ArrayList<RestaurantBoard> searchRestaurant(String text, int cpage, int pagesize) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<RestaurantBoard> searchList = new ArrayList<>();

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "SELECT IDX, TITLE, CONTENT, HIT, WRITEDATE, FILENAME,  ID FROM (SELECT ROWNUM RN, IDX, TITLE, CONTENT, HIT, WRITEDATE, FILENAME,  ID, ROWNUM FROM " +
                    "(SELECT IDX, TITLE, CONTENT, HIT, WRITEDATE, FILENAME, RESTAURANT.ID FROM RESTAURANT,MEMBER WHERE RESTAURANT.ID=MEMBER.ID and TITLE " +
                    "LIKE '%" + text + "%' ORDER BY IDX DESC) L) WHERE RN BETWEEN ? and ?";

            int start = cpage * pagesize - (pagesize - 1); //1 * 5 - (5 - 1) >> 1
            int end = cpage * pagesize; // 1 * 5 >> 5;
            System.out.println("start: " + start);
            System.out.println("end: " + end);
            System.out.println("cpage: " + cpage);

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, start);
            pstmt.setInt(2, end);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                RestaurantBoard restaurant = new RestaurantBoard();
                restaurant.setIdx(rs.getInt("idx"));
                restaurant.setId(rs.getString("id"));
                restaurant.setTitle(rs.getString("title"));
                restaurant.setContent(rs.getString("content"));
                restaurant.setFileName(rs.getString("filename"));
                restaurant.setHit(rs.getInt("hit"));
                restaurant.setWriteDate(rs.getDate("writedate"));
                System.out.println("여기는 restaurantSearchDao" + restaurant);
                searchList.add(restaurant);
            }

        } catch (Exception e) {
            System.out.println("restaurantSearchDao 오류 :" + e.getMessage());
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return searchList;
    }

    //검색한 게시글 개수
    public int totalSerachCount(String text) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;

        String sql = "SELECT count(*) cnt FROM (SELECT IDX, TITLE, CONTENT, HIT, WRITEDATE, FILENAME, RESTAURANT.ID FROM RESTAURANT,MEMBER WHERE RESTAURANT.ID=MEMBER.ID and TITLE LIKE '%" + text + "%' ORDER BY IDX DESC) L";

        try {
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt("cnt");
            } else {
                result = 0;
            }

        } catch (Exception e) {
            System.out.println("여기는 검색다오카운트 : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return result;
    }

}















