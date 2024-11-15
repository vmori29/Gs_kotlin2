package com.github.vmori29.globalsolution.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.github.vmori29.globalsolution.model.DicaModel

@Dao
interface DicaDao {
    @Query("SELECT * FROM DicaModel")
    fun getAll(): LiveData<List<DicaModel>>

    @Insert
    fun insert(dica: DicaModel)

    @Delete
    fun delete(dica: DicaModel)
}