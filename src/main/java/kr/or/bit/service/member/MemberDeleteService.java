package kr.or.bit.service.member;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.MemberDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberDeleteService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String[] ids = request.getParameterValues("id");

        if (ids.length > 0) {
            System.out.println("가져옴");
        }
        MemberDao dao = new MemberDao();
//        String msg="";
//        String url="";

        int n = dao.deleteMember(ids);
        System.out.println(n);


//        if(n > 0){
//            msg ="회원을 삭제했습니다.";
//            url ="Admin.member";
//        }else{
//            msg="삭제실패";
//            url="Admin.member";
//        }
//
//        request.setAttribute("msg", msg);
//        request.setAttribute("url", url);
//
//        ActionForward forward = new ActionForward();
//        forward.setRedirect(false);
//        forward.setPath("/WEB-INF/views/adminPop.jsp");
//
//        return forward;
        return null;
    }

}
