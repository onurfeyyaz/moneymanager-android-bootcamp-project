package com.feyyazonur.moneymanager.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.feyyazonur.moneymanager.HarcamalarAdapter
import com.feyyazonur.moneymanager.R
import com.feyyazonur.moneymanager.databinding.FragmentHomeBinding
import com.feyyazonur.moneymanager.viewmodel.HarcamaViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var mHarcamaViewModel: HarcamaViewModel

    private var savedIsim: String? = "isim giriniz"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        // Recyclerview
        val adapter = HarcamalarAdapter()
        val recyclerView = binding.harcamaListRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // HarcamaViewModel
        mHarcamaViewModel = ViewModelProvider(this).get(HarcamaViewModel::class.java)
        mHarcamaViewModel.getAllHarcama.observe(viewLifecycleOwner, Observer { harcama ->
            adapter.setData(harcama)
        })

        binding.harcamaListRecyclerView.adapter = adapter

        if (ismiGetir()) {
            binding.whoIsButton.text = savedIsim
        }

        binding.whoIsButton.setOnClickListener {
            //findNavController().navigate(R.id.action_homeFragment_to_isimFragment)
            val action = HomeFragmentDirections.actionHomeFragmentToİsimFragment(binding.whoIsButton.text.toString())
            findNavController().navigate(action)
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_harcamaEkleFragment)
        }

        return binding.root
    }

    private fun ismiGetir(): Boolean {
        // SharedPreferences
        val sharedPref = activity?.getSharedPreferences(
            "sharedPref", Context.MODE_PRIVATE
        )
        savedIsim = sharedPref?.getString("isim", "isim giriniz")
        return !savedIsim.isNullOrEmpty()
    }

}