    <DIV id="invite" class="invite">
        <DIV id="show_btn" class="show_btn">
            <IMG id="show_invite_btn" class="show_invite_btn" 
                 src="inside/add_image_blue.png">
        </DIV>
        <DIV>
            <LABEL id="invite_label" class="invite_label"> Invite people whom you 
                know</LABEL>
        </DIV>
        <BR>
        <DIV class="invite_send">
            <INPUT id="text" class="text" type="text" autocomplete="off" placeholder="Enter email address(es)">
            <input type="hidden" id="userKey" name="userKey" value="<%=session.getAttribute("userKey")%>" />
            <INPUT id="invite_btn" class="invite_btn" value="Send Invite" type="button">
        </DIV>
        <!-- <img src="add_image_blue.png" class="img_btn" id="img_btn" > -->
    </DIV>
            
    <div id="chat_boxes">
        <div class="chat-window" style="right:0;">
            <div class="chat-window-title" onclick="$('#chat_list').toggle();">
                <div class="text">Chat</div>
            </div>
            <div class="chat-window-content" id="chat_list" style="display: none;">

            </div>
        </div>  
        <input type="hidden" id="chat_to_user_key" value="0">
    </div>
    </div><!-- screen lock div started in header -->

    <!--<div id="ui-datepicker-div" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all">
    </div>-->
