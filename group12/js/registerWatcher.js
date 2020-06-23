$(document).ready(function() {

    $('a.btn_1').click(function(){
        var userName = $('#userName').val(); //id of first password field
        var password = $('#password').val(); //id of second password field
        var input = { userName: userName, password: password };
        $.ajax({
            url: "http://localhost:8080/watcher/register",
            type: "POST",
            data: input,
            //data: { groupName: 'Cloud', moderatorAccount: 'Jack' },
            //processData: false,   // jQuery不要去处理发送的数据
            //contentType: false,
            success:function(data){
                console.log(data);
                if(data.code == "4101"){
                    alert("The Username has already been used!");
                }else if(data.code == "4000"){
                    alert("The Password must between 8 and 20 characters");
                }else{
                    location.href = "loginWatcher.html";
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if(XMLHttpRequest.status == "500"){
                    alert("The Username has already been used!");
                }
                else if(XMLHttpRequest.status == "400"){
                    alert("The Password must between 8 and 20 characters");
                }
                console.log(XMLHttpRequest.status);
                // 状态
                console.log(XMLHttpRequest.readyState);
                // 错误信息
                console.log(textStatus);
            }
        });
    });
});