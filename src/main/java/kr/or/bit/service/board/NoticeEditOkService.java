package kr.or.bit.service.board;


import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.NoticeDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoticeEditOkService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("EditOkService 들어옴");
        String idx = request.getParameter("idx");
        String title = request.getParameter("title");
        String id = request.getParameter("writer");
        int hit = Integer.parseInt(request.getParameter("hit"));
        String content = request.getParameter("content");
        NoticeDao dao = new NoticeDao();

        String msg ="";
        String url ="";
        ActionForward forward = null;

        try {

            if(idx == null || idx.trim().equals("")) {
                System.out.println("if탐");
                msg = "수정오류";
                url = "noticeList.board";
            } else {
                System.out.println("else탐");
                int result = dao.boardEditOk(request);
                System.out.println("result : " + result);
                if (result > 0) {
                    msg = "success";
                    url = "NoticeList.board";
                } else {
                    msg = "fail";
                    url = "NoticeEdit.board?idx=" + idx;
                }
            }
            request.setAttribute("msg", msg);
            request.setAttribute("url", url);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/board/boardModifyPop.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
