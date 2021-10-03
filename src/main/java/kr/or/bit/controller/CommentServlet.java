package kr.or.bit.controller;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.DailyDao;
import kr.or.bit.dao.LostDao;
import kr.or.bit.dao.ProductDao;
import kr.or.bit.dao.RestaurantDao;
import kr.or.bit.dto.DailyComment;
import kr.or.bit.dto.LostComment;
import kr.or.bit.dto.ProductComment;
import kr.or.bit.dto.RestaurantComment;
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


        } else if (urlCommand.equals("/productCommentWrite.board")) {

            // 일상 댓글
        } else if (urlCommand.equals("/dailyCommentWrite.comment")) {

            String commentId = request.getParameter("commentId");
            String content = request.getParameter("content");
            String idx = request.getParameter("idx");


            DailyDao dao = new DailyDao();
            int result = dao.writeDailyComment(commentId, content, idx);
        } else if (urlCommand.equals("/dailyCommentList.comment")) {
            String idx = request.getParameter("idx");
            DailyDao dao = new DailyDao();
            List<DailyComment> commentList = dao.getDailyCommentList(idx);

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

        } else if (urlCommand.equals("/dailyCommentDelete.comment")) {
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


                DailyDao dao = new DailyDao();
                int result = dao.deleteDailyComment(no);


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


        } else if (urlCommand.equals("/productCommentWrite.comment")) {
            System.out.println("PRODUCT 댓글 작성 서비스 실행");
            String commentId = request.getParameter("commentId");
            String content = request.getParameter("content");
            String idx = request.getParameter("idx");

            ProductDao dao = new ProductDao();
            int result = dao.writeProductComment(commentId, content, idx);
            System.out.println("PRODUCT 댓글 작성 result : " + result);

        } else if (urlCommand.equals("/productCommentList.comment")) {
            String idx = request.getParameter("idx");
            ProductDao dao = new ProductDao();
            List<ProductComment> commentList = dao.getProductCommentList(idx);
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

        } else if (urlCommand.equals("/productCommentDelete.comment")) {
            String idx = request.getParameter("idx");
            int no = Integer.parseInt(request.getParameter("no"));

            try {

                PrintWriter out = response.getWriter();

                if (idx == null) {
                    out.print("<script>");
                    out.print("alert('글번호가 넘어오지 않았습니다');");
                    out.print("history.back();");
                    out.print("</script>");
                }

                ProductDao dao = new ProductDao();
                int result = dao.deleteProductComment(no);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


            //맛집 게시판 댓글
        } else if (urlCommand.equals("/RestaurantCommentWrite.comment")) {
            String id = request.getParameter("id");
            String content = request.getParameter("content");
            int idx = Integer.parseInt(request.getParameter("idx"));

            RestaurantDao dao = new RestaurantDao();
            int result = dao.commentWrite(idx, id, content);

            //e댓글 목록보기
        } else if (urlCommand.equals("/RestaurantCommentList.comment")) {
            String idx = request.getParameter("idx");
            RestaurantDao dao = new RestaurantDao();
            List<RestaurantComment> commentList = dao.getRestaurantCommentList(idx);
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

            //댓글 삭제하기
        } else if (urlCommand.equals("/commentDelete.comment")) {
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


