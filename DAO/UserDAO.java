package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.User.Feedback;
import com.User.UserDetails;

public class UserDAO {
    private Connection conn;

    public UserDAO(Connection conn) {
        super();
        this.conn = conn;
    }

    public boolean addUser(UserDetails userDetails) {
        boolean success = false;

        try {
            String query = "INSERT INTO user (name, email, password, dob, gender, address, phone, disabled) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userDetails.getName());
            ps.setString(2, userDetails.getEmail());
            ps.setString(3, userDetails.getPassword());
            ps.setString(4, userDetails.getDob());
            ps.setString(5, userDetails.getGender());
            ps.setString(6, userDetails.getAddress());
            ps.setString(7, userDetails.getPhone());
            ps.setBoolean(8, userDetails.isDisabled());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public UserDetails loginUser(UserDetails userDetails) {
        UserDetails user = null;
        try {
            String query = "SELECT * FROM user WHERE email=? AND password=?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userDetails.getEmail());
            ps.setString(2, userDetails.getPassword());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new UserDetails();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setDob(rs.getString("dob"));
                user.setGender(rs.getString("gender"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setDisabled(rs.getBoolean("disabled"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
    
    public boolean addFeedback(Feedback feedback) {
        boolean success = false;
        try {
            String query = "INSERT INTO feedback (name, email, subject, message) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, feedback.getName());
            ps.setString(2, feedback.getEmail());
            ps.setString(3, feedback.getSubject());
            ps.setString(4, feedback.getMessage());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}
