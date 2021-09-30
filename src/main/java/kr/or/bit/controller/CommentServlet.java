package kr.or.bit.controller;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.LostDao;
import kr.or.bit.dto.LostComment;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(urlPatterns = "*.comment")
public class CommentServlet extends HttpServlet {
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String urlCommand = requestURI.substring(contextPath.length());

        ActionForward forward = null;
        Action action = null;

        if (urlCommand.equals("/lostCommentWrite.comment")) {
            String commentId = request.getParameter("commentId");
            String content = request.getParameter("content");
            String idx = request.getParameter("idx");

            LostDao dao = new LostDao();
            int result = dao.writeLostComment(commentId, content, idx);

        } else if (urlCommand.equals("/lostCommentList.comment")) {
            String idx = request.getParameter("idx");
            LostDao dao = new LostDao();
            List<LostComment> commentList = dao.getLostCommentList(idx);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            JSONArray jsonArray = new JSONArray();

            for (int i = 0; i < commentList.size(); i++) {
                String tempDate = dateFormat.format(commentList.get(i).getWriteDate());

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("no", commentList.get(i).getNo());
                jsonObject.put("id", commentList.get(i).getId());
                jsonObject.put("content", commentList.get(i).getContent());
                jsonObject.put("writedate", tempDate);
                jsonObject.put("idx_fk", commentList.get(i).getIdx());

                jsonArray.add(jsonObject);
            }

            response.setContentType("application/x-json; charset=UTF-8");
            response.getWriter().print(jsonArray);
        } else if (urlCommand.equals("/lostCommentDelete.comment")) {
            String idx_fk = request.getParameter("idx_fk");
            int no = Integer.parseInt(request.getParameter("no"));

            try {

                PrintWriter out = response.getWriter();

                if (idx_fk == null) {
                    out.print("<script>");
                    out.print("alert('글번호가 넘어오지 않았습니다');");
                    out.print("history.back();");
                    out.print("</script>");
                }

                LostDao dao = new LostDao();
                int result = dao.deleteLostComment(no);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
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

