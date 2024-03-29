/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.projetoweb.servlets;

import edu.utfpr.projetoweb.entities.PostEntity;
import edu.utfpr.projetoweb.entities.UserEntity;
import edu.utfpr.projetoweb.repositories.PostRepository;
import edu.utfpr.projetoweb.repositories.UserRepository;
import edu.utfpr.projetoweb.utils.ServletUtils;
import static edu.utfpr.projetoweb.utils.ServletUtils.getIntParameterValue;
import static edu.utfpr.projetoweb.utils.ServletUtils.printError;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rodrigo
 */
@WebServlet(name = "DeleteServlet", urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet {

    final UserRepository userRepository = UserRepository.getInstance();
    final PostRepository postRepository = PostRepository.getInstance();

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
        HttpSession session = request.getSession(false);
        int id = getIntParameterValue(request.getParameter("p"));
        if (id != -1) {
            if (session != null) {
                request.setAttribute("session", session);
            }
            PostEntity post = postRepository.find(id);
            String url = ServletUtils.getCompleteURL(request);
            request.setAttribute("post", post);
            request.setAttribute("url", url);
            RequestDispatcher view = request.getRequestDispatcher("jsp/delete.jsp");
            view.forward(request, response);
        } else {
            response.sendRedirect("404.html");
        }
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
        HttpSession session = request.getSession(false);
        if (session != null) {
            int id = Integer.parseInt(request.getParameter("postID"));
            PostEntity post = postRepository.find(id);
            UserEntity user = userRepository.findbyUsername((String) session.getAttribute("username"));
            if (post.getUser().getUsername().equals(user.getUsername())) {
                postRepository.delete(post);
                response.sendRedirect("hot");
            } else {
                printError(request, response, "You are not allowed to perform this operation.");
            }
        } else {
            printError(request, response, "You cannot delete if you are not logged in.");
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
