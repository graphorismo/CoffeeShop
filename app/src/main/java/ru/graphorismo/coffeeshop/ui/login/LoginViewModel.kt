package ru.graphorismo.coffeeshop.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.graphorismo.coffeeshop.data.auth.AuthResponse
import ru.graphorismo.coffeeshop.data.auth.Credentials
import ru.graphorismo.coffeeshop.data.auth.RegistrateResponse
import ru.graphorismo.coffeeshop.data.remote.NetworkException
import ru.graphorismo.coffeeshop.data.repositories.AuthRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val authRepository: AuthRepository) : ViewModel() {

    var credentials: Credentials = Credentials("","")
    var authResponse =
        MutableStateFlow<AuthResponse>(AuthResponse(result = "", token = ""))
    var registrateResponse =
        MutableStateFlow<RegistrateResponse>(RegistrateResponse(status = ""))

    fun tryToLogIn(credentials: Credentials){
        this.credentials = credentials
        viewModelScope.launch(Dispatchers.Default) {
            var response = authRepository.getResponseToLogin(credentials)
            if (response.isSuccessful && response.body() != null){
                authResponse.value = response.body()!!
            }else{
                authResponse.value = AuthResponse("error", "")
            }
        }
    }

    fun tryToRegistrate(credentials: Credentials) {
        this.credentials = credentials
        viewModelScope.launch(Dispatchers.Default) {
            var response = authRepository.getResponseToRegistration(credentials)
            if (response.isSuccessful && response.body() != null){
                registrateResponse.value = response.body()!!
            }else{
                registrateResponse.value = RegistrateResponse(status = "error")
            }
        }
    }


}