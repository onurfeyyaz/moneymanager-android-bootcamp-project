package com.feyyazonur.moneymanager

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.feyyazonur.moneymanager.database.Harcama
import com.feyyazonur.moneymanager.database.HarcamaDatabase
import com.feyyazonur.moneymanager.database.HarcamaDatabaseDao
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class HarcamaDatabaseTest {

    private lateinit var harcamaDao: HarcamaDatabaseDao
    private lateinit var db: HarcamaDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, HarcamaDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        harcamaDao = db.harcamaDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    suspend fun insertAndGetParaBirimi() {
        val paraBirimi = Harcama(1,"ayakkabı", "trendyoldan aldım", "ihtiyaç", "TL")
        harcamaDao.insert(paraBirimi)
        val lastHarcama = harcamaDao.getLastHarcama()
        assertEquals(lastHarcama?.harcamaIsmi, "ayakkabı")
    }
}


