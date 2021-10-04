package kr.or.bit.service.member;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.MemberDao;
import kr.or.bit.dto.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

        ActionForward forward = new ActionForward();

        String id = request.getParameter("id");
        String password = request.getParameter("password");

        Member result = null;
        HttpSession session = request.getSession();
        Member memberdto = new Member();

        memberdto.setId(id);
        memberdto.setPassword(password);
        try {
            MemberDao memberdao = new MemberDao();

            result = memberdao.loginMember(memberdto);
            request.getSession().setAttribute("user", result);

            String msg = "";
            String url = "";
            if (result == null) {
                msg = "일치하는 회원정보가 없습니다.";
                url = "Login.member";
            } else if (result != null) {
                msg = "고구마켓에 어서오세요!";
                url = "productList.board";


                session.setAttribute("id", id);


            }

            request.setAttribute("msg", msg);
            request.setAttribute("url", url);

            /*
             * HttpSession session = request.getSession(); String id = (String)
             * session.getAttribute("email"); System.out.println(id);
             */

            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/loginPop.jsp");

        } catch (Exception e) {
            System.out.println("로그인 오류 : " + e.getMessage());
        }

        return forward;
    }

}