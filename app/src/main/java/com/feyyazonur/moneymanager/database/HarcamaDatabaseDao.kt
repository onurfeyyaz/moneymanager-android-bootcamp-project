package com.feyyazonur.moneymanager.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.feyyazonur.moneymanager.model.Harcama

@Dao
interface HarcamaDatabaseDao {

    @Insert()
    suspend fun insert(harcama: Harcama)

    @Update
    suspend fun update(harcama: Harcama)

    @Delete
    suspend fun deleteHarcama(harcama: Harcama)

    @Query("SELECT SUM(harcanan_para) FROM harcama_listesi_table")
    fun toplamHarcananPara(): LiveData<Float>

    @Query("SELECT * from harcama_listesi_table Where harcamaId = :key")
    suspend fun get(key: Long): Harcama?

    @Query("DELETE FROM harcama_listesi_table")
    suspend fun clear()

    @Query("SELECT * FROM harcama_listesi_table ORDER BY harcamaId DESC LIMIT 1")
    suspend fun getLastHarcama(): Harcama?

    // En son aldığım ürün en başta gözüksün diye DESC kullandım.
    @Query("SELECT * FROM harcama_listesi_table ORDER BY harcamaId DESC")
    fun getAllHarcama(): LiveData<List<Harcama>>
}
