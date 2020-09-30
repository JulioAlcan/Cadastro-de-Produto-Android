package com.example.ado1_cadastro
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val sh = getSharedPreferences("nome", Context.MODE_PRIVATE)
        val hs = getSharedPreferences("valorCompra", Context.MODE_PRIVATE)
        val ss = getSharedPreferences("valorCusto", Context.MODE_PRIVATE)


        //limpa a caixa de bloco de notas e de anotação
        btLimpar.setOnClickListener { v: View? ->
            txtNomeProd.text.clear()
            txtPesq.text.clear()
            txtValorCompra.text.clear()
            txtValorVenda.text.clear()
            txtLucro.setText("Lucro / Prejuízo")
            txtLucro.setTextColor(Color.parseColor("#000000"))
        }

        //grava o conteúdo do bloco de notas usando o nome da anotação com chave
        btSalvar.setOnClickListener { v: View? ->
            if(txtPesq.text.isNotEmpty()){
                sh.edit().putString(txtPesq.text.toString(),txtNomeProd.text.toString()).apply()
                hs.edit().putString(txtPesq.text.toString(),txtValorCompra.text.toString()).apply()
                ss.edit().putString(txtPesq.text.toString(),txtValorVenda.text.toString()).apply()

                Toast.makeText(this,"Produto Salvo!",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Nome do produto inexistente",Toast.LENGTH_SHORT).show()
            }
        }

        btCalc.setOnClickListener { v: View? ->
            var custo = txtValorCompra.text.toString().toDouble();
            var venda = txtValorVenda.text.toString().toDouble();

            if(custo>venda){
                txtLucro.setText("Houve um prejuízo de -R$"+(venda-custo))
                txtLucro.setTextColor(Color.parseColor("#b51616"))
            }else if(custo<venda){
                txtLucro.setText("Houve um lucro de +R$"+(venda-custo))
                txtLucro.setTextColor(Color.parseColor("#1e9e19"))
            }else{
                txtLucro.setText("Não houve lucro nem prejuízo")
            }
        }

        btAbrir.setOnClickListener { v: View? ->
            if(txtPesq.text.isNotEmpty()){
                var texto = sh.getString(txtPesq.text.toString(),"")
                var texto2 = hs.getString(txtPesq.text.toString(),"")
                var texto3 = ss.getString(txtPesq.text.toString(),"")


                if(texto.isNullOrEmpty()){
                    Toast.makeText(this,"Produto não encontrado",Toast.LENGTH_SHORT).show()
                }
                else{
                    txtNomeProd.setText(texto).toString()
                    txtValorCompra.setText(texto2).toString()
                    txtValorVenda.setText(texto3).toString()

                    Toast.makeText(this,"Produto carregado com sucesso!",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Produto inexistente",Toast.LENGTH_SHORT).show()
            }

        }

    }
}