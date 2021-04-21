package com.example.timer_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.timer_example.databinding.ActivityMainBinding
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var inputTime by Delegates.notNull<Long>()
    private lateinit var timer: CountDownTimer
    private var countDown = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.buttonStart.setOnClickListener {

            var TimeStart = binding.inputTimeValue.text.toString()
            var TimeStart_int = TimeStart.toInt()
            TimeStart_int = TimeStart_int * 1000
            inputTime = TimeStart_int.toLong()
            startTimer()
            binding.buttonStart.visibility = View.INVISIBLE
            binding.buttonStop.visibility = View.VISIBLE

        }
        binding.buttonStop.setOnClickListener {
            timer.cancel()
            binding.textViewShowtime.text = ""
            binding.inputTimeValue.setText("")
            binding.buttonStart.visibility = View.VISIBLE
            binding.buttonStop.visibility = View.INVISIBLE
            Toast.makeText(this, "Timer Stopped ..", Toast.LENGTH_LONG).show()
        }


    }

    fun startTimer() {
        timer = object : CountDownTimer(inputTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val f: NumberFormat = DecimalFormat("00")
                val min = millisUntilFinished / 60000 % 60
                val sec = millisUntilFinished / 1000 % 60
                println(countDown)
                countDown = (f.format(min) + ":" + f.format(sec))
                binding.textViewShowtime.text = countDown
            }

            override fun onFinish() {
                binding.textViewShowtime.text = "Done"
            }

        }
        timer.start()

    }

}

//timer = object  :CountDownTimer(600000,1000){
//
//    override fun onFinish() {
//        binding.textViewShowtime.text="Done"
//
//    }
//
//    override fun onTick(millisUntilFinished: Long) {
//        binding.textViewShowtime.text=(450000/1000).toString()
//    }
//}
//binding.buttonStart.setOnClickListener {
//    timer.start()
//}

