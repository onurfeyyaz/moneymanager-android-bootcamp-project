package com.feyyazonur.moneymanager.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.feyyazonur.moneymanager.databinding.FragmentViewPagerBinding
import com.feyyazonur.moneymanager.onboarding.screens.FirstScreen
import com.feyyazonur.moneymanager.onboarding.screens.SecondScreen
import com.feyyazonur.moneymanager.onboarding.screens.ThirdScreen


class ViewPagerFragment : Fragment() {

    private var _binding: FragmentViewPagerBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val view = binding.root

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}