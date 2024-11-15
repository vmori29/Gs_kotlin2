package com.github.vmori29.globalsolution.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.vmori29.globalsolution.model.DicaModel

@Database(entities = [DicaModel::class], version = 1)
abstract class DicaDatabase : RoomDatabase(){
    abstract fun dicaDao():DicaDao
}