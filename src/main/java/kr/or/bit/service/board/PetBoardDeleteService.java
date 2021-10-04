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
            msg = "삭제 실패되었습니다.";
            url = "petList.board";
        } else {
            msg = "삭제가 완료되었습니다.";
            url = "petList.board";
        }

        request.setAttribute("board_msg", msg);
        request.setAttribute("board_url", url);


        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/board/boardDeletePop.jsp");

        return forward;
    }
}
