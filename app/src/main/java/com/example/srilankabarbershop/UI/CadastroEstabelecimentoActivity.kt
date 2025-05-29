package com.example.srilankabarbershop.UI

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.srilankabarbershop.R
import com.example.srilankabarbershop.api.RetrofitClient
import com.example.srilankabarbershop.model.EstabelecimentoRequest
import com.example.srilankabarbershop.model.EstabelecimentoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroEstabelecimentoActivity : AppCompatActivity() {

    private lateinit var etNome: EditText
    private lateinit var etCep: EditText
    private lateinit var etNumero: EditText
    private lateinit var etComplemento: EditText
    private lateinit var btnCadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_estabelecimento)

        etNome = findViewById(R.id.etNome)
        etCep = findViewById(R.id.etCep)
        etNumero = findViewById(R.id.etNumero)
        etComplemento = findViewById(R.id.etComplemento)
        btnCadastrar = findViewById(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener {
            cadastrarEstabelecimento()
        }
    }

    private fun cadastrarEstabelecimento() {
        val nome = etNome.text.toString()
        val cep = etCep.text.toString()
        val numero = etNumero.text.toString()
        val complemento = etComplemento.text.toString()

        if (nome.isEmpty() || cep.isEmpty() || numero.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show()
            return
        }

        val request = EstabelecimentoRequest(nome, cep, numero, complemento.ifEmpty { null })

        // Aqui você deve buscar o token salvo após o login
        val token = "Bearer " + getSharedPreferences("app_prefs", MODE_PRIVATE).getString("jwt_token", "")

        RetrofitClient.instance.criarEstabelecimento(request, token!!)
            .enqueue(object : Callback<EstabelecimentoResponse> {
                override fun onResponse(
                    call: Call<EstabelecimentoResponse>,
                    response: Response<EstabelecimentoResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@CadastroEstabelecimentoActivity, "Estabelecimento cadastrado com sucesso!", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Toast.makeText(this@CadastroEstabelecimentoActivity, "Erro: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<EstabelecimentoResponse>, t: Throwable) {
                    Toast.makeText(this@CadastroEstabelecimentoActivity, "Falha: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
