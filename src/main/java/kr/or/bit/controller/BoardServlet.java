package kr.or.bit.controller;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.MemberDao;
import kr.or.bit.service.board.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "*.board")
public class BoardServlet extends HttpServlet {
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String urlCommand = requestURI.substring(contextPath.length());

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("id");

        // 주소 가져오기
        MemberDao dao = new MemberDao();
        String address = dao.getAddress(userId);

        session.setAttribute("userId", userId);
        session.setAttribute("address", address);

        ActionForward forward = null;
        Action action = null;

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

            //맛집 글ok
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

            //맛집 글 수정
        } else if (urlCommand.equals("/RestaurantEdit.board")) {
            System.out.println("맛집 글 수정 컨트롤러 탔냐고");
            action = new RestaurantEditService();
            forward = action.execute(request, response);
            System.out.println("수정서비스간다.");

        } else if (urlCommand.equals("/RestaurantEditOk.board")) {
            action = new RestaurantEditOkService();
            forward = action.execute(request, response);
            System.out.println("수정완료서비스");


            //맛집 글 삭제
        } else if (urlCommand.equals("/RestaurantDelete.board")) {
            System.out.println("삭제 컨트롤러 탔냐고ㅠㅠ");
            // action = new RestaurantDeleteService();
            forward = action.execute(request, response);

            // 분실실종 게시판
        } else if (urlCommand.equals("/lostWrite.board")) {
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

            // 반려동물 게시판
        } else if (urlCommand.equals("/petDelete.board")) {
            System.out.println("PET BOARD DELETE SERVICE 실행");
            action = new PetBoardDeleteService();
            forward = action.execute(request, response);

        } else if (urlCommand.equals("/petWrite.board")) {
            System.out.println("PET BOARD WRITE SERVICE 실행");
            action = new PetBoardWriteService();
            forward = action.execute(request, response);


        } else if (urlCommand.equals("/petForm.board")) {
            System.out.println("PET BOARD FORM SERVICE 실행");
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/petWrite.jsp");

        } else if (urlCommand.equals("/petContent.board")) {
            System.out.println("PET BOARD CONTENT SERVICE 실행");
            action = new PetContentService();
            forward = action.execute(request, response);

        } else if (urlCommand.equals("/petModify.board")) {
            System.out.println("PET BOARD MODIFY SERVICE 실행");
            action = new PetBoardModifyService();
            forward = action.execute(request, response);

        } else if (urlCommand.equals("/petList.board")) {
            System.out.println("PET BOARD LIST SERVICE 실행");
            action = new PetBoardListService();
            forward = action.execute(request, response);

            // 상품 게시판
        } else if (urlCommand.equals("/productDelete.board")) {
            System.out.println("PRODUCT BOARD DELETE SERVICE 실행");
            action = new ProductBoardDeleteService();
            forward = action.execute(request, response);

        } else if (urlCommand.equals("/productWrite.board")) {
            System.out.println("PRODUCT BOARD WRITE SERVICE 실행");
            action = new ProductBoardWriteService();
            forward = action.execute(request, response);

        } else if (urlCommand.equals("/productForm.board")) {
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/productWrite.jsp");

        } else if (urlCommand.equals("/productContent.board")) {
            System.out.println("PRODUCT BOARD CONTENT SERVICE 실행");
            action = new ProductContentService();
            forward = action.execute(request, response);

        } else if (urlCommand.equals("/productModify.board")) {
            System.out.println("PRODUCT BOARD MODIFY SERVICE 실행");
            action = new ProductBoardModifyService();
            forward = action.execute(request, response);

        } else if (urlCommand.equals("/productList.board")) {
            System.out.println("PRODUCT BOARD LIST SERVICE 실행");
            action = new ProductBoardListService();
            forward = action.execute(request, response);
        }

        // 일상 게시판
        else if (urlCommand.equals("/dailyWrite.board")) {
            System.out.println("일상 게시판 쓰기");
            action = new DailyBoardWriteService();
            forward = action.execute(request, response);
        } else if (urlCommand.equals("/dailyContent.board")) {
            System.out.println("일상 게시판 상세");
            action = new DailyBoardContentService();
            forward = action.execute(request, response);
        } else if (urlCommand.equals("/dailyList.board")) {
            System.out.println("일상 게시판 리스트");
            action = new DailyBoardListService();
            forward = action.execute(request, response);
        } else if (urlCommand.equals("/dailyForm.board")) {
            System.out.println("일상 게시판 입력 폼");
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/dailyWrite.jsp");
        } else if (urlCommand.equals("/dailyModify.board")) {
            System.out.println("일상 게시판 수정");
            action = new DailyBoardModifyService();
            forward = action.execute(request, response);
        } else if (urlCommand.equals("/dailyDelete.board")) {
            System.out.println("일상 게시판 삭제");
            action = new DailyBoardDeleteService();
            forward = action.execute(request, response);
        } else if (urlCommand.equals("/dailyReply.board")) {
            System.out.println("일상 게시판 답글 폼");
            action = new DailyBoardReplyService();
            forward = action.execute(request, response);
        } else if (urlCommand.equals("/dailyReplyOk.board")) {
            System.out.println("일상 게시판 답글 입력");
            action = new DailyBoardReplyOkService();
            forward = action.execute(request, response);
        } else if (urlCommand.equals("/search.board")) {
            System.out.println("게시판 검색");
            action = new SearchBoardService();
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

