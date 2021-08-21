/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any. - - - - - - - - - - -
 * - - - - - - - - - - - - - - - - - - - - - - - - - -
 */
package tester.chat;

import java.util.ArrayList;
import java.util.List;

import core.chat.ChatEventData;
import core.chat.ChatMessageData;
import core.chat.ChatUserStatusData;
import core.regn.UserProfileKey;
import ctrl.chat.ChatEventController;
import ctrl.chat.ChatUserController;
import utils.ErrorMaster;

/**
 * File: ChatTest.java
 *
 * Created on 16-Oct-2013 3:57:46 PM
 */
public class ChatTest {

    private static ErrorMaster errorMaster_ = null;
    /*
     * Method : ChatTest.java() -- constructor
     * 
     * Input  : None
     * 
     * Return : None
     * 
     * Purpose:
     */

    public ChatTest() {

        if (errorMaster_ == null) {
            errorMaster_ = new ErrorMaster();
        }
    }

    /* Main test method for chat */
    public void execute() {
		//getUserListTest();

        getEventsTest();
    }

    /* Get all the connection of the user */
    public void getUserListTest() {
        ChatUserController controller = new ChatUserController();

        UserProfileKey userKey = new UserProfileKey();

        userKey.email_ = "smadmn1@gmail.com";

        List< ChatUserStatusData> userList = new ArrayList<ChatUserStatusData>();

        int result = controller.get(userKey, userList);

        if (result == 10600) {
            errorMaster_.insert("User Fetch test success");

            for (ChatUserStatusData chatUserStatusData : userList) {
                chatUserStatusData.show();
            }
        } else {
            errorMaster_.insert("User fetch test failed");
        }
    }

    /* Get all the events for the  user */
    public void getEventsTest() {
        ChatEventController controller = new ChatEventController();

        UserProfileKey userKey = new UserProfileKey();

        userKey.email_ = "smadmn1@gmail.com";

        ChatEventData chatEventData = new ChatEventData();

        int result = controller.get(userKey, chatEventData);

        if (result == 10630) {
            errorMaster_.insert("User Fetch test success");

            errorMaster_.insert("Chat user count=" + chatEventData.chatUsersStatus_.size());

            errorMaster_.insert("Chat message count=" + chatEventData.chatMessages_.size());

            for (ChatMessageData chatMessageData : chatEventData.chatMessages_) {
                chatMessageData.show();
            }
            for (ChatUserStatusData chatUserStatusData : chatEventData.chatUsersStatus_) {
                chatUserStatusData.show();
            }
        } else {
            errorMaster_.insert("User event test failed");
        }
    }

}
