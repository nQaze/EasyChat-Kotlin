package com.nqaze.easychat_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginBtn?.setOnClickListener {
            signInTapped()
        }
        signUpBtn?.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
        }
    }

    private fun signInTapped() {
        val user = User()
        user.uid = etMobile.text.toString()
        login(user)
    }

    private fun login(user: User) {
        progressBar.visibility = View.VISIBLE
        CometChat.login(user.uid, AppConfig.AppDetails.API_KEY, object : CometChat.CallbackListener<User?>() {
                override fun onSuccess(user: User?) {
                    progressBar.visibility = View.GONE
                    startActivity(Intent(this@LoginActivity, ConversationsActivity::class.java))
                }
                override fun onError(e: CometChatException) {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@LoginActivity, e.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
    }
}