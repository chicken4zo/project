package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.RestaurantDao;
import kr.or.bit.dto.RestaurantBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestaurantEditService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        RestaurantDao dao = new RestaurantDao();
        String idx = request.getParameter("idx");
        ActionForward forward = null;

        String msg = "";
        String url = "";

        try {
            if (idx == null || idx.trim().equals("")) {
                response.sendRedirect("RestaurantList.board");
            }

            RestaurantDao doa = new RestaurantDao();
            RestaurantBoard board = dao.getEditContent(idx);

            if (board == null) {
                msg = "데이터 오류";
                url = "RestaurantList.board";

                request.setAttribute("board_msg", msg);
                request.setAttribute("board_url", url);

                forward = new ActionForward();
                forward.setRedirect(false);
                forward.setPath("/WEB-INF/views/board/redirect.jsp");
            } else {
                request.setAttribute("idx", idx);
                request.setAttribute("board", board);

                forward = new ActionForward();
                forward.setRedirect(false);
                forward.setPath("/WEB-INF/views/board/restaurantEdit.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return forward;
    }
}
