package com.feyyazonur.moneymanager.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.feyyazonur.moneymanager.R
import com.feyyazonur.moneymanager.databinding.FragmentKisiBinding


class KisiFragment : Fragment() {

    private var _binding: FragmentKisiBinding? = null

    private val binding get() = _binding!!
    private val args by navArgs<KisiFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentKisiBinding.inflate(inflater, container, false)

        var isim = args.currentIsim

        when{
            isim.contains("Hanım") -> {
                binding.radioGroup.check(binding.radioButton2.id)
                // trim ekledim çünkü her kaydet'e bastığımda isim'in sonuna boşluk ekliyordu
                // birden fazla kez, art arda kaydet butonuna basınca "feyyaz    bey" gibi bir string çıkıyordu.
                isim = isim.replace("Hanım", "").trim()
            }
            isim.contains("Bey") -> {
                binding.radioGroup.check(binding.radioButton1.id)
                isim = isim.replace("Bey", "").trim()
            }
            isim.contains("isim giriniz") -> {
                isim = isim.replace("isim giriniz", "").trim()
            }
            else -> binding.radioGroup.check(binding.radioButton3.id)
        }

        binding.isimGirEdittext.setText(isim)


        binding.isimDegistirKaydetBtn.setOnClickListener {
            ismiKaydet()
            val action = KisiFragmentDirections.actionKisiFragmentToHomeFragment()
            findNavController().navigate(
                action
            )
        }

        return binding.root
    }

    private fun ismiKaydet() {
        val justIsim = binding.isimGirEdittext.text.toString()
        val cinsiyetli = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radio_button_1 ->
                "$justIsim Bey"
            R.id.radio_button_2 ->
                "$justIsim Hanım"
            else ->
                justIsim
        }
        val sharedPref = activity?.getSharedPreferences(
            "sharedPref", Context.MODE_PRIVATE
        )
        val editor = sharedPref!!.edit()
        editor.apply {
            putString("isim", cinsiyetli)
        }.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}