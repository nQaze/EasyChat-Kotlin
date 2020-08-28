package com.nqaze.easychat_kotlin

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.cometchat.pro.core.AppSettings
import com.cometchat.pro.core.Call
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import constant.StringContract
import listeners.CometChatCallListener
import screen.CometChatCallActivity


class ChatApp : Application() {

    private val listenerID: String = "999"

    override fun onCreate() {
        super.onCreate()

        val appSettings = AppSettings.AppSettingsBuilder().subscribePresenceForAllUsers().setRegion(AppConfig.AppDetails.REGION).build()

        CometChat.init(this, AppConfig.AppDetails.APP_ID, appSettings, object : CometChat.CallbackListener<String?>() {
            override fun onSuccess(s: String?) {
            }
            override fun onError(e: CometChatException) {
            }
        })

        CometChatCallListener.addCallListener(listenerID,this);
    }

    override fun onTerminate() {
        CometChatCallListener.removeCallListener(listenerID)
        super.onTerminate()
    }
}