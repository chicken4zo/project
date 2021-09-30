package kr.or.bit.dao;

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
            conn = ConnectionHelper.getConnection("mysql");
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
            conn = ConnectionHelper.getConnection("mysql");
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

        String sql = "select id, password, address, birth,name "
                + "from member where id like ?";


        try (

                Connection conn = ConnectionHelper.getConnection("mysql");
                PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, "%" + id + "%");

            ResultSet rs = pstmt.executeQuery();

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
        }


        return list;
    }
}
