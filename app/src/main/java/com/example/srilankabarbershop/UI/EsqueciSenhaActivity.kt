package com.example.srilankabarbershop.UI

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.databinding.ActivityEsqueciSenhaBinding

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
        btnEnviarCod.setOnClickListener{
            enviado.visibility = View.VISIBLE
        }

        btnEnviarCod.setOnClickListener {
            val email = binding.emailET.text.toString().trim()

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


    }
}