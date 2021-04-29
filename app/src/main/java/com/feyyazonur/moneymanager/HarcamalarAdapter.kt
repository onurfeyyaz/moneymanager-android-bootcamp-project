package com.feyyazonur.moneymanager

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.feyyazonur.moneymanager.model.HarcamaData

class HarcamalarAdapter : RecyclerView.Adapter<HarcamalarViewHolder>() {

    var data = listOf<HarcamaData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HarcamalarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.harcamalar_item_view, parent, false) as TextView
        return HarcamalarViewHolder(view)
    }

    override fun onBindViewHolder(holder: HarcamalarViewHolder, position: Int) {
        val item = data[position]

        holder.textView.text = item.harcamaAdi.toString()

    }

    override fun getItemCount() = data.size
}