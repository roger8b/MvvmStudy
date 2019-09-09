package com.rms.mvvmstudy.imc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rms.mvvmstudy.ScreenState

class ImcCalcViewModel(val imc: ImcContract) : ViewModel() {

    private val _imcCalcState: MutableLiveData<ScreenState<ImcCalcState>> = MutableLiveData()

    val imcCalcState: LiveData<ScreenState<ImcCalcState>>
        get() = _imcCalcState

    fun onImcCalcClicked(weight: String, height: String) {
        imc.calculate(weight, height).fold(::onError, ::onSuccess)
    }

    private fun onError(error: List<ImcCalcState>) {
        error.map {
            _imcCalcState.value = ScreenState.Render(it)
        }
    }

    private fun onSuccess(success: ImcCalcState.Success) {
        _imcCalcState.value = ScreenState.Render(success)
    }
}
