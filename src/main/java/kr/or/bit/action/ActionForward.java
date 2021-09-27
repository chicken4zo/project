package kr.or.bit.action;

// 요청
// 1. 화면
// 2. 처리

// 화면이냐 처리냐에 대한 결정 여부를 가지는 변수
// 뷰의 경로 설정 여부

public class ActionForward {
    private boolean isRedirect = false; // 화면 전환 여부
    private String path = null; // 뷰의 이동 주소

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
