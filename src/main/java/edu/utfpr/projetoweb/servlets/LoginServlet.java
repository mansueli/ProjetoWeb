/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.projetoweb.servlets;

import edu.utfpr.projetoweb.entities.UserEntity;
import edu.utfpr.projetoweb.repositories.PostRepository;
import edu.utfpr.projetoweb.repositories.UserRepository;
import edu.utfpr.projetoweb.utils.Encryption;
import static edu.utfpr.projetoweb.utils.ServletUtils.printError;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rodrigo
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    UserRepository userRepository = UserRepository.getInstance();
    PostRepository postRepository = PostRepository.getInstance();
    final int WEEK_SECONDS = 604800;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher view = request.getRequestDispatcher("jsp/login.jsp");
        view.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("usermail");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true);
        UserEntity user = userRepository.findbyEmail(email);
        try {
            if (user.getEmail().equals(email)) {
                int salt = user.getSalt();
                try {
                    if (user.getPasswordHash().equals(Encryption.getUserHash(password, salt))) {
                        Cookie sessionCookie = new Cookie("sessionID", session.getId());
                        session.setAttribute("username", user.getUsername());
                        session.setAttribute("avatar", user.getAvatarURL());
                        session.setAttribute("logged", "true");
                        session.setAttribute("userID", user.getId());
                        response.addCookie(sessionCookie);
                        session.setMaxInactiveInterval(WEEK_SECONDS);
                        response.sendRedirect("hot");
                    } else {
                        printError(request,response,"Wrong login");
                    }
                } catch (Exception e) {
                    printError(request,response,"Wrong login");
                }
            }
        } catch (Exception e) {
            printError(request,response,"Something went wrong. Does this username exists?");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
