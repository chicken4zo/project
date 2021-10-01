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
        System.out.println("어디가 오류야 ");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        int birth = Integer.parseInt(request.getParameter("birth"));
        String name = request.getParameter("name");
        System.out.println("어디가 오류야 ");

        ActionForward forward = new ActionForward();
        MemberDao memberDao = new MemberDao();
        Member memberDto = new Member();

        try {
            System.out.println("여기는 타는거니?");

            memberDto.setId(id);
            memberDto.setPassword(password);
            memberDto.setAddress(address);
            memberDto.setBirth(birth);
            memberDto.setName(name);

            int result = memberDao.RegisterMember(memberDto);

            String msg = "";
            String url = "";
            if (result > 0) {
                msg = "회원가입에 성공하였습니다!";
                url = "main.member";
            } else {
                msg = "회원가입에 실패하였습니다 ㅠㅠ";
                url = "Register.member";
            }
            request.setAttribute("msg", msg);
            request.setAttribute("url", url);


            forward.setRedirect(false);


        } catch (Exception e) {
            e.printStackTrace();
        }
        forward.setPath("/WEB-INF/views/redirect.jsp");
        return forward;

    }

}
