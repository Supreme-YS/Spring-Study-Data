function requestUrl() {
    const userUrlData = document.getElementById('urlText').value;
    fetch('/api/oembed' + userUrlData).then((resolvedData) => {
        resolvedData.json().then((json) => {
            const render = document.getElementById('responseDataInContainer');
            const render2 = document.getElementById('title_container');
            render.innerHTML = "";
            render2.innerHTML = "";
            let titleData = "";
            if (json.title != null) {
                titleData = "<div><div>title</div><div>" + json.title + "</div></div>";
                render2.innerHTML = titleData;
            }
            let data = "";
            if (json.type != null) data += "<li>" + "<div>type</div><div>" + json.type + "</div></li>";
            if (json.version != null) data += "<li>" + "<div>version</div><div>" + json.version + "</div></li>";
            if (json.provider_name != null) data += "<li>" + "<div>provider_name</div><div>" + json.provider_name + "</div></li>";
            if (json.provider_url != null) data += "<li>" + "<div>provider_url</div><div><a href='" + json.provider_url + "'>" + json.provider_url + "</a></div></li>";
            if (json.author_name != null) data += "<li>" + "<div>author_name</div><div>" + json.author_name + "</div></li>";
            if (json.author_url != null) data += "<li>" + "<div>author_url</div><div><a href='" + json.author_url + "'>" + json.author_url + "</a></div></li>";
            if (json.is_plus != null) data += "<li>" + "<div>is_plus</div><div>" + json.is_plus + "</div></li>";
            if (json.html != null) data += "<li>" + "<div>html<br/>(" + json.width + "/" + json.height + ")</div><div><xmp>" + json.html + "</xmp>" + json.html + "</div></li>";
            if (json.width != null) data += "<li>" + "<div>width</div><div>" + json.width + "</div></li>";
            if (json.height != null) data += "<li>" + "<div>height</div><div>" + json.height + "</div></li>";
            if (json.duration != null) data += "<li>" + "<div>height</div><div>" + json.duration + "</div></li>";
            if (json.description != null) data += "<li>" + "<div>height</div><div>" + json.description + "</div></li>";
            if (json.thumbnail_url != null) data += "<li>" + "<div>thumbnail_url<br/>(" + json.thumbnail_width + "/" + json.thumbnail_height + ")</div><div><a href='" + json.thumbnail_url + "'>" + json.thumbnail_url + "</a><br/><br/><img src='" + json.thumbnail_url + "'/></div></li>";
            if (json.thumbnail_width != null) data += "<li>" + "<div>thumbnail_width</div><div>" + json.thumbnail_width + "</div></li>";
            if (json.thumbnail_height != null) data += "<li>" + "<div>thumbnail_height</div><div>" + json.thumbnail_height + "</div></li>";
            if (json.thumbnail_url_with_play_button != null) data += "<li>" + "<div>thumbnail_url_with_play_button</div><div><a href='" + json.thumbnail_url_with_play_button + "'>" + json.thumbnail_url_with_play_button + "</a></div></li>";
            if (json.upload_data != null) data += "<li>" + "<div>upload_data</div><div>" + json.upload_data + "</div></li>";
            if (json.video_id != null) data += "<li>" + "<div>video_id</div><div>" + json.video_id + "</div></li>";
            if (json.uri != null) data += "<li>" + "<div>uri</div><div>" + json.uri + "</div></li>";
            if (json.cache_age != null) data += "<li>" + "<div>cache_age</div><div>" + json.cache_age + "</div></li>";
            render.innerHTML = data;
            console.log(json);
        }).catch((err) => {
            alert('유요하지 않은 주소입니다.');
            console.log(err);
        })
    }).catch((err) => {
        alert('유요하지 않은 주소입니다.');
        console.log(err);
    });
}

