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

        // 분실실종 게시판
        if (urlCommand.equals("/lostWrite.board")) {
            System.out.println("분실실종 글쓰기");
            action = new LostBoardWriteService();
            forward = action.execute(request, response);
        } else if (urlCommand.equals("/lostForm.board")) {
            System.out.println("분실실종 폼");
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/lostWrite.jsp");
        } else if (urlCommand.equals("/lostList.board")) {
            System.out.println("분실실종 리스트");
            action = new LostBoardListService();
            forward = action.execute(request, response);
        } else if (urlCommand.equals("/lostContent.board")) {
            System.out.println("분실실종 상세페이지");
            action = new LostBoardContentService();
            forward = action.execute(request, response);
        } else if (urlCommand.equals("/lostModify.board")) {
            System.out.println("분실실종 수정");
            action = new LostBoardModifyService();
            forward = action.execute(request, response);
        } else if (urlCommand.equals("/lostDelete.board")) {
            System.out.println("분실실종 삭제");
            action = new LostBoardDeleteService();
            forward = action.execute(request, response);
        } else if (urlCommand.equals("/lostReply.board")) {
            System.out.println("분실실종 답글");
            action = new LostBoardReplyService();
            forward = action.execute(request, response);
        } else if (urlCommand.equals("/lostReplyOk.board")) {
            System.out.println("분실실종 답글 쓰기");
            action = new LostBoardReplyOkService();
            forward = action.execute(request, response);

            // 상품 게시판

        } else if (urlCommand.equals("/productDelete.board")) {
            System.out.println("PRODUCTBOARD DELETE SERVICE 실행");
            action = new ProductBoardDeleteService();
            forward = action.execute(request, response);

        } else if (urlCommand.equals("/productWrite.board")) {
            System.out.println("PRODUCTBOARD WRITE SERVICE 실행");
            action = new ProductBoardWriteService();
            forward = action.execute(request, response);

        } else if (urlCommand.equals("/productForm.board")) {
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/productWrite.jsp");

        } else if (urlCommand.equals("/productContent.board")) {
            System.out.println("PRODUCTBOARD CONTENT SERVICE 실행");
            action = new ProductContentService();
            forward = action.execute(request, response);

        } else if (urlCommand.equals("/productModify.board")) {
            System.out.println("PRODUCTBOARD MODIFY SERVICE 실행");
            action = new ProductBoardModifyService();
            forward = action.execute(request, response);

        } else if (urlCommand.equals("/productList.board")) {
            System.out.println("PRODUCTBOARD LIST SERVICE 실행");
            action = new ProductBoardListService();
            forward = action.execute(request, response);
        } else if (urlCommand.equals("/dailyWrite.board")) {

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

