package com.feyyazonur.moneymanager

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.feyyazonur.moneymanager.database.Harcama
import com.feyyazonur.moneymanager.model.HarcamaData
import com.google.android.material.textview.MaterialTextView

class HarcamalarAdapter : RecyclerView.Adapter<HarcamalarViewHolder>() {

    private var harcamaList = emptyList<Harcama>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HarcamalarViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.harcamalar_item_view, parent, false)
        return HarcamalarViewHolder(v)
    }

    override fun onBindViewHolder(holder: HarcamalarViewHolder, position: Int) {
        val currentItem = harcamaList[position]

        holder.itemView.findViewById<MaterialTextView>(R.id.harcama_ismi_recycler_view).text = currentItem.harcamaIsmi.toString()
        // TODO ICON ayarını burada yap
        //holder.itemView.findViewById<ImageView>(R.id.icon_of_harcama)
        holder.itemView.findViewById<MaterialTextView>(R.id.tutar_text_view).text = currentItem.harcananPara.toInt().toString()
    }
    fun setData(harcama: List<Harcama>){
        this.harcamaList = harcama
        notifyDataSetChanged()
    }

    override fun getItemCount() = harcamaList.size
}