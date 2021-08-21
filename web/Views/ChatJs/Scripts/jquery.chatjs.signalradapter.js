/**
 * ChatJS 1.0 - MIT License
 * www.chatjs.net
 * 
 * Copyright (c) 2013, André Pena
 * All rights reserved.
 *
 **/



function SignalRAdapter() {
   
}

SignalRAdapter.prototype = {
    init: function (chat, done) {
        
        var _this = this;


        
        _this.hub = $.connection.chatHub;
        _this.hub.client.sendMessage = function (message) {
            chat.client.sendMessage(message);
        };

        _this.hub.client.sendTypingSignal = function (otherUserId) {
            chat.client.sendTypingSignal(otherUserId);
        };

        _this.hub.client.usersListChanged = function (usersList) {
            chat.client.usersListChanged(usersList);
        };

        if (!window.hubReady)
            window.hubReady = $.connection.hub.start();

        window.hubReady.done(function () {
            // function passed by ChatJS to the adapter to be called when the adapter initialization is completed
            done();
        });

        
        _this.server = new Object();

        _this.server.sendMessage = function (otherUserId, messageText, clientGuid, done) {
            
            _this.hub.server.sendMessage(otherUserId, messageText, clientGuid).done(function (result) {
                if (done)
                    done(result);
            });
        };

        _this.server.sendTypingSignal = function (otherUserId, done) {
            
            _this.hub.server.sendTypingSignal(otherUserId).done(function (result) {
                if (done)
                    done(result);
            });
        };

        _this.server.getMessageHistory = function (otherUserId, done) {
            
            _this.hub.server.getMessageHistory(otherUserId).done(function (result) {
                if (done)
                    done(result);
            });
        };

        _this.server.getUserInfo = function (otherUserId, done) {
            
            _this.hub.server.getUserInfo(otherUserId).done(function (result) {
                if (done)
                    done(result);
            });
        };
        
        _this.server.getUsersList = function (done) {
           
            _this.hub.server.getUsersList().done(function (result) {
                if (done)
                    done(result);
            });
        };
    }
}