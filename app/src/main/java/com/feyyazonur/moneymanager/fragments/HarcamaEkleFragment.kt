package com.feyyazonur.moneymanager.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.feyyazonur.moneymanager.R
import com.feyyazonur.moneymanager.model.Harcama
import com.feyyazonur.moneymanager.databinding.FragmentHarcamaEkleBinding
import com.feyyazonur.moneymanager.viewmodel.HarcamaViewModel

class HarcamaEkleFragment : Fragment() {
    private var _binding: FragmentHarcamaEkleBinding? = null

    private val binding get() = _binding!!

    private lateinit var mHarcamaViewModel: HarcamaViewModel

    enum class HarcamaTipi { FATURA, KIRA, DIGER }
    enum class ParaBirimi { TL, DOLAR, EURO, STERLIN }

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
        val harcamaIsmi = binding.harcamaDetayTextInputEditText.text.toString()
        val harcananPara = binding.urunParasiTextInputEditText.text.toString().toFloat()
        val harcamaTipiRadioGroup = binding.radioGroup.checkedRadioButtonId
        val paraBirimiRadioGroup = binding.radioGroup2.checkedRadioButtonId

        if (inputCheck(
                harcamaIsmi,
                harcananPara.toString(),
                harcamaTipiRadioGroup.toString(),
                paraBirimiRadioGroup.toString()
            )
        ) {
            // Harcama Objesi Oluştur
            val harcama =
                Harcama(0, harcamaIsmi, harcananPara, harcamaTipiRadioGroup, paraBirimiRadioGroup)
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