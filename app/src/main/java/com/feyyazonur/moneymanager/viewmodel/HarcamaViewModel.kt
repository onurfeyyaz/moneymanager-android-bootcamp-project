package com.feyyazonur.moneymanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.feyyazonur.moneymanager.model.Harcama
import com.feyyazonur.moneymanager.database.HarcamaDatabase
import com.feyyazonur.moneymanager.repository.HarcamaRepository
import kotlinx.coroutines.launch

class HarcamaViewModel(
    application: Application
) : AndroidViewModel(application) {

    val getAllHarcama: LiveData<List<Harcama>>
    private val repository: HarcamaRepository

    //private var sonHarcama = MutableLiveData<Harcama?>()
    //val tumHarcamalar = database.getAllHarcama()

    init {
        //initializeSonHarcama()
        val harcamaDao = HarcamaDatabase.getInstance(application).harcamaDatabaseDao()
        repository = HarcamaRepository(harcamaDao)
        getAllHarcama = repository.getAllHarcama
    }
    /*private fun initializeSonHarcama() {
        viewModelScope.launch {
            sonHarcama.value = getSonHarcamaFromDatabase()
        }
    }
    private suspend fun getSonHarcamaFromDatabase(): Harcama?{
        var harcama = database.getLastHarcama()
        return harcama
    }*/

    fun harcamaEkle(harcama: Harcama) {
        viewModelScope.launch {
            repository.addHarcama(harcama)
        }
    }

    fun deleteHarcama(harcama: Harcama){
        viewModelScope.launch {
            repository.deleteHarcama(harcama)
        }
    }

    /*
    private suspend fun insert(harcama: Harcama){
        database.insert(harcama)
    }*/
}