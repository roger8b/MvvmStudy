package com.rms.mvvmstudy.imc

sealed class MainState {

    class ShowItns(val itens: List<String>): MainState()
    class ShowMessage(val message: String): MainState()
}