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

public class LostBoardModifyService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        LostDao dao = new LostDao();
        ActionForward forward = new ActionForward();
        String msg = "";
        String url = "";

        if (request.getParameter("title") == null || request.getParameter("title").equals("")) {
            String id = request.getParameter("id");
            String idx = request.getParameter("idx");
            LostBoard lost = new LostBoard();
            lost = dao.viewLostContent(idx);
            System.out.println("수정");

            request.setAttribute("lostBoard", lost);

            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/lostModify.jsp");
        } else {
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
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            assert multi != null;
            int idx = Integer.parseInt(multi.getParameter("idx"));

            lostBoard.setTitle(multi.getParameter("title"));
            lostBoard.setContent(multi.getParameter("content"));
            lostBoard.setId(multi.getParameter("id"));
            String originalFileName = multi.getOriginalFileName("originalfile");
            lostBoard.setIdx(idx);
            Enumeration fileNames = multi.getFileNames();
            String fileTag = (String) fileNames.nextElement();
            String fileName = multi.getFilesystemName(fileTag);
            String fileName2 = multi.getOriginalFileName(fileTag);

            if (fileName == null || fileName.equals("")) {
                lostBoard.setFileName(originalFileName);
            } else {
                lostBoard.setFileName(fileName);
            }

            lostBoard.setFilePath(uploadPath);

            System.out.println(lostBoard);
            int result = dao.modifyLost(lostBoard);

            if (result > 0) {
                msg = "success";
                url = "lostList.board";
            } else {
                msg = "fail";
                url = "lostModify.board?idx=" + idx;
            }

            request.setAttribute("msg", msg);
            request.setAttribute("url", url);


            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/board/boardModifyPop.jsp");
        }

        return forward;
    }
}
