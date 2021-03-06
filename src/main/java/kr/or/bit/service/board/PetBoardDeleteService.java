package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.PetDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PetBoardDeleteService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String idx = request.getParameter("idx");
        PetDao dao = new PetDao();
        int result = dao.deletePet(idx);

        String msg = "";
        String url = "";

        if (result > 0) {
            msg = "success";
            url = "petList.board";
        } else {
            msg = "fail";
            url = "petList.board";
        }

        request.setAttribute("msg", msg);
        request.setAttribute("url", url);


        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/board/boardDeletePop.jsp");

        return forward;
    }
}
