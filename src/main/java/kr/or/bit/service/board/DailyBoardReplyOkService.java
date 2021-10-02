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

public class DailyBoardReplyOkService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
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

            String ps = multi.getParameter("ps");
            String cp = multi.getParameter("cp");
            int idx = Integer.parseInt(multi.getParameter("idx"));
            dailyBoard.setIdx(idx);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        dailyBoard.setTitle(multi.getParameter("title"));
        dailyBoard.setContent(multi.getParameter("content"));
        dailyBoard.setId(multi.getParameter("id"));
        Enumeration fileNames = multi.getFileNames();

        String fileTag = (String) fileNames.nextElement();
        String fileName = multi.getFilesystemName(fileTag);
        System.out.println(fileName);
        String fileName2 = multi.getOriginalFileName(fileTag);

        dailyBoard.setFileName(fileName);
        dailyBoard.setFilePath(uploadPath);

        System.out.println(dailyBoard);

        DailyDao dao = new DailyDao();
        int result = dao.replyLost(dailyBoard);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("dailyList.board");
        return forward;
    }
}
