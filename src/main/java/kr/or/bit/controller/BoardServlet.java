package kr.or.bit.controller;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.board.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "*.board")
public class BoardServlet extends HttpServlet {
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String urlCommand = requestURI.substring(contextPath.length());

        ActionForward forward = null;
        Action action = null;

        //공지사항
        //글쓰기
        if (urlCommand.equals("/NoticeWrite.board")) {
            System.out.println("쓰기 ok 컨트롤러");
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/noticeWrite.jsp");

            //글 저장
        } else if (urlCommand.equals("/NoticeWriteOk.board")) {
            action = new NoticeWriteService();
            forward = action.execute(request, response);

            //공지사항 목록보기
        } else if (urlCommand.equals("/NoticeList.board")) {
            System.out.println("컨트롤러탔다.");
            //forward = new ActionForward();
            //forward.setPath("/WEB-INF/views/board/noticeList.jsp");
            //forward.setRedirect(false);
            action = new NoticeBoardListService();
            forward = action.execute(request, response);

            //글 상세보기
        } else if (urlCommand.equals("/NoticeContent.board")) {
            System.out.println("content 컨트롤러 탔다.");
            action = new NoticeContentService();
            forward = action.execute(request, response);

            //글 수정
        } else if (urlCommand.equals("/NoticeEdit.board")) {
            action = new NoticeEditService();
            forward = action.execute(request, response);

            //글 수정 완료
        } else if (urlCommand.equals("/NoticeEditOk.board")) {
            System.out.println("EditOk컨트롤러왔니..?");
            action = new NoticeEditOkService();
            forward = action.execute(request, response);

            //글 삭제
        } else if (urlCommand.equals("/NoticeDelete.board")) {
            System.out.println("삭제 컨트롤러 탔다.");
            action = new NoticeDeleteService();
            forward = action.execute(request, response);

            //맛집시작
            //맛집 글쓰기
        } else if (urlCommand.equals("/RestaurantWrite.board")) {
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/restaurantWrite.jsp");

        } else if (urlCommand.equals("/RestaurantWriteOk.board")) {
            action = new RestaurantWriteService();
            forward = action.execute(request, response);

            //맛집 목록
        } else if (urlCommand.equals("/RestaurantList.board")) {
            action = new RestaurantBoardListService();
            forward = action.execute(request, response);


            //맛집 상세보기
        } else if (urlCommand.equals("/RestaurantContent.board")) {
            System.out.println("서블릿옴");
            action = new RestaurantContentService();
            forward = action.execute(request, response);

            //댓글 등록, 삭제
        } else if (urlCommand.equals("/RestaurantComment.board")) {
            System.out.println("댓글 서블릿옴");
            action = new RestaurantCommentService();
            forward = action.execute(request, response);
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

