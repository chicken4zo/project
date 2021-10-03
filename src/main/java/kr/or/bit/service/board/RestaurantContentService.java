package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.RestaurantDao;
import kr.or.bit.dto.RestaurantBoard;
import kr.or.bit.dto.RestaurantComment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class RestaurantContentService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("맛집서비스들어옴");
        String id = request.getParameter("id");
        String idx = request.getParameter("idx");
        String cpage = request.getParameter("cp");
        String pagesize = request.getParameter("ps");
        ActionForward forward = null;

        try {
            if (idx == null || idx.trim().equals("")) {
                response.sendRedirect("RestaurantList.board");
            }

            idx = idx.trim();

            if (cpage == null || cpage.trim().equals("")) {
                cpage = "1";
            }

            if (pagesize == null || pagesize.trim().equals("")) {
                pagesize = "10";
            }

            List<RestaurantComment> commentList = new ArrayList<>();
            RestaurantDao dao = new RestaurantDao();
            boolean isread = false;
            isread = dao.getReadNum(idx);
            if (isread) commentList = dao.getRestaurantCommentList(idx);

            RestaurantBoard board = dao.getContent(idx);
            System.out.println("contentservice들어있냐? : " + board);

            request.setAttribute("cpage", cpage);
            request.setAttribute("pagesize", pagesize);
            request.setAttribute("idx", idx);
            request.setAttribute("board", board);
            request.setAttribute("commentList", commentList);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/restaurantContent.jsp");

        } catch (Exception e) {
            System.out.println("상세서비스오류 : " + e.getMessage());
            e.printStackTrace();
        }

        return forward;
    }

}
