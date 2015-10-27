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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
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
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Rodrigo
 */
@WebServlet(name = "UploadFileServlet", urlPatterns = {"/upload"})
@MultipartConfig(location = "/posts/img", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadFileServlet extends HttpServlet {

    public void setSettings() {
        try {
            File[] folder = folderPath.listFiles();

            for (File f : folder) {
                imgID++;
            }
        } catch (Exception e) {

        }
    }
    private final UserRepository userRepository = UserRepository.getInstance();
    private final PostRepository postRepository = PostRepository.getInstance();
    private final File folderPath = new File("D:\\posts\\img");
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
//        System.out.println("here");
//        setSettings();
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            request.setAttribute("session", session);
//            String title = request.getParameter("title");
//            String category = request.getParameter("category");
//            Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
//            String fileName = filePart.getSubmittedFileName();
//            try (InputStream input = filePart.getInputStream()) {
//                try {
//                    BufferedImage img = ImageIO.read(input);
//                    File file = new File(folderPath.getAbsolutePath() + "/" + imgID + ".png");
//                    ImageIO.write(img, "png", file);
//                    UserEntity user = userRepository.findbyUsername((String) session.getAttribute("username"));
//                    //public PostEntity(UserEntity user, String title, String imgURL, String category)
//                    PostEntity post = new PostEntity(user, title, "/posts/img/" + imgID + ".png", category);
//                    System.out.println("Upload done! Yes!!!");
//                } catch (Exception e) {
//                    printError(request, response, e.getLocalizedMessage());
//                }
//            } catch (Exception e) {
//                printError(request, response, e.getLocalizedMessage());
//            }
//        }
        HttpSession session = request.getSession(false);
        if (session != null) {
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            String title = "";
            String category = "";//request.getParameter("category");
            setSettings();
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    if (fieldName.equals("title")) {
                        title = fieldValue;
                    }
                    if (fieldName.equals("category")) {
                        category = fieldValue;
                    }
                    System.out.println("fieldName>"+ fieldName +"\n fieldVal>" + fieldValue);
                } else {
                    // Process form file field (input type="file").
                    String fieldName = item.getFieldName();
                    String fileName = FilenameUtils.getName(item.getName());
                    InputStream fileContent = item.getInputStream();
                    File targetFile = new File(folderPath.getAbsolutePath()+"\\"+imgID+item.getName().substring(item.getName().indexOf('.')));
                    FileUtils.copyInputStreamToFile(fileContent, targetFile);
                    UserEntity user = userRepository.findbyUsername((String) session.getAttribute("username"));
                    PostEntity post = new PostEntity(user, title, "/posts/img/" + targetFile.getName(), category);
                    postRepository.save(post);
                    System.out.println("Upload done! Yes!!!");
                }
            }
        } catch (Exception e) {
            throw new ServletException("Cannot parse multipart request.", e);
        }
        }else{
            printError(request, response, "Not allowed");
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
