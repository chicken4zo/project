package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.LostDao;
import kr.or.bit.dto.LostBoard;
import kr.or.bit.util.ThePager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class LostBoardListService implements Action {
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

        LostDao dao = new LostDao();

        int totalLostCount = dao.totalLostCount();

        ArrayList<LostBoard> lostList = dao.getLostList(cpage, pagesize);
        System.out.println(lostList);

        if (totalLostCount % pagesize == 0) {
            pagecount = totalLostCount / pagesize;
        } else {
            pagecount = (totalLostCount / pagesize) + 1;
        }

        int pagersize = 3;
        ThePager pager = new ThePager(totalLostCount, cpage, pagesize, pagersize, "lostList.board");

        request.setAttribute("lostList", lostList);
        request.setAttribute("cpage", cpage);
        request.setAttribute("pager", pager);
        request.setAttribute("pagesize", pagesize);
        request.setAttribute("pagecount", pagecount);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/board/lostList.jsp");
        return forward;
    }
}
