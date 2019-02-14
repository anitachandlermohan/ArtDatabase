
// function inputToJSONstring(){
//     let gallery = '{'
//         + '"name" :' + '"' + document.getElementById("galleryName").value + '",'
//         + '"description" :' + '"' + document.getElementById("description").value + '",'
//         + '"city" :' + '"' + document.getElementById("city").value + '",'
//         + '"country" :' + '"' + document.getElementById("country").value + '",'
//         + '"imageRef" :' + '"' + document.getElementById("imageRef").value + '",'
//     return gallery;
// }

function sendGallery(){
    let gallery = {}
    gallery.name = document.getElementById("galleryName").value;
    gallery.description = document.getElementById("description").value
    gallery.city = document.getElementById("city").value
    gallery.country = document.getElementById("country").value
    gallery.imageRef =  document.getElementById("imageRef").value
    let jsonGallery = JSON.stringify(gallery);

    let request = new XMLHttpRequest();
    let url = "http://localhost:8080/api/Gallery";
    
    request.open("POST",url,true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.setRequestHeader('Access-Control-Allow-Origin', '*');
    request.send( jsonGallery);
    console.log(JSON.stringify(gallery));
    let content = document.getElementById("content").innerHTML += "<h1>Gallery Created!</h1>";

}




function passSearchParameters(){
    
    let searchTerm = document.getElementById("searchTerm").value.toLowerCase();
    let querystring = "?search=" + searchTerm;
    let newURL = "index.html" + querystring;
    console.log(newURL);
    return newURL;
}

function clearForm(){
    document.getElementById("galleryName").innerHTML = "";
    document.getElementById("description").innerHTML= "";
    document.getElementById("city").innerHTML = "";
    document.getElementById("country").innerHTML= "";
    document.getElementById("imageRef").innerHTML= "";

}


$(document).ready(function($){
$(".card").click(function (){
    window.document.location = 
    $(this).data("href");
    });
});
   