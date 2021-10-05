package kr.or.bit.dao;

import kr.or.bit.dto.PetBoard;
import kr.or.bit.dto.PetComment;
import kr.or.bit.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PetDao {

    // 총 게시물 수
    public int totalPetCount() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalCount = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select count(*) cnt from pet";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                totalCount = rs.getInt("cnt");
            }

        } catch (Exception e) {
            System.out.println("PETDAO TOTAL 에러");
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return totalCount;
    }

    // 리스트 불러오기
    public List<PetBoard> getPetBoardList(int cpage, int pagesize) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PetBoard> list = null;

        try {
            conn = ConnectionHelper.getConnection("oracle");
//            String sql = "select * from (select rownum rn, m.id, p.idx, p.title, p.content, p.hit, p.writedate, p.filename, p.filepath from pet p join member m on(p.id = m.id) order by idx desc) where rn between ? and ?";
            String sql = "select * from(SELECT ROWNUM rn, id,  idx, title,address, content, hit, writedate, filename, filepath from (select m.id,m.address, p.idx, p.title, p.content, p.hit, p.writedate, p.filename, p.filepath from pet p join member m on(p.id = m.id) order by idx desc) where rownum <=?) where rn>=?";

            pstmt = conn.prepareStatement(sql);


            //공식같은 로직
            int start = cpage * pagesize - (pagesize - 1); //1 * 5 - (5 - 1) >> 1
            int end = cpage * pagesize; // 1 * 5 >> 5

            pstmt.setInt(1, end);
            pstmt.setInt(2, start);
            rs = pstmt.executeQuery();
            list = new ArrayList<>();

            while (rs.next()) {
                PetBoard petBoard = new PetBoard();

                petBoard.setId(rs.getString("id"));
                petBoard.setIdx(rs.getInt("idx"));
                petBoard.setAddress(rs.getString("address"));
                petBoard.setTitle(rs.getString("title"));
                petBoard.setContent(rs.getString("content"));
                petBoard.setHit(rs.getInt("hit"));
                petBoard.setWriteDate(rs.getDate("writedate"));
                petBoard.setFileName1(rs.getString("filename"));
                petBoard.setFilePath1(rs.getString("filepath"));

                list.add(petBoard);
            }
        } catch (Exception e) {
            System.out.println("PETBOARD DAO LIST 에러");
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return list;
    }

    // 글쓰기
    public int writePetBoard(PetBoard petBoard) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultRow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "insert into pet(idx, title,id, content, hit, writedate, " +
                    "filename, filepath, filename2, filepath2, filename3, filepath3)" +
                    " values (pet_seq.nextval,?,?,?,0,sysdate,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, petBoard.getTitle());
            pstmt.setString(2, petBoard.getId());
            pstmt.setString(3, petBoard.getContent());
            pstmt.setString(4, petBoard.getFileName1());
            pstmt.setString(5, petBoard.getFilePath1());

            if (petBoard.getFileName2() != null) {
                pstmt.setString(6, petBoard.getFileName2());
                pstmt.setString(7, petBoard.getFilePath2());
            } else {
                pstmt.setString(6, "");
                pstmt.setString(7, "");
            }

            if (petBoard.getFileName3() != null) {
                pstmt.setString(8, petBoard.getFileName3());
                pstmt.setString(9, petBoard.getFilePath3());
            } else {
                pstmt.setString(8, "");
                pstmt.setString(9, "");
            }


            resultRow = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("PETDAO 글쓰기 에러");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return resultRow;
    }

    // 상세보기
    public PetBoard getPetBoardContent(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PetBoard petBoard = null;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select idx, m.id, title, content, hit, writedate, filename, filepath, filename2, filepath2, filename3, filepath3, m.address from pet p join member m on(p.id = m.id) where idx=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idx);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                petBoard = new PetBoard();
                petBoard.setIdx(rs.getInt("idx"));
                petBoard.setId(rs.getString("id"));
                petBoard.setTitle(rs.getString("title"));
                petBoard.setContent(rs.getString("content"));
                petBoard.setWriteDate(rs.getDate("writedate"));
                petBoard.setFileName1(rs.getString("filename"));
                petBoard.setFilePath1(rs.getString("filepath"));
                petBoard.setFileName2(rs.getString("filename2"));
                petBoard.setFilePath2(rs.getString("filepath2"));
                petBoard.setFileName3(rs.getString("filename3"));
                petBoard.setFilePath3(rs.getString("filepath3"));
                petBoard.setHit(rs.getInt("hit"));
                petBoard.setAddress(rs.getString("address"));

            }
        } catch (Exception e) {
            System.out.println("PETDAO 상세보기 에러");
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return petBoard;
    }

    // 조회수 가져오기
    public boolean getHit(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "update pet set hit=hit+1 where idx = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, idx);

            if (pstmt.executeUpdate() > 0) {
                result = true;
            }

        } catch (Exception e) {
            System.out.println("PETDAO 조회수 가져오기 에러");
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return result;
    }

    // 수정하기
    public int modifyPet(PetBoard petBoard) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultRow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "update pet set title=?, content=?, filename=?, filepath=?, filename2=?, filepath2=?, filename3=?, filepath3=? where idx=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, petBoard.getTitle());
            pstmt.setString(2, petBoard.getContent());
            pstmt.setString(3, petBoard.getFileName1());
            pstmt.setString(4, petBoard.getFilePath1());
            if (petBoard.getFileName2() != null) {
                pstmt.setString(5, petBoard.getFileName2());
                pstmt.setString(6, petBoard.getFilePath2());
            } else {
                pstmt.setString(5, "");
                pstmt.setString(6, "");
            }

            if (petBoard.getFileName3() != null) {
                pstmt.setString(7, petBoard.getFileName3());
                pstmt.setString(8, petBoard.getFilePath3());
            } else {
                pstmt.setString(7, "");
                pstmt.setString(8, "");
            }
            pstmt.setInt(9, petBoard.getIdx());

            resultRow = pstmt.executeUpdate();


        } catch (Exception e) {
            System.out.println("PETDAO 수정하기 오류");
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return resultRow;
    }

    // 삭제하기
    public int deletePet(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultRow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "UPDATE PET SET TITLE = ? WHERE IDX = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, "deleted");
            pstmt.setString(2, idx);
            resultRow = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("PETDAO 삭제하기 에러");
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return resultRow;
    }

    // 총 댓글 갯수
    public int getPetCommentCount() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalCommentCount = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select count(*) cnt from pet_comment";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                totalCommentCount = rs.getInt("cnt");
            }

        } catch (Exception e) {
            System.out.println("PET DAO 총 댓글 갯수 에러");
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return totalCommentCount;
    }

    // 댓글 가져오기
    public List<PetComment> getPetCommentList(String index) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PetComment> commentList = null;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select no, content, writedate, id, idx from pet_comment where idx=? order by no desc";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, index);
            rs = pstmt.executeQuery();

            commentList = new ArrayList<>();

            while (rs.next()) {
               /* int no = rs.getInt("no");
                String content = rs.getString("content");
                Date writeDate = rs.getDate("writedate");
                String id = rs.getString("id");
                int idx = rs.getInt("idx");

                PetComment petComment = new PetComment(no, content, writeDate, id, idx);
                commentList.add(petComment);*/

                PetComment petComment = new PetComment();

                petComment.setNo(rs.getInt("no"));
                petComment.setContent(rs.getString("content"));
                petComment.setWriteDate(rs.getDate("writedate"));
                petComment.setId(rs.getString("id"));
                petComment.setIdx(rs.getInt("idx"));

                commentList.add(petComment);
            }
        } catch (Exception e) {
            System.out.println("PET DAO 댓글 가져오기 에러");
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return commentList;
    }

    // 댓글 작성
    public int writePetComment(String id, String content, String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultRow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "insert into pet_comment(no, content, id, writedate, idx) values (pet_comment_seq.nextval, ?, ?, sysdate, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, content);
            pstmt.setString(2, id);
            pstmt.setInt(3, Integer.parseInt(idx));

            resultRow = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("PET DAO 댓글 작성 에러");
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return resultRow;
    }

    // 댓글 제거
    public int deletePetComment(int no) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultRow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "delete from pet_comment where no=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, no);
            resultRow = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("PETDAO 댓글 삭제 에러");
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return resultRow;
    }


    //검색기능
    public ArrayList<PetBoard> searchPet(String text, int cpage, int pagesize) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<PetBoard> searchList = new ArrayList<>();
        String sql = "";

        try {
            conn = ConnectionHelper.getConnection("oracle");
            sql = "select * from(SELECT ROWNUM rn, id, address, idx, title, content, hit, writedate, filename, filepath from (select m.id, m.address, p.idx, p.title, p.content, p.hit, p.writedate, p.filename, p.filepath from pet p join member m on(p.id = m.id) and p.title like '%" + text + "%') where rownum <=?) where rn>=?";


            int start = cpage * pagesize - (pagesize - 1); //1 * 5 - (5 - 1) >> 1
            int end = cpage * pagesize; // 1 * 5 >> 5;
            System.out.println("start: " + start);
            System.out.println("end: " + end);
            System.out.println("cpage: " + cpage);

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, end);
            pstmt.setInt(2, start);

            rs = pstmt.executeQuery();


            while (rs.next()) {
                PetBoard pet = new PetBoard();
                System.out.println(rs.getInt("idx"));
                pet.setIdx(rs.getInt("idx"));
                pet.setId(rs.getString("id"));
                pet.setTitle(rs.getString("title"));
                pet.setContent(rs.getString("content"));
                pet.setFileName1(rs.getString("filename"));
                pet.setFilePath1(rs.getString("filepath"));
                pet.setHit(rs.getInt("hit"));
                pet.setWriteDate(rs.getDate("writedate"));
                pet.setAddress(rs.getString("address"));
                System.out.println("객체:" + pet);

                searchList.add(pet);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }


        return searchList;
    }

    public int totalSearchCount(String text) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;

        String sql = "SELECT count(*) cnt FROM (SELECT TITLE FROM pet WHERE TITLE LIKE '%" + text + "%')";

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
            System.out.println(e.getMessage());
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return result;
    }
}
