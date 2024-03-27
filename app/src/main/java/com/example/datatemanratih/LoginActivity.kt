package com.example.datatemanratih

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.datatemanratih.databinding.ActivityLoginBinding
import com.example.datatemanratih.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

 class LoginActivity : AppCompatActivity(), View.OnClickListener {
        private var auth: FirebaseAuth? = null
        private val RC_SIGN_IN = 1
        private lateinit var binding: ActivityLoginBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.progres.visibility = View.GONE
            binding.login.setOnClickListener(this)
            auth = FirebaseAuth.getInstance()

            if (auth!!.currentUser == null) {

            } else {
                intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == RC_SIGN_IN) {
                val response = IdpResponse.fromResultIntent(data)

                if (resultCode == Activity.RESULT_OK) {
                    val user = FirebaseAuth.getInstance().currentUser
                    Toast.makeText(this, "login berhasil", Toast.LENGTH_SHORT).show()
                    intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "login dibatalkan", Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun onClick(v: View?) {
            val providers = arrayListOf(
                AuthUI.IdpConfig.GoogleBuilder().build()
            )

            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build(), RC_SIGN_IN
            )
        }
    }

