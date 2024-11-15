package com.github.vmori29.globalsolution.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.github.vmori29.globalsolution.R
import com.github.vmori29.globalsolution.model.DicaModel

class DicaAdapter(private var dicas: List<DicaModel>, private val onItemClick: (DicaModel) -> Unit) 
    : RecyclerView.Adapter<DicaAdapter.DicaViewHolder>() {

    inner class DicaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tituloTextView: TextView = view.findViewById(R.id.tituloDica)
        private val descricaoTextView: TextView = view.findViewById(R.id.descricaoDica)

        fun bind(dica: DicaModel) {
            tituloTextView.text = dica.titulo
            descricaoTextView.text = dica.descricao
            itemView.setOnClickListener { onItemClick(dica) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DicaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dica, parent, false)
        return DicaViewHolder(view)
    }

    override fun getItemCount(): Int = dicas.size

    override fun onBindViewHolder(holder: DicaViewHolder, position: Int) {
        val dica = dicas[position]
        holder.bind(dica)

        holder.itemView.setOnClickListener {
            val detalhes = "Título: ${dica.titulo}\nDescrição: ${dica.descricao}"
            Toast.makeText(holder.itemView.context, detalhes, Toast.LENGTH_LONG).show()
        }
    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val filteredList = if (charSequence.isNullOrEmpty()) {
                    dicas
                } else {
                    val filterPattern = charSequence.toString().lowercase().trim()
                    dicas.filter { it.titulo.lowercase().contains(filterPattern) || it.descricao.lowercase().contains(filterPattern) }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: FilterResults?
            ) {
                dicas = filterResults?.values as List<DicaModel>
                notifyDataSetChanged()
            }
        }
    }

    fun updateDica(novasDicas: List<DicaModel>) {
        dicas = novasDicas
        notifyDataSetChanged()
    }
}
