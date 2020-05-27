package ua.lviv.travelagency.servlet;

import ua.lviv.travelagency.domain.Role;
import ua.lviv.travelagency.domain.User;
import ua.lviv.travelagency.service.UserService;
import ua.lviv.travelagency.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getUserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/registration.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");

        User user = null;
        if (!email.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty()) {
            user = userService.create(new User(firstName, lastName, email, phoneNumber, Role.USER.toString(), password));
        }
        HttpSession session = request.getSession(true);
        if (user != null) {
            session.setAttribute("userId", user.getId());
            session.setAttribute("role", user.getRole());
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }
}