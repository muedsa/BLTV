package com.muedsa.bltv.model.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _loginState = mutableStateOf(LoginState.UNKNOWN)

    val loginState by _loginState

    private fun checkLogin() {
        _loginState.value = LoginState.UNKNOWN
        Thread {
            Thread.sleep(300)
            _loginState.value = LoginState.LOGIN
        }.start()
    }

    fun login() {
        Thread {
            Thread.sleep(300)
            _loginState.value = LoginState.LOGIN
        }.start()
    }

    fun logout() {
        _loginState.value = LoginState.NO_LOGIN
    }

    init {
        checkLogin()
    }
}