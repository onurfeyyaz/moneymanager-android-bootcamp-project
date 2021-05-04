package com.feyyazonur.moneymanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.feyyazonur.moneymanager.fragments.HomeFragmentDirections
import com.feyyazonur.moneymanager.model.Harcama
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.harcamalar_item_view.view.*

class HarcamalarAdapter : RecyclerView.Adapter<HarcamalarViewHolder>() {

    private var harcamaList = emptyList<Harcama>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HarcamalarViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.harcamalar_item_view, parent, false)
        return HarcamalarViewHolder(v)
    }

    override fun onBindViewHolder(holder: HarcamalarViewHolder, position: Int) {
        val currentItem = harcamaList[position]

        holder.itemView.harcama_ismi_text_view.text =
            currentItem.harcamaIsmi.toString()
        // TODO ICON ayarını burada yap
        when (currentItem.harcamaTipi) {
            2131231067 -> {
                holder.itemView.icon_of_harcama
                    .setImageResource(R.drawable.ic_outline_receipt_long_24)
            }
            2131231068 -> {
                holder.itemView.icon_of_harcama
                    .setImageResource(R.drawable.ic_outline_home_24)
            }
            else -> {
                holder.itemView.icon_of_harcama
                    .setImageResource(R.drawable.ic_outline_shopping_bag_24)
            }
        }
        holder.itemView.tutar_text_view.text =
            currentItem.harcananPara.toInt().toString()

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

    override fun getItemCount() = harcamaList.size
}