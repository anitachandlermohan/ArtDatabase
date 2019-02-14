let piece_array = [];
let gallery_array = [];
let searchResults = [];
function loadPieceDB(){
    let xhttp = new XMLHttpRequest();
    let url = "http://localhost:8080/api/PieceModel";
    xhttp.onreadystatechange = function(){
        if(xhttp.readyState ==4 && xhttp.status == 200){
            let piece_list = JSON.parse(xhttp.responseText);
            for(let i in piece_list){
                piece_array.push(piece_list[i]);
                
            }
        }
    }
    xhttp.open("GET", url, true);
    xhttp.send();
    
}

function loadGalleryDB(){
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
    return gallery_array;
   
}

function searchPieceDB(searchTerm){
    // let searchTerm = document.getElementById("searchTerm").value.toLowerCase();
    let searchResults = [];
    for(let i = 0; i<piece_array.length;i++){
        galleryID = piece_array[i].gallery;
        gallery = gallery_array.find(item => item.id === galleryID);
        let piece = piece_array[i];
        let pieceType = JSON.stringify(piece.type).toLowerCase();
        let pieceName = JSON.stringify(piece.name).toLowerCase();
        let pieceArtist = JSON.stringify(piece.artist).toLowerCase();
        let pieceGallery = JSON.stringify(gallery.name).toLowerCase();
        let pieceCountry = JSON.stringify(gallery.country).toLowerCase();
        let pieceCity = JSON.stringify(gallery.city).toLowerCase();
        
        if(pieceType.includes(searchTerm)|| pieceName.includes(searchTerm)|| pieceArtist.includes(searchTerm)
        || pieceGallery.includes(searchTerm)|| pieceCountry.includes(searchTerm)|| pieceCity.includes(searchTerm)){
            searchResults.push(piece);   
            
        }
    }
   presentSearchResults(searchResults); 
    
}

function presentSearchResults(searchResults){

    let resulttable = document.createElement("TABLE");
    let table_body = document.createElement("TBODY");
   
    document.getElementById("searchresults").innerHTML = (
        "<table id ='searchresultsTable' class ='table table-hover row-clickable'>" + 
            "<thead>" +
                "<th>Name</th>" +
                
                "<th>Artist</th>"+ 
                "<th>Type</th>" +
                 "<th>Gallery</th>"+ 
            "</thead>" +
        "</table>"
    );
    for(let i in searchResults){
       
        galleryID = searchResults[i].gallery;
        gallery = gallery_array.find(item => item.id === galleryID);

        document.getElementById("searchresultsTable").innerHTML += (
            "<tr class ='table-row' data-href='#' onclick='generateResult("+ JSON.stringify(searchResults[i]) + ")'>" +
                "<td>" + searchResults[i].name +"</td>" +
               
                "<td>" + searchResults[i].artist +"</td>"+
                 "<td>" + searchResults[i].type +"</td>" +
                 "<td>" + gallery.name +", "+ gallery.country +  "</td>" +
            "</tr>"                

        );
     document.getElementById("searchresults").scrollIntoView();   
}

function clearSearch(){
    let tablenode = document.getElementById("searchresultsTable");
    let firstnode = tablenode.firstChild;
    while (firstnode){
        tablenode.removeChild(firstnode);
        firstnode = tablenode.firstChild;
    }
}
}
$(document).ready(function($){
$(".table-row").click(function (){
    window.document.location = 
    $(this).data("href");
    });
});

function generateResult(result){
    let pieceDesc = document.getElementById("description");
    let pieceimage = document.getElementById("picture");
    let galleryID = result.gallery;
    let gallery = gallery_array.find(item => item.id === galleryID);
    let pageurl=  "gallerypage.html";
     galleryURL = passGalleryParameters(galleryID, pageurl)
        pieceDesc.innerHTML = ("<h2>" + result.name +"</h2>" );
        pieceimage.innerHTML = ("<img src ='" + result.imageRef + "' class ='img-fluid'>");
        pieceDesc.innerHTML += ( "<p>"+ result.artist + "</p>");
        pieceDesc.innerHTML += ("<p>" + result.description + "</p>");
        pieceDesc.innerHTML += ("<p>Currently displayed in: </br><a href =" + galleryURL + ">" + gallery.name + "</a></p>");
       
    document.getElementById("showmore").scrollIntoView();
    
}

function passGalleryParameters(parameter,pageurl){
    let querystring = "?gallery=" + parameter;
    let newURL = pageurl + querystring;
    return newURL;
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
function useURLsearchparams(){
   
    if(window.location.href.indexOf("search")> -1){
    let searchTerm = getURLparams("search").toLowerCase();
    searchPieceDB(searchTerm);
    }

    
}

 function getRandom(items){
     return items[Math.floor(Math.random()*items.length)];
 } 

 function populateCarousel(){
     let carousel1 = getRandom(piece_array);
     let carousel2 = getRandom(piece_array);
     let carousel3 = getRandom(piece_array);
     let carousel1div = document.getElementById("carousel1");
     let carousel2div = document.getElementById("carousel2");
     let carousel3div = document.getElementById("carousel3");
     carousel1div.style.backgroundImage = "url("+ carousel1.imageRef+ ")";
     carousel1div.style.backgroundPosition = "center top";

     carousel2div.style.backgroundImage = "url("+ carousel2.imageRef+ ")";
     carousel2div.style.backgroundPosition = "center top";

     carousel3div.style.backgroundImage = "url("+ carousel3.imageRef+ ")";
     carousel3div.style.backgroundPosition = "center top";
     
    carousel1div.innerHTML += "<div class='carousel-caption d-none d-md-block'>"
               +"<h3>"+ carousel1.name + "</h3>"
              + "<p>"+ carousel1.artist + "</p></div>"
    
     carousel2div.innerHTML += "<div class='carousel-caption d-none d-md-block'>"
               +"<h3>"+ carousel2.name + "</h3>"
              + "<p>"+ carousel2.artist + "</p></div>"
     carousel3div.innerHTML += "<div class='carousel-caption d-none d-md-block'>"
               +"<h3>"+ carousel3.name + "</h3>"
              + "<p>"+ carousel3.artist + "</p></div>"
                    

 }