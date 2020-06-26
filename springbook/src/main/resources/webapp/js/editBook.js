// makeDefault();
function getQueryVariable(variable)
{

    var query = window.location.search.substring(1);

    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}
var id = getQueryVariable("id");
var book_id = getQueryVariable("bookId");
var book_name = getQueryVariable("bookName");
var book_price = getQueryVariable("bookPrice");
var date = getQueryVariable("date");
// console.log(id+" "+book_id+" "+book_name+" "+book_price+" "+date+" ");

function makeDefault() {
    console.log() ;
    // document.getElementById("book_id").placeholder ="1111";

}


function update() {
     if(document.getElementById("book_id").value != 0){book_id = document.getElementById("book_id").value;}
     if(document.getElementById("book_name").value != 0){book_name = document.getElementById("book_name").value;}
     if(document.getElementById("book_price").value != 0){book_price = document.getElementById("book_price").value;}
     if(document.getElementById("date").value !=0){date = document.getElementById("date").value;}
     const book_json = {};
     book_json["book_id"] = book_id;
     book_json["book_name"] = book_name;
     book_json["book_price"] = book_price;
     book_json["date"] = date;
     book_json["id"] = id;

     updateBook(book_json);


}




