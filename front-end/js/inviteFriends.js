$(document).ready(function() {

    $('a.btn_1').click(function(){
        var groupName = $('#groupName').val(); //id of first password field
        var email = $('#email').val(); //id of second password field
        var input = { groupName: groupName, moderatorAccount: localStorage.id, watcherAccount: email };
        $.ajax({
            url: "http://localhost:8080/group/invite",
            type: "POST",
            data: input,
            //data: { groupName: 'Cloud', moderatorAccount: 'Jack' },
            //processData: false,   // jQuery不要去处理发送的数据
            //contentType: false,
            success:function(data){
                console.log(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                // 状态码
                console.log(XMLHttpRequest.status);
                // 状态
                console.log(XMLHttpRequest.readyState);
                // 错误信息
                console.log(textStatus);
            }
        });

        var input2 = { groupName: groupName, watcherAccount: email };
        $.ajax({
            url: "http://localhost:8080/group/join",
            type: "POST",
            data: input2,
            crossDomain: true,
            //data: { groupName: 'Cloud', moderatorAccount: 'Jack' },
            //processData: false,   // jQuery不要去处理发送的数据
            //contentType: false,
            success:function(data){
                console.log(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
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