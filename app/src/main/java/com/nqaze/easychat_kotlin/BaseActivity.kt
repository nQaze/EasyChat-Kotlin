package com.nqaze.easychat_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cometchat.pro.core.Call
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import constant.StringContract
import screen.CometChatCallActivity
import screen.CometChatCallActivity.mainView


open class BaseActivity : AppCompatActivity() {

    private val listenerID = "999"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CometChat.addCallListener(listenerID, object: CometChat.CallListener(){
            override fun onOutgoingCallAccepted(p0: Call?) {
                CometChat.startCall(this@BaseActivity, p0!!.sessionId, mainView, object :CometChat.OngoingCallListener{
                    override fun onUserJoined(p0: User?) {
                        Toast.makeText(this@BaseActivity, p0?.name + " joined", Toast.LENGTH_LONG).show()
                    }

                    override fun onUserLeft(p0: User?) {
                        Toast.makeText(this@BaseActivity, p0?.name + " left", Toast.LENGTH_LONG).show()
                    }

                    override fun onError(p0: CometChatException?) {
                        Toast.makeText(this@BaseActivity, p0?.localizedMessage, Toast.LENGTH_LONG).show()
                    }

                    override fun onCallEnded(p0: Call?) {
                        Toast.makeText(this@BaseActivity, "Call ended", Toast.LENGTH_LONG).show()
                        // TODO: Handle here
                    }
                })
            }

            override fun onIncomingCallReceived(p0: Call?) {
                val user = p0?.callInitiator as User
                val intent = Intent(this@BaseActivity, CometChatCallActivity::class.java)
                intent.putExtra(StringContract.IntentStrings.NAME, user.name)
                intent.putExtra(StringContract.IntentStrings.SESSION_ID, p0.sessionId)
                intent.type = StringContract.IntentStrings.INCOMING
                intent.action = p0.type
                startActivity(intent)
            }

            override fun onIncomingCallCancelled(p0: Call?) {
                Toast.makeText(this@BaseActivity, "Call cancelled", Toast.LENGTH_LONG).show()
            }

            override fun onOutgoingCallRejected(p0: Call?) {
                Toast.makeText(this@BaseActivity, "Call rejected", Toast.LENGTH_LONG).show()
                // TODO: Handle Here
            }
        })
    }

    override fun onDestroy() {
        CometChat.removeCallListener(listenerID)
        super.onDestroy()
    }
}