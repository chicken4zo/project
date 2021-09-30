package kr.or.bit.service.member;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.MemberDao;
import kr.or.bit.dto.Member;
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MemberCheckService implements Action {
    // 아이디 중복을 찾는 서비스
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        MemberDao dao = new MemberDao();
        String id = request.getParameter("id");

        List<Member> list = new ArrayList<Member>();

        if (id != null) {
            list = dao.IdCheck(id);
        }

        JSONArray jsonArr = JSONArray.fromObject(list);

        response.setContentType("application/x-json; charset=UTF-8");
        try {
            response.getWriter().print(jsonArr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
