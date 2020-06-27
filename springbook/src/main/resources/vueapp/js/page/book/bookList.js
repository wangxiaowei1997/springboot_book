new Vue({
    el: '#App',
    data() {
        return {
            loading: false,
            //图书列表
            bookList: [{}],
            //正在被编辑的书
            tempBook:{
                bookId:'',
                bookName:'',
                bookPrice:'',
                date:'',
                id:''
            },
            editBookDialogVisible:false,
            title:''
        }
    },
    created() {
        this.loading = true;
        this.queryBookList();
    },
    methods: {
        queryBookList() {

            getBookList().then(response => {
                let data = response.data;
                console.log(data);
                this.bookList = data;
                this.loading = false;
            })
        },
        editBook(book){
            console.log(book);
            this.tempBook = JSON.parse(JSON.stringify(book));
            this.title = '编辑书籍';
            this.editBookDialogVisible = true;
        },
        addNewBook(){
            this.tempBook={};
            this.title = '新增书籍';
            this.editBookDialogVisible = true;
        },
        saveBook(){
            const  param = this.tempBook;
            if(param.id !== null && param.id > 0){
                updateBook(param).then(response =>{
                    console.log('保存成功');
                    this.tempBook = '';
                    this.editBookDialogVisible = false;
                    this.queryBookList();
                })
            }else {
                addBook(param).then(response =>{
                    console.log('保存成功');
                    this.tempBook = '';
                    this.editBookDialogVisible = false;
                    this.queryBookList();
                })
            }
        },
        closeEditBookDialog(){
            this.tempBook = '';
            this.editBookDialogVisible = false;
        }
    }
});


function getBookList() {
    return instance.request({
        url: '/book/list',
        method: 'get',
    })
}

function addBook(data) {
    return instance.request({
        url: '/book/add',
        method: 'post',
        data: data
    })
}


function updateBook(data) {
    return instance.request({
        url: '/book/update',
        method: 'post',
        data: data
    })
}

function deleteBook(data) {
    return instance.request({
        url: '/book/delete',
        method: 'post',
        data: data
    })
}