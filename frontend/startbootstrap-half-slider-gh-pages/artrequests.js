let piece_array = []
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

function searchPieceDB(){
    let searchTerm = document.getElementById("searchTerm").value;
    let searchResults = [];
    for(let i = 0; i<piece_array.length;i++){
        let piece = piece_array[i];
        if(piece.name === searchTerm || piece.type === searchTerm || piece.artist === searchTerm){
            searchResults.push(piece);
        }
    }
    
}

function presentSearchResults(){
    
    let resulttable = document.createElement("TABLE");
    let table_body = document.createElement("TBODY");
    for(let i in search_results){
        let row = document.createElement("TR");
                // title column //
                let title_cell = document.createElement("TD");
                let title_cell_text = document.createTextNode( JSON.stringify(search_results[i].name));
                title_cell.appendChild(title_cell_text);
                row.appendChild(title_cell);

                // description column //
                let desc_cell = document.createElement("TD");
                let desc_cell_text = document.createTextNode(JSON.stringify(search_results[i].type));
                desc_cell.appendChild(desc_cell_text);
                row.appendChild(desc_cell);

                 //category //
                let cat_cell = document.createElement("TD");
                let cat_cell_text = document.createTextNode(JSON.stringify(searchResults[i].artist));
                cat_cell.appendChild(cat_cell_text);
                row.appendChild(cat_cell);

                //append this row to table// 
                table_body.appendChild(row);
    }

    resulttable.appendChild(table_body);
    document.getElementById("searchresults").appendChild(resulttable);
}