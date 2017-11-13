package com.example.antibioticapp.Home

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.antibioticapp.HistoryActivity
import com.example.antibioticapp.Question.ProfilePatientActivity
import com.example.antibioticapp.Question.QuestionActivity
import com.example.antibioticapp.RegisLogin.MainActivity
import com.example.antibioticapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home_patient.*
import kotlinx.android.synthetic.main.activity_home_provider.*

class HomePatientActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    private val TAG:String = "Home Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_patient)

        mAuth = FirebaseAuth.getInstance()

        setSupportActionBar(toolbar_home)
        tv_toolbar_home.setText("สำหรับผู้ป่วย-ประชาชน")
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        val type = intent.getStringExtra("type")

        btn_gotoHis_pa.setOnClickListener {
            var intent2 = Intent(baseContext, HistoryActivity::class.java)
            intent2.putExtra("type", "provider")
            startActivity(intent2)
        }

        btn_gotoQ_patient.setOnClickListener {
            if (type.equals("patient"))
            {
                var intent2 = Intent(baseContext, QuestionActivity::class.java)
                intent2.putExtra("type", "patient")
                startActivity(intent2)
            }
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
