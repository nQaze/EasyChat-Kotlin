package com.nqaze.easychat_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import kotlinx.android.synthetic.main.activity_register.*

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        createUserBtn?.setOnClickListener {
            signUpTapped()
        }
    }

    private fun signUpTapped() {
        val user = User()
        user.uid = etMobile.text.toString()
        user.name = etName.text.toString()
        registerUser(user)
    }

    private fun registerUser(user: User) {
        progressBar.visibility = View.VISIBLE
        CometChat.createUser(user, AppConfig.AppDetails.API_KEY, object : CometChat.CallbackListener<User>() {
                override fun onSuccess(user: User) {
                    progressBar.visibility = View.GONE
                    login(user)
                }
                override fun onError(e: CometChatException) {
                    progressBar.visibility = View.GONE
                    createUserBtn.isClickable = true
                    Toast.makeText(this@RegistrationActivity, e.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun login(user: User) {
        progressBar.visibility = View.VISIBLE
        CometChat.login(user.uid, AppConfig.AppDetails.API_KEY, object : CometChat.CallbackListener<User?>() {
                override fun onSuccess(user: User?) {
                    progressBar.visibility = View.GONE
                    startActivity(Intent(this@RegistrationActivity, CallHistoryActivity::class.java))
                }
                override fun onError(e: CometChatException) {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@RegistrationActivity, e.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
    }
}