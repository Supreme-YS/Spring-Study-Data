const button = document.getElementById("getOembed")
button.addEventListener("click", getData)

const apiURL = "http://localhost:8080/api/oembed/?url="

function getData() {
    const urlParam = document.getElementById("url").value.replace(/\/\s*$/, '')

    const request = new XMLHttpRequest

    request.onload = () => {
        if (request.status === 200) {
            console.log(request.response)
            jsonToTable(request.response)
            twttr.widgets.load(document.getElementById("container"))

        }
        if (request.status === 400) {
            console.log(request.response)
            alert(request.response['error_message'])
        }
        if (request.status === 403) {
            console.error(request.response)
            alert(request.response['error_message'])
        }
    };
    request.responseType = "json";
    request.open('GET', apiURL + urlParam)
    request.send()
}

function jsonToTable(response) {
    const table = document.getElementById("oembedResult")
    table.innerHTML = ''
    const parser = new DOMParser();

    let keys = Object.keys(response)

    for (let i = 0; i < keys.length; i++) {
        let key = keys[i];
        let td;
        if (key === 'title') {
            td = `<tr><td>${key}</td><td><b>${response[key]}</b></td></tr>`

        } else if (key.includes("_url")) {

            td = `<tr><td>${key}</td><td><a href="${response[key]}">${response[key]}</a></td></tr>`;

            if (key === ("thumbnail_url")) {

                td = `<tr><td>${key}</td><td><img src="${response[key]}" alt="썸네일 이미지" /></td></tr>`;

            }
        } else {

            td = `<tr><td>${key}</td><td>${response[key]}</td></tr>`;
        }

        console.log("key : " + key + ", value : " + response[key])
        table.innerHTML += td;
    }
}
