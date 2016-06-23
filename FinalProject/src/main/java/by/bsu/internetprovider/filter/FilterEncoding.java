package by.bsu.internetprovider.filter;

import javax.servlet.*;
import java.io.IOException;


/**
 * Class FilterEncoding ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class FilterEncoding implements Filter {
    /** Field FILTERABLE_CONTENT_TYPE  */
    private static final String FILTERABLE_CONTENT_TYPE = "application/x-www-form-urlencoded";

    /** Field ENCODING_DEFAULT  */
    private static final String ENCODING_DEFAULT = "UTF-8";

    /** Field ENCODING_INIT_PARAM_NAME  */
    private static final String ENCODING_INIT_PARAM_NAME = "encoding";

    /** Field encoding  */
    private String encoding;

    /**
     * Method destroy ...
     */
    public void destroy() {

    }

    /**
     * Method doFilter ...
     *
     * @param request of type ServletRequest
     * @param response of type ServletResponse
     * @param chain of type FilterChain
     * @throws ServletException when
     * @throws IOException when
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String contentType = request.getContentType();
        if (contentType != null && contentType.startsWith(FILTERABLE_CONTENT_TYPE)) {
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    /**
     * Method init ...
     *
     * @param config of type FilterConfig
     * @throws ServletException when
     */
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter(ENCODING_INIT_PARAM_NAME);
        if (encoding == null) {
            encoding = ENCODING_DEFAULT;
        }
    }
}
