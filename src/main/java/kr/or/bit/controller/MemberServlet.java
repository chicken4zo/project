package kr.or.bit.controller;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.member.*;

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
        // 뒤로가기 캐쉬삭퇴

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.


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
            forward.setPath("/WEB-INF/views/login.jsp");
        } else if (urlCommand.equals("/mypage.member")) {
            System.out.println("mypage");
            action = new MemberInfoService();
            forward = action.execute(request, response);
            System.out.println("IdCheck Start");
        } else if (urlCommand.equals("/admin.member")) {
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/admin.jsp");
        } else if (urlCommand.equals("/adminlist.member")){
            action = new AdminMemberList();
            forward = action.execute(request, response);
            System.out.println("/Adminlist Start");
        }else if (urlCommand.equals("/memberDelete.member")) {
            action = new MemberDeleteService();
            forward = action.execute(request, response);
            System.out.println("MemberDelete Start");
        }else if (urlCommand.equals("/ModifyMember.member")){
            action = new MemberModifyService();
            forward = action.execute(request,response);
            System.out.println("MemberModify Start");
        }else if (urlCommand.equals("/Unregi.member")) {
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/unregister.jsp");
        }else if (urlCommand.equals("/Unregister.member")) {
            action = new UnregisterService();
            forward = action.execute(request, response);
            System.out.println("Unregister Start");
        }else if(urlCommand.equals("/Index.member")) {
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/index.jsp");
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

