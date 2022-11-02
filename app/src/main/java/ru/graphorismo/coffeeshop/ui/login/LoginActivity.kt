package ru.graphorismo.coffeeshop.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.graphorismo.coffeeshop.R
import ru.graphorismo.coffeeshop.data.auth.Credentials
import ru.graphorismo.coffeeshop.data.remote.NetworkException

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    val loginViewModel: LoginViewModel by viewModels()

    lateinit var loginButton: Button
    lateinit var registrationButton: Button
    lateinit var loginEditText: EditText
    lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById<Button>(R.id.activityLogin_button_signIn)
        registrationButton = findViewById<Button>(R.id.activityLogin_button_signUp)
        loginEditText = findViewById(R.id.activityLogin_editText_login)
        passwordEditText = findViewById(R.id.activityLogin_editText_password)

        loginButton.setOnClickListener() { tryToLogIn() }

        lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.authResponse.collect() {
                    Log.d("RESP", it.toString())
                    if (it.result == "ok") {
                        Toast.makeText(this@LoginActivity, "OK", Toast.LENGTH_LONG).show()
                    }
                    else if (it.result == "deny") {
                        Toast.makeText(
                            this@LoginActivity,
                            "Wrong login or password",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else if (it.result == "error") {
                        Toast.makeText(this@LoginActivity, "Network error", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }
    private fun tryToLogIn() {
        var credentials =
            Credentials(loginEditText.text.toString(), passwordEditText.text.toString())
        loginViewModel.tryToLogIn(credentials)

    }
}

