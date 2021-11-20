package com.rh.logoquiz.model

import android.os.CountDownTimer
import com.rh.logoquiz.viewModel.QuizViewModel

class QuizTimer(private val viewModel: QuizViewModel) {
    private val MAX_TIME = 20000L //20 seconds
    private var timer: CountDownTimer? = null

    internal fun startTimer() {
        timer = object : CountDownTimer(MAX_TIME, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                viewModel.updateTime((millisUntilFinished / 1000).toString())
            }

            override fun onFinish() {
                viewModel.timeFinished()
            }
        }.start()
    }

    internal fun stopTimer() {
        timer?.cancel()
    }
}