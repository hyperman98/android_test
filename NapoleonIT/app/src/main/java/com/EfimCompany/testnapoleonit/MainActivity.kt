package com.EfimCompany.testnapoleonit

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var count:Float = 0.0F
    var pref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pref=getSharedPreferences("Values", Context.MODE_PRIVATE)

        count= pref?.getFloat("count", 0.0F)!!
        textView.text = String.format("%.2f", count.toFloat())
    }

    fun addWater(view: View){
        val countString = textView.text.toString();

        if(editTextNumberDecimal.text.isNotEmpty()){

            var addCount:Float = editTextNumberDecimal.text.toString().toFloat()
            count+=addCount
        }

        textView.text = String.format("%.2f", count)
    }

    fun reduceWater(view: View) {
        val countString = textView.text.toString();

        if(editTextNumberDecimal.text.isNotEmpty()) {

            var reduceCount: Float = editTextNumberDecimal.text.toString().toFloat()
            count -= reduceCount
        }
        textView.text = String.format("%.2f", count)

    }

    fun saveData(res:Float){
        val editor = pref?.edit()

        editor?.putFloat("count", res)

        editor?.apply()
    }


    override fun onPause() {
        super.onPause()
        saveData(count)
    }
}