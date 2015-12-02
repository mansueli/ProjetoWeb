function refresh() {
    var currentPostHref = document.querySelector('.stream .post h2 a').getAttribute('href');
    var regex = new RegExp('.*=([0-9]+)');
    var result = regex.exec(currentPostHref);
    if (result) {
        var url = window.location.protocol + "//" + window.location.host + window.location.pathname;
        var currentPostId = result[1];
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("GET", url + "?last=" + currentPostId + "&json", true);
        alert('open: ' + url + "?last=" + currentPostId + "&json");
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                alert('resp: ' + xmlhttp.responseText);
                var data = JSON.parse(xmlhttp.responseText);
                alert('data: ' + data);
                for (var i = 0; i < data.length; i++) {
                    var post = data[i];
                    alert('data[i]: ' + post.title);
                }
            }
        };
        xmlhttp.send();
    }
}
