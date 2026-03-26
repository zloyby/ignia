# Fix Plan: Resolved UI Routing and HttpsRedirect

This plan addresses the issue where visiting `http://0.0.0.0:8080` results in an empty page and a console error "FAILURE Selector didn't match".

## Root Causes

1. **Forced HTTPS Redirect**: The `HttpsRedirect` plugin in `HTTP.kt` is active but SSL is not configured. This causes the browser to be redirected to a non-existent HTTPS server on port 443.
2. **Conflicting Root Routes**: The root path (`/`) is handled by three different components: 
   - A literal `get("/")` in `Routing.kt` (returns "Hello World!").
   - `staticResources("/", "static", index = "index.html")` in `Routing.kt`.
   - `webSocket("/")` in `Sockets.kt`.

## Proposed Changes

### [Component: Networking & HTTP]

#### [MODIFY] [HTTP.kt](file:///home/eugene/Workspace/zloy/ignia/src/main/kotlin/by/zloy/igniagame/plugins/HTTP.kt)
- Comment out or remove the `HttpsRedirect` installation to allow local development on HTTP.

### [Component: Routing]

#### [MODIFY] [Routing.kt](file:///home/eugene/Workspace/zloy/ignia/src/main/kotlin/by/zloy/igniagame/plugins/Routing.kt)
- Remove the conflicting `get("/")` and `get("/hello")` blocks to prioritize static content (index.html).

#### [MODIFY] [Sockets.kt](file:///home/eugene/Workspace/zloy/ignia/src/main/kotlin/by/zloy/igniagame/plugins/Sockets.kt)
- Change the WebSocket endpoint from `/` to `/ws` to prevent protocol conflicts on the root path.

## Verification Plan

### Automated Tests
- Run `./gradlew test` to ensure existing tests still pass.

### Manual Verification
1. Run the application: `./gradlew run`
2. Open `http://localhost:8080/` in the browser.
3. Verify that the "Welcome to Ignia Game" page (from `index.html`) is displayed instead of a blank page or "Hello World!".
4. Check the browser console to ensure the "Selector didn't match" error is gone.
