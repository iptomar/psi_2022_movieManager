package com.example.retrofitmoshi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.register_login.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_login)


        btRegister.setOnClickListener {
            performRegister()
            val email = txtEmail.text.toString()
            val password = txtPassword.text.toString()
        }

        btToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

        private fun performRegister(){
            val email = txtEmail.text.toString()
            val password = txtPassword.text.toString()


            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Por favor insira um email ou password",Toast.LENGTH_SHORT).show()
            }

            Log.d("LoginActivity","Email is" +email)
            Log.d("LoginActivity","Password is" +password)
            //Firebase Autenticacao para criar um utilizador com email e password
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener{
                    if (!it.isSuccessful) return@addOnCompleteListener
                    txtEmail.setText("")
                    txtPassword.setText("")
                    Log.w("Main", "Criado com sucesso ${it.result.user?.uid}")
                    saveUserToFirebase()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener{
                    Log.w("LoginActivity","Falhou a criacao do user${it.message}")
                    Toast.makeText(applicationContext, "${it.message}", Toast.LENGTH_LONG).show();
                }
        }


    private fun saveUserToFirebase(){
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance("https://moviemanager-deee0-default-rtdb.europe-west1.firebasedatabase.app").getReference("/users/$uid")
        val user = User(uid, txtNome.text.toString())

        ref.setValue(user).addOnSuccessListener {
            Log.d("RegisterActivity", "O utilizador foi salvo")
        }
    }

}

class User(val uid:String, val username: String)