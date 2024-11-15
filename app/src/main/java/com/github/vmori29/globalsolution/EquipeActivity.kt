package com.github.vmori29.globalsolution

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EquipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipe)


        val nome01TextView: TextView = findViewById(R.id.nomeTextView1)
        val rm01TextView: TextView = findViewById(R.id.rmTextView1)

        val nome01 = "Enzo Yuta Nakamura Onishi"
        val rm01 = "93488"

        val nome02TextView: TextView = findViewById(R.id.nomeTextView2)
        val rm02TextView: TextView = findViewById(R.id.rmTextView2)

        val nome02 = "Victor Mori Kikuchi"
        val rm02 = "93121"

        nome01TextView.text = "Nome: $nome01"
        rm01TextView.text = "RM: $rm01"
        nome02TextView.text = "Nome: $nome02"
        rm02TextView.text = "RM: $rm02"
    }
}