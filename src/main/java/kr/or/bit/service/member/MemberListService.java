package kr.or.bit.service.member;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.MemberDao;
import kr.or.bit.dto.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberListService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ActionForward forward = null;
        try {
            MemberDao dao = new MemberDao();
            Member info = dao.GetMemberListById(id);
            request.setAttribute("info", info);

            forward = new ActionForward();
            forward.setRedirect(false); //forward
            forward.setPath("/WEB-INF/views/adminlist.jsp");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return forward;
    }

}
