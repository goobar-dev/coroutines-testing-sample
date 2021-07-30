import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class ExampleTest1 {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `example test 1`() {
        assert(true)
    }

    @Test
    fun `run a suspending function from test`() = runBlocking {
        helloWorld()
    }

    @Test
    fun `get long running calculation`() = runBlocking {
        val result = longRunningCalculation()

        assert(result == 5)
    }

    @Test
    fun `get long running calculation with accelerated time`() = runBlockingTest {
        val result = longRunningCalculation()
        advanceTimeBy(2000)

        assert(result == 5)
    }

    @Test
    fun `get long running calculation with IO dispatcher`() = runBlockingTest(testDispatcher) {
        val result = longRunningIOCalculation(testDispatcher)

        assert(result == 5)
    }

    @Test
    fun `basic flow test`() = runBlockingTest {
        val firstLanguage = getProgrammingLanguages().first()

        assert(firstLanguage == "Kotlin")

        assert(getProgrammingLanguages().count() == 3)
    }
}