package com.babarehner.loginform

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

const val VALIDATE_USER_INTENT = 1 //request code
const val USER_VALIDATED = "USER_VALIDATED"
const val USER_NAME = "USER_NAME"
const val PASS_WORD = "PASS_WORD"
const val TorF = false

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        findViewById<Button>(R.id.login_button).setOnClickListener{
            val userName = findViewById<EditText>(R.id.user_name).text.toString().trim()
            val passWord = findViewById<EditText>(R.id.pass_word).text.toString().trim()
            Intent(this, CheckLogIn::class.java)
                    .also { logInCheckerIntent ->
                            // add extras to startActivity intent
                            logInCheckerIntent.putExtra(USER_NAME, userName).putExtra(PASS_WORD,
                                    passWord)
                        startActivityForResult(logInCheckerIntent, VALIDATE_USER_INTENT)
                    }
        }

//        // Same as above- format below seems simpler
//        findViewById<Button>(R.id.login_button).setOnClickListener{
//            val userName = findViewById<EditText>(R.id.user_name).text.toString().trim()
//            val passWord = findViewById<EditText>(R.id.pass_word).text.toString().trim()
//            Log.d(TAG, "user:" + userName)
//            val intent = Intent(this, CheckLogIn::class.java)
//            intent.putExtra(USER_NAME, userName).putExtra(PASS_WORD, passWord)
//            startActivityForResult(intent, VALIDATE_USER_INTENT)
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VALIDATE_USER_INTENT && resultCode == Activity.RESULT_OK) {
            val validated = data?.getBooleanExtra(USER_VALIDATED, TorF ?: TorF)
            val l = findViewById<TextView>(R.id.logged_in)
            if (validated != null && validated == true ) {
                l.isVisible = true
                findViewById<TextView>(R.id.directions).isVisible = false
                findViewById<Button>(R.id.login_button).isVisible = false
                findViewById<EditText>(R.id.user_name).isVisible = false
                findViewById<EditText>(R.id.pass_word).isVisible = false
            }
            else {
                Toast.makeText(this, getString(R.string.login_error), Toast.LENGTH_LONG).
                apply{
                    //Unable to make Toast move to a different position- no-op in R API30 +
                    setGravity(Gravity.TOP, 0, 0)
                    show()
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }


}