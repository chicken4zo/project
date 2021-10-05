package kr.or.bit.service.board;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.DailyDao;
import kr.or.bit.dto.DailyBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class DailyBoardModifyService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        DailyDao dao = new DailyDao();
        ActionForward forward = new ActionForward();
        String msg = "";
        String url = "";

        if (request.getParameter("title") == null || request.getParameter("title").equals("")) {
            String id = request.getParameter("id");
            String idx = request.getParameter("idx");
            DailyBoard daily = new DailyBoard();
            daily = dao.viewDailyContent(idx);

            request.setAttribute("dailyBoard", daily);

            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/dailyModify.jsp");
        } else {
            DailyBoard dailyBoard = new DailyBoard();
            String uploadPath = request.getRealPath("assets/upload");
            System.out.println(uploadPath);
            int size = 1024 * 1024 * 10;

            MultipartRequest multi = null;
            try {
                multi = new MultipartRequest(
                        request,
                        uploadPath,
                        size,
                        "UTF-8",
                        new DefaultFileRenamePolicy()
                );
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            if (multi != null) {
                int idx = Integer.parseInt(multi.getParameter("idx"));

                dailyBoard.setTitle(multi.getParameter("title"));
                dailyBoard.setContent(multi.getParameter("content"));
                dailyBoard.setId(multi.getParameter("id"));
                dailyBoard.setIdx(idx);
                String originalFileName = multi.getParameter("originalfile");
                    Enumeration fileNames = multi.getFileNames();

                    String fileTag = (String) fileNames.nextElement();
                    String fileName = multi.getFilesystemName(fileTag);
                if (fileName != null) {
                    String fileName2 = multi.getOriginalFileName(fileTag);
                    dailyBoard.setFileName(fileName);
                    dailyBoard.setFilePath(uploadPath);
                } else {
                    dailyBoard.setFileName(originalFileName);
                    dailyBoard.setFilePath(uploadPath);
                }

            }


            System.out.println(dailyBoard);
            int result = dao.modifyDaily(dailyBoard);
            String idx = request.getParameter("idx");

            if (result > 0) {
                msg = "success";
                url = "dailyList.board";
            } else {
                msg = "fail";
                url = "dailyModify.board?idx=" + idx;
            }

            request.setAttribute("msg", msg);
            request.setAttribute("url", url);


            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/boardModifyPop.jsp");
        }

        return forward;
    }
}
