$(document).ready(function() {
    //var groupName = $('#groupName').val(); //id of first password field
    //var email = $('#email').val(); //id of second password field
    console.log(localStorage.type);
    console.log(localStorage.id);
    var input = { type: localStorage.type, account: localStorage.id };
    $.ajax({
        url: "http://localhost:8080/group/getGroups",
        type: "GET",
        data: input,
        crossDomain: true,
        //data: { groupName: 'Cloud', moderatorAccount: 'Jack' },
        //processData: false,   // jQuery不要去处理发送的数据
        //contentType: false,
        success:function(a){
            console.log(a);
            var result = "";
            for(var i=0; i<a.data.groupList.length;i++) {
                result+= "<div class=\"col-lg-4\">"+
                    "<div class=\"box_pricing\" >" +
                    "<h4 id>"+ a.data.groupList[i].groupName +"</h4>"+
                    "<p>Moderator: "+ a.data.groupList[i].moderatorAccount +"</p> " +
                    "<p>Here is the list of watchers in this group. You can invite more friends to this specific group.</p>"

                for(var j=0;j<a.data.groupList[i].watcherList.length;j++){
                    var ls = a.data.groupList[i].watcherList[j];
                    result+= "<li>"+ ls +"</li>"
                }
                result+="<hr>" +
                    "<a class=\"btn_1\" href=\"manageGroupWatcher.html\" id=\"unsubcribe\">Unsubscribe group</a>"+
                    "</div>"+
                    "</div>"
            }
            $("#test").html(result);
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