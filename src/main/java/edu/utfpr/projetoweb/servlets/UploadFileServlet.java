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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Rodrigo
 */

@WebServlet(name = "UploadFileServlet", urlPatterns = {"/upload"})
@MultipartConfig(location="/posts/img", fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class UploadFileServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        folderPath = new File(getServletContext().getInitParameter("img.dir"));
        File[] folder = folderPath.listFiles();
        for (File f : folder) {
            imgID++;
        }
    }
    private final UserRepository userRepository = UserRepository.getInstance();
    private final PostRepository postRepository = PostRepository.getInstance();
    private File folderPath = null;
    private long imgID = 0;

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
        RequestDispatcher view = request.getRequestDispatcher("jsp/upload.jsp");
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
        if (session != null) {
            request.setAttribute("session", session);
            String title = request.getParameter("title");
            String category = request.getParameter("category");
            Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
            String fileName = filePart.getSubmittedFileName();
            try (InputStream input = filePart.getInputStream()) {
                try {
                    BufferedImage img = ImageIO.read(input);
                    File file = new File(folderPath.getAbsoluteFile() + "/" + imgID + ".png");
                    ImageIO.write(img, "png", file);
                    UserEntity user = userRepository.findbyUsername((String) session.getAttribute("username"));
                    //public PostEntity(UserEntity user, String title, String imgURL, String category)
                    PostEntity post = new PostEntity(user, title, "/posts/img/" + imgID + ".png", category);
                   
                } catch (Exception e) {
//                    imprimeErro(response);
                }
            }
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
