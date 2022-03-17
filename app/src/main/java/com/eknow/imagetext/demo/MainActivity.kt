package com.eknow.imagetext.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.eknow.imagetext.ImageTextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var itvTest: ImageTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itvTest = findViewById(R.id.itv_test)
        itvTest?.setOnClickListener(this)

        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.itv_test -> {
                Toast.makeText(this, "我被点击了", Toast.LENGTH_SHORT).show()
            }
            R.id.btn1 -> {
                itvTest?.isSelected = true
            }
            R.id.btn2 -> {
                itvTest?.isSelected = false
            }
            R.id.btn3 -> {
                itvTest?.isEnabled = true
            }
            R.id.btn4 -> {
                itvTest?.isEnabled = false
            }
            R.id.btn5 -> {
                itvTest?.setEnabledNoMask(false)
            }
            R.id.btn6 -> {
                itvTest?.setMaskEnable(false)
            }
        }
    }
}