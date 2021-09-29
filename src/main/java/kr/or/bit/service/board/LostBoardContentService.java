package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.LostDao;
import kr.or.bit.dto.LostBoard;
import kr.or.bit.dto.LostComment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class LostBoardContentService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String cpage = request.getParameter("cp");
        String pagesize = request.getParameter("ps");
        String idx = request.getParameter("idx");

        List<LostComment> commentList = new ArrayList<>();
        boolean isRead = false;
        LostBoard lost = new LostBoard();

        LostDao dao = new LostDao();
        lost = dao.viewLostContent(idx);
        request.setAttribute("lost", lost);
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
            commentList = dao.getLostCommentList(idx);
        }

        request.setAttribute("commentList", commentList);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/board/lostContent.jsp");

        return forward;
    }
}
