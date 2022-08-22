package com.example.mfirstapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.view.isVisible

import com.example.mfirstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    private var login: String = ""
    private var password: String = ""
    private var name1: String = ""
    private var name2: String = ""
    private var name3: String = ""
    private var avatarImageId: Int = 0

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constance.requestCode_sign_in) {
            val log = data?.getStringExtra(Constance.login)
            val pass = data?.getStringExtra(Constance.password)
            if (login == log && password == pass) {
                bindingClass.imAvatar.setImageResource(avatarImageId)
                val textInfo = "$name1 $name2 $name3"
                bindingClass.tvInfo.text = textInfo
                bindingClass.bHide.visibility = View.GONE
                bindingClass.bExit.text = "Выйти"
            } else {
                bindingClass.imAvatar.visibility = View.VISIBLE
                bindingClass.tvInfo.text = "Такого аккаутна не существует!"
                bindingClass.imAvatar.setImageResource(R.drawable.wrong_password)
            }
        } else if (resultCode == Constance.requestCode_sign_up) {
            login = data?.getStringExtra(Constance.login)!!
            password = data.getStringExtra(Constance.password)!!
            name1 = data.getStringExtra(Constance.name1)!!
            name2 = data.getStringExtra(Constance.name2)!!
            name3 = data.getStringExtra(Constance.name3)!!
            bindingClass.imAvatar.visibility = View.VISIBLE
            avatarImageId = data.getIntExtra(Constance.avatar_id, 0)
            bindingClass.imAvatar.setImageResource(avatarImageId)
            val textInfo = "$name1 $name2 $name3"
            bindingClass.tvInfo.text = textInfo
            bindingClass.bHide.visibility = View.GONE
            bindingClass.bExit.text = "Выйти"
        }
    }
    fun onClickSignIn(view: View) {
        if (bindingClass.imAvatar.isVisible
            && bindingClass.tvInfo.text.toString() != "Такого аккаунта не существует!"
        ) {
            bindingClass.imAvatar.visibility = View.INVISIBLE
            bindingClass.tvInfo.text = ""
            bindingClass.bHide.visibility = View.VISIBLE
            bindingClass.bExit.text = getString(R.string.sign_in)
        } else {
            val intent = Intent(this, SignUpInAct::class.java)
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
            startActivityForResult(intent, Constance.requestCode_sign_in)
        }
    }
        fun onClickSignUp(view: View) {
            val intent = Intent(this, SignUpInAct::class.java)
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
            startActivityForResult(intent, Constance.requestCode_sign_up)
        }
}






