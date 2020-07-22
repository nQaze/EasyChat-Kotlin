package com.nqaze.easychat_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cometchat.pro.models.Conversation
import com.cometchat.pro.models.User
import constant.StringContract
import listeners.OnItemClickListener
import screen.CometChatConversationListScreen

class ConversationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversations)

        CometChatConversationListScreen.setItemClickListener(object : OnItemClickListener<Conversation?>() {
            override fun OnItemClick(conversation: Conversation?, position: Int) {
                val user = User.fromJson(conversation?.conversationWith.toString())
                val intent = Intent(this@ConversationsActivity, ChatMessageActivity::class.java)
                intent.putExtra(StringContract.IntentStrings.UID, user.uid)
                intent.putExtra(StringContract.IntentStrings.NAME, user.name)
                intent.putExtra(StringContract.IntentStrings.AVATAR, user.avatar)
                startActivity(intent)
            }
        })
    }

    fun newChatTapped(view: View?) {
        startActivity(Intent(this@ConversationsActivity, ContactsActivity::class.java))
    }
}