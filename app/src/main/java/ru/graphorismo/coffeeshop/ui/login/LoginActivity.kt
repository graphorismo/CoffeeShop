package ru.graphorismo.coffeeshop.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.graphorismo.coffeeshop.R
import ru.graphorismo.coffeeshop.data.auth.Credentials

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    val loginViewModel: LoginViewModel by viewModels()

    lateinit var loginButton: Button
    lateinit var registrationButton: Button
    lateinit var loginEditText : EditText
    lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById<Button>(R.id.activityLogin_button_signIn)
        registrationButton = findViewById<Button>(R.id.activityLogin_button_signUp)
        loginEditText = findViewById(R.id.activityLogin_editText_login)
        passwordEditText = findViewById(R.id.activityLogin_editText_password)

        loginButton.setOnClickListener() {tryToLogIn()}


    }

    private fun tryToLogIn() {
        var credentials =
            Credentials(loginEditText.text.toString(), passwordEditText.text.toString())
        loginViewModel.tryToLogIn(credentials)
    }
}