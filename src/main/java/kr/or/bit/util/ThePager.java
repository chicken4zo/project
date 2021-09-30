package kr.or.bit.util;

public class ThePager {

    private int pageSize;//한 페이지당 데이터 개수
    private int pagerSize;//번호로 보여주는 페이지 Link 개수
    private int dataCount;//총 데이터 수
    private int currentPage;//현재 페이지 번호
    private int pageCount;//총 페이지 수

    private String linkUrl;//페이저가 포함되는 페이지의 주소

    public ThePager(int dataCount, int currentPage,
                    int pageSize, int pagerSize, String linkUrl) {

        this.linkUrl = linkUrl;

        this.dataCount = dataCount;
        this.pageSize = pageSize;
        this.pagerSize = pagerSize;
        this.currentPage = currentPage;
        pageCount =
                (dataCount / pageSize) + ((dataCount % pageSize) > 0 ? 1 : 0);
    }

    public String toString() {
        StringBuffer linkString = new StringBuffer();

        //1. 처음, 이전 항목 만들기
        if (currentPage > 1) {
            linkString.append(
                    String.format("[<a href='%s?cp=1'>처음</a>]", linkUrl));
            linkString.append("&nbsp;");
            linkString.append("&nbsp;");
            linkString.append(String.format(
                    "[<a href='%s?cp=%d'>이전</a>]", linkUrl, currentPage - 1));
            linkString.append("&nbsp;");
        }

        //2. 페이지 번호 Link 만들기
        // 총 페이지 수가 8이면 표시되어야 하는 화면은 [1 2 3] (여기까지만 표시되고 다음 누르면) [4 5 6] (다시 다음 누르면) [7 8]
        // pagersize는 3이라고 가정했을 때
        int pagerBlock = (currentPage - 1) / pagerSize; // (1-1)/3 = 0 -> 1,2,3
        int start = (pagerBlock * pagerSize) + 1; // 1 1*3+1 = 4 -> 2*3+1 = 7
        int end = start + pagerSize; // 1+3 = 4 -> 4+3 = 7 -> 7+3 = 10 [7 8 9]
        for (int i = start; i < end; i++) {
            if (i > pageCount) break; // i가 pageCount를 넘어가면 더이상 번호를 만들지 않고 break 하여 for문 탈출 (9는 출력 x)
            linkString.append("&nbsp;");
            if (i == currentPage) { // 현재 페이지가 i 라면 번호 만들어서 [i] 처럼 현재 해당되는 위치에 괄호 표시 (a링크 없음)
                linkString.append(String.format("[%d]", i));
            } else {
                linkString.append(String.format( // 그렇지 않으면 i에 해당하는 페이지에 해당하는 번호 형성 ([]괄호 없고 링크 걸림)
                        "<a href='%s?cp=%d'>%d</a>", linkUrl, i, i));
            }
            linkString.append("&nbsp;");
        }

        //3. 다음, 마지막 항목 만들기
        if (currentPage < pageCount) {
            linkString.append("&nbsp;");
            linkString.append(String.format(
                    "[<a href='%s?cp=%d'>다음</a>]", linkUrl, currentPage + 1));
            linkString.append("&nbsp;");
            linkString.append("&nbsp;");
            linkString.append(String.format(
                    "[<a href='%s?cp=%d'>마지막</a>]", linkUrl, pageCount));
        }

        return linkString.toString();
    }

}
