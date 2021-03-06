package kr.or.bit.service.member;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.MemberDao;
import kr.or.bit.dto.Member;
import kr.or.bit.dto.ProductBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class MemberInfoService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("여기왔나요?");

        ActionForward forward = null;
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        System.out.println("userid : " + session.getAttribute("id"));
        try {
            MemberDao memberDao = new MemberDao();
            Member memberDto = memberDao.GetMemberListById(id);
            List<ProductBoard> productBoardLIst = memberDao.searchProductById(id);
            request.setAttribute("memberDto", memberDto);
            request.setAttribute("list", productBoardLIst);

            forward = new ActionForward();
            forward.setRedirect(false); //forward
            forward.setPath("/WEB-INF/views/mypage.jsp");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return forward;


    }
}

