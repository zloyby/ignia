package by.zloy.igniagame.plugins

import io.ktor.server.auth.*
import io.ktor.util.*
import io.ktor.server.sessions.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

fun Application.configureSecurity() {

    @Serializable
    data class MySession(val count: Int = 0)
    install(Sessions) {
        cookie<MySession>("X_IGNIA_SESSION") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

    routing {
        get("/session/increment") {
            val session = call.sessions.get<MySession>() ?: MySession()
            call.sessions.set(session.copy(count = session.count + 1))
            call.respondText("Counter is ${session.count}. Refresh to increment.")
        }
    }
}
