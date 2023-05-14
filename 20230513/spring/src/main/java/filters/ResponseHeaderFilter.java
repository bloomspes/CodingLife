package filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.github.bloomspes.sample.services.CheckEmailService;

@WebFilter("/filter-response-header/*")
public class ResponseHeaderFilter implements Filter {
    private final CheckEmailService checkEmailService;

    public ResponseHeaderFilter(CheckEmailService checkEmailService) {
        this.checkEmailService = checkEmailService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(
            ServletRequest request, ServletResponse response, FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String email = httpServletRequest.getParameter("email");

        if (isEmailDuplicate(email)) {
            httpServletResponse.setHeader("X-Email-Duplicate", "true");
        }

        if (!isEmailDuplicate(email)) {
            httpServletResponse.setHeader("X-Email-Duplicate", "false");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean isEmailDuplicate(String email) {
        return checkEmailService.checkEmailExisting(email);
    }
}
