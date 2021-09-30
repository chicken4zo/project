package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.NoticeDao;
import kr.or.bit.dto.NoticeBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoticeContentService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("서비스왔냐?");
        String idx = request.getParameter("idx");
        ActionForward forward = null;

        try {
            if(idx == null || idx.trim().equals("")){
                response.sendRedirect("NoticeList.board");
            }

            idx = idx.trim();

            String cpage = request.getParameter("cp");
            String pagesize = request.getParameter("ps");

            if(cpage == null || cpage.trim().equals("")) {
                cpage = "1";
            }
            if(pagesize == null || pagesize.trim().equals("")) {
                pagesize = "10";
            }

            NoticeDao dao = new NoticeDao();

            boolean isread = dao.getReadNum(idx);
            if(isread) System.out.println("조회수 증가 : " + isread);

            NoticeBoard board = dao.getContent(Integer.parseInt(idx));
            System.out.println("board : " + board);

            request.setAttribute("cpage", cpage);
            request.setAttribute("pagesize", pagesize);
            request.setAttribute("idx", idx);
            request.setAttribute("board", board);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/noticeContent.jsp");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return forward;
    }
}
