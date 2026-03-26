package by.zloy.igniagame.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.webjars.*
import java.time.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    install(Webjars) {
        path = "/webjars" //defaults to /webjars
    }

    routing {

        // Static plugin. Try to access `/static/index.html`
        staticResources("/", "static", index = "index.html")
    }
}
