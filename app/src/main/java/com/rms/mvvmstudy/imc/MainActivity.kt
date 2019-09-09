package com.rms.mvvmstudy.imc

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.rms.mvvmstudy.R
import com.rms.mvvmstudy.ScreenState
import com.rms.mvvmstudy.imc.ImcCalcState.Success

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ImcCalcViewModel
    private lateinit var btImcCalc: MaterialButton
    private lateinit var etWeight: TextInputEditText
    private lateinit var etHeight: TextInputEditText
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(Imc())
        )[ImcCalcViewModel::class.java]

        viewModel.imcCalcState.observe(::getLifecycle, ::updateUI)

        init()
    }

    private fun init() {
        btImcCalc = findViewById<MaterialButton>(R.id.bt_imc_calc)
        etHeight = findViewById<TextInputEditText>(R.id.et_height)
        etWeight = findViewById<TextInputEditText>(R.id.et_weight)
        tvResult = findViewById<TextView>(R.id.tv_result)

        btImcCalc.setOnClickListener { onCalcClicked() }
    }

    private fun onCalcClicked() {
        viewModel.onImcCalcClicked(etWeight.text.toString(), etHeight.text.toString())
    }

    private fun updateUI(screenState: ScreenState<ImcCalcState>?) {
        when (screenState) {
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun processRenderState(imcCalcState: ImcCalcState) {
        when (imcCalcState) {
            ImcCalcState.EmptyHeight -> etHeight.error = getString(R.string.height_empty_error)
            ImcCalcState.EmptyWeight -> etWeight.error = getString(R.string.weight_empty_error)
            ImcCalcState.WeightIsZero -> etWeight.error = getString(R.string.error_zero_value)
            ImcCalcState.HeightIsZero -> etHeight.error = getString(R.string.error_zero_value)
            is Success -> showSuccessMessage(imcCalcState.result)
        }
    }

    private fun showSuccessMessage(result: String) {
        tvResult.text = result
    }
}
