package com.github.vmori29.globalsolution.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.github.vmori29.globalsolution.data.DicaDao
import com.github.vmori29.globalsolution.data.DicaDatabase
import com.github.vmori29.globalsolution.model.DicaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DicaViewModel (application: Application) : AndroidViewModel(application) {

    private val dicaDao: DicaDao

    val dicaLiveData: LiveData<List<DicaModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            DicaDatabase::class.java,
            "dica_database"
        ).build()

        dicaDao = database.dicaDao()

        dicaLiveData = dicaDao.getAll()
    }

    fun addDica(titulo: String, descricao:String) {
    viewModelScope.launch ( Dispatchers.IO ){

        val novaDica = DicaModel(titulo = titulo, descricao = descricao)

        dicaDao.insert(novaDica)

    }
    }

    fun removeDica(dica: DicaModel) {
        viewModelScope.launch(Dispatchers.IO) {
            dicaDao.delete(dica)
        }
    }
}