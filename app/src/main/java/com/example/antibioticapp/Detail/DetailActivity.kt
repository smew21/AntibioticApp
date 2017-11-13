package com.example.antibioticapp.Detail

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.view.*
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.antibioticapp.R
import kotlinx.android.synthetic.main.activity_conclude__homepro.*
import kotlinx.android.synthetic.main.row_main.view.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conclude__homepro)

        setSupportActionBar(Title2)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        conclude_listView.adapter = myCustomAdapter(this)
        val result = "ควรไปพบแพทย์" //@todo assign real val here
        val total = "3"
        sumTextView.text = total
        resultText2.text = result

    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        //getMenuInflater().inflate() @todo add menufile here
//        return true
//    }

    //    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when (item!!.getItemId()) {
//            android.R.id.home -> {
//                NavUtils.navigateUpFromSameTask(this)
//                return true
//            }
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }
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

    private class myCustomAdapter(context: Context) : BaseAdapter() {
        private val mContext: Context
        var somelist = arrayOf("มีอาการมากกว่า 3 วัน", "น้ำมูก คัดจมูกหรือแน่นจมูก", "ไข้  (อุณหภูมิที่วัดทางปาก มากกว่า 38c  หรือ มากกว่า 37.5c ทางรักแร้)", "ไอหรือจาม", "ส่องตรวจคอพบจุดหนอง", "ตรวจพบต่อมน้ำเหลืองโตที่ลำคอ")

        //        private val somelist = arrayListOf<String>("1. avlfsud","2.fsdj","3..sdfsi","4. sfdgjk") //@todo use list of question here
        init {
            mContext = context
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getItem(p0: Int): Any {
            return somelist[p0]
        }

        override fun getCount(): Int {
            return somelist.size
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val rowMain: View

            if (p1 == null) {
                val layoutInfrator = LayoutInflater.from(p2!!.context)
                rowMain = layoutInfrator.inflate(R.layout.row_main, p2, false)
                val viewHolder = ViewHolder(rowMain.question_textView, rowMain.answerImageView)
                rowMain.tag = viewHolder
            } else {
                rowMain = p1
            }

            rowMain.setBackgroundColor(Color.WHITE)
            val viewHolder = rowMain.tag as ViewHolder

            viewHolder.nameTextView.text = somelist.get(p0)
            if (p0 % 2 == 0) {
                viewHolder.rowImageView.setImageResource(R.drawable.wrong)
            } else {
                viewHolder.rowImageView.setImageResource(R.drawable.correct)
            }

            return rowMain
        }

        private class ViewHolder(val nameTextView: TextView, val rowImageView: ImageView)

    }
}
