package kr.or.bit.service.member;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.MemberDao;
import kr.or.bit.dto.LostBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class MemberInfoService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String loginId = (String) session.getAttribute("id");
        List<LostBoard> lost = new ArrayList<>();
        System.out.println("loginId" + loginId);

        MemberDao dao = new MemberDao();
        lost = dao.searchLostById(loginId);

        request.setAttribute("lostList", lost);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/mypage.jsp");
        return forward;
    }
}
