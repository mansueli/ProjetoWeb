<%-- 
    Document   : login
    Created on : Oct 20, 2015, Oct 20, 2015 12:57:38 PM
    Author     : Rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="popup.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="main.css">
        <title>Log in</title>
    </head>
    <body class="dark">
        <div class="popup">
            <h1>Login</h1>
            <section>
                <form name="login" action="login" method="post" accept-charset="utf-8">
                    <ul>
                        <li>
                            <label for="usermail">Email</label>
                            <input type="email" name="usermail" placeholder="yourname@email.com" required>
                        </li>
                        <li>
                            <label for="password">Password</label>
                            <input type="password" name="password" placeholder="password" required>
                        </li>
                        <li>
                            <input class="btn" type="submit" value="Log in">
                        </li>
                    </ul>
                </form>
            </section>
        </div>
    </body>
</html>
