package kr.or.bit.dao;

import kr.or.bit.dto.LostBoard;
import kr.or.bit.dto.Member;
import kr.or.bit.util.ConnectionHelper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {



    DataSource ds = null;
    //회원가입

    public int RegisterMember(Member memberDto) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        int resultRow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "insert into member(id,password,address,birth,name) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, memberDto.getId());
            pstmt.setString(2, memberDto.getPassword());
            pstmt.setString(3, memberDto.getAddress());
            pstmt.setInt(4, memberDto.getBirth());
            pstmt.setString(5, memberDto.getName());

            resultRow = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("회원가입오류 : " + e.getMessage());

        } finally {

            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return resultRow;
    }

    // 로그인
    public Member loginMember(Member memberDto) {
        System.out.println("여기는1");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Member user = null;


        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select id,password from member where id=?";
            System.out.println("여기는2");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberDto.getId());
            rs = pstmt.executeQuery();


            if (rs.next()) {
                if (rs.getString("password").equals(memberDto.getPassword())) {
                    user = new Member();
                    //일치
                    System.out.println("여기는3");
                    user.setId(rs.getString("id"));
                    user.setPassword(rs.getString("password"));


                } else {
                    //불일치
                }
            }
        } catch (Exception e) {
            System.out.println("로그인 에러 : " + e.getMessage());
        } finally {


            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    // 아이디 체크
    public List<Member> IdCheck(String id) {
        List<Member> list = new ArrayList<Member>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select id, password, address, birth,name "
                    + "from member where id like ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + id + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Member memberdto = new Member();
                memberdto.setId(rs.getString("id"));
                memberdto.setPassword(rs.getString("password"));
                memberdto.setAddress(rs.getString("address"));
                memberdto.setBirth(rs.getInt("birth"));
                memberdto.setName(rs.getString("name"));

                list.add(memberdto);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);

        }

        return list;
    }

    public List<LostBoard> searchLostById(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<LostBoard> lostList = new ArrayList<>();

        try {
            conn = ConnectionHelper.getConnection("mysql");
            String sql = "SELECT IDX, TITLE, CONTENT, HIT, WRITEDATE, FILENAME, FILEPATH, REFER, DEPTH, STEP, PASSWORD, ADDRESS, BIRTH, NAME, ID, (@ROWNUM:=@ROWNUM+1) RN FROM (SELECT IDX, TITLE, CONTENT, HIT, WRITEDATE, FILENAME, FILEPATH, REFER, DEPTH, STEP, PASSWORD, ADDRESS, BIRTH, NAME, LOST.ID FROM LOST,MEMBER WHERE LOST.ID=MEMBER.ID and LOST.ID = ? ORDER BY REFER DESC, STEP ASC) L, (SELECT @ROWNUM:=0) R LIMIT 0,3";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                LostBoard lost = new LostBoard();
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

                System.out.println(lost);

                lostList.add(lost);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return lostList;
    }
}
