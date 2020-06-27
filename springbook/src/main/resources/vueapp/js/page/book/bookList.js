new Vue({
    el: '#App',
    data() {
        return {
            loading: false,
            bookList: [{}]
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