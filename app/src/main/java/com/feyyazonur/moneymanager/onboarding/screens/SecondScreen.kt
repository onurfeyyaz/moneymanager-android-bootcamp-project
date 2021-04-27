package com.feyyazonur.moneymanager.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.feyyazonur.moneymanager.R
import com.feyyazonur.moneymanager.databinding.FragmentFirstScreenBinding
import com.feyyazonur.moneymanager.databinding.FragmentSecondScreenBinding

class SecondScreen : Fragment() {
    private var _binding: FragmentSecondScreenBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondScreenBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.button2.setOnClickListener{
            viewPager?.currentItem = 2
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}