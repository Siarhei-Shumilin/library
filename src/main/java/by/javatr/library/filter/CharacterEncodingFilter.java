package by.javatr.library.filter;

import javax.servlet.*;
import java.io.IOException;
/**
 * filter for UTF-8 encoding
 */
public class CharacterEncodingFilter implements Filter {
    private String code = "UTF-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        code = null;
    }
}
