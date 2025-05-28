package com.example.srilankabarbershop.UI

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.srilankabarbershop.databinding.ActivityNovaSenhaBinding
import com.example.srilankabarbershop.model.RedefinirSenhaRequest
import com.example.srilankabarbershop.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NovaSenhaActivity : AppCompatActivity() {

    lateinit var binding: ActivityNovaSenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()

        binding = ActivityNovaSenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("email")

        binding.confirmBtn.setOnClickListener {
            val codigo = binding.codigoEditText.text.toString().trim()
            val novaSenha = binding.novaSenhaEditText.text.toString().trim()

            if (email.isNullOrEmpty() || codigo.isEmpty() || novaSenha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val request = RedefinirSenhaRequest(email, codigo, novaSenha)

            RetrofitClient.instance.redefinirSenha(request).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@NovaSenhaActivity, "Senha alterada com sucesso", Toast.LENGTH_LONG).show()
                        finish() // Fecha a tela e volta pro login
                    } else {
                        Toast.makeText(this@NovaSenhaActivity, "Código inválido ou expirado", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(this@NovaSenhaActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
