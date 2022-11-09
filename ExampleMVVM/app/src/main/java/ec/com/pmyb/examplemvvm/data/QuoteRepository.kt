package ec.com.pmyb.examplemvvm.data

import ec.com.pmyb.examplemvvm.data.database.dao.QuoteDao
import ec.com.pmyb.examplemvvm.data.database.entities.QuoteEntity
import ec.com.pmyb.examplemvvm.data.model.QuoteModel
import ec.com.pmyb.examplemvvm.data.network.QuoteService
import ec.com.pmyb.examplemvvm.domain.model.Quote
import ec.com.pmyb.examplemvvm.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {

    suspend fun getAllQuotesFromApi(): List<Quote> {
        val response: List<QuoteModel> = api.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase():List<Quote>{
        val response: List<QuoteEntity> = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes:List<QuoteEntity>){
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes(){
        quoteDao.deleteAllQuotes()
    }
}