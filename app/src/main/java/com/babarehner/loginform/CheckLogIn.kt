package com.babarehner.loginform

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

val sign_in_name = "mike"
val pass_word = "pass"

var u = ""

class CheckLogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //get the passed values from the intent
        val user = getIntent().getStringExtra(USER_NAME)
        val pass = getIntent().getStringExtra(PASS_WORD)


//        Log.d(TAG, "user: " + user)
//        // user seems to be coming in as null
//        Log.d(TAG, "password: " + pass)


        if (user == sign_in_name && pass == pass_word)  {
            setValidation(true)
        }
        else {
            setValidation(false)
        }

    }

    private fun setValidation(validated: Boolean){
        Intent().let { validatedIntent ->
            validatedIntent.putExtra(USER_VALIDATED, validated)
            setResult(Activity.RESULT_OK, validatedIntent )
            finish()
        }
    }

    companion object {
        private const val TAG = "CheckLogin"
    }
}