<%-- 
    Document   : post
    Created on : Oct 20, 2015, Oct 20, 2015 9:15:18 AM
    Author     : Rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Post</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="font-awesome-4.4.0/css/font-awesome.min.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="content.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="menu.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="main.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="post.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="sidebar.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="ads.css">
    </head>
    <body>
        <div id="fb-root"></div>
        <script>
            (function (d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id))
                    return;
                js = d.createElement(s);
                js.id = id;
                js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.5&appId=611311742344814";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));
        </script>
        <div class="menu">
            <div class="menuLeft">
                <ul class="main">
                    <li><a class="logo" href="/">9GAG</a></li>
                    <li><a class="navmain hot selected" href="/hot">Hot</a></li>
                    <li><a class="navmain trending" href="/trending">Trending</a></li>
                    <li><a class="navmain fresh" href="/fresh">Fresh</a></li>
                </ul>
                <ul>
                    <li><a class="navalt" href="/category?c=funny">Funny</a></li>
                    <li><a class="navalt" href="/category?c=gaming">Gaming</a></li>
                    <li><a class="navalt" href="/category?c=comic">Comic</a></li>
                    <li><a class="navalt" href="/category?c=meme">Meme</a></li>
                    <li><a class="navalt" href="/category?c=gif">@TODO GIF</a></li>
                    <li><a class="navalt" href="/category?c=wtf">@TODO WTF</a></li>
                    <li><a class="navalt" href="/category?c=gaming">@TODO Geeky</a></li>
                </ul>
            </div>
            <div class="menuRight">
                <div class="search"><a href="/search">Search</a></div>
                <a class="navalt" href="/login">Login</a>
                <button class="button" type="button">Sign up</button>
            </div>
        </div>
        <div class="content">
            <div class="stream">
                <div class="post">
                    <div>
                        <h2>${post.title}</h2>
                        <p>
                            <span>${post.likes} points</span>
                            &middot;
                            <span>48 comments</span>
                        </p>
                    </div>

                    <div class="actions">
                        <div class="actions">
                            <ul class="votes">
                                <li class="btn border"><span class="fa fa-arrow-up">&nbsp;</span>UP</li>
                                <li class="btn border"><span class="fa fa-arrow-down"></span></li>
                            </ul>
                            <ul>
                                <li class="btn border"><span class="fa fa-comment"></span></li>
                            </ul>
                            <ul class="social">
                                <li class="btn social facebook"><div class="fb-share-button" data-href="http://localhost:8084/gag/${post.id}" data-layout="icon"></div></li>
                                <li class="btn social twitter"><span class="fa fa-twitter">&nbsp;</span>Twitter</li>
                            </ul>
                            <ul>
                                <li class="btn border"><span class="fa fa-ellipsis-h"></span></li>
                            </ul>
                            <ul>
                                <span class="arrow"></span>
                                <li class="btn next">Next Post</li>
                            </ul>
                        </div>
                    </div>

                    <div>
                        <img id='image' src='${post.imgURL}'/>
                    </div>

                    <div class="ad">
                        <div class="image">
                            <img src="./img/ad2.png"></img>
                        </div>
                    </div>

                    <div class="actions">
                        <ul class="social">
                            <li><div class="btn half left social facebook fb-share-button fb_btn" data-href="http://localhost:8084/gag/${post.id}" data-layout="button"></div></li>
<!--                            <li class="btn half left social facebook fb-share-button fb_btn" data-href="http://localhost:8084/gag/${post.id}" data-layout="button">Share on Facebook</li>-->
                            <li class="btn half right social twitter">Share on Twitter</li>
                        </ul>
                    </div>

                    <hr>
                    <a href="mailto:example@example.org">REPORT</a>
                    <div class="fb-comments" data-href="http://localhost:8084/post/34" data-width="600" data-numposts="7"></div>
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

                <div class="item">
                    <div class="post">
                        <div class="image">
                            <a>RANDOM IMAGE 1</a>
                        </div>
                        <h3>Random post 1</h3>
                    </div>
                </div>

                <div class="item">
                    <div class="post">
                        <div class="image">
                            <a>RANDOM IMAGE 2</a>
                        </div>
                        <h3>Random post 2</h3>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
