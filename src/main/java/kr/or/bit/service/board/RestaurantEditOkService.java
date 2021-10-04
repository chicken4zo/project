package kr.or.bit.service.board;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.RestaurantDao;
import kr.or.bit.dto.RestaurantBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class RestaurantEditOkService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("EditOk서비스 들어왔니?");
        String uploadPath = request.getRealPath("assets/upload");
        RestaurantDao dao = new RestaurantDao();
        int size = 1024 * 1024 * 10;
        ActionForward forward = null;
        MultipartRequest multi = null;
        RestaurantBoard board = new RestaurantBoard();
        String msg = "";
        String url = "";

        try {
            multi = new MultipartRequest(
                    request,
                    uploadPath,
                    size,
                    "UTF-8",
                    new DefaultFileRenamePolicy()
            );


            int idx = Integer.parseInt(multi.getParameter("idx"));


            board.setTitle(multi.getParameter("title"));
            board.setContent(multi.getParameter("content"));
            board.setId(multi.getParameter("id"));
            board.setIdx(idx);

            String originalFileName = multi.getParameter("originalfile");
            Enumeration fileNames = multi.getFileNames();
            String file = (String) fileNames.nextElement();
            String fileName = multi.getFilesystemName(file);
            board.setFileName(fileName);
            board.setFilePath(uploadPath);
            board.setFileName(originalFileName);

            System.out.println(board);
            System.out.println("여기탓냐");
            int result = dao.boardEditOk(board);

            if (result > 0) {
                msg = "수정이 완료되었습니다.";
                url = "RestaurantList.board";
            } else {
                msg = "수정 오류";
                url = "RestaurantEdit.board?idx=" + idx;
            }

            request.setAttribute("board_msg", msg);
            request.setAttribute("board_url", url);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/board/boardModifyPop.jsp");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return forward;
    }

}
