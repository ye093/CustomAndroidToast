package com.yejy.liketoastapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yejy.liketoastapp.ui.CloneToast
import com.yejy.liketoastapp.ui.EnNaToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testBtn.setOnClickListener {
//            EnNaToast.makeText(this@MainActivity, "测试Toast弹框1", Toast.LENGTH_LONG).show()
            CloneToast.makeText(this@MainActivity, "测试Toast弹框2", Toast.LENGTH_LONG).show()
        }
    }
}
