package by.zloy.igniagame

import by.zloy.igniagame.plugins.configureRouting
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.time.Duration
import io.ktor.serialization.jackson.*
import com.fasterxml.jackson.databind.*
import io.ktor.server.plugins.*
import io.ktor.server.webjars.*
import java.time.*
import io.ktor.server.http.content.*
import io.ktor.server.auth.*
import io.ktor.util.*
import io.ktor.server.sessions.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlin.test.*
import io.ktor.server.testing.*
import by.zloy.igniagame.plugins.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
            configureSockets()
            configureSerialization()
            configureHTTP()
            configureSecurity()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertTrue(bodyAsText().contains("Welcome to Ignia Game"))
        }
    }
    
    @Test
    fun testHelloEndpoint() = testApplication {
        application {
            configureRouting()
            configureSockets()
            configureSerialization()
            configureHTTP()
            configureSecurity()
        }
        client.get("/hello").apply {
            assertEquals(HttpStatusCode.NotFound, status)
        }
    }
}