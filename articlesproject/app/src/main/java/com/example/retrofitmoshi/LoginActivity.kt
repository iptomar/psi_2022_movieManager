package com.example.retrofitmoshi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.register_login.*

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btLogin.setOnClickListener {
            verifyIfIsLogged()
        }

        btToRegister.setOnClickListener {
            finish()
        }
    }
      private fun verifyIfIsLogged(){
          val auth = Firebase.auth
          val email = txtEmailLogin.text.toString()
          val password = txtPasswordLogin.text.toString()
          val uid = FirebaseAuth.getInstance().uid
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("LoginActivity", "signInWithEmail:success")
                    val user = auth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Email ou password incorretos",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}









