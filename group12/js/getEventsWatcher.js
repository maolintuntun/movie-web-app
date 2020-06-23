$(document).ready(function() {
    //var groupName = $('#groupName').val(); //id of first password field
    //var email = $('#email').val(); //id of second password field
    var input = { type: localStorage.type, account: localStorage.id };
    //console.log(localStorage.type);
    $.ajax({
        url: "http://localhost:8080/event/getEvents",
        type: "GET",
        data: input,
        crossDomain: true,
        //data: { groupName: 'Cloud', moderatorAccount: 'Jack' },
        //processData: false,   // jQuery不要去处理发送的数据
        //contentType: false,
        success:function(a){
            console.log(a);
            var result = "";
            for(var i=0; i<a.data.eventList.length;i++) {
                result+= "<div class=\"col-lg-4\">"+
                    "<div class=\"box_pricing\" >" +
                    "<h4>Event Name: "+ a.data.eventList[i].name +"</h4>"+
                    "<p>Moderator: "+ a.data.eventList[i].moderatorAccount +"</p> " +
                    "<p>Group Name: "+ a.data.eventList[i].groupName +"</p> " +
                    "<p>Winner: "+ a.data.eventList[i].winner +"</p > " +
                    "<ul>" +
                    "<li><strong>Event Start Time: </strong>"+ a.data.eventList[i].startTime +"</li> " +
                    "<li><strong>Event End Time: </strong>"+ a.data.eventList[i].endTime +"</li> " +
                    "<li><strong>Vote Start Time: </strong>"+ a.data.eventList[i].voteStart +"</li> " +
                    "<li><strong>Vote End Time: </strong>"+ a.data.eventList[i].voteEnd +"</li> " +
                    "</ul>"


                result+="<hr>" +
                    "<a href=\"watchMovieListWatcher.html?eventName="+ a.data.eventList[i].name +"\">Movie Details</a>"+
                    "</div>"+
                    "</div>"
            }
            $("#test2").html(result);
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