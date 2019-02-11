<script type="text/javascript">

    function getAuthorInfo(authorName) {
        console.log("name to search:" + authorName);
        $.ajax({
            url: "userInfo",
            type: "POST",
            data: {username: authorName},
            success: function (msg) {

                console.log("a message arrived back from userInfo: " + JSON.stringify(msg));
                console.log("the real name:" + msg.realname);

                $('#modal-title').text("Username: " + msg.username);
                $('#modal-body').text("");

                $('#modal-body').append("<div id=\"image\" class=\"text-center\"><img src=\""+ msg.image + "\" width=\"250\" class=\"img-thumbnail\"></div>");

                if (msg.realname !== "" && msg.realname !== null){
                    $('#modal-body').append("Real Name: " + msg.realname + "<br>");
                }
                if (msg.dob != null) {
                    $('#modal-body').append("Date of Birth: " + msg.dob + "<br>");
                }
                if (msg.country !== "" && msg.country !== null) {
                    $('#modal-body').append("Country: " + msg.country + "<br>");
                }
                if (msg.bio !== "" && msg.bio !== null) {
                    $('#modal-body').append("Bio: " + msg.bio + "<br>");
                }


                $("#userInfoModal").modal('show');


            }
        })
    }
</script>

<div class="modal" id="userInfoModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title" id="modal-title">Modal Heading</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body" id="modal-body">

            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>