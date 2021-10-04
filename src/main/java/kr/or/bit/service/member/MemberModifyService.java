package kr.or.bit.service.member;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.MemberDao;
import kr.or.bit.dto.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberModifyService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("여기옴?");
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        ActionForward forward = null;

        if (name == null) {
            MemberDao memberdao = new MemberDao();
            Member memberdto = memberdao.DetailMember(id);

            request.setAttribute("detailmember", memberdto);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/mypage.jsp");
        } else {
            Member memberdto = new Member();
            int birth = Integer.parseInt(request.getParameter("birth"));
            String address = request.getParameter("address");
            String password = request.getParameter("password");
            memberdto.setBirth(birth);
            memberdto.setName(name);
            memberdto.setPassword(password);
            memberdto.setAddress(address);
            memberdto.setId(id);

            MemberDao dao = new MemberDao();
            int result = dao.EditMember(memberdto);

            String msg = "";
            String url = "";

            if (result > 0) {
                msg = "수정이 완료되었습니다.";
                url = "mypage.member";
            } else {
                msg = "수정이 실패하였습니다.";
                url = "mypage.member";
            }

            request.setAttribute("msg", msg);
            request.setAttribute("url", url);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/redirect.jsp");

        }
        return forward;
    }
}



