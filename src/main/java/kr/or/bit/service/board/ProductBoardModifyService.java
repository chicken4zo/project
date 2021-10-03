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

public class ProductBoardModifyService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String uploadpath = request.getSession().getServletContext().getRealPath("/assets/upload");
        int size = 1024 * 1024 * 10;
        ActionForward forward = null;

        try {
            if (request.getParameter("title") == null || request.getParameter("title").equals("")) {
                String idx = request.getParameter("idx");
                ProductDao dao = new ProductDao();
                ProductBoard productBoard = dao.getProductContent(idx);

                System.out.println(productBoard.toString());
                request.setAttribute("productBoard", productBoard);

                forward = new ActionForward();
                forward.setRedirect(false);
                forward.setPath("/WEB-INF/views/board/productModify.jsp");
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
                int price = Integer.parseInt(multi.getParameter("price").trim());

                Enumeration filenames = multi.getFileNames();

                // 이미지 대신 글만 수정하고 싶을 땐?
                ArrayList<String> files = new ArrayList<>();
                while (filenames.hasMoreElements()) {
                    String file = (String) filenames.nextElement();
                    files.add(multi.getFilesystemName(file));
                }

                ProductDao dao = new ProductDao();
                ProductBoard productBoard = dao.getProductContent(idx);

                productBoard.setIdx(Integer.parseInt(idx));
                productBoard.setTitle(title);
                productBoard.setContent(content);
                productBoard.setPrice(price);
                productBoard.setFileName1(files.get(0));
                productBoard.setFilePath1(uploadpath);
                productBoard.setFileName2(files.get(1));
                productBoard.setFilePath2(uploadpath);
                productBoard.setFileName3(files.get(2));
                productBoard.setFilePath3(uploadpath);


                int result = dao.modifyProduct(productBoard);
                forward = new ActionForward();
                forward.setRedirect(false);
                forward.setPath("productList.board");
            }

        } catch (Exception e) {
            System.out.println("PRODUCT BOARD MODIFY SERVICE 에러");
            System.out.println(e);
        }

        return forward;
    }
}
