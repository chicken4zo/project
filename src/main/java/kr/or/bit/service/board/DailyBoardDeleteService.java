package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.DailyDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DailyBoardDeleteService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String idx = request.getParameter("idx");
        DailyDao dao = new DailyDao();
        int result = dao.deleteDaily(idx);

        String msg = "";
        String url = "";

        if (result > 0) {
            msg = "success";
            url = "dailyList.board";
        } else {
            msg = "fail";
            url = "dailyList.board";
        }

        request.setAttribute("msg", msg);
        request.setAttribute("url", url);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/board/boardDeletePop.jsp");

        return forward;
    }
}
