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
        <title>Search</title>
    </head>
    <body class="dark">
        <div class="popup">
            <h1>Search for a post</h1>
            <section>
                <form name="search" action="search" method="post" accept-charset="utf-8">
                    <ul>
                        <li>
                            <input id="searchable" type="text" name="q" placeholder="search query" required>
                        </li>
                        <li>
                            <input class="btn" type="submit" value="Search">
                        </li>
                    </ul>
                </form>
            </section>
            <h2 class="msg">Loading . . . </h2>
            <hr/>
            <div class="results">
            </div>
            <script type="text/javascript" charset="utf-8">

                /***
                 * Ajax->
                 *      XMLHttpRequest
                 *      Progress Indicator
                 *      Live Search
                 *      JSON-Message
                 */
                var container = document.querySelector(".popup"),
                        mensagens = document.querySelector(".msg");
                document.querySelector("#searchable").addEventListener("keyup", function (ev) {
                    if (ev.keyCode != 13)
                        return;
                });
                /***
                 * Ajax->
                 *      XMLHttpRequest
                 *      Progress Indicator
                 *      Submission Throttling
                 *      JSON-Message
                 */
                setInterval(function () {
                    var container = document.querySelector(".results");
                    var xhr = new XMLHttpRequest();
                    xhr.open("GET", "JSONSearch?q=" + document.querySelector("#searchable").value, true);
                    xhr.onprogress = function () {
//                        alert("loading");
                         mensagens.innerHTML = "loading...";
                    }
                    xhr.onloadstart = function () {
//                        alert("search");
                        mensagens.innerHTML = "Searching...";
                    }
                    xhr.onloadend = function (e) {
                        mensagens.innerHTML = "";
                    }
                    xhr.send();
                    xhr.onreadystatechange = function () {
                        container.innerHTML = "";
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            mensagens.innerHTML = "success";
                            var postObjects = JSON.parse(xhr.responseText);
                            for (var i = 0; i < (postObjects.length - 1); i++) {
                                var li = document.createElement("li");
                                li.innerHTML = "<li><a href=\"/gag?p=" + postObjects[i].id + "\">" + postObjects[i].title + "</a></li>";
                                container.appendChild(li);
                            }
                            mensagens.innerHTML = "";
                        }
                        if (xhr.readyState < 4) {
                            mensagens.innerHTML = "Loading...";
                        }
                        if (xhr.readyState === 4 && xhr.status !== 200) {
                            mensagens.value = "Error";
                        }
                    };
                }, 2500);
            </script>
    </body>
</html>
