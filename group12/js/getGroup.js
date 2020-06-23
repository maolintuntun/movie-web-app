$(document).ready(function() {
    //var groupName = $('#groupName').val(); //id of first password field
    //var email = $('#email').val(); //id of second password field
    var input = { groupName: "Love"};
    $.ajax({
        url: "http://localhost:8080/group/getGroup",
        type: "GET",
        data: input,
        crossDomain: true,
        //data: { groupName: 'Cloud', moderatorAccount: 'Jack' },
        //processData: false,   // jQuery不要去处理发送的数据
        //contentType: false,
        success:function(data){
            console.log(data.data);
            var result = "<h4>GroupName: "+ data.data.groupName +"</h4>";
            result+= "<p>Moderator: "+ data.data.moderatorAccount +"</p> " +
                     "<p>Here is the list of watchers in this group 01. You can invite more friends to this specific group.</p>"

            for(var i=0;i<data.data.watcherList.length;i++){
                var ls = data.data.watcherList[i];
                result+= "<li>"+ ls +"</li>"
            }
            result+="<hr> <a href=\"inviteFriends.html\">Invite friends</a>"
            $("#test").html(result);
            //for(var i=0;i<data.length;i++){  //遍历data数组
                //var ls = data.groupName;
                //result +="<span>"+ls.name+"</span>";
                //$("#test").html(result);
            //}
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

    $('a.btn_1').click(function(){
        //var groupName = $('#groupName').val(); //id of first password field
        //var email = $('#email').val(); //id of second password field
        var input = { groupName: "Love"};
        $.ajax({
            url: "http://localhost:8080/group/getGroup",
            type: "GET",
            data: input,
            crossDomain: true,
            //data: { groupName: 'Cloud', moderatorAccount: 'Jack' },
            //processData: false,   // jQuery不要去处理发送的数据
            //contentType: false,
            success:function(data){
                console.log(data);
                var result = '';
                for(var i=0;i<data.length;i++){  //遍历data数组
                    var ls = data[i];
                    result +="<span>测试："+ls.name+"</span>";
                    $("#test").html(html);
                }
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