/**
 * ChatJS 1.0 - MIT License
 * www.chatjs.net
 * 
 * Copyright (c) 2013, André Pena
 * All rights reserved.
 *
 **/



(function ($) {

    // adds a long-polling listener
    $.addLongPollingListener = function (providerName, success, error) {
       
        if (!$._longPollingData)
            $._longPollingData = {
                listeners: new Object(),
                started: false,
                lastFetchTimeStamp: 0
            };
        if ($._longPollingData.listeners[providerName])
            throw "Cannot add long polling listener. There's already a listener for the provider FullName. Provider FullName: " + providerName;
        $._longPollingData.listeners[providerName] = new Object();
        $._longPollingData.listeners[providerName].success = success;
        $._longPollingData.listeners[providerName].error = error;
    };

    // starts the long polling process
    $.startLongPolling = function (longPollingUrl) {
        
        if (!$._longPollingData)
            throw "Cannot start long polling. There's no registered listeners";
        if ($._longPollingData.started)
            throw "Cannot start long polling. It has already started";
        

        $._longPollingData.started = true;

        function doLongPollingRequest() 
        {
        	var userId = $("#EmailAddress").val();
            $.ajax({
                url: longPollingUrl,
                cache: false,
                data: {
                    timestamp: $._longPollingData.lastFetchTimeStamp,
                    userId : userId
                },
                success: function (data, s) 
                {
                    $._longPollingData.lastFetchTimeStamp = data.Timestamp;
                    
                    for (var i = 0; i < data.Events.length; i++)
                    {
                        var event = data.Events[i];
                        
                        if (!$._longPollingData.listeners[event.ProviderName])
                            throw "Long polling server sent a message but there is no client listener. Provider FullName: " + event.ProviderName;
                        else
                        {
                            var listener = $._longPollingData.listeners[event.ProviderName].success;
                            try 
                            {
                                listener(event);
                                
                            } catch (ex) 
                            {
                                throw "Long polling listener triggered an Exception: " + ex;
                            }
                        }
                    };

                    setTimeout(function () {
                        doLongPollingRequest();
                    }, 1000);
                },
                error: function (data) {
                    for (var providerName in $._longPollingData.listeners) {
                        if ($._longPollingData.listeners[providerName].error)
                            $._longPollingData.listeners[providerName].error.apply(this, arguments);
                    };

                    // an error ocurred but life must go on
                    setTimeout(function () {
                        doLongPollingRequest();
                    }, 1000);

                    if (console && console.log) {
                        console.log("error in the long-polling");
                        console.log(data);
                    }
                }
            });
        }

        doLongPollingRequest();
    };

})(jQuery);

// actual ChatJS long polling adapter
function LongPollingAdapter(options) {
    

    this.defaults = {
        sendMessageUrl		: getBaseURL()+"/ChatMessageServlet",
        sendTypingSignalUrl	: getBaseURL()+"/ChatMessageServlet",
        getMessageHistoryUrl: getBaseURL()+"/ChatMessageServlet",
        userInfoUrl			: getBaseURL()+"/ChatMessageServlet",
        usersListUrl		: getBaseURL()+"/ChatUserServlet"
    };

    //Extending options:
    this.opts = $.extend({}, this.defaults, options);
}

LongPollingAdapter.prototype = {
    init: function (chat, done) {
       
        var _this = this;

        $.addLongPollingListener("chat",
                // success
                function (event) 
                {
                    if (event.EventKey == "new-messages")
                    {
                    	 for (var i = 0; i < event.Data.length; i++)
                             chat.client.sendMessage(event.Data[i]);
                    	 
                    }
                       
                    else if (event.EventKey == "user_list")
                    {
                    	
                    	 chat.client.usersListChanged(event.Data);
                    }
                       
                    else if (event.EventKey == "user-typed")
                    {
                    	
                    	 chat.client.sendTypingSignal(event.Data);
                    }
                    else
                    {
                    	//alert("other "+event.EventKey);
                    }
                       
                },
                // error
                function (e) {
                    switch (e.status) {
                        case 403:
                            chat.client.showError("Your user is not logged in or not allowed to access the chat now");
                            break;
                        case 500:
                            chat.client.showError("An error ocurred while trying to load the chat");
                            break;
                        default:
                            // chances are that the user just clicked a link. When you click a link
                            // the pending ajaxes break
                    }
                }
            );

       
        _this.server = {
            sendMessage: function (otherUserId, messageText, clientGuid, done) {
                
            	
            	messageText = messageText.replace( /"/g,'\\"');
            	
            	var userId = $("#EmailAddress").val();
                $.ajax({
                    type: "POST",
                    url: _this.opts.sendMessageUrl,
                    data: {
                        otherUserId: otherUserId,
                        userId : userId,
                        message: messageText,
                        clientGuid: clientGuid
                    },
                    cache: false,
                    success: function (result) {
                        // fine
                        if (done)
                            done(result);
                    },
                    error: function (result) {
                        // too bad
                    }
                });
            },
            sendTypingSignal: function (otherUserId, done)
            {
               
            
              /*  $.ajax({
                    type: "POST",
                    async: false,
                    url: _this.opts.sendTypingSignalUrl,
                    data: {
                        otherUserId: otherUserId
                    },
                    cache: false,
                    success: function (data) {
                        // fine
                        if (done)
                            done(data.Messages);
                    },
                    error: function () {
                        // too bad
                    }
                });*/
            },
            getMessageHistory: function (otherUserId, done) {
                
            	
            /*	alert("get mesage history");
                $.ajax({
                    type: "POST",
                    async: false,
                    url: _this.opts.getMessageHistoryUrl,
                    data: {
                        otherUserId: otherUserId
                    },
                    cache: false,
                    success: function (data) {
                        // fine
                        if (done)
                            done(data.Messages);
                    },
                    error: function () {
                        // too bad
                    }
                });*/
            },
            getUserInfo: function (userId, done) {
                
            	
            /*	alert("get user info");
                $.ajax({
                    type: "POST",
                    
                    async: false,
                    url: _this.opts.userInfoUrl,
                    data: {
                        userId: userId
                    },
                    cache: false,
                    success: function (result) {
                        // fine
                        if (done)
                            done(result.User);
                    },
                    error: function () {
                        // too bad
                    }
                });*/
            },
            getUsersList: function (done) {
                
            	
            	var userId = $("#EmailAddress").val();
                $.ajax({
                    type: "POST",
                    async: false,
                    url: _this.opts.usersListUrl,
                    data: {
                        userId: userId
                    },
                    cache: false,
                    success: function (result) {
                        // fine
                        if (done)
                            done(result.Users);
                    },
                    error: function () {
                        // too bad
                    }
                });
            }
        };

        // starts to poll the server for events
        /////////////////////////////$.startLongPolling( getBaseURL()+"/ChatEventServlet" );

        // function passed by ChatJS to the adapter to be called when the adapter initialization is completed
        done();
    }
}
