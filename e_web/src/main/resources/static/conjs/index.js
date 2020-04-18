$(function () {

    //加载所有的导航菜单数据
    loadAllWebMenus();
    //收到登录模块的令牌
    getToken();
    var InterValObj; //定义定时器

    //js轮询
    InterValObj = window.setInterval(getToken, 10000);

    function loadAllWebMenus() {
        $.ajax({
            type:"POST",
            url:"webmenu/loadAll",
            dataType:"JSON",
            success:function (data) {
                var webMenuStr = '';
                $.each(data,function (i,webMenu) {
                    webMenuStr += '<li><a href="'+webMenu.url+'">'+webMenu.title+'</a></li>';
                });
                $(".menu_r").html(webMenuStr);
            },
            error:function () {
                alert("服务器异常！！")
            }
        });
    }


    //收到登录模块的令牌
    function getToken() {
        console.log("在获取令牌。。")
        $.ajax({
            type:"POST",
            xhrFields:{withCredentials:true}, //允许ajax的跨域访问
            url:"http://localhost:8091/webusers/getToken",
            success:function (data) {
                console.log(data);
                if(data!=''){
                    //关掉js轮询
                    window.clearInterval(InterValObj);
                    //获取到了令牌，拿着令牌去redis中找用户数据
                    getRedisLoginUser(data);

                }
            },
            error:function () {
                layer.msg('服务器异常！！', {icon: 3,time:2000,anim: 6,shade:0.5});
            }
        });
        //获取到了令牌，拿着令牌去redis中找用户数据
        function getRedisLoginUser(token) {
            $.ajax({
                type:"POST",
                url:"http://localhost:8091/webusers/getRedisLoginUser",
                data:{"token":token},
                success:function (data) {
                    console.log(data);
                    if(data!=""){

                        $("#loginHeader").replaceWith('<span class="fl" id="loginHeader">你好，欢迎'+data.uname+'<img src="'+data.userheader+'" width="45px;" height="45px;"/>&nbsp; &nbsp; </span>');
                    }else {
                        InterValObj = window.setInterval(getToken, 10000);  //重新开启js轮询
                    }
                },
                error:function () {
                    alert("服务器异常！！")
                }
            });
        }
    }

    //注销
    $("#exitUser").click(function () {
        $.ajax({
            type:"POST",
            url:"http://localhost:8091/webusers/exitUser",
            xhrFields:{withCredentials:true}, //允许ajax的跨域访问获取cookie
            success:function (data) {
                console.log(data);
                if(data){
                    $("#loginHeader").replaceWith('<span class="fl" id="loginHeader">你好，请<a href="model/toLogin">登录</a>&nbsp; <a href="model/toReg" style="color:#ff4e00;">免费注册</a>&nbsp; </span>');
                }
            },
            error:function () {
                alert("服务器异常！！")
            }
        });
    })
});