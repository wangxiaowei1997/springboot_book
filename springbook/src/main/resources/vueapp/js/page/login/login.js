

new Vue({
    el: '#App',
    data() {
        return{
            loading: false,
            loginUser:{
                username:'',
                password:''
            }
        }
    },
    created() {

    },
    methods:{
        loginIn(){
            const  param = this.loginUser;
            login(param).then(response => {
                let token = response.data.token;
                console.log(token);
                this.setCookie("token",token,1)
                window.location.href = "/view/book/bookList.html";
            })
        }
    }
});


function login(param) {
    return instance.request({
        url: '/login',
        method: 'post',
        data: param
    })
}