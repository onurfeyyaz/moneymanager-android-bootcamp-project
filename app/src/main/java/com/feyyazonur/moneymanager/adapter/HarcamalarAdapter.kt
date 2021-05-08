package com.feyyazonur.moneymanager.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.feyyazonur.moneymanager.R
import com.feyyazonur.moneymanager.ui.fragments.HomeFragmentDirections
import com.feyyazonur.moneymanager.model.Harcama
import kotlinx.android.synthetic.main.harcamalar_item_view.view.*
import java.util.*
import kotlin.math.roundToInt

class HarcamalarAdapter : RecyclerView.Adapter<HarcamalarViewHolder>() {

    private var harcamaList = emptyList<Harcama>()

    private var paraBirimi: String = "TL"
    private lateinit var harcamaTipi: String

    private var tutar: Int = 0

    enum class PARA(val deger: Double) {
        TL(1.0),
        DOLAR(8.0),
        EURO(10.0),
        STERLIN(11.0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HarcamalarViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.harcamalar_item_view, parent, false)
        return HarcamalarViewHolder(v)
    }

    override fun onBindViewHolder(holder: HarcamalarViewHolder, position: Int) {
        val currentItem = harcamaList[position]

        harcamaTipi = currentItem.harcamaTipi
        tutar = currentItem.harcananPara.toInt()

        holder.itemView.harcama_ismi_text_view.text =
            currentItem.harcamaIsmi


        //if (paraBirimi == currentItem.paraBirimi) {
        val cevrilenTutar =
            paraDegeriniCevir(tutar, PARA.valueOf(paraBirimi.toUpperCase(Locale.ROOT)).deger)
        Log.d("---CEVRİLEN_TUTAR---", cevrilenTutar.toString())
        when (paraBirimi) {
            "Dolar" -> {
                holder.itemView.tutar_text_view.text = holder.itemView.context.getString(
                    R.string.detay_para_birimi,
                    cevrilenTutar.toString(),
                    "$"
                )
                currentItem.paraBirimi = paraBirimi
            }
            "Euro" -> {
                holder.itemView.tutar_text_view.text = holder.itemView.context.getString(
                    R.string.detay_para_birimi,
                    cevrilenTutar.toString(),
                    "€"
                )
                currentItem.paraBirimi = paraBirimi
            }
            "Sterlin" -> {
                holder.itemView.tutar_text_view.text = holder.itemView.context.getString(
                    R.string.detay_para_birimi,
                    cevrilenTutar.toString(),
                    "£"
                )
                currentItem.paraBirimi = paraBirimi
            }
            "TL" -> {
                holder.itemView.tutar_text_view.text = holder.itemView.context.getString(
                    R.string.detay_para_birimi,
                    cevrilenTutar.toString(),
                    "₺"
                )
                currentItem.paraBirimi = paraBirimi
            }
        }
        //}
        /*else{
            when (paraBirimi) {
                "Dolar" -> {
                    holder.itemView.tutar_text_view.text = holder.itemView.context.getString(
                        R.string.detay_para_birimi,
                        tutar.toString(),
                        "$"
                    )
                    currentItem.paraBirimi = paraBirimi
                }
                "Euro" -> {
                    holder.itemView.tutar_text_view.text = holder.itemView.context.getString(
                        R.string.detay_para_birimi,
                        tutar.toString(),
                        "€"
                    )
                    currentItem.paraBirimi = paraBirimi
                }
                "Sterlin" -> {
                    holder.itemView.tutar_text_view.text = holder.itemView.context.getString(
                        R.string.detay_para_birimi,
                        tutar.toString(),
                        "£"
                    )
                    currentItem.paraBirimi = paraBirimi
                }
                "TL" -> {
                    holder.itemView.tutar_text_view.text = holder.itemView.context.getString(
                        R.string.detay_para_birimi,
                        tutar.toString(),
                        "₺"
                    )
                    currentItem.paraBirimi = paraBirimi
                }
            }
        }*/


        // TODO ICON ayarını burada yap
        when (harcamaTipi) {
            "Fatura" -> {
                holder.itemView.icon_of_harcama
                    .setImageResource(R.drawable.ic_outline_receipt_long_24)
            }
            "Kira" -> {
                holder.itemView.icon_of_harcama
                    .setImageResource(R.drawable.ic_outline_home_24)
            }
            else -> {
                holder.itemView.icon_of_harcama
                    .setImageResource(R.drawable.ic_outline_shopping_bag_24)
            }
        }

        holder.itemView.harcamalar_item_card_view.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToHarcamaDetayFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(harcama: List<Harcama>) {
        this.harcamaList = harcama
        notifyDataSetChanged()
    }

    fun changeParaBirimi(paraBirimi: String) {
        this.paraBirimi = paraBirimi
        notifyDataSetChanged()
    }


    fun paraDegeriniCevir(tutar: Int, birim: Double): Int {
        return (tutar.toDouble() * birim).roundToInt()
    }

    override fun getItemCount() = harcamaList.size
}