package ec.com.pmyb.examplemvvm.domain

import ec.com.pmyb.examplemvvm.data.QuoteRepository
import ec.com.pmyb.examplemvvm.data.model.QuoteModel
import ec.com.pmyb.examplemvvm.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val repository: QuoteRepository) {

    suspend operator fun invoke(): Quote? {
        val quotes = repository.getAllQuotesFromDatabase()
        if (quotes.isNotEmpty()) {
            val randomIndex = quotes.indices.random()
            return quotes[randomIndex]
        }
        return null
    }
}