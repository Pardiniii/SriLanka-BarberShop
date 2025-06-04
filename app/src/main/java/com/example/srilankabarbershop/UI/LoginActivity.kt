package com.example.srilankabarbershop.UI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.srilankabarbershop.api.RetrofitClient
import com.example.srilankabarbershop.databinding.ActivityLoginBinding
import com.example.srilankabarbershop.model.LoginRequest
import com.example.srilankabarbershop.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val btnCadastrar = binding.cadastrarTV
        btnCadastrar.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }

        val btnEsquciSenha = binding.esqueciMinhaSenhaTV
        btnEsquciSenha.setOnClickListener {
            startActivity(Intent(this, EsqueciSenhaActivity::class.java))
        }

        val btnLogin = binding.confirmBtn

        btnLogin.setOnClickListener {
            val email = binding.emailED.text.toString()
            val senha = binding.passwordET.text.toString()

            val request = LoginRequest(email, senha)

            RetrofitClient.instance.login(request).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val token = response.body()?.token
                        Toast.makeText(this@LoginActivity, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
                        val sharedPreferences = getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE)
                        sharedPreferences.edit().putString("USER_TOKEN", token).apply()
                        val intent = Intent(this@LoginActivity, EscolhaCorteActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@LoginActivity, "Email ou senha inválidos!", Toast.LENGTH_SHORT).show()
                        Log.d("LOGIN_DEBUG", "Código: ${response.code()} | Body: ${response.body()} | Error: ${response.errorBody()?.string()}")

                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Erro de rede: ${t.message}", Toast.LENGTH_LONG).show()
                    Log.i("FALHA", "onFailure: Erro de rede: ${t.message}")
                }
            })
        }
    }
}
