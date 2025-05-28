package com.example.srilankabarbershop.UI

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.srilankabarbershop.databinding.ActivityEsqueciSenhaBinding
import com.example.srilankabarbershop.model.RecuperarSenhaRequest
import com.example.srilankabarbershop.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EsqueciSenhaActivity : AppCompatActivity() {

    lateinit var binding: ActivityEsqueciSenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()

        binding = ActivityEsqueciSenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val enviado = binding.enviadoParaEmailTV
        val btnEnviarCod = binding.confirmBtn

        btnEnviarCod.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Informe o e-mail", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val request = RecuperarSenhaRequest(email)

            RetrofitClient.instance.recuperarSenha(request).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        enviado.visibility = View.VISIBLE
                        Toast.makeText(this@EsqueciSenhaActivity, "Código enviado para o e-mail", Toast.LENGTH_SHORT).show()

                        // Abre tela de redefinir senha
                        val intent = Intent(this@EsqueciSenhaActivity, NovaSenhaActivity::class.java)
                        intent.putExtra("email", email)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this@EsqueciSenhaActivity, "E-mail não encontrado", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(this@EsqueciSenhaActivity, "Erro de rede: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
