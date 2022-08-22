package com.example.mfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.mfirstapp.databinding.ActivitySigninupBinding

class SignUpInAct : AppCompatActivity() {
    lateinit var bindingclass: ActivitySigninupBinding
    private var sign_state = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingclass = ActivitySigninupBinding.inflate(layoutInflater)
        setContentView(bindingclass.root)

        sign_state = intent.getStringExtra(Constance.SIGN_STATE)!!
        if (sign_state == Constance.SIGN_IN_STATE) {
            bindingclass.edName1.visibility = View.GONE
            bindingclass.edName2.visibility = View.GONE
            bindingclass.edName3.visibility = View.GONE
            bindingclass.bAvatar.visibility = View.INVISIBLE
        }

        }
    fun onClickDone(view: View) {
        if (sign_state == Constance.SIGN_UP_STATE) {
            val intent = Intent()
            intent.putExtra(Constance.login, bindingclass.edlogin.text.toString())
            intent.putExtra(Constance.password, bindingclass.edpassword.text.toString())
            intent.putExtra(Constance.name1, bindingclass.edName1.text.toString())
            intent.putExtra(Constance.name2, bindingclass.edName2.text.toString())
            intent.putExtra(Constance.name3, bindingclass.edName3.text.toString())
            if(bindingclass.imAvatar.isVisible) intent.putExtra(Constance.avatar_id, R.drawable.petyshara)
            setResult(RESULT_OK,intent)
            finish()
        } else if (sign_state == Constance.SIGN_IN_STATE){
            intent.putExtra(Constance.login, bindingclass.edlogin.text.toString())
            intent.putExtra(Constance.password, bindingclass.edpassword.text.toString())
            setResult(RESULT_OK,intent)
            finish()
        }
    }
    fun onClickAvatar(view: View) {
        bindingclass.imAvatar.visibility = View.VISIBLE
    }
}