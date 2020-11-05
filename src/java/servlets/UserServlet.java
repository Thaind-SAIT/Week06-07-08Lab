/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.UserService;
import models.User;

/**
 *
 * @author 808278
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService us = new UserService();
        try {
            request.setAttribute("users", us.getAll());
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService us = new UserService();
        
        User user = (User) request.getAttribute("user");
        request.setAttribute("user", user);
        
        
        if ( request.getParameter("action").equals("Add") ) {
            try {
                us.insert(
                        request.getParameter("username"),
                        request.getParameter("password"),
                        request.getParameter("firstname"),
                        request.getParameter("lastname"),
                        request.getParameter("email")
                );
                request.setAttribute("users", us.getAll());
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ( request.getParameter("action").equals("Delete") ) {
            try {
                us.delete(request.getParameter("user"));
                request.setAttribute("users", us.getAll());
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ( request.getParameter("action").equals("Edit") ) {
            try {
                request.setAttribute("user", us.get(request.getParameter("user")) );
                request.setAttribute("users", us.getAll());
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ( request.getParameter("action").equals("Save") ) {
            try {
                us.update(
                        request.getParameter("admin"),
                        request.getParameter("password"),
                        request.getParameter("firstname"),
                        request.getParameter("lastname"),
                        request.getParameter("email")
                );
                request.setAttribute("users", us.getAll());
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
    }

}
