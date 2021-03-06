package kr.or.bit.service.board;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.PetDao;
import kr.or.bit.dto.PetBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;

public class PetBoardModifyService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String uploadpath = request.getSession().getServletContext().getRealPath("/assets/upload");
        int size = 1024 * 1024 * 10;
        ActionForward forward = null;
        String msg = "";
        String url = "";

        try {
            if (request.getParameter("title") == null || request.getParameter("title").equals("")) {
                String idx = request.getParameter("idx");
                PetDao dao = new PetDao();
                PetBoard petBoard = dao.getPetBoardContent(idx);

                System.out.println(petBoard.toString());
                request.setAttribute("petBoard", petBoard);

                forward = new ActionForward();
                forward.setRedirect(false);
                forward.setPath("/WEB-INF/views/board/petModify.jsp");
            } else {
                MultipartRequest multi = new MultipartRequest(
                        request,
                        uploadpath,
                        size,
                        "UTF-8",
                        new DefaultFileRenamePolicy()
                );

                String idx = multi.getParameter("idx").trim();
                String title = multi.getParameter("title").trim();
                String content = multi.getParameter("content").trim();

                String originalFileName1 = multi.getOriginalFileName("originalfile1");
                String originalFileName2 = multi.getOriginalFileName("originalfile2");
                String originalFileName3 = multi.getOriginalFileName("originalfile3");

                Enumeration filenames = multi.getFileNames();

                // ????????? ?????? ?????? ???????????? ?????? ????
                ArrayList<String> files = new ArrayList<>();
                while (filenames.hasMoreElements()) {
                    String file = (String) filenames.nextElement();
                    files.add(multi.getFilesystemName(file));
                }

                PetDao dao = new PetDao();
                PetBoard petBoard = dao.getPetBoardContent(idx);

                petBoard.setIdx(Integer.parseInt(idx));
                petBoard.setTitle(title);
                petBoard.setContent(content);
                originalFileName1 = multi.getParameter("originalfile1");
                originalFileName2 = multi.getParameter("originalfile2");
                originalFileName3 = multi.getParameter("originalfile3");
                if (files.get(0) != null) {
                    petBoard.setFileName3(files.get(0));
                    petBoard.setFilePath3(uploadpath);
                } else {
                    petBoard.setFileName3(originalFileName3);
                    petBoard.setFilePath3(uploadpath);
                }

                if (files.get(1) != null) {
                    petBoard.setFileName2(files.get(1));
                    petBoard.setFilePath2(uploadpath);
                } else {
                    petBoard.setFileName2(originalFileName2);
                    petBoard.setFilePath2(uploadpath);
                }

                if (files.get(2) != null) {
                    petBoard.setFileName1(files.get(2));
                    petBoard.setFilePath1(uploadpath);
                } else {
                    petBoard.setFileName1(originalFileName1);
                    petBoard.setFilePath1(uploadpath);
                }


//                int result = dao.modifyPet(petBoard);

                int result = dao.modifyPet(petBoard);

                if (result > 0) {
                    msg = "success";
                    url = "petList.board";
                } else {
                    msg = "fail";
                    url = "petModify.board?idx=" + idx;
                }

                request.setAttribute("msg", msg);
                request.setAttribute("url", url);


                forward = new ActionForward();
                forward.setRedirect(false);
                forward.setPath("/WEB-INF/views/board/boardModifyPop.jsp");
            }

        } catch (Exception e) {
            System.out.println("PETBOARD MODIFY SERVICE ??????");
            e.printStackTrace();
        }

        return forward;
    }
}
