/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.projetoweb.servlets;

import com.google.gson.Gson;
import edu.utfpr.projetoweb.entities.PostEntity;
import edu.utfpr.projetoweb.repositories.PostRepository;
import edu.utfpr.projetoweb.repositories.UserRepository;
import static edu.utfpr.projetoweb.utils.ServletUtils.getIntParameterValue;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "FreshServlet", urlPatterns = {"/fresh"})
public class FreshServlet extends HttpServlet {
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
        List<PostEntity> postList;
        int lastID ;
        if (request.getParameterMap().containsKey("last")) {
            String param = new String(request.getParameter("last"));
        }
        if (request.getParameterMap().containsKey("p")) {
            String param = new String(request.getParameter("p"));
            int p = getIntParameterValue(param);
            postList = postRepository.getPostsbyLikesASC(p);

        } else {
            postList = postRepository.getPostsbyLikesASC(0);
        }
        request.setAttribute("postList", postList);
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie != null) {
                    if (cookie.getName().equals("sessionID")) {
                        request.setAttribute("sessionID", cookie.getValue());
                        HttpSession session = request.getSession(false);
                        if (session != null) {
                            request.setAttribute("session", session);
                            request.setAttribute("logged", true);
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
        if (request.getParameterMap().containsKey("json")) {
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                Gson gson = new Gson();
                String json = gson.toJson(postList);
                out.println(json);
            } catch (Exception e) {
            }
        } else {
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher view = request.getRequestDispatcher("jsp/fresh.jsp");
            view.forward(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
