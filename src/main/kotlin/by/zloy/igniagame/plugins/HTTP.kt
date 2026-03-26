package by.zloy.igniagame.plugins

import io.ktor.server.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.httpsredirect.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureHTTP() {
//    install(HttpsRedirect) {
//        // The port to redirect to. By default 443, the default HTTPS port.
//        sslPort = 443
//        // 301 Moved Permanently, or 302 Found redirect.
//        permanentRedirect = true
//    }
    install(DefaultHeaders) {
        header("X-Ignia", "Game") // will send this header with each response
    }
    install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024) // condition
        }
    }

}
