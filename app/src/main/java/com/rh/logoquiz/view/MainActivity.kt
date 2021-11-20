package com.rh.logoquiz.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rh.logoquiz.R
import com.rh.logoquiz.databinding.ActivityMainBinding
import com.rh.logoquiz.viewModel.BaseViewModelFactory
import com.rh.logoquiz.viewModel.QuizViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: QuizViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initListener()
        initViewModel()
        addObservers()
    }

    private fun addObservers() {
        viewModel.updateImage().observe(this, Observer{
            //Update image here

        })

        viewModel.updateTimer().observe(this, Observer{
            binding.tvTimeRemaining.text = it
        })

        viewModel.totalScore().observe(this, Observer{
            binding.tvScore.text = it.toString()
        })

        viewModel.totalScore().observe(this, Observer{
            binding.tvScore.text = it.toString()
        })

        viewModel.adapterList().observe(this, Observer{
            //Update suggestions in Recycler View
        })
    }

    private fun initListener() {
        binding.btnNext.setOnClickListener{
            viewModel.nameSubmitted(binding.etLogoName.text.toString())
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this,BaseViewModelFactory(application)).get(QuizViewModel::class.java)
    }
}