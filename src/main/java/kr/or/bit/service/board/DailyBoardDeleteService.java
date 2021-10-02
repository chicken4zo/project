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

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("dailyList.board");

        return forward;
    }
}
