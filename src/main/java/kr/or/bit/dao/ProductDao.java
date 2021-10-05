package kr.or.bit.dao;

import kr.or.bit.dto.ProductBoard;
import kr.or.bit.dto.ProductComment;
import kr.or.bit.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDao {

    // 리스트 불러오기
    public List<ProductBoard> getProductBoardList(int cpage, int pagesize) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProductBoard> list = null;

        try {
            conn = ConnectionHelper.getConnection("oracle");

            // 상품게시판과 멤버의 정보를 idx desc 정렬
            // String sql = "select * from (select rownum rn, m.id, m.address, p.idx, p.title, p.content, p.hit, p.writedate, p.filename, p.filepath, p.price from product p join member m on(p.id = m.id) order by idx desc) where rn between ? and ? ";
            String sql = "select * from(SELECT ROWNUM rn, id,  address, idx, title, content, hit, writedate, filename, filepath, price from (select m.id, m.address, p.idx, p.title, p.content, p.hit, p.writedate, p.filename, p.filepath, p.price from product p join member m on(p.id = m.id) order by idx desc) where rownum <=?) where rn>=?";

            pstmt = conn.prepareStatement(sql);

            //공식같은 로직
            int start = cpage * pagesize - (pagesize - 1); //1 * 5 - (5 - 1) >> 1
            int end = cpage * pagesize; // 1 * 5 >> 5

            pstmt.setInt(1, end);
            pstmt.setInt(2, start);

            rs = pstmt.executeQuery();

            list = new ArrayList<>();

            while (rs.next()) {
                ProductBoard productBoard = new ProductBoard();
                productBoard.setIdx(rs.getInt("idx"));
                productBoard.setTitle(rs.getString("title"));
                productBoard.setContent(rs.getString("content"));
                productBoard.setHit(rs.getInt("hit"));
                productBoard.setWriteDate(rs.getDate("writedate"));
                productBoard.setFileName1(rs.getString("filename"));
                productBoard.setFilePath1(rs.getString("filepath"));
                productBoard.setPrice(rs.getInt("price"));
                productBoard.setId(rs.getString("id"));
                productBoard.setAddress(rs.getString("address"));

                list.add(productBoard);
            }
        } catch (Exception e) {
            System.out.println("PRODUCTDAO LIST 에러");
            System.out.println(e);
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return list;
    }

    // 게시물 총 건수
    public int totalProductBoard() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalCount = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select count(*) cnt from product";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                totalCount = rs.getInt("cnt");
            }
        } catch (Exception e) {
            System.out.println("PRODUCTDAO TOTAL 에러");
            System.out.println(e);
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return totalCount;
    }

    // 글 쓰기
    public int writeProductBoard(ProductBoard productBoard) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultRow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "insert into product(idx, title, id,writedate,price,hit,content," +
                    "filename,filepath,filename2,filepath2,filename3,filepath3)" +
                    "values (product_seq.nextval,?,?,sysdate,?,0,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, productBoard.getTitle());
            pstmt.setString(2, productBoard.getId());
            pstmt.setInt(3, productBoard.getPrice());
            pstmt.setString(4, productBoard.getContent());
            pstmt.setString(5, productBoard.getFileName1());
            pstmt.setString(6, productBoard.getFilePath1());

            if (productBoard.getFileName2() != null) {
                pstmt.setString(7, productBoard.getFileName2());
                pstmt.setString(8, productBoard.getFilePath2());
            } else {
                pstmt.setString(7, "");
                pstmt.setString(8, "");
            }
            if (productBoard.getFileName3() != null) {
                pstmt.setString(9, productBoard.getFileName3());
                pstmt.setString(10, productBoard.getFilePath3());
            } else {
                pstmt.setString(9, "");
                pstmt.setString(10, "");
            }

            resultRow = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("PRODUCTDAO 글쓰기 에러");
            System.out.println(e);
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return resultRow;
    }

    // 상세보기
    public ProductBoard getProductContent(String index) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ProductBoard productBoard = null;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select m.id, m.address, p.idx, p.title, p.content, p.hit, p.writedate, p.filename,p.filename2,p.filename3, p.filepath,p.filepath2,p.filepath3, p.price from product p join member m on(p.id = m.id) where idx=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, index);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                productBoard = new ProductBoard();
                productBoard.setId(rs.getString("id"));
                productBoard.setAddress(rs.getString("address"));
                productBoard.setIdx(rs.getInt("idx"));
                productBoard.setTitle(rs.getString("title"));
                productBoard.setContent(rs.getString("content"));
                productBoard.setHit(rs.getInt("hit"));
                productBoard.setPrice(rs.getInt("price"));
                productBoard.setWriteDate(rs.getDate("writedate"));
                productBoard.setFileName1(rs.getString("filename"));
                productBoard.setFilePath1(rs.getString("filepath"));
                productBoard.setFileName2(rs.getString("filename2"));
                productBoard.setFilePath2(rs.getString("filepath2"));
                productBoard.setFileName3(rs.getString("filename3"));
                productBoard.setFilePath3(rs.getString("filepath3"));
            }
        } catch (Exception e) {
            System.out.println("PRODUCTDAO GET CONTENT 에러");
            System.out.println(e);
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return productBoard;
    }

    // 수정하기
    public int modifyProduct(ProductBoard productBoard) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultRow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "update product set title=?, content=?,price=?,filename=?,filepath=?,filename2=?,filepath2=?,filename3=?,filepath3=? where idx=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productBoard.getTitle());
            pstmt.setString(2, productBoard.getContent());
            pstmt.setInt(3, productBoard.getPrice());
            pstmt.setString(4, productBoard.getFileName1());
            pstmt.setString(5, productBoard.getFilePath1());
            if (productBoard.getFileName2() != null) {
                pstmt.setString(6, productBoard.getFileName2());
                pstmt.setString(7, productBoard.getFilePath2());
            } else {
                pstmt.setString(6, "");
                pstmt.setString(7, "");
            }

            if (productBoard.getFileName3() != null) {
                pstmt.setString(8, productBoard.getFileName3());
                pstmt.setString(9, productBoard.getFilePath3());
            } else {
                pstmt.setString(8, "");
                pstmt.setString(9, "");
            }

            pstmt.setInt(10, productBoard.getIdx());
            resultRow = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("PRODUCTDAO MODIFYPRODUCT 에러");
            System.out.println(e.getMessage());
        }

        return resultRow;
    }

    // 제거하기
    public int deleteProduct(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultRow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "UPDATE PRODUCT SET TITLE = ? WHERE IDX = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "deleted");
            pstmt.setString(2, idx);

            resultRow = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("PRODUCTDAO DELETE 에러");
            System.out.println(e);
        }

        return resultRow;
    }

    // 조회수 가져오기
    public boolean getHit(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "update product set hit = hit +1 where idx =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idx);

            int resultRow = pstmt.executeUpdate();

            if (resultRow > 0) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println("PRODUCTDAO GETHIT 에러");
            System.out.println(e);
        }

        return result;
    }

    // 총 댓글 갯수
    public int getPetCommentCount() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalCommentCount = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select count(*) cnt from product_comment";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                totalCommentCount = rs.getInt("cnt");
            }

        } catch (Exception e) {
            System.out.println("PRODUCT DAO 총 댓글 갯수 에러");
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return totalCommentCount;
    }

    // 댓글 가져오기
    public List<ProductComment> getProductCommentList(String index) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ProductComment> commentList = null;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "select no, content, writedate, id, idx from product_comment where idx=? order by no desc";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(index));
            rs = pstmt.executeQuery();

            commentList = new ArrayList<>();

            while (rs.next()) {
                int no = rs.getInt("no");
                String content = rs.getString("content");
                Date writeDate = rs.getDate("writedate");
                String id = rs.getString("id");
                int idx = rs.getInt("idx");

                ProductComment comment = new ProductComment(no, content, writeDate, id, idx);
                commentList.add(comment);
            }
        } catch (Exception e) {
            System.out.println("PRODUCTDAO GET PRODUCTCOMMENTLIST 에러");
            System.out.println(e);
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return commentList;
    }

    // 댓글 작성
    public int writeProductComment(String id, String content, String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultRow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "insert into product_comment(no, content, id, writedate, idx) values (product_comment_seq.nextval, ?, ?, sysdate, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, content);
            pstmt.setString(2, id);
            pstmt.setInt(3, Integer.parseInt(idx));

            resultRow = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("PRODUCT DAO 댓글 작성 에러");
            System.out.println(e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return resultRow;
    }

    // 댓글 제거
    public int deleteProductComment(int no) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultRow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "delete from product_comment where no=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);

            resultRow = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("PRODUCTDAO DELETE COMMENT 에러");
            System.out.println(e);
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return resultRow;
    }

    //검색
    public ArrayList<ProductBoard> searchProduct(String text, int cpage, int pagesize) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ProductBoard> searchList = new ArrayList<>();
        String sql = "";

        try {
            conn = ConnectionHelper.getConnection("oracle");

            sql = "select * from(SELECT ROWNUM rn, id, address, idx, title, content, hit, writedate, filename, filepath, price from (select m.id, m.address, p.idx, p.title, p.content, p.hit, p.writedate, p.filename, p.filepath, p.price from product p join member m on(p.id = m.id) and p.title like '%" + text + "%' order by idx desc) where rownum <=?) where rn>=?";

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
                ProductBoard product = new ProductBoard();
                System.out.println(rs.getInt("idx"));
                product.setIdx(rs.getInt("idx"));
                product.setId(rs.getString("id"));
                product.setTitle(rs.getString("title"));
                product.setContent(rs.getString("content"));
                product.setFileName1(rs.getString("filename"));
                product.setFilePath1(rs.getString("filepath"));
                product.setHit(rs.getInt("hit"));
                product.setWriteDate(rs.getDate("writedate"));
                product.setPrice(rs.getInt("price"));
                product.setAddress(rs.getString("address"));
                System.out.println("객체:" + product);
                searchList.add(product);
            }


        } catch (Exception e) {
            System.out.println("productSearchDao 오류 :" + e.getMessage());
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return searchList;
    }

    //검색개수
    public int totalSearchCount(String text) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;

        String sql = "SELECT count(*) cnt FROM (SELECT TITLE FROM product WHERE TITLE LIKE '%" + text + "%')";

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
