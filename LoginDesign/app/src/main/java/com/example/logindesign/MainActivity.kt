package com.example.logindesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.password
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import kotlinx.android.synthetic.main.invalid_dialog.view.*
import kotlinx.android.synthetic.main.valid_dialog.view.*
import kotlinx.android.synthetic.main.valid_dialog.view.message
import kotlinx.android.synthetic.main.valid_dialog.view.ok_btn
import java.security.AccessController.getContext
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    lateinit var Username: String
    lateinit var Password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (username.text.toString().length >= 4 &&
                    username.text.toString().length <40) {
                    username.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.done_black_24dp, 0);
                } else {
                    username.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }

        })


        login.setOnClickListener {
            Username = username.text.toString()
            Password = password.text.toString()


            if (Username.equals("") || Password.equals("")  ) {
                show_Invalid_dialog()
            }
            else if(!isPasswordValid(Password)){
                show_warrining_dialog()
            }
            else if (Username.equals("Admin") && Password.equals("P@ssw0rd")) {
                show_valid_dialog()
            }
            else{
                Toast.makeText(applicationContext,"Username or Password are Incorrect....",Toast.LENGTH_SHORT).show()
            }
        }


    }

    fun show_valid_dialog() {

        val mDialogView = LayoutInflater.from(this).inflate(R.layout.valid_dialog, null)
        val mBuilder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .setView(mDialogView)
        //show dialog
        val mAlertDialog = mBuilder.show()
        mDialogView.message.text = "Your account  has been created and your password : " + Password
        mDialogView.ok_btn.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }

    fun show_Invalid_dialog() {

            val mDialogView = LayoutInflater.from(this).inflate(R.layout.invalid_dialog, null)
            val mBuilder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .setView(mDialogView)
            //show dialog
            val mAlertDialog = mBuilder.show()
            mDialogView.ok_btn.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }


    }

    fun show_warrining_dialog() {

        val mDialogView = LayoutInflater.from(this).inflate(R.layout.warrining_dialog, null)
        val mBuilder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .setView(mDialogView)
        //show dialog
        val mAlertDialog = mBuilder.show()
        mDialogView.ok_btn.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }


    fun isPasswordValid(password: String): Boolean {

       // ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).*[A-Za-z0-9]$
        val regExpn = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})"

       // val inputStr = password

       // val pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
        val pattern = Pattern.compile(regExpn);
        val matcher = pattern.matcher(password)

         if (matcher.matches()){
             return true
         } else {
             return false
         }
    }


}


