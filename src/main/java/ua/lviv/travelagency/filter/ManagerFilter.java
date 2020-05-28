package ua.lviv.travelagency.filter;

import ua.lviv.travelagency.domain.Role;
import ua.lviv.travelagency.shared.FilterService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Arrays;

@WebFilter({"/statistic", "/manager", "/addhotel", "/deleteHotel", "/deleteRoom", "/managerooms", "/addroom"})
public class ManagerFilter implements Filter {

    private final FilterService filterService = FilterService.getFilterService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterService.doFilterValidation(servletRequest, servletResponse, filterChain, Arrays.asList(Role.MANAGER));
    }

    @Override
    public void destroy() {

    }
}
