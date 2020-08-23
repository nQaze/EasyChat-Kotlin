package com.nqaze.easychat_kotlin

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class CallActivity : AppCompatActivity() {

    var callView: RelativeLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_history)
        callView = findViewById(R.id.callView)
    }
}