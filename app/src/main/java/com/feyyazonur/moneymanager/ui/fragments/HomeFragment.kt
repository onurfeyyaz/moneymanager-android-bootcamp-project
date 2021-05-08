package com.feyyazonur.moneymanager.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.feyyazonur.moneymanager.R
import com.feyyazonur.moneymanager.adapter.HarcamalarAdapter
import com.feyyazonur.moneymanager.databinding.FragmentHomeBinding
import com.feyyazonur.moneymanager.viewmodel.HarcamaViewModel

class HomeFragment : Fragment() {

    private lateinit var paraStatus: String
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
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // HarcamaViewModel
        mHarcamaViewModel = ViewModelProvider(this).get(HarcamaViewModel::class.java)

        mHarcamaViewModel.getAllHarcama.observe(viewLifecycleOwner, Observer { harcama ->
            adapter.setData(harcama)
        })

        mHarcamaViewModel.toplamHarcananPara.observe(viewLifecycleOwner, { para ->
            binding.toplamHarcananTv.text = getString(
                R.string.sample_tutar,
                para?.toInt()?.toString() ?: 0,
                getParaStatus("₺"),
            )//"Harcamanız\n${para?.toInt()?.toString()}"
        })

        //binding.harcamaListRecyclerView.adapter = adapter

        if (ismiGetir()) {
            binding.whoIsButton.text = savedIsim
        }

        // TL
        binding.tlButton.setOnClickListener {
            //adapter.changeParaBirimi("TL")
            mHarcamaViewModel.toplamHarcananPara.observe(viewLifecycleOwner, { para ->
                binding.toplamHarcananTv.text = getString(
                    R.string.sample_tutar,
                    para?.toInt()?.toString(),
                    getParaStatus("₺"),
                )//"Harcamanız\n${para?.toInt()?.toString()}"
            })
            adapter.changeParaBirimi(getParaStatus("TL"))
        }
        // Dolar
        binding.dolarButton.setOnClickListener {
            //adapter.changeParaBirimi("Dolar")
            mHarcamaViewModel.toplamHarcananPara.observe(viewLifecycleOwner, { para ->
                binding.toplamHarcananTv.text = getString(
                    R.string.sample_tutar,
                    para?.toInt()?.toString(),
                    getParaStatus("$"),
                )//"Harcamanız\n${para?.toInt()?.toString()}"
            })
            adapter.changeParaBirimi(getParaStatus("Dolar"))

        }
        //Euro
        binding.euroButton.setOnClickListener {
            //adapter.changeParaBirimi("Euro")
            mHarcamaViewModel.toplamHarcananPara.observe(viewLifecycleOwner, { para ->
                binding.toplamHarcananTv.text = getString(
                    R.string.sample_tutar,
                    para?.toInt()?.toString(),
                    getParaStatus("€"),
                )//"Harcamanız\n${para?.toInt()?.toString()}"
            })
            adapter.changeParaBirimi(getParaStatus("Euro"))
        }
        //Sterlin
        binding.sterlinButton.setOnClickListener {
            //adapter.changeParaBirimi("Sterlin")
            mHarcamaViewModel.toplamHarcananPara.observe(viewLifecycleOwner, { para ->
                binding.toplamHarcananTv.text = getString(
                    R.string.sample_tutar,
                    para?.toInt()?.toString(),
                    getParaStatus("£"),
                )//"Harcamanız\n${para?.toInt()?.toString()}"
            })
            adapter.changeParaBirimi(getParaStatus("Sterlin"))
        }

        binding.whoIsButton.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToKisiFragment(binding.whoIsButton.text.toString())
            findNavController().navigate(action)
        }
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_harcamaEkleFragment)
        }

        Log.d("STATUSRETRO", mHarcamaViewModel.status.value.toString())
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

    fun getParaStatus(paraBirimi: String): String {
        this.paraStatus = paraBirimi//ParaBirimiStatus.valueOf(paraBirimi)
        Log.d("---GET PARA STATUS---", paraStatus)
        return paraStatus
    }
}