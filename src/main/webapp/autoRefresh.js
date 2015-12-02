function loadjscssfile(filename, filetype) {
    if (filetype == "js") { //if filename is a external JavaScript file
        var fileref = document.createElement('script')
        fileref.setAttribute("type", "text/javascript")
        fileref.setAttribute("src", filename)
    } else if (filetype == "css") { //if filename is an external CSS file
        var fileref = document.createElement("link")
        fileref.setAttribute("rel", "stylesheet")
        fileref.setAttribute("type", "text/css")
        fileref.setAttribute("href", filename)
    }
    if (typeof fileref != "undefined")
        document.getElementsByTagName("head")[0].appendChild(fileref)
}

function refresh() {
    loadjscssfile("mustache.js", "js");
//    var container = document.querySelector('.stream')
    var currentPostHref = container.querySelector('.post h2 a').getAttribute('href');
    alert(currentPostHref);
    var regex = new RegExp('.*=([0-9]+)');
    var result = regex.exec(currentPostHref);
    if (result) {
        var url = window.location.protocol + "//" + window.location.host + window.location.pathname;
        var currentPostId = result[1];
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("GET", url + "?last=" + currentPostId + "&json", true);
//        alert('open: ' + url + "?last=" + currentPostId + "&json");
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
//                alert('resp: ' + xmlhttp.responseText);
                var data = JSON.parse(xmlhttp.responseText);
                alert('data: ' + data);
                for (var i = 0; i < data.length; i++) {
                    alert('data: ' + data[i].title);
                    var postHtml = Mustache.render(template, data[i]);
                    alert('temp: ' + temp);
                    var resultDiv = document.createElement('div');
                    resultDiv.innerHTML = postHtml;
                    container.insertBefore(resultDiv, container.firstChild);
                    break;
                }
            }
        };
        xmlhttp.send();
    }
}
