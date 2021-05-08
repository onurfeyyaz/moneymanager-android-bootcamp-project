package com.feyyazonur.moneymanager.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.feyyazonur.moneymanager.database.HarcamaDatabase
import com.feyyazonur.moneymanager.model.Harcama
import com.feyyazonur.moneymanager.network.Api
import com.feyyazonur.moneymanager.network.ApiResponse
import com.feyyazonur.moneymanager.repository.HarcamaRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HarcamaViewModel(
    application: Application
) : AndroidViewModel(application) {

    val getAllHarcama: LiveData<List<Harcama>>
    val toplamHarcananPara: LiveData<Float>
    private val repository: HarcamaRepository

    private var sharedPrefs: SharedPreferences =
        application.getSharedPreferences("kurKaydet", Context.MODE_PRIVATE)

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    init {
        val harcamaDao = HarcamaDatabase.getInstance(application).harcamaDatabaseDao()
        repository = HarcamaRepository(harcamaDao)
        getAllHarcama = repository.getAllHarcama
        toplamHarcananPara = repository.toplamHarcananPara
        kurGuncelle()
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

    private fun kurGuncelle() {
        Api.retrofitService.getKurTRY().enqueue(
            object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    kurKaydet(response)
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.d("FAILURE", t.message ?: "HO")
                }

            }
        )
    }

    private fun kurKaydet(response: Response<ApiResponse>) {
        with(sharedPrefs.edit()) {
            response.body()?.conversion_rates?.USD?.let { putFloat("kurGuncelleUSD", it) }
            response.body()?.conversion_rates?.EUR?.let { putFloat("kurGuncelleEUR", it) }
            response.body()?.conversion_rates?.GBP?.let { putFloat("kurGuncelleGBP", it) }
            apply()
        }
    }
}
