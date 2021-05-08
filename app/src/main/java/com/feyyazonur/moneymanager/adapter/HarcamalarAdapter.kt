package com.feyyazonur.moneymanager.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.feyyazonur.moneymanager.R
import com.feyyazonur.moneymanager.model.Harcama
import com.feyyazonur.moneymanager.ui.fragments.HomeFragmentDirections
import kotlinx.android.synthetic.main.harcamalar_item_view.view.*
import kotlin.math.roundToInt

class HarcamalarAdapter : RecyclerView.Adapter<HarcamalarViewHolder>() {

    private var harcamaList = emptyList<Harcama>()

    var paraBirimi: String = "TL"
    private lateinit var harcamaTipi: String

    private var tutar: Int = 0
    private var oran = .0


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


        when (paraBirimi) {
            "Dolar" -> {
                tutar = (tutar * oran).roundToInt()
                holder.itemView.tutar_text_view.text = holder.itemView.context.getString(
                    R.string.detay_para_birimi,
                    tutar.toString(),
                    "$"
                )
                currentItem.paraBirimi = paraBirimi
            }
            "Euro" -> {
                tutar = (tutar * oran).roundToInt()
                holder.itemView.tutar_text_view.text = holder.itemView.context.getString(
                    R.string.detay_para_birimi,
                    tutar.toString(),
                    "€"
                )
                currentItem.paraBirimi = paraBirimi
            }
            "Sterlin" -> {
                tutar = (tutar * oran).roundToInt()
                holder.itemView.tutar_text_view.text = holder.itemView.context.getString(
                    R.string.detay_para_birimi,
                    tutar.toString(),
                    "£"
                )
                currentItem.paraBirimi = paraBirimi
            }
            "TL" -> {
                tutar = (tutar * oran).roundToInt()
                holder.itemView.tutar_text_view.text = holder.itemView.context.getString(
                    R.string.detay_para_birimi,
                    tutar.toString(),
                    "₺"
                )
                currentItem.paraBirimi = paraBirimi
            }
        }

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

    fun getOran(paraBirimi: String, oran: Double): Double {
        this.oran = oran
        this.paraBirimi = paraBirimi
        Log.d("ADAPTER ORAN: ", oran.toString())
        Log.d("ADAPTER PARA B", paraBirimi)
        notifyDataSetChanged()
        return oran
    }

    override fun getItemCount() = harcamaList.size
}