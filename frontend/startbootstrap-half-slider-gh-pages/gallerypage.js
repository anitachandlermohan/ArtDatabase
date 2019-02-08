let piece_array = [];
let gallery_array = [];
let searchResults = [];

function loadGalleryDB(callback){
    let xhttp = new XMLHttpRequest();
    let url = "http://localhost:8080/api/Gallery";
    xhttp.onreadystatechange = function(){
        if(xhttp.readyState ==4 && xhttp.status == 200){
            let gallery_list = JSON.parse(xhttp.responseText);
            for(let i in gallery_list){
                
                gallery_array.push(gallery_list[i]);
            }
        }
    }
    xhttp.open("GET", url, true);
    xhttp.send();
    if(callback){
        callback();
    }
    let galleryID = getURLparams("gallery");
    gallery = gallery_array.find(item => item.id === galleryID);
    console.log(gallery_array.length);
     document.getElementById("content").innerHTML += ("<h1>" + "what" + "</h1>")
     document.getElementById("headerimage").innerHTML += (
         "<img src = '" + "crap" + "' class = img-responsive>"
     )

}

function getURLparams(variable){
    let query = window.location.search.substring(1);
    let urlparam = query.split("&");
    for (let i = 0; i <urlparam.length; i++){
        let keyvaluepair = urlparam[i].split("=");
        if(keyvaluepair[0] == variable){
            return keyvaluepair[1];
        }
    }
    return(false);

}