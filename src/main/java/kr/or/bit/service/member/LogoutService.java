package kr.or.bit.service.member;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutService implements Action {
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = new ActionForward();

        HttpSession session = request.getSession();
        session.invalidate();

        System.out.println("로그아웃");
        forward.setRedirect(false);
        forward.setPath("Login.member");
        return forward;
    }
}
