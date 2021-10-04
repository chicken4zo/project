package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.ProductDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductBoardDeleteService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String idx = request.getParameter("idx");
        ProductDao dao = new ProductDao();

        int result = dao.deleteProduct(idx);

//        ActionForward forward = new ActionForward();
//        forward.setRedirect(false);
//        forward.setPath("productList.board");

    /*    String url = "";
        if (result > 0) {
            url = "productList.board";
        } else {
            url = "productList.board";
        }

        request.setAttribute("board_url", url);*/

        /*ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/redirect.jsp");*/

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("productList.board");

        return forward;
    }
}
