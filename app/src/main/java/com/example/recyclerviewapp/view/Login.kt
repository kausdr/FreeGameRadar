package com.example.recyclerviewapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewapp.R

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_activity)


        val nameField = findViewById<TextView>(R.id.userName)

        val password = findViewById<TextView>(R.id.userPassword)

        val login = findViewById<Button>(R.id.loginButton)

        val createAccount = findViewById<Button>(R.id.createAccountButton)


        login.setOnClickListener {
            if ((nameField.text.toString() != null ) && (password.text.toString() != null)) {
                // verifica se tem usuario no bd

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }

//        createAccount.setOnClickListener {
//            val intent = Intent(this, CreateUser::class.java)
//            startActivity(intent)
//        }





    }
}