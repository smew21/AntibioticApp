package com.example.antibioticapp.Question

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.antibioticapp.R
import com.example.antibioticapp.RegisLogin.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home_provider.*
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    private val TAG:String = "Question Activity"

    var index = 0
    var caseResult = 0
//    var type = "provider"
    var providerQuestionList = arrayOf("มีอาการมากกว่า 3 วัน","น้ำมูก คัดจมูกหรือแน่นจมูก","ไข้  (อุณหภูมิที่วัดทางปาก มากกว่า 38c  หรือ มากกว่า 37.5c ทางรักแร้)","ไอหรือจาม","ส่องตรวจคอพบจุดหนอง","ตรวจพบต่อมน้ำเหลืองโตที่ลำคอ")
    var patientQuestionList = arrayOf("มีอาการมากกว่า 3 วัน","น้ำมูก คัดจมูกหรือแน่นจมูก","ไข้  (อุณหภูมิที่วัดทางปาก มากกว่า 38c  หรือ มากกว่า 37.5c ทางรักแร้)","ไอหรือจาม","มีอาการเจ็บคอมากกลืนอาหารหรือน้ำลายลำบาก")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        setSupportActionBar(toolbar_Q)
        typeText.setText("สำหรับบุคลากรทางการแพทย์")
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mAuth = FirebaseAuth.getInstance()
        val type = intent.getStringExtra("type")
        val name= intent.getStringExtra("namePatient")
        namePatient.setText("การวินิจฉัยครั้งนี้เป็นของ คุณ : $name")
        Log.e("eiei","$type")

        if (  type =="provider") {
            linearLayout.setBackgroundColor(Color.rgb(64,110,143))
            toolbar_Q.setBackgroundColor(Color.rgb(102,142,179))
//            typeText.setBackgroundColor(Color.rgb(102,142,179))
            namePatient.setBackgroundColor(Color.rgb(255,255,255))
            namePatient.setBackgroundColor(Color.alpha(3))
            typeText.text = "สำหรับบุคลากรทางการแพทย์"
            resultsText.text = providerQuestionList[index]
            index++
        } else {
            namePatient.setVisibility(View.GONE)
            typeText.text = "สำหรับผู้ป่วย - ประชาชน "
            resultsText.text = patientQuestionList[index]
            index++
        }

        yesButton.setOnClickListener {
            if ( type =="provider") {
                if (index < providerQuestionList.size) {
                    if (index == 1) {
                        caseResult = caseResult + 1
                        numberImage.setImageResource(R.drawable.n2)
                    }
                    if (index == 2) {
                        caseResult = caseResult + 0
                        numberImage.setImageResource(R.drawable.n3)
                    }
                    if (index == 3) {
                        caseResult = caseResult + 1
                        numberImage.setImageResource(R.drawable.n4)
                    }
                    if (index == 4) {
                        caseResult = caseResult + 0
                        numberImage.setImageResource(R.drawable.n5)
                    }
                    if (index == 5) {
                        caseResult = caseResult + 3
                        numberImage.setImageResource(R.drawable.n6)
                    }

                    resultsText.text = providerQuestionList[index]
                    index++

                } else {
                    caseResult = caseResult + 1
                    val intent = Intent(this, ConcludeActivity::class.java)
                    intent.putExtra("type", "provider")
                    intent.putExtra("resultNumber", caseResult.toString())
                    startActivity(intent)

                }
            } else {
                if (index < patientQuestionList.size) {
                    if (index == 1) {
                        caseResult = caseResult + 1
                        numberImage.setImageResource(R.drawable.n2)
                    }
                    if (index == 2) {
                        caseResult = caseResult + 0
                        numberImage.setImageResource(R.drawable.n3)
                    }
                    if (index == 3) {
                        caseResult = caseResult + 1
                        numberImage.setImageResource(R.drawable.n4)
                    }
                    if (index == 4) {
                        caseResult = caseResult + 0
                        numberImage.setImageResource(R.drawable.n5)
                    }
                    resultsText.text = patientQuestionList[index]
                    index++

                } else {
                    caseResult = caseResult + 0
                    val intent = Intent(this, ConcludeActivity::class.java)
                    intent.putExtra("type", "patient")
                    intent.putExtra("resultNumber", caseResult.toString())
                    startActivity(intent)

                }
            }

        }
        noButton.setOnClickListener {
            if ( type =="provider") {
                if (index < providerQuestionList.size) {
                    if (index == 1) {
                        caseResult = caseResult + 0
                        numberImage.setImageResource(R.drawable.n2)
                    }
                    if (index == 2) {
                        caseResult = caseResult + 1
                        numberImage.setImageResource(R.drawable.n3)
                    }
                    if (index == 3) {
                        caseResult = caseResult + 0
                        numberImage.setImageResource(R.drawable.n4)
                    }
                    if (index == 4) {
                        caseResult = caseResult + 1
                        numberImage.setImageResource(R.drawable.n5)
                    }
                    if (index == 5) {
                        caseResult = caseResult + 0
                        numberImage.setImageResource(R.drawable.n6)
                    }
                    resultsText.text = providerQuestionList[index]
                    index++
                } else {
                    caseResult = caseResult + 0
                    val intent = Intent(this, ConcludeActivity::class.java)
                    intent.putExtra("type", "provider")
                    intent.putExtra("resultNumber", caseResult.toString())
                    startActivity(intent)
                }
            }else{
                if (index < patientQuestionList.size) {
                    if (index == 1) {
                        caseResult = caseResult + 0
                        numberImage.setImageResource(R.drawable.n2)
                    }
                    if (index == 2) {
                        caseResult = caseResult + 1
                        numberImage.setImageResource(R.drawable.n3)
                    }
                    if (index == 3) {
                        caseResult = caseResult + 0
                        numberImage.setImageResource(R.drawable.n4)
                    }
                    if (index == 4) {
                        caseResult = caseResult + 1
                        numberImage.setImageResource(R.drawable.n5)
                    }
                    resultsText.text = patientQuestionList[index]
                    index++
                } else {
                    caseResult = caseResult + 0
                    val intent = Intent(this, ConcludeActivity::class.java)
                    intent.putExtra("type", "patient")
                    intent.putExtra("resultNumber", caseResult.toString())
                    startActivity(intent)


                }
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
        else
        {
            finish()
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
