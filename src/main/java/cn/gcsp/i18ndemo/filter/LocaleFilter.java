package cn.gcsp.i18ndemo.filter;

/**
 * @author gongchengship@163.com
 */

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;


import java.io.IOException;

public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Filters the request to replace underscores with hyphens in the "Accept-Language" header.
     * This filter is used to standardize the language settings in the request header,
     * ensuring that all language-related processing uses a consistent format with hyphens.
     *
     * @param request The ServletRequest object, which contains the client's request information.
     * @param response The ServletResponse object, which contains the response information to the client.
     * @param chain The FilterChain object, used to pass control to the next filter or the requested resource.
     * @throws IOException If an I/O error occurs during the filtering process.
     * @throws ServletException If a servlet error occurs during the filtering process.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Cast the ServletRequest to HttpServletRequest for access to HTTP-specific methods.
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // Retrieve the "Accept-Language" header to determine the client's language preference.
        String acceptLanguage = httpRequest.getHeader("Accept-Language");

        // If the "Accept-Language" header is not null, process it.
        if (acceptLanguage != null) {
            // Replace underscores with hyphens in the "Accept-Language" header
            // because some systems may use underscores as separators, which need to be unified into the standard format using hyphens.
            acceptLanguage = acceptLanguage.replace("_", "-");

            // Wrap the request to modify the "Accept-Language" header
            // This is done to intercept and modify the "Accept-Language" header without affecting other parts of the request.
            String finalAcceptLanguage = acceptLanguage;
            HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(httpRequest) {
                @Override
                public String getHeader(String name) {
                    // Override the getHeader method to return the modified "Accept-Language" header
                    if ("Accept-Language".equalsIgnoreCase(name)) {
                        return finalAcceptLanguage;
                    }
                    // For other headers, call the superclass method to return the original value.
                    return super.getHeader(name);
                }
            };
            // Pass the wrapped request object to the next filter or the requested resource in the chain.
            chain.doFilter(wrappedRequest, response);
        } else {
            // If the "Accept-Language" header is null, pass the request and response objects directly to the next filter or the requested resource.
            chain.doFilter(request, response);
        }
    }


    @Override
    public void destroy() {
    }
}
