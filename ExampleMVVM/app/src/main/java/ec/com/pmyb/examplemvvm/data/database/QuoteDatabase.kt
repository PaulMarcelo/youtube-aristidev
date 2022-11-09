package ec.com.pmyb.examplemvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ec.com.pmyb.examplemvvm.data.database.dao.QuoteDao
import ec.com.pmyb.examplemvvm.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase: RoomDatabase() {

    abstract fun getQuoteDao(): QuoteDao

}