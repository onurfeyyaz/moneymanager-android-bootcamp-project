package com.feyyazonur.moneymanager.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "harcama_listesi_table")
data class Harcama(
    @PrimaryKey(autoGenerate = true)
    var harcamaId: Long = 0L,

    @ColumnInfo(name = "harcama_ismi")
    var harcamaIsmi: String,

    @ColumnInfo(name = "harcanan_para")
    var harcananPara: Float,

    @ColumnInfo(name = "harcama_tipi")
    var harcamaTipi: Int,

    @ColumnInfo(name = "para_birimi")
    var paraBirimi: Int
): Parcelable