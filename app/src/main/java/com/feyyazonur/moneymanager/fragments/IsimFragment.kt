package com.feyyazonur.moneymanager.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.feyyazonur.moneymanager.R
import com.feyyazonur.moneymanager.databinding.FragmentIsimBinding


class IsimFragment : Fragment() {

    private var _binding: FragmentIsimBinding? = null

    private val binding get() = _binding!!
    private val args by navArgs<IsimFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIsimBinding.inflate(inflater, container, false)

        var isim = args.currentIsim

        if (isim.contains("Hanım")){
            binding.radioGroup.check(binding.radioButton2.id)
            isim = isim.replace("Hanım", "")
        }

        else if (args.currentIsim.contains("Bey")){
            binding.radioGroup.check(binding.radioButton1.id)
            isim = isim.replace("Bey", "")
        }
        else
            binding.radioGroup.check(binding.radioButton3.id)

        binding.isimGirEdittext.setText(isim)


        binding.isimDegistirKaydetBtn.setOnClickListener {
            ismiKaydet()
            val action = IsimFragmentDirections.actionİsimFragmentToHomeFragment()
            findNavController().navigate(
                action
            )
        }

        return binding.root
    }

    private fun ismiKaydet(){
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