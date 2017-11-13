package com.example.antibioticapp.Home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.antibioticapp.RegisLogin.MainActivity
import com.example.antibioticapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home_staff.*

class HomeStaffActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    private val TAG:String = "Home Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_staff)

        mAuth = FirebaseAuth.getInstance()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        staffPatient.setOnClickListener {
            val intent = Intent(this,HomePatientActivity::class.java)//@todo -- change the class to intent to
            intent.putExtra("type","patient")
            startActivity(intent)
        }

        staffProvider.setOnClickListener {
            val intent = Intent(this,HomeProviderActivity::class.java)//@todo -- change the class to intent to
            intent.putExtra("type","provider")
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.action_logout) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun actionResourceClickHandler(item: MenuItem?) {
        mAuth!!.signOut()
        Toast.makeText(this, "Signed out!", Toast.LENGTH_LONG).show()
        Log.d(TAG, "Signed out!")
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}

