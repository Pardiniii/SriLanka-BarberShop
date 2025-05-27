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
                    val senha = passwordET.text.toString()

                    // Cria o objeto da requisição com tipo CLIENTE
                    val request = RegisterRequest(
                        nome = nome,
                        email = email,
                        senha = senha,
                        tipo = "CLIENTE" // Define o tipo como CLIENTE
                    )

                    // Faz a chamada da API
                    RetrofitClient.instance.registrar(request).enqueue(object : Callback<RegisterResponse> {
                        override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                            val responseBodyString = response.errorBody()?.string() ?: response.body()?.toString()
                            Log.d("DEBUG_RESPONSE", "Resposta bruta: $responseBodyString")

                            if (response.isSuccessful) {
                                Toast.makeText(this@CadastroActivity, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@CadastroActivity, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Log.e("CADASTRO_ERROR", "Código: ${response.code()}, Erro: $responseBodyString")
                                respostaET.setText("Erro no cadastro: ${response.code()} - ${responseBodyString ?: "Erro desconhecido"}")
                            }
                        }

                        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
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
