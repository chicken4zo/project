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
        System.out.println("수정 서비스 탔니");
        String idx = request.getParameter("idx");
        ActionForward forward = null;

//        String msg = "";
        String url = "";

        try {
            if (idx == null || idx.trim().equals("")) {
                response.sendRedirect("RestaurantList.board");
            }
            RestaurantDao dao = new RestaurantDao();
            RestaurantBoard board = dao.getEditContent(idx);

            if (board == null) {
//                msg = "수정할 게시글이 존재하지 않습니다.";
                url = "RestaurantList.board";

//                request.setAttribute("board_msg", msg);
                request.setAttribute("board_url", url);

                forward = new ActionForward();
                forward.setRedirect(false);
                forward.setPath("/WEB-INF/views/board/redirect.jsp");
            } else {
                request.setAttribute("idx", idx);
                request.setAttribute("board", board);

                forward = new ActionForward();
                forward.setRedirect(false);
                forward.setPath("/WEB-INF/views/board/RestaurantEdit.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return forward;
    }
}
