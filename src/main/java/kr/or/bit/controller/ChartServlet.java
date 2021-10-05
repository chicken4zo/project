package kr.or.bit.controller;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.MemberDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "*.chart")
public class ChartServlet extends HttpServlet {
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String urlCommand = requestURI.substring(contextPath.length());

        ActionForward forward = null;
        Action action = null;

        if (urlCommand.equals("/chartpage.chart")) {
            JSONArray jsonArray1 = new JSONArray();
//            JSONArray jsonArray2 = new JSONArray();
            MemberDao dao = new MemberDao();
            List<String> addressList = dao.getAddressList();
//            List<String> ageList = dao.getAgeList();

            for (int i = 0; i < addressList.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("address", addressList.get(i));

                jsonArray1.add(jsonObject);
            }

           /* for (int i = 0; i < ageList.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("age", ageList.get(i));

                jsonArray2.add(jsonObject);
            }
*/
            response.setContentType("application/x-json; charset=UTF-8");
            response.getWriter().print(jsonArray1);
//            response.getWriter().print(jsonArray2);

        } else if (urlCommand.equals("/chartView.chart")) {
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/chartpage.jsp");
        }

        if (forward != null) {
            if (forward.isRedirect()) { //true 페이지를 재요청
                response.sendRedirect(forward.getPath());
            } else { // false
                //1.UI + 로직이 다 있는 경우
                //2.UI만 있는 경우
                System.out.println("forward: " + forward.getPath());
                RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
                dis.forward(request, response);
            }

        }
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


