package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.RestaurantDao;
import kr.or.bit.dto.RestaurantBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestaurantContentService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("맛집서비스들어옴");
        String idx = request.getParameter("idx");
        ActionForward forward = null;

        try {
            if (idx == null || idx.trim().equals("")) {
                response.sendRedirect("RestaurantList.board");
            }

            idx = idx.trim();

            String cpage = request.getParameter("cp");
            String pagesize = request.getParameter("ps");

            if (cpage == null || cpage.trim().equals("")) {
                cpage = "1";
            }
            if (pagesize == null || pagesize.trim().equals("")) {
                pagesize = "10";
            }

            RestaurantDao dao = new RestaurantDao();
            boolean isread = dao.getReadNum(idx);
            if (isread) System.out.println("조회수 증가 : " + isread);

            RestaurantBoard board = dao.getContent(Integer.parseInt(idx));
            System.out.println("contentservice들어있냐? : " + board);


            request.setAttribute("cpage", cpage);
            request.setAttribute("pagesize", pagesize);
            request.setAttribute("idx", idx);
            request.setAttribute("board", board);

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
