package kr.or.bit.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;


@WebFilter(
        description = "어노테이션 활용 필터 적용하기",
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8", description = "모든 페이지 한글 처리")
        })
public class EncodingFilter implements Filter {

    private String encoding;

    public EncodingFilter() {

    }

    public void init(FilterConfig fConfig) throws ServletException {
        this.encoding = fConfig.getInitParameter("encoding");
        System.out.println("Filter init : " + this.encoding);
    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //요청
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(this.encoding);  //모든 페이지에 적용되는 공통관심
            System.out.println("encoding : " + this.encoding);
        }
        chain.doFilter(request, response);
        //응답
    }


}
