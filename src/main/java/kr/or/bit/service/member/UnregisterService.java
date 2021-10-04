package kr.or.bit.service.member;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.MemberDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnregisterService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        //memo m = new memo();  //권장사항
        //m.setId(id);

        MemberDao memberdao = new MemberDao(); //POINT
        int result = memberdao.DeleteMemberok(id);

        String msg = "";
        String url = "";
        if (result > 0) {
            msg = "탈퇴 완료했습니다.";
            url = "Index.member";
        } else {
            msg = "탈퇴에 실패하였습니다.";
            url = "main.member";
        }

        request.setAttribute("msg", msg);
        request.setAttribute("url", url);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/redirect.jsp");

        return forward;
    }

}