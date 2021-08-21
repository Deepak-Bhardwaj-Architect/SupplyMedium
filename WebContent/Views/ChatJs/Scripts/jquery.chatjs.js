/**
 * ChatJS 1.0 - MIT License
 * www.chatjs.net
 * 
 * Copyright (c) 2013, André Pena
 * All rights reserved.
 *
 **/

// CHAT CONTAINER
(function ($) {
    function ChatContainer(options) {
        

        this.defaults = {
            objectType: null,
            objectName: null,
            title: null,
            canClose: true,
            showTextBox: true,
            initialToggleState: "minimized",
            onCreated: function (chatContainer) { },
            onClose: function (chatContainer) { },
            // triggers when the window changes it's state: minimized or maximized
            onToggleStateChanged: function (currentState) { }
        };

        
        this.opts = $.extend({}, this.defaults, options);

        //Privates:
        this.$el = null;
        this.$window = null;
        this.$windowTitle = null;
        this.$windowContent = null;
        this.$windowInnerContent = null;
        this.$textBox = null;
    }

    // Separate functionality from object creation
    ChatContainer.prototype = {

        init: function () {
            var _this = this;

          
            _this.$window = $("<div/>").addClass("chat-window").appendTo($("body"));

            
            _this.$windowTitle = $("<div/>").addClass("chat-window-title").appendTo(_this.$window);
            if (_this.opts.canClose) {
                var $closeButton = $("<div/>").addClass("close").appendTo(_this.$windowTitle);
                $closeButton.click(function (e) {
                    e.stopPropagation();

                    // removes this item from the collection
                    for (var i = 0; i < $._chatContainers.length; i++) {
                        if ($._chatContainers[i] == _this) {
                            $._chatContainers.splice(i, 1);
                            break;
                        }
                    }

                    // removes the window
                    _this.$window.remove();

                    // triggers the event
                    _this.opts.onClose(_this);
                });

            }

            $("<div/>").addClass("text").text(_this.opts.title).appendTo(_this.$windowTitle);

            // content
            _this.$windowContent = $("<div/>").addClass("chat-window-content").appendTo(_this.$window);
            if (_this.opts.initialToggleState == "minimized")
                _this.$windowContent.hide();

            _this.$windowInnerContent = $("<div/>").addClass("chat-window-inner-content").appendTo(_this.$windowContent);

          
            if (_this.opts.showTextBox) {
                var $windowTextBoxWrapper = $("<div/>").addClass("chat-window-text-box-wrapper").appendTo(_this.$windowContent);
                _this.$textBox = $("<textarea autofocus='autofocus' />").attr({"rows": "1","maxlength":"1000"}).addClass("chat-window-text-box").appendTo($windowTextBoxWrapper);
                _this.$textBox.autosize();
            }

            
            _this.$windowTitle.click(function () {
                _this.$windowContent.toggle();
                if (_this.$windowContent.is(":visible") && _this.opts.showTextBox)
                    _this.$textBox.focus();
                _this.opts.onToggleStateChanged(_this.$windowContent.is(":visible") ? "maximized" : "minimized");
            });

            
            if (!$._chatContainers)
                $._chatContainers = new Array();
            $._chatContainers.push(_this);

            $.organizeChatContainers();

            _this.opts.onCreated(_this);
        },

        	getContent: function () {
            
            var _this = this;
            return _this.$windowInnerContent;
        },

        setTitle: function (title) {
            var _this = this;
            $("div[class=text]", _this.$windowTitle).text(title);
        },

        setVisible: function (visible) {
           
            var _this = this;
            if (visible)
                _this.$window.show();
            else
                _this.$window.hide();
        },

        getToggleState: function () {
            var _this = this;
            return _this.$windowContent.is(":visible") ? "maximized" : "minimized";
        },

        setToggleState: function (state) {
            var _this = this;
            if (state == "minimized")
                _this.$windowContent.hide();
            else if (state == "maximized")
                _this.$windowContent.show();
        }
    };

   
    $.chatContainer = function (options) {
        var chatContainer = new ChatContainer(options);
        chatContainer.init();

        return chatContainer;
    };

    $.organizeChatContainers = function () {
        // this is the initial right offset
        var rightOffset = 10;
        var deltaOffset = 10;
        for (var i = 0; i < $._chatContainers.length; i++) {
            $._chatContainers[i].$window.css("right", rightOffset);
            rightOffset += $._chatContainers[i].$window.outerWidth() + deltaOffset;
        }
    };

})(jQuery);


// CHAT WINDOW
(function ($) {

    function ChatWindow(options) {
        
        this.defaults = {
            chat: null,
            myUser: null,
            otherUser: null,
            typingText: null,
            initialToggleState: "minimized",
            initialFocusState: "focused",
            userIsOnline: false,
            adapter: null,
            onReady: function () { },
            onClose: function (container) { },
            // triggers when the window changes it's state: minimized or maximized
            onToggleStateChanged: function (currentState) { }
        };

        //Extending options:
        this.opts = $.extend({}, this.defaults, options);

        //Privates:
        this.$el = null;
        this.chatContainer = null;

        this.addMessage = function (message, clientGuid) {
            var _this = this;
            _this.chatContainer.setToggleState("maximized");

            if (message.UserFromId != this.opts.myUser.Id) {
                
                _this.removeTypingSignal();
            }

            
            function linkify($element) {
                var inputText = $element.html();
                var replacedText, replacePattern1, replacePattern2, replacePattern3;

                
                replacePattern1 = /(\b(https?|ftp):\/\/[-A-Z0-9+&@#\/%?=~_|!:,.;]*[-A-Z0-9+&@#\/%=~_|])/gim;
                replacedText = inputText.replace(replacePattern1, '<a href="$1" target="_blank">$1</a>');

                
                replacePattern2 = /(^|[^\/])(www\.[\S]+(\b|$))/gim;
                replacedText = replacedText.replace(replacePattern2, '$1<a href="http://$2" target="_blank">$2</a>');

                
                replacePattern3 = /(\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6})/gim;
                replacedText = replacedText.replace(replacePattern3, '<a href="mailto:$1">$1</a>');

                return $element.html(replacedText);
            }

            function emotify($element) {
                var inputText = $element.html();
                var replacedText = inputText;

                var emoticons = [
                    { pattern: ":-\)", cssClass: "happy" },
                    { pattern: ":\)", cssClass: "happy" },
                    { pattern: "=\)", cssClass: "happy" },
                    { pattern: ":-D", cssClass: "very-happy" },
                    { pattern: ":D", cssClass: "very-happy" },
                    { pattern: "=D", cssClass: "very-happy" },
                    { pattern: ":-\(", cssClass: "sad" },
                    { pattern: ":\(", cssClass: "sad" },
                    { pattern: "=\(", cssClass: "sad" },
                    { pattern: ":-\|", cssClass: "wary" },
                    { pattern: ":\|", cssClass: "wary" },
                    { pattern: "=\|", cssClass: "wary" },
                    { pattern: ":-O", cssClass: "astonished" },
                    { pattern: ":O", cssClass: "astonished" },
                    { pattern: "=O", cssClass: "astonished" },
                    { pattern: ":-P", cssClass: "tongue" },
                    { pattern: ":P", cssClass: "tongue" },
                    { pattern: "=P", cssClass: "tongue" }
                ];

                for (var i = 0; i < emoticons.length; i++) {
                    replacedText = replacedText.replace(emoticons[i].pattern, "<span class='" + emoticons[i].cssClass + "'></span>");
                }

                return $element.html(replacedText);
            }

            if (message.ClientGuid && $("p[data-val-client-guid='" + message.ClientGuid + "']").length) 
            {
                 //This message is comming from the server AND the current user POSTED the message.
                // so he/she already has this message in the list. We DO NOT need to add the message.
                $("p[data-val-client-guid='" + message.ClientGuid + "']").removeClass("temp-message").removeAttr("data-val-client-guid");
            } 
            else 
            {
            	var textMessage =  message.Message.replace( /\\"/g,'"');
            	
                var $messageP = $("<p/>").text(textMessage);
                
                if (clientGuid)
                    $messageP.attr("data-val-client-guid", clientGuid).addClass("temp-message");

                linkify($messageP);
                emotify($messageP);

                // gets the last message to see if it's possible to just append the text
                var $lastMessage = $("div.chat-message:last", _this.chatContainer.$windowInnerContent);
                if ($lastMessage.length && $lastMessage.attr("data-val-user-from") == message.UserFromId) 
                {
                  
                    $messageP.appendTo($(".chat-text-wrapper", $lastMessage));
                }
                else 
                {
                    // in this case we need to create a whole new message
                    var $chatMessage = $("<div/>").addClass("chat-message").attr("data-val-user-from", message.UserFromId);
                    $chatMessage.appendTo(_this.chatContainer.$windowInnerContent);

                    var $gravatarWrapper = $("<div/>").addClass("chat-gravatar-wrapper profile_picture_container").appendTo($chatMessage);
                    
                    var $textWrapper = $("<div/>").addClass("chat-text-wrapper").appendTo($chatMessage);

                   
                    $messageP.appendTo($textWrapper);

                    // add image
                    var messageUserFrom = _this.opts.chat.usersById[message.UserFromId];
                    
                    
                    $("<img/>")
                    .addClass( ( messageUserFrom.ProfilePictureUrl == null || messageUserFrom.ProfilePictureUrl == 'null') ? "no-profile-picture profile_image" : "profile-picture profile_image")
                    .attr("src",( messageUserFrom.ProfilePictureUrl == null || messageUserFrom.ProfilePictureUrl == 'null') ? "" :messageUserFrom.ProfilePictureUrl)
                    .appendTo($gravatarWrapper);
                    
                    
                   /* $("<img/>").attr("src", decodeURI(messageUserFrom.ProfilePictureUrl))
                    .addClass("profile_image")
                    .appendTo($gravatarWrapper);*/
                }

                // scroll to the bottom
                _this.chatContainer.$windowInnerContent.scrollTop(_this.chatContainer.$windowInnerContent[0].scrollHeight);
            }
        };

        this.sendMessage = function (messageText) 
        {
            var _this = this;

            var generateGuidPart = function () {
                return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
            };

            var clientGuid = (generateGuidPart() + generateGuidPart() + '-' + generateGuidPart() + '-' + generateGuidPart() + '-' + generateGuidPart() + '-' + generateGuidPart() + generateGuidPart() + generateGuidPart());
            _this.addMessage({
                UserFromId: _this.opts.myUser.Id,
                Message: messageText
            }, clientGuid);

            _this.opts.adapter.server.sendMessage(_this.opts.otherUser.Id, messageText, clientGuid);
        };

        this.sendTypingSignal = function () {
            
            var _this = this;
            _this.opts.adapter.server.sendTypingSignal(_this.opts.otherUser.Id);
        };

        this.getToggleState = function () {
            var _this = this;
            return _this.chatContainer.getToggleState();
        };

        this.setVisible = function (value) {
            var _this = this;
            _this.chatContainer.setVisible(value);
        };
    }

    // Separate functionality from object creation
    ChatWindow.prototype = {

        init: function () {
            var _this = this;

            _this.chatContainer = $.chatContainer({
                title: _this.opts.userToName,
                canClose: true,
                initialToggleState: _this.opts.initialToggleState,
                onClose: function (e) {
                    _this.opts.onClose(e);
                },
                onToggleStateChanged: function (toggleState) {
                    _this.opts.onToggleStateChanged(toggleState);
                }
            });

            _this.chatContainer.$textBox.keypress(function (e) {
                
                if (_this.$sendTypingSignalTimeout == undefined) {
                    _this.$sendTypingSignalTimeout = setTimeout(function () {
                        _this.$sendTypingSignalTimeout = undefined;
                    }, 3000);
                    _this.sendTypingSignal();
                }

                if (e.which == 13) {
                    e.preventDefault();
                    if ($(this).val()) {
                        _this.sendMessage($(this).val());
                        $(this).val('').trigger("autosize.resize");
                    }
                }
            });

            _this.chatContainer.setTitle(_this.opts.otherUser.Name);

            _this.opts.adapter.server.getMessageHistory(_this.opts.otherUser.Id, function (messageHistory) {
                for (var i = 0; i < messageHistory.length; i++)
                    _this.addMessage(messageHistory[i]);

                _this.chatContainer.setVisible(true);

                if (_this.opts.initialFocusState == "focused")
                    _this.chatContainer.$textBox.focus();

                // scroll to the bottom
                _this.chatContainer.$windowInnerContent.scrollTop(_this.chatContainer.$windowInnerContent[0].scrollHeight);

                if (_this.opts.onReady)
                    _this.opts.onReady(_this);
            });

            _this.setOnlineStatus(_this.opts.userIsOnline);
        },

        focus: function () {
            var _this = this;
            _this.chatContainer.$textBox.focus();
        },

        showTypingSignal: function (user) {
          
            var _this = this;
            if (_this.$typingSignal)
                _this.$typingSignal.remove();
            _this.$typingSignal = $("<p/>").addClass("typing-signal").text(user.Name + _this.opts.typingText);
            _this.chatContainer.$windowInnerContent.after(_this.$typingSignal);
            if (_this.typingSignalTimeout)
                clearTimeout(_this.typingSignalTimeout);
            _this.typingSignalTimeout = setTimeout(function () {
                _this.removeTypingSignal();
            }, 5000);
        },

        removeTypingSignal: function () {
            
            var _this = this;
            if (_this.$typingSignal)
                _this.$typingSignal.remove();
            if (_this.typingSignalTimeout)
                clearTimeout(_this.typingSignalTimeout);
        },

        setOnlineStatus: function (userIsOnline) {
            var _this = this;
            if (userIsOnline) {
                _this.chatContainer.$windowTitle.addClass("online");
                _this.chatContainer.$windowTitle.removeClass("offline");
            } else {
                _this.chatContainer.$windowTitle.removeClass("online");
                _this.chatContainer.$windowTitle.addClass("offline");
            }
        }
    };

    // The actual plugin
    $.chatWindow = function (options) {
        var chatWindow = new ChatWindow(options);
        chatWindow.init();

        return chatWindow;
    };
})(jQuery);

// CHAT
(function ($) {

    // creates a chat user-list
    function Chat(options) {
        var _this = this;

        
        _this.defaults = {
            user: null,
            adapter: null,
            titleText: 'Chat',
            emptyRoomText: "There's no other users",
            typingText: " is typing...",
            useActivityIndicatorPlugin: true,
            playSound: true
        };

        
        _this.opts = $.extend({}, _this.defaults, options);

        //Privates:
        _this.$el = null;

        
        _this.chatWindows = new Object();
        _this.lastMessageCheckTimeStamp = null;
        _this.chatContainer = null;
        _this.usersById = {};
    }

    Chat.prototype = {

        init: function () {
            var _this = this;

            var mainChatWindowChatState = _this.readCookie("main_window_chat_state");
           // if (mainChatWindowChatState)
                mainChatWindowChatState = "minimized";

            // will create user list chat container
            _this.chatContainer = $.chatContainer({
                title: _this.opts.titleText,
                showTextBox: false,
                canClose: false,
                initialToggleState: mainChatWindowChatState,
                onCreated: function (container) {
                    if (!container.$windowInnerContent.html()) {
                        var $loadingBox = $("<div/>").addClass("loading-box").appendTo(container.$windowInnerContent);
                        if (_this.opts.useActivityIndicatorPlugin)
                            $loadingBox.activity({ segments: 8, width: 3, space: 0, length: 3, color: '#666666', speed: 1.5 });
                    }
                },
                onToggleStateChanged: function (toggleState) {
                    _this.createCookie("main_window_chat_state", toggleState);
                }
            });

            
            _this.client = {
                sendMessage: function (message) 
                {
                	
                	
                    
                    if (message.UserFromId != _this.opts.user.Id) {
                        
                        if (!_this.chatWindows[message.UserFromId])
                        {
                        	_this.createNewChatWindow(message.UserFromId);
                        	
                            _this.chatWindows[message.UserFromId].addMessage(message);
                        }
                        	
                        else
                        {
                        	 _this.chatWindows[message.UserFromId].addMessage(message);
                        }
                           
                        if (_this.opts.playSound)
                            _this.playSound(getBaseURL()+"/Views/ChatJs/Sounds/chat");

                        // play sound here
                    } else {
                        if (_this.chatWindows[message.UserToId]) {
                            _this.chatWindows[message.UserToId].addMessage(message);
                        }
                    }
                },

                sendTypingSignal: function (otherUserId) {
                                  if (_this.chatWindows[otherUserId]) {
                        var otherUser = _this.usersById[otherUserId];
                        _this.chatWindows[otherUserId].showTypingSignal(otherUser);
                    }
                },

                usersListChanged: function (usersList) {
                   
                    _this.usersById = {};
                    _this.usersById[_this.opts.user.Id] = _this.opts.user;

                    _this.chatContainer.getContent().html('');
                    if (usersList.length == 0) {
                        $("<div/>").addClass("user-list-empty").text(_this.opts.emptyRoomText).appendTo(_this.chatContainer.getContent());
                    }
                    else {
                        for (var i = 0; i < usersList.length; i++)
                        {
                            if (usersList[i].Id != _this.opts.user.Id)
                            {
                                _this.usersById[usersList[i].Id] = usersList[i];
                                
                                
                                var $userListItem = $("<div/>")
                                    .addClass("user-list-item")
                                    .attr("data-val-id", usersList[i].Id)
                                    .appendTo(_this.chatContainer.getContent());
                                
                                //alert(usersList[i].ProfilePictureUrl);
                                
                                var $imageContainer = $("<div/>")
                                .addClass("profile_picture_container")
                                .appendTo($userListItem);
                            
                              
                                $("<img/>")
                                    .addClass( ( usersList[i].ProfilePictureUrl == null || usersList[i].ProfilePictureUrl == 'null') ? "no-profile-picture profile_image" : "profile-picture profile_image")
                                    .attr("src", usersList[i].ProfilePictureUrl)
                                    .appendTo($imageContainer);

                                $("<div/>")
                                    .addClass("profile-status")
                                    .addClass(usersList[i].Status == 0 ? "offline" : "online")
                                    .appendTo($userListItem);

                                $("<div/>")
                                    .addClass("content")
                                    .text(usersList[i].Name)
                                    .appendTo($userListItem);
                                

                                
                                (function (otherUserId) {
                                    
                                    $userListItem.click(function () {
                                        if (_this.chatWindows[otherUserId]) {
                                            _this.chatWindows[otherUserId].focus();
                                        } else
                                            _this.createNewChatWindow(otherUserId);
                                    });
                                })(usersList[i].Id);
                            }
                        }
                    }

                    // update the online status of the remaining windows
                    for (var i in _this.chatWindows) {
                        if (_this.usersById && _this.usersById[i])
                            _this.chatWindows[i].setOnlineStatus(_this.usersById[i].Status == 1);
                        else
                            _this.chatWindows[i].setOnlineStatus(false);
                    }

                    _this.chatContainer.setVisible(true);
                },

                showError: function (errorMessage) {
                    // todo
                }
            };

            _this.opts.adapter.init(_this, function () {
               
                _this.opts.adapter.server.getUsersList(function (usersList) {
                    _this.client.usersListChanged(usersList);
                    _this.loadWindows();
                });
            });
        },

        playSound: function (filename) {
            
            var $soundContainer = $("#soundContainer");
            if (!$soundContainer.length)
                $soundContainer = $("<div>").attr("id", "soundContainer").appendTo($("body"));
            $soundContainer.html('<audio autoplay="autoplay"><source src="' + filename + '.mp3" type="audio/mpeg" /><source src="' + filename + '.ogg" type="audio/ogg" /><embed hidden="true" autostart="true" loop="false" src="' + filename + '.mp3" /></audio>');
        },

        loadWindows: function () {
            var _this = this;
            var cookie = _this.readCookie("chat_state");
            if (cookie) {
                var openedChatWindows = JSON.parse(cookie);
                for (var i = 0; i < openedChatWindows.length; i++) {
                    var otherUserId = openedChatWindows[i].userId;
                    _this.opts.adapter.server.getUserInfo(otherUserId, function (user) {
                        if (user) {
                            if (!_this.chatWindows[otherUserId])
                                _this.createNewChatWindow(otherUserId, null, "blured");
                        } else {
                            
                            _this.eraseCookie("chat_state");
                        }
                    });
                }
            }
        },

        saveWindows: function () {
            var _this = this;
            var openedChatWindows = new Array();
            for (var otherUserId in _this.chatWindows) {
                openedChatWindows.push({
                    userId: otherUserId,
                    toggleState: _this.chatWindows[otherUserId].getToggleState()
                });
            }
            _this.createCookie("chat_state", JSON.stringify(openedChatWindows), 365);
        },

        createNewChatWindow: function (otherUserId, initialToggleState, initialFocusState) {

            if (!initialToggleState)
                initialToggleState = "maximized";

            if (!initialFocusState)
                initialFocusState = "focused";

            var _this = this;

            var otherUser = _this.usersById[otherUserId];
            if (!otherUser)
                throw "Cannot find the other user in the list";

            // if this particular chat-window does not exist yet, create it
            var newChatWindow = $.chatWindow({
                chat: _this,
                myUser: _this.opts.user,
                otherUser: otherUser,
                newMessageUrl: _this.opts.newMessageUrl,
                messageHistoryUrl: _this.opts.messageHistoryUrl,
                initialToggleState: initialToggleState,
                initialFocusState: initialFocusState,
                userIsOnline: otherUser.Status == 1,
                adapter: _this.opts.adapter,
                typingText: _this.opts.typingText,
                onClose: function () {
                    delete _this.chatWindows[otherUser.Id];
                    $.organizeChatContainers();
                    _this.saveWindows();
                },
                onToggleStateChanged: function (toggleState) {
                    _this.saveWindows();
                }
            });

            
            _this.chatWindows[otherUser.Id.toString()] = newChatWindow;
            _this.saveWindows();
        },

        eraseCookie: function (name) {
            var _this = this;
            _this.createCookie(name, "", -1);
        },

        readCookie: function (name) {
            var nameEq = name + "=";
            var ca = document.cookie.split(';');
            for (var i = 0; i < ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0) == ' ') c = c.substring(1, c.length);
                if (c.indexOf(nameEq) == 0) return c.substring(nameEq.length, c.length);
            }
            return null;
        },

        createCookie: function (name, value, days) {
            var expires;
            if (days) {
                var date = new Date();
                date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
                expires = "; expires=" + date.toGMTString();
            } else {
                expires = "";
            }
            document.cookie = name + "=" + value + expires + "; path=/";
        }
    };

    // The actual plugin
    $.chat = function (options) {
        var chat = new Chat(options);
        chat.init();
        return chat;
    };
})(jQuery);

// creates a chat user-list

