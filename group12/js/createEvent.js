$(document).ready(function() {
    console.log($.query.get("moderatorAccount"));
    console.log($.query.get("groupName"));
    $('#createEventNext').click(function(){
        var moderatorAccount = $.query.get("moderatorAccount");
        var groupName = $.query.get("groupName");
        var eventName = $("#eventName").val();
        window.location.href = "setStartTime.html?moderatorAccount="+moderatorAccount+"&groupName="+groupName+"&eventName="+eventName;
    });
});