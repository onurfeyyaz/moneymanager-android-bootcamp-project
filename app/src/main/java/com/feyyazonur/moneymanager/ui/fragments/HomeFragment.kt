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
import kotlin.math.roundToInt

class HomeFragment : Fragment() {

    private lateinit var mHarcamaViewModel: HarcamaViewModel

    private var savedIsim: String? = "isim giriniz"

    private var dolarDegeri: Float = 0.0f
    private var euroDegeri: Float = 0.0f
    private var sterlinDegeri: Float = 0.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        // Recyclerview
        val adapter = HarcamalarAdapter()
        val recyclerView = binding.harcamaListRecyclerView
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mHarcamaViewModel = ViewModelProvider(this).get(HarcamaViewModel::class.java)

        mHarcamaViewModel.getAllHarcama.observe(viewLifecycleOwner, Observer { harcama ->
            adapter.setData(harcama)
            adapter.getOran("TL", 1.0)
        })

        mHarcamaViewModel.toplamHarcananPara.observe(viewLifecycleOwner, { para ->
            binding.toplamHarcananTv.text = getString(
                R.string.sample_tutar,
                para?.toInt()?.toString() ?: 0,
                "₺",
            )
        })

        if (ismiGetir()) {
            binding.whoIsButton.text = savedIsim
        }
        kurGetir()

        // TL
        binding.tlButton.setOnClickListener {
            mHarcamaViewModel.toplamHarcananPara.observe(viewLifecycleOwner, { para ->
                binding.toplamHarcananTv.text = getString(
                    R.string.sample_tutar,
                    para?.roundToInt()?.toString() ?: 0,
                    "₺",
                )//"Harcamanız\n${para?.toInt()?.toString()}"
            })
            adapter.getOran("TL", 1.0)
        }
        // Dolar
        binding.dolarButton.setOnClickListener {
            mHarcamaViewModel.toplamHarcananPara.observe(viewLifecycleOwner, { para ->

                binding.toplamHarcananTv.text = getString(
                    R.string.sample_tutar,
                    (para?.toDouble()?.times(dolarDegeri))?.roundToInt().toString(),
                    "$",
                )//"Harcamanız\n${para?.toInt()?.toString()}"
            })
            adapter.getOran("Dolar", dolarDegeri.toDouble())
            Log.d("HF DOLAR DEĞER", dolarDegeri.toString())

        }
        //Euro
        binding.euroButton.setOnClickListener {
            mHarcamaViewModel.toplamHarcananPara.observe(viewLifecycleOwner, { para ->
                binding.toplamHarcananTv.text = getString(
                    R.string.sample_tutar,
                    (para?.toDouble()?.times(euroDegeri))?.roundToInt().toString(),
                    "€",
                )//"Harcamanız\n${para?.toInt()?.toString()}"
            })
            adapter.getOran("Euro", euroDegeri.toDouble())
            Log.d("HF EURO DEĞER", euroDegeri.toString())

        }
        //Sterlin
        binding.sterlinButton.setOnClickListener {
            mHarcamaViewModel.toplamHarcananPara.observe(viewLifecycleOwner, { para ->
                binding.toplamHarcananTv.text = getString(
                    R.string.sample_tutar,
                    (para?.toDouble()?.times(sterlinDegeri))?.roundToInt().toString(),
                    "£",
                )//"Harcamanız\n${para?.toInt()?.toString()}"
            })
            adapter.getOran("Sterlin", sterlinDegeri.toDouble())
            Log.d("HF STERLIN DEĞER", sterlinDegeri.toString())
        }

        binding.whoIsButton.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToKisiFragment(binding.whoIsButton.text.toString())
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

    private fun kurGetir() {
        val sharedPref = activity?.getSharedPreferences(
            "kurKaydet", Context.MODE_PRIVATE
        )
        dolarDegeri = sharedPref!!.getFloat("kurGuncelleUSD", 0.12F)
        euroDegeri = sharedPref.getFloat("kurGuncelleEUR", 0.09F)
        sterlinDegeri = sharedPref.getFloat("kurGuncelleGBP", 0.08F)
    }
}