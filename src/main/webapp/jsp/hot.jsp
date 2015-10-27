<%-- 
    Document   : hot
    Created on : Sep 22, 2015, 12:07:21 AM
    Author     : Rodrigo
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>IF68B - 9GAG clone</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="font-awesome-4.4.0/css/font-awesome.min.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="content.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="main.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="post.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="sidebar.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="ads.css">
    </head>
    <body>
        <!---  Facebook  API  -->
        <script>
            // Facebook JS SDK
            window.fbAsyncInit = function () {
                FB.init({
                    appId: '611311742344814',
                    xfbml: true,
                    status: true,
                    version: 'v2.5'
                });
            };
            (function (d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id)) {
                    return;
                }
                js = d.createElement(s);
                js.id = id;
                js.src = "//connect.facebook.net/en_US/sdk.js";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));
            //Share code
            FB.ui({
                method: 'share',
                href: 'https://developers.facebook.com/docs/',
            }, function (response) {});
            function search() {
                window.location.href = "/search";
            }
            function shareFacebook(postid) {
                window.location.href = "https://www.facebook.com/sharer/sharer.php?app_id=611311742344814&sdk=joey&u=http://localhost:8085/gag?p=" + postid;
            }
            function shareTwitter(postid, title) {
                window.location.href = "https://twitter.com/intent/tweet?text=" + title + "&url=http://localhost:8085/gag?p=" + postid;
            }
        </script>  
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
                            <a class="navalt button btn rightitem" href="/signup">Sign up</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="content">
                <div class="stream">
                    <c:forEach var="post" items="${postList}">
                        <div class="post">
                            <div>
                                <h2><a href="/gag?p=${post.id}">  ${post.title} </a></h2>
                            </div>
                            <div>
                                <img src='${post.imgURL}'/>
                            </div>
                            <p class="stats">
                                <span>${post.likes} points</span>
                                &middot;
                                <span><a href="/gag?p=${post.id}"> comments</a></span>
                            </p>
                            <div class="actions">
                                <div class="actions">
                                    <ul class="votes">
                                        <li class="btn border"><span class="fa fa-arrow-up"></span></li>
                                        <li class="btn border"><span class="fa fa-arrow-down"></span></li>
                                    </ul>
                                    <ul>
                                        <li class="btn border"><span class="fa fa-comment"></span></li>
                                    </ul>
                                    <ul class="social right">
                                        <li class="btn social facebook">
                                            <span class="fa fa-facebook" onclick="shareFacebook(${post.id});">&nbsp;</span>
                                            <span class="label">Facebook</span>
                                        </li>
                                        <li class="btn social twitter">
                                            <span class="fa fa-twitter" onclick="shareTwitter(${post.id}, ${post.title});">&nbsp;</span>
                                            <span class="label">Twitter</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <hr>
                    </c:forEach>
                </div>
        <div class="sidebar">
                    <div class="item">
                        <div class="ad">
                            <img src="./img/ad1.png"></img>
                        </div>
                    </div>
        </div>
    </body>
</html>
