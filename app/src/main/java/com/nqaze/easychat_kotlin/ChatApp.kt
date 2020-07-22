package com.nqaze.easychat_kotlin

import android.app.Application
import android.util.Log
import com.cometchat.pro.core.AppSettings
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException

class ChatApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val appSettings = AppSettings.AppSettingsBuilder().subscribePresenceForAllUsers().setRegion(AppConfig.AppDetails.REGION).build()

        CometChat.init(this, AppConfig.AppDetails.APP_ID, appSettings, object : CometChat.CallbackListener<String?>() {
            override fun onSuccess(s: String?) {
            }
            override fun onError(e: CometChatException) {
            }
        })
    }
}