$(document).ready(function() {

    $('a.btn_1').click(function(){
        var groupName = $('#groupName').val(); //id of first password field
        //var moderatorName = $('#moderatorName').val(); //id of second password field
        var input = { groupName: groupName, moderatorAccount: localStorage.id };
        $.ajax({
            url: "http://localhost:8080/group/create",
            type: "POST",
            data: input,
            //data: { groupName: 'Cloud', moderatorAccount: 'Jack' },
            //processData: false,   // jQuery不要去处理发送的数据
            //contentType: false,
            success:function(data){
                console.log(data);
                if(data.code == "5000"){
                    alert("The Group has already been existed!");
                }else{
                    location.href = "ModeratorIndex.html";
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("The Group has already been existed!");
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