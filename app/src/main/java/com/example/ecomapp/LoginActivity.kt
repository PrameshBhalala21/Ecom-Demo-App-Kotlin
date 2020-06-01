package com.example.ecomapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.ecomapp.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.loginButton.setOnClickListener(View.OnClickListener {

            if (validate()){
            startActivity(Intent(this,MainActivity :: class.java))
            finish()
            }

        })



    }

    private fun validate(): Boolean {

        var isValidUsername = false
        var isValidPassword = false

        if (TextUtils.isEmpty(binding.emailTextInputEditText.text)) {
            binding.emailTextInputLayout.error = resources.getString(R.string.cannot_be_empty)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailTextInputEditText.text).matches()) {
            binding.emailTextInputLayout.error = resources.getString(R.string.enter_valid_email_address)
        } else {
            isValidUsername = true
            binding.emailTextInputLayout.error = null
            binding.emailTextInputLayout.isErrorEnabled = false
        }


        when {
            TextUtils.isEmpty(binding.passwordTextInputEditText.text) -> {
                binding.passwordTextInputLayout.error = resources.getString(R.string.cannot_be_empty)
            }
            binding.passwordTextInputEditText.text!!.length < 6 -> {
                binding.passwordTextInputLayout.error =
                    resources.getString(R.string.password_will_must_be_greater_than_six)
            }
            else -> {
                isValidPassword = true
                binding.passwordTextInputLayout.error = null
                binding.passwordTextInputLayout.isErrorEnabled = false
            }
        }

        return isValidUsername && isValidPassword

    }
}
