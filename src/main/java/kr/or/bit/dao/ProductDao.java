package kr.or.bit.dao;

import kr.or.bit.dto.ProductBoard;
import kr.or.bit.dto.ProductComment;
import kr.or.bit.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
            String sql = "select m.id, m.address, p.idx, p.title, p.content, p.hit, p.writedate, p.filename, p.filepath, p.price from product p join member m on(p.id = m.id) order by idx desc";
            pstmt = conn.prepareStatement(sql);
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
            pstmt.setString(7, productBoard.getFileName2());
            pstmt.setString(8, productBoard.getFilePath2());
            pstmt.setString(9, productBoard.getFileName3());
            pstmt.setString(10, productBoard.getFilePath3());

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
                System.out.println(productBoard.getFileName3());
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
            pstmt.setString(6, productBoard.getFileName2());
            pstmt.setString(7, productBoard.getFilePath2());
            pstmt.setString(8, productBoard.getFileName3());
            pstmt.setString(9, productBoard.getFilePath3());
            pstmt.setInt(10, productBoard.getIdx());
            resultRow = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("PRODUCTDAO MODIFYPRODUCT 에러");
            System.out.println(e);
        }

        return resultRow;
    }

    public int deleteProduct(String idx) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultRow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "delete from product where idx=?";
            pstmt = conn.prepareStatement(sql);

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

    // 댓글 가져오기
    public List<ProductComment> getProductCommentList(String index) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ProductComment> commentList = null;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(index));
            rs = pstmt.executeQuery();

            commentList = new ArrayList<>();

            while (rs.next()) {

            }
        } catch (Exception e) {
            System.out.println("PRODUCTDAO GETPRODUCTCOMMENTLIST 에러");
            System.out.println(e);
        }

        return commentList;
    }
}
