package com.feyyazonur.moneymanager.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "harcama_listesi_table")
data class Harcama(
    @PrimaryKey(autoGenerate = true)
    var harcamaId: Long = 0L,

    @ColumnInfo(name = "harcama_ismi")
    var harcamaIsmi: String,

    @ColumnInfo(name = "harcama_aciklamasi")
    var harcamaAciklamasi: String,

    @ColumnInfo(name = "harcama_tipi")
    var harcamaTipi: String,

    @ColumnInfo(name = "para_birimi")
    var paraBirimi: String
)