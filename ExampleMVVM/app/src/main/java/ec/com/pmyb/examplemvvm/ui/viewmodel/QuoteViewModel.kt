package ec.com.pmyb.examplemvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ec.com.pmyb.examplemvvm.data.model.QuoteModel
import ec.com.pmyb.examplemvvm.domain.GetQuotesUseCase
import ec.com.pmyb.examplemvvm.domain.GetRandomQuoteUseCase
import ec.com.pmyb.examplemvvm.domain.model.Quote
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel() {

    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()
            if (!result.isNullOrEmpty()) {
                quoteModel.postValue(result[0])
            }
            isLoading.postValue(false)
        }

    }

    fun randomQuote() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val currentQuote = getRandomQuoteUseCase()
            if (currentQuote != null) {
                quoteModel.postValue(currentQuote)
            }
            isLoading.postValue(false)
        }
    }


}