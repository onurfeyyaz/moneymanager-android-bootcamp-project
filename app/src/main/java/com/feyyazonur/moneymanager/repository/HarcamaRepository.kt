package com.feyyazonur.moneymanager.repository

import androidx.lifecycle.LiveData
import com.feyyazonur.moneymanager.database.HarcamaDatabaseDao
import com.feyyazonur.moneymanager.model.Harcama

class HarcamaRepository(private val harcamaDatabaseDao: HarcamaDatabaseDao) {

    val getAllHarcama: LiveData<List<Harcama>> = harcamaDatabaseDao.getAllHarcama()

    suspend fun addHarcama(harcama: Harcama) {
        harcamaDatabaseDao.insert(harcama)
    }

    suspend fun deleteHarcama(harcama: Harcama) {
        harcamaDatabaseDao.deleteHarcama(harcama)
    }
}