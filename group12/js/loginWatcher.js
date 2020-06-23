$(document).ready(function() {

    $('a.btn_1').click(function(){
        var userName = $('#userName').val(); //id of first password field
        var password = $('#password').val(); //id of second password field
        var input = { userName: userName, password: password };
        $.ajax({
            url: "http://localhost:8080/watcher/login",
            type: "POST",
            data: input,
            //data: { groupName: 'Cloud', moderatorAccount: 'Jack' },
            //processData: false,   // jQuery不要去处理发送的数据
            //contentType: false,
            success:function(data){
                console.log(data);
                if(data.code == "2000"){
                    localStorage.type = "Watcher";
                    localStorage.id = data.data.userName;
                    location.href = "manageGroupWatcher.html";
                }else{
                    alert("Wrong Password or Username!");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("Wrong Password or Username!");
                // 状态码
                console.log(XMLHttpRequest.status);
                // 状态
                console.log(XMLHttpRequest.readyState);
                // 错误信息
                console.log(textStatus);
            }
        });
    });
});