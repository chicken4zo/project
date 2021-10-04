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
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try {
            conn = ConnectionHelper.getConnection("mysql");
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

        }

        return list;
    }
    // 멤버상세보기
    public Member DetailMember(String id) {

        Member memberdto = new Member();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionHelper.getConnection("mysql");
            String sql = "select id,password,name,address,birth from member where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);


            rs = pstmt.executeQuery();
            //rs.next(); 추후에 데이터 1건 경우  (while 없이 )

            while (rs.next()) {


                memberdto.setId(rs.getString("id"));
                memberdto.setPassword(rs.getString("password"));
                memberdto.setName(rs.getString("name"));
                memberdto.setBirth(rs.getInt("birth"));
                memberdto.setAddress(rs.getNString("address"));


            }


        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return memberdto;
    }
    //전체 데이터 리스트
    public ArrayList<Member> GetMemberList() throws SQLException {
        Connection conn = ConnectionHelper.getConnection("mysql");

        PreparedStatement pstmt = null;
        String sql = "SELECT id,password,name,birth,address FROM MEMBER";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<Member> memberlist = new ArrayList<>();
        while (rs.next()) {
            Member memberDto = new Member();
            memberDto.setId(rs.getString("id"));
            memberDto.setPassword(rs.getString("password"));
            memberDto.setName(rs.getString("name"));
            memberDto.setBirth(rs.getInt("birth"));
            memberDto.setAddress(rs.getString("address"));
            memberlist.add(memberDto);
        }
        ConnectionHelper.close(rs);
        ConnectionHelper.close(pstmt);
        ConnectionHelper.close(conn); //반환하기

        return memberlist;
    }
    // 멤버 아이디 값으로 받아오기
    public Member GetMemberListById(String id) {
        /*
         * select id, email ,content from memo where id=? memo m = new memo();
         * m.setId(rs.getInt(1)) ... return m
         */
        Connection conn = null;// 추가
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Member Info = new Member();
        try {
            conn = ConnectionHelper.getConnection("mysql");// 추가
            String sql = "select * from Member where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                Info.setId(rs.getString("id"));
                Info.setPassword(rs.getString("password"));
                Info.setName(rs.getString("NAME"));
                Info.setBirth(rs.getInt("birth"));
                Info.setAddress(rs.getString("address"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);

        }

        return Info;

    }

    public int EditMember(Member memberdto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultrow = 0;

        try {
            conn = ConnectionHelper.getConnection("mysql");
            String sql = "update member set name=? , birth=? , address=? , password=? where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberdto.getName());
            pstmt.setInt(2, memberdto.getBirth());
            pstmt.setString(3, memberdto.getAddress());
            pstmt.setString(4, memberdto.getPassword());
            pstmt.setString(5, memberdto.getId());
            resultrow = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return resultrow;
    }
        // Admin vㅔ
    public int deleteMember(String[] ids) {
        // delete from memo where id=?
        Connection conn = null;// 추가
        int resultrow = 0;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionHelper.getConnection("mysql");// 추가

            String sql = "delete from Member where id=?";
            pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < ids.length; i++) {
                pstmt.setString(1, ids[i]);
                resultrow += pstmt.executeUpdate(); // 반영된 행의 수
            }

        } catch (Exception e) {
            System.out.println("Insert : " + e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
            try {
                conn.close(); // 받환하기
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultrow;
    }
    public int DeleteMemberok(String id) {

        Connection conn = null;
        int resultrow = 0;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionHelper.getConnection("mysql");//추가

            String sql = "delete from member where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);


            resultrow = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Delete : " + e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
            try {
                conn.close(); //반환하기
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultrow;
    }
}


