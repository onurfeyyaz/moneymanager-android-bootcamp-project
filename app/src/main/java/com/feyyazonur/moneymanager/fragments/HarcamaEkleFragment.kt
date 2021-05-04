package com.feyyazonur.moneymanager.fragments

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

    enum class HarcamaTipi { FATURA, KIRA, DIGER }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHarcamaEkleBinding.inflate(inflater, container, false)

        mHarcamaViewModel = ViewModelProvider(this).get(HarcamaViewModel::class.java)

        binding.harcamaEkleButton.setOnClickListener {
            insertDataToDatabase()
        }

        //binding.lifecycleOwner = this
        return binding.root
    }

    private fun insertDataToDatabase() {
        val harcamaTipiRadioGroup = binding.radioGroup.checkedRadioButtonId
        val paraBirimiRadioGroup = binding.radioGroup2.checkedRadioButtonId

        lateinit var paraBirimi: String
        lateinit var harcamaTipi: String

        when (harcamaTipiRadioGroup){
            R.id.radio_button_1 -> harcamaTipi = binding.radioButton1.text.toString()
            R.id.radio_button_2 -> harcamaTipi = binding.radioButton2.text.toString()
            R.id.radio_button_3 -> harcamaTipi = binding.radioButton3.text.toString()
        }
        Log.d("HARCAMA_TIPI", harcamaTipi.toString())

        when (paraBirimiRadioGroup) {
            R.id.radio_button_tl -> paraBirimi = binding.radioButtonTl.text.toString()
            R.id.radio_button_usd -> paraBirimi = binding.radioButtonUsd.text.toString()
            R.id.radio_button_eur -> paraBirimi = binding.radioButtonEur.text.toString()
            R.id.radio_button_gbp -> paraBirimi = binding.radioButtonGbp.text.toString()
        }
        Log.d("PARA_BIRIMI", paraBirimi.toString())

        val harcamaIsmi = binding.harcamaDetayTextInputEditText.text.toString()
        val harcananPara = binding.urunParasiTextInputEditText.text.toString().toFloat()

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
        else{
            Toast.makeText(requireContext(), "Tüm alanları doldurun.", Toast.LENGTH_LONG).show()
        }
    }


    private fun inputCheck(
        harcamaIsmi: String?,
        harcananPara: String?,
        harcamaTipi: String?,
        paraBirimi: String?
    ): Boolean {
        return !(
                harcamaIsmi.isNullOrBlank()
                        && harcananPara.isNullOrBlank()
                        && harcamaTipi.isNullOrBlank()
                        && paraBirimi.isNullOrBlank()
                )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}