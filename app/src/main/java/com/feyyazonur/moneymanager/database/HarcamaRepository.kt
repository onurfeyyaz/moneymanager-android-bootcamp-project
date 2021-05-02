package com.feyyazonur.moneymanager.database

import androidx.lifecycle.LiveData

class HarcamaRepository(private val harcamaDatabaseDao: HarcamaDatabaseDao) {

    val getAllHarcama: LiveData<List<Harcama>> = harcamaDatabaseDao.getAllHarcama()

    suspend fun addHarcama(harcama: Harcama){
        harcamaDatabaseDao.insert(harcama)
    }
}