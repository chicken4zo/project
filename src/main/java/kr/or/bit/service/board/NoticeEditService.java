package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.NoticeDao;
import kr.or.bit.dto.NoticeBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoticeEditService implements Action {


    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String idx = request.getParameter("idx");
        ActionForward forward = null;

        String msg = "";
        String url = "";


        try {
            if(idx == null || idx.trim().equals("")) {
                response.sendRedirect("NoticeList.board");
            }
            NoticeDao dao = new NoticeDao();
            NoticeBoard board = dao.getEditContent(idx);

            if(board == null) {
                msg = "수정할 데이터가 존재하지 않습니다.";
                url = "NoticeList.board";

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
                forward.setPath("/WEB-INF/views/board/noticeEdit.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
