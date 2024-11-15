package com.github.vmori29.globalsolution

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.vmori29.globalsolution.viewModel.DicaAdapter
import com.github.vmori29.globalsolution.viewModel.DicaViewModel
import com.github.vmori29.globalsolution.viewModel.DicaViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dicaAdapter: DicaAdapter
    private lateinit var dicaViewModel: DicaViewModel
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botaoIntegrantes: Button = findViewById(R.id.button)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Green Dica"

        recyclerView = findViewById(R.id.recyclerView) 

        dicaAdapter = DicaAdapter(emptyList()) { dica ->
            Toast.makeText(this, dica.descricao, Toast.LENGTH_LONG).show()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = dicaAdapter

        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                dicaAdapter.getFilter().filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                dicaAdapter.getFilter().filter(newText)
                return false
            }
        })

        val tituloInput = findViewById<EditText>(R.id.tituloInput)
        val descInput = findViewById<EditText>(R.id.descInput)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val titulo = tituloInput.text.toString()
            val descricao = descInput.text.toString()

            if (titulo.isEmpty()) {
                tituloInput.error = "Preencha o título"
                return@setOnClickListener
            }

            if (descricao.isEmpty()) {
                descInput.error = "Preencha a descrição"
                return@setOnClickListener
            }

            dicaViewModel.addDica(titulo, descricao)

            tituloInput.text.clear()
            descInput.text.clear()
        }

        botaoIntegrantes.setOnClickListener {
            val intent = Intent(this, EquipeActivity::class.java)
            startActivity(intent)
        }


        val viewModelFactory = DicaViewModelFactory(application)
        dicaViewModel = ViewModelProvider(this, viewModelFactory).get(DicaViewModel::class.java)

        dicaViewModel.dicaLiveData.observe(this) { dicas ->
            dicaAdapter.updateDica(dicas)
        }
    }
}
