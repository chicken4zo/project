package kr.or.bit.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    ActionForward execute(HttpServletRequest request, HttpServletResponse response);

    // 당신이 만약 Action 인터페이스를 구현한다면 반드시 execute 함수를 재정의 해야한다 (강제 사용)
    // class a implement Action { @override execute(){ return new ActionForward; } }
}
