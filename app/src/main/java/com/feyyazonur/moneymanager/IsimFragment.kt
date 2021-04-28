package com.feyyazonur.moneymanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.feyyazonur.moneymanager.databinding.FragmentIsimBinding

class IsimFragment : Fragment() {
    private var _binding: FragmentIsimBinding? = null

    private val binding get() = _binding!!

    lateinit var isim: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIsimBinding.inflate(inflater, container, false)



        binding.isimDegistirKaydetBtn.setOnClickListener {
            isim = binding.isimGirEdittext.text.toString()

            val cinsiyet = when (binding.radioGroup.checkedRadioButtonId) {
                R.id.radio_button_1 ->
                    "$isim Bey"
                R.id.radio_button_2 ->
                    "$isim Hanım"
                else ->
                    isim
            }
            val action = IsimFragmentDirections.actionİsimFragmentToHomeFragment(cinsiyet)
            findNavController().navigate(
                action
            )
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}