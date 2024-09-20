package com.example.recyclerviewapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewapp.R
import com.example.recyclerviewapp.model.UserSingleton
import com.example.recyclerviewapp.viewmodel.LoginViewModel
import com.example.recyclerviewapp.viewmodel.LoginViewModelFactory
import com.example.recyclerviewapp.viewmodel.MainViewModelFactory

class Login : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_activity)

        UserSingleton.init(this)

        loginViewModel = LoginViewModelFactory(UserSingleton.userDao).create(LoginViewModel::class.java)

        val nameField = findViewById<TextView>(R.id.userName)
        val userName = intent.getStringExtra("USER_NAME")

        if (userName != null) {
            nameField.text = userName
        }

        val password = findViewById<TextView>(R.id.userPassword)

        val login = findViewById<Button>(R.id.loginButton)

        val createAccount = findViewById<Button>(R.id.createAccountButton)


        login.setOnClickListener {
            if ((nameField.text.toString() != "" ) && (password.text.toString() != "")) {
                val userExist = loginViewModel.verifyUser(nameField.text.toString(), password.text.toString())


                if (userExist) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Informações de login incorretas.", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }

        }

        createAccount.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }





    }
}