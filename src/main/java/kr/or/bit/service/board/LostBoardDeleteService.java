package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.LostDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LostBoardDeleteService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String idx = request.getParameter("idx");
        LostDao dao = new LostDao();
        int result = dao.deleteLost(idx);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("lostList.board");

        return forward;
    }
}
