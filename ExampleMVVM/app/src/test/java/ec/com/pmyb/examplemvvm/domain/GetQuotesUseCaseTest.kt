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

internal class GetQuotesUseCaseTest {

    @RelaxedMockK
//    @MockK
    private lateinit var quoteRepository: QuoteRepository

    private lateinit var getQuotestUseCase: GetQuotesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getQuotestUseCase = GetQuotesUseCase(quoteRepository)
    }

    @Test
    fun `when the api doesnt return anyhing then get values from database`() = runBlocking {
        // Given
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()
        // When
        getQuotestUseCase()
        // then
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }
    }

    @Test
    fun `when the apiss return something then get values from api`() = runBlocking {
        // Given
        val list = listOf(Quote("",""))
        coEvery { quoteRepository.getAllQuotesFromApi() } returns list
        // When
        val response = getQuotestUseCase()
        // then
        coVerify(exactly = 1) { quoteRepository.clearQuotes() }
        coVerify(exactly = 1) { quoteRepository.insertQuotes(any()) }
        coVerify(exactly = 0) { quoteRepository.getAllQuotesFromDatabase() }
        assert(response == list)
    }

}