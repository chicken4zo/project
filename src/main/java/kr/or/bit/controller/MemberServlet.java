package kr.or.bit.controller;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.member.LoginService;
import kr.or.bit.service.member.LogoutService;
import kr.or.bit.service.member.MemberCheckService;
import kr.or.bit.service.member.RegisterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "*.member")
public class MemberServlet extends HttpServlet {
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("여기까진오냐 0");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String urlCommand = requestURI.substring(contextPath.length());

        ActionForward forward = null;
        Action action = null;


        if (urlCommand.equals("/Register.member")) { //회원가입 페이지로 가는 태그

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/register.jsp");

        } else if (urlCommand.equals("/RegisterOk.member")) { // 회원가입 완료
            System.out.println("여기까진오냐1");
            action = new RegisterService();
            forward = action.execute(request, response);
            System.out.println("MemberRegister Start");

        } else if (urlCommand.equals("/Login.member")) { // 인덱스 페이지에서 누르면 로그인 폼으로 가는 태그
            System.out.println("여기까진오냐 2");
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/login.jsp");

        } else if (urlCommand.equals("/LoginOk.member")) {
            System.out.println("여기까진오냐 3");
            action = new LoginService();
            forward = action.execute(request, response);
            System.out.println("MemberJoin Start");

        } else if (urlCommand.equals("/LogOut.member")) {
            System.out.println("여기까진오냐4");
            System.out.println("로그아웃완료");
            action = new LogoutService();
            forward = action.execute(request, response);
            System.out.println("Logout Start");

        } else if (urlCommand.equals("/IdCheck.member")) {
            System.out.println("여기까진오냐5");
            action = new MemberCheckService();
            forward = action.execute(request, response);
            System.out.println("IdCheck Start");

        } else if (urlCommand.equals("/main.member")) { // 잠깐 로그인 성공시 가는 페이지 만든
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/loginok.jsp");
        }

        if (forward != null) {
            if (forward.isRedirect()) { //true 페이지를 재요청
                response.sendRedirect(forward.getPath());
            } else { // false
                //1.UI + 로직이 다 있는 경우
                //2.UI만 있는 경우
                System.out.println("forward: " + forward.getPath());
                RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
                dis.forward(request, response);
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);

    }
}

