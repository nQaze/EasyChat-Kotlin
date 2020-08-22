package com.nqaze.easychat_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cometchat.pro.constants.CometChatConstants
import com.cometchat.pro.models.Conversation
import com.cometchat.pro.models.User
import constant.StringContract
import listeners.OnItemClickListener
import screen.CometChatConversationListScreen
import screen.messagelist.CometChatMessageListActivity

class ConversationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversations)

        CometChatConversationListScreen.setItemClickListener(object : OnItemClickListener<Conversation?>() {
            override fun OnItemClick(conversation: Conversation?, position: Int) {
                val user = conversation?.conversationWith as User
                val intent = Intent(this@ConversationsActivity, CometChatMessageListActivity::class.java)
                intent.putExtra(StringContract.IntentStrings.UID, user.uid)
                intent.putExtra(StringContract.IntentStrings.NAME, user.name)
                intent.putExtra(StringContract.IntentStrings.AVATAR, user.avatar)
                intent.putExtra(StringContract.IntentStrings.TYPE, CometChatConstants.RECEIVER_TYPE_USER);
                startActivity(intent)
            }
        })
    }

    fun newChatTapped(view: View) {
        startActivity(Intent(this@ConversationsActivity, ContactsActivity::class.java))
    }
}