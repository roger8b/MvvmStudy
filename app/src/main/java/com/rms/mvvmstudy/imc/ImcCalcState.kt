package com.rms.mvvmstudy.imc

sealed class ImcCalcState {
    class Success(val result: String) : ImcCalcState()
    object EmptyWeight : ImcCalcState()
    object EmptyHeight : ImcCalcState()
    object WeightIsZero : ImcCalcState()
    object HeightIsZero : ImcCalcState()
}