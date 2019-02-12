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
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.setRequestHeader('Access-Control-Allow-Origin', '*');
    
    xhttp.send();
    if(callback){
        callback();
    }

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

function generateGalleryPage(name,city,country,image, description){
    document.getElementById("headertext").innerHTML += "<h1 class = 'header-text display-2'>" + name + "</h1>";
     document.getElementById("headerimage").innerHTML += 
         "<img src = '" + image + "' class = 'header-img'>";
    document.getElementById("country").innerHTML += "<h2 class='display-2'>" + city +  " </br><small class = 'display-4'>" + country + "</small></h2>";  
    document.getElementById("desc").innerHTML += "<h1>" + description + "</h1>"
}

function passSearchParameters(){
    
    let searchTerm = document.getElementById("searchTerm").value;
    let querystring = "?search=" + searchTerm;
    let newURL = "index.html" + querystring;
    console.log(newURL);
    return newURL;
}
   