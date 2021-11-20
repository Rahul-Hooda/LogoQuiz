package com.rh.logoquiz.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rh.logoquiz.model.CompanyModel
import com.rh.logoquiz.model.QuizTimer
import com.rh.logoquiz.model.RandomLetterGenerator
import com.rh.logoquiz.model.Repository

class QuizViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository()
    private val quizTimer = QuizTimer(this)
    private val randomLetterGenerator = RandomLetterGenerator()
    private var companyList: ArrayList<CompanyModel>? = null
    private var currentIndex = 0
    private var score = 0

    private val updateTimer: MutableLiveData<String> = MutableLiveData()
    private val updateImage: MutableLiveData<String> = MutableLiveData()
    private val adapterList: MutableLiveData<ArrayList<Char>> = MutableLiveData()
    private val totalScore: MutableLiveData<Int> = MutableLiveData()

    internal fun updateTimer(): LiveData<String> = updateTimer
    internal fun updateImage(): LiveData<String> = updateImage
    internal fun totalScore(): LiveData<Int> = totalScore
    internal fun adapterList(): LiveData<ArrayList<Char>> = adapterList

    init {
        quizTimer.startTimer()
        companyList = repository.getCompanyList(application)
        prepareData()
    }

    private fun prepareData() {
        adapterList.value =
            randomLetterGenerator.getListOfRandomLetters(companyList?.get(currentIndex)?.name)
        updateImage.value = companyList?.get(currentIndex)?.imgUrl
    }

    internal fun updateTime(value: String) {
        updateTimer.value = value
    }

    internal fun timeFinished() {
        currentIndex++;
        prepareData()
    }

    fun nameSubmitted(value: String) {
        //Check if the name is correct
        if (value == companyList?.get(currentIndex)?.name)
            score++
        else
            score--

        totalScore.value = score
        currentIndex++
        prepareData()
    }
}