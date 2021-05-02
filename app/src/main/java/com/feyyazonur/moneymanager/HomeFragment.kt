package com.feyyazonur.moneymanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.feyyazonur.moneymanager.database.HarcamaDatabase
import com.feyyazonur.moneymanager.databinding.FragmentHomeBinding
import com.feyyazonur.moneymanager.manager.HarcamaViewModel

class HomeFragment : Fragment() {

    private lateinit var mHarcamaViewModel: HarcamaViewModel

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




        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_harcamaEkleFragment)
        }

        //TODO
        //önce isim fragment'deki input'u sharedpref'e ekle
        //sonra whoisbutton'a sharedpref'ten aldığın ismi yazdır

        binding.whoIsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_isimFragment)
        }


        return binding.root
    }

}