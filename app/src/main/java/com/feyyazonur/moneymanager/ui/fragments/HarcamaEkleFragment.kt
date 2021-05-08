package com.feyyazonur.moneymanager.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.feyyazonur.moneymanager.R
import com.feyyazonur.moneymanager.databinding.FragmentHarcamaEkleBinding
import com.feyyazonur.moneymanager.model.Harcama
import com.feyyazonur.moneymanager.viewmodel.HarcamaViewModel

class HarcamaEkleFragment : Fragment() {
    private var _binding: FragmentHarcamaEkleBinding? = null

    private val binding get() = _binding!!

    private lateinit var mHarcamaViewModel: HarcamaViewModel

    var dolarDegeri: Float = 0.0f
    var euroDegeri: Float = 0.0f
    var sterlinDegeri: Float = 0.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHarcamaEkleBinding.inflate(inflater, container, false)

        mHarcamaViewModel = ViewModelProvider(this).get(HarcamaViewModel::class.java)

        binding.harcamaEkleButton.setOnClickListener {
            Log.d("EKLEFRAGMENT---", mHarcamaViewModel.status.value.toString())
            insertDataToDatabase()
        }

        binding.lifecycleOwner = this
        return binding.root
    }

    private fun insertDataToDatabase() {
        val harcamaTipiRadioGroup = binding.radioGroup.checkedRadioButtonId
        val paraBirimiRadioGroup = binding.radioGroup2.checkedRadioButtonId

        var paraBirimi = ""
        var harcamaTipi = ""
        var harcananPara = 0.0F

        when (harcamaTipiRadioGroup) {
            R.id.radio_button_1 -> harcamaTipi = binding.radioButton1.text.toString()
            R.id.radio_button_2 -> harcamaTipi = binding.radioButton2.text.toString()
            R.id.radio_button_3 -> harcamaTipi = binding.radioButton3.text.toString()
        }

        when (paraBirimiRadioGroup) {
            R.id.radio_button_tl -> paraBirimi = binding.radioButtonTl.text.toString()
            R.id.radio_button_usd -> paraBirimi = binding.radioButtonUsd.text.toString()
            R.id.radio_button_eur -> paraBirimi = binding.radioButtonEur.text.toString()
            R.id.radio_button_gbp -> paraBirimi = binding.radioButtonGbp.text.toString()
        }

        val harcamaIsmi = binding.harcamaDetayTextInputEditText.text.toString()

        kurGetir()
        Log.d("H-EKLE DOLAR DEĞER", dolarDegeri.toString())
        // TL biriminde DB'ye kaydet
        when (paraBirimi) {
            binding.radioButtonTl.text.toString() -> {
                harcananPara = binding.urunParasiTextInputEditText.text.toString().toFloat()
                paraBirimi = binding.radioButtonTl.text.toString()
            }
            binding.radioButtonUsd.text.toString() -> {
                harcananPara =
                    (binding.urunParasiTextInputEditText.text.toString().toFloat() / dolarDegeri)
                paraBirimi = binding.radioButtonTl.text.toString()
            }
            binding.radioButtonEur.text.toString() -> {
                harcananPara =
                    (binding.urunParasiTextInputEditText.text.toString().toFloat() / euroDegeri)
                paraBirimi = binding.radioButtonTl.text.toString()
            }
            binding.radioButtonGbp.text.toString() -> {
                harcananPara =
                    (binding.urunParasiTextInputEditText.text.toString().toFloat() / sterlinDegeri)
                paraBirimi = binding.radioButtonTl.text.toString()
            }
        }

        try {
            if (inputCheck(
                    harcamaIsmi,
                    harcananPara.toString(),
                    harcamaTipiRadioGroup.toString(),
                    paraBirimiRadioGroup.toString()
                )
            ) {
                // Harcama Objesi Oluştur
                val harcama =
                    Harcama(0, harcamaIsmi, harcananPara, harcamaTipi, paraBirimi)
                // Database'e data ekle
                mHarcamaViewModel.harcamaEkle(harcama)
                Toast.makeText(requireContext(), "Kaydedildi!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_harcamaEkleFragment_to_homeFragment)
            }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Tüm alanları doldurun.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(
        harcamaIsmi: String,
        harcananPara: String,
        harcamaTipi: String,
        paraBirimi: String
    ): Boolean {
        return (
                harcamaIsmi != ""
                        && harcananPara != ""
                        && harcamaTipi != ""
                        && paraBirimi != ""
                )
    }

    private fun kurGetir() {
        // SharedPreferences
        val sharedPref = activity?.getSharedPreferences(
            "kurKaydet", Context.MODE_PRIVATE
        )
        dolarDegeri = sharedPref!!.getFloat("kurGuncelleUSD", 0.45F)
        euroDegeri = sharedPref.getFloat("kurGuncelleEUR", 0.144F)
        sterlinDegeri = sharedPref.getFloat("kurGuncelleGBP", 0.1442F)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}