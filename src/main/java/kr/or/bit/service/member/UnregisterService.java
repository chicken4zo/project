package kr.or.bit.service.member;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.MemberDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UnregisterService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        //memo m = new memo();  //권장사항
        //m.setId(id);
        HttpSession session = request.getSession();
        session.invalidate();

        MemberDao memberdao = new MemberDao(); //POINT
        int result = memberdao.DeleteMemberok(id);

        String msg = "";
        String url = "";
        if (result > 0) {
            msg = "탈퇴 완료했습니다.";
            url = "Login.member";
        } else {
            msg = "탈퇴에 실패하였습니다.";
            url = "Mypage.member";
        }

        request.setAttribute("msg", msg);
        request.setAttribute("board_url", url);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/redirect.jsp");

        return forward;
    }

}