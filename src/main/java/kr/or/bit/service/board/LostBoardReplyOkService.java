package kr.or.bit.service.board;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.LostDao;
import kr.or.bit.dto.LostBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class LostBoardReplyOkService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        LostBoard lostBoard = new LostBoard();
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
            lostBoard.setIdx(idx);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        lostBoard.setTitle(multi.getParameter("title"));
        lostBoard.setContent(multi.getParameter("content"));
        lostBoard.setId(multi.getParameter("id"));
        Enumeration fileNames = multi.getFileNames();

        String fileTag = (String) fileNames.nextElement();
        String fileName = multi.getFilesystemName(fileTag);
        System.out.println(fileName);
        String fileName2 = multi.getOriginalFileName(fileTag);

        lostBoard.setFileName(fileName);
        lostBoard.setFilePath(uploadPath);

        System.out.println(lostBoard);

        LostDao dao = new LostDao();
        int result = dao.replyLost(lostBoard);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("lostList.board");
        return forward;
    }
}
