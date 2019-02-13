let piece_array = [];
let gallery_array = [];
let searchResults = [];

function loadGalleryDB(){
    let xhttp = new XMLHttpRequest();
    let url = "http://localhost:8080/api/Gallery";
    xhttp.onreadystatechange = function(){
        if(xhttp.readyState ==4 && xhttp.status == 200){
            let gallery_list = JSON.parse(xhttp.responseText);
            for(let i in gallery_list){
                let galleryURL = passGalleryParameters(gallery_list[i].id, "gallerypage.html" );
                document.getElementById("gallerycards").innerHTML += "<a href = '"+galleryURL+ "' class = 'card mb-3'>" 
                +  "<img class = 'card-img-top' src=" + gallery_list[i].imageRef + ">"
                + "<div class='card-body'><h1 class='card-text'>" + gallery_list[i].name + "</h1></div></a>";
            }
        }
    }
    xhttp.open("GET", url, true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.setRequestHeader('Access-Control-Allow-Origin', '*');
    
    xhttp.send();

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
function getGalleryByID(){
    let galleryID = getURLparams("gallery");
    let xhttp = new XMLHttpRequest();

    let url = "http://localhost:8080/api/Gallery/" + galleryID;

    xhttp.open("GET", url, true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.setRequestHeader('Access-Control-Allow-Origin', '*');
    xhttp.send();
 
    xhttp.onload = function(){
         let gallery = JSON.parse(xhttp.responseText);
            let galleryName = gallery.name;
            let galleryCountry = gallery.country;
            let galleryCity = gallery.city;
            let galleryImage = gallery.imageRef;
            let galleryDesc = gallery.description;
            generateGalleryPage(galleryName,galleryCity,galleryCountry,galleryImage,galleryDesc);
    }
}



function passGalleryParameters(parameter,pageurl){
    let querystring = "?gallery=" + parameter;
    let newURL = pageurl + querystring;
    return newURL;
}
function passSearchParameters(){
    
    let searchTerm = document.getElementById("searchTerm").value;
    let querystring = "?search=" + searchTerm;
    let newURL = "index.html" + querystring;
    console.log(newURL);
    return newURL;
}

function presentAllGalleries(){

}
$(document).ready(function($){
$(".card").click(function (){
    window.document.location = 
    $(this).data("href");
    });
});
   