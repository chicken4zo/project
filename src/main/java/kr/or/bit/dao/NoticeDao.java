package kr.or.bit.dao;


import kr.or.bit.dto.NoticeBoard;
import kr.or.bit.util.ConnectionHelper;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoticeDao {

    //공지사항 글쓰기(관리자)
    public int writeOk(NoticeBoard noticeBoard) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int row = 0;
        System.out.println("dao 왔니..?");
        try {
            conn = ConnectionHelper.getConnection("oracle");
            System.out.println("try들어왔니...?");
            String sql = "insert into NOTICE(idx, title, content, hit, writedate, id) values(NOTICE_IDX.nextval,?,?,0,sysdate,?)";
            pstmt = conn.prepareStatement(sql);
            System.out.println("pstmt : " + pstmt);
            pstmt.setString(1, noticeBoard.getTitle());
            pstmt.setString(2, noticeBoard.getContent());
            pstmt.setString(3, noticeBoard.getId());
            System.out.println(noticeBoard);

            row = pstmt.executeUpdate();
            System.out.println("row : " + row);
        } catch (Exception e) {
            System.out.println("NoticeDao 오류 : " + e.getMessage());
            e.printStackTrace();
        } finally {
                ConnectionHelper.close(pstmt);
                ConnectionHelper.close(conn);
        }
        return row;
    }

    //공지사항 목록보기
    public List<NoticeBoard> list(int cpage, int pagesize) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<NoticeBoard> list = null;
        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select * from (select rownum  rn, idx, title, content, hit, writedate, id from (select * from NOTICE order by idx desc) where rownum <=? ) where rn >=? ";
            pstmt = conn.prepareStatement(sql);

            int start = cpage * pagesize - (pagesize - 1);
            int end = cpage * pagesize;

            pstmt.setInt(1, end);
            pstmt.setInt(2, start);

            rs = pstmt.executeQuery();
            list = new ArrayList<NoticeBoard>();
            while (rs.next()) {
                NoticeBoard noticeboard = new NoticeBoard();
                noticeboard.setIdx(rs.getInt("idx"));
                noticeboard.setTitle(rs.getString("title"));
                noticeboard.setContent(rs.getString("content"));
                noticeboard.setHit(rs.getInt("hit"));
                noticeboard.setWriteDate(rs.getDate("writedate"));
                noticeboard.setId(rs.getString("id"));
                System.out.println(noticeboard);

                list.add(noticeboard);
            }

        } catch (Exception e) {
            System.out.println("오류 : " + e.getMessage());
        } finally {
            try {
                ConnectionHelper.close(rs);
                ConnectionHelper.close(pstmt);
                ConnectionHelper.close(conn);
            } catch (Exception e) {
                System.out.println("오류 : " + e.getMessage());
            }
        }
        return list;
    }

    //게시물 총 건수 구하기
    public int totalNoticeCount() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalcount = 0;
        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select count(*) cnt from Notice";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                totalcount = rs.getInt("cnt");
            }
        } catch (Exception e) {

        } finally {
            try {
                ConnectionHelper.close(rs);
                ConnectionHelper.close(pstmt);
            } catch (Exception e) {

            }
        }
        return totalcount;
    }

    //게시물 상세보기
    public NoticeBoard getContent(int idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        NoticeBoard noticeBoard = null;
        String message = null;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select * from Notice where idx=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idx);

            //String title, content /  int hit / date writedate / String id
            rs = pstmt.executeQuery();
            if(rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                int hit = rs.getInt("hit");
                java.sql.Date writedate = rs.getDate("writedate");
                String id = rs.getString("id");

                noticeBoard = new NoticeBoard(idx, title, content, hit, writedate, id);
            }
        } catch (SQLException e) {
            System.out.println("content : " + e.getMessage());
        } finally {
            try {
                ConnectionHelper.close(rs);
                ConnectionHelper.close(pstmt);
                ConnectionHelper.close(conn);
            } catch (Exception e) {
                message = "empty";
                System.out.println(message);
            }
        }
        return noticeBoard;
    }

    private int getMaxRefer() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int refer_max = 0;
        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select nvl(max(refer),0) from NOTICE";

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


    //공지사항 조회수 증가
    public boolean getReadNum(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "update Notice set hit = hit + 1 where idx = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idx);

            int row = pstmt.executeUpdate();
            if(row > 0) {
                result = true;
            }
        }catch (Exception e) {
            System.out.println("오류 : " + e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return result;
    }

    //게시물 수정하기 화면
    public NoticeBoard getEditContent(String idx) {
        return this.getContent(Integer.parseInt(idx));
        //조회화면 동일 (기존에 있는 함수 재활용)
    }

    //게시글 수정하기 처리
    public int boardEditOk(HttpServletRequest board) {
        int idx = Integer.parseInt(board.getParameter("idx"));
        String title = board.getParameter("title");
        String content = board.getParameter("content");
        int hit = Integer.parseInt(board.getParameter("hit"));
        String Date = board.getParameter("writedate");
        String id = board.getParameter("writer");
        System.out.println("id : " + id);

        Connection conn = null;
        PreparedStatement pstmt = null;
        int row = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            System.out.println("수정하기 try들어왔니...?");
            String sql = "update NOTICE set title=?, content=? where idx=?";
            pstmt = conn.prepareStatement(sql);
            System.out.println(pstmt);
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setInt(3, idx);


            row = pstmt.executeUpdate();

            System.out.println("row : " + row);

        } catch (Exception e) {
            System.out.println("오류"+e.getMessage());
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return row;
    }

    //게시글 삭제하기
    public int NoticeDelete(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;
        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "delete from Notice where idx=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idx);

            rs = pstmt.executeQuery();
            System.out.println("rs: " + rs);
            if(rs.next()) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, idx);
                row = pstmt.executeUpdate();
                conn.commit();
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


















