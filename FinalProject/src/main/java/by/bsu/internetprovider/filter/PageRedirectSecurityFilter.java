package by.bsu.internetprovider.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class PageRedirectSecurityFilter ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
@WebFilter( urlPatterns = { "/jsp/*" },
        initParams = { @WebInitParam(name = "INDEX_PATH", value = "/controller") })
public class PageRedirectSecurityFilter implements Filter {
    /** Field indexPath  */
    private String indexPath;

    /**
     * Method init ...
     *
     * @param fConfig of type FilterConfig
     * @throws ServletException when
     */
    public void init(FilterConfig fConfig) throws ServletException {
        indexPath = fConfig.getInitParameter("INDEX_PATH");
    }
    /**
     * Method doFilter ...
     *
     * @param request of type ServletRequest
     * @param response of type ServletResponse
     * @param chain of type FilterChain
     * @throws IOException when
     * @throws ServletException when
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
        chain.doFilter(request, response);
    }
    /**
     * Method destroy ...
     */
    public void destroy() {
    }
}
