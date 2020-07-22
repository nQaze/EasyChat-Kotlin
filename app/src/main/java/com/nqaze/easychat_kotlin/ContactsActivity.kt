package com.nqaze.easychat_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cometchat.pro.models.User
import constant.StringContract
import listeners.OnItemClickListener
import screen.CometChatUserListScreen

class ContactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        CometChatUserListScreen.setItemClickListener(object : OnItemClickListener<User?>() {
            override fun OnItemClick(user: User?, position: Int) {
                val intent = Intent(this@ContactsActivity, ChatMessageActivity::class.java)
                intent.putExtra(StringContract.IntentStrings.UID, user?.uid)
                intent.putExtra(StringContract.IntentStrings.NAME, user?.name)
                intent.putExtra(StringContract.IntentStrings.AVATAR, user?.avatar)
                startActivity(intent)
            }
        })
    }
}