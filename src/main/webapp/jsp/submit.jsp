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
        <title>Login</title>
    </head>
    <style>
        .loginform ul {
            padding: 0;
            margin: 0;
        }
        .loginform li {
            display: inline;
            float: left;
        }
        label {
            display: block;
            color: #999;
        }
        .cf:before,
        .cf:after {
            content: ""; 
            display: table;
        }

        .cf:after {
            clear: both;
        }
        .cf {
            *zoom: 1;
        }
        :focus {
            outline: 0;
        }

        .loginform input:not([type=submit]) {
            padding: 5px;
            margin-right: 10px;
            border: 1px solid rgba(0, 0, 0, 0.3);
            border-radius: 3px;
            box-shadow: inset 0px 1px 3px 0px rgba(0, 0, 0, 0.1), 
                0px 1px 0px 0px rgba(250, 250, 250, 0.5) ;
        }

        .loginform input[type=submit] {
            border: 1px solid rgba(0, 0, 0, 0.3);
            background: #64c8ef; /* Old browsers */
            background: -moz-linear-gradient(top,  #64c8ef 0%, #00a2e2 100%); /* FF3.6+ */
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#64c8ef), color-stop(100%,#00a2e2)); /* Chrome,Safari4+ */
            background: -webkit-linear-gradient(top,  #64c8ef 0%,#00a2e2 100%); /* Chrome10+,Safari5.1+ */
            background: -o-linear-gradient(top,  #64c8ef 0%,#00a2e2 100%); /* Opera 11.10+ */
            background: -ms-linear-gradient(top,  #64c8ef 0%,#00a2e2 100%); /* IE10+ */
            background: linear-gradient(to bottom,  #64c8ef 0%,#00a2e2 100%); /* W3C */
            filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#64c8ef', endColorstr='#00a2e2',GradientType=0 ); /* IE6-9 */
            color: #fff;
            padding: 5px 15px;
            margin-right: 0;
            margin-top: 15px;
            border-radius: 3px;
            text-shadow: 1px 1px 0px rgba(0, 0, 0, 0.3);
        }
        .center{
            width: 500px ;
            margin-left: auto ;
            margin-right: auto ;
            margin-top: 5%;

        }
        .message-page h1{
            font-size:50px;
            text-transform:uppercase;
            margin:0 0 10px;
        }
        .message-page p{
            margin:0 0 5px;font-size:18px;color:#999;
        }
        .message-page a{
            color:#000090;
        }
    </style>
    <body>
        <div class="center">
            <section class="loginform cf">
                <form name="submit" action="submit" method="post" accept-charset="utf-8">
                    <ul>
                        <li>
                            <label for="title">Title</label>
                            <input type="text" name="title" placeholder="A cool title here!" required>
                        </li>
                        <li>
                            <label for="url">url</label>
                            <input type="text" name="url" placeholder="http://example.org/image.jpg" required>
                        </li>
                        <li>
                            <label for="categoty">Category</label>
                            <input type="text" name="category" list="categoryList" required>
                            <datalist id="categoryList" >
                                <option>funny</option>  
                                <option>gaming</option>
                                <option>cosplay</option>
                                <option>meme</option>
                                <option>aww</option>
                                <option>comic</option>
                            </datalist>
                        </li>
                        <li>
                            <input type="submit" value="submit">
                        </li>
                    </ul>
                </form>
                <div>
                </div>
            </section>
        </div>
        <div>

        </div>
        <div class="message-page center">
            <p><a href="/upload">Is the file on your PC, then use the file uploader!</a></p>
        </div>
    </body>
</html>



