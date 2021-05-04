package com.feyyazonur.moneymanager.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.feyyazonur.moneymanager.R
import com.feyyazonur.moneymanager.viewmodel.HarcamaViewModel
import kotlinx.android.synthetic.main.fragment_harcama_detay.view.*

class HarcamaDetayFragment : Fragment() {

    private val args by navArgs<HarcamaDetayFragmentArgs>()

    private lateinit var mHarcamaViewModel: HarcamaViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_harcama_detay, container, false)

        mHarcamaViewModel = ViewModelProvider(this).get(HarcamaViewModel::class.java)

        when (args.currentHarcama.harcamaTipi) {
            "Diğer" -> {
                view.detay_icon
                    .setImageResource(R.drawable.ic_outline_shopping_bag_24)
            }
            "Fatura" -> {
                view.detay_icon
                    .setImageResource(R.drawable.ic_outline_receipt_long_24)
            }
            "Kira" -> {
                view.detay_icon
                    .setImageResource(R.drawable.ic_outline_home_24)
            }
        }
        when (args.currentHarcama.paraBirimi) {
            "TL" -> {
                view.detay_tutar_tv.text = getString(
                    R.string.detay_para_birimi,
                    args.currentHarcama.harcananPara.toString(),
                    args.currentHarcama.paraBirimi
                )
            }"Dolar" -> {
                view.detay_tutar_tv.text = getString(
                    R.string.detay_para_birimi,
                    args.currentHarcama.harcananPara.toString(),
                    args.currentHarcama.paraBirimi
                )
            }"Euro" -> {
                view.detay_tutar_tv.text = getString(
                    R.string.detay_para_birimi,
                    args.currentHarcama.harcananPara.toString(),
                    args.currentHarcama.paraBirimi
                )
            }
            "Sterlin" -> {
                view.detay_tutar_tv.text = getString(
                    R.string.detay_para_birimi,
                    args.currentHarcama.harcananPara.toString(),
                    args.currentHarcama.paraBirimi
                )
            }
        }
        view.detay_harcama_ismi_tv.setText(args.currentHarcama.harcamaIsmi)

        view.detay_sil_btn.setOnClickListener {
            deleteItem()
        }

        return view
    }

    private fun deleteItem() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Evet") { _, _ ->
            mHarcamaViewModel.deleteHarcama(args.currentHarcama)
            Toast.makeText(
                requireContext(),
                "${args.currentHarcama.harcamaIsmi} harcaması başarıyla silindi!",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_harcamaDetayFragment_to_homeFragment)
        }
        builder.setNegativeButton("Hayır") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Silmek istemiyorsan, tamam silme (:",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_harcamaDetayFragment_to_homeFragment)
        }
        builder.setTitle("Silme İşlemi")
        builder.setMessage(
            "${
                args.currentHarcama.harcananPara.toInt().toString()
            } tutarındaki ${args.currentHarcama.harcamaIsmi} harcamasını silmek istiyor musunuz?"
        )
        builder.create().show()
    }

}