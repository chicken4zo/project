package kr.or.bit.service.board;

import com.mysql.cj.protocol.x.Notice;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.NoticeDao;
import kr.or.bit.dto.NoticeBoard;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoticeWriteService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("서비스왔냐");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String admin = request.getParameter("writer");
        System.out.println(title);
        NoticeDao dao = new NoticeDao();
        int result = dao.writeOk(title, content, admin);
        System.out.println(result);


        String msg = "";
        String url = "";
        if(result > 0) {
            msg = "공지사항 등록이 완료되었습니다.";
            url = "NoticeList.board";
        } else {
            msg = "오류가 발생하였습니다.";
            url = "NoticeWrite.board";
        }

        request.setAttribute("board_msg", msg);
        request.setAttribute("board_url", url);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/board/redirect.jsp");

        return forward;
    }
}
