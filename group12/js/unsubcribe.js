$(document).ready(function() {

    $('a.btn_1').click(function(){
        //var groupName = $('#groupName').val(); //id of first password field
        //var email = $('#email').val(); //id of second password field
        var input = { groupName: "Love", watcherAccount: "Didi" };
        $.ajax({
            url: "http://localhost:8080/group/unsubscribe",
            type: "POST",
            data: input,
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