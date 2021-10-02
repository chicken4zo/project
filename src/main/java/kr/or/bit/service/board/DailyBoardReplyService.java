package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DailyBoardReplyService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

        String idx = request.getParameter("idx");
        String cpage = request.getParameter("cp");
        String pagesize = request.getParameter("ps");

        try {
            if (idx == null || idx.trim().equals("")) {
                response.sendRedirect("dailyList.board");
                return null;
            }

            if (cpage == null || pagesize == null) {
                cpage = "1";
                pagesize = "5";
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        request.setAttribute("idx", idx);
        request.setAttribute("cp", cpage);
        request.setAttribute("ps", pagesize);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/board/dailyReply.jsp");
        return forward;
    }
}
