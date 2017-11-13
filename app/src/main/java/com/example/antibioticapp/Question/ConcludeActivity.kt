package com.example.antibioticapp.Question

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.antibioticapp.Detail.DetailActivity
import com.example.antibioticapp.Home.HomePatientActivity
import com.example.antibioticapp.Home.HomeProviderActivity
import com.example.antibioticapp.Home.HomeStaffActivity
import com.example.antibioticapp.R
import kotlinx.android.synthetic.main.activity_conclude.*
import kotlinx.android.synthetic.main.activity_profile_patient.*
import kotlinx.android.synthetic.main.activity_question.*
import java.text.SimpleDateFormat
import java.util.*

class ConcludeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conclude)
        val type = intent.getStringExtra("type")

        var g: Int
        if (type.equals("provider")) {
            resultTypeText.text = "สำหรับบุคลากรทางการแพทย์"
//            toolbar_Q.setBackgroundColor(Color.rgb(64, 110, 143))

        } else {
            resultTypeText.text = "สำหรับผู้ป่วย - ประชาชน"
            nameText.setVisibility(View.GONE)
        }

        numberResultText.text = intent.getStringExtra("resultNumber")
        g = (intent.getStringExtra("resultNumber")).toInt()
        print(g)
        if (g <= 2) {
            detailResult.text = "ควรพักผ่อนมากๆ ใช้ยารักษาตามอาการ สังเกตอาการหากอาการไม่ดีขึ้นในช่วง 2-3 วันต่อมา พิจารณาพบแพทย์ หรือเภสัชกรร้านยาคุณภาพเพื่อส่องตรวจคอ และตรวจต่อมน้ำเหลืองที่คอ "
        } else {
            detailResult.text = "ควรไปพบแพทย์ หรือเภสัชกรร้านยาคุณภาพเพื่อส่องตรวจคอ และตรวจต่อมน้ำเหลืองที่คอ"
        }

        val date = SimpleDateFormat("dd")
        val _date = date.format(Calendar.getInstance().time)

        val month = SimpleDateFormat("MM")
        val _month = month.format(Calendar.getInstance().time)
        val months = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

        val year = SimpleDateFormat("yyyy")
        val _year = year.format(Calendar.getInstance().time)

        val dfTime = SimpleDateFormat("HH:mm")
        val time = dfTime.format(Calendar.getInstance().time)

        tv_date.text = "Date: "+_date+"-"+months[_month.toInt()-1]+"-"+_year+" "+time

        Log.e("eiei",type)

        homeButton.setOnClickListener {
            if (type.equals("provider"))
            {
                val intent1 = Intent(this, HomeProviderActivity::class.java)
                startActivity(intent1)
                finish()
            }
            if (type.equals("patient"))
            {
                val intent2 = Intent(this, HomePatientActivity::class.java)
                startActivity(intent2)
                finish()
            }
            else
            {
                val intent3 = Intent(this, HomeStaffActivity::class.java)
                startActivity(intent3)
                finish()
            }

        }

        detailButton.setOnClickListener {
            val intent3 = Intent(this, DetailActivity::class.java)
            startActivity(intent3)
//            finish()
        }


    }
}
