package com.example.antibioticapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.antibioticapp.Home.HomePatientActivity
import com.example.antibioticapp.Home.HomeProviderActivity
import com.example.antibioticapp.Home.HomeStaffActivity
import com.example.antibioticapp.RegisLogin.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    private val TAG: String = "Login Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_new)

        mAuth = FirebaseAuth.getInstance()

        if (mAuth!!.currentUser != null) {
            startActivity(Intent(this@LoginActivity, ResultActivity::class.java))
            finish()
        }

        login_loginBtn.setOnClickListener {
            val email = login_emailEditText.text.toString().trim { it <= ' ' }
            val password = login_passwordEditText.text.toString().trim { it <= ' ' }
            Log.e("eiei", email)
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email address.", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Email was empty!")
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(this, "Please enter your password.", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Password was empty!")
                return@setOnClickListener
            }

            mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    if (password.length < 6) {
                        login_passwordEditText.error = "Please check your password. Password must have minimum 6 characters."
                        Log.d(TAG, "Enter password less than 6 characters.")
                    } else {
                        Toast.makeText(this, "Authentication Failed: " + task.exception!!.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Authentication Failed: " + task.exception!!.message)
                    }
                } else {
                    Toast.makeText(this, "Sign in successfully!", Toast.LENGTH_LONG).show()
                    Log.d(TAG, "Sign in successfully!")
                    if (email.equals("smewnoi.cupper@gmail.com")) {
                        var intent1 = Intent(baseContext, HomePatientActivity::class.java)
                        intent1.putExtra("type", "patient")
                        startActivity(intent1)
//                        startActivity(Intent(this@LoginActivity, HomePatientActivity::class.java))
                        finish()
                    }
                    if (email.equals("salisa.vit@gmail.com")) {
                        var intent2 = Intent(baseContext, HomeProviderActivity::class.java)
                        intent2.putExtra("type", "provider")
                        startActivity(intent2)
//                        startActivity(Intent(this@LoginActivity, HomeProviderActivity::class.java))
                        finish()
                    }
                    if (email.equals("smoo.cu@gmail.com")) {
                        var intent3 = Intent(baseContext, HomeStaffActivity::class.java)
                        intent3.putExtra("type", "staff")
                        startActivity(intent3)
////                        startActivity(Intent(this@LoginActivity, HomeStaffActivity::class.java))
                        finish()
                    }
                }
            }
        }

        login_createBtn.setOnClickListener { startActivity(Intent(this@LoginActivity, RegisterActivity::class.java)) }

//        login_backBtn.setOnClickListener { onBackPressed() }
    }

}
