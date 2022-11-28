package text.analyzer
import io.micronaut.http.HttpRequest.POST
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions

@MicronautTest
class Exer3Test {

    @Inject
    lateinit var application: EmbeddedApplication<*>
    lateinit var client: HttpClient

    @Test
    fun testItWorks() {
        /*val call = client.exchange(
            POST("/text/read", "hello world")
                .contentType(MediaType.TEXT_PLAIN_TYPE)
                .accept(MediaType.TEXT_PLAIN_TYPE), String::class.java)
        */
        Assertions.assertTrue(application.isRunning)
    }

}
