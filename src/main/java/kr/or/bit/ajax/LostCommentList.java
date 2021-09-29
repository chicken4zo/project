package kr.or.bit.ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.bit.dao.LostDao;
import kr.or.bit.dto.LostComment;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "LostCommentList", value = "/LostCommentList")
public class LostCommentList extends HttpServlet {
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idx = request.getParameter("idx");

        LostDao dao = new LostDao();
        List<LostComment> commentList = dao.getLostCommentList(idx);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < commentList.size(); i++) {
            String tempDate = dateFormat.format(commentList.get(i).getWriteDate());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("no", commentList.get(i).getNo());
            jsonObject.put("id", commentList.get(i).getId());
            jsonObject.put("content", commentList.get(i).getContent());
            jsonObject.put("writedate", tempDate);
            jsonObject.put("idx_fk", commentList.get(i).getIdx());

            jsonArray.add(jsonObject);
        }

        response.setContentType("application/x-json; charset=UTF-8");
        response.getWriter().print(jsonArray);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }
}
