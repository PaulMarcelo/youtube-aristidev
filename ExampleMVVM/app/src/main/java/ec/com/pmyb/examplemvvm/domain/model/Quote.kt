package ec.com.pmyb.examplemvvm.domain.model

import ec.com.pmyb.examplemvvm.data.database.entities.QuoteEntity
import ec.com.pmyb.examplemvvm.data.model.QuoteModel

data class Quote (val quote:String, val author:String)

fun QuoteModel.toDomain() = Quote(quote, author)
fun QuoteEntity.toDomain() = Quote(quote, author)
