package com.rms.mvvmstudy.imc

import arrow.core.Either

interface ImcContract {
    fun calculate(weight: String, height: String): Either<List<ImcCalcState>, ImcCalcState.Success>

}
