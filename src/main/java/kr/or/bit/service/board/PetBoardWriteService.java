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

public class PetBoardWriteService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String uploadpath = request.getSession().getServletContext().getRealPath("/assets/upload");
        int size = 1024 * 1024 * 10;
        ActionForward forward = null;

        try {
            System.out.println("WRITE SERVICE");

            MultipartRequest multi = new MultipartRequest(
                    request, //기존에 있는  request 객체의 주소값
                    uploadpath, //실 저장 경로 (배포경로)
                    size, //10M
                    "UTF-8",
                    new DefaultFileRenamePolicy() //파일 중복(upload 폴더 안에:a.jpg -> a_1.jpg(업로드 파일 변경) )
            );

            String id = multi.getParameter("id");
            String title = multi.getParameter("title");
            String content = multi.getParameter("content");

            Enumeration filenames = multi.getFileNames();

            ArrayList<String> files = new ArrayList<>();
            while (filenames.hasMoreElements()) {
                String file = (String) filenames.nextElement();
                files.add(multi.getFilesystemName(file));
            }

            PetBoard petBoard = new PetBoard();
            petBoard.setId(id);
            petBoard.setTitle(title);
            petBoard.setContent(content);
            petBoard.setFileName1(files.get(0));
            petBoard.setFilePath1(uploadpath);
            petBoard.setFileName2(files.get(1));
            petBoard.setFilePath2(uploadpath);
            petBoard.setFileName3(files.get(2));
            petBoard.setFilePath3(uploadpath);

            PetDao dao = new PetDao();
            int result = dao.writePetBoard(petBoard);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("petList.board");


        } catch (Exception e) {
            System.out.println("PETBOARD WRITE SERVICE 에러");
            e.printStackTrace();
        }


        return forward;
    }
}
