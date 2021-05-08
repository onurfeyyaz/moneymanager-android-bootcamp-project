package com.feyyazonur.moneymanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.feyyazonur.moneymanager.database.HarcamaDatabase
import com.feyyazonur.moneymanager.model.Harcama
import com.feyyazonur.moneymanager.network.Api
import com.feyyazonur.moneymanager.repository.HarcamaRepository
import kotlinx.coroutines.launch

class HarcamaViewModel(
    application: Application
) : AndroidViewModel(application) {

    val getAllHarcama: LiveData<List<Harcama>>
    val toplamHarcananPara: LiveData<Float>
    private val repository: HarcamaRepository

    //private val _paraStatus = MutableLiveData<String>()
    //val paraStatus: LiveData<String> = _paraStatus

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    init {
        val harcamaDao = HarcamaDatabase.getInstance(application).harcamaDatabaseDao()
        repository = HarcamaRepository(harcamaDao)
        getAllHarcama = repository.getAllHarcama
        toplamHarcananPara = repository.toplamHarcananPara
        getPhotos()
    }

    fun harcamaEkle(harcama: Harcama) {
        viewModelScope.launch {
            repository.addHarcama(harcama)
        }
    }

    fun deleteHarcama(harcama: Harcama) {
        viewModelScope.launch {
            repository.deleteHarcama(harcama)
        }
    }

    /*fun getParaStatus(paraBirimi: String): String {
        _paraStatus.value = paraBirimi//ParaBirimiStatus.valueOf(paraBirimi)
        Log.d("---GET PARA STATUS---", _paraStatus.value.toString())
        return _paraStatus.value.toString()
    }*/

    private fun getPhotos() {
        viewModelScope.launch {
            try {
                val listResult = Api.retrofitService.getPhotos()
                _status.value = "Success: ${listResult.size}"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}