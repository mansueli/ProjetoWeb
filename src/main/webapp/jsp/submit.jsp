<%-- 
    Document   : submit
    Created on : Oct 26, 2015, Oct 26, 2015 1:29:18 PM
    Author     : Rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="popup.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="main.css">
        <title>Submit a post</title>
    </head>
    <body class="dark">
        <div class="popup">
            <h1>Submit a post</h1>
            <section>
                <form name="submit" action="submit" method="post" accept-charset="utf-8">
                    <ul>
                        <li>
                            <label for="title">Title</label>
                            <input type="text" name="title" placeholder="A cool title here!" required>
                        </li>
                        <li>
                            <label for="url">URL</label>
                            <input type="text" name="url" placeholder="http://example.org/image.jpg" required>
                        </li>
                        <li>
                            <label for="category">Category</label>
                            <input type="text" name="category" list="categoryList" required>
                            <datalist id="categoryList" >
                                <option>Funny</option>  
                                <option>Gaming</option>
                                <option>Cosplay</option>
                                <option>Meme</option>
                                <option>Aww</option>
                                <option>Comic</option>
                            </datalist>
                        </li>
                        <li>
                            <input class="btn" type="submit" value="Post">
                        </li>
                    </ul>
                </form>
            </section>
        </div>
        <!--        
                <div class="message-page center">
                    <p><a href="/upload">Is the file on your PC, then use the file uploader!</a></p>
                </div>
        -->
    </body>
</html>



