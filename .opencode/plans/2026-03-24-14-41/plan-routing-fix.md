# Routing Fix Plan for Ignia Game Project

## Problem Analysis
The project is experiencing a "Selector didn't match" error, which suggests routing issues. Based on the project structure, I can see:
1. The main Application.kt file calls configureRouting() in the embeddedServer
2. The Routing.kt plugin implements a basic routing structure with "/" endpoint
3. The test is failing with NoClassDefFoundError, suggesting the issue might be in test environment setup rather than routing logic

## Root Cause Analysis
The error likely occurs because:
1. Ktor 3.x has changed routing behavior compared to 2.x
2. There might be missing or incorrect dependency versions
3. The test environment isn't properly configured for Ktor 3
4. Routing might be conflicting or not properly registered in a specific environment

## Fix Steps

### Step 1: Update Routing Configuration
- Ensure proper routing registration in the application module
- Check if the routing needs to be in a specific module context

### Step 2: Fix Test Environment
- Properly set up test dependencies for Ktor 3
- Update testApplication usage to match Ktor 3 conventions
- Ensure all required plugins are properly initialized

### Step 3: Run Local Testing
- Test the application locally to confirm the fix works
- Check the specific endpoint routes for proper registration

## Testing Plan
1. Run `./gradlew test` to verify the test environment works
2. Run `./gradlew run` to verify application starts properly
3. Verify routing by hitting:
   - http://localhost:8080/ (should return static page index.html)
   - http://localhost:8080/hello (should return "Hello World!")
   - http://localhost:8080/webjars (should return HTML with script tag)
4. Test both static and websocket routing functionality

## Implementation Checklist
- [ ] Update tests  
- [ ] Verify routing registration order 
- [ ] Fix any bugs
- [ ] Run all tests to confirm no regressions
- [ ] Test local application startup
- [ ] Confirm endpoint accessibility