package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.NoticeDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoticeDeleteService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String idx = request.getParameter("idx");
        System.out.println("서비스왔다");
        System.out.println("idx : " + idx);

        ActionForward forward = null;
        NoticeDao dao = new NoticeDao();

        try {
            int result = dao.NoticeDelete(idx);
            String msg = "";
            String url = "";
//            if(idx == null || idx.trim().equals("")) {
//                msg = "프로그램 오류";
//                url = "NoticeList.board";
//            }
            if (result > 0) {
                msg = "success";
                url = "NoticeList.board";
            } else {
                msg = "fail";
                url = "NoticeList.board";
            }

            request.setAttribute("msg", msg);
            request.setAttribute("url", url);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/boardDeletePop.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
