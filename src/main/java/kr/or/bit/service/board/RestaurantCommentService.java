package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.RestaurantDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestaurantCommentService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

        String idx = request.getParameter("idx");
        String id = request.getParameter("id");
        String content = request.getParameter("content");

        ActionForward forward = null;
        try {
            RestaurantDao dao = new RestaurantDao();
            int result = dao.commentWrite(Integer.parseInt(idx), id, content);

            String msg = "";
            String url = "";

            if (result > 0) {
                msg = "댓글 입력이 완료되었습니다.";
                url = "RestaurantContent.board?idx=" + idx;
            } else {
                msg = "댓글 입력 오류";
                url = "RestaurantContent.board?idx=" + idx;
            }

            request.setAttribute("board_msg", msg);
            request.setAttribute("board_url", url);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/board/views/redirect.jsp");

        } catch (Exception e) {
            System.out.println("댓글서비스 오류 :" + e.getMessage());
            e.printStackTrace();
        }
        return forward;
    }
}
