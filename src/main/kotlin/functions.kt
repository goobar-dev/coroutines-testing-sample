import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext

suspend fun helloWorld() {
    println("Hello World!")
}

suspend fun longRunningCalculation(): Int {
    delay(5000)
    return 5
}

suspend fun longRunningIOCalculation(dispatcher: CoroutineDispatcher = Dispatchers.IO): Int = withContext(dispatcher) {
    delay(5000)
    5
}

fun getProgrammingLanguages(): Flow<String> = flowOf("Kotlin", "Java", "C++")