package com.example.antibioticapp

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.view.*
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.row_history.view.*
import java.util.*

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        historyListView.adapter = HistoryActivity.myCustomAdapter2(this)

        setSupportActionBar(Title3)
        toolbar_title3.setText(Title3.title)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val title = "การวินิจฉัยครั้งนี้เป็นของคุณ : smewnoi"
        titlelistview.text = title

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //getMenuInflater().inflate() @todo add menufile here
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private class myCustomAdapter2(context: Context): BaseAdapter() {
        private val mContext: Context
        private val somelist = arrayListOf<String>("result:2","result : 5") //@todo use list of question here
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

            if(p1 == null){
                val layoutInfrator = LayoutInflater.from(p2!!.context)
                rowMain = layoutInfrator.inflate(R.layout.row_history,p2,false)
                val viewHolder = ViewHolder(rowMain.hisIdTextView,rowMain.resultScoreTextView,rowMain.resultTextView,rowMain.dateTextView,rowMain.deleteBtn)
                rowMain.tag = viewHolder
            }else{
                rowMain = p1
            }

            rowMain.setBackgroundColor(Color.WHITE)
            val viewHolder = rowMain.tag as ViewHolder

            viewHolder.noTextView.text = p0.toString()
            viewHolder.resultscTextView.text = somelist.get(p0)
            viewHolder.resultTextView.text = "should visit doctor"
            viewHolder.dateTextView.text = Date().toString()
            viewHolder.deleteButton.setOnClickListener {
                somelist.removeAt(p0)
                notifyDataSetChanged()
            }

            return rowMain
        }

        private class ViewHolder(val noTextView: TextView, val resultscTextView: TextView, val resultTextView: TextView, val dateTextView: TextView, val deleteButton: ImageView)

    }
}
