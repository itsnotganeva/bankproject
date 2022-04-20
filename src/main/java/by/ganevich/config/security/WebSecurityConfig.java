package by.ganevich.config.security;

import by.ganevich.config.security.jwt.JwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/admin/*",
//                        "/register/operator",
//                        "/banks/add",
//                        "/banks/{id}/update",
//                        "/banks/{id}/delete",
//                        "/clients/{id}/delete").hasRole("ADMIN")
//                .antMatchers("/bank-accounts/add").hasRole("CLIENT")
//                .antMatchers("/clients/get",
//                        "/clients/{id}/get",
//                        "/clients/{id}/update",
//                        "/clients/{id}/delete").hasRole("OPERATOR")
//                .antMatchers("/register",
//                        "/auth",
//                        "/clients/{id}/bank-accounts/get",
//                        "/banks/get",
//                        "/banks/{id}/get",
//                        "/clients/{id}/transactions/get").permitAll()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
