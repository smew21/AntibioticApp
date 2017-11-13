package com.example.antibioticapp.Question

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.antibioticapp.R
import kotlinx.android.synthetic.main.activity_profile_patient.*

class ProfilePatientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_patient)
        val type = intent.getStringExtra("type")
        nameButton.setOnClickListener{
            val intent = Intent(this, QuestionActivity::class.java)
            intent.putExtra("namePatient", edt_profile.text.toString())
            intent.putExtra("type", type)
            Log.e("eiei","$type")
            startActivity(intent)
            finish()
        }
    }
}
