package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.RestaurantDao;
import kr.or.bit.dto.RestaurantBoard;
import kr.or.bit.util.ThePager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

public class RestaurantBoardListService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = null;

        try {
            RestaurantDao dao = new RestaurantDao();
            int totalBoardCount = dao.totalBoardCount();

            String ps = request.getParameter("ps");
            String cp = request.getParameter("cp");

            if (ps == null || ps.trim().equals("")) {
                ps = "10";
            }
            if (cp == null || ps.trim().equals("")) {
                cp = "1";
            }

            int pagesize = Integer.parseInt(ps);
            int cpage = Integer.parseInt(cp);
            int pagecount = 0;

            if (totalBoardCount % pagesize == 0) {
                pagecount = totalBoardCount / pagesize;
            } else {
                pagecount = (totalBoardCount / pagesize) + 1;
            }

            List<RestaurantBoard> list = dao.list(cpage, pagesize);


            PrintWriter out = response.getWriter();
            if (list == null || list.size() == 0) {
                out.print("데이터가 존재하지 않습니다.");
            }

            int pagersize = 10;
            ThePager pager = new ThePager(totalBoardCount, cpage, pagesize, pagersize, "RestaurantList.board");

            request.setAttribute("pagesize", pagesize);
            request.setAttribute("cpage", cpage);
            request.setAttribute("pagecount", pagecount);
            request.setAttribute("list", list);
            request.setAttribute("totalBoardCount", totalBoardCount);
            request.setAttribute("pager", pager);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/restaurantList.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return forward;
    }
}
