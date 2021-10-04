package kr.or.bit.ajax;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/LoginLogout")
public class LoginLogout extends HttpServlet {
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setCharacterEncoding("UTF-8");
        String id = (String) session.getAttribute("id");
        PrintWriter out = response.getWriter();
        System.out.println("쳌1");

        if (id != null) {

            if (id.equals("admin")) {
                out.print("<a href=adminlist.member>");
                out.print("<div class=\"register_btn\">회원관리</div>");
                out.print("</a>");
                out.print("<a href=LogOut.member>");
                out.print("<div class=\"myPage_btn\">Log Out</div>");
                out.print("</a>");
            } else {
                out.print("<a href=mypage.member>");
                out.print("<div class=\"register_btn\">My Guma</div>");
                out.print("</a>");
                out.print("<a href=LogOut.member>");
                out.print("<div class=\"myPage_btn\">Log Out</div>");
                out.print("</a>");
            }


        } else {
            out.print("<a href=Register.member>");
            out.print("<div class=\"register_btn\">Register</div>");
            out.print("</a>");
            out.print("<a href=Login.member>");
            out.print("<div class=\"myPage_btn\">Log In</div>");
            out.print("</a>");
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

