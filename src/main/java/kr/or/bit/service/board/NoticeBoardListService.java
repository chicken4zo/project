package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.NoticeDao;
import kr.or.bit.dto.NoticeBoard;
import kr.or.bit.util.ThePager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

public class NoticeBoardListService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = null;

        try {
            NoticeDao dao = new NoticeDao();
            int totalNoticeCount = dao.totalNoticeCount();

            String ps = request.getParameter("ps");
            String cp = request.getParameter("cp");

            if(cp == null || cp.trim().equals("")) {
                ps = "10";
            }

            if(cp == null || cp.trim().equals("")) {
                cp = "1";
            }

            int pagesize = Integer.parseInt(ps);
            int cpage = Integer.parseInt(cp);
            int pagecount = 0;

            if(totalNoticeCount % pagesize == 0) {
                pagecount = totalNoticeCount / pagesize;
            } else {
                pagecount = (totalNoticeCount / pagesize) + 1;
            }

            List<NoticeBoard> list = dao.list(cpage, pagesize);

            PrintWriter out = response.getWriter();

            if(list == null || list.size() == 0) {
                out.print("데이터가 없습니다.");
            }

            int pagersize = 10;
            ThePager pager = new ThePager(totalNoticeCount, cpage, pagersize, pagersize, "NoticeList.board");

            request.setAttribute("pagesize", pagesize);
            request.setAttribute("cpage", cpage);
            request.setAttribute("pagecount", pagecount);
            request.setAttribute("list", list);
            request.setAttribute("totalNoticeCount", totalNoticeCount);
            request.setAttribute("pager", pager);


            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/noticeList.jsp");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return forward;
    }
}
