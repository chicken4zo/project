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
        RestaurantBoard restaurantBoard = new RestaurantBoard();
        String uploadpath = request.getRealPath("assets/upload");
        System.out.println(uploadpath);
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

            restaurantBoard.setTitle(title);
            restaurantBoard.setContent(content);
            restaurantBoard.setFileName(filename);
            restaurantBoard.setId(id);
            restaurantBoard.setFilePath(uploadpath);

            RestaurantDao dao = new RestaurantDao();
            int result = dao.writeOk(restaurantBoard);
            System.out.println(result);

            String url = "RestaurantList.board";
            request.setAttribute("board_url", url);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/redirect.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
