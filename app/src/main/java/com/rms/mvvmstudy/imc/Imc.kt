package com.rms.mvvmstudy.imc

import arrow.core.Either

class Imc : ImcContract {

    override fun calculate(
        weight: String,
        height: String
    ): Either<List<ImcCalcState>, ImcCalcState.Success> {
        return validateValues(weight, height)
    }

    private fun validateValues(
        weight: String,
        height: String
    ): Either<List<ImcCalcState>, ImcCalcState.Success> {
        val errorList = mutableListOf<ImcCalcState>()

        if (weight.isEmpty()) errorList.add(ImcCalcState.EmptyWeight)
        else if (weight.toDouble().compareTo(0.0) == 0) errorList.add(ImcCalcState.WeightIsZero)
        if (height.isEmpty()) errorList.add(ImcCalcState.EmptyHeight)
        else if (height.toDouble().compareTo(0.0) == 0) errorList.add(ImcCalcState.HeightIsZero)




        return if (errorList.isNotEmpty()) {
            Either.left(errorList)
        } else
            calculateImc(weight, height)
    }

    private fun calculateImc(
        weight: String,
        height: String
    ): Either<List<ImcCalcState>, ImcCalcState.Success> {
        val result: Double = weight.toDouble() / (height.toDouble() * height.toDouble())
        return Either.right(ImcCalcState.Success(result.toString()))
    }
}
