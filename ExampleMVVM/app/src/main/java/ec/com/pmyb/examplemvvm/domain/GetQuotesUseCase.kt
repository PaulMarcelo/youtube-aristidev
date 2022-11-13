package ec.com.pmyb.examplemvvm.domain

import ec.com.pmyb.examplemvvm.data.QuoteRepository
import ec.com.pmyb.examplemvvm.data.database.entities.toDatabase
import ec.com.pmyb.examplemvvm.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository: QuoteRepository) {

    suspend operator fun invoke(): List<Quote> {
        val quotes = repository.getAllQuotesFromApi()
        return if (quotes.isNotEmpty()) {
            repository.clearQuotes()
            val quotesEntitys = quotes.map { it.toDatabase() }
            repository.insertQuotes(quotesEntitys)
            quotes
        } else {
            repository.getAllQuotesFromDatabase()
        }
    }

}