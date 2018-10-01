var myList = [
    {
        "book_id": 1001,
        "book_name": "银河帝国",
        "book_price": 58.5,
        "date": "9/23",
        "id": 1
    },
    {
        "book_id": 1002,
        "book_name": "三体",
        "book_price": 28,
        "date": "9/23",
        "id": 2
    }
];
var url3 ='http://127.0.0.1:8080/book/list';
var ajax = new Ajax;
ajax.ajax({
    url: url3,
    contentType:'application/json',
    type:'get',
    success: function(data){
        var arr=eval(data);
        myList = arr;
        console.log(myList);

    }
});
window.onload = function () {
    var oTab = document.getElementById('book-table');
    var oTbody = oTab.tBodies[0];
    var book_s;
    for(var index=0;index<myList.length;index++){
        var oTr = document.createElement('tr');
        oTbody.appendChild(oTr);

        var oTd1 = document.createElement('td');
        var oTd2 = document.createElement('td');
        var oTd3 = document.createElement('td');
        var oTd4 = document.createElement('td');
        var oTd5 = document.createElement('td');
        var oTd6 = document.createElement('td');
        var oTd7 = document.createElement('td');
        oTd1.innerHTML = myList[index].id;
        oTd2.innerHTML = myList[index].book_id;
        book_s = myList[index].book_id;
        oTd3.innerHTML = myList[index].book_name;
        oTd4.innerHTML = myList[index].book_price;
        oTd5.innerHTML = myList[index].date;
        oTd6.innerHTML = '<a onclick="deleteBook(\'+book_s+\')" ><span class="glyphicon glyphicon-edit"></span></a>';
        oTd7.innerHTML = '<a href="../WEB-INF/views/editBook.html" onclick="" ><span class="glyphicon glyphicon-trash"></span></a>';
        oTr.appendChild(oTd1);
        oTr.appendChild(oTd2);
        oTr.appendChild(oTd3);
        oTr.appendChild(oTd4);
        oTr.appendChild(oTd5);
        oTr.appendChild(oTd6);
        oTr.appendChild(oTd7);
    }
};
function addBook() {
    var book_id = document.getElementById("book_id").value;
    var book_name = document.getElementById("book_name").value;
    var book_price = document.getElementById("book_price").value;
    var date = document.getElementById("date").value;

    var book_json = {};
    book_json["book_id"] = book_id;
    book_json["book_name"] = book_name;
    book_json["book_price"] = book_price;
    book_json["date"] = date;
    var book_jsonString = JSON.stringify(book_json);
    console.log(book_jsonString);
    var ajax = new Ajax();
    url4 = 'http://127.0.0.1:8080/book/add';
    ajax.ajax({
        url: url4,
        contentType:'application/json',
        type:'post',
        data:book_jsonString,
        success: function(data){
            var arr=eval(data);
            console.log(arr);
            location.reload();
        }
    });


}

function deleteBook(bookid) {
    console.log(bookid);
    var book_json = {};
    book_json["book_id"] = bookid;
    var book_jsonString = JSON.stringify(book_json);
    var ajax = new Ajax();
    url4 = 'http://127.0.0.1:8080/book/delete';
    ajax.ajax({
        url: url4,
        contentType:'application/json',
        type:'post',
        data:book_jsonString,
        success: function(data){
            var arr=eval(data);
            console.log(arr);
            location.reload();
        }
    });

}


function updateBook(id) {
    console.log(id);


}