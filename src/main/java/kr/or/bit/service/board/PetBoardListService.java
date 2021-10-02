package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.PetDao;
import kr.or.bit.dto.PetBoard;
import kr.or.bit.util.ThePager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PetBoardListService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = null;

        try {
            PetDao dao = new PetDao();

            String ps = request.getParameter("ps");
            String cp = request.getParameter("cp");
            int totalBoardCount = dao.totalPetCount();

            if (ps == null || ps.trim().equals("")) {
                ps = "9";
            }

            if (cp == null || cp.trim().equals("")) {
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

            List<PetBoard> petBoardList = dao.getPetBoardList(cpage, pagesize);

            int pagersize = 3;
            ThePager pager = new ThePager(totalBoardCount, cpage, pagesize, pagersize, "petList.board");

            request.setAttribute("petBoardList", petBoardList);
            request.setAttribute("pagesize", pagesize);
            request.setAttribute("cpage", cpage);
            request.setAttribute("pager", pager);
            request.setAttribute("pagecount", pagecount);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/petList.jsp");
        } catch (Exception e) {
            System.out.println("PRODUCTBOARDLIST SERVICE 에러");
            e.printStackTrace();
        }

        return forward;
    }
}
