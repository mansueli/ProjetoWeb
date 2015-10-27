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
@WebServlet(name = "PostServlet", urlPatterns = {"/gag"})
public class PostServlet extends HttpServlet {

    UserRepository userRepository = UserRepository.getInstance();
    PostRepository postRepository = PostRepository.getInstance();

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
        //processRequest(request, response);
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession(false);
            int id = getIntParameterValue(request.getParameter("p"));
            UserEntity user = null;
            if (id != -1) {
                if (session != null) {
                    request.setAttribute("session", session);
                }
                PostEntity post = postRepository.find(id);
                PostEntity nextPost = null;
                int i=1;
                while(nextPost == null){
                    nextPost = postRepository.find(post.getId()+i);
                    i++;
                }
                request.setAttribute("post", post);
                request.setAttribute("nextPost", nextPost);
                String url = ServletUtils.getCompleteURL(request);
                request.setAttribute("url", url);
                RequestDispatcher view = request.getRequestDispatcher("jsp/post.jsp");
                view.forward(request, response);
            } else {
                //response.sendRedirect("/404.html");
            }
        } catch (Exception e) {
            //response.sendRedirect("/404.html");
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
        System.out.println("@TODO never handle this");
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
