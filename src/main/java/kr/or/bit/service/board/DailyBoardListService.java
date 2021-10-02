package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.DailyDao;
import kr.or.bit.dto.DailyBoard;
import kr.or.bit.util.ThePager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class DailyBoardListService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String ps = request.getParameter("ps");
        String cp = request.getParameter("cp");

//        HttpSession session = request.getSession();
//        String id = (String) session.getAttribute("userid");


        // List 페이지 처음 호출 ...
        if (ps == null || ps.trim().equals("")) {
            // default 값 설정
            ps = "5"; // 5개씩
        }

        if (cp == null || cp.trim().equals("")) {
            // default 값 설정
            cp = "1"; // 1번째 페이지 보겠다
        }

        int pagesize = Integer.parseInt(ps);
        int cpage = Integer.parseInt(cp);
        int pagecount = 0;

        DailyDao dao = new DailyDao();

        int totalDailyCount = dao.totalDailyCount();

        ArrayList<DailyBoard> dailyList = dao.getDailyList(cpage, pagesize);
        System.out.println(dailyList);

        if (totalDailyCount % pagesize == 0) {
            pagecount = totalDailyCount / pagesize;
        } else {
            pagecount = (totalDailyCount / pagesize) + 1;
        }

        int pagersize = 3;
        ThePager pager = new ThePager(totalDailyCount, cpage, pagesize, pagersize, "dailyList.board");

        request.setAttribute("dailyList", dailyList);
        request.setAttribute("cpage", cpage);
        request.setAttribute("pager", pager);
        request.setAttribute("pagesize", pagesize);
        request.setAttribute("pagecount", pagecount);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/board/dailyList.jsp");
        return forward;
    }
}
