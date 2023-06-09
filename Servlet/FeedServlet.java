package com.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UserDAO;
import com.Db.DBConnect;
import com.User.Feedback;

public class FeedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        Feedback feedback = new Feedback();
        feedback.setName(name);
        feedback.setEmail(email);
        feedback.setSubject(subject);
        feedback.setMessage(message);

        UserDAO dao = new UserDAO(DBConnect.getConn());
        boolean success = dao.addFeedback(feedback);

        HttpSession session = request.getSession();
        if (success) {
            session.setAttribute("feedback-success", "Feedback submitted successfully!");
            response.sendRedirect("index.html");
        } else {
            session.setAttribute("failed-msg", "Something went wrong while submitting feedback.");
            response.sendRedirect("contactUs.html");
        }
    }
}
