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
import static edu.utfpr.projetoweb.utils.ServletUtils.printError;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
@WebServlet(name = "SubmitPostServlet", urlPatterns = {"/submit"})
public class SubmitPostServlet extends HttpServlet {

    private UserRepository userRepository = UserRepository.getInstance();
    private PostRepository postRepository = PostRepository.getInstance();
    private final String[] categoryList = {"funny", "meme", "gaming", "cosplay", "aww", "comic"};

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
        RequestDispatcher view = request.getRequestDispatcher("jsp/submit.jsp");
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
        HttpSession session = request.getSession(false);
        boolean isValidCategory = false;
        if (session != null) {
            String category = request.getParameter("category").toLowerCase();
            if (isValidCategory(category)) {
                String title = request.getParameter("title");
                String url = request.getParameter("url");
                UserEntity user = userRepository.findbyUsername((String) session.getAttribute("username"));
                String regex = "(http(s?):/)(/[^/]+)+\" + \"\\.(?:jpg|gif|png)";
                Matcher m = Pattern.compile(regex).matcher(url.toLowerCase());
                if (url.startsWith("http")
                        && ((url.toLowerCase().endsWith(".jpg"))
                        || (url.toLowerCase().endsWith(".gif"))
                        || (url.toLowerCase().endsWith(".jpeg"))
                        || (url.toLowerCase().endsWith(".png")))) {
                    PostEntity post = new PostEntity(user, title, url, 1, category);
                    postRepository.save(post);
                    PrintWriter out = response.getWriter();
                    try {
                        //response.sendRedirect("http://www.studytonight.com");
                        response.sendRedirect("hot");
                    } finally {
                        out.close();
                    }
                } else {
                    printError(request, response, "Invalid Image URL.");
                }
            } else {
                printError(request, response, "You cannot create your own category.");
            }
        } else {
            printError(request, response, "You cannot send a post without being logged in.");
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

    private boolean isValidCategory(String category) {
        for (String c : categoryList) {
            if (category.equals(c)) {
                return true;
            }
        }
        return false;
    }
}
