package kubo.filter;

/**
 * Created by Administrator on 2016/11/2.
 */

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Administrator
 *
 */
public class LogFilter implements Filter {
    private FilterConfig filterConfig;

    public FilterConfig getFilterConfig() {
        System.err.println("...getFilterConfig...");
        return filterConfig;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        System.err.println("...setFilterConfig...");
        this.filterConfig = filterConfig;
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
//    @Override
    public void destroy() {
        System.err.println("...filter destroy...");
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
//    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.err.println("...doFilter...");
        chain.doFilter(request, response);//看到这没，这只要是传递下一个Filter
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
//    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.err.println("...init Filter...");
        this.filterConfig = filterConfig;
    }

}