package com.muedsa.bltv.model.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

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