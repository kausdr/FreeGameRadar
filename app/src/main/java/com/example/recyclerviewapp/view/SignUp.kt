package com.example.recyclerviewapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.recyclerviewapp.R
import com.example.recyclerviewapp.databinding.ActivityMainBinding
import com.example.recyclerviewapp.model.City
import com.example.recyclerviewapp.model.Singleton
import com.example.recyclerviewapp.model.User
import com.example.recyclerviewapp.model.UserSingleton
import com.example.recyclerviewapp.viewmodel.LoginViewModel
import com.example.recyclerviewapp.viewmodel.LoginViewModelFactory
import com.example.recyclerviewapp.viewmodel.MainViewModelFactory

class SignUp : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.signup_activity)
        
        UserSingleton.init(this)

        loginViewModel = LoginViewModelFactory(UserSingleton.userDao).create(LoginViewModel::class.java)


        val name = findViewById<TextView>(R.id.userCreateName)
        val password = findViewById<TextView>(R.id.userCreatePassword)

        val passwordConfirmation = findViewById<TextView>(R.id.userCreatePasswordConfirmation)

        val saveAccount = findViewById<Button>(R.id.saveButton)

        saveAccount.setOnClickListener {
            if (password.text.toString() == passwordConfirmation.text.toString()) {
                val intent = Intent(this, Login::class.java)

                loginViewModel.addUser(User(name = name.text.toString(), password = password.text.toString()))

                intent.putExtra("USER_NAME", name.text.toString())
                startActivity(intent)

            } else {
                Toast.makeText(this, "AS SENHAS NÃO COINCIDEM", Toast.LENGTH_SHORT).show()
            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}