package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.DailyDao;
import kr.or.bit.dao.LostDao;
import kr.or.bit.dto.DailyBoard;
import kr.or.bit.dto.LostBoard;
import kr.or.bit.util.ThePager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class SearchBoardService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String boardName = request.getParameter("boardName");
        String searchText = request.getParameter("searchText");
        System.out.println(boardName);

        String ps = request.getParameter("ps");
        String cp = request.getParameter("cp");

        ThePager pager = null;

        String path = null;

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

        if (boardName.equals("daily")) {
            DailyDao dailyDao = new DailyDao();
            ArrayList<DailyBoard> list = dailyDao.searchDaily(searchText, cpage, pagesize);
            int result = dailyDao.totalSearchCount(searchText);
            System.out.println("검색결과 건수" + result);

            if (result % pagesize == 0) {
                pagecount = result / pagesize;
            } else {
                pagecount = (result / pagesize) + 1;
            }

            int pagersize = 3;
            pager = new ThePager(result, cpage, pagesize, pagersize, "search.board");

            request.setAttribute("dailyList", list);
            request.setAttribute("pager", pager);

            path = "/WEB-INF/views/board/dailyList.jsp";

        } else if (boardName.equals("lost")) {
            LostDao lostDao = new LostDao();
            ArrayList<LostBoard> list = lostDao.searchLost(searchText, cpage, pagesize);
            int result = lostDao.totalSearchCount(searchText);
            System.out.println("검색결과 건수" + result);

            if (result % pagesize == 0) {
                pagecount = result / pagesize;
            } else {
                pagecount = (result / pagesize) + 1;
            }

            int pagersize = 3;
            pager = new ThePager(result, cpage, pagesize, pagersize, "search.board");
            System.out.println(pager);

            request.setAttribute("lostList", list);
            request.setAttribute("pager", pager);

            path = "/WEB-INF/views/board/lostList.jsp";

        } else if (boardName.equals("product")) {

        } else if (boardName.equals("pet")) {

        } else if (boardName.equals("restaurant")) {

        } else {

        }

        request.setAttribute("cpage", cpage);
        request.setAttribute("pagesize", pagesize);
        request.setAttribute("pagecount", pagecount);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath(path);
        return forward;
    }
}
