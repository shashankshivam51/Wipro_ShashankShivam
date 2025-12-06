package com.myfinbank.customer.config;

import com.myfinbank.customer.model.Customer;
import com.myfinbank.customer.repository.CustomerRepository;
import com.myfinbank.customer.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomerRepository customerRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(CustomerRepository customerRepository,
                          JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.customerRepository = customerRepository;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                // JWT login API open
                .requestMatchers("/api/auth/**").permitAll()
                // Any other /api/** requires JWT
                .requestMatchers("/api/**").authenticated()
                // Everything else (your JSP + /customer/**) stays open as before
            .anyRequest().permitAll()
            )

            // We are not using Spring’s username/password auth
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())

            // Google OAuth2 login (for browser flow)
            .oauth2Login(oauth -> oauth
                .loginPage("/customer/login")
                .successHandler(googleSuccessHandler())
            );

        // Add JWT filter before the standard username/password filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler googleSuccessHandler() {
        return (request, response, authentication) -> {
            Object principal = authentication.getPrincipal();
            if (!(principal instanceof OAuth2User oAuth2User)) {
                response.sendRedirect("/customer/login?error=google_auth");
                return;
            }

            String email = (String) oAuth2User.getAttributes().get("email");

            if (email == null || email.isBlank()) {
                response.sendRedirect("/customer/login?error=google_no_email");
                return;
            }

            // Using your existing repo method
            Customer customer = customerRepository.findFirstByEmailOrderByIdAsc(email);

            if (customer == null) {
                response.sendRedirect("/customer/login?error=google_not_registered");
                return;
            }

            if (!"ACTIVE".equalsIgnoreCase(customer.getStatus())) {
                response.sendRedirect("/customer/login?error=account_inactive");
                return;
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("loggedInCustomerId", customer.getId());

            response.sendRedirect("/customer/dashboard/" + customer.getId());
        };
    }
}
