package meister.hackaton.maskserver.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import meister.hackaton.maskserver.global.filter.FilterConfig
import meister.hackaton.maskserver.global.security.token.JwtParser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val objectMapper: ObjectMapper,
    private val jwtParser: JwtParser
) {

    @Bean
    protected fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors().and()
            .csrf().disable()
            .formLogin().disable()

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
            .authorizeRequests()

            // swagger
            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/v3/api-docs/**").permitAll()
            .antMatchers("/api-docs").permitAll()

            // users
            .antMatchers(HttpMethod.POST, "/users").permitAll()

            // images
            .antMatchers(HttpMethod.POST, "/images").permitAll()

            .anyRequest().authenticated()

        http
            .apply(FilterConfig(objectMapper, jwtParser))

        return http.build()
    }

    @Bean
    protected fun PasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

}