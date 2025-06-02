package com.example.srilankabarbershop.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.api.RetrofitClient
import com.example.srilankabarbershop.databinding.ActivityCadastroBinding
import com.example.srilankabarbershop.model.RegisterRequest
import com.example.srilankabarbershop.model.RegisterResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CadastroActivity : AppCompatActivity() {

    lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        supportActionBar?.hide()

        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nomeET = binding.nomeET
        var emailET = binding.emailET
        var passwordET = binding.passwordET
        var confirmPasswordET = binding.confirmPasswordET
        val confirmBtn = binding.confirmBtn
        var respostaET = binding.respostaTV
        var telefoneET = binding.cellET

        fun verificaCamposVazios(): Boolean {
            if (nomeET.text.isEmpty() || emailET.text.isEmpty() || passwordET.text.isEmpty() || confirmPasswordET.text.isEmpty()) {
                return true
            }
            return false
        }

        fun verificaSenha(): Boolean {
            var senha = passwordET.text.toString()
            var confirmaSenha = confirmPasswordET.text.toString()
            if (senha.equals(confirmaSenha)) {
                Log.i("senha", "${passwordET}" + " - "+ "${confirmPasswordET}")
                return true
            } else {
                Log.i("senha", "Se for falso ${passwordET}" + " - "+ "${confirmPasswordET}")
                return false
            }
        }

        confirmBtn.setOnClickListener {
            if (verificaCamposVazios()) {
                respostaET.setText("Preencha todos os campos")
            } else {
                if (verificaSenha()) {
                    val nome = nomeET.text.toString()
                    val email = emailET.text.toString()
                    val telefone = telefoneET.text.toString()
                    val senha = passwordET.text.toString()

                    // Cria o objeto da requisição com tipo CLIENTE
                    val request = RegisterRequest(
                        nome = nome,
                        email = email,
                        telefone = telefone,
                        senha = senha,
                        tipo = "CLIENTE" // Define o tipo como CLIENTE
                    )



                    RetrofitClient.instance.registrar(request).enqueue(object : Callback<ResponseBody> {
                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            try {
                                val mensagem = response.body()?.string() ?: "Resposta vazia"
                                Log.d("DEBUG_RESPONSE", "Resposta bruta: $mensagem")

                                if (response.isSuccessful) {
                                    Toast.makeText(this@CadastroActivity, mensagem, Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this@CadastroActivity, EscolhaCorteActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    respostaET.text = "Erro no cadastro: ${response.code()} - $mensagem"
                                    Log.e("CADASTRO_ERROR", "Código: ${response.code()}, Erro: $mensagem")
                                }
                            } catch (e: Exception) {
                                Log.e("CADASTRO_ERROR", "Erro ao processar resposta: ${e.message}")
                                respostaET.text = "Erro ao interpretar a resposta."
                            }
                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Log.e("CADASTRO_ERROR", "Erro ao fazer a chamada da API: ${t.message}")
                            respostaET.text = "Erro ao realizar o cadastro. Tente novamente mais tarde."
                        }
                    })

                } else {
                    respostaET.setText("As senhas não coincidem")
                }
            }
        }
    }
}