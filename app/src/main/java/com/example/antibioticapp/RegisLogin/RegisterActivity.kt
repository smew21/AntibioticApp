package com.example.antibioticapp.RegisLogin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.antibioticapp.Home.HomePatientActivity
import com.example.antibioticapp.Home.HomeProviderActivity
import com.example.antibioticapp.Home.HomeStaffActivity
import com.example.antibioticapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register_new.*

class RegisterActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    private val TAG: String = "Register Activity"
    private val position = arrayOf("Patient", "Provider", "Staff")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_new)

        mAuth = FirebaseAuth.getInstance()

        val spinner = spinPosition
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, position)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(adapter)

        if (mAuth!!.currentUser != null) {
            if (mAuth!!.currentUser!!.email.equals("smewnoi.cupper@gmail.com")) {
                var intent1 = Intent(baseContext, HomePatientActivity::class.java)
                intent1.putExtra("type", "patient")
                startActivity(intent1)
//                        startActivity(Intent(this@LoginActivity, HomePatientActivity::class.java))
                finish()
            }
            if (mAuth!!.currentUser!!.email.equals("salisa.vit@gmail.com")) {
                var intent2 = Intent(baseContext, HomeProviderActivity::class.java)
                intent2.putExtra("type", "provider")
                startActivity(intent2)
//                        startActivity(Intent(this@LoginActivity, HomeProviderActivity::class.java))
                finish()
            }
            if (mAuth!!.currentUser!!.email.equals("smoo.cu@gmail.com")) {
                var intent3 = Intent(baseContext, HomeStaffActivity::class.java)
                intent3.putExtra("type", "staff")
                startActivity(intent3)
////                        startActivity(Intent(this@LoginActivity, HomeStaffActivity::class.java))
                finish()
            }
//            startActivity(Intent(this@RegisterActivity, ResultActivity::class.java))
//            finish()
        }

        register_registerBtn.setOnClickListener {
            val email = register_emailEditText.text.toString().trim { it <= ' ' }
            val password = register_passwordEditText.text.toString().trim { it <= ' ' }

            if (email.isEmpty()) {
                Toast.makeText(this,"Please enter your email address.", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Email was empty!")
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(this,"Please enter your password.", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Password was empty!")
                return@setOnClickListener
            }

            mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    if (password.length < 6) {
                        Toast.makeText(this,"Password too short! Please enter minimum 6 characters.", Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Enter password less than 6 characters.")
                    } else {
                        Toast.makeText(this,"Authentication Failed: " + task.exception!!.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Authentication Failed: " + task.exception!!.message)
                    }
                } else {
                    Toast.makeText(this,"Create account successfully!", Toast.LENGTH_LONG).show()
                    Log.d(TAG, "Create account successfully!")
                    if (spinner.selectedItem.toString().equals("Patient")) {
                        var intent1 = Intent(baseContext, HomePatientActivity::class.java)
                        intent1.putExtra("type", "patient")
                        startActivity(intent1)
//                        startActivity(Intent(this@LoginActivity, HomePatientActivity::class.java))
                        finish()
                    }
                    if (spinner.selectedItem.toString().equals("Provider")) {
                        var intent2 = Intent(baseContext, HomeProviderActivity::class.java)
                        intent2.putExtra("type", "provider")
                        startActivity(intent2)
//                        startActivity(Intent(this@LoginActivity, HomeProviderActivity::class.java))
                        finish()
                    }
                    if (spinner.selectedItem.toString().equals("Staff")) {
                        var intent3 = Intent(baseContext, HomeStaffActivity::class.java)
                        intent3.putExtra("type", "staff")
                        startActivity(intent3)
////                        startActivity(Intent(this@LoginActivity, HomeStaffActivity::class.java))
                        finish()
                    }
//                    startActivity(Intent(this@RegisterActivity, ResultActivity::class.java))
//                    finish()
                }
            }
        }

//        register_signinBtn.setOnClickListener { startActivity(Intent(this@RegisterActivity, LoginActivity::class.java)) }
    }

}
