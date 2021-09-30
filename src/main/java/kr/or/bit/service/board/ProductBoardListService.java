package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.ProductDao;
import kr.or.bit.dto.ProductBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductBoardListService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

        ActionForward forward = null;

        try {
            ProductDao dao = new ProductDao();

            String ps = request.getParameter("ps");
            String cp = request.getParameter("cp");
            int totalBoardCount = dao.totalProductBoard();

            if (ps == null || ps.trim().equals("")) {
                ps = "5";
            }

            if (cp == null || cp.trim().equals("")) {
                cp = "1";
            }

            int pagesize = Integer.parseInt(ps);
            int cpage = Integer.parseInt(cp);
            int pagecount = 0;

            if (totalBoardCount % pagesize == 0) {
                pagecount = totalBoardCount / pagesize;
            } else {
                pagecount = (totalBoardCount / pagesize) + 1;
            }

            List<ProductBoard> productBoardList = dao.getProductBoardList(cpage, pagesize);

            request.setAttribute("productBoardList", productBoardList);
            request.setAttribute("pagesize", pagesize);
            request.setAttribute("cpage", cpage);
            request.setAttribute("pagecount", pagecount);
            request.setAttribute("totalBoardCount", totalBoardCount);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/productList.jsp");
        } catch (Exception e) {
            System.out.println("PRODUCTBOARDLIST SERVICE 에러");
        }

        return forward;
    }
}
