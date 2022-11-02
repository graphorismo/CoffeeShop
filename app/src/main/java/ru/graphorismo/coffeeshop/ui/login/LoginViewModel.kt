package ru.graphorismo.coffeeshop.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.graphorismo.coffeeshop.data.auth.AuthResponse
import ru.graphorismo.coffeeshop.data.auth.Credentials
import ru.graphorismo.coffeeshop.data.repositories.AuthRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val authRepository: AuthRepository) : ViewModel() {

    var credentials: Credentials = Credentials("","")
    var authResponse = AuthResponse(authResult = "DENY")

    fun tryToLogIn(credentials: Credentials){
        this.credentials = credentials
        viewModelScope.launch {
            authRepository.tryToLogin(credentials)
        }
    }


}