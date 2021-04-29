package com.feyyazonur.moneymanager.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HarcamaDatabaseDao {

    @Insert
    fun insert(harcama: Harcama)

    @Update
    fun update(harcama: Harcama)

    @Query("SELECT * from harcama_listesi_table Where harcamaId = :key")
    fun get(key: Long): Harcama?

    @Query("DELETE FROM harcama_listesi_table")
    fun clear()

    @Query("SELECT * FROM harcama_listesi_table ORDER BY harcamaId DESC LIMIT 1")
    fun getLastHarcama(): Harcama?

    @Query("SELECT * FROM harcama_listesi_table ORDER BY harcamaId DESC")
    fun getAllHarcama(): LiveData<List<Harcama>>
}
