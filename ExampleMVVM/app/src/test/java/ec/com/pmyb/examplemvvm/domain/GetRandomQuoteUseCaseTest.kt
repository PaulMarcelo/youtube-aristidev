package ec.com.pmyb.examplemvvm.domain

import ec.com.pmyb.examplemvvm.data.QuoteRepository
import ec.com.pmyb.examplemvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetRandomQuoteUseCaseTest {


    @RelaxedMockK
//    @MockK
    private lateinit var quoteRepository: QuoteRepository

    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRepository)
    }

    @Test
    fun `when database is empty then null`() = runBlocking {
        // Given
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns emptyList()
        // When
        val response = getRandomQuoteUseCase()
        // then
        assert(response == null)
    }

    @Test
    fun `when database is not empty then return Quote`() = runBlocking {
        // Given
        val list = listOf(Quote("", ""))
        val quote = Quote("", "")
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns list
        // When
        val response = getRandomQuoteUseCase()
        // then
        assert(response != null)
        assert(response == list.first())
    }

}