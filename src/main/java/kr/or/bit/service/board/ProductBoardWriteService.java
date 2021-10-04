package kr.or.bit.service.board;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.ProductDao;
import kr.or.bit.dto.ProductBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;

public class ProductBoardWriteService implements Action {
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
            String idx = multi.getParameter("idx");
            String title = multi.getParameter("title");
            String content = multi.getParameter("content");
            int price = Integer.parseInt(multi.getParameter("price"));
            String address = multi.getParameter("address");

            Enumeration filenames = multi.getFileNames();

            ArrayList<String> files = new ArrayList<>();
            while (filenames.hasMoreElements()) {
                String file = (String) filenames.nextElement();
                files.add(multi.getFilesystemName(file));
            }

            ProductBoard productBoard = new ProductBoard();
            productBoard.setId(id);
            productBoard.setTitle(title);
            productBoard.setContent(content);
            productBoard.setPrice(price);
            productBoard.setFileName1(files.get(0));
            productBoard.setFilePath1(uploadpath);
            productBoard.setFileName2(files.get(1));
            productBoard.setFilePath2(uploadpath);
            productBoard.setFileName3(files.get(2));
            productBoard.setFilePath3(uploadpath);

            ProductDao dao = new ProductDao();
            int result = dao.writeProductBoard(productBoard);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("productList.board");

            /*String msg = "";
            String url = "";
            if (result > 0) {
                msg = "상품 게시판 글쓰기 성공";
                url = "productList.board";
            } else {
                msg = "상품 게시판 글쓰기 실패";
                url = "productList.board";
            }

            request.setAttribute("board_msg", msg);
            request.setAttribute("board_url", url);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/redirect.jsp");*/

        } catch (Exception e) {
            System.out.println("PRODUCTBOARD WRITE SERVICE 에러");
            e.printStackTrace();
        }


        return forward;
    }
}
