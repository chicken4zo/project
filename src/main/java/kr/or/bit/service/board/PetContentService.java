package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.PetDao;
import kr.or.bit.dto.PetBoard;
import kr.or.bit.dto.PetComment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class PetContentService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String idx = request.getParameter("idx");
        String cpage = request.getParameter("cp");
        String pagesize = request.getParameter("ps");
        ActionForward forward = null;
        List<PetComment> commentList = new ArrayList<>();
        boolean isRead;

        try {
            if (idx == null || idx.trim().equals("")) {
                response.sendRedirect("petList.board");
            }

            idx = idx.trim();

            //List 페이지 처음 호출
            if (cpage == null || cpage.trim().equals("")) {
                //default 값 설정
                cpage = "1";
            }

            if (pagesize == null || pagesize.trim().equals("")) {
                //default 값 설정
                pagesize = "9";
            }

            PetDao dao = new PetDao();
            PetBoard pet = dao.getPetBoardContent(idx);

            System.out.println(pet);

            request.setAttribute("pet", pet);
            request.setAttribute("cpage", cpage);
            request.setAttribute("pagesize", pagesize);
            isRead = dao.getHit(idx);

            if (isRead) {
                commentList = dao.getPetCommentList(idx);
            }

            request.setAttribute("commentList", commentList);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/petContent.jsp");
        } catch (Exception e) {
            System.out.println("PETCONTENT SERVICE 에러");
            e.printStackTrace();
        }

        return forward;
    }
}
