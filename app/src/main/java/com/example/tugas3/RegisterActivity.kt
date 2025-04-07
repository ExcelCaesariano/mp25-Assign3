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

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nameInput = findViewById<EditText>(R.id.editName)
        val emailInput = findViewById<EditText>(R.id.editEmail)
        val passwordInput = findViewById<EditText>(R.id.editPassword)
        val registerButton = findViewById<Button>(R.id.buttonRegister)
        val loginLink = findViewById<TextView>(R.id.textLogin)

        val showPasswordCheckbox = findViewById<CheckBox>(R.id.checkboxShowPassword)

        showPasswordCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                passwordInput.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                passwordInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            passwordInput.setSelection(passwordInput.text.length)
        }

        registerButton.setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
            } else {
                val user = User(name, email, password)
                val intent = Intent(this, LoginActivity::class.java)
                val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString("name", name)
                    putString("email", email)
                    putString("password", password)
                    apply()
                }
                intent.putExtra("user", user)
                startActivity(intent)
            }
        }

        loginLink.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}