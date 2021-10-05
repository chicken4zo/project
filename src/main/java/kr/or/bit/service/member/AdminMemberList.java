package kr.or.bit.service.member;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.MemberDao;
import kr.or.bit.dto.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminMemberList implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("여기왔나요?");
        ActionForward forward = null;
        try {
            MemberDao memberDao = new MemberDao();
            List<Member> memberList = memberDao.GetMemberList();
            request.setAttribute("memberList", memberList);

            forward = new ActionForward();
            forward.setRedirect(false); //forward
            forward.setPath("/WEB-INF/views/admin.jsp");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return forward;

    }

}
