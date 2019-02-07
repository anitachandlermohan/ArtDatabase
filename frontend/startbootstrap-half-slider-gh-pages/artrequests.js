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

}

function searchPieceDB(){
    let searchTerm = document.getElementById("searchTerm").value;
    console.log(searchTerm);
    let searchResults = [];
    for(let i = 0; i<piece_array.length;i++){
        
        let piece = piece_array[i];
        
        if( piece.type === searchTerm ){
            searchResults.push(piece);
            
        }
    }
   presentSearchResults(searchResults); 
    
}

function presentSearchResults(searchResults){

    let resulttable = document.createElement("TABLE");
    let table_body = document.createElement("TBODY");
    
    document.getElementById("searchresults").innerHTML = (
        "<table id ='searchresultsTable' class ='table'>" + 
            "<tr>" +
                "<th>Name</th>" +
                "<th>Description</th>" +
                "<th>Type</th>"+ 
                 "<th>Gallery</th>"+ 
            "</tr>" +
        "</table>"
    );
    for(let i in searchResults){
        galleryID = searchResults[i].gallery;
        console.log(galleryID);
        gallery = gallery_array.find(item => item.id === galleryID);
        document.getElementById("searchresultsTable").innerHTML += (
            "<tr>" +
                "<td>" + searchResults[i].name +"</td>" +
                "<td>" + searchResults[i].type +"</td>" +
                "<td>" + searchResults[i].description +"</td>"+
                 "<td>" + gallery.name +"</td>" +
            "</tr>"                

        );
        
    //     let row = document.createElement("TR");
    //             // title column //
    //             let title_cell = document.createElement("TD");
    //             let title_cell_text = document.createTextNode( JSON.stringify(searchResults[i].name));
    //             title_cell.appendChild(title_cell_text);
    //             row.appendChild(title_cell);

    //             // description column //
    //             let desc_cell = document.createElement("TD");
    //             let desc_cell_text = document.createTextNode(JSON.stringify(searchResults[i].type));
    //             desc_cell.appendChild(desc_cell_text);
    //             row.appendChild(desc_cell);

    //              //category //
    //             let cat_cell = document.createElement("TD");
    //             let cat_cell_text = document.createTextNode(JSON.stringify(searchResults[i].artist));
    //             cat_cell.appendChild(cat_cell_text);
    //             row.appendChild(cat_cell);

    //             //append this row to table// 
    //             table_body.appendChild(row);
    // }

    // resulttable.appendChild(table_body);
    // document.getElementById("searchresultsTable").appendChild(resulttable);
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