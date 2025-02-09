# Spring Boot (Spring Security integration with JWT) primer

### About
This Spring Boot application contains a simple security configuration integrated with JWT.<br>
For the simplicity sake an in-memory H2 database is being used to hold information about users.<br>

### Setup
Configuration requires to set your own JWT secret in the property `jwt.secret` located in `application.properties`<br>
Default JWT token timeout is set to 1 minute, which can be changed in the property `security.jwt.expiration-time-minutes`.

### Available endpoints

* `/register` path allows to register a user <br>
e.g. (`localhost:8080/register?username=myuser&password=mypassword`)
* `/auth` path allows to perform an authentication resulting in the JWT token <br>
e.g. (`localhost:8080/auth?username=myuser&password=mypassword`)
* `/ok` path allows to test the configuration, which is accessible only for authenticated users

### Security confiugration walkthrough
* `JwtUtil`<br>
offers capabilities to generate a JWT token for the user and to verify it
* `JwtAuthenticationFilter`<br>
acts as a custom `Filter` in order to extract and verify the token
* `SecurityConfig`<br>
enables Spring Security, allows to access being unauthenticated paths `/auth` and `/register` and registers the `JwtAuthenticationFilter`
