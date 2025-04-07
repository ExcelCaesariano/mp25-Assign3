package com.example.tugas3

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.text.InputType
import android.widget.CheckBox


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailInput = findViewById<EditText>(R.id.editEmail)
        val passwordInput = findViewById<EditText>(R.id.editPassword)
        val loginButton = findViewById<Button>(R.id.buttonLogin)
        val registerLink = findViewById<TextView>(R.id.textRegister)

        val showPasswordCheckbox = findViewById<CheckBox>(R.id.checkboxShowPassword)

        showPasswordCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                passwordInput.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                passwordInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            passwordInput.setSelection(passwordInput.text.length)
        }

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
            } else {
                val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
                val savedEmail = sharedPref.getString("email", null)
                val savedPassword = sharedPref.getString("password", null)
                val savedName = sharedPref.getString("name", "User")

                if (email == savedEmail && password == savedPassword) {
                    val user = User(savedName ?: "User", email, password)
                    val intent = Intent(this, LandingActivity::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                } else if (email != savedEmail && password != savedPassword) {
                    Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show()
                } else if (email != savedEmail) {
                    Toast.makeText(this, "Incorrect email", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                }
            }
        }



        registerLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
