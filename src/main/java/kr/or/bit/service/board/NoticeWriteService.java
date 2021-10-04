package kr.or.bit.service.board;


import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.NoticeDao;
import kr.or.bit.dto.NoticeBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class NoticeWriteService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        NoticeBoard noticeBoard = new NoticeBoard();
        System.out.println("서비스왔냐");
        noticeBoard.setId(request.getParameter("id"));
        noticeBoard.setTitle(request.getParameter("title"));
        noticeBoard.setContent(request.getParameter("content"));
        System.out.println(request.getParameter("id"));
        System.out.println(request.getParameter("title"));
        System.out.println(request.getParameter("content"));

        NoticeDao dao = new NoticeDao();
        int result = dao.writeOk(noticeBoard);
        System.out.println("result : " + result);


        String msg = "";
        String url = "";
        if (result > 0) {
            msg = "공지사항 등록이 완료되었습니다.";
            url = "NoticeList.board";
        } else {
            msg = "오류가 발생하였습니다.";
            url = "NoticeWrite.board";
        }

        request.setAttribute("board_msg", msg);
        request.setAttribute("board_url", url);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/board/redirect.jsp");

        return forward;
    }
}
