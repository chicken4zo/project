package kr.or.bit.service.board;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.RestaurantDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestaurantDeleteService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("삭제 서비스 들어왔냐");
        String idx = request.getParameter("idx");
        System.out.println("idx :" + idx);
        RestaurantDao dao = new RestaurantDao();
        ActionForward forward = null;

        try {
            int result = dao.RestaurantDelete(idx);
            System.out.println("result : " + result);
            String msg = "";
            String url = "";

//            if (idx == null || idx.trim().equals("")) {
//                msg = "프로그램 오류";
//                url = "RestaurantList.board";
//            }
            if (result > 0) {
                msg = "삭제 실패되었습니다.";
                url = "RestaurantList.board";
            } else {
                msg = "삭제가 완료되었습니다.";
                url = "RestaurantList.board";
            }

            request.setAttribute("board_msg", msg);
            request.setAttribute("board_url", url);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/boardDeletePop.jsp");
            System.out.println("너왜안뜨니");

        } catch (Exception e) {
            System.out.println("게시물삭제오류 : " + e.getMessage());
            e.printStackTrace();
        }
        return forward;
    }
}
