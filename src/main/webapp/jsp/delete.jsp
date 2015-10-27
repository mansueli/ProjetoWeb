<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Delete Post</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="content.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="main.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="post.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="ads.css">
        <link media="screen,projection" type="text/css" rel="stylesheet" href="popup.css">
    </head>
    <body class="dark">
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
            }, function
                    (response) {
            });
            function search() {
                window.location.href = "/search";
            }
            function shareFacebook(postid) {
                window.location.href = "https://www.facebook.com/sharer/sharer.php?app_id=611311742344814&sdk=joey&u=http://localhost:8085/gag?p=" + postid;
            }
        </script>  
        <div class="popup">
            <h2>${post.title}</h2>

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
                    <li>
                        <label class="prompt" for="submit">Are you sure you wish to delete this post?</label>
                        <input class="btn left" type="submit" value="Yes">
                    </li>
                </ul>

            </form>

            <a class="btn left no" href="http://localhost:8085/gag?p=${post.id}">No</a>

            <div class="clear"></div>
        </div>
    </body>
</html>
