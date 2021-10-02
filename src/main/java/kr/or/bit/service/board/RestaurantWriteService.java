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

public class RestaurantWriteService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String uploadpath = request.getSession().getServletContext().getRealPath("upload");
        int size = 1024 * 1024 * 10;

        ActionForward forward = null;

        try {
            MultipartRequest multi = new MultipartRequest(
                    request, uploadpath, size,
                    "UTF-8", new DefaultFileRenamePolicy()
            );

            String title = multi.getParameter("title");
            String content = multi.getParameter("content");
            String id = multi.getParameter("id");

            Enumeration filenames = multi.getFileNames();
            String file = (String) filenames.nextElement();
            String filename = multi.getFilesystemName(file);

            System.out.println(title + "/" + content + "/" + filename + "/" + id);

            if (filename == null) {
                filename = "";
            }

            RestaurantBoard board = new RestaurantBoard();
            board.setTitle(title);
            board.setContent(content);
            board.setFileName(filename);
            board.setId(id);

            RestaurantDao dao = new RestaurantDao();
            int result = dao.writeOk(board);
            System.out.println(result);

            String msg = "";
            String url = "";
            if (result > 0) {
                msg = "게시글 작성이 완료되었습니다.";
                url = "RestaurantList.board";
            } else {
                msg = "작성실패, 다시 시도해주세요.";
                url = "RestaurantWrite.board";
            }

            request.setAttribute("board_msg", msg);
            request.setAttribute("board_url", url);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/redirect.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
