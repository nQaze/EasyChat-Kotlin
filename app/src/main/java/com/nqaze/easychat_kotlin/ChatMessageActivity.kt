package com.nqaze.easychat_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cometchat.pro.constants.CometChatConstants
import constant.StringContract
import screen.messagelist.CometChatMessageScreen

class ChatMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_message)

        val intent = intent
        val uid = intent.getStringExtra(StringContract.IntentStrings.UID)
        val name = intent.getStringExtra(StringContract.IntentStrings.NAME)
        val avatar = intent.getStringExtra(StringContract.IntentStrings.AVATAR)

        val bundle = Bundle()
        bundle.putString(StringContract.IntentStrings.UID, uid)
        bundle.putString(StringContract.IntentStrings.NAME, name)
        bundle.putString(StringContract.IntentStrings.AVATAR, avatar)
        bundle.putString(StringContract.IntentStrings.TYPE, CometChatConstants.RECEIVER_TYPE_USER)

        val chatFragment: Fragment = CometChatMessageScreen()
        chatFragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.chatFrame, chatFragment).commit()
    }
}