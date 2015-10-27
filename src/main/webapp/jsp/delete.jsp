<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Delete Post</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="font-awesome-4.4.0/css/font-awesome.min.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="content.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="main.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="post.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="sidebar.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="ads.css">
    </head>
    <body>
        <div id="fb-root"></div>
        <div class="menu">
            <div class="menuLeft">
                <ul class="main">
                    <li><a class="logo" href="/hot">9GAG</a></li>
                    <li><a class="navmain hot selected" href="/hot">Hot</a></li>
                    <li><a class="navmain trending" href="/trending">Trending</a></li>
                    <li><a class="navmain fresh" href="/fresh">Fresh</a></li>
                </ul>
                <ul>
                    <li><a class="navalt" href="/category?c=funny">Funny</a></li>
                    <li><a class="navalt" href="/category?c=gaming">Gaming</a></li>
                    <li><a class="navalt" href="/category?c=cosplay">Cosplay</a></li>
                    <li><a class="navalt" href="/category?c=meme">Meme</a></li>
                    <li><a class="navalt" href="/category?c=aww">AWW</a></li>
                    <li><a class="navalt" href="/category?c=comic">Comic</a></li>
                </ul>
            </div>
            <div class="menuRight">
                <div class="function">
                    <c:choose>
                        <c:when test="${logged}">
                            <div class="search rightitem" onclick="search();"><a href="/search">Search</a></div>
                            <a class="navalt rightitem" href="userMain">${session.getAttribute("username")}</a>
                            <a class="navalt button btn rightitem" href="/logout">Log out</a></li>
                            <a class="navalt button btn rightitem" href="/submit">+ Submit</a>
                        </c:when>
                        <c:otherwise>
                            <div class="search rightitem" onclick="search();"><a href="/search">Search</a></div>
                            <a class="navalt button btn rightitem" href="/login">Log in</a>
                            <a class="navalt button btn rightitem" href="/singup">Sing up</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="content">
                <div class="stream">
                    <div class="post">
                        <div>
                            <h2>${post.title}</h2>
                            <p>
                                <span>${post.likes} points</span>
                            </p>
                        </div>
                        <div>
                            <img id='image' src='${post.imgURL}'/>
                        </div>

                        <div class="ad">
                            <div class="image">
                                <img src="./img/ad2.png"></img>
                            </div>
                        </div>

                        <h3>Are you sure you wish to delete this post?</h3>

                        <form name="delete" action="delete" method="post" accept-charset="utf-8">
                            <ul>
                                <li>
                                    <input type="hidden" name="postID" value="${post.id}" required>
                                </li>
                                <li class="button btn">
                                    <input type="submit" value="Yes">
                                </li>
                            </ul>

                        </form>

                        <a class="button btn no" href="/gag?p=${post.id}">No</a>

                    </div>
                </div>

                <div class="sidebar">
                    <div class="item">
                        <div class="ad">
                            <div class="image">
                                <a>PLACE YOUR AD HERE</a>
                            </div>
                        </div>
                    </div>
                </div>
                </body>
                </html>
