/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.projetoweb.servlets;

import edu.utfpr.projetoweb.entities.PostEntity;
import edu.utfpr.projetoweb.repositories.PostRepository;
import edu.utfpr.projetoweb.repositories.UserRepository;
import static edu.utfpr.projetoweb.utils.ServletUtils.getIntParameterValue;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rodrigo
 */
@WebServlet(name = "CategoryServlet", urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {

    UserRepository userRepository = UserRepository.getInstance();
    PostRepository postRepository = PostRepository.getInstance();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<PostEntity> postList;
        try {
            if (request.getParameterMap().containsKey("c")) {
                
                String category = request.getParameter("c");
                if (request.getParameterMap().containsKey("p")) {
                    String param = request.getParameter("p");
                    int p = getIntParameterValue(param);
                    postList = postRepository.getPostsbyCategory(category, p);
                } else {
                    postList = postRepository.getPostsbyCategory(category, 0);
                }
                if (!postList.isEmpty()) {
                    request.setAttribute("postList", postList);
                    request.setAttribute("category", category);
                    RequestDispatcher view = request.getRequestDispatcher("jsp/category.jsp");
                    view.forward(request, response);
                }else{
//                     response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                     response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        }catch(Exception e){
             response.setStatus(HttpServletResponse.SC_NOT_FOUND);
             response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }  
    }

            /**
             * Handles the HTTP <code>GET</code> method.
             *
             * @param request servlet request
             * @param response servlet response
             * @throws ServletException if a servlet-specific error occurs
             * @throws IOException if an I/O error occurs
             */
            @Override
            protected void doGet
            (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                processRequest(request, response);
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
            protected void doPost
            (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                processRequest(request, response);
            }

            /**
             * Returns a short description of the servlet.
             *
             * @return a String containing servlet description
             */
            @Override
            public String getServletInfo
            
                () {
        return "Short description";
            }// </editor-fold>

        }
