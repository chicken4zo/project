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
        String msg = "";
        String url = "";

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
                String category = multi.getParameter("category");


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
                productBoard.setTitle("[" + category + "] " + title);
               /* productBoard.setFileName1(files.get(0));
                productBoard.setFilePath1(uploadpath);
                productBoard.setFileName2(files.get(1));
                productBoard.setFilePath2(uploadpath);
                productBoard.setFileName3(files.get(2));
                productBoard.setFilePath3(uploadpath);*/
                String originalFileName1 = multi.getParameter("originalfile1");
                String originalFileName2 = multi.getParameter("originalfile2");
                String originalFileName3 = multi.getParameter("originalfile3");
                if (files.get(0) != null) {
                    productBoard.setFileName3(files.get(0));
                    productBoard.setFilePath3(uploadpath);
                } else {
                    productBoard.setFileName3(originalFileName3);
                    productBoard.setFilePath3(uploadpath);
                }

                if (files.get(1) != null) {
                    productBoard.setFileName2(files.get(1));
                    productBoard.setFilePath2(uploadpath);
                } else {
                    productBoard.setFileName2(originalFileName2);
                    productBoard.setFilePath2(uploadpath);
                }

                if (files.get(2) != null) {
                    productBoard.setFileName1(files.get(2));
                    productBoard.setFilePath1(uploadpath);
                } else {
                    productBoard.setFileName1(originalFileName1);
                    productBoard.setFilePath1(uploadpath);
                }


                int result = dao.modifyProduct(productBoard);

                if (result > 0) {
                    msg = "success";
                    url = "productList.board";
                } else {
                    msg = "fail";
                    url = "productModify.board?idx=" + idx;
                }
                request.setAttribute("msg", msg);
                request.setAttribute("url", url);

                forward = new ActionForward();
                forward.setRedirect(false);
                forward.setPath("/WEB-INF/views/board/boardModifyPop.jsp");
            }

        } catch (Exception e) {
            System.out.println("PRODUCT BOARD MODIFY SERVICE 에러");
            System.out.println(e.getMessage());
        }

        return forward;
    }
}
