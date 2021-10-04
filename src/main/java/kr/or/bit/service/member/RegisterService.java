package kr.or.bit.service.member;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.MemberDao;
import kr.or.bit.dto.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        int birth = Integer.parseInt(request.getParameter("birth"));
        String name = request.getParameter("name");

        ActionForward forward = new ActionForward();
        MemberDao memberDao = new MemberDao();
        Member memberDto = new Member();

        try {
            memberDto.setId(id);
            memberDto.setPassword(password);
            memberDto.setAddress(address);
            memberDto.setBirth(birth);
            memberDto.setName(name);

            int result = memberDao.RegisterMember(memberDto);

            String url = "";

            if (result > 0) {
                url = "Login.member";
            } else {
                url = "Register.member";
            }

            request.setAttribute("url", url);

            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/redirect.jsp");

        } catch (Exception e) {
            System.out.println("REGISTER SERVICE 에러");
            e.printStackTrace();
        }
        return forward;
    }

}
