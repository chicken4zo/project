package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.ProductDao;
import kr.or.bit.dto.ProductBoard;
import kr.or.bit.dto.ProductComment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ProductContentService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

        String idx = request.getParameter("idx");
        String cpage = request.getParameter("cp");
        String pagesize = request.getParameter("ps");
        ActionForward forward = null;
        List<ProductComment> commentList = new ArrayList<>();
        boolean isRead = false;

        try {
            if (idx == null || idx.trim().equals("")) {
                response.sendRedirect("productList.board");
            }

            idx = idx.trim();

            //List 페이지 처음 호출
            if (cpage == null || cpage.trim().equals("")) {
                //default 값 설정
                cpage = "1";
            }

            if (pagesize == null || pagesize.trim().equals("")) {
                //default 값 설정
                pagesize = "5";
            }

            ProductDao dao = new ProductDao();
            ProductBoard product = dao.getProductContent(idx);

            System.out.println(product);

            request.setAttribute("product", product);
            request.setAttribute("cpage", cpage);
            request.setAttribute("pagesize", pagesize);
            isRead = dao.getHit(idx);

            if (isRead) {
                commentList = dao.getProductCommentList(idx);
            }

            request.setAttribute("commentList", commentList);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/productContent.jsp");
        } catch (Exception e) {
            System.out.println("PRODUCTCONTENT SERVICE 에러");
            System.out.println(e);
        }

        return forward;
    }
}
