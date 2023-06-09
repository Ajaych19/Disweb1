package com.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UserDAO;
import com.Db.DBConnect;
import com.User.UserDetails;

public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        boolean disabled = request.getParameter("disabled") != null;

        UserDetails userDetails = new UserDetails();
        userDetails.setName(name);
        userDetails.setEmail(email);
        userDetails.setPassword(password);
        userDetails.setDob(dob);
        userDetails.setGender(gender);
        userDetails.setAddress(address);
        userDetails.setPhone(phone);
        userDetails.setDisabled(disabled);

        UserDAO userDAO = new UserDAO(DBConnect.getConn());
        boolean isUserAdded = userDAO.addUser(userDetails);

        HttpSession session = request.getSession();
        if (isUserAdded) {
            session.setAttribute("reg-success", "Registration Successfully...");
            response.sendRedirect("About.html");
        } else {
            session.setAttribute("failed-msg", "Something went wrong on the server.");
            response.sendRedirect("sign.html");
        }
    }
}
