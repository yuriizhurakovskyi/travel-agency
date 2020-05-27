package ua.lviv.travelagency.servlet;

import com.google.gson.Gson;
import ua.lviv.travelagency.domain.Role;
import ua.lviv.travelagency.domain.User;
import ua.lviv.travelagency.dto.UserLogin;
import ua.lviv.travelagency.service.UserService;
import ua.lviv.travelagency.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getUserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userService.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("userId", user.getId());
            session.setAttribute("role", user.getRole());
            UserLogin userLogin = new UserLogin();
            if (user.getRole().equals(Role.MANAGER.name()))
                userLogin.destinationUrl = "manager";
            else userLogin.destinationUrl = "index";
            userLogin.userEmail = user.getEmail();
            String json = new Gson().toJson(userLogin);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        }
    }
}
