package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.DailyDao;
import kr.or.bit.dto.DailyBoard;
import kr.or.bit.dto.DailyComment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class DailyBoardContentService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");
        String cpage = request.getParameter("cp");
        String pagesize = request.getParameter("ps");
        String idx = request.getParameter("idx");

        List<DailyComment> commentList = new ArrayList<>();
        boolean isRead = false;
        DailyBoard daily = new DailyBoard();

        DailyDao dao = new DailyDao();
        daily = dao.viewDailyContent(idx);
        request.setAttribute("daily", daily);
        request.setAttribute("cpage", cpage);
        request.setAttribute("pagesize", pagesize);

        if (cpage == null || cpage.trim().equals("")) {
            cpage = "1";
        }

        if (pagesize == null || pagesize.trim().equals("")) {
            //default 값 설정
            pagesize = "5";
        }

        isRead = dao.getHit(idx);

        if (isRead) {
            commentList = dao.getDailyCommentList(idx);
        }

        request.setAttribute("commentList", commentList);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/board/dailyContent.jsp");

        return forward;
    }
}
